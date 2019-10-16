package com.ait.kpa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class PaParam {
    private static ServiceLocator services;

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
    
    private String groupId;

    public String getParamDefaultValue(String paramNo) {
        this.setParamDefaultValue(paramNo);
        return this.default_value;
    }
    
    public String getDiffDefaultValue(String paramNo) {
        this.setDiffDefaultValue(paramNo);
        return this.default_value;
    }

    public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setParamDefaultValue(String paramNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select NVL(DEFAULT_VAL, '') From KPA_PARAM_ITEM WHERE PARAM_NO = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, paramNo);
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
    
    public void setDiffDefaultValue(String paramNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select NVL(DEFAULT_VAL, '') From KPA_DIFF_PARAM_ITEM WHERE PARAM_NO = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, paramNo);
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
        String sql = "SELECT NVL(PA_PARAM_ITEM.DEFAULT_VAL, '') "
                + "FROM PA_PARAM_ITEM, PA_PARAM_DATA "
                + "WHERE PA_PARAM_DATA.PARAM_NO = PA_PARAM_ITEM.PARAM_NO "
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

	public Vector getPaParamViewList(String param_no, String deptid, String key) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        Vector v = new Vector();
        String sql = " Select "
                + " PARAM_DATA_NO,PARAM_NO,"
                //+ " FIELD1_VALUE ,FIELD1_EN_VALUE,FIELD1_KOR_VALUE,"
                + " (select MAX(e.group_emp_id) from HR_EMPLOYEE E where e.EMPID=d.FIELD1_VALUE OR e.group_emp_id=d.FIELD1_VALUE) FIELD1_VALUE ,FIELD1_EN_VALUE,FIELD1_KOR_VALUE,"
                + " FIELD2_VALUE ,FIELD2_EN_VALUE,FIELD2_KOR_VALUE,"
                + " RETURN_VALUE ,START_MONTH ,END_MONTH ,SD_ED_VALUE"
                + " From KPA_PARAM_DATA d,HR_EMPLOYEE HR where FIELD1_VALUE = HR.EMPID(+) AND PARAM_NO = ? AND HR.KO_CALC_FLAG = 'Y' AND HR.JOIN_TYPE_CODE = 'C_12009_1330064' " ;
        if(deptid != null && deptid.length() > 0)
        {
        	sql = sql + " AND EXISTS( SELECT HD.DEPTID FROM HR_DEPARTMENT HD WHERE HR.DEPTID = HD.DEPTID  START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID ) " ;
        }
        
        if(key != null && key.length() > 0)
        {
        	key = "%" + key.toLowerCase() + "%" ;
        	sql = sql + " AND (HR.CHINESENAME LIKE '%' || ? || '%' OR UPPER(HR.EMPID) LIKE '%' || UPPER(?) || '%' OR UPPER(HR.CHINESE_PINYIN) LIKE '%' || UPPER(?) || '%') " ;
        }
        sql = sql + " order by FIELD1_VALUE,FIELD2_VALUE";
        
        ResultSet rs = null;
        Logger.getLogger(getClass()).debug("PaParam_list_sql=" + sql);
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            int i = 1 ;
            ps.setString(i++, param_no);
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

    public Vector getPaParamList(String pamonth, String empID, String deptid) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        Vector v = new Vector();
        String sql = " SELECT hr_employee.empid,pa_param_item.param_name, pa_param_item.PARAM_NO, "
        		   + " pa_param_item.pa_month_str, pa_param_data.return_value, pa_param_data.param_data_no "
        		   + " FROM kpa_param_item,kpa_param_data,hr_employee "
        		   + " WHERE kpa_param_item.distinct_field = 'EMPID' "
        		   + "   AND hr_employee.empid = ? "
        		   + "   AND kpa_param_item.PA_MONTH_STR = ? "
        		   + "   AND kpa_param_item.param_no = kpa_param_data.Param_No(+) "
        		   + "   AND kpa_param_data.Field1_Value = hr_employee.empid "
        		   + " ORDER BY pa_month_str, empid ";
        ResultSet rs = null;
        Logger.getLogger(getClass()).debug("PaParam_list_sql=" + sql);
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, empID);
            ps.setString(2, pamonth);
            ps.setString(3, deptid);
            rs = ps.executeQuery();
            while (rs.next()) {
                paParam = new PaParam();
                paParam.setParam_date_no(rs.getInt("PARAM_DATA_NO"));
                paParam.setParam_no(rs.getInt("PARAM_NO"));
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
    public Vector AddPaParamList(String param_no, String actionType, String deptid, String key) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParam paParam = null;
        CallableStatement cs = null; // 建立参数声明
        Vector v = new Vector();
        String sql = " select HR.group_emp_id GROUPID,field1,field1_en,field1_kor,field2,field2_en, field2_kor from kpa_param_data_list,hr_employee hr where hr.empid = field1 AND KO_CALC_FLAG = 'Y' AND JOIN_TYPE_CODE = 'C_12009_1330064' ";
        if(deptid != null && deptid.length() > 0)
        {
        	sql = sql + " AND EXISTS( SELECT HD.DEPTID FROM HR_DEPARTMENT HD WHERE HR.DEPTID = HD.DEPTID  START WITH DEPTID = ? CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID ) " ;
        }
        
        if(key != null && key.length() > 0)
        {
        	key = "%" + key.toLowerCase() + "%" ;
        	sql = sql + " AND (HR.CHINESENAME LIKE '%' || ? || '%' OR UPPER(HR.EMPID) LIKE '%' || UPPER(?) || '%' OR UPPER(HR.CHINESE_PINYIN) LIKE '%' || UPPER(?) || '%') " ;
        }
        sql = sql + " order by field1, field2";
        
        ResultSet rs = null;
        try {
            con = services.getConnection();
            
            if (!actionType.equals("search"))
            {
            	cs = con.prepareCall("{call kpa_param_list(?)}");
                cs.setString(1, param_no);
                cs.execute();
            }
            ps = con.prepareStatement(sql);
            int i = 1 ;
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
            
            Logger.getLogger(getClass()).debug("PaParam_list SQL= " + sql);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                paParam = new PaParam();
                paParam.setField1_value(rs.getString("field1"));
                paParam.setField1_en_value(rs.getString("field1_en"));
                paParam.setField1_kor_value(rs.getString("field1_kor"));
                paParam.setField2_value(rs.getString("field2"));
                paParam.setField2_en_value(rs.getString("field2_en"));
                paParam.setField2_kor_value(rs.getString("field2_kor"));
                paParam.setGroupId(rs.getString("GROUPID"));
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

    public void addPaParam(int param_no, String[] field1,String[] fielden1,String[] fieldkor1, String[] field2,
    		 String[] fielden2, String[] fieldkor2, String[] return_value, String[] startMonth, String[] endMonth ,String[] sdEdValue ) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT into KPA_PARAM_DATA ( PARAM_DATA_NO,"
                + " PARAM_NO, FIELD1_VALUE,FIELD1_EN_VALUE,FIELD1_KOR_VALUE," 
                + " FIELD2_VALUE,FIELD2_EN_VALUE,FIELD2_KOR_VALUE,"
                + " RETURN_VALUE, START_MONTH ,END_MONTH, SD_ED_VALUE"
                + ") values (KPA_PARAM_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
        this.setParamDefaultValue("" + param_no);
        System.out.println(sql);
        Logger.getLogger(getClass()).debug("Param Default Value: " + this.default_value);
        try {
            con = services.getConnection();
            con.setAutoCommit(false) ;
            ps = con.prepareStatement(sql);
            ps.setInt(1, param_no);
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
        String sql = " Update KPA_PARAM_DATA set RETURN_VALUE = ?,START_MONTH = ? ,END_MONTH = ? ,SD_ED_VALUE = ? where PARAM_DATA_NO = ?";
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
            String[] return_value, String empID, String chineseName,String pinyinName,String korName) {
        Connection con = null;
        PreparedStatement ps = null;
        String update = " Update PA_PARAM_DATA set " + " RETURN_VALUE = ? "
                + " where PARAM_DATA_NO = ?";
        String insert = " INSERT into PA_PARAM_DATA ( " + " PARAM_DATA_NO,"
                + " PARAM_NO," + " FIELD1_VALUE,FIELD1_EN_VALUE,FIELD1_KOR_VALUE," + " FIELD2_VALUE,FIELD2_EN_VALUE,FIELD2_KOR_VALUE,"
                + " RETURN_VALUE "
                + ") values (PA_PARAM_DATA_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";

        try {
            con = services.getConnection();
            for (int i = 0; i < param_data_no.length; i++) {
                if (param_data_no[i].equals("0")) {
                    if (!return_value[i].equals("")) {
                        ps = con.prepareStatement(insert);
                        ps.setString(1, param_no[i]);
                        ps.setString(2, empID);
                        ps.setString(3, empID);
                        ps.setString(4, empID);
                        ps.setString(5, chineseName);
                        ps.setString(6, pinyinName);
                        ps.setString(7, korName);
                        ps.setString(8, StringUtil.toCN(return_value[i]));
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
        String sql = "Delete From KPA_PARAM_DATA where PARAM_DATA_NO = ? ";
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
     * delete data with param_no
     * 
     */

    public void removePaParam(String param_no) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "Delete From KPA_PARAM_DATA where PARAM_NO = ? ";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, param_no);
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