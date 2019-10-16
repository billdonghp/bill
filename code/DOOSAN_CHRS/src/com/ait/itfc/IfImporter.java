package com.ait.itfc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.itfc.bean.Mapping;
import com.ait.itfc.bean.Parameter;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

/**
 * 导数据~~~~~~~~~~~~~~~~~
 * @author Pennix
 *
 */
public class IfImporter {

	/**
	 * 执行更新操作的连接,更新过程中需要再进行只读查询操作的,需另行开连接
	 */
	private Connection connForUpdate;

	/**
	 * 接口参数:上次同步时间
	 */
	private Timestamp lastTime;

	/**
	 * 接口参数:接口用户
	 */
	private String ifUser;

	/**
	 * 接口参数:接口表中用以标志最后更新时间的列名
	 */
	private String stampColumn;

	/**
	 * 本次同步时间
	 */
	private Timestamp thisTime;

	private List<Mapping> mappings = new ArrayList<Mapping>();

	private static final Logger logger = Logger.getLogger(IfImporter.class);

	public IfImporter() {
		thisTime = new Timestamp(new GregorianCalendar().getTimeInMillis());
		this.getParams();
		this.getMappings();
		logger.debug("New Instance initialized");
	}
	
	private static int syncCalcFlag = 0 ; // 是否在进行同步的标示
	
	

	public static synchronized final int getSyncCalcFlag() {
		return syncCalcFlag;
	}

	public static synchronized final void setSyncCalcFlag(int syncCalcFlag) {
		IfImporter.syncCalcFlag = syncCalcFlag;
	}

	/**
	 * 主调方法
	 */
	public String start() {
		String result="";
		if(this.syncCalcFlag==0){
			try {
				this.setSyncCalcFlag(1);
				
				// 刷新接口表
				this.executeRefreshInfTables() ;
				
				connForUpdate = ConnBean.getConn();
				connForUpdate.setAutoCommit(false);
				for (Mapping mapping : mappings) {
					this.execute(mapping);
				}
				this.updateLastTime();
				connForUpdate.commit();
				
				// 执行一些不能通过配置完成的存储
				connForUpdate = ConnBean.getConn();
				this.executeProcedure() ;
				
				result="同步人事数据成功！";
			} catch (Exception e) {
				result="同步人事数据失败！";
				logger.error(e.toString());
				e.printStackTrace();
				throw new GlRuntimeException("接口操作失败", e);
			} finally {
				SqlUtil.close(connForUpdate);		
				this.setSyncCalcFlag(0);
			}			
		}else{
			result="对不起，人事信息正在同步中，请稍后进行同步";
		}
		return result;
	}
	
	/**
	 * 刷新指定接口表
	 * @param 
	 * @return
	 */
	private void executeRefreshInfTables() throws Exception {
		logger.debug("executeRefreshInfTables");
		Connection conn = null;
		CallableStatement cs = null; // 建立参数声明
		try {

			conn = ConnBean.getConnIfEhr();
			cs = conn.prepareCall("{call REFRESH_INF_TABLES}");
			cs.execute();
			
		} catch (Exception e) {
			logger.error("接口发生错误, 请等待", e);
			throw new GlRuntimeException("接口发生错误, 请等待", e);
		} finally {
			SqlUtil.close(cs, conn);
		}

	}
	
	/**
	 * 执行额外的存储
	 * 
	 * @throws Exception
	 */
	private void executeProcedure() throws Exception {
		logger.debug("executeProcedure");
		CallableStatement cs = null; // 建立参数声明
		try{
			cs = this.connForUpdate.prepareCall("{call IF_IMPORT}");
			cs.execute();
		}catch (SQLException e) {
			throw e ;
		} finally {
			SqlUtil.close(cs);
		}
	}


	/**
	 * 主更新方法
	 * @param mapping
	 * @throws Exception
	 */
	private void execute(Mapping mapping) throws Exception {
		String ifTable = mapping.getIfTable();
		List<Map<String, Object>> ifDatas = this.getIfData(ifTable);
		if (ifDatas.size() > 0) {
			String emTable = mapping.getEmTable();
			Set<String> emPkColumns = this.getPkColumn(emTable);
			Set<String> emUkColumns = this.getUkColumn(emTable);
			Map<String, Integer> emColumnTypes = this.getColumnTypes(emTable);
			List<Parameter> params = new ArrayList<Parameter>();
			StringBuffer insert = new StringBuffer();
			StringBuffer update = new StringBuffer();
			update.append("UPDATE ");
			update.append(emTable);
			update.append(" SET ");
			insert.append("INSERT INTO ");
			insert.append(emTable);
			insert.append(" (");
			int paramSequence = 0;
			for (String emColumn : mapping.getEmColumns()) {
				if (!emPkColumns.contains(emColumn)) {
					
					if (!emUkColumns.contains(emColumn)&& !emColumn.equals("POST_GRADE_YEAR")) {
						update.append(emColumn);
						update.append(" = ?, ");
					}
					
					insert.append(emColumn);
					insert.append(", ");
					params.add(new Parameter(++paramSequence, emColumnTypes.get(emColumn), mapping.getFormula(emColumn), emColumn));
				}
			}
			update.delete(update.length() - 2, update.length());
			update.append(" WHERE ");
			for (String emPkColumn : emPkColumns) {
				
				update.append(emPkColumn);
				update.append(" = ? AND ");
				insert.append(emPkColumn);
				insert.append(", ");
				params.add(new Parameter(++paramSequence, emColumnTypes.get(emPkColumn), mapping.getFormula(emPkColumn), emPkColumn));
			}
			logger.debug(update.toString());
			update.delete(update.length() - 5, update.length());
			insert.delete(insert.length() - 2, insert.length());
			insert.append(") VALUES (");
			
			for (int i = 0; i < mapping.getEmColumns().size(); ++i)
				insert.append("?, ");
			
			insert.delete(insert.length() - 2, insert.length());
			insert.append(")");
			logger.debug(update.toString());
			logger.debug(insert.toString());
			for (Map<String, Object> ifData : ifDatas) {
				
				PreparedStatement pstmt = connForUpdate.prepareStatement(update.toString());
				paramSequence = 0;
				//TODO 缓存translate结果,避免两次解析
				ArrayList errorList = new ArrayList() ;
				for (Parameter param : params)
				{
					if(!emUkColumns.contains(param.getEmColumn())&&!param.getEmColumn().equals("POST_GRADE_YEAR"))
					{
						Object obj = Translator.translateFormula(param.getFormula(), ifData) ;
						pstmt.setObject(++paramSequence, obj, param.getType());
						errorList.add(obj) ;
					}
					
				}
				
				/**
				 * 执行更新,如果更新条数为0,执行插入操作
				 */
				int updteNumber = -1 ;
				try
				{
					updteNumber = pstmt.executeUpdate() ;
				}
				catch(Exception e)
				{
					logger.debug("update error:") ;
					for(int i = 0 ; i < errorList.size() ; i ++ )
					{
						logger.debug("update param= " + errorList.get(i)) ;
					}
					logger.debug("") ;
					throw e ;
				}
				
				if (updteNumber == 0) {
					SqlUtil.close(pstmt);
					pstmt = connForUpdate.prepareStatement(insert.toString());
					paramSequence = 0;
					errorList.clear() ;
					for (Parameter param : params){
						Object obj = Translator.translateFormula(param.getFormula(), ifData) ;
						pstmt.setObject(++paramSequence, obj, param.getType());
						errorList.add(obj) ;
					}
					
					try
					{
						pstmt.executeUpdate() ;
					}
					catch(Exception e)
					{
						logger.debug("insert error:") ;
						for(int i = 0 ; i < errorList.size() ; i ++ )
						{
							logger.debug("inert param= " + errorList.get(i)) ;
						}
						logger.debug("") ;
						throw e ;
					}
					
					
				}
				SqlUtil.close(pstmt);
			}
		}
	}

	/**
	 * 读取参数
	 */
	private void getParams() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(GET_PARAM);
			if (rs.next()) {
				this.lastTime = rs.getTimestamp("LAST_SYNC");
				this.stampColumn = rs.getString("STAMP_COLUMN");
				this.ifUser = rs.getString("IF_USER");
			} else {
				logger.error("参数缺失");
				throw new GlRuntimeException("参数缺失");
			} 
			logger.debug("lastTime= " + lastTime ) ;
			logger.debug("stampColumn= " + stampColumn ) ;
			logger.debug("ifUser= " + ifUser ) ;
		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException("初始化参数失败", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
	}

	/**
	 * 读取待更新的人事表及公式
	 */
	private void getMappings() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Mapping mapping = null;
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(GET_EMTABLES);
			while (rs.next()) {
				if (mapping == null || !mapping.getEmTable().equals(rs.getString("EM_TABLE"))) {
					mapping = new Mapping();
					mappings.add(mapping);
					mapping.setEmTable(rs.getString("EM_TABLE"));
					mapping.setIfTable(rs.getString("IF_TABLE"));
					
					//不是日志信息
					logger.debug("EM_TABLE= " + mapping.getEmTable() ) ;
					logger.debug("IF_TABLE= " + mapping.getIfTable() ) ;
				}
				mapping.addFormula(rs.getString("EM_COLUMN"), rs.getString("FORMULA"));
				logger.debug("Formula_EM_COLUMN= " +  rs.getString("EM_COLUMN") + "  Formula_FORMULA= " +  rs.getString("FORMULA")) ;
			}
			
		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException("", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
	}

	/**
	 * 取得指定表各列的jdbc类型
	 * @param tableName
	 * @return
	 */
	private Map<String, Integer> getColumnTypes(String tableName) {
		Map<String, Integer> types = new HashMap<String, Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE ROWNUM < 1");
			pstmt.executeQuery();
			ResultSetMetaData resultSetMetaData = pstmt.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			for (int i = 1; i <= columnCount; ++i) {
				String columnName = resultSetMetaData.getColumnName(i);
				int columnType = resultSetMetaData.getColumnType(i);
				/**
				 * DATE类型字段强制使用Timestamp类型
				 */
				types.put(columnName, columnType == Types.DATE ? Types.TIMESTAMP : columnType);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException("", e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return types;
	}

	/**
	 * 读取指定接口表的更新内容
	 * @param ifTable
	 * @return
	 */
	private List<Map<String, Object>> getIfData(String ifTable) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append(this.ifUser);
		sql.append('.');
		sql.append(ifTable);
		//sql.append(" WHERE ");
		//sql.append(this.stampColumn);
		//sql.append(" > ? AND ");
		//sql.append(this.stampColumn);
		//sql.append(" <= ?");
		logger.debug(sql.toString());
		try {
			conn = ConnBean.getConn();
			conn.setReadOnly(true);
			pstmt = conn.prepareStatement(sql.toString());
			//pstmt.setTimestamp(1, lastTime);
			//pstmt.setTimestamp(2, thisTime);
			rs = pstmt.executeQuery();
			ResultSetMetaData resultSetMetaData = pstmt.getMetaData();
			while (rs.next()) {
				HashMap<String, Object> record = new HashMap<String, Object>();
				String columnName = "";
				int columnCount = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= columnCount; ++i) {
					columnName = resultSetMetaData.getColumnName(i);
					/**
					 * DATE类型字段内容强制使用getTimestamp方法
					 */
					if (resultSetMetaData.getColumnType(i) == 91)
						record.put(columnName, rs.getTimestamp(i));
					else
						record.put(columnName, rs.getObject(i));
				}
				list.add(record);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException("取接口表" + ifTable + "内容失败", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取得指定表的主键字段
	 * @param tableName
	 * @return
	 */
	private Set<String> getPkColumn(String tableName) {
		Set<String> columns = new HashSet<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(GET_PK_COLUMN);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			while (rs.next())
				columns.add(rs.getString("COLUMN_NAME"));
		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException("取得" + tableName + "主键字段失败", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return columns;
	}
	
	/**
	 * 取得指定表的唯一字段
	 * @param tableName
	 * @return
	 */
	private Set<String> getUkColumn(String tableName) {
		Set<String> columns = new HashSet<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(GET_UK_COLUMN);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			while (rs.next())
				columns.add(rs.getString("COLUMN_NAME"));
		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException("取得" + tableName + "唯一字段失败", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return columns;
	}

	/**
	 * 同步完毕后,更新最后同步时间
	 */
	private void updateLastTime() {
		PreparedStatement pstmt = null;
		try {
			pstmt = connForUpdate.prepareStatement(UPDATE_LASTTIME);
			pstmt.setTimestamp(1, thisTime);
			pstmt.executeUpdate();
			pstmt = connForUpdate.prepareStatement("UPDATE IF_PARAM SET LAST_SYNC = LAST_SYNC+1/24");
			pstmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e);
			throw new GlRuntimeException("更新最后同步时间失败", e);
		} finally {
			SqlUtil.close(pstmt);
		}
	}

	/**
	 * 实例被回收时,再次检查连接是否未关闭(中途如果有异常抛出,conn可能未关闭)
	 */
	protected void finalize() throws Throwable {
		SqlUtil.close(connForUpdate);
		super.finalize();
	}

	private static final String GET_PK_COLUMN = "SELECT USER_CONS_COLUMNS.COLUMN_NAME FROM USER_CONS_COLUMNS, USER_CONSTRAINTS WHERE USER_CONS_COLUMNS.CONSTRAINT_NAME = USER_CONSTRAINTS.CONSTRAINT_NAME AND CONSTRAINT_TYPE = 'P' AND USER_CONS_COLUMNS.TABLE_NAME = ?";

	private static final String GET_UK_COLUMN = "SELECT USER_CONS_COLUMNS.COLUMN_NAME FROM USER_CONS_COLUMNS, USER_CONSTRAINTS WHERE USER_CONS_COLUMNS.CONSTRAINT_NAME = USER_CONSTRAINTS.CONSTRAINT_NAME AND CONSTRAINT_TYPE = 'U' AND USER_CONS_COLUMNS.TABLE_NAME = ?";
	
	private static final String GET_EMTABLES = "SELECT EM_TABLE, EM_COLUMN, IF_TABLE, FORMULA FROM IF_MAPPING ORDER BY ORDER_NO, EM_TABLE";

	private static final String UPDATE_LASTTIME = "UPDATE IF_PARAM SET LAST_SYNC = ?";

	private static final String GET_PARAM = "SELECT * FROM IF_PARAM";
}