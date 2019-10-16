package com.ait.kpa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.pa.cmd.ccplan.CCPlan;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;
import com.ibm.icu.text.SimpleDateFormat;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-04-01
 * 
 */
public class PayPlanCommand implements Command {
	
	private PaServices  paServices = null;
	
	 private static ServiceLocator services;

	public PayPlanCommand(){
		paServices = PaServices.getInstance();
		
		 try {
	            services = ServiceLocator.getInstance();
	        } catch (ServiceLocatorException ex) {
	        }
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if(!content.equals("") && content.equals("pa_payPlan_01")){			
			returnPage = "/pa_payPlan_01.jsp";
		}else if(!content.equals("") && content.equals("pa_payPlan_02")){
			request.setAttribute("seqChar","z");
			returnPage="/pa_payPlan_02.jsp";
		}else if(!content.equals("") && content.equals("pa_payPlan_03")){
			ArrayList FRateList = new ArrayList();
			ArrayList BRateList = new ArrayList();
			for(int i=1;i<13;i++){
		    SimpleMap FRateMap = new SimpleMap();
		    SimpleMap BRateMap = new SimpleMap();
		    FRateMap.set("FRate"+i,"0");	
		    BRateMap.set("BRate"+i,"0");
		    FRateList.add(FRateMap);
		    BRateList.add(BRateMap);
			}			
			request.setAttribute("paVersion",this.getVesionNote("PA"));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG"));
			request.setAttribute("FRateList", FRateList);
			request.setAttribute("BRateList", BRateList);			
			returnPage="/pa_payPlan_03.jsp";
		}else if(!content.equals("") && content.equals("pa_payPlan_04")){
			request.setAttribute("paVersion",this.getVesionNote("PA"));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG"));
			returnPage="/pa_payPlan_04.jsp";
		}else if(!content.equals("") && content.equals("payPlan01Serche")){
			returnPage = this.payPlan01Serche(request);
		}else if(!content.equals("") && content.equals("payPlan01Save")){			
			String flag=this.payPlan01Save(request,admin);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa_payPlan_01.jsp?menu_code=pa0401");
			returnPage="/inc/alertMessage.jsp";
		}else if(!content.equals("") && content.equals("payPlan02Serche")){
			returnPage = this.payPlan02Serche(request,admin);
		}else if(!content.equals("") && content.equals("payPlan02Save")){
			String flag=this.payPlan02Save(request,admin);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa_payPlan_02.jsp?menu_code=pa0402");
			returnPage="/inc/alertMessage.jsp";			
		}else if(!content.equals("") && content.equals("payPlan03Serche")){	
			request.setAttribute("paVersion",this.getVesionNote("PA"));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG"));
			returnPage = this.payPlan03Serche(request);
		}else if(!content.equals("") && content.equals("payPlan03Save")){			
			returnPage=this.payPlan03Save(request,admin);			
		}else if(!content.equals("") && content.equals("payPlan04Serche")){
			request.setAttribute("paVersion",this.getVesionNote("PA"));
			request.setAttribute("peVersion", this.getVesionNote("PE_RG"));
			returnPage=this.payPlan04Serche(request);
		}else if(!content.equals("") && content.equals("payPlan04Save")){
			String flag=this.payPlan04Save(request,admin);
			request.setAttribute("alert", flag);
			request.setAttribute("url", "/pa/pa_payPlan_04.jsp?menu_code=pa0404");
			returnPage="/inc/alertMessage.jsp";				
		}else if(!content.equals("") && content.equals("pa_payPlan_05")){
			request.setAttribute("paVersion",this.getVesionNote("PA"));
			request.setAttribute("pbVersion",this.getVesionNote("PB"));
			request.setAttribute("pcVersion",this.getVesionNote("PC"));
			request.setAttribute("peVersion",this.getVesionNote("PE_RG"));
			
			returnPage="/pa_payPlan_05.jsp";	
		}else if(!content.equals("") && content.equals("synchronizePayPlan")){
			int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
			String maxPayMonthStr ="";
			if(maxPayMonth<10){
				maxPayMonthStr="0"+maxPayMonth;
			}else{
				maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
			}
			Date d = new Date();
			SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");
			int extisPersonPlan =this.ExitsPersonPlan(request);
			if(extisPersonPlan==1){
				this.GenerationPayPlanCC(request,admin);
				request.setAttribute("alert", "生成人件费计划成功！");
				request.setAttribute("url", "/pa/pa_payPlan_05.jsp?menu_code=pa0405");
				returnPage="/inc/alertMessage.jsp";		
				
			}else{
				CCPlan cc = new CCPlan();
				int flag=cc.productsTempPersonPlanTable(request.getParameter("planyear"),(timeFormatter.format(d)+maxPayMonthStr));	
				if(flag==1){
					this.GenerationPayPlanCC(request,admin);					
					request.setAttribute("alert", "生成人件费计划成功！");
					request.setAttribute("url", "/pa/pa_payPlan_05.jsp?menu_code=pa0405");
					returnPage="/inc/alertMessage.jsp";	
				}else{
					request.setAttribute("alert", "同步"+request.getParameter("planyear")+"计划年度"+(timeFormatter.format(d)+maxPayMonthStr)+"基准月的人员计划失败！");
					request.setAttribute("url", "/pa/pa_payPlan_05.jsp?menu_code=pa0405");
					returnPage="/inc/alertMessage.jsp";					
				}				
			}		
			
		}else{
			return "error.jsp";
		}
		return returnPage;
	}
	
	public String payPlan01Serche(HttpServletRequest request){
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="";
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";
		int rateMonth=new Integer(request.getParameter("thisYearRoseMonth"));
		int startMonth=new Integer(request.getParameter("startMonth"));
		int endMonth=new Integer(request.getParameter("endMonth"));
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		double rate=0;		
		if(maxPayMonth!=rateMonth){
			rate=(new Double(request.getParameter("thisYearRate"))/100);
		}
			
		if(timeFormatter.format(d).equals(request.getParameter("planyear"))){
		sql += " select  dep.deptname,code.code_name,grade.post_grade_name,'基本工资' as paytype " ;      
        for(int s=1;s<rateMonth;s++){
		sql += " ,trunc((";
		for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.month"+i+")+" ;
		}
	    sql =sql.substring(0,sql.length()-1);
		sql +=")/ "
			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) month"+s;
        }
        for(int s=rateMonth;s<13;s++){
    		sql += " ,trunc(((trunc((";
    		for(int i=startMonth;i<(endMonth+1);i++){
    			sql +="sum(temp.month"+i+")+" ;
    		}
    	    sql =sql.substring(0,sql.length()-1);
    		sql +=")/ "
	    			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end),2))*(1+"+rate+")),2) month"+s;
        }
		sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+ "        hr.join_type_code, "
			+ "         hr.post_grade_code, "
			+ "        hr.empid ";
		   for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , nvl(pa"+i+".base_pay, 0) as month"+i+" ";
			}
			
			sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000' and t.deptid not in('78000000','780T0000')) hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql +="  full join "
			+ "  (select t.empid, t.base_pay "
			+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
			}

			sql +="  full join (select t.empid, t.base_pay "
			+ "             from pa_history t "
			+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "    )temp ,  "
			+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.countm"+i+")+";
			}				
			sql=(sql.substring(0,(sql.length()-1)));
			sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+"         hr.join_type_code, "
			+ "        hr.post_grade_code,"
			+ "        hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , case when pa"+i+".base_pay<>0 then count(hr.empid) else 0 end as countm"+i+" ";
			}

			sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000') hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql += "   full join "
			+ "    (select t.empid, t.base_pay "
			+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
		
		   }
			sql +=" full join (select t.empid, t.base_pay "
			+ "                      from pa_history t "
			+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=",pa"+i+".base_pay";
			 }
			sql += "   ) temp  "
			+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber, sy_code code,hr_post_grade grade,hr_department dep "
			+ "   where sumpnumber.deptid=temp.deptid "
			+ "  and temp.deptid=dep.deptid(+) "
			+ "   and sumpnumber.join_type_code=temp.join_type_code "
			+ "   and sumpnumber.post_grade_code=temp.post_grade_code "
			+ "   and temp.join_type_code=code.code_id(+) "
			+ "   and temp.post_grade_code=grade.post_grade_id(+) "
			+ "   group by  dep.deptname,code.code_name,grade.post_grade_name,sumpnumber.sump  order by dep.deptname ";
		}else{
			double rate1=(new Double(request.getParameter("nextYearRate"))/100);
			sql += " select  dep.deptname,code.code_name,grade.post_grade_name,'基本工资' as paytype " ;
	        for(int s=1;s<new Integer(request.getParameter("nextYearRoseMonth"));s++){
			sql += " ,trunc((";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.month"+i+")+" ;
			}
		    sql =sql.substring(0,sql.length()-1);
			sql +=")/ "
				+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end )*(1+"+rate+"),2) month"+s;
	        }
	        for(int s=new Integer(request.getParameter("nextYearRoseMonth"));s<13;s++){
	    		sql += " ,trunc((((";
	    		for(int i=startMonth;i<(endMonth+1);i++){
	    			sql +="sum(temp.month"+i+")+" ;
	    		}
	    	    sql =sql.substring(0,sql.length()-1);
	    		sql +=")/ "
		    			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end))*("+((1+rate)*(1+rate1))+")),2) month"+s;
	        }
			sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+ "        hr.join_type_code, "
				+ "         hr.post_grade_code, "
				+ "        hr.empid ";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , nvl(pa"+i+".base_pay, 0) as month"+i+" ";
				}
				
				sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000' and t.deptid not in('78000000','780T0000')) hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql +="  full join "
				+ "  (select t.empid, t.base_pay "
				+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
				}

				sql +="  full join (select t.empid, t.base_pay "
				+ "             from pa_history t "
				+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "    )temp ,  "
				+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.countm"+i+")+";
				}				
				sql=(sql.substring(0,(sql.length()-1)));
				sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+"         hr.join_type_code, "
				+ "        hr.post_grade_code,"
				+ "        hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , case when pa"+i+".base_pay<>0 then count(hr.empid) else 0 end as countm"+i+" ";
				}

				sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql += "   full join "
				+ "    (select t.empid, t.base_pay "
				+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
			
			   }
				sql +=" full join (select t.empid, t.base_pay "
				+ "                      from pa_history t "
				+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=",pa"+i+".base_pay";
				 }
				sql += "   ) temp  "
				+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber, sy_code code,hr_post_grade grade,hr_department dep "
				+ "   where sumpnumber.deptid=temp.deptid "
				+ "  and temp.deptid=dep.deptid(+) "
				+ "   and sumpnumber.join_type_code=temp.join_type_code "
				+ "   and sumpnumber.post_grade_code=temp.post_grade_code "
				+ "   and temp.join_type_code=code.code_id(+) "
				+ "   and temp.post_grade_code=grade.post_grade_id(+) "
				+ "   group by  dep.deptname,code.code_name,grade.post_grade_name,sumpnumber.sump  order by dep.deptname ";
			
			
		}
			System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			List list = new ArrayList();
			System.out.println((new Integer(request.getParameter("thisYearRate"))/100));
			System.out.println(new Double(15)/100);
			try {
			
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				while(result.next()){
					SimpleMap sMap = new SimpleMap();
					sMap.set("deptname", result.getString("deptname"));
					sMap.set("code_name", result.getString("code_name"));
					sMap.set("post_grade_name", result.getString("post_grade_name"));
					sMap.set("paytype", result.getString("paytype"));					
					sMap.set("month1", result.getDouble("month1"));
					sMap.set("month2", result.getDouble("month2"));
					sMap.set("month3", result.getDouble("month3"));
					sMap.set("month4", result.getDouble("month4"));
					sMap.set("month5", result.getDouble("month5"));
					sMap.set("month6", result.getDouble("month6"));
					sMap.set("month7", result.getDouble("month7"));
					sMap.set("month8", result.getDouble("month8"));
					sMap.set("month9", result.getDouble("month9"));
					sMap.set("month10",result.getDouble("month10"));
					sMap.set("month11",result.getDouble("month11"));
					sMap.set("month12",result.getDouble("month12"));
					list.add(sMap);
				}
				
			
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}			
		
		request.setAttribute("thisYearRoseMonth",request.getParameter("thisYearRoseMonth"));
		request.setAttribute("nextYearRoseMonth",request.getParameter("nextYearRoseMonth"));
		request.setAttribute("planyear",request.getParameter("planyear"));
		request.setAttribute("endMonth",request.getParameter("endMonth"));
		request.setAttribute("startMonth",request.getParameter("startMonth"));
		request.setAttribute("thisYearRate",request.getParameter("thisYearRate"));
		request.setAttribute("nextYearRate",request.getParameter("nextYearRate"));
		request.setAttribute("payPlan01Serche", list);
			return "/pa_payPlan_01.jsp";
		
	  }
	public String payPlan01Save(HttpServletRequest request,AdminBean admin){
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="";
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";
		int rateMonth=new Integer(request.getParameter("thisYearRoseMonth"));
		int startMonth=new Integer(request.getParameter("startMonth"));
		int endMonth=new Integer(request.getParameter("endMonth"));
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		double rate=0;		
		if(maxPayMonth!=rateMonth){
			rate=(new Double(request.getParameter("thisYearRate"))/100);
		}
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}	
		if(timeFormatter.format(d).equals(request.getParameter("planyear"))){
		sql += " select dep.deptid, dep.deptname, temp.join_type_code, temp.post_grade_code, 'PA' as paytype " ;
        for(int s=1;s<rateMonth;s++){
		sql += " ,trunc((";
		for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.month"+i+")+" ;
		}
	    sql =sql.substring(0,sql.length()-1);
		sql +=")/ "
			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) month"+s;
        }
        for(int s=rateMonth;s<13;s++){
    		sql += " ,trunc(((trunc((";
    		for(int i=startMonth;i<(endMonth+1);i++){
    			sql +="sum(temp.month"+i+")+" ;
    		}
    	    sql =sql.substring(0,sql.length()-1);
    		sql +=")/ "
	    			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end),2))*(1+"+rate+")),2) month"+s;
        }
		sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+ "        hr.join_type_code, "
			+ "         hr.post_grade_code, "
			+ "        hr.empid ";
		  for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , nvl(pa"+i+".base_pay, 0) as month"+i+" ";
			}
			
			sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000' and t.deptid not in('78000000','780T0000')) hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql +="  full join "
			+ "  (select t.empid, t.base_pay "
			+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
			}

			sql +="  full join (select t.empid, t.base_pay "
			+ "             from pa_history t "
			+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "    )temp ,  "
			+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.countm"+i+")+";
			}				
			sql=(sql.substring(0,(sql.length()-1)));
			sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+"         hr.join_type_code, "
			+ "        hr.post_grade_code,"
			+ "        hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , case when pa"+i+".base_pay<>0 then count(hr.empid) else 0 end as countm"+i+" ";
			}

			sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000') hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql += "   full join "
			+ "    (select t.empid, t.base_pay "
			+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
		
		   }
			sql +=" full join (select t.empid, t.base_pay "
			+ "                      from pa_history t "
			+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=",pa"+i+".base_pay";
			 }
			sql += "   ) temp  "
			+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber, hr_department dep "
			+ "   where sumpnumber.deptid=temp.deptid "
			+ "  and temp.deptid=dep.deptid(+) "
			+ "   and sumpnumber.join_type_code=temp.join_type_code "
			+ "   and sumpnumber.post_grade_code=temp.post_grade_code "			
			+ "   group by dep.deptid,dep.deptname,temp.join_type_code, temp.post_grade_code,sumpnumber.sump  order by dep.deptname ";
		}else{
			double rate1=(new Double(request.getParameter("nextYearRate"))/100);
			sql += " select dep.deptid, dep.deptname, temp.join_type_code, temp.post_grade_code, 'PA' as paytype " ;
	        for(int s=1;s<new Integer(request.getParameter("nextYearRoseMonth"));s++){
			sql += " ,trunc((";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.month"+i+")+" ;
			}
		    sql =sql.substring(0,sql.length()-1);
			sql +=")/ "
				+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end )*(1+"+rate+"),2) month"+s;
	        }
	        for(int s=new Integer(request.getParameter("nextYearRoseMonth"));s<13;s++){
	    		sql += " ,trunc((((";
	    		for(int i=startMonth;i<(endMonth+1);i++){
	    			sql +="sum(temp.month"+i+")+" ;
	    		}
	    	    sql =sql.substring(0,sql.length()-1);
	    		sql +=")/ "
		    			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end))*("+((1+rate)*(1+rate1))+")),2) month"+s;
	        }
			sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+ "        hr.join_type_code, "
				+ "         hr.post_grade_code, "
				+ "        hr.empid ";
			   for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , nvl(pa"+i+".base_pay, 0) as month"+i+" ";
				}
				
				sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000' and t.deptid not in('78000000','780T0000')) hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql +="  full join "
				+ "  (select t.empid, t.base_pay "
				+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
				}

				sql +="  full join (select t.empid, t.base_pay "
				+ "             from pa_history t "
				+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "    )temp ,  "
				+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.countm"+i+")+";
				}				
				sql=(sql.substring(0,(sql.length()-1)));
				sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+"         hr.join_type_code, "
				+ "        hr.post_grade_code,"
				+ "        hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , case when pa"+i+".base_pay<>0 then count(hr.empid) else 0 end as countm"+i+" ";
				}

				sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql += "   full join "
				+ "    (select t.empid, t.base_pay "
				+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
			
			   }
				sql +=" full join (select t.empid, t.base_pay "
				+ "                      from pa_history t "
				+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=",pa"+i+".base_pay";
				 }
				sql += "   ) temp  "
				+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber,hr_department dep "
				+ "   where sumpnumber.deptid=temp.deptid "
				+ "  and temp.deptid=dep.deptid(+) "
				+ "   and sumpnumber.join_type_code=temp.join_type_code "
				+ "   and sumpnumber.post_grade_code=temp.post_grade_code "				
				+ "   group by dep.deptid,dep.deptname,temp.join_type_code, temp.post_grade_code,sumpnumber.sump order by dep.deptname ";
			
			
		}	
			Logger.getLogger(getClass()).debug(sql);		
			String nextseq=this.getSequence(request.getParameter("planyear"),timeFormatter.format(d)+maxPayMonthStr,"PA");
			try {
			
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				while(result.next()){
				PreparedStatement pstmt1=conn.prepareStatement(" insert into py_pay_plan_detail (PAYPLAN_DETAIL_ID,COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,PAY_MONTH_04,"
							             +" PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID,VERSIONNOTE) values (py_pay_plan_detail_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)");					
				pstmt1.setString(1,"1200");
				pstmt1.setString(2,request.getParameter("planyear"));				
				pstmt1.setString(3,timeFormatter.format(d)+maxPayMonthStr);							
				pstmt1.setString(4,nextseq);
				pstmt1.setString(5,result.getString("deptid"));
				pstmt1.setString(6,result.getString("deptname"));
				pstmt1.setString(7,result.getString("join_type_code"));
				pstmt1.setString(8,result.getString("post_grade_code"));
				pstmt1.setString(9,result.getString("paytype"));
				pstmt1.setDouble(10,result.getDouble("month1"));
				pstmt1.setDouble(11,result.getDouble("month2"));
				pstmt1.setDouble(12,result.getDouble("month3"));
				pstmt1.setDouble(13,result.getDouble("month4"));
				pstmt1.setDouble(14,result.getDouble("month5"));
				pstmt1.setDouble(15,result.getDouble("month6"));
				pstmt1.setDouble(16,result.getDouble("month7"));
				pstmt1.setDouble(17,result.getDouble("month8"));
				pstmt1.setDouble(18,result.getDouble("month9"));
				pstmt1.setDouble(19,result.getDouble("month10"));
				pstmt1.setDouble(20,result.getDouble("month11"));
				pstmt1.setDouble(21,result.getDouble("month12"));
				pstmt1.setDouble(22,(result.getDouble("month1")+result.getDouble("month2")+result.getDouble("month3")+result.getDouble("month4")+result.getDouble("month5")+result.getDouble("month6")+result.getDouble("month7")+result.getDouble("month8")+result.getDouble("month9")+result.getDouble("month10")+result.getDouble("month11")+result.getDouble("month12")));
				pstmt1.setString(23,admin.getAdminID());					
				pstmt1.setString(24,"1200");
				pstmt1.setString(25,request.getParameter("versionNote"));
				System.out.println("ddddddddddddddd");
				pstmt1.executeUpdate();
				}
				
			
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}			
			return request.getParameter("planyear")+"年基本工资推算成功！";
		
	  }
	
	public String payPlan02Serche(HttpServletRequest request,AdminBean admin){
		int startMonth=new Integer(request.getParameter("startMonth"));
		int endMonth=new Integer(request.getParameter("endMonth"));				
		Connection conn3 = ConnBean.getConn();			
		PreparedStatement pstmt3 = null;
		try {
			pstmt3 = conn3.prepareStatement("delete from T_PY_PAY_PLAN_DETAIL ");
			pstmt3.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			SqlUtil.close(pstmt3, conn3);
		}
		
		Date d = new Date();
		List list = new ArrayList();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";		
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}
		String[]payParm={"RESIDENTIAL_GRANTS","DUTIES_ALLOWANCE","SPECIAL_GRANTS","DUTY_SUBSIDIES","REGIONAL_GRANTS","OTHER_COMPENSATION"};
		String[]payParmName={"PE_RG","PE_DA","PE_SG","PE_DS","PE_RS","PE_OC"};	
		if(timeFormatter.format(d).equals(request.getParameter("planyear"))){
	    for(int p=0;p<payParm.length;p++){
	    	String sql="";
		sql += " select dep.deptid, dep.deptname, temp.join_type_code, temp.post_grade_code,'"+payParmName[p]+"' as paytype " ;
		for(int s=1;s<13;s++){
    		sql += " ,trunc((((";
    		for(int i=startMonth;i<(endMonth+1);i++){
    			sql +="sum(temp.month"+i+")+" ;
    		}
    	    sql =sql.substring(0,sql.length()-1);
    		sql +=")/ "
	    			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end))),2) month"+s;
        }
		sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+ "        hr.join_type_code, "
			+ "         hr.post_grade_code, "
			+ "        hr.empid ";
		 for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , nvl(pa"+i+"."+payParm[p]+", 0) as month"+i+" ";
			}
			
			sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.deptid not in('78000000','780T0000')) hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql +="  full join "
			+ "  (select t.empid, t."+payParm[p]+" "
			+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
			}

			sql +="  full join (select t.empid, t."+payParm[p]+" "
			+ "             from pa_history t "
			+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "    )temp ,  "
			+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.countm"+i+")+";
			}				
			sql=(sql.substring(0,(sql.length()-1)));
			sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+"         hr.join_type_code, "
			+ "        hr.post_grade_code,"
			+ "        hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , case when pa"+i+"."+payParm[p]+"<>0 then count(hr.empid) else 0 end as countm"+i+" ";
			}

			sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000' and t.deptid not in('78000000','780T0000')) hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql += "   full join "
			+ "    (select t.empid, t."+payParm[p]+" "
			+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
		
		   }
			sql +=" full join (select t.empid, t."+payParm[p]+" "
			+ "                      from pa_history t "
			+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=",pa"+i+"."+payParm[p]+" ";
			 }
			sql += "   ) temp  "
			+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber,hr_department dep "
			+ "   where sumpnumber.deptid=temp.deptid "
			+ "   and temp.deptid=dep.deptid(+) "
			+ "   and sumpnumber.join_type_code=temp.join_type_code "
			+ "   and sumpnumber.post_grade_code=temp.post_grade_code "			
			+ "   group by  dep.deptid, dep.deptname, temp.join_type_code, temp.post_grade_code,sumpnumber.sump ";

			System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			
			try {
				ResultSet result = null;				
				Connection conn = ConnBean.getConn();			
				PreparedStatement pstmt = null;
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				String nextseq=this.getSequence(request.getParameter("planyear"),timeFormatter.format(d)+maxPayMonthStr,"PE_RG");
				while(result.next()){
				PreparedStatement pstmt1=conn.prepareStatement(" insert into t_py_pay_plan_detail (PAYPLAN_DETAIL_ID,COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,PAY_MONTH_04,"
							             +" PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID) values (py_pay_plan_detail_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)");					
				pstmt1.setString(1,"1200");
				pstmt1.setString(2,request.getParameter("planyear"));						
				pstmt1.setString(3,timeFormatter.format(d)+maxPayMonthStr);				
				pstmt1.setString(4,nextseq);
				pstmt1.setString(5,result.getString("deptid"));
				pstmt1.setString(6,result.getString("deptname"));
				pstmt1.setString(7,result.getString("join_type_code"));
				pstmt1.setString(8,result.getString("post_grade_code"));
				pstmt1.setString(9,result.getString("paytype"));
				pstmt1.setDouble(10,result.getDouble("month1"));
				pstmt1.setDouble(11,result.getDouble("month2"));
				pstmt1.setDouble(12,result.getDouble("month3"));
				pstmt1.setDouble(13,result.getDouble("month4"));
				pstmt1.setDouble(14,result.getDouble("month5"));
				pstmt1.setDouble(15,result.getDouble("month6"));
				pstmt1.setDouble(16,result.getDouble("month7"));
				pstmt1.setDouble(17,result.getDouble("month8"));
				pstmt1.setDouble(18,result.getDouble("month9"));
				pstmt1.setDouble(19,result.getDouble("month10"));
				pstmt1.setDouble(20,result.getDouble("month11"));
				pstmt1.setDouble(21,result.getDouble("month12"));
				pstmt1.setDouble(22,(result.getDouble("month1")+result.getDouble("month2")+result.getDouble("month3")+result.getDouble("month4")+result.getDouble("month5")+result.getDouble("month6")+result.getDouble("month7")+result.getDouble("month8")+result.getDouble("month9")+result.getDouble("month10")+result.getDouble("month11")+result.getDouble("month12")));
				pstmt1.setString(23,admin.getAdminID());					
				pstmt1.setString(24,"1200");				
				pstmt1.executeUpdate();
				SqlUtil.close(pstmt1);
			}
				SqlUtil.close(result,pstmt,conn);
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			}		
	      }
			
		}else{
			for(int p=0;p<payParm.length;p++){		
				String sql="";
			sql += " select dep.deptid, dep.deptname, temp.join_type_code, temp.post_grade_code,'"+payParmName[p]+"' as paytype " ;
			for(int s=1;s<13;s++){
	    		sql += " ,trunc((((";
	    		for(int i=startMonth;i<(endMonth+1);i++){
	    			sql +="sum(temp.month"+i+")+" ;
	    		}
	    	    sql =sql.substring(0,sql.length()-1);
	    		sql +=")/ "
		    			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end))),2) month"+s;
	        }
			sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+ "        hr.join_type_code, "
				+ "         hr.post_grade_code, "
				+ "        hr.empid ";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , nvl(pa"+i+"."+payParm[p]+", 0) as month"+i+" ";
				}
				
				sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000' and t.deptid not in('78000000','780T0000')) hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql +="  full join "
				+ "  (select t.empid, t."+payParm[p]+" "
				+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
				}

				sql +="  full join (select t.empid, t."+payParm[p]+" "
				+ "             from pa_history t "
				+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "    )temp ,  "
				+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.countm"+i+")+";
				}				
				sql=(sql.substring(0,(sql.length()-1)));
				sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+"         hr.join_type_code, "
				+ "        hr.post_grade_code,"
				+ "        hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , case when pa"+i+"."+payParm[p]+"<>0 then count(hr.empid) else 0 end as countm"+i+" ";
				}

				sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000' and t.deptid not in('78000000','780T0000')) hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql += "   full join "
				+ "    (select t.empid, t."+payParm[p]+" "
				+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') ) pa"+i+" on hr.empid=pa"+i+".empid ";
			
			   }
				sql +=" full join (select t.empid, t."+payParm[p]+" "
				+ "                      from pa_history t "
				+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=",pa"+i+"."+payParm[p]+" ";
				 }
				sql += "   ) temp  "
				+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber,hr_department dep "
				+ "   where sumpnumber.deptid=temp.deptid "
				+ "  and temp.deptid=dep.deptid(+) "
				+ "   and sumpnumber.join_type_code=temp.join_type_code "
				+ "   and sumpnumber.post_grade_code=temp.post_grade_code "				
				+ "   group by  dep.deptid, dep.deptname, temp.join_type_code, temp.post_grade_code,sumpnumber.sump ";
			
			System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			
			try {
				ResultSet result = null;				
				Connection conn = ConnBean.getConn();			
				PreparedStatement pstmt = null;
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				String nextseq=this.getSequence(request.getParameter("planyear"),timeFormatter.format(d)+maxPayMonthStr,"PE_RG");
				while(result.next()){
				PreparedStatement pstmt1=conn.prepareStatement(" insert into t_py_pay_plan_detail (PAYPLAN_DETAIL_ID,COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,PAY_MONTH_04,"
							             +" PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID) values (py_pay_plan_detail_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)");					
				pstmt1.setString(1,"1200");
				pstmt1.setString(2,request.getParameter("planyear"));						
				pstmt1.setString(3,timeFormatter.format(d)+maxPayMonthStr);				
				pstmt1.setString(4,nextseq);
				pstmt1.setString(5,result.getString("deptid"));
				pstmt1.setString(6,result.getString("deptname"));
				pstmt1.setString(7,result.getString("join_type_code"));
				pstmt1.setString(8,result.getString("post_grade_code"));
				pstmt1.setString(9,result.getString("paytype"));
				pstmt1.setDouble(10,result.getDouble("month1"));
				pstmt1.setDouble(11,result.getDouble("month2"));
				pstmt1.setDouble(12,result.getDouble("month3"));
				pstmt1.setDouble(13,result.getDouble("month4"));
				pstmt1.setDouble(14,result.getDouble("month5"));
				pstmt1.setDouble(15,result.getDouble("month6"));
				pstmt1.setDouble(16,result.getDouble("month7"));
				pstmt1.setDouble(17,result.getDouble("month8"));
				pstmt1.setDouble(18,result.getDouble("month9"));
				pstmt1.setDouble(19,result.getDouble("month10"));
				pstmt1.setDouble(20,result.getDouble("month11"));
				pstmt1.setDouble(21,result.getDouble("month12"));
				pstmt1.setDouble(22,(result.getDouble("month1")+result.getDouble("month2")+result.getDouble("month3")+result.getDouble("month4")+result.getDouble("month5")+result.getDouble("month6")+result.getDouble("month7")+result.getDouble("month8")+result.getDouble("month9")+result.getDouble("month10")+result.getDouble("month11")+result.getDouble("month12")));
				pstmt1.setString(23,admin.getAdminID());					
				pstmt1.setString(24,"1200");				
				pstmt1.executeUpdate();
				SqlUtil.close(pstmt1);
			}
				SqlUtil.close(result,pstmt,conn);
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			}		
		}
		}
		ResultSet result4 = null;				
		Connection conn4 = ConnBean.getConn();			
		PreparedStatement pstmt4 = null;
		try {
			pstmt4 = conn4.prepareStatement("select t.*, sy.code_name,grade.post_grade_name,"+
										      "   case "+
										        "    when t.pay_type ='PE_RG' then '住宅补助'"+
										        "    when t.pay_type ='PE_DA' then '职务津贴'"+
										        "    when t.pay_type ='PE_SG' then '特殊补助'"+
										        "    when t.pay_type ='PE_DS' then '值班补助'"+
										        "    when t.pay_type ='PE_RS' then '地区补助'"+
										       "     when t.pay_type ='PE_OC' then '其他补助'"+
										      "    end as paytype "+
										      "     from t_py_pay_plan_detail t, hr_post_grade grade, sy_code sy"+
										      "    where t.auth_group_type = sy.code_id(+)"+
										      "     and t.pos_type = grade.post_grade_id(+)"+
										      "    order by t.bg_gu, t.auth_group_type, t.pos_type,t.pay_type");
			result4=pstmt4.executeQuery();
			while(result4.next()){
				SimpleMap sMap = new SimpleMap();
				sMap.set("deptname", result4.getString("bg_nm"));
				sMap.set("code_name", result4.getString("code_name"));
				sMap.set("post_grade_name", result4.getString("post_grade_name"));
				sMap.set("paytype", result4.getString("paytype"));					
				sMap.set("month1", result4.getDouble("pay_month_01"));
				sMap.set("month2", result4.getDouble("pay_month_02"));
				sMap.set("month3", result4.getDouble("pay_month_03"));
				sMap.set("month4", result4.getDouble("pay_month_04"));
				sMap.set("month5", result4.getDouble("pay_month_05"));
				sMap.set("month6", result4.getDouble("pay_month_06"));
				sMap.set("month7", result4.getDouble("pay_month_07"));
				sMap.set("month8", result4.getDouble("pay_month_08"));
				sMap.set("month9", result4.getDouble("pay_month_09"));
				sMap.set("month10",result4.getDouble("pay_month_10"));
				sMap.set("month11",result4.getDouble("pay_month_11"));
				sMap.set("month12",result4.getDouble("pay_month_12"));
				list.add(sMap);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			SqlUtil.close(result4, pstmt4, conn4);
		}
		request.setAttribute("planyear",request.getParameter("planyear"));
		request.setAttribute("endMonth",request.getParameter("endMonth"));
		request.setAttribute("startMonth",request.getParameter("startMonth"));		
		request.setAttribute("payPlan02Serche", list);
			return "/pa_payPlan_02.jsp";			
		
	  }
	public String payPlan02Save(HttpServletRequest request,AdminBean admin){
		int startMonth=new Integer(request.getParameter("startMonth"));
		int endMonth=new Integer(request.getParameter("endMonth"));
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="insert into py_pay_plan_detail select t.*,'"+request.getParameter("versionNote")+"' VERSIONNOTE from t_py_pay_plan_detail t ";
		   try{
			   pstmt=conn.prepareStatement(sql);
			   pstmt.executeUpdate();
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}			
		return request.getParameter("planyear")+"补助推算成功！";
		
	  }
	public String payPlan03Serche(HttpServletRequest request){
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="";
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";	
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}
		sql += " select pa.bg_nm,sy.code_name, grade.post_grade_name,'奖金' as paytype " ;
        for(int s=1;s<13;s++){
		sql +=", (case  when pa.auth_group_type<>'C_12009_1330065'  then pa.pay_month_"+s+"  else (pa.pay_month_"+s+"+PE_RG.pay_month_"+s+") end) base"+s+","
	          +" (case  when pa.auth_group_type<>'C_12009_1330065'  then "+StringUtil.checkNull(request.getParameter("FRate"+s),"0")+"  else "+StringUtil.checkNull(request.getParameter("BRate"+s),"0")+" end) rate"+s+","
	          +" round((case  when pa.auth_group_type<>'C_12009_1330065'  then pa.pay_month_"+s+"*"+new Double(StringUtil.checkNull(request.getParameter("FRate"+s),"0"))+"  else (pa.pay_month_"+s+"+PE_RG.pay_month_"+s+")*"+new Double(StringUtil.checkNull(request.getParameter("BRate"+s),"0"))+" end),2) bonus"+s;
		}
        sql +=" from (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
        	+" from py_pay_plan_detail t where t.pay_type = 'PA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("paVersion").split(",")[0]+"') pa, " 
        	+" (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
        	+" from py_pay_plan_detail t where t.pay_type = 'PE_RG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_RG, " 
        	+" sy_code sy,hr_post_grade grade    "     
        	+" where pa.bg_gu = PE_RG.bg_gu " 
        	+" and pa.bg_nm = PE_RG.bg_nm " 
        	+" and pa.auth_group_type = PE_RG.auth_group_type " 
        	+" and pa.pos_type = PE_RG.pos_type " 
        	+" and sy.code_id(+)=pa.auth_group_type " 
        	+" and grade.post_grade_id(+)=pa.pos_type order by pa.bg_nm, pa.auth_group_type, pa.pos_type " ;	  
		
			System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			List list = new ArrayList();			
			try {
			
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();				
				while(result.next()){
					SimpleMap sMap = new SimpleMap();
					sMap.set("deptname", result.getString("bg_nm"));
					sMap.set("code_name", result.getString("code_name"));
					sMap.set("post_grade_name", result.getString("post_grade_name"));
					sMap.set("paytype", result.getString("paytype"));
					for(int i=1;i<13;i++){
						sMap.set("base"+i, result.getDouble("base"+i));
						sMap.set("rate"+i, result.getDouble("rate"+i));
						sMap.set("bonus"+i, result.getDouble("bonus"+i));
					}					
					list.add(sMap);
				}
				
			
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}			
		request.setAttribute("planyear",request.getParameter("planyear"));
		request.setAttribute("endMonth",request.getParameter("endMonth"));
		request.setAttribute("startMonth",request.getParameter("startMonth"));
		ArrayList FRateList = new ArrayList();
		ArrayList BRateList = new ArrayList();
		for(int i=1;i<13;i++){
	    SimpleMap FRateMap = new SimpleMap();
	    SimpleMap BRateMap = new SimpleMap();
	    FRateMap.set("FRate"+i,request.getParameter("FRate"+i));	
	    BRateMap.set("BRate"+i,request.getParameter("BRate"+i));
	    FRateList.add(FRateMap);
	    BRateList.add(BRateMap);
		}
		request.setAttribute("paVersionStr", request.getParameter("paVersion").split(",")[0]);
		request.setAttribute("peVersionStr", request.getParameter("peVersion").split(",")[0]);
		request.setAttribute("FRateList", FRateList);
		request.setAttribute("BRateList", BRateList);
		request.setAttribute("payPlan03Serche", list);
			return "/pa_payPlan_03.jsp";
		
	  }
	public String payPlan03Save(HttpServletRequest request,AdminBean admin){
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="";
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");	
		int startMonth=new Integer(request.getParameter("startMonth"));
		int endMonth=new Integer(request.getParameter("endMonth"));
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";		
		sql += " select pa.bg_gu,pa.bg_nm,pa.auth_group_type, pa.pos_type,'PB' as paytype " ;
        for(int s=1;s<13;s++){
		sql +=", round((case  when pa.auth_group_type<>'C_12009_1330065'  then pa.pay_month_"+s+"*"+new Double(StringUtil.checkNull(request.getParameter("FRate"+s),"0"))+"  else (pa.pay_month_"+s+"+PE_RG.pay_month_"+s+")*"+new Double(StringUtil.checkNull(request.getParameter("BRate"+s),"0"))+" end),2) bonus"+s;
		}
        sql +=" from (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
        	+" from py_pay_plan_detail t where t.pay_type = 'PA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("paVersion").split(",")[0]+"') pa, " 
        	+" (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
        	+" from py_pay_plan_detail t where t.pay_type = 'PE_RG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_RG, " 
        	+" sy_code sy,hr_post_grade grade    "     
        	+" where pa.bg_gu = PE_RG.bg_gu " 
        	+" and pa.bg_nm = PE_RG.bg_nm " 
        	+" and pa.auth_group_type = PE_RG.auth_group_type " 
        	+" and pa.pos_type = PE_RG.pos_type " 
        	+" and sy.code_id(+)=pa.auth_group_type " 
        	+" and grade.post_grade_id(+)=pa.pos_type " ;	  
		
			System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			List list = new ArrayList();			
			try {
			
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				String nextseq=this.getSequence(request.getParameter("planyear"),timeFormatter.format(d)+maxPayMonthStr,"PB");
				while(result.next()){
				PreparedStatement pstmt1=conn.prepareStatement(" insert into py_pay_plan_detail (PAYPLAN_DETAIL_ID,COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,PAY_MONTH_04,"
							             +" PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID,VERSIONNOTE) values (py_pay_plan_detail_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)");					
				pstmt1.setString(1,"1200");
				pstmt1.setString(2,request.getParameter("planyear"));								
				pstmt1.setString(3,timeFormatter.format(d)+maxPayMonthStr);				
				pstmt1.setString(4,nextseq);
				pstmt1.setString(5,result.getString("bg_gu"));
				pstmt1.setString(6,result.getString("bg_nm"));
				pstmt1.setString(7,result.getString("auth_group_type"));
				pstmt1.setString(8,result.getString("pos_type"));
				pstmt1.setString(9,result.getString("paytype"));
				pstmt1.setDouble(10,result.getDouble("bonus1"));
				pstmt1.setDouble(11,result.getDouble("bonus2"));
				pstmt1.setDouble(12,result.getDouble("bonus3"));
				pstmt1.setDouble(13,result.getDouble("bonus4"));
				pstmt1.setDouble(14,result.getDouble("bonus5"));
				pstmt1.setDouble(15,result.getDouble("bonus6"));
				pstmt1.setDouble(16,result.getDouble("bonus7"));
				pstmt1.setDouble(17,result.getDouble("bonus8"));
				pstmt1.setDouble(18,result.getDouble("bonus9"));
				pstmt1.setDouble(19,result.getDouble("bonus10"));
				pstmt1.setDouble(20,result.getDouble("bonus11"));
				pstmt1.setDouble(21,result.getDouble("bonus12"));
				pstmt1.setDouble(22,(result.getDouble("bonus1")+result.getDouble("bonus2")+result.getDouble("bonus3")+result.getDouble("bonus4")+result.getDouble("bonus5")+result.getDouble("bonus6")+result.getDouble("bonus7")+result.getDouble("bonus8")+result.getDouble("bonus9")+result.getDouble("bonus10")+result.getDouble("bonus11")+result.getDouble("bonus12")));
				pstmt1.setString(23,admin.getAdminID());					
				pstmt1.setString(24,"1200");
				pstmt1.setString(25,request.getParameter("versionNote"));
				pstmt1.executeUpdate();
				}			
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}		
		ArrayList FRateList = new ArrayList();
		ArrayList BRateList = new ArrayList();
		for(int i=1;i<13;i++){
	    SimpleMap FRateMap = new SimpleMap();
	    SimpleMap BRateMap = new SimpleMap();
	    FRateMap.set("FRate"+i,"0");	
	    BRateMap.set("BRate"+i,"0");
	    FRateList.add(FRateMap);
	    BRateList.add(BRateMap);
		}
		request.setAttribute("FRateList", FRateList);
		request.setAttribute("BRateList", BRateList);	
	   return "/pa_payPlan_03.jsp";
		
	  }
	public String payPlan04Serche(HttpServletRequest request){
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="";
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";	
		int startMonth=new Integer(request.getParameter("startMonth"));
		int endMonth=new Integer(request.getParameter("endMonth"));
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}
		if(timeFormatter.format(d).equals(request.getParameter("planyear"))){
	    sql +="select dep.deptname, sy.code_name,grade.post_grade_name,'加班费' as paytype ";
	    for(int y=1;y<(maxPayMonth+1);y++){
	     sql +=",'' as base"+y+",'' as avgottime"+y+",PERFORMANCE.month"+y;
	    }
	    for(int x=(maxPayMonth+1);x<13;x++){
		     sql +=",basetable.base"+x+" as base"+x+",avgottimetable.avgottime as avgottime"+x+" ,round((basetable.base"+x+"*avgottimetable.avgottime),2) as month"+x;
		}
		sql += " from (select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      
		for(int s=startMonth;s<(endMonth+1);s++){
		sql += " ,trunc((";
		for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.month"+i+")+" ;
		}
	    sql =sql.substring(0,sql.length()-1);
		sql +=")/ "
			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) month"+s;
        }      
		sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+ "        hr.join_type_code, "
			+ "         hr.post_grade_code, "
			+ "        hr.empid ";
		for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , nvl(pa"+i+".PERFORMANCE, 0) as month"+i+" ";
			}
			
			sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.deptid not in('78000000','780T0000') and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +="  full join "
			+ "  (select t.empid,case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE "
			+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
			}

			sql +="  full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
			+ "             from pa_history t "
			+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "    )temp ,  "
			+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.countm"+i+")+";
			}				
			sql=(sql.substring(0,(sql.length()-1)));
			sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+"         hr.join_type_code, "
			+ "        hr.post_grade_code,"
			+ "        hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , case when pa"+i+".PERFORMANCE<>0 then count(hr.empid) else 0 end as countm"+i+" ";
			}

			sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.deptid not in('78000000','780T0000')  and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql += "   full join "
			+ "    (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
			+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
		
		   }
			sql +=" full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
			+ "                      from pa_history t "
			+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=",pa"+i+".PERFORMANCE";
			 }
			sql += "   ) temp  "
			+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
			+ "   where sumpnumber.deptid=temp.deptid "		
			+ "   and sumpnumber.join_type_code=temp.join_type_code "
			+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
			+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) PERFORMANCE ,";
			sql += "( select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      	       
			sql += " ,trunc((";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.month"+i+")+" ;
			}
		    sql =sql.substring(0,sql.length()-1);
			sql +=")/ "
				+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) avgottime";
	         
			sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+ "        hr.join_type_code, "
				+ "         hr.post_grade_code, "
				+ "        hr.empid ";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , nvl(pa"+i+".ottime, 0) as month"+i+" ";
				}
				
				sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.deptid not in('78000000','780T0000') and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql +="  full join "
				+ "  (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime "
				+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
				}

				sql +="  full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
				+ "             from pa_history t "
				+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "    )temp ,  "
				+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.countm"+i+")+";
				}				
				sql=(sql.substring(0,(sql.length()-1)));
				sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+"         hr.join_type_code, "
				+ "        hr.post_grade_code,"
				+ "        hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , case when pa"+i+".ottime<>0 then count(hr.empid) else 0 end as countm"+i+" ";
				}

				sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.deptid not in('78000000','780T0000') and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql += "   full join "
				+ "    (select t.empid, (nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
				+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
			
			   }
				sql +=" full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
				+ "                      from pa_history t "
				+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=",pa"+i+".ottime";
				 }
				sql += "   ) temp  "
				+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
				+ "   where sumpnumber.deptid=temp.deptid "			
				+ "   and sumpnumber.join_type_code=temp.join_type_code "
				+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
				+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) avgottimetable,";
				sql += " (select pa.bg_gu,pa.bg_nm,pa.auth_group_type,pa.pos_type" ;
		        for(int s=1;s<13;s++){
				sql +=", round((( pa.pay_month_"+s+"+PE_RG.pay_month_"+s+"+PE_DA.pay_month_"+s+"+PE_SG.pay_month_"+s+")/21.5/8),2) base"+s;
				}
		        sql +=" from (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
		        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
		        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
		        	+" from py_pay_plan_detail t where t.pay_type = 'PA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') pa, " 
		        	+" (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
		        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
		        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
		        	+" from py_pay_plan_detail t where t.pay_type = 'PE_RG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_RG, "
		        	+"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
				    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
				    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
				    +" from py_pay_plan_detail t where t.pay_type = 'PE_DA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_DA, "
				    +"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
				    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
				    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
				    +" from py_pay_plan_detail t where t.pay_type = 'PE_SG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_SG " 
		        	+" where pa.bg_gu = PE_RG.bg_gu " 
		        	+" and pa.bg_nm = PE_RG.bg_nm " 
		        	+" and pa.auth_group_type = PE_RG.auth_group_type " 
		        	+" and pa.pos_type = PE_RG.pos_type " 
		        	+" and PE_DA.bg_gu = PE_RG.bg_gu " 
		        	+" and PE_DA.bg_nm = PE_RG.bg_nm " 
		        	+" and PE_DA.auth_group_type = PE_RG.auth_group_type " 
		        	+" and PE_DA.pos_type = PE_RG.pos_type " 
		        	+" and pa.bg_gu = PE_SG.bg_gu " 
		        	+" and pa.bg_nm = PE_SG.bg_nm " 
		        	+" and pa.auth_group_type = PE_SG.auth_group_type " 
		        	+" and pa.pos_type = PE_SG.pos_type) basetable ,sy_code sy,hr_post_grade grade,hr_department dep " 
		        	+" where PERFORMANCE.deptid=basetable.bg_gu " 
		        	+" and PERFORMANCE.join_type_code=basetable.auth_group_type " 
		        	+" and PERFORMANCE.post_grade_code=basetable.pos_type " 
		        	+" and avgottimetable.deptid=basetable.bg_gu " 
		        	+" and avgottimetable.join_type_code=basetable.auth_group_type " 
		        	+" and avgottimetable.post_grade_code=basetable.pos_type " 
		        	+" and basetable.bg_gu=dep.deptid " 
		        	+" and basetable.auth_group_type=sy.code_id " 
		        	+" and basetable.pos_type=grade.post_grade_id	 " 	          
		        	+" order by dep.deptname,sy.code_name,grade.post_grade_name " ;
		}else{
		    sql +="select dep.deptname, sy.code_name,grade.post_grade_name,'加班费' as paytype ";		    
		    for(int x=1;x<13;x++){
			     sql +=",basetable.base"+x+" as base"+x+",avgottimetable.avgottime as avgottime"+x+" ,round((basetable.base"+x+"*avgottimetable.avgottime),2) as month"+x;
			}
			sql += " from (select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      
			for(int s=startMonth;s<(endMonth+1);s++){
			sql += " ,trunc((";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.month"+i+")+" ;
			}
		    sql =sql.substring(0,sql.length()-1);
			sql +=")/ "
				+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) month"+s;
	        }      
			sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+ "        hr.join_type_code, "
				+ "         hr.post_grade_code, "
				+ "        hr.empid ";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , nvl(pa"+i+".PERFORMANCE, 0) as month"+i+" ";
				}
				
				sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.deptid not in('78000000','780T0000') and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql +="  full join "
				+ "  (select t.empid,case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE "
				+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
				}

				sql +="  full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
				+ "             from pa_history t "
				+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "    )temp ,  "
				+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.countm"+i+")+";
				}				
				sql=(sql.substring(0,(sql.length()-1)));
				sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+"         hr.join_type_code, "
				+ "        hr.post_grade_code,"
				+ "        hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , case when pa"+i+".PERFORMANCE<>0 then count(hr.empid) else 0 end as countm"+i+" ";
				}

				sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.deptid not in('78000000','780T0000')  and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql += "   full join "
				+ "    (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
				+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
			
			   }
				sql +=" full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
				+ "                      from pa_history t "
				+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=",pa"+i+".PERFORMANCE";
				 }
				sql += "   ) temp  "
				+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
				+ "   where sumpnumber.deptid=temp.deptid "		
				+ "   and sumpnumber.join_type_code=temp.join_type_code "
				+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
				+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) PERFORMANCE ,";
				sql += "( select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      	       
				sql += " ,trunc((";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +="sum(temp.month"+i+")+" ;
				}
			    sql =sql.substring(0,sql.length()-1);
				sql +=")/ "
					+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) avgottime";
		         
				sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
					+ "        hr.join_type_code, "
					+ "         hr.post_grade_code, "
					+ "        hr.empid ";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=" , nvl(pa"+i+".ottime, 0) as month"+i+" ";
					}
					
					sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.deptid not in('78000000','780T0000') and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
					for(int i=startMonth;i<endMonth;i++){
					sql +="  full join "
					+ "  (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime "
					+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
					+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
					}

					sql +="  full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
					+ "             from pa_history t "
					+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
					+ "    )temp ,  "
					+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
					for(int i=startMonth;i<(endMonth+1);i++){
					sql +="sum(temp.countm"+i+")+";
					}				
					sql=(sql.substring(0,(sql.length()-1)));
					sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
					+"         hr.join_type_code, "
					+ "        hr.post_grade_code,"
					+ "        hr.empid";
					for(int i=startMonth;i<(endMonth+1);i++){
					sql +=" , case when pa"+i+".ottime<>0 then count(hr.empid) else 0 end as countm"+i+" ";
					}

					sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.deptid not in('78000000','780T0000')  and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
					for(int i=startMonth;i<endMonth;i++){
					sql += "   full join "
					+ "    (select t.empid, (nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
					+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
					+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
				
				   }
					sql +=" full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
					+ "                      from pa_history t "
					+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
					+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
					for(int i=startMonth;i<(endMonth+1);i++){
						sql +=",pa"+i+".ottime";
					 }
					sql += "   ) temp  "
					+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
					+ "   where sumpnumber.deptid=temp.deptid "			
					+ "   and sumpnumber.join_type_code=temp.join_type_code "
					+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
					+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) avgottimetable,";
					sql += " (select pa.bg_gu,pa.bg_nm,pa.auth_group_type,pa.pos_type" ;
			        for(int s=1;s<13;s++){
					sql +=", round((( pa.pay_month_"+s+"+PE_RG.pay_month_"+s+"+PE_DA.pay_month_"+s+"+PE_SG.pay_month_"+s+")/21.5/8),2) base"+s;
					}
			        sql +=" from (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
			        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
			        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
			        	+" from py_pay_plan_detail t where t.pay_type = 'PA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("paVersion").split(",")[0]+"') pa, " 
			        	+" (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
			        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
			        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
			        	+" from py_pay_plan_detail t where t.pay_type = 'PE_RG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_RG, "
			        	+"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
					    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
					    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
					    +" from py_pay_plan_detail t where t.pay_type = 'PE_DA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_DA, "
					    +"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
					    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
					    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
					    +" from py_pay_plan_detail t where t.pay_type = 'PE_SG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_SG " 
			        	+" where pa.bg_gu = PE_RG.bg_gu " 
			        	+" and pa.bg_nm = PE_RG.bg_nm " 
			        	+" and pa.auth_group_type = PE_RG.auth_group_type " 
			        	+" and pa.pos_type = PE_RG.pos_type " 
			        	+" and PE_DA.bg_gu = PE_RG.bg_gu " 
			        	+" and PE_DA.bg_nm = PE_RG.bg_nm " 
			        	+" and PE_DA.auth_group_type = PE_RG.auth_group_type " 
			        	+" and PE_DA.pos_type = PE_RG.pos_type " 
			        	+" and pa.bg_gu = PE_SG.bg_gu " 
			        	+" and pa.bg_nm = PE_SG.bg_nm " 
			        	+" and pa.auth_group_type = PE_SG.auth_group_type " 
			        	+" and pa.pos_type = PE_SG.pos_type) basetable ,sy_code sy,hr_post_grade grade,hr_department dep " 
			        	+" where PERFORMANCE.deptid=basetable.bg_gu " 
			        	+" and PERFORMANCE.join_type_code=basetable.auth_group_type " 
			        	+" and PERFORMANCE.post_grade_code=basetable.pos_type " 
			        	+" and avgottimetable.deptid=basetable.bg_gu " 
			        	+" and avgottimetable.join_type_code=basetable.auth_group_type " 
			        	+" and avgottimetable.post_grade_code=basetable.pos_type " 
			        	+" and basetable.bg_gu=dep.deptid " 
			        	+" and basetable.auth_group_type=sy.code_id " 
			        	+" and basetable.pos_type=grade.post_grade_id	 " 	          
			        	+" order by dep.deptname ,sy.code_name,grade.post_grade_name" ;
			
		}
			System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			List list = new ArrayList();			
			System.out.println(new Double(15)/100);
			try {
			
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				while(result.next()){
					SimpleMap sMap = new SimpleMap();
					sMap.set("deptname", result.getString("deptname"));
					sMap.set("code_name", result.getString("code_name"));
					sMap.set("post_grade_name", result.getString("post_grade_name"));
					sMap.set("paytype", result.getString("paytype"));
					for(int i=1;i<13;i++){
						sMap.set("base"+i, result.getDouble("base"+i));
						sMap.set("avgottime"+i, result.getDouble("avgottime"+i));
						sMap.set("month"+i, result.getDouble("month"+i));
					}				
					list.add(sMap);
				}
				
			
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}	
		request.setAttribute("paVersionStr", request.getParameter("paVersion").split(",")[0]);
		request.setAttribute("peVersionStr", request.getParameter("peVersion").split(",")[0]);	
		request.setAttribute("planyear",request.getParameter("planyear"));
		request.setAttribute("endMonth",request.getParameter("endMonth"));
		request.setAttribute("startMonth",request.getParameter("startMonth"));		
		request.setAttribute("payPlan04Serche", list);
			return "/pa_payPlan_04.jsp";
		
	  }
	
	public String payPlan04Save(HttpServletRequest request,AdminBean admin){
		int startMonth=new Integer(request.getParameter("startMonth"));
		int endMonth=new Integer(request.getParameter("endMonth"));
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="";
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";		
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}
		if(timeFormatter.format(d).equals(request.getParameter("planyear"))){
	    sql +="select basetable.bg_gu, basetable.bg_nm,basetable.auth_group_type,basetable.pos_type,'PC' as paytype ";
	    for(int y=1;y<(maxPayMonth+1);y++){
	     sql +=",PERFORMANCE.month"+y;
	    }
	    for(int x=(maxPayMonth+1);x<13;x++){
		     sql +=",round((basetable.base"+x+"*avgottimetable.avgottime),2) as month"+x;
		}
		sql += " from (select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      
		for(int s=startMonth;s<(endMonth+1);s++){
		sql += " ,trunc((";
		for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.month"+i+")+" ;
		}
	    sql =sql.substring(0,sql.length()-1);
		sql +=")/ "
			+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) month"+s;
        }      
		sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+ "        hr.join_type_code, "
			+ "         hr.post_grade_code, "
			+ "        hr.empid ";
		for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , nvl(pa"+i+".PERFORMANCE, 0) as month"+i+" ";
			}
			
			sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
	    for(int i=startMonth;i<endMonth;i++){
			sql +="  full join "
			+ "  (select t.empid,case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE "
			+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
			}

			sql +="  full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
			+ "             from pa_history t "
			+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "    )temp ,  "
			+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +="sum(temp.countm"+i+")+";
			}				
			sql=(sql.substring(0,(sql.length()-1)));
			sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
			+"         hr.join_type_code, "
			+ "        hr.post_grade_code,"
			+ "        hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
			sql +=" , case when pa"+i+".PERFORMANCE<>0 then count(hr.empid) else 0 end as countm"+i+" ";
			}

			sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
			for(int i=startMonth;i<endMonth;i++){
			sql += "   full join "
			+ "    (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
			+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
			+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
		
		   }
			sql +=" full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
			+ "                      from pa_history t "
			+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
			+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=",pa"+i+".PERFORMANCE";
			 }
			sql += "   ) temp  "
			+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
			+ "   where sumpnumber.deptid=temp.deptid "		
			+ "   and sumpnumber.join_type_code=temp.join_type_code "
			+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
			+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) PERFORMANCE ,";
			sql += "( select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      	       
			sql += " ,trunc((";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.month"+i+")+" ;
			}
		    sql =sql.substring(0,sql.length()-1);
			sql +=")/ "
				+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) avgottime";
	         
			sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+ "        hr.join_type_code, "
				+ "         hr.post_grade_code, "
				+ "        hr.empid ";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , nvl(pa"+i+".ottime, 0) as month"+i+" ";
				}
				
				sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
			for(int i=startMonth;i<endMonth;i++){
				sql +="  full join "
				+ "  (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime "
				+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
				}

				sql +="  full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
				+ "             from pa_history t "
				+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "    )temp ,  "
				+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.countm"+i+")+";
				}				
				sql=(sql.substring(0,(sql.length()-1)));
				sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+"         hr.join_type_code, "
				+ "        hr.post_grade_code,"
				+ "        hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , case when pa"+i+".ottime<>0 then count(hr.empid) else 0 end as countm"+i+" ";
				}

				sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
			for(int i=startMonth;i<endMonth;i++){
				sql += "   full join "
				+ "    (select t.empid, (nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
				+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
			
			   }
				sql +=" full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
				+ "                      from pa_history t "
				+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
			for(int i=startMonth;i<(endMonth+1);i++){
					sql +=",pa"+i+".ottime";
				 }
				sql += "   ) temp  "
				+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
				+ "   where sumpnumber.deptid=temp.deptid "			
				+ "   and sumpnumber.join_type_code=temp.join_type_code "
				+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
				+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) avgottimetable,";
				sql += " (select pa.bg_gu,pa.bg_nm,pa.auth_group_type,pa.pos_type" ;
		        for(int s=1;s<13;s++){
				sql +=", round((( pa.pay_month_"+s+"+PE_RG.pay_month_"+s+"+PE_DA.pay_month_"+s+"+PE_SG.pay_month_"+s+")/21.5/8),2) base"+s;
				}
		        sql +=" from (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
		        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
		        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
		        	+" from py_pay_plan_detail t where t.pay_type = 'PA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("paVersion").split(",")[0]+"') pa, " 
		        	+" (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
		        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
		        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
		        	+" from py_pay_plan_detail t where t.pay_type = 'PE_RG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_RG, "
		        	+"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
				    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
				    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
				    +" from py_pay_plan_detail t where t.pay_type = 'PE_DA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_DA, "
				    +"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
				    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
				    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
				    +" from py_pay_plan_detail t where t.pay_type = 'PE_SG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_SG " 
		        	+" where pa.bg_gu = PE_RG.bg_gu " 
		        	+" and pa.bg_nm = PE_RG.bg_nm " 
		        	+" and pa.auth_group_type = PE_RG.auth_group_type " 
		        	+" and pa.pos_type = PE_RG.pos_type " 
		        	+" and PE_DA.bg_gu = PE_RG.bg_gu " 
		        	+" and PE_DA.bg_nm = PE_RG.bg_nm " 
		        	+" and PE_DA.auth_group_type = PE_RG.auth_group_type " 
		        	+" and PE_DA.pos_type = PE_RG.pos_type " 
		        	+" and pa.bg_gu = PE_SG.bg_gu " 
		        	+" and pa.bg_nm = PE_SG.bg_nm " 
		        	+" and pa.auth_group_type = PE_SG.auth_group_type " 
		        	+" and pa.pos_type = PE_SG.pos_type) basetable " 
		        	+" where PERFORMANCE.deptid=basetable.bg_gu " 
		        	+" and PERFORMANCE.join_type_code=basetable.auth_group_type " 
		        	+" and PERFORMANCE.post_grade_code=basetable.pos_type " 
		        	+" and avgottimetable.deptid=basetable.bg_gu " 
		        	+" and avgottimetable.join_type_code=basetable.auth_group_type " 
		        	+" and avgottimetable.post_grade_code=basetable.pos_type " ;
		        	
		}else{
		    sql +="select basetable.bg_gu, basetable.bg_nm,basetable.auth_group_type,basetable.pos_type,'PC' as paytype ";		    
		    for(int x=1;x<13;x++){
			     sql +=",round((basetable.base"+x+"*avgottimetable.avgottime),2) as month"+x;
			}
			sql += " from (select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      
	        for(int s=1;s<(maxPayMonth+1);s++){
			sql += " ,trunc((";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.month"+i+")+" ;
			}
		    sql =sql.substring(0,sql.length()-1);
			sql +=")/ "
				+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) month"+s;
	        }      
			sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+ "        hr.join_type_code, "
				+ "         hr.post_grade_code, "
				+ "        hr.empid ";
			for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , nvl(pa"+i+".PERFORMANCE, 0) as month"+i+" ";
				}
				
				sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql +="  full join "
				+ "  (select t.empid,case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE "
				+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
				}

				sql +="  full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
				+ "             from pa_history t "
				+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "    )temp ,  "
				+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +="sum(temp.countm"+i+")+";
				}				
				sql=(sql.substring(0,(sql.length()-1)));
				sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
				+"         hr.join_type_code, "
				+ "        hr.post_grade_code,"
				+ "        hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
				sql +=" , case when pa"+i+".PERFORMANCE<>0 then count(hr.empid) else 0 end as countm"+i+" ";
				}

				sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
				for(int i=startMonth;i<endMonth;i++){
				sql += "   full join "
				+ "    (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
				+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
				+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
			
			   }
				sql +=" full join (select t.empid, case  when t.EMPLOYEE_DISTINCTION = '职员' then  t.PERFORMANCE_PAY2    when t.EMPLOYEE_DISTINCTION = '工人' or t.EMPLOYEE_DISTINCTION = '临时工' then  t.performance_pay3   end   as PERFORMANCE  "
				+ "                      from pa_history t "
				+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
				+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=",pa"+i+".PERFORMANCE";
				 }
				sql += "   ) temp  "
				+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
				+ "   where sumpnumber.deptid=temp.deptid "		
				+ "   and sumpnumber.join_type_code=temp.join_type_code "
				+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
				+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) PERFORMANCE ,";
				sql += "( select   temp.deptid, temp.join_type_code, temp.post_grade_code  " ;      	       
				sql += " ,trunc((";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +="sum(temp.month"+i+")+" ;
				}
			    sql =sql.substring(0,sql.length()-1);
				sql +=")/ "
					+ " (case when (sumpnumber.sump)=0 then 1 else (sumpnumber.sump) end ),2) avgottime";
		         
				sql +="  from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
					+ "        hr.join_type_code, "
					+ "         hr.post_grade_code, "
					+ "        hr.empid ";
				for(int i=startMonth;i<(endMonth+1);i++){
					sql +=" , nvl(pa"+i+".ottime, 0) as month"+i+" ";
					}
					
					sql +="   from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'  and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
					for(int i=startMonth;i<endMonth;i++){
					sql +="  full join "
					+ "  (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime "
					+ "          from "+this.getPayTableName((i-1), sysDate1)+" t "
					+ "         where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
					}

					sql +="  full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
					+ "             from pa_history t "
					+ "               where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm') and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
					+ "    )temp ,  "
					+ "   (select  temp.deptid,temp.join_type_code,temp.post_grade_code,(";				
					for(int i=startMonth;i<(endMonth+1);i++){
					sql +="sum(temp.countm"+i+")+";
					}				
					sql=(sql.substring(0,(sql.length()-1)));
					sql	+=") as sump from (select hr_get_level_deptid(hr.deptid,'3') deptid, "
					+"         hr.join_type_code, "
					+ "        hr.post_grade_code,"
					+ "        hr.empid";
					for(int i=startMonth;i<(endMonth+1);i++){
					sql +=" , case when pa"+i+".ottime<>0 then count(hr.empid) else 0 end as countm"+i+" ";
					}

					sql +="  from (select t.empid,t.deptid,t.join_type_code,t.post_grade_code from hr_employee t where (t.date_left is null or t.date_left >to_date('"+sysDate+"','yyyy-mm-dd')) and t.empid<>'000000'   and t.wages_type_code<>'C_20020_1' and t.EMP_DIFF_CODE='C_12067_1330306') hr ";
					for(int i=startMonth;i<endMonth;i++){
					sql += "   full join "
					+ "    (select t.empid, (nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
					+ "           from "+this.getPayTableName((i-1), sysDate1)+" t "
					+ "        where t.pa_month =to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(i-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+i+" on hr.empid=pa"+i+".empid ";
				
				   }
					sql +=" full join (select t.empid,(nvl(t.WEEKDAY_OT, 0) * 1.5+nvl(t.WEEKEND_OT, 0) * 2+nvl(t.HOLIDAY_OT, 0)* 3) ottime  "
					+ "                      from pa_history t "
					+ "            where t.pa_month = to_char(add_months(to_date('"+sysDate+"','yyyy-mm-dd'),"+(maxPayMonth-1)+"),'yyyymm')  and t.wages_type_code<>'C_20020_1' and t.attendance_period_code='C_12067_1330306' ) pa"+maxPayMonth+" on hr.empid = pa"+maxPayMonth+".empid "
					+ "  group by deptid,  hr.join_type_code, hr.post_grade_code, hr.empid";
					for(int i=startMonth;i<(endMonth+1);i++){
						sql +=",pa"+i+".ottime";
					 }
					sql += "   ) temp  "
					+ "   group by temp.deptid,temp.join_type_code,temp.post_grade_code) sumpnumber "
					+ "   where sumpnumber.deptid=temp.deptid "			
					+ "   and sumpnumber.join_type_code=temp.join_type_code "
					+ "   and sumpnumber.post_grade_code=temp.post_grade_code "		
					+ "   group by temp.deptid, temp.join_type_code, temp.post_grade_code , sumpnumber.sump order by temp.deptid ) avgottimetable,";
					sql += " (select pa.bg_gu,pa.bg_nm,pa.auth_group_type,pa.pos_type" ;
			        for(int s=1;s<13;s++){
					sql +=", round((( pa.pay_month_"+s+"+PE_RG.pay_month_"+s+"+PE_DA.pay_month_"+s+"+PE_SG.pay_month_"+s+")/21.5/8),2) base"+s;
					}
			        sql +=" from (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
			        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
			        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
			        	+" from py_pay_plan_detail t where t.pay_type = 'PA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("paVersion").split(",")[0]+"') pa, " 
			        	+" (select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
			        	+" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
			        	+"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
			        	+" from py_pay_plan_detail t where t.pay_type = 'PE_RG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_RG, "
			        	+"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
					    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
					    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
					    +" from py_pay_plan_detail t where t.pay_type = 'PE_DA' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_DA, "
					    +"(select t.bg_gu,t.bg_nm, t.auth_group_type, t.pos_type, " 
					    +" t.pay_month_01 pay_month_1, t.pay_month_02 pay_month_2, t.pay_month_03 pay_month_3, t.pay_month_04 pay_month_4, t.pay_month_05 pay_month_5,t.pay_month_06 pay_month_6, " 
					    +"  t.pay_month_07 pay_month_7, t.pay_month_08 pay_month_8, t.pay_month_09 pay_month_9, t.pay_month_10 pay_month_10, t.pay_month_11 pay_month_11,t.pay_month_12 pay_month_12 " 
					    +" from py_pay_plan_detail t where t.pay_type = 'PE_SG' and t.plan_yy='"+request.getParameter("planyear")+"' and t.base_yymm='"+(timeFormatter.format(d)+maxPayMonthStr)+"' and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"') PE_SG " 
			        	+" where pa.bg_gu = PE_RG.bg_gu " 
			        	+" and pa.bg_nm = PE_RG.bg_nm " 
			        	+" and pa.auth_group_type = PE_RG.auth_group_type " 
			        	+" and pa.pos_type = PE_RG.pos_type " 
			        	+" and PE_DA.bg_gu = PE_RG.bg_gu " 
			        	+" and PE_DA.bg_nm = PE_RG.bg_nm " 
			        	+" and PE_DA.auth_group_type = PE_RG.auth_group_type " 
			        	+" and PE_DA.pos_type = PE_RG.pos_type " 
			        	+" and pa.bg_gu = PE_SG.bg_gu " 
			        	+" and pa.bg_nm = PE_SG.bg_nm " 
			        	+" and pa.auth_group_type = PE_SG.auth_group_type " 
			        	+" and pa.pos_type = PE_SG.pos_type) basetable " 
			        	+" where PERFORMANCE.deptid=basetable.bg_gu " 
			        	+" and PERFORMANCE.join_type_code=basetable.auth_group_type " 
			        	+" and PERFORMANCE.post_grade_code=basetable.pos_type " 
			        	+" and avgottimetable.deptid=basetable.bg_gu " 
			        	+" and avgottimetable.join_type_code=basetable.auth_group_type " 
			        	+" and avgottimetable.post_grade_code=basetable.pos_type " ;
			        	
			        	
			
		}
			System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			List list = new ArrayList();			
			System.out.println(new Double(15)/100);
			try {
			
				pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				String nextseq=this.getSequence(request.getParameter("planyear"), timeFormatter.format(d)+maxPayMonthStr,"PC");
				while(result.next()){
				PreparedStatement pstmt1=conn.prepareStatement(" insert into py_pay_plan_detail (PAYPLAN_DETAIL_ID,COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,BG_GU,BG_NM,AUTH_GROUP_TYPE,POS_TYPE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,PAY_MONTH_04,"
							             +" PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,PAY_MONTH_12,PAY_MONTH_TOT,MOD_USER_ID,MOD_DATE,MOD_LOC_ID,VERSIONNOTE) values (py_pay_plan_detail_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)");					
				pstmt1.setString(1,"1200");
				pstmt1.setString(2,request.getParameter("planyear"));
				pstmt1.setString(3,timeFormatter.format(d)+maxPayMonthStr);				
				pstmt1.setString(4,nextseq);
				pstmt1.setString(5,result.getString("bg_gu"));
				pstmt1.setString(6,result.getString("bg_nm"));
				pstmt1.setString(7,result.getString("auth_group_type"));
				pstmt1.setString(8,result.getString("pos_type"));
				pstmt1.setString(9,result.getString("paytype"));
				pstmt1.setDouble(10,result.getDouble("month1"));
				pstmt1.setDouble(11,result.getDouble("month2"));
				pstmt1.setDouble(12,result.getDouble("month3"));
				pstmt1.setDouble(13,result.getDouble("month4"));
				pstmt1.setDouble(14,result.getDouble("month5"));
				pstmt1.setDouble(15,result.getDouble("month6"));
				pstmt1.setDouble(16,result.getDouble("month7"));
				pstmt1.setDouble(17,result.getDouble("month8"));
				pstmt1.setDouble(18,result.getDouble("month9"));
				pstmt1.setDouble(19,result.getDouble("month10"));
				pstmt1.setDouble(20,result.getDouble("month11"));
				pstmt1.setDouble(21,result.getDouble("month12"));
				pstmt1.setDouble(22,(result.getDouble("month1")+result.getDouble("month2")+result.getDouble("month3")+result.getDouble("month4")+result.getDouble("month5")+result.getDouble("month6")+result.getDouble("month7")+result.getDouble("month8")+result.getDouble("month9")+result.getDouble("month10")+result.getDouble("month11")+result.getDouble("month12")));
				pstmt1.setString(23,admin.getAdminID());					
				pstmt1.setString(24,"1200");
				pstmt1.setString(25,request.getParameter("versionNote"));
				pstmt1.executeUpdate();
				
				}
				
			
			} catch (Exception e) {
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}		
			return request.getParameter("planyear")+"加班费推算成功！";
		
	  }
	public String GenerationPayPlanCC(HttpServletRequest request,AdminBean admin){
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql="";
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}
		//补贴
		 sql +="select pp.com_org_id,pp.plan_yy,pp.base_yymm,pp.bg_gu,pp.bg_nm,pp.org_id,pp.org_full_nm,'PE' as pay_type,pp.group_type," ;
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0) pay_month_"+("0"+i)+",";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0) pay_month_"+i+",";	
				}			
			}
		 sql +="(";
		    for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0)+";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0)+";	
				}			
			}
		 sql = sql.substring(0,sql.length()-1);
		 sql +=") pay_month_tot from (select t.bg_gu,t.bg_nm,substr(t.auth_group_type,9,7) auth_group_type ,substr(t.pos_type, 9, 7) pos_type, t.plan_yy,t.base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="sum(t.pay_month_"+("0"+i)+") pay_month_"+("0"+i)+",";					
				}else{
					sql +="sum(t.pay_month_"+i+") pay_month_"+i+",";	
				}			
			}
		 sql +="'PE' as pay_type  from py_pay_plan_detail t ";
         sql +=" where t.pay_type in ('PE_DS', 'PE_OC', 'PE_RG', 'PE_RS', 'PE_SG', 'PE_DA') and t.seq_id = '"+request.getParameter("peVersion").split(",")[0]+"' and t.plan_yy = '"+request.getParameter("planyear")+"'  and t.base_yymm = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               " group by t.bg_gu, t.bg_nm, t.auth_group_type, t.plan_yy, t.base_yymm,t.pos_type) pe,";
         sql +="(select t.bg_gu,t.com_org_id,t.bg_nm,t.org_id,t.org_full_nm,t.group_type ,t.pos_type,t.plan_yy,substr(t.plan_base_ymd, 1, 6) as base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="inwon_month_"+("0"+i)+",";					
				}else{
					sql +="inwon_month_"+i+",";	
				}			
			}
		 sql +="inwon_month_tot ";
		 sql +="from t_py_person_plan t where t.plan_yy = '"+request.getParameter("planyear")+"' and substr(t.plan_base_ymd,1,6) = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               " ) pp ";
		 sql +=" where pe.bg_gu(+) = pp.bg_gu and pe.auth_group_type(+)=pp.group_type and pp.pos_type=pe.pos_type " +
		 		" group by pp.com_org_id,  pp.plan_yy,  pp.base_yymm,   pp.bg_gu,   pp.bg_nm,   pp.org_id, pp.org_full_nm,pp.group_type ";
		 //基本工资
		 sql +=" union ";
		 sql +="select pp.com_org_id,pp.plan_yy,pp.base_yymm,pp.bg_gu,pp.bg_nm,pp.org_id,pp.org_full_nm,'PA' as pay_type,pp.group_type," ;
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0) pay_month_"+("0"+i)+",";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0) pay_month_"+i+",";	
				}			
			}
		 sql +="(";
		    for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0)+";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0)+";	
				}			
			}
		 sql = sql.substring(0,sql.length()-1);
		 sql +=") pay_month_tot from (select t.bg_gu,t.bg_nm,substr(t.auth_group_type,9,7) auth_group_type ,substr(t.pos_type, 9, 7) pos_type,t.plan_yy,t.base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +=" pay_month_"+("0"+i)+",";					
				}else{
					sql +=" pay_month_"+i+",";	
				}			
			}
		 sql +="'PA' as pay_type from py_pay_plan_detail t";
         sql +=" where t.pay_type='PA' and t.seq_id = '"+request.getParameter("paVersion").split(",")[0]+"' and t.plan_yy = '"+request.getParameter("planyear")+"'  and t.base_yymm = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               "  ) pe,";
         sql +="(select t.bg_gu,t.com_org_id,t.bg_nm,t.org_id,t.org_full_nm,t.group_type ,t.pos_type,t.plan_yy,substr(t.plan_base_ymd, 1, 6) as base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="inwon_month_"+("0"+i)+",";					
				}else{
					sql +="inwon_month_"+i+",";	
				}			
			}
		 sql +="inwon_month_tot ";
		 sql +="from t_py_person_plan t where t.plan_yy = '"+request.getParameter("planyear")+"' and substr(t.plan_base_ymd,1,6) = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               " ) pp ";
		 sql +=" where pe.bg_gu(+) = pp.bg_gu and pe.auth_group_type(+)=pp.group_type  and pp.pos_type=pe.pos_type " +
		 		" group by pp.com_org_id,  pp.plan_yy,  pp.base_yymm,   pp.bg_gu,   pp.bg_nm,   pp.org_id, pp.org_full_nm,pp.group_type ";
		 //奖金
		 sql +=" union ";
		 sql +="select pp.com_org_id,pp.plan_yy,pp.base_yymm,pp.bg_gu,pp.bg_nm,pp.org_id,pp.org_full_nm,'PB' as pay_type,pp.group_type," ;
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0) pay_month_"+("0"+i)+",";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0) pay_month_"+i+",";	
				}			
			}
		 sql +="(";
		    for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0)+";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0)+";	
				}			
			}
		 sql = sql.substring(0,sql.length()-1);
		 sql +=") pay_month_tot from (select t.bg_gu,t.bg_nm,substr(t.auth_group_type,9,7) auth_group_type ,substr(t.pos_type, 9, 7) pos_type,t.plan_yy,t.base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="pay_month_"+("0"+i)+",";					
				}else{
					sql +="pay_month_"+i+",";	
				}			
			}
		 sql +="'PB' as pay_type from py_pay_plan_detail t";
         sql +=" where t.pay_type='PB' and t.seq_id = '"+request.getParameter("pbVersion").split(",")[0]+"' and t.plan_yy = '"+request.getParameter("planyear")+"'  and t.base_yymm = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               " ) pe,";
         sql +="(select t.bg_gu,t.com_org_id,t.bg_nm,t.org_id,t.org_full_nm,t.group_type ,t.pos_type,t.plan_yy,substr(t.plan_base_ymd, 1, 6) as base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="inwon_month_"+("0"+i)+",";					
				}else{
					sql +="inwon_month_"+i+",";	
				}			
			}
		 sql +="inwon_month_tot ";
		 sql +="from t_py_person_plan t where t.plan_yy = '"+request.getParameter("planyear")+"' and substr(t.plan_base_ymd,1,6) = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               " ) pp ";
		 sql +=" where pe.bg_gu(+) = pp.bg_gu and pe.auth_group_type(+)=pp.group_type and pp.pos_type=pe.pos_type " +
		 		" group by pp.com_org_id,  pp.plan_yy,  pp.base_yymm,   pp.bg_gu,   pp.bg_nm,   pp.org_id, pp.org_full_nm,pp.group_type ";
		 //加班费
		 sql +=" union ";
		 sql +="select pp.com_org_id,pp.plan_yy,pp.base_yymm,pp.bg_gu,pp.bg_nm,pp.org_id,pp.org_full_nm,'PC' as pay_type,pp.group_type," ;
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0) pay_month_"+("0"+i)+",";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0) pay_month_"+i+",";	
				}			
			}
		 sql +="(";
		    for(int i=1;i<13;i++){
				if(i<10){
					sql +="nvl(sum(pe.pay_month_"+("0"+i)+" * pp.inwon_month_"+("0"+i)+"),0)+";					
				}else{
					sql +="nvl(sum(pe.pay_month_"+i+" * pp.inwon_month_"+i+"),0)+";	
				}			
			}
		 sql = sql.substring(0,sql.length()-1);
		 sql +=") pay_month_tot from (select t.bg_gu,t.bg_nm,substr(t.auth_group_type,9,7) auth_group_type ,substr(t.pos_type, 9, 7) pos_type,t.plan_yy,t.base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="pay_month_"+("0"+i)+",";					
				}else{
					sql +="pay_month_"+i+",";	
				}			
			}
		 sql +="'PC' as pay_type from py_pay_plan_detail t";
         sql +=" where t.pay_type='PC' and t.seq_id = '"+request.getParameter("pcVersion").split(",")[0]+"' and t.plan_yy = '"+request.getParameter("planyear")+"'  and t.base_yymm = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               " ) pe,";
         sql +="(select t.bg_gu,t.com_org_id,t.bg_nm,t.org_id,t.org_full_nm,t.group_type ,t.pos_type,t.plan_yy,substr(t.plan_base_ymd, 1, 6) as base_yymm,";
		 for(int i=1;i<13;i++){
				if(i<10){
					sql +="inwon_month_"+("0"+i)+",";					
				}else{
					sql +="inwon_month_"+i+",";	
				}			
			}
		 sql +="inwon_month_tot ";
		 sql +="from t_py_person_plan t where t.plan_yy = '"+request.getParameter("planyear")+"' and substr(t.plan_base_ymd,1,6) = '"+(timeFormatter.format(d)+maxPayMonthStr)+"' "+
               " ) pp ";
		 sql +=" where pe.bg_gu(+) = pp.bg_gu and pe.auth_group_type(+)=pp.group_type and pp.pos_type=pe.pos_type " +
		 		" group by pp.com_org_id,  pp.plan_yy,  pp.base_yymm,   pp.bg_gu,   pp.bg_nm,   pp.org_id, pp.org_full_nm,pp.group_type ";
		 		System.out.println(sql);			
			Logger.getLogger(getClass()).debug(sql);
			
			try {
			   String seqid=this.getVersionSeq(request.getParameter("planyear"),timeFormatter.format(d)+maxPayMonthStr);
				pstmt = conn.prepareStatement(sql);
				System.out.println(sql);
				result=pstmt.executeQuery();
				while(result.next()){
				PreparedStatement pstmt1=conn.prepareStatement(" insert into py_pay_plan_detail_cc (PAYPLAN_DETAIL_CC_ID,COM_ORG_ID,PLAN_YY,BASE_YYMM,SEQ_ID,BG_GU,BG_NM,ORG_ID,ORG_FULL_NM,AUTH_GROUP_TYPE,PAY_TYPE,PAY_MONTH_01,PAY_MONTH_02,PAY_MONTH_03,PAY_MONTH_04,"
							             +" PAY_MONTH_05,PAY_MONTH_06,PAY_MONTH_07,PAY_MONTH_08,PAY_MONTH_09,PAY_MONTH_10,PAY_MONTH_11,PAY_MONTH_12,PAY_TOT_MON,MOD_USER_ID,MOD_DATE,MOD_LOC_ID) values (py_pay_plan_detail_cc_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)");					
				pstmt1.setString(1,"78000000");
				System.out.println("ddddddd");
				pstmt1.setString(2,result.getString("plan_yy"));
				pstmt1.setString(3,timeFormatter.format(d)+maxPayMonthStr);
				pstmt1.setString(4,seqid);
				pstmt1.setString(5,result.getString("bg_gu"));
				pstmt1.setString(6,result.getString("bg_nm"));
				pstmt1.setString(7,result.getString("org_id"));
				pstmt1.setString(8,result.getString("org_full_nm"));
				pstmt1.setString(9,result.getString("group_type"));
				pstmt1.setString(10,result.getString("pay_type"));
				pstmt1.setDouble(11,result.getDouble("pay_month_01"));
				pstmt1.setDouble(12,result.getDouble("pay_month_02"));
				pstmt1.setDouble(13,result.getDouble("pay_month_03"));
				pstmt1.setDouble(14,result.getDouble("pay_month_04"));
				pstmt1.setDouble(15,result.getDouble("pay_month_05"));
				pstmt1.setDouble(16,result.getDouble("pay_month_06"));
				pstmt1.setDouble(17,result.getDouble("pay_month_07"));
				pstmt1.setDouble(18,result.getDouble("pay_month_08"));
				pstmt1.setDouble(19,result.getDouble("pay_month_09"));
				pstmt1.setDouble(20,result.getDouble("pay_month_10"));
				pstmt1.setDouble(21,result.getDouble("pay_month_11"));
				pstmt1.setDouble(22,result.getDouble("pay_month_12"));
				pstmt1.setDouble(23,result.getDouble("pay_month_tot"));
				pstmt1.setString(24,admin.getAdminID());					
				pstmt1.setString(25,"78000000");				
				pstmt1.executeUpdate();
				SqlUtil.close(pstmt1);
				}
				
			
			} catch (Exception e) {
				System.out.println("error when  insert data!");
		
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}			
			return request.getParameter("planyear")+"年基本工资推算成功！";
		
	  }
	public  int ExitsPersonPlan(HttpServletRequest request){
		int flag=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		Connection conn = ConnBean.getConn();
		ResultSet rs=null;
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");		
		String sysDate=timeFormatter.format(d)+"-01-01";
		String sysDate1=timeFormatter.format(d)+"0101";
		int maxPayMonth=this.getPayMonthMax(request.getParameter("startMonth"),request.getParameter("endMonth"));
		String maxPayMonthStr ="";
		if(maxPayMonth<10){
			maxPayMonthStr="0"+maxPayMonth;
		}else{
			maxPayMonthStr=StringUtil.checkNull(maxPayMonth);
		}
        String sql = "select * from T_PY_PERSON_PLAN t where t.PLAN_YY='"+request.getParameter("planyear")+"' and substr(t.PLAN_BASE_YMD,1,6)='"+(timeFormatter.format(d)+maxPayMonthStr)+"'";
        System.out.println(sql);
        try {
        	pstmt = conn.prepareStatement(sql);
        	rs=pstmt.executeQuery();
        	if(rs.next()){        	
        		flag=1;
        	}
           
       
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs,pstmt, conn);
        }
		return flag;
		
	}
	/*得到工资的最大月份*/
	private int getPayMonthMax(String startMonth,String endMonth){
		ResultSet result = null;
		int maxMonth=0;
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql = " select to_number(nvl(substr(nvl(max(t.pa_month),0),5,2),0)) as maxmonth from pa_history t "+
                     " where t.pa_month between to_char(SYSDATE,'yyyy')||'"+startMonth+"' and to_char(SYSDATE,'yyyy')||'"+endMonth+"'  ";
		Logger.getLogger(getClass()).debug(sql);
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			if(result.next()){
				maxMonth=result.getInt("maxmonth");
			}
			
		
		} catch (Exception e) {
	
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		
		return maxMonth;
		
	}
	/*得到工资表名*/
	private String getPayTableName(int addMonth,String sysdate){
		ResultSet result = null;
		String tableName="";
		Connection conn = ConnBean.getConn();			
		PreparedStatement pstmt = null;
		String sql = " select to_char('pa_history'||(to_char(add_months(to_date('"+sysdate+"','yyyymmdd'),"+addMonth+"),'yyyymm'))) mytable from dual";
		Logger.getLogger(getClass()).debug(sql);
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			if(result.next()){
				tableName=result.getString("mytable");
			}
			
		
		} catch (Exception e) {
	
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		
		return tableName;
		
	}
	/*得到序列号*/
	private String getSequence(String plan_yy,String base_yymm,String pay_type) {
		String  result = "";
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select  case  when (to_number(nvl(max(t.seq_id),0))+1)<10 then '0'||to_char((to_number(nvl(max(t.seq_id),0))+1))  else to_char((to_number(nvl(max(t.seq_id),0))+1)) end  as nexseq from py_pay_plan_detail t where t.plan_yy='"+plan_yy+"' and t.base_yymm='"+base_yymm+"' and t.pay_type='"+pay_type+"'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得版本号时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}
	/*得到人件费计划的下一个编号*/
	private String getVersionSeq(String plan_yy,String base_yymm) {
		String  result = "";
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select  case  when (to_number(nvl(max(t.seq_id),0))+1)<10 then '0'||to_char((to_number(nvl(max(t.seq_id),0))+1))  else to_char((to_number(nvl(max(t.seq_id),0))+1)) end  as nexseq from py_pay_plan_detail_cc t where t.plan_yy='"+plan_yy+"' and t.base_yymm='"+base_yymm+"'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得版本号时出错！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}


public List getVesionNote(String pay_type) {
	List list = new ArrayList();
	Connection conn = ConnBean.getConn();
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "select distinct t.seq_id,t.versionnote from py_pay_plan_detail t where  t.pay_type = '"+pay_type+"'";
	Logger.getLogger(getClass()).debug(sql);
	System.out.println(sql);
	try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			SimpleMap sm = new SimpleMap();
			sm.set("seqid", rs.getString("seq_id"));
			sm.set("versionnote", rs.getString("versionnote"));
			list.add(sm);
		
		}
		
	} catch (Exception e) {
		Logger.getLogger(getClass()).debug(e.toString());
		throw new GlRuntimeException("得到版本信息时出错！", e);
	} finally {
		SqlUtil.close(rs, stmt, conn);
	}
	return list;
}
}


