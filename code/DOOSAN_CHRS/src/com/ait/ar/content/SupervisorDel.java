package com.ait.ar.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.dao.implementation.SupervisorDAOImpl;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DataAccessException;
import com.ait.web.Content;


public class SupervisorDel implements Content {
	private ArServices arServices;

	public String transfer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdminBean adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
		String str = request.getParameter("str");
		String[] s = str.split("-");
		String id = null;
		SupervisorDAOImpl dao = new SupervisorDAOImpl();
		for (int i = 0; i < s.length; i++) {
			id = s[i];
			try {
				dao.deleteSupervisor(id);
				List supervisorList = null;
				arServices = new ArServices();
				supervisorList = arServices.getAllSupervisor(adminBean.getCpnyId());
				request.setAttribute("supervisorList", supervisorList);
			} catch (DataAccessException ex) {
				Logger.getLogger(getClass()).error(ex.toString());
			}
		}
		return "/ar_maintenance_supervisor_v.jsp";
	}
}
