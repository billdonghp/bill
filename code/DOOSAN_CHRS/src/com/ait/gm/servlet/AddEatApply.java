package com.ait.gm.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.bean.GmBean;
import com.ait.gm.dao.GMDAO;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.DateUtil;

public class AddEatApply extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageSource messageSource;
		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject;
		SimpleMap map=null;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		int gmtypeSize = NumberUtil.parseInt(request.getParameter("gmtypeSize"));
		int empNameSize = NumberUtil.parseInt(request.getParameter("empNameSize"));
		
		try {
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("AdminID", admin.getAdminID());
			//validate empid and plan_month
			int Eatery_num = NumberUtil.parseInt(request.getParameter("Eatery_num"));
			
			String deptid = request.getParameter("deptlist");
			for(int i=0;i<Eatery_num;i++){
		        //num[i] = request.getParameter("gm_num"+i);
				
				String Forecast_num = request.getParameter("Forecast_gm_num"+i);
				
				String num = request.getParameter("gm_num"+i);
				String gmtypeID = request.getParameter("gmtypeID"+i);
				
				parameterObject.set("adminid",admin.getAdminID());
				parameterObject.set("Forecast_num", Forecast_num);
				parameterObject.set("num", num);
				parameterObject.set("GmTypeid", gmtypeID);
				parameterObject.set("deptid", deptid);
				parameterObject.set("applyno", DateUtil.formatDate(java.util.Calendar.getInstance().getTime(), "yyyyMMdd")+admin.getDeptID());
//				取得餐别表里的FROM  TO   时间
				List gmFromToDate = gm.gmFromToDate(parameterObject);
				int gmFromToDateSize = gmFromToDate.size();
				String FromDate="";//参别表里的  开始时间
					for(int j=0;j<gmFromToDateSize; j++){
						map=(SimpleMap)gmFromToDate.get(j);
						FromDate=map.getString("GM_FROM_DATE");
					}
					String applyyear=DateUtil.formatDate(java.util.Calendar.getInstance().getTime(), "yyyy-MM-dd").replace("-", "");
					String applyday=DateUtil.formatDate(java.util.Calendar.getInstance().getTime(), "HH:mm").replace(":", "");
					int fromdate=NumberUtil.parseInt(FromDate.replace(":", ""));//参别表里的  开始时间   拆分后的
					int applydate =NumberUtil.parseInt(applyyear);
					
					int seq = gm.getSeq(parameterObject);
					parameterObject.set("seq", seq);
						
					if(fromdate>NumberUtil.parseInt(applyday)){
						int eatdate = applydate;
						parameterObject.set("eatdate", eatdate);
						
						gm.insertEatApply(parameterObject);
					}
					if(fromdate<NumberUtil.parseInt(applyday)){
						int eatdate = applydate+1;
						parameterObject.set("eatdate", eatdate);
						
						gm.insertEatApply(parameterObject);
					}
			}
/*//			得到刚刚申请的编号
			int soonApplyId = gm.getSoonApplyId(parameterObject);
			
			parameterObject.set("soonApplyId", soonApplyId);*/
			String gmids = "";
			String empnames = "";
			for(int k=0 ; k<gmtypeSize ; k++){
				gmids = request.getParameter("gmid"+k);
				empnames = request.getParameter("empId"+k);
				
				String empname[] = empnames.split(",");
				String en = "";
				for(int i=0 ; i<empname.length ; i++){
					en = empname[i];
					parameterObject.set("empnames", en);
					parameterObject.set("gmids", gmids);
					
					SimpleMap sm = null;
					SimpleMap sm1 = null;
					List geteateryTypeNo = gm.geteateryTypeNo(parameterObject);
					for(int h=0 ; h<geteateryTypeNo.size(); h++){
						sm = (SimpleMap) geteateryTypeNo.get(h);
						String eateryTypeNo = sm.getString("EAT_APPLY_EATERY");
						
						if(gmids.equals(eateryTypeNo.toString())){
							parameterObject.set("eateryTypeNo", eateryTypeNo);
							List eatApplyId = gm.getEatApplyId(parameterObject);
							for(int g=0 ; g<eatApplyId.size() ; g++){
								sm1 = (SimpleMap) eatApplyId.get(g);
								String eatapplyid = sm1.getString("EAT_APPLY_ID");
								
								parameterObject.set("eatapplyid", eatapplyid);

								if(empnames.equals("")){
									continue;
								}
								
								int result = gm.selectSame(parameterObject);
								
								if(result > 0){
									continue;
								}
								if(!gmids.equals("") && gmids!=null && !empnames.equals("") && empnames!=null){
									gm.insertEatApplyEmp(parameterObject);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insertEateryException Gm Exception. ", e);
		}
		request.setAttribute("url","/gmControlServlet?operation=eatApply&menu_code=" + parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
}