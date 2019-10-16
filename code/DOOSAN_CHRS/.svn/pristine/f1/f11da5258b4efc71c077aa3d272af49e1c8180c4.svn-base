package com.ait.ar.dao.item;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItem;
import com.ait.ar.dao.ArItemBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.NumberUtil;
import com.ait.web.Command;

public class ItemAdd implements Command {
	private String returnR = null;

	private static ServiceLocator services;

	public ItemAdd() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int count = 0 ;
		ArItem info = new ArItem();
		ArItemBean dao = new ArItemBean();
		String menu_code = request.getParameter("menu_code");
		String itemName = request.getParameter("itemName");
		String itemEnName = request.getParameter("itemEnName");
		String itemKorName = request.getParameter("itemKorName");
		String itempGroupCode = request.getParameter("itempGroupCode");
		String itemId = request.getParameter("itemId");
		String shortName = request.getParameter("shortName");
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		int activity = NumberUtil.parseInt(request.getParameter("activity"));
		String description = request.getParameter("description");
		String cpnyId=request.getParameter("cpnyId");
		
		int itemNo = dao.getMaxSeq() + 1;
		ArrayList values = new ArrayList();
		values.add(String.valueOf(itemNo));
		values.add(itemName);
		values.add(itemEnName);
		values.add(itemKorName);
		values.add(shortName);
		values.add(description);
		values.add(new Integer(activity));
		values.add(itempGroupCode);
		values.add(itemId);
		values.add(adminId);
		values.add(cpnyId);
		info.setItemName(itemName) ;
		info.setItemGroupCode(itempGroupCode) ;
		info.setItemId(itemId) ;
		info.setShortName(shortName) ;
		info.setActivity(activity) ;
		info.setDescription(description) ;
		info.setCpnyId(cpnyId);
		String url = "/aritemlist.jsp?menu_code=" + menu_code;
		try {
			count = dao.validateItemNameInsert(info) ;
			if(count > 0)
			{
				url = "/aritem_a.jsp?menu_code=" + menu_code ;
				request.setAttribute("arItem", info) ;
				request.setAttribute("error", "error") ;
			}
			else
			{
				dao.addItem(values);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Insert attendance item Exception." + e.toString());
			throw new GlRuntimeException("Insert attendance item Exception.", e);
		}
		Logger.getLogger(getClass()).debug("return page : " + url);
		return url;
	}

	public boolean addItem(ArItem arItem) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ar_item_param (");
		sql.append("ar_param_no, ");
		sql.append("ar_item_no, ");
		sql.append("ar_group_no, ");
		sql.append("unit, ");
		sql.append("unit_value, ");
		sql.append("min_value, ");
		sql.append("max_value, ");
		sql.append("depend_item, ");
		sql.append("replace_item, ");
		sql.append("card_flag, ");
		sql.append("card_from_flag, ");
		sql.append("card_from_offset, ");
		sql.append("card_from_relation, ");
		sql.append("card_to_flag, ");
		sql.append("card_to_offset, ");
		sql.append("card_to_relation, ");
		sql.append("apply_flag, ");
		sql.append("apply_type, ");
		sql.append("apply_fullday_value, ");
		sql.append("apply_card_priority, ");
		sql.append("date_type, ");
		sql.append("activity,");
		sql.append("STAT_TYPE_CODE,");
		sql.append("WAGES_TYPE_CODE,");
		sql.append("SHIFT_NO,");
		sql.append("CPNY_ID");
		sql.append(") VALUES (ar_item_param_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = services.getConnection();
			stmt = con.prepareStatement(sql.toString());
			int i = 1;
			stmt.setInt(i++, arItem.getItemNo());
			stmt.setString(i++, arItem.getAr_group_no());
			stmt.setString(i++, arItem.getUnit());
			stmt.setFloat(i++, NumberUtil.parseFloat(arItem.getUnit_value()));
			
			if (arItem.getMin_value().equals(""))
				stmt.setString(i++, "");
			else
				stmt.setFloat(i++, Float.parseFloat(arItem.getMin_value()));
			
			if (arItem.getMax_value().equals(""))
				stmt.setString(i++, "");
			else
				stmt.setFloat(i++, NumberUtil.parseFloat(arItem.getMax_value()));
			
			stmt.setString(i++, arItem.getDepend_item());
			stmt.setString(i++, arItem.getReplace_item());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getCard_flag()));
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getCard_from_flag()));
			stmt.setFloat(i++, Float.parseFloat(arItem.getCard_from_offset()));
			stmt.setString(i++, arItem.getCard_from_relation());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getCard_to_flag()));
			stmt.setFloat(i++, Float.parseFloat(arItem.getCard_to_offset()));
			stmt.setString(i++, arItem.getCard_to_relation());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getApply_flag()));
			stmt.setString(i++, arItem.getApply_type());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getApply_fullday_value()));
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getApply_card_proior()));
			stmt.setString(i++, arItem.getDate_type());
			stmt.setInt(i++, arItem.getActivity());
			stmt.setString(i++, arItem.getStat_type_code());
			stmt.setString(i++, arItem.getWages_type_code());
			stmt.setString(i++, arItem.getShiftNo());
			stmt.setString(i++, arItem.getCpnyId());
			int isSuc = stmt.executeUpdate();
			if (isSuc > 0) {
				flag = true;
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("cant get connection" + e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new DataAccessException(" cant execute grouplist query! " + e);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException(
					"Insert attendance item parameter Exception.", e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return flag;

	}

	public boolean modifyItem(ArItem arItem) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		boolean flag = false;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		sql.append("UPDATE ar_item_param SET ");
		sql.append("unit = ?, ");
		sql.append("unit_value = ?, ");
		sql.append("min_value = ?, ");
		sql.append("max_value = ?, ");
		sql.append("depend_item = ?, ");
		sql.append("replace_item = ?, ");
		sql.append("card_flag = ?, ");
		sql.append("card_from_flag =?, ");
		sql.append("card_from_offset = ?, ");
		sql.append("card_from_relation = ?, ");
		sql.append("card_to_flag =?, ");
		sql.append("card_to_offset = ?, ");
		sql.append("card_to_relation = ?, ");
		sql.append("apply_flag = ?, ");
		sql.append("apply_type = ?, ");
		sql.append("apply_fullday_value = ?, ");
		sql.append("apply_card_priority = ?, ");
		sql.append("date_type = ?, ");
		sql.append("activity = ? ");
		sql.append("WHERE ar_item_no = ? and ar_group_no = ? and AR_PARAM_NO = ?");
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = services.getConnection();
			stmt = con.prepareStatement(sql.toString());
			int i = 1;
			stmt.setString(i++, arItem.getUnit());
			stmt.setFloat(i++, NumberUtil.parseFloat(arItem.getUnit_value()));
			
			if (arItem.getMin_value().equals(""))
				stmt.setString(i++, "");
			else
				stmt.setFloat(i++, NumberUtil.parseFloat(arItem.getMin_value()));
			
			if (arItem.getMax_value().equals(""))
				stmt.setString(i++, "");
			else
				stmt.setFloat(i++, NumberUtil.parseFloat(arItem.getMax_value()));
			
			stmt.setString(i++, arItem.getDepend_item());
			stmt.setString(i++, arItem.getReplace_item());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getCard_flag()));
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getCard_from_flag()));
			
			if (arItem.getCard_from_offset().equals(""))
				stmt.setString(i++, "");
			else
				stmt.setFloat(i++, NumberUtil.parseFloat(arItem.getCard_from_offset()));
			
			stmt.setString(i++, arItem.getCard_from_relation());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getCard_to_flag()));
			
			if (arItem.getCard_to_offset().equals(""))
				stmt.setString(i++, "");
			else
				stmt.setFloat(i++, NumberUtil.parseFloat(arItem.getCard_to_offset()));
			
			stmt.setString(i++, arItem.getCard_to_relation());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getApply_flag()));
			stmt.setString(i++, arItem.getApply_type());
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getApply_fullday_value()));
			stmt.setInt(i++, NumberUtil.parseInt(arItem.getApply_card_proior()));
			stmt.setString(i++, arItem.getDate_type());
			stmt.setInt(i++, arItem.getActivity());
			stmt.setInt(i++, arItem.getItemNo());
			stmt.setString(i++, arItem.getAr_group_no());
			stmt.setInt(i++, arItem.getArParamNo());
			int isSuc = stmt.executeUpdate();
			if (isSuc > 0) {
				flag = true;
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("cant get connection" + e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new DataAccessException(" cant execute grouplist query! " + e);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException(
					"Update attendance item parameter Exception.", e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return flag;

	}

	public ArItem getArItem(String ar_item_no, String ar_group_no,String paramNo)
			throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		ArItem arItem = new ArItem();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		sql.append("SELECT ar_item_param.*,ar_item.*, ar_dynamic_group.*,sy_code.code_name as stat_Type,wages.Code_Name AS wages_type,ar_shift010.Shift_Name ");
		sql.append("FROM ar_item_param, ");
		sql.append("ar_item, ");
		sql.append("ar_dynamic_group, ");
        sql.append("sy_code,");
        sql.append("sy_code wages,");
        sql.append("ar_shift010 ");
		sql.append("WHERE ar_item_no = ? ");
		sql.append("AND ar_item_no = item_no ");
		sql.append("AND to_char(ar_dynamic_group.group_no(+)) = ar_item_param.ar_group_no ");
        sql.append("AND ar_item_param.Wages_Type_Code = wages.Code_Id  ");
        sql.append("AND ar_item_param.Shift_No = to_char(ar_shift010.shift_no(+)) ");
		sql.append("AND ar_group_no  = ? ") ;
		sql.append("AND AR_PARAM_NO  = ? ") ;
		sql.append("AND ar_item_param.Stat_Type_Code = sy_code.code_id") ;
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = services.getConnection();

			stmt = con.prepareStatement(sql.toString());
			int i = 1;
			stmt.setInt(i++, NumberUtil.parseInt(ar_item_no));
			stmt.setString(i++, ar_group_no);
			stmt.setInt(i++, NumberUtil.parseInt(paramNo));
			rs = stmt.executeQuery();
			if (rs.next()) {
				arItem.setItemNo(rs.getInt("ar_item_no"));
				arItem.setItemName(rs.getString("item_name"));
				arItem.setAr_group_no(rs.getString("ar_group_no"));
				arItem.setAr_group_no_name(rs.getString("group_name"));
				arItem.setAr_group_no_en_name(rs.getString("group_en_name"));
				arItem.setAr_group_no_kor_name(rs.getString("group_kor_name"));
				arItem.setUnit(rs.getString("unit"));
				arItem.setUnit_value(rs.getString("unit_value"));
				arItem.setMin_value(rs.getString("min_value"));
				arItem.setMax_value(rs.getString("max_value"));
				arItem.setDepend_item(rs.getString("depend_item"));
				arItem.setReplace_item(rs.getString("replace_item"));
				arItem.setCard_flag(rs.getString("card_flag"));
				arItem.setCard_from_flag(rs.getString("card_from_flag"));
				arItem.setCard_from_offset(rs.getString("card_from_offset"));
				arItem.setCard_from_relation(rs.getString("card_from_relation"));
				arItem.setCard_to_flag(rs.getString("card_to_flag"));
				arItem.setCard_to_offset(rs.getString("card_to_offset"));
				arItem.setCard_to_relation(rs.getString("card_to_relation"));
				arItem.setApply_flag(rs.getString("apply_flag"));
				arItem.setApply_type(rs.getString("apply_type"));
				arItem.setApply_fullday_value(rs.getString("apply_fullday_value"));
				arItem.setApply_card_proior(rs.getString("apply_card_priority"));
				arItem.setDate_type(rs.getString("date_type"));
				arItem.setActivity(rs.getInt("activity"));
				arItem.setStat_type(rs.getString("stat_Type")) ;
				arItem.setWages_type(rs.getString("wages_type")) ;
				arItem.setShiftName(rs.getString("Shift_Name")) ;
				arItem.setShiftNo(rs.getString("shift_no")) ;
				
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("cant get connection" + e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new DataAccessException(" cant execute grouplist query! " + e);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException(
					"Retrieve attendance item parameter Exception.", e);
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return arItem;
	}

	public boolean deleteArItem(String ar_item_no, String ar_group_no,String paramNo)
			throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		Connection con = null;
		PreparedStatement stmt = null;
		sql.append("DELETE ");
		sql.append("FROM ar_item_param ");
		sql.append("WHERE ar_item_no = ? ");
		sql.append("AND ar_group_no  = ?");
		sql.append("AND AR_PARAM_NO  = ?");
		boolean flag = false;
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = services.getConnection();
			stmt = con.prepareStatement(sql.toString());
			int i = 1;
			stmt.setInt(i++, NumberUtil.parseInt(ar_item_no));
			stmt.setString(i++, ar_group_no);
			stmt.setInt(i++, NumberUtil.parseInt(paramNo));
			int isSuc = stmt.executeUpdate();
			if (isSuc > 0) {
				flag = true;
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("cant get connection" + e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new DataAccessException(" cant execute grouplist query! " + e);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException(
					"Delete attendance item parameter Exception.", e);
		} finally {
			SqlUtil.close(stmt, con);
		}
		return flag;
	}
}
