
package com.ait.sys.cmd.essleave;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.service.SysService;

public class RetrievedataEssLeaveParamForUpdateCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		SysService syservice = SysService.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List list=null;

		try {

			// bind parameter
			parameterObject.setString("PARAM_NO", request.getParameter("PARAM_NO")) ;
			parameterObject.setString("cpnyId", request.getParameter("cpnyId"));
			// retrieve essLeaveParam by PARAM_NO 
			Object result = syservice.getEssLeaveParam(parameterObject) ;
			list=syservice.EssLeaveParam(parameterObject);

			request.setAttribute("result", result);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve essLeaveParam by PARAM_NO Exception. ", e);
		}		
		request.setAttribute("leaveParam", list);
		return "/sy_modify_essleave.jsp?menu_code=" + request.getParameter("menu_code") 
			 + "&currentPage" + request.getParameter("currentPage") + "&APPLY_TYPE" + request.getParameter("APPLY_TYPE") ;
	}

}
