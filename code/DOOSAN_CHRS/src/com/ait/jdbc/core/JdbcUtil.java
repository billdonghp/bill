/**
 * @date 2006-6-15
 */
package com.ait.jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

/**
 * sql执行工具类
 * @version 
 */
public class JdbcUtil {
	
	private static final Logger logger = Logger.getLogger(JdbcUtil.class);
	
	/**
	 * 执行sql更新 
	 * @param sql
	 * @param callback
	 * @param connection 如果不为null,需要调用者自己释放connection
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(String sql, StatementCallback callback, Connection connection) throws SQLException{
		logger.debug(sql);
		Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connection != null ? connection : ConnBean.getConn();
            pstmt = conn.prepareStatement(sql);
            if (callback != null)
                callback.doSetParams(pstmt);//
            return pstmt.executeUpdate();
        } catch (SQLException e) {
        	throw e;
        } finally {
        	SqlUtil.close(pstmt, connection == null ? conn : null);
        }		
	}
	/**
	 * 执行sql更新
	 * @param sql
	 * @param callback
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(String sql, StatementCallback callback) throws SQLException{
		return executeUpdate(sql, callback, null);
	}
	
	/**
	 * 执行sql更新 
	 * @param sql
	 * @param SqlParameter[] 参数
	 * @param connection 如果不为null,需要调用者自己释放connection
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(String sql, final SqlParameter[] params, Connection connection) throws SQLException{
		return executeUpdate(sql, 
				new StatementCallback(){
			       public void doSetParams(PreparedStatement pstmt) throws SQLException{
			    	    if (params == null)
			    		    return;
			            for (int i = 0; i < params.length; i++){
			                StatementUtils.setParameterValue(pstmt, i+1, params[i].getSqlType().getValue(), params[i].getInValue());
			             }   			    	   
			       }
		        }, connection);
	}
	
	/**
	 * 执行sql更新 
	 * @param sql
	 * @param connection 如果不为null,需要调用者自己释放connection
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(String sql) throws SQLException{
		return executeUpdate(sql, new SqlParameter[0]);
	}
	
	/**
	 * 执行sql更新 
	 * @param sql
	 * @param SqlParameter[] 参数
	 * @param connection 如果不为null,需要调用者自己释放connection
	 * @return
	 * @throws SQLException
	 */
	public static int executeUpdate(String sql, final SqlParameter[] params) throws SQLException{
		return executeUpdate(sql,params, null);
	}

	/**
	 * 执行sql查询
	 * @param sql
	 * @param callback
	 * @param connection 如果不为null,需要调用者自己释放connection
	 * @return
	 * @throws SQLException
	 */
	public static Object executeQuery(String sql, QueryCallback callback, Connection connection) throws SQLException{
		logger.debug(sql);
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = connection != null ? connection : ConnBean.getConn();
            pstmt = conn.prepareStatement(sql);
            callback.doSetParams(pstmt);//
            rs = pstmt.executeQuery();
            return callback.doBulidResultSet(rs);
        } catch (SQLException e) {
        	throw e;
        } finally {
            SqlUtil.close(rs, pstmt, connection == null ? conn : null);
        }			
	}

	/**
	 * 执行sql查询
	 * @param sql
	 * @param callback
	 * @return
	 * @throws SQLException
	 */
	public static Object executeQuery(String sql, QueryCallback callback) throws SQLException{
		return executeQuery(sql, callback, null);
	}

	/**
	 * 执行sql查询
	 * @param sql
	 * @param params
	 * @param connection 如果不为null,需要调用者自己释放connection
	 * @return
	 * @throws SQLException
	 */
	public static SQLResult executeQuery(String sql, final SqlParameter[] params, Connection connection) throws SQLException{
		return (SQLResult)executeQuery(sql, 
				new QueryCallback(){
			       public void doSetParams(PreparedStatement pstmt) throws SQLException{
			    	   printParamValues(params);
			    	    if (params == null)
			    		    return;
			            for (int i = 0; i < params.length; i++){
			                StatementUtils.setParameterValue(pstmt, i+1, params[i].getSqlType().getValue(), params[i].getInValue());
			             }   			    	   			    	   
			       }
			       public Object doBulidResultSet(ResultSet rs) throws SQLException{
			    	   return assembleResult(rs);
			       }
	            }, connection);        
	}


	/**
	 * 执行sql查询
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static SQLResult executeQuery(String sql) throws SQLException{
		return executeQuery(sql, new SqlParameter[0]);
	}


	/**
	 * 执行sql查询
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static SQLResult executeQuery(String sql, final SqlParameter[] params) throws SQLException{
		return executeQuery(sql, params, null);
	}
	
	public static Integer executeInteger(String sql, final SqlParameter[] params) throws SQLException{
		SQLResult rt = executeQuery(sql, params, null);
		return rt.size() == 0 ? null : rt.get(0).getInteger(RowResult.FIRSTINDEX);
	}
	
	public static String executeString(String sql, final SqlParameter[] params) throws SQLException{
		SQLResult rt = executeQuery(sql, params, null);
		return rt.size() == 0 ? null : rt.get(0).getString(RowResult.FIRSTINDEX);
	}
	/**
	 * 组装查询结果
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static SQLResult assembleResult(ResultSet rs)throws SQLException{
		SQLResult ret = new SQLResult(rs.getMetaData());
		
		while (rs.next()){
			RowResult rt = new RowResult(ret.getColNameList());
			for (int i = 1; i <= ret.getColumnCount(); i++)
				rt.add(rs.getObject(i));			
			ret.add(rt);
		}
		if (logger.isDebugEnabled())
		    logger.debug("***查询结果个数：" + ret.size());
		return ret;
	}
	
	private static void printParamValues(SqlParameter[] params){
		if (params == null || params.length == 0)
			return;
		if (logger.isDebugEnabled()){
			logger.debug("**********************SQLParameter [Type,Value]**********************");
			StringBuffer sb = new StringBuffer();
            for (int i = 0; i < params.length; i++){
                sb.append(params[i]).append(" ");
             } 
             logger.debug(sb.toString()); 
			
		}
	}
	
	public static void main(String[] args)throws SQLException{
		Connection conn = null;
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection(
	                "jdbc:oracle:thin:@127.0.0.1:1521:DEMO",
	                "EM2YX", "EM2YX");
			} catch(Exception ex){
				ex.printStackTrace();
			}
			
		String sql = "select id, name, startdate, starttime, v1 from tmp_test" +
		    " where id = ? and name = ?";
		SqlParameter[] params = new SqlParameter[2];
		params[0] = new SqlParameter(1);
		params[1] = new SqlParameter(ParameterType.STRING, "t1");
		SQLResult list = executeQuery(sql, params, conn);
		logger.debug(list.getColNameList());
		logger.debug("recordcount == " + new Integer(list.size()));
		Iterator it = list.iterator();
		
		while (it.hasNext()){
			RowResult r = (RowResult)it.next();
			logger.debug(new Integer(r.getInt(1)));
			logger.debug(r.getString(2));
			logger.debug(r.getDate("startdate"));
			logger.debug(r.getDate("starttime"));
			logger.debug(new Double(r.getDouble(5)));
			logger.debug(new Float(r.getFloat("v1")));
			
			//logger.debug(r.getString(1));
		}
		conn.close();
		conn = null;
	}
	
	

}
