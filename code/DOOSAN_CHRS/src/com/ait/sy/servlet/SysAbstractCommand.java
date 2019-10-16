/*
 * @(#)SysAbstractCommand.java 1.0 2007-9-25 下午04:16:03
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sy.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.web.Command;                


public abstract class SysAbstractCommand  implements Command{

	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
	
	public void putPost(HttpServletRequest request) throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();

			List list = services.retrievePostList();

			request.setAttribute("post_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put post tree to request Exception.", e);
		}
	}
	
	public void putPostGrade(HttpServletRequest request) throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();

			List list = services.retrievePostGradeList();

			request.setAttribute("post_grade_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put post grade to request Exception.", e);
		}
	}
	
	
	
	public void putGradeLevel(HttpServletRequest request) throws GlRuntimeException {

		try {

			HrmServices services = HrmServices.getInstance();

			List list = services.retrieveGradeLevelList();

			request.setAttribute("post_grade_level_list", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put post grade level to request Exception.", e);
		}
	}


}

