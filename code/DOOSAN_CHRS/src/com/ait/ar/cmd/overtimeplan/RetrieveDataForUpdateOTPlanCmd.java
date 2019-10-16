
package com.ait.ar.cmd.overtimeplan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;

public class RetrieveDataForUpdateOTPlanCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			// retrieve OT Plan by No 
			Object result = services.retrieveDataForUpdateOTPlan(parameterObject);
			String startMonth = ((SimpleMap)result).getString("START_MONTH");
			String endMonth = ((SimpleMap)result).getString("END_MONTH");
			((SimpleMap)result).setString("startYear",startMonth.substring(0,4));
			((SimpleMap)result).setString("endYear",endMonth.substring(0,4));
			((SimpleMap)result).setString("startMonth",startMonth.substring(4,6));
			((SimpleMap)result).setString("endMonth",endMonth.substring(4,6));
		
			request.setAttribute("result", result);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve OT Plan by No Exception. ", e);
		}
		

		return "/ar_modify_otplan.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

}
