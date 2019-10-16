/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-6-23
 */
package com.ait.sysparam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ait.jdbc.core.JdbcUtil;
import com.ait.jdbc.core.ParameterType;
import com.ait.jdbc.core.QueryCallback;
import com.ait.jdbc.core.SQLResult;
import com.ait.jdbc.core.SqlParameter;
import com.ait.jdbc.core.StatementCallback;
import com.ait.utils.ConnBean;
import com.thoughtworks.xstream.XStream;

/**
 * 系统参数设置工具类
 * 
 * @version
 */
public class SysparamUtils {
	private final static Logger log = Logger.getLogger(SysparamUtils.class);
	private final static String ROOT_NAME = "root";
	private final static String PARAM_TABLE = "sy_param";
	private final static String SELECT_SQL = "select clsName, bvalue, paramName, description from " + PARAM_TABLE;
	private final static String CHARSETNAME_CN = "UTF-8";
	
	private static Connection getConn(){
		return ConnBean.getConn();
		
	}
	
	public static Sysparam getSysparam(Class cls,String cpnyId) throws SysparamException {
		return getSysparam(cls.getName(),cpnyId);
	}
    /**
     * 根据clsName查询参数设置对象
     * @param clsName
     * @return
     */
	public static Sysparam getSysparam(String clsName,String cpnyId) throws SysparamException {
		Sysparam ret = null;
		Connection conn = getConn();
		try {
			InputStreamWrapper in = get(clsName, conn,cpnyId);	
			try {
			  Class cls = Class.forName(clsName);
			  if (in == null) {
				  ret = (Sysparam)cls.newInstance();
				  log.debug("*** result is newInstance() not db");
			  }	else {
			      ret = (Sysparam)fromXML(cls, in.getInputStream());  
			      if (ret == null) {
				      ret = (Sysparam)cls.newInstance();      
				      log.debug("*** result is error");
			      }     
			  }   
			  log.debug(ret.toString());
			  return ret;
			}
			finally {
				if (in != null)
					in.close();
			}
		} catch (SQLException e){
			throw new SysparamException(e);
	    } catch (ClassNotFoundException e){
	    	throw new SysparamException(e);
	    } catch (IOException e){
	    	throw new SysparamException(e);
	    } catch (Exception e){
	    	throw new SysparamException(e);
	    } finally {
	    	try {
			  conn.close();
			  conn = null;
	    	} catch (SQLException e){
	    		throw new SysparamException(e);
	    	}
		}
	}
	/**
	 * 查询全部的参数设置集合对象
	 * @return Map<BaseSysparam>
	 * @throws SysparamException
	 */
	public static Map getSysparamList()throws SysparamException {
		return getSysparamList(new String[0]);
	}
	/**
	 * 根据clsNames集合查询参数设置集合对象
	 * @param clsNames
	 * @return Map<BaseSysparam>
	 * @throws SysparamException
	 */
	public static Map getSysparamList(String[] clsNames)throws SysparamException {
		Connection conn = getConn();
		Map map = new HashMap();
		try {
		   InputStreamWrapper[] ins = get(clsNames, conn);	
		   for (int i = 0; i < ins.length; i++){			   
			   try {
				   Class cls = Class.forName(ins[i].getClsName());
				   Sysparam p = (Sysparam)fromXML(cls, ins[i].getInputStream());
				   if (p == null)
					   p = (Sysparam)cls.newInstance();
				   map.put(ins[i].getClsName(), p);
			   } finally{
				   ins[i].close();
			   }
		   }
		   return map;
		} catch (SQLException e){
			throw new SysparamException(e);
	    } catch (ClassNotFoundException e){
	    	throw new SysparamException(e);
	    }catch (IOException e){
	    	throw new SysparamException(e);
	    } catch (Exception e){
	    	throw new SysparamException(e);
	    } finally {
	    	try {
	    		if (conn != null){
	    			conn.close();
	  			    conn = null;
	    		}			  
	    	} catch (SQLException e){
	    		throw new SysparamException(e);
	    	}
		}
	}
	
    /**
     * 保存参数设置
     * @param p
     * @throws SysparamException
     * @throws SQLException
     */ 
	public static void saveSysparam(Sysparam p,String cpnyId) throws SysparamException {
		log.debug(p);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(out, CHARSETNAME_CN);
			XStream xstream = newXStream(p.getClass());
			xstream.toXML(p, writer);
			byte[] blob = out.toByteArray();
			save(blob, p.getClass().getName(), p.getParamName(), p.getDescription(),cpnyId);
		} catch (SQLException e) {
			throw new SysparamException(e);
		} catch (Exception e) {
			throw new SysparamException(e);
		} finally {			
			try {
				out.close();	
				if(writer != null)
					writer.close();			
			} catch (IOException e) {
				throw new SysparamException(e);
			}
		}
	}
    /**
     * insert or update Param
     * @param blob
     * @param clsName
     * @param description
     * @throws SQLException
     */
	private static void save(final byte[] blob, final String clsName, final String paramName,
			final String description,final String cpnyId) throws SQLException {
		String sql = "select clsName from " + PARAM_TABLE + " where clsName =?  AND CPNY_ID=? ";
		SqlParameter[] sqlParams = new SqlParameter[] { new SqlParameter(
				ParameterType.STRING, clsName),new SqlParameter(ParameterType.STRING, cpnyId) };
		final SQLResult r = JdbcUtil.executeQuery(sql, sqlParams);

		if (r.size() == 0) {
			sql = " insert into " + PARAM_TABLE + "(clsName, bvalue, paramName, description,cpny_id) values(?, ?, ?,?,?)";
		} else {
			sql = " update " + PARAM_TABLE + " set bvalue = ?, paramName = ?, description = ? where clsName = ? and cpny_id=? ";
		}
		JdbcUtil.executeUpdate(sql, new StatementCallback() {
			public void doSetParams(PreparedStatement pstmt)
					throws SQLException {
				if (r.size() == 0){
					pstmt.setString(1, clsName);
					pstmt.setBytes(2, (byte[]) blob);
					pstmt.setString(3, paramName);
					pstmt.setString(4, description);
					pstmt.setString(5, cpnyId);
				} else {
					pstmt.setBytes(1, (byte[]) blob);
					pstmt.setString(2, paramName);
					pstmt.setString(3, description);
					pstmt.setString(4, clsName);
					pstmt.setString(5, cpnyId);
				}				
			}
		});
	}
	/**
	 * 根据clsName查询
	 * @param clsName
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private static InputStreamWrapper get(final String clsName, Connection conn,final String cpnyId) throws SQLException{
		String sql = SELECT_SQL + " where clsName = ?  AND CPNY_ID=? ";
		return (InputStreamWrapper) JdbcUtil.executeQuery(sql,
				     new QueryCallback() {
               			public void doSetParams(PreparedStatement pstmt) throws SQLException {
               				pstmt.setString(1, clsName);
               				pstmt.setString(2, cpnyId);
               			}
            			public Object doBulidResultSet(ResultSet rs) throws SQLException {
				                while (rs.next()) {
				                   InputStreamWrapper ret = new InputStreamWrapper();
				                   ret.setClsName(rs.getString(1));
				            	   ret.setInputStream(rs.getBinaryStream(2));
				            	   return ret;
				                } 
				                return null;
				        }
	         	}, conn);
		
	}
	private static InputStreamWrapper[] get(final String[] clsNames, Connection conn) throws SQLException{
		String sql = SELECT_SQL;
		if (clsNames != null && clsNames.length > 0){
			sql += " where clsName in (";
			for (int i = 0; i < clsNames.length; i++){			
				sql += (i > 0 ? ", '" : "'") + clsNames[i] + "'";
			}
			sql += ")";
		}
		sql += " order by clsNames";
		return (InputStreamWrapper[]) JdbcUtil.executeQuery(sql,
				     new QueryCallback() {
               			public void doSetParams(PreparedStatement pstmt) throws SQLException { 
               				
               			}
            			public Object doBulidResultSet(ResultSet rs) throws SQLException {
            				List list = new ArrayList();
				            while (rs.next()) {
				            	InputStreamWrapper in = new InputStreamWrapper();
				                in.setClsName(rs.getString(1));
				            	in.setInputStream(rs.getBinaryStream(2));
				               list.add(in);
				            } 
				            return list.toArray(new InputStreamWrapper[list.size()]);
				        }
	         	}, conn);
		
	}
	/**
	 * 
	 * @param paramClass
	 * @param in
	 * @return
	 */
	private static Object fromXML(Class paramClass, InputStream ins) throws Exception {
		InputStreamReader reader = new InputStreamReader(ins, CHARSETNAME_CN);
		try {
		  XStream xstream = newXStream(paramClass);
		  Object ret = xstream.fromXML(reader);	
		  log.debug(reader.getEncoding());
	      return  ret;
		} catch (Exception ex){
		    throw ex;	
		}
		finally {
			try {
			  reader.close();
			}
			catch (Exception ex){
				throw ex;
			}
		}
	}
	
	private static XStream newXStream(Class paramClass){
		XStream xstream = new XStream();
		xstream.alias(ROOT_NAME, paramClass); // 设置xml元素和类的对应关系
		return xstream;
	}
	/**
	 * 临时包装查询结果
	 *
	 */
	private static class InputStreamWrapper{
		private String clsName; 
		/**
		 * 
		 */
		private InputStream inputStream;
		
		public InputStreamWrapper(){
			
		}
		
		public InputStreamWrapper(String clsName, InputStream inputStream){
			this.clsName = clsName;
			this.inputStream = inputStream;
		}

		public String getClsName() {
			return clsName;
		}

		public void setClsName(String clsName) {
			this.clsName = clsName;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		
		public void close() throws IOException{
			if (inputStream != null)
				inputStream.close();
		}
		
	}

}
