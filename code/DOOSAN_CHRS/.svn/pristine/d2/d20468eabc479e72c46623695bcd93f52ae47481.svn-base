package com.ait.gm.servlet;
/*
 * auth:skulls
 * Email:yangfan9336@gmail.com
 * 
 * */
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.core.config.ConfigurationException;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.gm.business.EateryServices;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.UploadFileUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;


public class EatStatistic implements Command {	
	
	SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	String adminId = "";
	String cpnyId = "";
	GMDAO gm = new GMDAO();
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		 adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		 cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		 String content =request.getParameter("content");
		 String returnpage="";
		 if(content.equals("view")){
			 returnpage=this.viewEatStatistic(request, response);
		 }else if(content.equals("add")){
			 returnpage=this.addEatStatistic(request, response);
		 }else if(content.equals("viewConfirm")){
			 returnpage=this.viewConfirm(request, response);
		 }else if(content.equals("updateFlag")){
			 returnpage=this.updateFlag(request, response); 
		 }else if(content.equals("arrangementView")){			 
			 returnpage=this.arrangementView(request, response);
		 }else if(content.equals("arrangementAddView")){
			 returnpage=this.arrangementAddView(request, response); 
		 }else if(content.equals("arrangementAdd")){
			 returnpage=this.arrangementAdd(request, response); 			   
		 }else if(content.equals("arrangementDelete")){			
			 returnpage=this.arrangementDelete(request, response);
		 }else if(content.equals("arrangementUpdateView")){
			 returnpage=this.arrangementUpdateView(request, response);
		 }else if(content.equals("arrangementUpdate")){
			 returnpage=this.arrangementUpdate(request, response);
		 }else if(content !=null && content.equals("eat_apply")){
			 this.eat_apply(request, response);
			 returnpage = "gm/gm_eat_apply.jsp";
		 }else if(content != null && content.equals("eatApply")){
			 this.eatApply(request, response);
			 this.eat_apply(request, response);
			 returnpage = "gm/gm_eat_apply.jsp";
		 }else if(content != null && content.equals("eat_confirmUpdate")){
			 this.eat_confirmUpdate(request, response);
			 this.eat_confirm(request, response);
			 returnpage = "gm/gm_eat_confirm.jsp";
		 }else if(content !=null && content.equals("eat_confirmDel")){
			 this.eat_confirmDel(request, response);
			 this.eat_confirm(request, response);
			 returnpage = "gm/gm_eat_confirm.jsp";
		 }else if(content !=null && content.equals("eat_confirmDels")){
			 this.eat_confirmDels(request, response);
			 this.eat_confirm(request, response);
			 returnpage = "gm/gm_eat_confirm.jsp";
		 }else if(content !=null && content.equals("ImportExcel")){
			 returnpage=this.ImportExcelSum(request, response);			
		 }
		 else if(content !=null && content.equals("eat_confirm")){
			 this.eat_confirm(request, response);
			 returnpage = "gm/gm_eat_confirm.jsp";
		 }
		 else if(content !=null && content.equals("ImportExcelInterface")){
			 returnpage=this.ImportExcelInterface(request, response);			
			 
		 }else{
			 returnpage="error.jsp";
		 }
		 return returnpage;
 

	}
	
	public String viewEatStatistic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";	
		SimpleMap parameterObject =new SimpleMap();		
		try {			
			String current_date = request.getParameter("selectdate");
			String eateryType=request.getParameter("eateryType");
			if(eateryType==null || eateryType.equals("")){
				eateryType="CH";
			}
			if(current_date == null || current_date.length() == 0){
				current_date = sdf.format(new GregorianCalendar().getTime());	
			}				
			parameterObject.setString("current_date", current_date);
			
			parameterObject.set("AdminId", adminId);

			parameterObject.set("cpnyId", cpnyId);
			if(cpnyId.equals("78000000")){
				parameterObject.set("lunch", "40");
				parameterObject.set("supper","42");
				parameterObject.set("dinner","43");
				parameterObject.set("break","26");	
			}else if(cpnyId.equals("63000000")){
				parameterObject.set("lunch","46");
				parameterObject.set("supper","44");
				parameterObject.set("dinner","47");
				parameterObject.set("break","45");
				parameterObject.set("line_one","line_one");
				parameterObject.set("line_two","line_two");
				parameterObject.set("line_three","line_three");
				parameterObject.set("line_four","line_four");
				parameterObject.set("line_five","line_five");
				parameterObject.set("line_six","line_six");
				parameterObject.set("line_seven","line_seven");
				parameterObject.set("line_eight","line_eight");
				parameterObject.set("line_nine","line_nine");
				parameterObject.set("line_ten","line_ten");
				parameterObject.set("line_eleven","line_eleven");
				parameterObject.set("line_twelve","line_twelve");
				parameterObject.set("line_thirteen","line_thirteen");
				parameterObject.set("line_fourteen","line_fourteen");
				parameterObject.set("line_fifteen","line_fifteen");
				parameterObject.set("line_sixteen","line_sixteen");
				parameterObject.set("line_seventeen","line_seventeen");
				parameterObject.set("line_eighteen","line_eighteen");
				parameterObject.set("line_nineteen","line_nineteen");
				parameterObject.set("line_twenty","line_twenty");
				
			}else if(cpnyId.equals("60000000")){
				parameterObject.set("lunch", "50");
				parameterObject.set("supper","48");
				parameterObject.set("dinner","51");
				parameterObject.set("break","49");
				
			}else if(cpnyId.equals("61000000")){
				parameterObject.set("lunch", "52");
				parameterObject.set("supper","53");
				parameterObject.set("dinner","55");	
				parameterObject.set("break","54");      
			}else{
				errorMsg="取出就餐类型时出错！";
			}			
			List StatisticList = new ArrayList();
			int StatisticFlag=0;
			if(eateryType.equals("CH")){
				StatisticList=gm.StatisticList(parameterObject);
				StatisticFlag=NumberUtil.parseInt(gm.StatisticFlag(parameterObject));
			}else{
				StatisticList=gm.StatisticListKO(parameterObject);
				StatisticFlag=NumberUtil.parseInt(gm.StatisticFlagKO(parameterObject));
			}
			
			if(StatisticFlag>0||StatisticList==null || StatisticList.size()==0){
				errorMsg="统计数据已经提交过了！";
			}else{
				request.setAttribute("StatisticList",StatisticList);		  
			}
			request.setAttribute("eateryType", eateryType);
			request.setAttribute("current_date", current_date) ;
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("EatStatistic by paging Exception. ", e);
		}

		return "gm/EatStatistic.jsp?menu_code=ga0706";		
		
	}
	
	public String addEatStatistic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";	
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;		
		List  insertList = new ArrayList();			
		SimpleMap basedata =new SimpleMap();	
		try {		
			String eateryType=request.getParameter("eateryType");
			if(eateryType==null || eateryType.equals("")){
				eateryType="CH";
			}
			String current_date = request.getParameter("selectdate");				
			int listsize=NumberUtil.parseInt(request.getParameter("listsize"));	
			basedata.set("current_date",current_date);
			basedata.set("cpnyId", cpnyId);
			basedata.set("adminId", adminId);	
			for(int i=0;i<listsize;i++){
				SimpleMap parameterObject =new SimpleMap();	
				
				if(cpnyId.equals("78000000")){
					parameterObject.set("type", "26");
				}else if(cpnyId.equals("63000000")){					
					parameterObject.set("type", "45");
				}else if(cpnyId.equals("60000000")){
					parameterObject.set("type","49");	
				}else if(cpnyId.equals("61000000")){
					parameterObject.set("type","54");	
				}else{
					errorMsg="取出就餐类型时出错！";
				}
				parameterObject.set("deptid", request.getParameter("deptid_"+i));
				parameterObject.set("remark", request.getParameter("remark_"+i));
				parameterObject.set("countnum", request.getParameter("breakcounts_"+i));
				parameterObject.set("current_date",current_date);
				parameterObject.set("cpnyId", cpnyId);
				parameterObject.set("adminId", adminId);				
				insertList.add(parameterObject)	;
				
			}
			for(int i=0;i<listsize;i++){
				SimpleMap parameterObject =new SimpleMap();	
				
				if(cpnyId.equals("78000000")){
					parameterObject.set("type", "40");
				}else if(cpnyId.equals("63000000")){					
					parameterObject.set("type", "46");
				}else if(cpnyId.equals("60000000")){
					parameterObject.set("type", "50");
				}else if(cpnyId.equals("61000000")){
					parameterObject.set("type", "52");
				}else{
					errorMsg="取出就餐类型时出错！";
				}
				parameterObject.set("deptid", request.getParameter("deptid_"+i));
				parameterObject.set("remark", request.getParameter("remark_"+i));
				parameterObject.set("countnum", request.getParameter("lunchcounts_"+i));
				parameterObject.set("current_date",current_date);
				parameterObject.set("cpnyId", cpnyId);
				parameterObject.set("adminId", adminId);				
				insertList.add(parameterObject)	;
				
			}
			for(int i=0;i<listsize;i++){
				SimpleMap parameterObject =new SimpleMap();	
				
				if(cpnyId.equals("78000000")){
					parameterObject.set("type", "42");
				}else if(cpnyId.equals("63000000")){					
					parameterObject.set("type", "44");
				}else if(cpnyId.equals("60000000")){
					parameterObject.set("type","48");
				}else if(cpnyId.equals("61000000")){
					parameterObject.set("type","53");
				}else{
					errorMsg="取出就餐类型时出错！";
				}
				parameterObject.set("deptid", request.getParameter("deptid_"+i));
				parameterObject.set("remark", request.getParameter("remark_"+i));
				parameterObject.set("countnum", request.getParameter("suppercounts_"+i));
				parameterObject.set("current_date",current_date);
				parameterObject.set("cpnyId", cpnyId);
				parameterObject.set("adminId", adminId);				
				insertList.add(parameterObject)	;
				
			}
			for(int i=0;i<listsize;i++){
				SimpleMap parameterObject =new SimpleMap();	
				
				if(cpnyId.equals("78000000")){
					parameterObject.set("type", "43");
				}else if(cpnyId.equals("63000000")){					
					parameterObject.set("type", "47");
				}else if(cpnyId.equals("60000000")){
					parameterObject.set("type","51");
				}else if(cpnyId.equals("61000000")){
					parameterObject.set("type","55");
				}else{
					errorMsg="取出就餐类型时出错！";
				}
				parameterObject.set("deptid", request.getParameter("deptid_"+i));
				parameterObject.set("remark", request.getParameter("remark_"+i));
				parameterObject.set("countnum", request.getParameter("dinnercounts_"+i));
				parameterObject.set("current_date",current_date);
				parameterObject.set("cpnyId", cpnyId);
				parameterObject.set("adminId", adminId);				
				insertList.add(parameterObject)	;
				
			}
			if(cpnyId.equals("63000000")){
				
				for(int i=0;i<listsize;i++){
					SimpleMap parameterObject =new SimpleMap();		
				    parameterObject.set("type", "line_one");					
					parameterObject.set("deptid", request.getParameter("deptid_"+i));
					parameterObject.set("remark", request.getParameter("remark_"+i));
					parameterObject.set("countnum", request.getParameter("line_one_"+i));
					parameterObject.set("current_date",current_date);
					parameterObject.set("cpnyId", cpnyId);
					parameterObject.set("adminId", adminId);				
					insertList.add(parameterObject)	;
					
				}
				for(int i=0;i<listsize;i++){
					SimpleMap parameterObject =new SimpleMap();	
				
					parameterObject.set("type", "line_two");				
					parameterObject.set("deptid", request.getParameter("deptid_"+i));
					parameterObject.set("remark", request.getParameter("remark_"+i));
					parameterObject.set("countnum", request.getParameter("line_two_"+i));
					parameterObject.set("current_date",current_date);
					parameterObject.set("cpnyId", cpnyId);
					parameterObject.set("adminId", adminId);				
					insertList.add(parameterObject)	;
					
				}
				for(int i=0;i<listsize;i++){
					SimpleMap parameterObject =new SimpleMap();			
					parameterObject.set("type", "line_three");					
					parameterObject.set("deptid", request.getParameter("deptid_"+i));
					parameterObject.set("remark", request.getParameter("remark_"+i));
					parameterObject.set("countnum", request.getParameter("line_three_"+i));
					parameterObject.set("current_date",current_date);
					parameterObject.set("cpnyId", cpnyId);
					parameterObject.set("adminId", adminId);				
					insertList.add(parameterObject)	;
					
				}
				for(int i=0;i<listsize;i++){
					SimpleMap parameterObject =new SimpleMap();	
			
					parameterObject.set("type", "line_four");					
					parameterObject.set("deptid", request.getParameter("deptid_"+i));
					parameterObject.set("remark", request.getParameter("remark_"+i));
					parameterObject.set("countnum", request.getParameter("line_four_"+i));
					parameterObject.set("current_date",current_date);
					parameterObject.set("cpnyId", cpnyId);
					parameterObject.set("adminId", adminId);				
					insertList.add(parameterObject)	;
					
				}
				for(int i=0;i<listsize;i++){
					SimpleMap parameterObject =new SimpleMap();	
			
					parameterObject.set("type", "line_five");					
					parameterObject.set("deptid", request.getParameter("deptid_"+i));
					parameterObject.set("remark", request.getParameter("remark_"+i));
					parameterObject.set("countnum", request.getParameter("line_five_"+i));
					parameterObject.set("current_date",current_date);
					parameterObject.set("cpnyId", cpnyId);
					parameterObject.set("adminId", adminId);				
					insertList.add(parameterObject)	;
					
				}
				
				
			}
			if(eateryType.equals("CH")){
				gm.addEatStatistic(insertList,basedata);			
			}else{
				gm.addEatStatisticKO(insertList,basedata);
			}
						
			errorMsg="计划人数统计成功！";			
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;


		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", errorMsg);
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}

		return this.viewEatStatistic(request, response);	
		
	}
	
	public String viewConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";	
		SimpleMap parameterObject =new SimpleMap();		
		String flag=request.getParameter("flag")!=null?request.getParameter("flag"):"view";
		try {			
			String current_date = request.getParameter("selectdate");
			String eateryType=request.getParameter("eateryType");
			if(eateryType==null || eateryType.equals("")){
				eateryType="CH";
			}
			if(current_date == null || current_date.length() == 0){
				current_date = sdf.format(new GregorianCalendar().getTime());	
			}				
			parameterObject.setString("current_date", current_date);
			
			parameterObject.set("AdminId", adminId);

			parameterObject.set("cpnyId", cpnyId);
			if(cpnyId.equals("78000000")){
				parameterObject.set("lunch", "40");
				parameterObject.set("supper","42");
				parameterObject.set("dinner","43");
				parameterObject.set("break","26");	
			}else if(cpnyId.equals("63000000")){
				parameterObject.set("lunch","46");
				parameterObject.set("supper","44");
				parameterObject.set("dinner","47");
				parameterObject.set("break","45");
				parameterObject.set("line_one","line_one");
				parameterObject.set("line_two","line_two");
				parameterObject.set("line_three","line_three");
				parameterObject.set("line_four","line_four");
				parameterObject.set("line_five","line_five");
				parameterObject.set("line_six","line_six");
				parameterObject.set("line_seven","line_seven");
				parameterObject.set("line_eight","line_eight");
				parameterObject.set("line_nine","line_nine");
				parameterObject.set("line_ten","line_ten");
				parameterObject.set("line_eleven","line_eleven");
				parameterObject.set("line_twelve","line_twelve");
				parameterObject.set("line_thirteen","line_thirteen");
				parameterObject.set("line_fourteen","line_fourteen");
				parameterObject.set("line_fifteen","line_fifteen");
				parameterObject.set("line_sixteen","line_sixteen");
				parameterObject.set("line_seventeen","line_seventeen");
				parameterObject.set("line_eighteen","line_eighteen");
				parameterObject.set("line_nineteen","line_nineteen");
				parameterObject.set("line_twenty","line_twenty");
				
				
			}else if(cpnyId.equals("60000000")){
				parameterObject.set("lunch", "50");
				parameterObject.set("supper","48");
				parameterObject.set("dinner","51");
				parameterObject.set("break","49");		
			}else if(cpnyId.equals("61000000")){
				parameterObject.set("lunch", "52");
				parameterObject.set("supper","53");
				parameterObject.set("dinner","55");	
				parameterObject.set("break","54");      
			}else{
				errorMsg="取出就餐类型时出错！";
			}			
			List StatisticList = new ArrayList();
			int StatisticFlag=0;
			System.out.println("eateryType+=="+eateryType);
			if(eateryType.equals("CH")){
				StatisticList=gm.StatisticConfimList(parameterObject);
				StatisticFlag=NumberUtil.parseInt(gm.StatisticFlag(parameterObject));
			}else{
				StatisticList=gm.StatisticConfimListKO(parameterObject);
				StatisticFlag=NumberUtil.parseInt(gm.StatisticFlagKO(parameterObject));
			}
			
			request.setAttribute("StatisticList",StatisticList);
			
		    if(StatisticList==null || StatisticList.size()==0 ){
		    	errorMsg="没有申请的数据!!!";
		    }
		    if (StatisticFlag>0){
		    	errorMsg="统计数据已经提交过了!!!";
			}			
		    request.setAttribute("eateryType", eateryType);
			request.setAttribute("current_date", current_date) ;
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("EatStatistic by paging Exception. ", e);
		}
		if(flag.equals("view")){
			return "/gm_eatStatistic_confirm.jsp?menu_code=ga0707";		
		}else{
			return "/reports/gm_report/gm_eatery_plan_confirmExcel.jsp";		
		}		
		
	}
	public String updateFlag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";	
		SimpleMap parameterObject =new SimpleMap();		
		try {			
			String current_date = request.getParameter("selectdate");
			String updateFlag=request.getParameter("updateFlag");
			String eateryType=request.getParameter("eateryType");
			if(eateryType==null || eateryType.equals("")){
				eateryType="CH";
			}
			if(current_date == null || current_date.length() == 0){
				current_date = sdf.format(new GregorianCalendar().getTime());	
			}	
			parameterObject.set("current_date", current_date);
			parameterObject.set("updateFlag", updateFlag);
			parameterObject.set("adminId", adminId);
			parameterObject.set("cpnyId", cpnyId);	
			if(cpnyId.equals("78000000")){
				parameterObject.set("lunch", 40);
				parameterObject.set("supper",42);
				parameterObject.set("dinner",43);
				parameterObject.set("break",26);						
			}else if(cpnyId.equals("63000000")){
				parameterObject.set("lunch","46");
				parameterObject.set("supper","44");
				parameterObject.set("dinner","47");
				parameterObject.set("break","45");
				parameterObject.set("bus",99);
				parameterObject.set("Cinese_Food","Cinese_Food");
				parameterObject.set("Korean_Food","Korean_Food");				
				parameterObject.set("line_one","line_one");
				parameterObject.set("line_two","line_two");
				parameterObject.set("line_three","line_three");
				parameterObject.set("line_four","line_four");
				parameterObject.set("line_five","line_five");
				parameterObject.set("line_six","line_six");
				parameterObject.set("line_seven","line_seven");
				parameterObject.set("line_eight","line_eight");
				parameterObject.set("line_nine","line_nine");
				parameterObject.set("line_ten","line_ten");
				parameterObject.set("line_eleven","line_eleven");
				parameterObject.set("line_twelve","line_twelve");
				parameterObject.set("line_thirteen","line_thirteen");
				parameterObject.set("line_fourteen","line_fourteen");
				parameterObject.set("line_fifteen","line_fifteen");
				parameterObject.set("line_sixteen","line_sixteen");
				parameterObject.set("line_seventeen","line_seventeen");
				parameterObject.set("line_eighteen","line_eighteen");
				parameterObject.set("line_nineteen","line_nineteen");
				parameterObject.set("line_twenty","line_twenty");
				
			}else if(cpnyId.equals("61000000")){
				parameterObject.set("lunch", "52");
				parameterObject.set("supper","53");
				parameterObject.set("dinner","55");	
				parameterObject.set("break","54");      
			}else{
				errorMsg="取出就餐类型时出错！";
			}	
			if(eateryType.equals("CH")){
				if(cpnyId.equals("63000000")){
				    gm.insertApplyDetail(parameterObject);				
				    gm.confimArrangement(parameterObject);	
				}else{
					gm.StatisticConfim(parameterObject);	
				}
			}else{
				if(cpnyId.equals("63000000")){
				    gm.insertApplyDetailKO(parameterObject);				
				    gm.confimArrangementKO(parameterObject);	
				}else{
					gm.StatisticConfimKO(parameterObject);	
				}
			}
			
			if(updateFlag.equals("1")){
				errorMsg="提交统计数据成功！";					
			}else{
				errorMsg="取消提交数据成功！";		
			}
			request.setAttribute("eateryType", eateryType);			
			request.setAttribute("current_date", current_date) ;
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;
			request.setAttribute("updateFlag", updateFlag);


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("EatStatistic by paging Exception. ", e);
		}

		return this.viewConfirm(request, response);
		
	}
	public String arrangementView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SimpleMap parameterObject = new SimpleMap();
		String errorMsg="";			
		String flag=request.getParameter("flag")!=null?request.getParameter("flag"):"view";
		try {	
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			parameterObject.set("startDate", request.getParameter("startDate"));
			parameterObject.set("endDate", request.getParameter("endDate"));
			parameterObject.set("cpnyId", cpnyId) ;
			if(cpnyId.equals("78000000")){
				parameterObject.set("lunch", 40);
				parameterObject.set("supper",42);
				parameterObject.set("dinner",43);
				parameterObject.set("break",26);						
			}else if(cpnyId.equals("63000000")){
				parameterObject.set("lunch","46");
				parameterObject.set("supper","44");
				parameterObject.set("dinner","47");
				parameterObject.set("break","45");
				parameterObject.set("bus",99);
				parameterObject.set("Cinese_Food","Cinese_Food");
				parameterObject.set("Korean_Food","Korean_Food");				
				parameterObject.set("line_one","line_one");
				parameterObject.set("line_two","line_two");
				parameterObject.set("line_three","line_three");
				parameterObject.set("line_four","line_four");
				parameterObject.set("line_five","line_five");
				parameterObject.set("line_six","line_six");
				parameterObject.set("line_seven","line_seven");
				parameterObject.set("line_eight","line_eight");
				parameterObject.set("line_nine","line_nine");
				parameterObject.set("line_ten","line_ten");
				parameterObject.set("line_eleven","line_eleven");
				parameterObject.set("line_twelve","line_twelve");
				parameterObject.set("line_thirteen","line_thirteen");
				parameterObject.set("line_fourteen","line_fourteen");
				parameterObject.set("line_fifteen","line_fifteen");
				parameterObject.set("line_sixteen","line_sixteen");
				parameterObject.set("line_seventeen","line_seventeen");
				parameterObject.set("line_eighteen","line_eighteen");
				parameterObject.set("line_nineteen","line_nineteen");
				parameterObject.set("line_twenty","line_twenty");
				
			}else if(cpnyId.equals("61000000")){
				parameterObject.set("lunch", "52");
				parameterObject.set("supper","53");
				parameterObject.set("dinner","55");	
				parameterObject.set("break","54");      
			}else{
				errorMsg="取出就餐类型时出错！";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			int resultCount=gm.arrangementViewCount(parameterObject);
			
			
			if(flag.equals("view")){
				List arrangementList=gm.arrangementViewList(parameterObject, currentPage, pageSize);
				request.setAttribute("arrangementList", arrangementList);
			}else{
				List arrangementList=gm.arrangementViewList(parameterObject, 0, 10000000);
				request.setAttribute("arrangementList", arrangementList);
			}	
			
			
			request.setAttribute("resultCount", resultCount);			
			request.setAttribute("currentPage", currentPage );
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate",  endDate);


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("arrangementAddView Exception. ", e);
		}
		if(flag.equals("view")){
			return "/gm_arrangement_view.jsp?menu_code=ga0710";		
		}else{
			return "/reports/gm_report/gm_arrangement_excel.jsp";		
		}		

			
		
	}
	public String arrangementAddView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";				
		try {	
			if(cpnyId.equals("78000000")){
				request.setAttribute("lunch", 40);
				request.setAttribute("supper",42);
				request.setAttribute("dinner",43);
				request.setAttribute("break",26);
							
			}else if(cpnyId.equals("63000000")){
				request.setAttribute("lunch",46);
				request.setAttribute("supper",44);
				request.setAttribute("dinner",47);
				request.setAttribute("break",45);
				request.setAttribute("bus",99);
				request.setAttribute("Cinese_Food","Cinese_Food");
				request.setAttribute("Korean_Food","Korean_Food");				
				request.setAttribute("line_one","line_one");
				request.setAttribute("line_two","line_two");
				request.setAttribute("line_three","line_three");
				request.setAttribute("line_four","line_four");
				request.setAttribute("line_five","line_five");	
				request.setAttribute("line_six","line_six");
				request.setAttribute("line_seven","line_seven");
				request.setAttribute("line_eight","line_eight");
				request.setAttribute("line_nine","line_nine");
				request.setAttribute("line_ten","line_ten");
				request.setAttribute("line_eleven","line_eleven");
				request.setAttribute("line_twelve","line_twelve");
				request.setAttribute("line_thirteen","line_thirteen");
				request.setAttribute("line_fourteen","line_fourteen");
				request.setAttribute("line_fifteen","line_fifteen");
				request.setAttribute("line_sixteen","line_sixteen");
				request.setAttribute("line_seventeen","line_seventeen");
				request.setAttribute("line_eighteen","line_eighteen");
				request.setAttribute("line_nineteen","line_nineteen");
				request.setAttribute("line_twenty","line_twenty");
				
			}else if(cpnyId.equals("61000000")){
				request.setAttribute("lunch", 52);
				request.setAttribute("supper",53);
				request.setAttribute("dinner",55);
				request.setAttribute("break",54);
			}else{
				errorMsg="取出就餐类型时出错！";
			}			
			
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("arrangementAddView Exception. ", e);
		}

		return "/gm_arrangement_add.jsp?menu_code=ga0710";		
		
	}
	public String arrangementAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";		
		List inserList = new ArrayList();
		List inserRecipesList = new ArrayList();
		String RECIPES_NAME="";
		String FILE_ADDRESS="";
		int RECIPES_ID=this.getSequence("GA_RECIPES_RECODE_SEQ");    
		HashMap<String, String> formParamMap = new HashMap<String, String>();
		UploadFileUtil uploadFileUtil = new UploadFileUtil();
		uploadFileUtil.setFileNo(RECIPES_ID);
		uploadFileUtil.setFilepath("/upload/files");
		formParamMap=(HashMap)uploadFileUtil.getUploadFileFormObjects(request, response);
		RECIPES_NAME=uploadFileUtil.getOldFileName();
		FILE_ADDRESS=uploadFileUtil.getSaveFileAddress();
		 //System.out.println("uploadFileUtil.getOldFileName();"+uploadFileUtil.getOldFileName());
		// System.out.println("FILE_ADDRESS"+FILE_ADDRESS);
		try {		
			Set dataSet=formParamMap.keySet();			
			Iterator dataIterator =dataSet.iterator();				
			while(dataIterator.hasNext()) {
				String Key=dataIterator.next().toString();				   
			    String DataValue=formParamMap.get(Key);
			    System.out.println("Key==="+Key+"   DataValue=="+DataValue);
			} 
			for(int i=0;i<26;i++){//DISD gm_arrangement_add 页面 编号 1--25 
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("eateryType", formParamMap.get("eateryType_"+i));
				parameterObject.set("foodType", formParamMap.get("foodType_"+i));
				parameterObject.set("diff", formParamMap.get("diff_"+i));
				parameterObject.set("num", formParamMap.get("num_"+i));
				parameterObject.set("flag", formParamMap.get("flag_"+i));
				parameterObject.set("arrangementDate", formParamMap.get("arrangementDate"));
				parameterObject.set("Remarks", formParamMap.get("Remarks"));
				parameterObject.set("adminId", adminId);
				parameterObject.set("cpnyId", cpnyId);	
				inserList.add(parameterObject);
			}
			SimpleMap CheckObject = new SimpleMap();
			CheckObject.set("arrangementDate", formParamMap.get("arrangementDate"));
			CheckObject.set("cpnyId", cpnyId);
			
			int exitsArrangementCount=gm.CheckArrangement(CheckObject);
			if(exitsArrangementCount>0){
				if(cpnyId.equals("78000000")){
					request.setAttribute("lunch", 40);
					request.setAttribute("supper",42);
					request.setAttribute("dinner",43);
					request.setAttribute("break",26);			
					
				}else if(cpnyId.equals("63000000")){
					request.setAttribute("lunch","46");
					request.setAttribute("supper","44");
					request.setAttribute("dinner","47");
					request.setAttribute("break","45");
					request.setAttribute("bus",99);
					request.setAttribute("Cinese_Food","Cinese_Food");
					request.setAttribute("Korean_Food","Korean_Food");				
					request.setAttribute("line_one","line_one");
					request.setAttribute("line_two","line_two");
					request.setAttribute("line_three","line_three");
					request.setAttribute("line_four","line_four");
					request.setAttribute("line_five","line_five");	
					request.setAttribute("line_six","line_six");
					request.setAttribute("line_seven","line_seven");
					request.setAttribute("line_eight","line_eight");
					request.setAttribute("line_nine","line_nine");
					request.setAttribute("line_ten","line_ten");
					request.setAttribute("line_eleven","line_eleven");
					request.setAttribute("line_twelve","line_twelve");
					request.setAttribute("line_thirteen","line_thirteen");
					request.setAttribute("line_fourteen","line_fourteen");
					request.setAttribute("line_fifteen","line_fifteen");
					request.setAttribute("line_sixteen","line_sixteen");
					request.setAttribute("line_seventeen","line_seventeen");
					request.setAttribute("line_eighteen","line_eighteen");
					request.setAttribute("line_nineteen","line_nineteen");
					request.setAttribute("line_twenty","line_twenty");
					
				}else if(cpnyId.equals("61000000")){
					request.setAttribute("lunch", 52);
					request.setAttribute("supper",53);
					request.setAttribute("dinner",55);
					request.setAttribute("break",54);      
				}else{
					errorMsg="取出就餐类型时出错！";
				}			
				errorMsg=request.getParameter("arrangementDate")+"数据已经提交过了！";					
				request.setAttribute("errorMsg", errorMsg) ;
				return "/gm_arrangement_add.jsp?menu_code=ga0710";
			}else{
			
		   
		    
		    gm.arrangementAdd(inserList);	
		   if(!RECIPES_NAME.equals("")){
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date  Formatstartdate = new Date();
				Date  Formatenddate = new Date();
				try {
					Formatstartdate=sdf.parse(formParamMap.get("startDate"));
					Formatenddate=sdf.parse(formParamMap.get("endDate"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double vra = DateUtil.DateDiff(Formatstartdate,Formatenddate,"DAY");
				for(int i=0;i<(vra+1);i++){
					SimpleMap parameterObject = new SimpleMap();
					Calendar today = Calendar.getInstance();
					today.setTime(Formatstartdate);
					today.add(today.DATE, i);	
					parameterObject.set("RECIPES_DATE",sdf.format(today.getTime()));
					parameterObject.set("RECIPES_ID",RECIPES_ID);
					parameterObject.set("RECIPES_NAME",RECIPES_NAME);	
					parameterObject.set("FILE_ADDRESS",FILE_ADDRESS);
					parameterObject.set("CPNY_ID",cpnyId);
					inserRecipesList.add(parameterObject);
				}
				gm.recipesLAdd(inserRecipesList);	
		   }
			errorMsg="添加数据成功！";			
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;
			
		  }


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("arrangementAddView Exception. ", e);
		}

		return this.arrangementView(request, response);
		
	}
	public String arrangementUpdateView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{					
		try {	
			String arrangementDate[]=request.getParameterValues("arrangementDate");			
			
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("arrangementDate", arrangementDate[0]);				
				parameterObject.set("adminId", adminId);
				parameterObject.set("cpnyId", cpnyId);		
			List arrangementUpdateViewList=gm.arrangementUpdateView(parameterObject);
			request.setAttribute("cpnyId", cpnyId) ;
			request.setAttribute("arrangementUpdateViewList", arrangementUpdateViewList);


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("arrangementAddView Exception. ", e);
		}

		return "/gm_arrangement_update.jsp?menu_code=ga0710";		
		
	}
	public String arrangementUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";		
		List updateList = new ArrayList();
		String RECIPES_NAME="";
		String FILE_ADDRESS="";
		HashMap<String, String> formParamMap = new HashMap<String, String>();
		List updateRecipesList =new ArrayList();
		UploadFileUtil uploadFileUtil = new UploadFileUtil();
		int RECIPES_ID=this.getSequence("GA_RECIPES_RECODE_SEQ");    
		uploadFileUtil.setFileNo(RECIPES_ID);
		uploadFileUtil.setFilepath("/upload/files");
		formParamMap=(HashMap)uploadFileUtil.getUploadFileFormObjects(request, response);
		RECIPES_NAME=uploadFileUtil.getOldFileName();
		FILE_ADDRESS=uploadFileUtil.getSaveFileAddress();
		int listSize=new Integer(formParamMap.get("listSize"));
		try {		
			for(int i=0;i<listSize;i++){
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("pkNo", formParamMap.get("pkNo_"+i));				
				parameterObject.set("num", formParamMap.get("num_"+i));
				parameterObject.set("flag", formParamMap.get("flag_"+i));				
				parameterObject.set("Remarks", formParamMap.get("Remarks"));
				parameterObject.set("adminId", adminId);
				parameterObject.set("cpnyId", cpnyId);	
				updateList.add(parameterObject);
			}			
			gm.arrangementUpdate(updateList);
			if(!RECIPES_NAME.equals("")){
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date  Formatstartdate = new Date();
				Date  Formatenddate = new Date();
				try {
					Formatstartdate=sdf.parse(formParamMap.get("startDate"));
					Formatenddate=sdf.parse(formParamMap.get("endDate"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double vra = DateUtil.DateDiff(Formatstartdate,Formatenddate,"DAY");
				for(int i=0;i<(vra+1);i++){
					SimpleMap parameterObject = new SimpleMap();
					Calendar today = Calendar.getInstance();
					today.setTime(Formatstartdate);
					today.add(today.DATE, i);	
					parameterObject.set("RECIPES_DATE",sdf.format(today.getTime()));
					parameterObject.set("RECIPES_ID",RECIPES_ID);
					parameterObject.set("RECIPES_NAME",RECIPES_NAME);	
					parameterObject.set("FILE_ADDRESS",FILE_ADDRESS);
					parameterObject.set("CPNY_ID",cpnyId);
					updateRecipesList.add(parameterObject);
				}
				gm.recipesLAdd(updateRecipesList);	
			}
			errorMsg="修改数据成功！";			
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;
		


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("arrangementAddView Exception. ", e);
		}

		return this.arrangementView(request, response);
		
	}
	public String arrangementDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String errorMsg="";		
		List deleteList = new ArrayList();		
		try {				
			 String arrangementDate[]=request.getParameterValues("arrangementDate");			
			for(int i=0;i<arrangementDate.length;i++){
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("arrangementDate", arrangementDate[i]);				
				parameterObject.set("adminId", adminId);
				parameterObject.set("cpnyId", cpnyId);	
				deleteList.add(parameterObject);
			}	
			gm.arrangementDelete(deleteList);	
			
			errorMsg="数据删除成功！";			
			request.setAttribute("errorMsg", errorMsg) ;
			request.setAttribute("cpnyId", cpnyId) ;
	


		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("arrangementAddView Exception. ", e);
		}

		return this.arrangementView(request, response);
		
	}
	/*得到序列号*/
	public int getSequence(String seqName) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT " + seqName + ".NEXTVAL FROM DUAL";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getInt(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得序列号失败", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}
	public void eat_apply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SimpleMap parameterObject = new SimpleMap();
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", adminId);
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		UserConfiguration config = UserConfiguration.getInstance("/system.properties");
		int pageSize;
		EateryServices es = new EateryServices();
		try {
			pageSize = config.getInt("page.style6.pagesize");
			parameterObject.set("cpnyId", cpny_id);
			List eatType = es.getEatType(parameterObject);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
		List listBus = gm.eatListBus(parameterObject);
		int pageGroupSize = config.getInt("page.style1.pagegroupsize");
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
		String current_date = request.getParameter("selectdate")!=null?request.getParameter("selectdate"):sdf.format(today.getTime());
		parameterObject.set("selectdate", current_date);
		int resultCount = gm.eatApplySize(parameterObject);
		List list = gm.eatApplyList(parameterObject, currentPage, pageSize);
		int confirmFlag=gm.confirmFlagToday(parameterObject);
		request.setAttribute("resultCount", resultCount);	
		request.setAttribute("confirmFlag", confirmFlag);	
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage );
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("eatType", eatType);
		request.setAttribute("listBus", listBus);
		request.setAttribute("deptID", request.getParameter("deptID"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("current_date", current_date);
		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	public void eatApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SimpleMap parameterObject = new SimpleMap();
		parameterObject = ObjectBindUtil.getData(request);
		String cnt = request.getParameter("chNo");
		String[] chno = cnt.split(",");
		for(int i=0;i<chno.length;i++){
			String eatType[] = request.getParameterValues("eatType"+chno[i]);
			String EATTYPE = "";
			for(int y=0;y<eatType.length;y++){
				EATTYPE += eatType[y]+",";
			}
			String EATTYPE1 = "EATTYPE1"+i;
			parameterObject.set(EATTYPE1, EATTYPE);
			parameterObject.set("adminId", adminId);
			
		}
		gm.addEatApply(parameterObject);
		request.setAttribute("error", "申请成功");
		
	}
	public void eat_confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SimpleMap parameterObject = new SimpleMap();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", adminId);
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		UserConfiguration config = UserConfiguration.getInstance("/system.properties");
		int pageSize = 0;
		EateryServices es = new EateryServices();
		try {
			pageSize = config.getInt("page.style6.pagesize");
			parameterObject.set("cpnyId", cpny_id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
		int pageGroupSize = config.getInt("page.style1.pagegroupsize");
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request
					.getParameter("currentPage"));
		String current_date = request.getParameter("selectdate")!=null?request.getParameter("selectdate"):sdf.format(today.getTime());
		parameterObject.set("selectdate", current_date);
		String qryType = parameterObject.getString("qryType")!=null?parameterObject.getString("qryType"):"0";
		String deptID = parameterObject.getString("deptID")!=null?parameterObject.getString("deptID"):"";
		String key = parameterObject.getString("key")!=null?parameterObject.getString("key"):"";
		parameterObject.set("qryType", qryType);
		parameterObject.set("deptID", deptID);
		parameterObject.set("key", key);
		int resultCount = gm.eatConfirmSize(parameterObject);
		List list = gm.eatConfirmList(parameterObject, currentPage, pageSize);
		
		GaAffirm gaAffirm = new GaAffirm();
		SimpleMap dataMap = new SimpleMap();
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				SimpleMap parameterObject1 =  new SimpleMap();
				dataMap=(SimpleMap)list.get(i);	
				dataMap.set("eatType",gaAffirm.getEateryTypeName(dataMap.getString("EATTYPE").substring(0,dataMap.getString("EATTYPE").length()-1), admin));
			}
		}
		
		request.setAttribute("resultCount", resultCount);	
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage );
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("deptID", deptID);
		request.setAttribute("key", key);
		request.setAttribute("current_date", current_date);
		request.setAttribute("qryType", qryType);
		
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void eat_confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
		SimpleMap parameterObject = new SimpleMap();
		parameterObject = ObjectBindUtil.getData(request);
		String applyno[] = request.getParameterValues("applyno");		
		gm.confirmUpdate(applyno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eat_confirmDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
		SimpleMap parameterObject = new SimpleMap();
		parameterObject = ObjectBindUtil.getData(request);
		
		gm.eat_confirmDel(parameterObject);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eat_confirmDels(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
		List parameterList = new ArrayList(10) ;
		
		//parameterObject = ObjectBindUtil.getData(request);
		
		String applyNos[] = request.getParameterValues("applyno") ;
		
		for (int i = 0 ; i < applyNos.length ; ++i){
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("chNo", applyNos[i]) ;
			parameterList.add(parameterObject) ;
		}
		
		gm.eat_confirmDel(parameterList);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String ImportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SimpleMap parameterObject = new SimpleMap();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", adminId);
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		UserConfiguration config = UserConfiguration.getInstance("/system.properties");
		EateryServices es = new EateryServices();
		try {
			parameterObject.set("cpnyId", cpny_id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			String current_date = request.getParameter("selectdate")!=null?request.getParameter("selectdate"):sdf.format(today.getTime());
			parameterObject.set("selectdate", current_date);
			String qryType = parameterObject.getString("qryType")!=null?parameterObject.getString("qryType"):"0";
			String deptID = parameterObject.getString("deptID")!=null?parameterObject.getString("deptID"):"";
			String key = parameterObject.getString("key")!=null?parameterObject.getString("key"):"";
			parameterObject.set("qryType", qryType);
			parameterObject.set("deptID", deptID);
			parameterObject.set("key", key);
			List list = gm.eatConfirmList1(parameterObject);
			
			GaAffirm gaAffirm = new GaAffirm();
			SimpleMap dataMap = new SimpleMap();
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				SimpleMap parameterObject1 =  new SimpleMap();
				dataMap=(SimpleMap)list.get(i);	
				dataMap.set("eatType",gaAffirm.getEateryTypeName(dataMap.getString("EATTYPE").substring(0,dataMap.getString("EATTYPE").length()-1), admin));
			}
		}
		
		request.setAttribute("list", list);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "reports/gm_report/gm_eat_Excel.jsp";
	}
	public String ImportExcelSum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SimpleMap parameterObject = new SimpleMap();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		parameterObject.set("adminId", admin.getAdminID());
		try {
			if(cpnyId.equals("78000000")){
				parameterObject.set("lunch", 40);
				parameterObject.set("supper",42);
				parameterObject.set("dinner",43);
				parameterObject.set("break",26);			
				
			}else if(cpnyId.equals("63000000")){
				parameterObject.set("lunch","46");
				parameterObject.set("supper","44");
				parameterObject.set("dinner","47");
				parameterObject.set("break","45");	
				parameterObject.set("wu","999");
				parameterObject.set("line_one","line_one");
				parameterObject.set("line_two","line_two");
				parameterObject.set("line_three","line_three");
				parameterObject.set("line_four","line_four");
				parameterObject.set("line_five","line_five");
				parameterObject.set("line_six","line_six");
				parameterObject.set("line_seven","line_seven");
				parameterObject.set("line_eight","line_eight");
				parameterObject.set("line_nine","line_nine");
				parameterObject.set("line_ten","line_ten");
				parameterObject.set("line_eleven","line_eleven");
				parameterObject.set("line_twelve","line_twelve");
				parameterObject.set("line_thirteen","line_thirteen");
				parameterObject.set("line_fourteen","line_fourteen");
				parameterObject.set("line_fifteen","line_fifteen");
				parameterObject.set("line_sixteen","line_sixteen");
				parameterObject.set("line_seventeen","line_seventeen");
				parameterObject.set("line_eighteen","line_eighteen");
				parameterObject.set("line_nineteen","line_nineteen");
				parameterObject.set("line_twenty","line_twenty");
				
				
			}else if(cpnyId.equals("61000000")){
				parameterObject.set("lunch", "52");
				parameterObject.set("supper","53");
				parameterObject.set("dinner","55");	
				parameterObject.set("break","54");      
			}
			parameterObject.set("cpnyId", admin.getCpnyId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			String current_date = request.getParameter("selectdate")!=null?request.getParameter("selectdate"):sdf.format(today.getTime());
			parameterObject.set("selectdate", current_date);
			List list = gm.eatConfirmListSumReport(parameterObject);		
		    //request.setAttribute("list", list);
			
			 SimpleMap columns = new SimpleMap();				
			 columns.setString("部门", "FOURDEPTNAME");				
			 columns.setString("课组", "DEPTNAME");
			 columns.setString("姓名", "CHINESENAME");
			 columns.setString("职号", "EMPIDNO");		 
			 columns.setString("就餐日期", "COUNT_DATE");
			 columns.setString("餐次", "COUNTM_TYPE");
			 columns.setString("就餐类型", "FOODTYPE");
			 columns.setString("就餐人数", "COUNT_NUM");
			 columns.setString("牟平17点", "LINE_SEVEN");
			 columns.setString("牟平20点", "LINE_ONE");
			 columns.setString("牟平夜班", "LINE_SIX");
			 columns.setString("开发区17点", "LINE_TWO");
			 columns.setString("开发区20点", "LINE_EIGHT");
			 columns.setString("开发区夜班","LINE_TWELVE");
			 columns.setString("福山17点", "LINE_THREE");
			 columns.setString("福山20点", "LINE_NINE");
			 columns.setString("福山夜班","LINE_THIRTEEN");
			 columns.setString("莱山17点", "LINE_FOUR");
			 columns.setString("莱山20点", "LINE_TEN");
			 columns.setString("莱山夜班","LINE_FOURTEEN");
			 columns.setString("芝罘17点", "LINE_FIVE");
			 columns.setString("芝罘20点", "LINE_ELEVEN");
			 columns.setString("芝罘夜班","LINE_FIFTEEN");
			 columns.setString("开发区21点","LINE_SIXTEEN");
			 columns.setString("牟平21点","LINE_SEVENTEEN");
			 columns.setString("福山21点","LINE_EIGHTEEN");
			 columns.setString("芝罘21点","LINE_NINETEEN");
			 columns.setString("莱山21点","LINE_TWENTY");
			 
			 
			 
			 
			 
			 
              //	 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("EMPIDNO", ReportConstant.CELL_TYPE_TEXT);
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("eatConfirmListSum.xls");
			paramBean.setSheetname("eatConfirmListSum");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setInLineHeadContent("DISD 就餐和班车人员统计汇总表");
			paramBean.setInLineHeadMergeSize(columns.size());
			ReportUtil.makeReport(request, paramBean); 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/inc/commonReport.jsp";
		//return "reports/gm_report/gm_eatery_apply_sum.jsp";
	}
	public String ImportExcelInterface(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		SimpleMap parameterObject = new SimpleMap();
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		parameterObject.set("adminId", admin.getAdminID());
		try {
			if(cpnyId.equals("78000000")){
				parameterObject.set("lunch", 40);
				parameterObject.set("supper",42);
				parameterObject.set("dinner",43);
				parameterObject.set("break",26);			
				
			}else if(cpnyId.equals("63000000")){
				parameterObject.set("lunch","46");
				parameterObject.set("supper","44");
				parameterObject.set("dinner","47");
				parameterObject.set("break","45");			
				parameterObject.set("line_one","line_one");
				parameterObject.set("line_two","line_two");
				parameterObject.set("line_three","line_three");
				parameterObject.set("line_four","line_four");
				parameterObject.set("line_five","line_five");
				parameterObject.set("line_six","line_six");
				parameterObject.set("line_seven","line_seven");
				parameterObject.set("line_eight","line_eight");
				parameterObject.set("line_nine","line_nine");
				parameterObject.set("line_ten","line_ten");
				parameterObject.set("line_eleven","line_eleven");
				parameterObject.set("line_twelve","line_twelve");
				parameterObject.set("line_thirteen","line_thirteen");
				parameterObject.set("line_fourteen","line_fourteen");
				parameterObject.set("line_fifteen","line_fifteen");	
				parameterObject.set("line_sixteen","line_sixteen");
				parameterObject.set("line_seventeen","line_seventeen");
				parameterObject.set("line_eighteen","line_eighteen");
				parameterObject.set("line_nineteen","line_nineteen");
				parameterObject.set("line_twenty","line_twenty");
				
			}else if(cpnyId.equals("61000000")){
				parameterObject.set("lunch", "52");
				parameterObject.set("supper","53");
				parameterObject.set("dinner","55");	
				parameterObject.set("break","54");      
			}
			parameterObject.set("cpnyId", admin.getCpnyId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			String current_date = request.getParameter("selectdate")!=null?request.getParameter("selectdate"):sdf.format(today.getTime());
			parameterObject.set("selectdate", current_date);
			List list = gm.ImportExcelInterface(parameterObject);		
		    SimpleMap columns = new SimpleMap();				
			 columns.setString("部门", "DEPTNAME");				
			 columns.setString("姓名", "CHINESENAME");
			 columns.setString("工号", "EMPID");
			 columns.setString("就餐日期", "COUNT_DATE");		 
			 columns.setString("餐别", "COUNTM_TYPE");
              //	 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("eatery_apply_interface.xls");
			paramBean.setSheetname("eatery_apply_interface");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setInLineHeadContent("");
			paramBean.setInLineHeadMergeSize(columns.size());
			ReportUtil.makeReport(request, paramBean); 
       
       
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "/inc/commonReport.jsp";
	}
}
