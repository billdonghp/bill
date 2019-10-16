package com.ait.sy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.HrSysparam;
import com.ait.sysparam.Sysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.sysparam.TaskSysparam;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class SyViewCommand implements Command {

	private String adminId; //当前登录者ID
	
	private String admincpnyId;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		admincpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String content = StringUtil.checkNull(request.getParameter("content"));
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug("content : " + content);
		if (content.equals("essparam"))
			return this.viewEssParam(request,admincpnyId);
		else if (content.equals("essparamSearch"))
			return this.searchEssParam(request, request.getParameter("cpnyId"));
		else if (content.equals("hrparam"))
			return this.viewHrParam(request);
		else if (content.equals("ifparam"))
			return this.viewIfParam(request);
		else if (content.equals("viewoption"))
			return this.viewOption(request);
		else if (content.equals("searchOption"))
			return this.searchOption(request, request.getParameter("cpnyId"));
		else if (content.equals("taskparam"))		
			return this.viewTaskParam(request);
		else if (content.equals("taskparamSearch"))
			return this.taskparamSearch(request, request.getParameter("cpnyId"));
		else
			return "/error.jsp";
	}
	
	private String searchEssParam(HttpServletRequest request,String cpnyId) {
		Sysparam sysparam = SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		request.setAttribute("sysparam", sysparam);
		request.setAttribute("cpnyId", cpnyId);
		return "/sy_param_ess.jsp";
	}


	private String viewEssParam(HttpServletRequest request,String admincpnyId) {
		Sysparam sysparam = SysparamUtils.getSysparam(EssSysparam.class,admincpnyId);
		request.setAttribute("sysparam", sysparam);
		request.setAttribute("cpnyId", admincpnyId);
		return "/sy_param_ess.jsp";
	}

	private String viewHrParam(HttpServletRequest request) {
		Sysparam sysparam = SysparamUtils.getSysparam(HrSysparam.class,admincpnyId);
		request.setAttribute("sysparam", sysparam);
		return "/sy_param_hr.jsp";
	}

	private String viewIfParam(HttpServletRequest request) {
		SysService service = SysService.getInstance();

		String emTable = request.getParameter("emTable");
		if (StringUtils.isEmpty(emTable)) {
			List<String> emTables = service.getEmTables();
			request.setAttribute("emTables", emTables);
		} else {
			request.setAttribute("emTable", emTable);
			request.setAttribute("orderNo", service.getEmOrder(emTable));
		}

		String ifTable = request.getParameter("ifTable");
		if (StringUtils.isEmpty(ifTable)) {
			List<String> ifTables = service.getIfTables(null);
			request.setAttribute("ifTables", ifTables);
		} else {
			request.setAttribute("ifTable", ifTable);
			if (StringUtils.isNotEmpty(emTable)) {
				request.setAttribute("emColumns", service.getEmColumns(emTable, ifTable));
				request.setAttribute("ifColumns", service.getIfColumns(null, ifTable));
			}
		}

		return "/sy_param_if.jsp";
	}
	
	private String viewOption(HttpServletRequest request) {
		request.setAttribute("cpnyId", admincpnyId);
		return "/sy_view_option.jsp";
	} 
	private String searchOption(HttpServletRequest request,String cpnyId) {
		request.setAttribute("cpnyId", cpnyId);
		return "/sy_view_option.jsp";
	} 
	
	private String viewTaskParam(HttpServletRequest request) {
		Sysparam sysparam = SysparamUtils.getSysparam(TaskSysparam.class,admincpnyId);
		request.setAttribute("cpnyId", admincpnyId);
		request.setAttribute("sysparam", sysparam);
		return "/sy_param_task.jsp";
	}
	private String taskparamSearch(HttpServletRequest request,String cpnyid) {
		Sysparam sysparam = SysparamUtils.getSysparam(TaskSysparam.class,cpnyid);
		request.setAttribute("cpnyId", cpnyid);
		request.setAttribute("sysparam", sysparam);
		return "/sy_param_task.jsp";
	}
}