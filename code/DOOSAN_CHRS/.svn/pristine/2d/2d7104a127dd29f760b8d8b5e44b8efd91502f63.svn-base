package com.ait.gm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;

public class TypenandshiftUpdate extends ArAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject = null;
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		
		String shift_id = (String) request.getAttribute("shift_id");
		int gm_id = Integer.parseInt(request.getParameter("gm_id"));
		try {

			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("shift_id", shift_id);
			parameterObject.set("GM_ID", gm_id);
			
			// retrieve attendance record list
			
			SimpleMap gmType = (SimpleMap)gm.gmTypeUpdate(parameterObject);
			SimpleMap timeGmType = (SimpleMap)gm.timeGmType(parameterObject);
			
			List shift = gm.shift(parameterObject);		
			List gmShiftList = gm.shiftlook(parameterObject);
			
			SimpleMap shiftMap = new SimpleMap();
			SimpleMap gmShiftMap = new SimpleMap() ;
			for(int i = 0 ; i < shift.size(); i++)
			{
				shiftMap = (SimpleMap)shift.get(i) ;
				
				for(int k = 0 ; k < gmShiftList.size() ; k ++)
				{
					gmShiftMap = (SimpleMap)gmShiftList.get(k) ;
					if(shiftMap.get("SHIFT_NO").equals(gmShiftMap.get("SHIFT_NO"))) 
					{
						shiftMap.setString("checkFalg", "checked='checked'") ;
					}
				}
			}
			
			
			
			if(Integer.parseInt(gm.timeGmTypeCount(parameterObject).toString())!=0){
				
				String gmfromdate = "";
				String gmtodate = "";
				int if_selected = 0;
				if(timeGmType == null || timeGmType.getString("START_TIME") == null || timeGmType.getString("END_TIME") == null || timeGmType.getString("START_TIME").equals("") || timeGmType.getString("END_TIME").equals("")){
					gmfromdate = "00:00";
					gmtodate = "00:00";
					if_selected = 1;
				}
				else{
					gmfromdate = timeGmType.getString("START_TIME");
					gmtodate = timeGmType.getString("END_TIME");
				}
				
				
				String gmfd[] = gmfromdate.split(":");
				String gmtd[] = gmtodate.split(":");
				
				List listMM=DateUtil.getTimeMMList();
				List listHH=DateUtil.getTimePerHourList();
				
				request.setAttribute("xs", gmfd[0]);
				request.setAttribute("fz", gmfd[1]);
				request.setAttribute("xse", gmtd[0]);
				request.setAttribute("fze", gmtd[1]);
				request.setAttribute("listMM",listMM);
				request.setAttribute("listHH",listHH);
				request.setAttribute("if_selected",if_selected);
			}else{
				List listMM=DateUtil.getTimeMMList();
				List listHH=DateUtil.getTimePerHourList();
				request.setAttribute("listMM",listMM);
				request.setAttribute("listHH",listHH);
			}
			gmType.set("gmShifts",shift) ;
			
			request.setAttribute("gmType", gmType);
			request.setAttribute("shift", shift);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("typeandshift by No Exception. ",
					e);
		}
		
		return "gm/typeandshiftUpdate.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
}