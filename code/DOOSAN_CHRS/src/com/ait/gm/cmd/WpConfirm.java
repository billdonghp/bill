package com.ait.gm.cmd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ga.bean.WoodProductsBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class WpConfirm  {

	/*得到未确认的定单号*/
	public String getNotConfirmDocumentNO(){
		String str = "";			
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="select  distinct gwa.document_no   from ga_woodproducts_apply gwa where gwa.document_no in( "+
                   "select t.apply_no from ga_woodproducts_affirm t where t.affirm_flag='1' and t.activity='1') "+
                   "and gwa.active='1' and gwa.confirm_flag='0'";

		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs==null){
				str="没有未确认的定单!";
			}		
			while(rs.next()){
	                str+=(rs.getString("document_no")+"，");
		      }			
			
			
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close( stmt, conn);
		}
	
		return str;
	}	
	/*得到未确认的定单号*/
	public String isCanConfirm(String documentno){
		String str = "";			
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="select distinct gwa.confirm_flag  from ga_woodproducts_apply gwa  where gwa.active='1' and gwa.document_no='"+documentno+"'";

		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()){
	             str=rs.getString("confirm_flag");
		     }			
			
			
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
	
		return str;
	}
	
	/*通过定单号等到申请信息*/
	public List getApplerList(String documentno){
		List list = new ArrayList();	
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql=" select distinct gwa.document_no,gwa.wp_applyerid,gwa.wp_applyername,gwa.wp_applyerdeptname,to_char(gwa.hope_completed_date,'yyyy-mm-dd') hope_completed_date"+
				   " from ga_woodproducts_apply gwa"+
				   " where gwa.active = '1'"+
				  " and gwa.document_no='"+documentno+"'";
   
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
       if(rs.next()){
        list.add(rs.getString("document_no"));
        list.add(rs.getString("wp_applyerid"));
        list.add(rs.getString("wp_applyername"));
        list.add(rs.getString("wp_applyerdeptname"));
        list.add(rs.getString("hope_completed_date"));
        }
			
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
	
		return list;
	}
	
	/*通过编号查得制品信息*/
	public List getWpListForExcel(String documentno){
		List list = new ArrayList();	
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="select distinct   gwa.wpname,gws.measurement_units,gws.measurement_unit_price,gwa.wpnumber, gwa.wp_l,gwa.wp_w,gwa.wp_h, gwa.wp_sumarea, gwa.wp_sumprice "+        
                "  from ga_woodproducts_apply gwa, gm_woodproducts_set gws"+  
                "  where gwa.active = '1'"+  
                "  and gws.woodproducts_id = gwa.wpid"+  
                "  and gwa.document_no='"+documentno+"'";    
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
         while(rs.next()){
        	 WoodProductsBean wpb = new WoodProductsBean();        	
				wpb.setWPNAME(rs.getString("wpname"));
				wpb.setWPNUMBER(rs.getInt("wpnumber"));
				wpb.setConfirm_confirmprice(Double.parseDouble(rs.getString("wp_sumprice")));
				wpb.setMEASUREMENT_UNITS(rs.getString("measurement_units"));
				wpb.setMEASUREMENT_UNIT_PRICE(Double.parseDouble(rs.getString("measurement_unit_price")));
				wpb.setWP_SUMAREA(Double.parseDouble(rs.getString("wp_sumarea")));
				wpb.setWP_H(Double.parseDouble(rs.getString("wp_h")));
				wpb.setWP_L(Double.parseDouble(rs.getString("wp_l")));
				wpb.setWP_W(Double.parseDouble(rs.getString("wp_w")));
				list.add(wpb);        	 
         }
			
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
	
		return list;
	}
	

}
