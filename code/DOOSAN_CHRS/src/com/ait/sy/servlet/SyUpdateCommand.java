package com.ait.sy.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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

public class SyUpdateCommand implements Command {

	private SysService sysService = SysService.getInstance();

	private String adminId; // 当前登录者ID
	
	private String cpnyId;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String content = StringUtil.checkNull(request.getParameter("content"));
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug("content : " + content);
		if (content.equals("essparam"))
			return this.viewEssParam(request);
		if (content.equals("hrparam"))
			return this.viewHrParam(request);
		if (content.equals("ifparam"))
			return this.updateIfParam(request);
		if (content.equals("taskparam"))
			return this.viewTaskParam(request);
		else
			return "/error.jsp";
	}

	private String viewEssParam(HttpServletRequest request) {
		sysService.saveParam(request, EssSysparam.class,request.getParameter("cpnyId"));
		Sysparam sysparam = SysparamUtils.getSysparam(EssSysparam.class,request.getParameter("cpnyId"));
		request.setAttribute("cpnyId", request.getParameter("cpnyId"));
		request.setAttribute("sysparam", sysparam);
		return "/sy_param_ess.jsp";
	}

	private String viewHrParam(HttpServletRequest request) {
		sysService.saveParam(request, HrSysparam.class,request.getParameter("cpnyId"));
		Sysparam sysparam = SysparamUtils.getSysparam(HrSysparam.class,request.getParameter("cpnyId"));
		request.setAttribute("sysparam", sysparam);
		return "/sy_param_hr.jsp";
	}

	private String updateIfParam(HttpServletRequest request) {
		Set<String[]> set = new HashSet<String[]>();
		String emTable = request.getParameter("emTable");
		int orderNo = NumberUtils.toInt(request.getParameter("orderNo"), 1000);
		String ifTable = request.getParameter("ifTable");
		String[] emColumns = request.getParameterValues("emColumn");
		for (String emColumn : emColumns) {
			String formula = request.getParameter("formula_" + emColumn);
			if (StringUtils.isNotEmpty(formula))
				set.add(new String[]{emColumn, formula});
		}
		sysService.saveMapping(emTable, orderNo, ifTable, set);

		if (StringUtils.isEmpty(emTable)) {
			List<String> emTables = sysService.getEmTables();
			request.setAttribute("emTables", emTables);
		} else {
			request.setAttribute("emTable", emTable);
			request.setAttribute("orderNo", sysService.getEmOrder(emTable));
		}
		if (StringUtils.isEmpty(ifTable)) {
			List<String> ifTables = sysService.getIfTables(null);
			request.setAttribute("ifTables", ifTables);
		} else {
			request.setAttribute("ifTable", ifTable);
			if (StringUtils.isNotEmpty(emTable)) {
				request.setAttribute("emColumns", sysService.getEmColumns(emTable, ifTable));
				request.setAttribute("ifColumns", sysService.getIfColumns(null, ifTable));
			}
		}

		return "/sy_param_if.jsp";
	}
	
	private String viewTaskParam(HttpServletRequest request) {
		sysService.saveParam(request, TaskSysparam.class,request.getParameter("cpnyId"));
		Sysparam sysparam = SysparamUtils.getSysparam(TaskSysparam.class,request.getParameter("cpnyId"));
		request.setAttribute("cpnyId", request.getParameter("cpnyId"));
		request.setAttribute("sysparam", sysparam);
		return "/sy_param_task.jsp";
	}
}