package com.ait.kpa;

import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ibm.icu.util.StringTokenizer;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sy.bean.SysCodeBean;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.util.SysCodeParser;

public class PaReport {
    private static ServiceLocator services;
    
    private static final Logger logger = Logger.getLogger(PaReport.class);
    
	private CommonSQLMapAdapter commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
    public PaReport() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

    Vector Vlist = new Vector(); // 产生Vector的对象Vlist

    Connection Conn = null; // 产生连接初始化的对象Conn

    int i = 0; // 初始化循环Vector的变量值

    // 选出所有记录列表的sql语句
    String SELECT_SQL = "SELECT * " + " FROM T_KPA_RESULT ";

    String Column_SQL = "SELECT 'PA_MONTH' AS COLUMN_ID,'工资月' AS COLUMN_NAME,'Pay Month' AS COLUMN_EN_NAME,'' AS COLUMN_KOR_NAME FROM DUAL "
	    			+ " UNION ALL "
					+ " SELECT A.DISTINCT_FIELD, A.FIELD_NAME, A.FIELD_EN_NAME, A.FIELD_KOR_NAME "
					+ "  FROM KPA_DISTINCT_LIST A "
					+ " WHERE (A.LANG_GROUP = 0 OR A.LANG_GROUP = 1) AND A.TABLE_NAME IN ('KPA_HR_V') "     
					+ " UNION ALL "
					+ " SELECT B.ITEM_ID, B.ITEM_NAME, B.ITEM_EN_NAME, B.ITEM_KOR_NAME FROM KPA_ITEM B "
					+ " UNION ALL "
					+ " SELECT C.PARAM_ID, C.PARAM_NAME, C.PARAM_EN_NAME, C.PARAM_KOR_NAME FROM KPA_PARAM_ITEM C ";
    
    String PaBonusColumn_SQL = "SELECT 'PA_MONTH' AS COLUMN_ID,'工资月' AS COLUMN_NAME, '급여월' AS COLUMN_NAME_KO FROM DUAL "
    	+ " UNION ALL "
    	+ " SELECT 'BONUS_TYPE' AS COLUMN_ID,'奖金类别' AS COLUMN_NAME, '포상금 유형' FROM DUAL "
		+ " UNION ALL "
		+ " SELECT A.DISTINCT_FIELD, A.FIELD_NAME, A.FIELD_KOR_NAME FROM KPA_DISTINCT_LIST A WHERE (A.LANG_GROUP = 0 OR A.LANG_GROUP = 1) AND A.TABLE_NAME IN ('KPA_HR_V') "     
		+ " UNION ALL "
		+ " SELECT C.PARAM_ID, C.PARAM_NAME, C.PARAM_KOR_NAME FROM KPA_BONUS_PARAM_ITEM C "
		+ " UNION ALL "
		+ " SELECT B.ITEM_ID, B.ITEM_NAME, B.ITEM_KOR_NAME FROM KPA_BONUS_ITEM B " ;

    // 返回连接
    public Connection Conn() throws Exception {
        Connection Conn = null;// 建立参数声明
        try {
            Conn = services.getConnection(); // 得到连接
        } catch (Exception e) {
            System.out.print("Conn Error:" + e);
        }
        return Conn;
    }

    // 返回全部记录，用于没有分页和搜索的情况
    public Vector DataSelect() throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
        PaReport report = null;
        Connection conn = Conn(); // 建立连接
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        ResultSetMetaData rsmd = null;
        Vector data_v = null;
        try {
            report = new PaReport();
            pstmt = conn.prepareStatement(SELECT_SQL);
            rs = pstmt.executeQuery(); // 得到信息数据集
            rsmd = rs.getMetaData();
            data_v = new Vector();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                data_v.add(rsmd.getColumnName(i));
            }
            report.setData(data_v);
            Vlist.addElement(report);
            while (rs.next()) { // 依次得到值
                report = new PaReport(); // 产生实体对象
                data_v = new Vector();
                // 按照上面搜索信息依次附值，以下方法如;
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                	if(rsmd.getColumnName(i).equals("工号") || rsmd.getColumnName(i).equals("Emp ID"))
                	{
                		data_v.add(this.editNull(rs.getString(i)) + "&nbsp;");
                	}
                	else
                	{
                		data_v.add(this.editNull(rs.getString(i)));
                	}
                }
                report.setData(data_v);
                Vlist.addElement(report); // 形成一条记录，插入记录列表
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    public Vector ColumnSelect() throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
        PaReport report = null;
        Connection conn = Conn(); // 建立连接
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
        	logger.debug("SQL= " + Column_SQL) ;
        	
            report = new PaReport();
            pstmt = conn.prepareStatement(Column_SQL);
            rs = pstmt.executeQuery(); // 得到信息数据集
            while (rs.next()) { // 依次得到值
                report = new PaReport(); // 产生实体对象
                report.setColumnId(rs.getString("COLUMN_ID"));
                report.setColumnname(rs.getString("COLUMN_NAME"));
                report.setColumnEnName(rs.getString("COLUMN_EN_NAME"));
                report.setColumnKoName(rs.getString("COLUMN_KOR_NAME"));
                Vlist.addElement(report); // 形成一条记录，插入记录列表
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }
    
    public Vector paBonusColumnSelect(String statTypeCode) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
        PaReport report = null;
        Connection conn = Conn(); // 建立连接
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        try {
        	logger.debug("SQL= " + PaBonusColumn_SQL) ;
        	if (statTypeCode == null || statTypeCode.length() == 0)
        	{
        		SysCodeBean codeBean = new SysCodeBean();
    			List statTypeList = SysCodeParser.getSysCode("EmpDiffType");
    			
    			statTypeCode = ((SysCodeBean)statTypeList.get(0)).getCodeId() ;
        	}
        	
            report = new PaReport();
            pstmt = conn.prepareStatement(PaBonusColumn_SQL);
            rs = pstmt.executeQuery(); // 得到信息数据集
            while (rs.next()) { // 依次得到值
                report = new PaReport(); // 产生实体对象
                report.setColumnId(rs.getString("COLUMN_ID"));
                report.setColumnname(rs.getString("COLUMN_NAME"));
                report.setColumnKoName(rs.getString("COLUMN_NAME_KO"));
                Vlist.addElement(report); // 形成一条记录，插入记录列表
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    public Vector DataSelect(String SQL) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
        PaReport report = null;
        Connection conn = Conn(); // 建立连接
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        ResultSetMetaData rsmd = null;
        Vector data_v = null;
        try {
            report = new PaReport();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery(); // 得到信息数据集
            rsmd = rs.getMetaData();
            data_v = new Vector();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                data_v.add(rsmd.getColumnName(i));
            }
            report.setData(data_v);
            Vlist.addElement(report);
            while (rs.next()) { // 依次得到值
                report = new PaReport(); // 产生实体对象
                data_v = new Vector();
                // 按照上面搜索信息依次附值，以下方法如;
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                	data_v.add(this.editNull(rs.getString(i)));
                }
                report.setData(data_v);
                Vlist.addElement(report); // 形成一条记录，插入记录列表
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    public Vector DataSelect(String SQL, String pamonth) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
        PaReport report = null;
        Connection conn = Conn(); // 建立连接
        PreparedStatement pstmt = null; // 建立参数声明
        ResultSet rs = null; // 建立数据集
        ResultSetMetaData rsmd = null;
        Vector data_v = null;
        try {
            report = new PaReport();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, pamonth);
            rs = pstmt.executeQuery(); // 得到信息数据集
            rsmd = rs.getMetaData();
            data_v = new Vector();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                data_v.add(rsmd.getColumnName(i));
            }
            report.setData(data_v);
            Vlist.addElement(report);
            while (rs.next()) { // 依次得到值
                report = new PaReport(); // 产生实体对象
                data_v = new Vector();
                // 按照上面搜索信息依次附值，以下方法如;
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {              	
                	data_v.add(this.editNull(rs.getString(i)));
                }
                report.setData(data_v);
                Vlist.addElement(report); // 形成一条记录，插入记录列表
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return Vlist; // 返回记录列表
    }

    /**
     * add this for searching salary information
     * @param adminid
     * @return
     * @throws Exception
     */
    public Object RetrievePaInfo(Object parameterObject){
    	
		Object object = null;
		try{			
			object = commonSQLMapAdapter.executeQuery("pa.common.RetrievePaInfo", parameterObject);
			Logger.getLogger(this.getClass()).debug("Retrieving salary");
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving salary information Exception. ", e);
		}
		return object;
    }
    
    /**
     * retrieve salary of year data.
     * @param adminid
     * @return
     * @throws Exception
     */
    public List RetrieveSalary(Object parameterObject){
    	List list = null;
    	try{
    		list = commonSQLMapAdapter.executeQueryForMulti("pa.common.RetrieveSalary", 
    				parameterObject);
    	}catch(Exception e){
    		Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving salary of year Exception. ", e);
    	}
    	return list;
    }
    
    /**
     * retrieve basic salary information for basic salary
     * @param parameterObject
     * @return
     */
    public Object RetrieveBasicPaInfo(Object parameterObject){
    	Object object = null;
		try{			
			object = commonSQLMapAdapter.executeQuery("pa.common.RetrieveBasicPaInfo", parameterObject);
			Logger.getLogger(this.getClass()).debug("Retrieving salary");
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving basic salary information Exception. ", e);
		}
		return object;
    }
    /**
     * 	searching personal information when retrieving salary of year.
     * @param parameterObject
     * @return object
     */
    public Object RetrievePersonalSalary(Object parameterObject){
    	Object object;
    	try{
    		object = commonSQLMapAdapter.executeQuery("pa.common.RetrievePersonalInfoForSalary",
    				parameterObject);
    		
    	}catch(Exception e){
    		Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving salary of year Personal Information. ", e);
    	}
    	return object;
    }
    
    /**
     * Retrieving reward information for PA
     * @param parameterObject
     * @return list
     */
    public List RetrieveRewardInfo(Object parameterObject){
    	List list;
    	try{
    		list = commonSQLMapAdapter.executeQueryForMulti("pa.common.RetrieveRewardInfo", 
    				parameterObject);
    		
    	}catch(Exception e){
    		Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving reward information for PA .", e);
    	}
    	return list;
    }
    
    /**
     * Retrieving punishment information for PA
     * @param parameterObject
     * @return list
     */
    public List RetrievePunishmentInfo(Object parameterObject){
    	List list;
    	try{
    		list = commonSQLMapAdapter.executeQueryForMulti("pa.common.RetrievePunishmentInfo", 
    				parameterObject);
    		
    	}catch(Exception e){
    		Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving punishment information for PA .", e);
    	}
    	return list;
    }
    
    /**
     * Retrieving evaluation information for PA
     * @param parameterObject
     * @return
     */
    public List RetrieveEvaluationInfo(Object parameterObject){
    	List list;
    	try{
    		list = commonSQLMapAdapter.executeQueryForMulti("pa.common.RetrieveEvaluationInfo", 
    				parameterObject);
    		
    	}catch(Exception e){
    		Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving evaluation information for PA .", e);
    	}
    	return list;
    }
    
    /**
     * Retrieving bonus of year for PA 
     * @param parameterObject
     * @return
     */
    public List RetrieveBonusOfYear(Object parameterObject){
    	List list;
    	try{
    		list = commonSQLMapAdapter.executeQueryForMulti("pa.common.RetrieveBonusOfYear", 
    				parameterObject);
    		
    	}catch(Exception e){
    		Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieving bonus of year for PA .", e);
    	}
    	return list;
    }
    
    public String PaConfirm(String adminid) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
        String result = "倒入不成功";
        Connection conn = Conn(); // 建立连接
        CallableStatement cs = null; // 建立参数声明
        try {
            cs = conn.prepareCall("{call Kpa_confirm(?,?)}");
            cs.setString(1, adminid);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            result = cs.getString(2); // 产生实体对象
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(cs, conn);
        }
        return result;
    }

    String columnId = "";
    
    String columnname = "";
    
    String columnEnName = "";
    
    String columnKoName = "";

    String dept_id = "";

    public Vector data;

    // Get
    public String getColumnname() {
        return editNull(this.columnname);
    }

    public String getdeptname1() {
        return editNull(this.dept_id);
    }

    public Vector getData() {
        return data;
    }

    // Set
    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public void setdeptname1(String dept_id) {
        this.dept_id = dept_id;
    }

    public void setData(Vector data) {
        this.data = data;
    }

    // entity

    public String editNull(String s) {
        if (s == null || s.equals("")) {
            return "";
        } else {
            return s;
        }
    }
	
	public String getColumnEnName() {
		return columnEnName;
	}

	
	public void setColumnEnName(String columnEnName) {
		this.columnEnName = columnEnName;
	}

	
	public String getColumnId() {
		return columnId;
	}

	
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	
	public String getColumnKoName() {
		return columnKoName;
	}

	
	public void setColumnKoName(String columnKoName) {
		this.columnKoName = columnKoName;
	}
     
}


