package com.ait.pa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.hrm.bean.BasicInfo;
import com.ait.sy.bean.AdminBean;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.util.NumberUtil;
import com.ait.web.ApplicationContext;

public class PaParam {
    private static ServiceLocator services;    
    AdminBean admin =ApplicationContext.getTheadLocal();    
    public PaParam() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

    /*
     * 
     * 
     * table name pa_param_data
     * 
     * PARAM_DATA_NO PARAM_NO EQUAL_VALUE RETURN_VALUE
     * 
     */

    private int param_date_no;

    private int param_no;

    private String field1_value;

    private String field1_en_value;

    private String field1_kor_value;

    private String field2_value;

    private String field2_en_value;

    private String field2_kor_value;

    private String return_value;

    private String param_name;

    private String param_en_name;

    private String param_kor_name;

    private String default_value;
    
    private String startMonth ;
    
    private String endMonth ;
    
    private String sdEdValue ;
    
    private String empId;

    public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getParamDefaultValue(String configureNO) {
        this.setParamDefaultValue(configureNO);
        return this.default_value;
    }

    public void setParamDefaultValue(String configureNO) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select NVL(DEFAULT_VAL, '') From PA_PARAM_ITEM_CONFIGURE WHERE CONFIGURE_NO = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, configureNO);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.default_value = rs.getString(1);
            } else {
                this.default_value = "";
            }
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, ps, con);
        }
    }

    public void setParamDefaultValue(int paramDataNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT NVL(DEFAULT_VAL, '') "
                + "FROM PA_PARAM_ITEM_CONFIGURE, PA_PARAM_DATA "
                + "WHERE PA_PARAM_DATA.CONFIGURE_NO = PA_PARAM_ITEM.PARAM_NO "
                + "AND PA_PARAM_DATA.PARAM_DATA_NO = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, paramDataNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                this.default_value = rs.getString(1);
            } else {
                this.default_value = "";
            }
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, ps, con);
        }
    }

  

    /*
     * 
     * data list no pagecontrol with PARAM_NO
     * 
     */

    public int getParam_date_no() {
		return param_date_no;
	}

	public void setParam_date_no(int param_date_no) {
		this.param_date_no = param_date_no;
	}

	public int getParam_no() {
		return param_no;
	}

	public void setParam_no(int param_no) {
		this.param_no = param_no;
	}

	public String getField1_value() {
		return field1_value;
	}

	public void setField1_value(String field1_value) {
		this.field1_value = field1_value;
	}

	public String getField2_value() {
		return field2_value;
	}

	public void setField2_value(String field2_value) {
		this.field2_value = field2_value;
	}

	public String getReturn_value() {
		return return_value;
	}

	public void setReturn_value(String return_value) {
		this.return_value = return_value;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
	
	public String getParam_en_name() {
		return param_en_name;
	}

	public void setParam_en_name(String param_en_name) {
		this.param_en_name = param_en_name;
	}

	public String getParam_kor_name() {
		return param_kor_name;
	}

	public void setParam_kor_name(String param_kor_name) {
		this.param_kor_name = param_kor_name;
	}
	
	public String getField1_en_value() {
		return field1_en_value;
	}

	public void setField1_en_value(String field1_en_value) {
		this.field1_en_value = field1_en_value;
	}

	public String getField1_kor_value() {
		return field1_kor_value;
	}

	public void setField1_kor_value(String field1_kor_value) {
		this.field1_kor_value = field1_kor_value;
	}

	public String getField2_en_value() {
		return field2_en_value;
	}

	public void setField2_en_value(String field2_en_value) {
		this.field2_en_value = field2_en_value;
	}

	public String getField2_kor_value() {
		return field2_kor_value;
	}

	public void setField2_kor_value(String field2_kor_value) {
		this.field2_kor_value = field2_kor_value;
	}

	public Vector getPaParamViewList(String configureNO, String deptid, String key) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        Vector v = new Vector();
        String sql = " Select "
                + " PARAM_DATA_NO,PARAM_NO,HR.EMPID,"
                + " FIELD1_VALUE ,FIELD1_EN_VALUE,FIELD1_KOR_VALUE,"
                + " FIELD2_VALUE ,FIELD2_EN_VALUE,FIELD2_KOR_VALUE,"
                + " RETURN_VALUE ,START_MONTH ,END_MONTH ,SD_ED_VALUE"
                + " From PA_PARAM_DATA,HR_EMPLOYEE HR where PARAM_NO = ? AND FIELD1_VALUE = HR.PERSON_ID(+) " 
                + " AND PA_PARAM_DATA.CPNY_ID='"+admin.getCpnyId()+"' " ;
        if(deptid != null && deptid.length() > 0)
        {
        	sql = sql + " AND EXISTS( SELECT HD.DEPTID FROM HR_DEPARTMENT HD WHERE HR.DEPTID = HD.DEPTID  START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID ) " ;
        }
        
        if(key != null && key.length() > 0)
        {
        	key = "%" + key.toLowerCase() + "%" ;
        	sql = sql + " AND (HR.CHINESENAME LIKE '%' || ? || '%' OR UPPER(HR.EMPID) LIKE '%' || UPPER(?) || '%' OR UPPER(HR.CHINESE_PINYIN) LIKE '%' || UPPER(?) || '%') " ;
        }
        sql = sql + " order by HR.EMPID";
        
        ResultSet rs = null;
        Logger.getLogger(getClass()).debug("PaParam_list_sql=" + sql);
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            int i = 1 ;
            ps.setString(i++, configureNO);
            if(deptid != null && deptid.length() > 0)
            {
            	ps.setString(i++, deptid);
            }
            
            if(key != null && key.length() > 0)
            {
            	ps.setString(i++, key);
            	ps.setString(i++, key);
            	ps.setString(i++, key);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                paParam = new PaParam();
                paParam.setParam_date_no(rs.getInt("PARAM_DATA_NO"));
                paParam.setParam_no(rs.getInt("PARAM_NO"));
                paParam.setField1_value(rs.getString("FIELD1_VALUE"));
                paParam.setField1_en_value(rs.getString("FIELD1_EN_VALUE"));
                paParam.setField1_kor_value(rs.getString("FIELD1_KOR_VALUE"));
                paParam.setField2_value(rs.getString("FIELD2_VALUE"));
                paParam.setField2_en_value(rs.getString("FIELD2_EN_VALUE"));
                paParam.setField2_kor_value(rs.getString("FIELD2_KOR_VALUE"));
                paParam.setReturn_value(rs.getString("RETURN_VALUE"));
                paParam.setStartMonth(rs.getString("START_MONTH")) ;
                paParam.setEndMonth(rs.getString("END_MONTH")) ;
                paParam.setSdEdValue(rs.getString("SD_ED_VALUE")) ;
                paParam.setEmpId(rs.getString("EMPID"));
                v.add(paParam);
            }
            return v;
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_list with param_no=" + ex.getMessage());
            return null;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }
	
    /*
     * 
     * data list no pagecontrol with pamonth and empid
     * 
     */

    public Vector getPaParamList(String pamonth, BasicInfo basic) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        Vector v = new Vector();
        String sql = " SELECT NVL(PA_PARAM_DATA.FIELD1_VALUE, ?) AS PERSON_ID, " +
        			 " PA_PARAM_ITEM.PARAM_NAME, " +
        			 " PA_PARAM_ITEM_CONFIGURE.CONFIGURE_NO, " +
				     " PA_PARAM_ITEM_CONFIGURE.PA_MONTH_STR, " +
        			 " PA_PARAM_DATA.RETURN_VALUE, " +
        			 " PA_PARAM_DATA.PARAM_DATA_NO " +
        		" FROM PA_PARAM_ITEM, PA_PARAM_ITEM_CONFIGURE, PA_PARAM_DATA " + 
        	   " WHERE PA_PARAM_ITEM.PARAM_NO = PA_PARAM_ITEM_CONFIGURE.PARAM_NO " +
        		"  AND PA_PARAM_ITEM_CONFIGURE.CONFIGURE_NO = PA_PARAM_DATA.PARAM_NO(+) " +
        	    "  AND PA_PARAM_DATA.FIELD1_VALUE(+) = ? " +
        	    "  AND PA_PARAM_ITEM_CONFIGURE.PA_MONTH_STR = ? " +
        	    "  AND PA_PARAM_ITEM_CONFIGURE.STAT_TYPE_CODE = ? " +
        	    "  AND PA_PARAM_ITEM_CONFIGURE.DISTINCT_FIELD = 'PERSON_ID' " +
        	    "  AND PA_PARAM_ITEM_CONFIGURE.CPNY_ID = ? " + 
        	    " ORDER BY PA_PARAM_ITEM.ORDER_NO " ; 
        ResultSet rs = null;
        Logger.getLogger(getClass()).debug("PaParam_list_sql=" + sql);
        Logger.getLogger(getClass()).debug("PersonId=" + basic.getPersonId());
        Logger.getLogger(getClass()).debug("pamonth=" + pamonth);
        Logger.getLogger(getClass()).debug("EmpDiffCode=" + basic.getEmpDiffCode());
        Logger.getLogger(getClass()).debug("CpnyId=" + admin.getCpnyId());
        
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, basic.getPersonId());
            ps.setString(2, basic.getPersonId());
            ps.setString(3, pamonth);
            ps.setString(4, basic.getEmpDiffCode());
            ps.setString(5, admin.getCpnyId());
            
            rs = ps.executeQuery();
            while (rs.next()) {
                paParam = new PaParam();
                paParam.setParam_date_no(rs.getInt("PARAM_DATA_NO"));
                paParam.setParam_no(rs.getInt("CONFIGURE_NO"));
                paParam.setParam_name(rs.getString("PARAM_NAME"));
                paParam.setReturn_value(rs.getString("RETURN_VALUE"));
                v.add(paParam);
            }
            return v;
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_list with param_no=" + ex.getMessage());
            return null;
        } finally {
            try {
            	rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }
    
    public Vector getPaBonusParamList(String pamonth, BasicInfo basic) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        Vector v = new Vector();
        String sql = " SELECT NVL(PA_BONUS_PARAM_DATA.FIELD1_VALUE, ?) AS PERSON_ID, " +
        			 " PA_BONUS_PARAM_ITEM.PARAM_NAME, " +
        			 " PA_BONUS_PARAM_ITEM_CONFIGURE.CONFIGURE_NO, " +
				     " PA_BONUS_PARAM_ITEM_CONFIGURE.PA_MONTH_STR, " +
        			 " PA_BONUS_PARAM_DATA.RETURN_VALUE, " +
        			 " PA_BONUS_PARAM_DATA.PARAM_DATA_NO " +
        		" FROM PA_BONUS_PARAM_ITEM, PA_BONUS_PARAM_ITEM_CONFIGURE, PA_BONUS_PARAM_DATA " + 
        	   " WHERE PA_BONUS_PARAM_ITEM.PARAM_NO = PA_BONUS_PARAM_ITEM_CONFIGURE.PARAM_NO " +
        		"  AND PA_BONUS_PARAM_ITEM_CONFIGURE.CONFIGURE_NO = PA_BONUS_PARAM_DATA.PARAM_NO(+) " +
        	    "  AND PA_BONUS_PARAM_DATA.FIELD1_VALUE(+) = ? " +
        	    "  AND PA_BONUS_PARAM_ITEM_CONFIGURE.PA_MONTH_STR = ? " +
        	    "  AND PA_BONUS_PARAM_ITEM_CONFIGURE.STAT_TYPE_CODE = ? " +
        	    "  AND PA_BONUS_PARAM_ITEM_CONFIGURE.DISTINCT_FIELD = 'PERSON_ID' " +
        	    "  AND PA_BONUS_PARAM_ITEM_CONFIGURE.CPNY_ID = ? " + 
        	    " ORDER BY PA_BONUS_PARAM_ITEM.ORDER_NO " ; 
        ResultSet rs = null;
        Logger.getLogger(getClass()).debug("PaParam_list_sql=" + sql);
        Logger.getLogger(getClass()).debug("PersonId=" + basic.getPersonId());
        Logger.getLogger(getClass()).debug("pamonth=" + pamonth);
        Logger.getLogger(getClass()).debug("EmpDiffCode=" + basic.getEmpDiffCode());
        Logger.getLogger(getClass()).debug("CpnyId=" + admin.getCpnyId());
        
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, basic.getPersonId());
            ps.setString(2, basic.getPersonId());
            ps.setString(3, pamonth);
            ps.setString(4, basic.getEmpDiffCode());
            ps.setString(5, admin.getCpnyId());
            
            rs = ps.executeQuery();
            while (rs.next()) {
                paParam = new PaParam();
                paParam.setParam_date_no(rs.getInt("PARAM_DATA_NO"));
                paParam.setParam_no(rs.getInt("CONFIGURE_NO"));
                paParam.setParam_name(rs.getString("PARAM_NAME"));
                paParam.setReturn_value(rs.getString("RETURN_VALUE"));
                v.add(paParam);
            }
            return v;
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_list with param_no=" + ex.getMessage());
            return null;
        } finally {
            try {
            	rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }

    /*
     * 
     * data detail
     * 
     */

    public static PaParam getPaParamDetail(String param_data_no) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        String sql = " Select " + " PARAM_DATA_NO," + " PARAM_NO,"
                + " FIELD1_VALUE," + " FIELD2_VALUE," + " RETURN_VALUE "
                + " From PA_PARAM_DATA where PARAM_DATA_NO = ?";
        ResultSet rs = null;
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, param_data_no);
            rs = ps.executeQuery();
            if (rs.next()) {
                paParam = new PaParam();
                paParam.setParam_no(rs.getInt("PARAM_NO"));
                paParam.setParam_date_no(rs.getInt("PARAM_DATA_NO"));
                paParam.setField1_value(rs.getString("FIELD1_VALUE"));
                paParam.setField2_value(rs.getString("FIELD2_VALUE"));
                paParam.setReturn_value(rs.getString("RETURN_VALUE"));
            }
            return paParam;
        } catch (Exception ex) {
            Logger.getLogger("getPaParamDetail").debug("PaParam_Detail=" + ex.getMessage());
            return null;
        } finally {
            SqlUtil.close(rs, ps, con);
        }
    }

    /*
     * 
     * add data
     * 
     */
    public Vector AddPaParamList(String configureNo, String actionType, String deptid, String key) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        CallableStatement cs = null; // 建立参数声明
        Vector v = new Vector();
        String sql = " select field1,field1_en,field1_kor,field2,field2_en, field2_kor,HR.EMPID from pa_param_data_list,hr_employee hr where hr.PERSON_ID = field1 and pa_param_data_list.CPNY_ID = ? ";
        if(deptid != null && deptid.length() > 0)
        {
        	sql = sql + " AND EXISTS( SELECT HD.DEPTID FROM HR_DEPARTMENT HD WHERE HR.DEPTID = HD.DEPTID  START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID ) " ;
        }
        
        if(key != null && key.length() > 0)
        {
        	key = "%" + key.toLowerCase() + "%" ;
        	sql = sql + " AND (HR.CHINESENAME LIKE '%' || ? || '%' OR UPPER(HR.EMPID) LIKE '%' || UPPER(?) || '%' OR UPPER(HR.CHINESE_PINYIN) LIKE '%' || UPPER(?) || '%') " ;
        }
        sql = sql + " order by HR.EMPID";
        
        ResultSet rs = null;
        try {
            con = services.getConnection();
            
            if (!actionType.equals("search"))
            {
            	cs = con.prepareCall("{call pr_param_list(?,?,'pa')}");
                cs.setString(1, configureNo);
                cs.setString(2, admin.getCpnyId());
                cs.execute();
            }
            Logger.getLogger(getClass()).debug("sql++++++++++++++++"+sql);
            ps = con.prepareStatement(sql);
            int i = 1 ;
            
            ps.setString(i++, admin.getCpnyId());
            if(deptid != null && deptid.length() > 0)
            {
            	ps.setString(i++, deptid);
            }
            
            if(key != null && key.length() > 0)
            {
            	ps.setString(i++, key);
            	ps.setString(i++, key);
            	ps.setString(i++, key);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                paParam = new PaParam();
                paParam.setField1_value(rs.getString("field1"));
                paParam.setField1_en_value(rs.getString("field1_en"));
                paParam.setField1_kor_value(rs.getString("field1_kor"));
                paParam.setField2_value(rs.getString("field2"));
                paParam.setField2_en_value(rs.getString("field2_en"));
                paParam.setField2_kor_value(rs.getString("field2_kor"));
                paParam.setEmpId(rs.getString("EMPID"));
                v.add(paParam);
            }
            return v;
        } catch (Exception ex) {
        	ex.printStackTrace() ;
            Logger.getLogger(getClass()).error("PaParam_list with param_no=" + ex.getMessage());
            return null;
        } finally {
            try {
            	rs.close();
            	
            	if (cs != null)
            		cs.close();
                
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }

    public void addPaParam() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT into PA_PARAM_DATA ( " + " PARAM_NO,"
                + " FIELD1_VALUE," + " FIELD2_VALUE," + " RETURN_VALUE "
                + ") values (PA_PARAM_DATA_SEQ.NEXTVAL,?,?,?,?)";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getParam_no());
            ps.setString(2, this.getField1_value());
            ps.setString(3, this.getField2_value());
            ps.setString(4, this.getReturn_value());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_modify=" + ex.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }

    public void addPaParam(int configureNo, String[] field1,String[] fielden1,String[] fieldkor1, String[] field2,
    		 String[] fielden2, String[] fieldkor2, String[] return_value, String[] startMonth, String[] endMonth ,String[] sdEdValue ) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT into PA_PARAM_DATA ( PARAM_DATA_NO,"
                + " PARAM_NO, FIELD1_VALUE,FIELD1_EN_VALUE,FIELD1_KOR_VALUE," 
                + " FIELD2_VALUE,FIELD2_EN_VALUE,FIELD2_KOR_VALUE,"
                + " RETURN_VALUE, START_MONTH ,END_MONTH, SD_ED_VALUE,CPNY_ID"
                + ") values (PA_PARAM_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.setParamDefaultValue("" + configureNo);
        Logger.getLogger(getClass()).debug("Param Default Value: " + this.default_value);
        try {
            con = services.getConnection();
            con.setAutoCommit(false) ;
            ps = con.prepareStatement(sql);
            ps.setInt(1, configureNo);
            for (int i = 0; i < return_value.length; i++) {
                if (!return_value[i].equals("") || !sdEdValue[i].equals("")) {
                    ps.setString(2, StringUtils.trimToEmpty(field1[i]));
                    ps.setString(3, StringUtils.trimToEmpty(fielden1[i]));
                    ps.setString(4, StringUtils.trimToEmpty(fieldkor1[i]));
                    ps.setString(5, StringUtils.trimToEmpty(field2[i]));
                    ps.setString(6, StringUtils.trimToEmpty(fielden2[i]));
                    ps.setString(7, StringUtils.trimToEmpty(fieldkor2[i]));
                    ps.setString(8, StringUtils.trimToEmpty(return_value[i]));
                    ps.setString(9, StringUtils.trimToEmpty(startMonth[i]));
                    ps.setString(10, StringUtils.trimToEmpty(endMonth[i]));
                    ps.setString(11, StringUtils.trimToEmpty(sdEdValue[i])) ;
                    ps.setString(12, admin.getCpnyId()) ;
                    ps.addBatch() ;
                } 
            }
            
            ps.executeBatch() ;
            con.commit() ;
        } catch (Exception ex) {
        	ex.printStackTrace() ;
            Logger.getLogger(getClass()).error("PaParam_Add_Sort=" + ex.getMessage());
            try
            {
            	con.rollback() ;
            }catch(Exception e){}
        } finally {
            SqlUtil.close(ps, con);
        }
    }

    /*
     * 
     * modify data
     * 
     */

    public void modifyPaParam(String[] param_data_no, String[] return_value,String[] startMonth, String[] endMonth ,String[] sdEdValue ) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " Update PA_PARAM_DATA set RETURN_VALUE = ?,START_MONTH = ? ,END_MONTH = ? ,SD_ED_VALUE = ? where PARAM_DATA_NO = ?";
        try {
            int paramDataNo = 0;
            String returnValue = null;
            con = services.getConnection();
            con.setAutoCommit(false) ;
            
            ps = con.prepareStatement(sql);
            paramDataNo = Integer.parseInt(param_data_no[0]);
            this.setParamDefaultValue(paramDataNo);
            Logger.getLogger(getClass()).debug("Param Default Value: " + this.default_value);
            for (int i = 0; i < param_data_no.length; i++) {
                ps.setString(1, StringUtils.trimToEmpty(return_value[i]));
                ps.setString(2, StringUtils.trimToEmpty(startMonth[i]));
                ps.setString(3, StringUtils.trimToEmpty(endMonth[i]));
                ps.setString(4, StringUtils.trimToEmpty(sdEdValue[i]));
                ps.setInt(5, Integer.parseInt(param_data_no[i]));
                ps.addBatch() ;
            }
            ps.executeBatch() ;
            
            con.commit() ;
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_modify=" + ex.getMessage());
            try
            {
            	con.rollback() ;
            }catch(Exception e){}
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }

    public void modifyPaParam(String[] param_data_no, String[] param_no,
            String[] return_value, String personId, String chineseName,String pinyinName,String korName) {
        Connection con = null;
        PreparedStatement ps = null;
        String update = " Update PA_PARAM_DATA set RETURN_VALUE = ? "
                + " where PARAM_DATA_NO = ?";
        String insert = " INSERT into PA_PARAM_DATA ( PARAM_DATA_NO,"
                + " PARAM_NO,FIELD1_VALUE,FIELD1_EN_VALUE,FIELD1_KOR_VALUE," 
                + " FIELD2_VALUE,FIELD2_EN_VALUE,FIELD2_KOR_VALUE,"
                + " RETURN_VALUE,CPNY_ID "
                + ") values (PA_PARAM_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";

        try {
            con = services.getConnection();
            for (int i = 0; i < param_data_no.length; i++) {
                if (param_data_no[i].equals("0")) {
                    if (!return_value[i].equals("")) {
                        ps = con.prepareStatement(insert);
                        ps.setString(1, param_no[i]);
                        ps.setString(2, personId);
                        ps.setString(3, personId);
                        ps.setString(4, personId);
                        ps.setString(5, chineseName);
                        ps.setString(6, pinyinName);
                        ps.setString(7, korName);
                        ps.setString(8, StringUtil.toCN(return_value[i]));
                        ps.setString(9, admin.getCpnyId());
                        ps.executeUpdate();
                    }
                } else {
                    ps = con.prepareStatement(update);
                    ps.setString(1, StringUtil.toCN(return_value[i]));
                    ps.setInt(2, Integer.parseInt(param_data_no[i]));
                    ps.executeUpdate();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_modify=" + ex.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }
    
    public void modifyPaBonusParam(String[] param_data_no, String[] param_no,
            String[] return_value, String personId, String chineseName,String pinyinName,String korName) {
        Connection con = null;
        PreparedStatement ps = null;
        String update = " Update PA_BONUS_PARAM_DATA set RETURN_VALUE = ? "
                + " where PARAM_DATA_NO = ?";
        String insert = " INSERT into PA_BONUS_PARAM_DATA ( PARAM_DATA_NO,"
                + " PARAM_NO,FIELD1_VALUE, FIELD2_VALUE,RETURN_VALUE,CPNY_ID "
                + ") values (PA_BONUS_PARAM_DATA_SEQ.NEXTVAL,?,?,?,?,?)";

        try {
            con = services.getConnection();
            for (int i = 0; i < param_data_no.length; i++) {
                if (param_data_no[i].equals("0")) {
                    if (!return_value[i].equals("")) {
                        ps = con.prepareStatement(insert);
                        ps.setString(1, param_no[i]);
                        ps.setString(2, personId);
                        ps.setString(3, chineseName);
                        ps.setString(4, StringUtil.toCN(return_value[i]));
                        ps.setString(5, admin.getCpnyId());
                        ps.executeUpdate();
                    }
                } else {
                    ps = con.prepareStatement(update);
                    ps.setString(1, StringUtil.toCN(return_value[i]));
                    ps.setInt(2, Integer.parseInt(param_data_no[i]));
                    ps.executeUpdate();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_modify=" + ex.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }
    
    /*
     * 
     * delete data
     * 
     */

    public void removePaParam(int param_data_no) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "Delete From PA_PARAM_DATA where PARAM_DATA_NO = ? ";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param_data_no);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_Delete=" + ex.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }
    
    /*
     * 
     * delete data
     * 
     */

    public void removeBatchPaParam(String[] param_data_no) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "Delete From PA_PARAM_DATA where PARAM_DATA_NO = ? ";
        
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            
            for(int i = 0; i < param_data_no.length ; ++ i)
            {
            	//System.out.println(param_data_no[i]);
            	ps.setInt(1, NumberUtil.parseInt(param_data_no[i])) ;
            	ps.addBatch() ;
            }
            ps.executeBatch();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_Delete=" + ex.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }
    
    /*
     * 
     * delete data with param_no
     * 
     */

    public void removePaParam(String configureNo) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " Delete From PA_PARAM_DATA PA where PARAM_NO = ? AND CPNY_ID = ? ";
        Logger.getLogger(getClass()).debug(sql) ;
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, configureNo);
            ps.setString(2, admin.getCpnyId());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug("PaParam_Delete_with_param_no="
                    + ex.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.getMessage());
            }
        }
    }
    
	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getSdEdValue() {
		return sdEdValue;
	}

	public void setSdEdValue(String sdEdValue) {
		this.sdEdValue = sdEdValue;
	}

	

	

	
}