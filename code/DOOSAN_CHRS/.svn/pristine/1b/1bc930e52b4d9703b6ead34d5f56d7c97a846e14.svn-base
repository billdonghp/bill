package com.ait.sy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.sy.bean.ReportItem;
import com.ait.sy.bean.ReportTable;
import com.ait.sy.service.SysService;
import com.ait.util.StringUtil;
import com.ait.util.NumberUtil;
import com.ait.web.Command;


public class SySaveCommand implements Command {
	
	private String adminId;			//当前登录者ID
	
	private SysService sysService ;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String content = StringUtil.checkNull(request.getParameter("content"));
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug("content : " + content);
		if (content.equals("viewoption"))
			return this.saveViewOption(request);
		else
			return "/error.jsp";
	}

	private String saveViewOption(HttpServletRequest request){
		String msg = "" ;
		String menu = request.getParameter("menu");
		String temp[] = request.getParameterValues("temp");
		String del_rt_no = StringUtil.checkNull(request.getParameter("del_rt_no"));
		String update_rt_no = StringUtil.checkNull(request.getParameter("update_rt_no"));
		String temp_for_add_item = StringUtil.checkNull(request.getParameter("temp_for_add_item"));
		String cpnyId=StringUtil.checkNull(request.getParameter("cpnyId"));
		ReportTable reportTable = null ;
		String[] rt_nos = null ;
		sysService = sysService.getInstance();
		List<ReportTable> dataList = new ArrayList<ReportTable>();
		List<ReportTable> delList = new ArrayList<ReportTable>();
		List<ReportTable> updateList = new ArrayList<ReportTable>();
		
		try {
			if(!del_rt_no.equals("")){
				rt_nos = del_rt_no.split(",");
				for (String tempRt_no : rt_nos) {
					delList.add(new ReportTable(NumberUtil.parseInt(tempRt_no)));
				}
			}
			
			if(!update_rt_no.equals("")){
				rt_nos = update_rt_no.split(",");
				for (String tempRt_no : rt_nos) {
					reportTable = new ReportTable(NumberUtil.parseInt(tempRt_no));
					reportTable.setTable_name(request.getParameter("name_"+tempRt_no));
					reportTable.setTable_en_name(StringUtil.checkNull(request.getParameter("en_name_"+tempRt_no)));
					reportTable.setTable_kor_name(StringUtil.checkNull(request.getParameter("kor_name_"+tempRt_no)));
					reportTable.setReport_type(NumberUtil.parseInt(request.getParameter("report_type_"+tempRt_no)));
					reportTable.setView_model(NumberUtil.parseInt(request.getParameter("show_model_"+tempRt_no)));
					reportTable.setUpdate_by(this.adminId);
					updateList.add(reportTable);
				}
			}
			
			if(temp != null){
				int nextRtNo = sysService.retrieveReportTableNextPK().intValue() ;
				for (int i = 0; i < temp.length; i++) {
					reportTable = new ReportTable();
					reportTable.setRt_no(nextRtNo + i);
					reportTable.setMenu_code(menu);
					reportTable.setTable_name(request.getParameter("name"+temp[i]));
					reportTable.setTable_kor_name(StringUtil.checkNull(request.getParameter("kor_name"+temp[i])));
					reportTable.setTable_en_name(StringUtil.checkNull(request.getParameter("en_name"+temp[i])));
					reportTable.setReport_type(NumberUtil.parseInt(request.getParameter("report_type"+temp[i])));
					reportTable.setView_model(NumberUtil.parseInt(request.getParameter("show_model"+temp[i])));
					reportTable.setCreate_by(this.adminId);
					if(!temp_for_add_item.equals("")){
						List<ReportItem> reportItemList = new ArrayList<ReportItem>();
						ReportItem reportItem = null ;
						String[] tfai = temp_for_add_item.split(",");
						String add_item_nos[] = StringUtil.checkNull(request.getParameter("add_item_nos")).split(",");
						String add_order_nos[] = StringUtil.checkNull(request.getParameter("add_order_nos")).split(",");
						String add_item_ids[] = StringUtil.checkNull(request.getParameter("add_item_ids")).split(",");
						String add_ref_tn[] = StringUtil.checkNull(request.getParameter("add_ref_tn")).split(",");
						String add_item_names[] = StringUtil.checkNull(request.getParameter("add_item_names")).split(",");
						String add_item_kor_names[] = StringUtil.checkNull(request.getParameter("add_item_kor_names")).split(",");
						String add_item_en_names[] = StringUtil.checkNull(request.getParameter("add_item_en_names")).split(",");
						for(int j = 0; j < tfai.length; j++){
							if(tfai[j].equals(temp[i])){
								reportItem = new ReportItem();
								reportItem.setRt_no(reportTable.getRt_no());
								reportItem.setItem_name(add_item_names[j]);
								reportItem.setItem_kor_name((add_item_kor_names[j].equals("null")?"":add_item_kor_names[j]));
								reportItem.setItem_en_name((add_item_en_names[j].equals("null")?"":add_item_en_names[j]));
								reportItem.setRef_table_name(add_ref_tn[j]);
								reportItem.setRef_item_no(NumberUtil.parseInt(add_item_nos[j]));
								reportItem.setOrder_no(add_order_nos[j]);
								reportItem.setRef_item_id(add_item_ids[j]);
								reportItem.setCreate_by(this.adminId);
								reportItemList.add(reportItem);
							}
						}
						reportTable.setReportItems(reportItemList);
					}
					reportTable.setCpny_id(cpnyId);
					dataList.add(reportTable);
				}
			}
			sysService.insertReportTable(dataList,delList,updateList,cpnyId);
			msg = "保存成功!" ;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg = "保存失败!" ;
		}
		request.setAttribute("alert", msg);
		request.setAttribute("url","/syControlServlet?operation=view&content=viewoption&menu_code="+ request.getParameter("menu_code"));
		return "/inc/alertMessage.jsp";
	}
}
