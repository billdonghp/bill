package com.ait.gm.cmd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */

public class WoodWorkingManager {


	/*添加木工制品信息*/
	public void addWoodProductsSet(HttpServletRequest request,AdminBean  admin){		
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
		String sql="insert into GA_SEALPRODUCTS_SET(WOODPRODUCTS_ID,PRODUCTS_NAME,MEASUREMENT_UNIT_PRICE,CREATE_DATE,CREATE_ID,ACTIVE,PRODUCTSTYPE,NOTES,CPNY_ID) values(" +
				"?,?,?,SYSDATE,?,'1',?,?,?)";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,NumberUtil.parseInt(request.getParameter("seqId")));
			stmt.setString(2,request.getParameter("PRODUCTS_NAME"));
			stmt.setDouble(3,Double.parseDouble(request.getParameter("MEASUREMENT_UNIT_PRICE")));
			stmt.setString(4,admin.getAdminID());
			stmt.setString(5,"Seal");
			stmt.setString(6,request.getParameter("notes"));
			stmt.setString(7,admin.getCpnyId());
			rs = stmt.executeUpdate();
			if(rs==0){
				request.setAttribute("errorMsg","制品名称重复，设置失败!");	
			}else{
				request.setAttribute("errorMsg","制品设置成功!");
			}
			
		} catch (Exception e) {
			request.setAttribute("errorMsg","制品名称重复，设置失败!");	
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close( stmt, conn);
		}
	
		
	}
	
	/*删除木工制品信息*/
	public void deleteWoodProductsSet(HttpServletRequest request){		
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
		String sql="update GA_SEALPRODUCTS_SET set ACTIVE='0' where WOODPRODUCTS_ID=?" ;
				
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,request.getParameter("tempforselect"));
			rs = stmt.executeUpdate();
			if(rs==0){
				request.setAttribute("errorMsg","制品删除失败!");	
			}else{
				request.setAttribute("errorMsg","制品删除成功!");
			}
			
		} catch (Exception e) {
		request.setAttribute("errorMsg","制品删除失败!");
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close( stmt, conn);
		}
	
		
	}
	
	/*修改木工制品信息*/
	public void updateWoodProductsSet(HttpServletRequest request,AdminBean admin){		
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
		String sql="update GA_SEALPRODUCTS_SET set PRODUCTS_NAME=?,MEASUREMENT_UNIT_PRICE=?,CREATE_DATE=SYSDATE,CREATE_ID=?,NOTES=? " +
				"where WOODPRODUCTS_ID=?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,request.getParameter("PRODUCTS_NAME"));
			stmt.setDouble(2,Double.parseDouble(request.getParameter("MEASUREMENT_UNIT_PRICE")));
			stmt.setString(3,admin.getAdminID());
			stmt.setString(4,request.getParameter("notes"));
			stmt.setInt(5,Integer.parseInt(request.getParameter("seqId")));
			rs = stmt.executeUpdate();
			if(rs==0){
				request.setAttribute("errorMsg","制品修改失败!");	
			}else{
				request.setAttribute("errorMsg","制品修改成功!");
			}
			
		} catch (Exception e) {
		request.setAttribute("errorMsg","制品修改失败!");
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close( stmt, conn);
		}
	
		
	}
	
	
	/*查看木工制品的所有信息*/
	public List getWoodProductsSet(AdminBean admin){	
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="select gws.woodproducts_id, gws.products_name,gws.measurement_units,gws.measurement_unit_price,gws.create_date,gws.create_id,gws.active, hr.chinesename, hr.deptid, dep.deptname from GM_WOODPRODUCTS_SET gws, hr_employee hr,hr_department dep where gws.create_id = hr.person_id(+) and dep.deptid(+)=hr.deptid  and gws.ACTIVE = '1' and gws.cpny_id='"+admin.getCpnyId()+"' order by gws.create_date desc";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				SimpleMap wp = new SimpleMap();
				wp.set("create_date",rs.getDate("create_date").toString());
				wp.set("create_id",rs.getString("create_id"));
				wp.set("deptid",rs.getString("deptid"));
				wp.set("deptname",rs.getString("deptname"));
				wp.set("chinesename",rs.getString("chinesename"));
				wp.set("measurement_unit_price",Double.parseDouble(rs.getString("measurement_unit_price")));
				wp.set("measurement_units",rs.getString("measurement_units"));
				wp.set("products_name",rs.getString("products_name"));
				wp.set("woodproducts_id",StringUtil.checkNull(rs.getInt("woodproducts_id")));
				if(rs.getString("measurement_units").equals("EachSquareMetres")){
				 wp.set("measurement_units_name","元/平方米");	
				/*}else if(rs.getString("measurement_units").equals("EachCubicMeters")){
				 wp.setMEASUREMENT_UNITS_name("每平方面(1面)");*/
				}else{
				wp.set("measurement_units_name","元/个");
				}
				list.add(wp);
			}
			
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close( stmt, conn);
		}
	
		return list;
	}
	
	/*查看木工制品的所有信息*/
	public ArrayList getAnWoodProductsSet(String productsid){	
		ArrayList list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="select gws.woodproducts_id, gws.products_name,gws.measurement_units,gws.measurement_unit_price,hr.chinesename,gws.create_date,gws.create_id,gws.active, hr.chinesename, hr.deptid, hr.chinesename,hr.deptid  from GM_WOODPRODUCTS_SET gws,hr_employee hr where gws.create_id=hr.empid  and gws.woodproducts_id="+productsid;
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()){
				list.add(rs.getString("products_name"));				
				list.add(rs.getString("measurement_units"));
				list.add(Double.parseDouble(rs.getString("measurement_unit_price")));				
			}
			
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close( stmt, conn);
		}
	
		return list;
	}


}
