package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsMaster;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class GetevsSetupcountCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		if(admin == null) 
			admin = new AdminBean();
		SimpleMap parameterObject = new SimpleMap();
		String craft = StringUtils.trimToEmpty(request.getParameter("craft"));
		parameterObject.put("craft", craft);
		CommonDAO commonDAO = new CommonDAO();
		StringBuffer htmlPosition = new StringBuffer();
        String count0="1";
        String count1="1";
        String count2="1";
        String count3="1";
        String count4="1";
		try {
			JSONObject object = new JSONObject();
			EvsMaster evsMasters=new EvsMaster();
			List lEvsEmp=null;
			lEvsEmp=evsMasters.getEvsPersonDaochu("RS");//大的 
			String adminid = admin.getAdminID();
			if(adminid!=null&&(adminid.equals("1464498")||adminid.equals("1464086")||adminid.equals("9999901")
					||adminid.equals("1466731")||adminid.equals("12884220")||adminid.equals("4529845"))){
				parameterObject.put("adminid", "");
			}else{
				parameterObject.put("adminid", adminid);
			}
				List<SimpleMap> setupcount = commonDAO.retrieveSetupcount(parameterObject);

				
				if(setupcount.size()>0){
					for (SimpleMap p : setupcount) {
						
						count0 = p.getString("COUNT0");
						count1 = p.getString("COUNT1");
						count2 = p.getString("COUNT2");
						count3 = p.getString("COUNT3");
						count4 = p.getString("COUNT4");
											
					}				
				}
				
			
			
			
			object.put("count0", count0);
			object.put("count1", count1);
			object.put("count2", count2);
			object.put("count3", count3);
			object.put("count4", count4);
			
			
			
	    	response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");

			PrintWriter out = response.getWriter();
			String str = object.toString();
			
	    	out.println(str);
	        out.close();
		} catch (Exception e) {

			throw new GlRuntimeException("Get employee division Exception.", e);
		}
		return "";
	}
}
