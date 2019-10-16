package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.sy.bean.AdminBean;
import com.ait.sy.bean.ReportItem;
import com.ait.sy.service.SysService;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class UpdateReportItemCommand implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String msg = "保存成功!";
		String add_item_ids[] = StringUtil.checkNull(request.getParameter("add_item_ids")).split(",");
		String add_order_nos[] = StringUtil.checkNull(request.getParameter("add_order_nos")).split(",");
		String add_ri_nos[] = StringUtil.checkNull(request.getParameter("add_ri_nos")).split(",");
		String add_item_names[] = StringUtil.checkNull(request.getParameter("add_item_names")).split(",");
		String add_item_kor_names[] = StringUtil.checkNull(request.getParameter("add_item_kor_names")).split(",");
		String add_item_en_names[] = StringUtil.checkNull(request.getParameter("add_item_en_names")).split(",");
		ReportItem reportItem = null;
		List<ReportItem> reportItemList = new ArrayList<ReportItem>();
		for (int j = 0; j < add_ri_nos.length; j++) {
			reportItem = new ReportItem();
			reportItem.setOrder_no(add_order_nos[j]);
			reportItem.setRef_item_id(add_item_ids[j]);
			reportItem.setRi_no(NumberUtil.parseInt(add_ri_nos[j]));
			reportItem.setItem_name(add_item_names[j]);
			reportItem.setItem_kor_name((add_item_kor_names[j].equals("null") ? "" : add_item_kor_names[j]));
			reportItem.setItem_en_name((add_item_en_names[j].equals("null") ? "" : add_item_en_names[j]));
			reportItem.setUpdate_by(adminId);
			reportItemList.add(reportItem);
		}
		try {
			SysService.getInstance().updateReportItem(reportItemList);
		} catch (Exception e) {
			// TODO: handle exception
			msg = "保存失败!";
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(msg);
		out.close();
		return "";
	}

}
