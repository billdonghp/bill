package com.ait.pa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class PaDetailView implements Command {

	PaServices  paServices = PaServices.getInstance();
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if(!content.equals("") && content.equals("F_viewDetailPage")){			
			returnPage=this.F_viewDetailPage(request, admin);
		}else if(!content.equals("") && content.equals("B_viewDetailPage")){
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			int resultCount=0;
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			returnPage="/pa0106.jsp";
		}else if(!content.equals("") && content.equals("F_viewDetail")){
			returnPage=this.F_viewDetail(request, admin);
		}else if(!content.equals("") && content.equals("B_viewDetail")){
			returnPage=this.B_viewDetail(request, admin);
		}else if(!content.equals("") && content.equals("PaBonusDetailPage")){
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			int resultCount=0;
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			returnPage="/pa0107.jsp";
		}else if(!content.equals("") && content.equals("PaBonusDetail")){
			returnPage=this.PaBonusDetail(request, admin);
		}else if(!content.equals("") && content.equals("ImportExcelPerson_F")){
			returnPage=this.ImportExcelPerson_F(request, admin);
		}else if(!content.equals("") && content.equals("ImportExcelDepartment")){
			returnPage=this.ImportExcelDepartment(request, admin);
		}else if(!content.equals("") && content.equals("ImportExcelPerson_B")){
			returnPage=this.ImportExcelPerson_B(request, admin);
		}else{
			return "error.jsp";
		}
		return returnPage;
	}
	
	public String F_viewDetailPage(HttpServletRequest request,AdminBean admin){
		try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);		
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());	
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;		
			int resultCount=0;			
			List paTypeList=paServices.paTypeList(parameterObject);			
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("loginAdmin", admin.getAdminID());
		    request.setAttribute("paTypeList", paTypeList);
		    
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getExpressAffirm happens Exception. ", e);
		}     
			return "/pa0105.jsp";
		
	  }
	
	public String F_viewDetail(HttpServletRequest request,AdminBean admin){
		String qufen=StringUtil.checkNull(request.getParameter("qufen"));
		try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("startyearMonth", request.getParameter("startyear")+request.getParameter("startmonth"));
			parameterObject.set("endyearMonth", request.getParameter("endyear")+request.getParameter("endmonth"));
			String distinction="";			
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());	
			parameterObject.set("cpnyId", admin.getCpnyId());	
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
			int resultCount = paServices.getF_viewDetailListNumber(parameterObject);
			List viewDetailList=paServices.getF_viewDetailList(parameterObject,currentPage,pageSize);
			List basicList=paServices.basicList(parameterObject);	
			List paTypeList=paServices.paTypeList(parameterObject);	
			request.setAttribute("startyear", request.getParameter("startyear"));
			request.setAttribute("startmonth", request.getParameter("startmonth"));
			request.setAttribute("endyear", request.getParameter("endyear"));
			request.setAttribute("endmonth", request.getParameter("endmonth"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("qufen", request.getParameter("qufen"));
			request.setAttribute("patype", request.getParameter("patype"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("loginAdmin", admin.getAdminID());
		    request.setAttribute("FviewDetailList", viewDetailList);
		    request.setAttribute("basicList", basicList);	
		    request.setAttribute("paTypeList", paTypeList);
		    
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getExpressAffirm happens Exception. ", e);
		}     
		if(qufen.equals("C_12067_1330308")){
			return "/pa0107.jsp";			
		}else{
			return "/pa0105.jsp";
		}
			
		
	  }
	
	
	public String B_viewDetail(HttpServletRequest request,AdminBean admin){
		try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("startyearMonth", request.getParameter("startyear")+request.getParameter("startmonth"));
			parameterObject.set("endyearMonth", request.getParameter("endyear")+request.getParameter("endmonth"));		
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());	
			parameterObject.set("cpnyId", admin.getCpnyId());	
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
			int resultCount = paServices.getB_viewDetailListNumber(parameterObject);
			List viewDetailList=paServices.getB_viewDetailList(parameterObject,currentPage,pageSize);
			List basicList=paServices.basicListForDept(parameterObject);
			request.setAttribute("dept", request.getParameter("dept"));
			request.setAttribute("startyear", request.getParameter("startyear"));
			request.setAttribute("startmonth", request.getParameter("startmonth"));
			request.setAttribute("endyear", request.getParameter("endyear"));
			request.setAttribute("endmonth", request.getParameter("endmonth"));			
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("loginAdmin", admin.getAdminID());
		    request.setAttribute("viewDetailList", viewDetailList);		   
		    request.setAttribute("basicList", basicList);		   
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getExpressAffirm happens Exception. ", e);
		}     
			return "/pa0106.jsp";
		
	  }
	public String PaBonusDetail(HttpServletRequest request,AdminBean admin){
		try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("startyearMonth", request.getParameter("startyear")+request.getParameter("startmonth"));
			parameterObject.set("endyearMonth", request.getParameter("endyear")+request.getParameter("endmonth"));
			String distinction="";			
			distinction="'"+StringUtil.checkNull(request.getParameter("pa_factory_distinction1"))+"',"
			           +"'"+StringUtil.checkNull(request.getParameter("pa_factory_distinction2"))+"',"
			           +"'"+StringUtil.checkNull(request.getParameter("pa_factory_distinction3"))+"',";
			distinction=distinction.substring(0,distinction.length()-1);
			if(request.getParameter("pa_factory_distinction1")==null && request.getParameter("pa_factory_distinction2")==null && request.getParameter("pa_factory_distinction3")==null){
				distinction=null;
			}else if(StringUtil.checkNull(request.getParameter("pa_factory_distinction1")).equals("") && StringUtil.checkNull(request.getParameter("pa_factory_distinction2")).equals("") && StringUtil.checkNull(request.getParameter("pa_factory_distinction3")).equals("")){
				distinction=null;
			}
			
			parameterObject.set("distinction",distinction);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());	
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
			int resultCount = paServices.getPaBonusDetailListNumber(parameterObject);
			List PaBonusDetailList=paServices.getPaBonusDetailList(parameterObject,currentPage,pageSize);			
			request.setAttribute("dept", request.getParameter("dept"));
			request.setAttribute("qufen", request.getParameter("qufen"));
			request.setAttribute("bonusTypeCode", request.getParameter("bonusTypeCode"));
			request.setAttribute("bonusNo", request.getParameter("bonusNo"));
			request.setAttribute("pa_factory_distinction1", request.getParameter("pa_factory_distinction1"));
			request.setAttribute("pa_factory_distinction2", request.getParameter("pa_factory_distinction2"));
			request.setAttribute("pa_factory_distinction3", request.getParameter("pa_factory_distinction3"));
			request.setAttribute("startyear", request.getParameter("startyear"));
			request.setAttribute("startmonth", request.getParameter("startmonth"));
			request.setAttribute("endyear", request.getParameter("endyear"));
			request.setAttribute("endmonth", request.getParameter("endmonth"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("loginAdmin", admin.getAdminID());
		    request.setAttribute("PaBonusDetailList", PaBonusDetailList);		    
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getExpressAffirm happens Exception. ", e);
		}     
			return "/pa0107.jsp";
		
	  }
	
	public String ImportExcelPerson_F(HttpServletRequest request,AdminBean admin){
		try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("startyearMonth", request.getParameter("startyear")+request.getParameter("startmonth"));
			parameterObject.set("endyearMonth", request.getParameter("endyear")+request.getParameter("endmonth"));			
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("cpnyId", admin.getCpnyId());	
			int pageSize =50;			
			int currentPage = 0;
			List viewDetailList=paServices.getF_viewDetailList(parameterObject,currentPage,pageSize);
			List basicList=paServices.basicList(parameterObject);				
			
			request.setAttribute("loginAdmin", admin.getAdminID());
		    request.setAttribute("viewDetailList", viewDetailList);		
		    request.setAttribute("basicList", basicList);		  
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ImportExcelPerson happens Exception. ", e);
		}     
			return "/reports/pa_report/pa_detail_all_person_F.jsp";
		
	  }
	public String ImportExcelPerson_B(HttpServletRequest request,AdminBean admin){
		try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("startyearMonth", request.getParameter("startyear")+request.getParameter("startmonth"));
			parameterObject.set("endyearMonth", request.getParameter("endyear")+request.getParameter("endmonth"));			
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("cpnyId", admin.getCpnyId());	
			int pageSize =50;			
			int currentPage = 0;
			List viewDetailList=paServices.getF_viewDetailList(parameterObject,currentPage,pageSize);
			List basicList=paServices.basicList(parameterObject);				request.setAttribute("loginAdmin", admin.getAdminID());
		    request.setAttribute("viewDetailList", viewDetailList);		
		    request.setAttribute("basicList", basicList);		  
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ImportExcelPerson happens Exception. ", e);
		}     
			return "/reports/pa_report/pa_detail_all_person_B.jsp";
		
	  }
	public String ImportExcelDepartment(HttpServletRequest request,AdminBean admin){
		try{
			
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("startyearMonth", request.getParameter("startyear")+request.getParameter("startmonth"));
			parameterObject.set("endyearMonth", request.getParameter("endyear")+request.getParameter("endmonth"));			
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("cpnyId", admin.getCpnyId());	
			int pageSize =50;			
			int currentPage = 0;
			List viewDetailList=paServices.getB_viewDetailList(parameterObject,currentPage,pageSize);
			List basicList=paServices.basicListForDept(parameterObject);
			request.setAttribute("loginAdmin", admin.getAdminID());
		    request.setAttribute("viewDetailList", viewDetailList);		
		    request.setAttribute("basicList", basicList);		  
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ImportExcelPerson happens Exception. ", e);
		}     
			return "/reports/pa_report/pa_detail_all_department.jsp";
		
	  }

}
