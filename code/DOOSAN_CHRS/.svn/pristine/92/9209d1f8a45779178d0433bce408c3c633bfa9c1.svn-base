package com.ait.ga.cmd.smock;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-23
 * 
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrieveDateForUpdateSmockRelationCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		Object result = null;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			SimpleMap map = new SimpleMap();
			map.setString("cpnyId", admin.getCpnyId());
			map.setString("seqNo", parameterObject.getString("seqNo"));
			// retrieve present by sequence No
			List list = services.selectSmockRelationList(map);
			//List smockNameList = services.getSmockNameList();

			if(list.size() > 0)
				result = list.get(0);

			request.setAttribute("result", result);
			//request.setAttribute("smockNameList", smockNameList);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve data for update smock relation Exception. ", e);
		}
		return "/ga_modify_smockRelation.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

}
