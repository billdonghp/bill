package com.ait.pa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.web.ApplicationContext;

public class PaParamItem {
    private static ServiceLocator services;
    private final static Logger log = Logger.getLogger(PaParamItem.class);
    AdminBean admin =ApplicationContext.getTheadLocal();

    public PaParamItem() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

    /*
     * 
     * data list no pagecontrol
     * 
     */

    public Vector getPaParamList(String statTypeCode, String pamonth) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParamItem paParamItem = null;
        Vector v = new Vector();
        String sql = " Select "
                + " A.PARAM_NO,B.CONFIGURE_NO,"
                + " PARAM_NAME,PARAM_EN_NAME,PARAM_KOR_NAME,PARAM_ID,"
                + " B.DISTINCT_FIELD,"
                + " B.DISTINCT_FIELD_2ND,"
                + " B.PA_MONTH_STR,"
                + " B.DATA_TYPE,"
                + " DESCR,"
                + " B.GENERATE_TYPE_CODE,B.STAT_TYPE_CODE" 
                + " FROM PA_PARAM_ITEM A, PA_PARAM_ITEM_CONFIGURE B where A.PARAM_NO = B.PARAM_NO "  
                + " AND B.STAT_TYPE_CODE = CASE NVL(LENGTH(?),0) WHEN 0 THEN 'C_12067_1330306'  ELSE ? END " 
                +" AND (B.CPNY_ID='"+admin.getCpnyId()+"' OR B.CPNY_ID IS NULL )"
                +" AND B.PA_MONTH_STR = ? "
                + " order by PARAM_NO, PARAM_NAME " ;
                
        ResultSet rs = null;
        Logger.getLogger(getClass()).debug("PaParamItem_list_sql=" + sql);
        Logger.getLogger(getClass()).debug("statTypeCode=" + statTypeCode);
        Logger.getLogger(getClass()).debug("pamonth=" + pamonth);
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, statTypeCode);
            ps.setString(2, statTypeCode);
            ps.setString(3, pamonth);
            rs = ps.executeQuery();
            while (rs.next()) {
                paParamItem = new PaParamItem();
                paParamItem.setParam_no(rs.getInt("PARAM_NO"));
                paParamItem.setConfigureNO(rs.getInt("CONFIGURE_NO"));
                paParamItem.setParam_name(rs.getString("PARAM_NAME"));
                paParamItem.setParam_en_name(rs.getString("PARAM_EN_NAME"));
                paParamItem.setParam_kor_name(rs.getString("PARAM_KOR_NAME"));
                paParamItem.setParam_id(rs.getString("PARAM_ID"));
                paParamItem.setDistinct_field(rs.getString("DISTINCT_FIELD"));
                paParamItem.setDistinct_field_2nd(rs.getString("DISTINCT_FIELD_2ND"));
                paParamItem.setPa_month_str(rs.getString("PA_MONTH_STR"));
                paParamItem.setData_type(rs.getString("DATA_TYPE"));
                paParamItem.setDescr(rs.getString("DESCR"));
                paParamItem.setGenerate_type_code(rs.getString("GENERATE_TYPE_CODE"));
                paParamItem.setStatTypeCode(rs.getString("STAT_TYPE_CODE"));
                v.add(paParamItem);
            }
            return v;
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug(
                    "PaParamItem_list=" + ex.getMessage());
            return null;
        } finally {
            SqlUtil.close(rs, ps, con);
        }
    }

    /*
     * 
     * data detail
     * 
     */

    public static PaParamItem getPaParamItemDetail(String param_data_no) {
        Connection con = null;
        PreparedStatement ps = null;
        PaParamItem paParamItem = new PaParamItem();
        String sql = " Select PARAM_NO,"
                + " PARAM_NAME,PARAM_ID,PARAM_EN_NAME,PARAM_KOR_NAME, DISTINCT_FIELD," 
                + " DISTINCT_FIELD_2ND, PA_MONTH_STR,DATA_TYPE,DESCR,"
                + " GENERATE_TYPE_CODE, DEFAULT_VAL,STAT_TYPE_CODE,PA_PARAM_ITEM.CPNY_ID,SY_CODE.CODE_NAME AS CPNY_NAME "
                + " From PA_PARAM_ITEM,SY_CODE where PA_PARAM_ITEM.CPNY_ID = SY_CODE.CODE_ID(+) AND PARAM_NO = ?";
        log.debug(sql);
        ResultSet rs = null;
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, param_data_no);
            rs = ps.executeQuery();
            if (rs.next()) {
                paParamItem.setParam_no(rs.getInt("PARAM_NO"));
				paParamItem.setParam_name(rs.getString("PARAM_NAME"));
				paParamItem.setParam_id(rs.getString("PARAM_ID"));
				paParamItem.setParam_en_name(rs.getString("PARAM_EN_NAME"));
				paParamItem.setParam_kor_name(rs.getString("PARAM_KOR_NAME"));
				paParamItem.setDistinct_field(rs.getString("DISTINCT_FIELD"));
				paParamItem.setDistinct_field_2nd(rs.getString("DISTINCT_FIELD_2ND"));
				paParamItem.setPa_month_str(rs.getString("PA_MONTH_STR"));
				paParamItem.setData_type(rs.getString("DATA_TYPE"));
				paParamItem.setDescr(rs.getString("DESCR"));
				paParamItem.setGenerate_type_code(rs.getString("GENERATE_TYPE_CODE"));
				paParamItem.setGenerate_type_code(rs.getString("GENERATE_TYPE_CODE"));
				paParamItem.setDefault_val(rs.getString("DEFAULT_VAL"));
				paParamItem.setStatTypeCode(rs.getString("STAT_TYPE_CODE")) ;
				paParamItem.setCpnyId(rs.getString("CPNY_ID"));
				paParamItem.setCpnyName(rs.getString("CPNY_NAME"));
            }
            
        } catch (Exception ex) {
            Logger.getLogger("getPaParamItemDetail").debug(
                    "PaParamItem_Detail=" + ex.getMessage());
        } finally {
            SqlUtil.close(rs, ps, con);
        }
        return paParamItem;
    }
    
    public static PaParamItem getPaParamItemDetail(String configureNo,String paMonth,String lan) {
        
        PaParamItem paParamItem = new PaParamItem();
        
        Connection con1 = null;
        PreparedStatement ps1 = null;
        String sql1 = "Select pap.DISTINCT_FIELD,p1.field_name,p1.field_en_name,p1.field_kor_name" +
        			 " From PA_PARAM_ITEM_CONFIGURE pap, pa_distinct_list p1 " +
        			 " where CONFIGURE_NO = ? AND PA_MONTH_STR = ? and p1.distinct_field = pap.distinct_field";
        ResultSet rs1 = null;
        try {
        	
            con1 = services.getConnection();
            ps1 = con1.prepareStatement(sql1);
			ps1.setString(1, configureNo);
			ps1.setString(2, paMonth);
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
            	paParamItem.setDistinct_field_id(rs1.getString("DISTINCT_FIELD")) ;
	            if(lan.equals("zh")){
	                paParamItem.setDistinct_field(rs1.getString("FIELD_NAME"));
	            }else if(lan.equals("ko")){
	                paParamItem.setDistinct_field(rs1.getString("FIELD_KOR_NAME"));	            	
	            }else{
	                paParamItem.setDistinct_field(rs1.getString("FIELD_EN_NAME"));	            	
	            }
            }
        } catch (Exception ex) {
            Logger.getLogger("getPaParamItemDetail").error(
                    "PaParamItem_Detail=" + ex.getMessage());
        } finally {
            SqlUtil.close(rs1, ps1, con1);
        }
        
        
        Connection con2 = null;
        PreparedStatement ps2 = null;
        String sql2 = " Select p2.distinct_field,p2.field_name ," +
        			  " p2.field_kor_name , p2.field_en_name " +
        			  " From PA_PARAM_ITEM_CONFIGURE pap, pa_distinct_list p2 " +
        			  " where CONFIGURE_NO = ? AND PA_MONTH_STR = ? and p2.distinct_field = CASE pap.distinct_field WHEN 'PERSON_ID' THEN 'CHINESENAME' ELSE pap.distinct_field_2nd END ";
		ResultSet rs2 = null;
		log.debug(sql2);
		try {
			con2 = services.getConnection();
			ps2 = con2.prepareStatement(sql2);
			ps2.setString(1, configureNo);
			ps2.setString(2, paMonth);
			rs2 = ps2.executeQuery();
			if (rs2.next()) {
				if(lan.equals("zh")){
	                paParamItem.setDistinct_field_2nd(rs2.getString("FIELD_NAME"));
	            }else if(lan.equals("ko")){
	                paParamItem.setDistinct_field_2nd(rs2.getString("FIELD_KOR_NAME"));	            	
	            }else{
	                paParamItem.setDistinct_field_2nd(rs2.getString("FIELD_EN_NAME"));	            	
	            }
            }
		} catch (Exception ex) {
			Logger.getLogger("getPaParamItemDetail").error(
			       "PaParamItem_Detail=" + ex.getMessage());
		} finally {
			SqlUtil.close(rs2, ps2, con2);
		}
        
		Connection con = null;
        PreparedStatement ps = null;
        
        String sql = " SELECT A.PARAM_NO, A.PARAM_NAME,A.PARAM_ID,A.PARAM_EN_NAME,A.PARAM_KOR_NAME,B.DISTINCT_FIELD, "
        		   + " B.DISTINCT_FIELD_2ND,B.PA_MONTH_STR,B.DATA_TYPE,A.DESCR,B.GENERATE_TYPE_CODE,B.DEFAULT_VAL "
        		   + " FROM PA_PARAM_ITEM A, PA_PARAM_ITEM_CONFIGURE B "
        		   + " WHERE A.PARAM_NO = B.PARAM_NO AND B.CONFIGURE_NO = ? AND B.PA_MONTH_STR = ? " ;
        log.debug(sql);
        ResultSet rs = null;
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, configureNo);
            ps.setString(2, paMonth);
            rs = ps.executeQuery();
            if (rs.next()) {
                paParamItem.setParam_no(rs.getInt("PARAM_NO"));
                paParamItem.setParam_name(rs.getString("PARAM_NAME"));
                paParamItem.setParam_id(rs.getString("PARAM_ID"));
                paParamItem.setParam_en_name(rs.getString("PARAM_EN_NAME"));
                paParamItem.setParam_kor_name(rs.getString("PARAM_KOR_NAME"));
                paParamItem.setPa_month_str(rs.getString("PA_MONTH_STR"));
                paParamItem.setData_type(rs.getString("DATA_TYPE"));
                paParamItem.setDescr(rs.getString("DESCR"));
                paParamItem.setGenerate_type_code(rs.getString("GENERATE_TYPE_CODE"));
                paParamItem.setDefault_val(rs.getString("DEFAULT_VAL"));
            }
            
        } catch (Exception ex) {
            Logger.getLogger("getPaParamItemDetail").error(
                    "PaParamItem_Detail=" + ex.getMessage());
        } finally {
            SqlUtil.close(rs, ps, con);
        }
        return paParamItem;
    }


    /*
     * 
     * add data
     * 
     */

    public void addPaParamItem() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO PA_PARAM_ITEM ( " + " PARAM_NO,"
                + " PARAM_NAME," + " DISTINCT_FIELD," + " DISTINCT_FIELD_2ND,"
                + " PA_MONTH_STR," + " DATA_TYPE," + " DESCR, DEFAULT_VAL,"
                + " GENERATE_TYPE_CODE,PARAM_ID,STAT_TYPE_CODE,ORDER_NO,CPNY_ID "
                + ") values (PA_PARAM_ITEM_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,(SELECT NVL(MAX(PA.ORDER_NO),2000) + 1 FROM PA_PARAM_ITEM PA),?)";
        Logger.getLogger(getClass()).debug(sql);
        try {
            con = services.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, this.getParam_name());
			if ("".equals(this.getDistinct_field()) && !"".equals(this.getDistinct_field_2nd())) {
				// 第一区分字段为空,第二区分字段不为空,将不为空字段赋予第一区分字段
				ps.setString(2, this.getDistinct_field_2nd());
				ps.setString(3, this.getDistinct_field());
			} else {
				ps.setString(2, this.getDistinct_field());
				ps.setString(3, this.getDistinct_field_2nd());
			}
			ps.setString(4, this.getPa_month_str());
			ps.setString(5, this.getData_type());
			ps.setString(6, this.getDescr());
			ps.setString(7, this.getDefault_val());
			ps.setString(8, this.getGenerate_type_code());
			ps.setString(9, this.getParam_id());
			ps.setString(10, this.getStatTypeCode());
			ps.setString(11, this.getCpnyId());
			
			ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(
                    "PaParamItem_Add=" + ex.getMessage());
        } finally {
            SqlUtil.close(ps, con);
        }
    }

    /*
	 * 
	 * modify data
	 * 
	 */

    public void modifyPaParamItem() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " Update PA_PARAM_ITEM set " + " PARAM_NAME=?,"
                + " DISTINCT_FIELD=?," + " DISTINCT_FIELD_2ND=?,"
                + " PA_MONTH_STR=?," + " DATA_TYPE=?," + " DESCR=?,"
                + " DEFAULT_VAL=?," + " GENERATE_TYPE_CODE =?, PARAM_ID=?," 
                + " STAT_TYPE_CODE=?"
                + " where PARAM_NO = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, this.getParam_name());
			if ("".equals(this.getDistinct_field()) && !"".equals(this.getDistinct_field_2nd())) {
				ps.setString(2, this.getDistinct_field_2nd());
				ps.setString(3, this.getDistinct_field());
				Logger.getLogger(getClass()).debug("2: " + this.getDistinct_field_2nd());
				Logger.getLogger(getClass()).debug("3: " + this.getDistinct_field());
			} else {
				ps.setString(2, this.getDistinct_field());
				ps.setString(3, this.getDistinct_field_2nd());
				Logger.getLogger(getClass()).debug("2: " + this.getDistinct_field());
				Logger.getLogger(getClass()).debug("3: " + this.getDistinct_field_2nd());
			}
			ps.setString(4, this.getPa_month_str());
			ps.setString(5, this.getData_type());
			ps.setString(6, this.getDescr());
			ps.setString(7, this.getDefault_val());
			ps.setString(8, this.getGenerate_type_code());
			ps.setString(9, this.getParam_id());
			ps.setString(10, this.getStatTypeCode());
			ps.setInt(11, this.getParam_no());
			ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(
                    "PaParamItem_modify=" + ex.getMessage());
        } finally {
            SqlUtil.close(ps, con);
        }
    }

    /*
	 * 
	 * delete data
	 * 
	 */

    public void removePaParamItem(String param_no) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM PA_PARAM_DATA WHERE PARAM_NO = ?";
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            ps.setString(1, param_no);
            ps.executeUpdate();
            sql = "Delete From PA_PARAM_ITEM where PARAM_NO = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, param_no);
            ps.executeUpdate();
            con.commit();
        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Logger.getLogger(getClass()).debug(
                    "PaParamItem_Delete=" + ex.getMessage());
        } finally {
            SqlUtil.close(ps, con);
        }
    }

    public void initalPaParamItem(String pamonth,String statTypeCode) {
        Connection con = null;
        CallableStatement cs = null; // 建立参数声明
        try {
        	Logger.getLogger(getClass()).debug("call pr_param_initial(?,?,?) ") ;
        	Logger.getLogger(getClass()).debug("pamonth = " + pamonth) ;
        	Logger.getLogger(getClass()).debug("statTypeCode = " + statTypeCode) ;
        	Logger.getLogger(getClass()).debug("CpnyId = " + admin.getCpnyId()) ;
        	
            con = services.getConnection();
            cs = con.prepareCall("{call pr_param_initial(?,?,?)}");
            cs.setString(1, pamonth);
            cs.setString(2, statTypeCode);
            cs.setString(3, admin.getCpnyId());
            cs.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).debug(
                    "PaParamItem_inital=" + ex.getMessage());
        } finally {
            SqlUtil.close(cs, con);
        }
    }

    /*
     * table PA_PARAM_ITEM PARAM_NO GENERAL_TYPE_NO PARAM_NAME DISTINCT_FIELD
     * DISTINCT_FIELD_2ND PA_MONTH_STR DATA_TYPE DESCR GENERATE_TYPE_CODE
     */

    private int param_no;
    
    private String statTypeCode;
    
    private String param_name;
    
    private String param_id;

    private String param_en_name;

    private String param_kor_name;
    
    private String default_val;
    
    private String distinct_field_id ;
    
    private String distinct_field; 
    
    private String distinct_field_2nd;
    
    private String distinct_field_2nd_id ;
    
    private String generate_type_code;
    
    private String data_type;
    
    private String pa_month_str; 
    
    private String descr;
    
    private String cpnyId;
    
    private String cpnyName ;
    
    private int configureNO ;

	public String getCpnyId() {
		return cpnyId;
	}

	public void setCpnyId(String cpnyId) {
		this.cpnyId = cpnyId;
	}

	public int getParam_no() {
		return param_no;
	}

	public void setParam_no(int param_no) {
		this.param_no = param_no;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public String getParam_id() {
		return param_id;
	}

	public void setParam_id(String param_id) {
		this.param_id = param_id;
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

	public String getDefault_val() {
		return default_val;
	}

	public void setDefault_val(String default_val) {
		this.default_val = default_val;
	}

	public String getDistinct_field() {
		return distinct_field;
	}

	public void setDistinct_field(String distinct_field) {
		this.distinct_field = distinct_field;
	}

	public String getDistinct_field_2nd() {
		return distinct_field_2nd;
	}

	public void setDistinct_field_2nd(String distinct_field_2nd) {
		this.distinct_field_2nd = distinct_field_2nd;
	}

	public String getGenerate_type_code() {
		return generate_type_code;
	}

	public void setGenerate_type_code(String generate_type_code) {
		this.generate_type_code = generate_type_code;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getPa_month_str() {
		return pa_month_str;
	}

	public void setPa_month_str(String pa_month_str) {
		this.pa_month_str = pa_month_str;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDistinct_field_2nd_id() {
		return distinct_field_2nd_id;
	}

	public void setDistinct_field_2nd_id(String distinct_field_2nd_id) {
		this.distinct_field_2nd_id = distinct_field_2nd_id;
	}

	public String getDistinct_field_id() {
		return distinct_field_id;
	}

	public void setDistinct_field_id(String distinct_field_id) {
		this.distinct_field_id = distinct_field_id;
	}

	public String getStatTypeCode() {
		return statTypeCode;
	}

	public void setStatTypeCode(String statTypeCode) {
		this.statTypeCode = statTypeCode;
	}

	public String getCpnyName() {
		return cpnyName;
	}

	public void setCpnyName(String cpnyName) {
		this.cpnyName = cpnyName;
	}

	public int getConfigureNO() {
		return configureNO;
	}

	public void setConfigureNO(int configureNO) {
		this.configureNO = configureNO;
	}
    
   
}