package com.ait.ga.cmd.visit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.api.resultApi.SMSAPI;
import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.ga.bean.VoitureBean;
import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;



/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class BookingVoitureCmd extends ArAbstractCommand {
	private ExpressApplyAndAffirmServices affirmServices = null;
	
	public BookingVoitureCmd(){
		affirmServices = new ExpressApplyAndAffirmServices();
	}
    private Mail mail = new Mail() ;
	
	private GaAffirm gaAffirm = new GaAffirm();
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		String content = request.getParameter("content");
		if(content.equals("bookVoiture") && content!=null){//得到已确认的申请信息
			List list=this.getApplyVoiture(request,admin);
			List list2=this.getDriverInfo(request,admin);
			request.setAttribute("getAllAffirm", list);
			request.setAttribute("getDriverInfo", list2);
			return "/ga_booking_voiture.jsp?menu_code="
				+ request.getParameter("menu_code");
		}else if(content.equals("booking") && content!=null){//派车操作
			this.bookingVoiture(request,adminid);
			List list=this.getApplyVoiture(request,admin);
			List list2=this.getDriverInfo(request,admin);
			request.setAttribute("getAllAffirm", list);
			request.setAttribute("getDriverInfo", list2);
			return "/ga_booking_voiture.jsp";			
		}else if(content.equals("updateBookingVoiture") && content!=null){
			if(!request.getParameter("isdelete").equals("") && request.getParameter("isdelete")!=null){
				this.deleteBookingVoiture(request);
				
			}else{
			 this.updateBookingVoiture(request,adminid);
			}
			
			List list=this.getApplyVoiture(request,admin);
			request.setAttribute("getAllAffirm", list);
			return "/ga_booking_voiture.jsp";
			
		}else if(content.equals("view") && content!=null){
			this.getView(request,admin);
			return "/ga_voiture_viewDetail.jsp";
		}
		else{
			return "/error.jsp";
		}
		
		
	}
	/*得到车辆申请信息，是已决裁过的*/
	public List getApplyVoiture(HttpServletRequest request,AdminBean admin){
		List result = new ArrayList();
		SimpleMap parameterObject = new SimpleMap();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
		String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
		String key = request.getParameter("key")!=null?request.getParameter("key"):"";
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId"):admin.getCpnyId();
		StringBuffer sql = new StringBuffer();
		 
		 sql.append("select distinct apply.VOITURE_APPLYID,to_char(apply.CREATE_DATE,'YYYY-MM-DD  HH24:MI:SS') as CREATE_DATE, e.chinesename as CHINESENAME, d.deptname as APPLYER_DEPTNAME,to_char(APPLY_DATE,'YYYY-MM-DD') as APPLY_DATE,APPLY_DATE,apply.APPLY_STARTTIME,to_char(apply_enddate,'YYYY-MM-DD') as apply_enddate,");
		 sql.append(" apply.APPLY_ENDTIME, apply.APPLY_USERSCOUNT,apply.departures, apply.destinations, apply.KILOMETER, apply.LARDER,apply.PURPOSE,apply.DRIVERFLAG, ");
		 sql.append(" apply.MODIFY_FLAG,apply.CONFIRMIDEA");
		 sql.append(" from GA_VOITURE_APPLY apply,hr_employee e,hr_department d");
		 sql.append(" where apply.active='1' and apply.applyer_id=e.person_id and apply.CREATE_DATE is not null" );
		 sql.append(" and d.DEPTID=apply.applyer_deptname AND e.cpny_id='"+cpnyId+"'");
		 boolean falg = true;
		 if(!startDate.equals("")){
			 sql.append(" and apply.CREATE_DATE >= to_date('"+ startDate +"','YYYY-MM-DD')");
			 falg = false;
		 }
		if(!endDate.equals("")){
			sql.append(" and apply.CREATE_DATE <= to_date('"+ endDate +"','YYYY-MM-DD')+1");
		}
		if(falg){
			 sql.append(" and apply.CREATE_DATE>=to_date(to_char(SYSDATE-7,'yyyy-mm-dd'),'yyyy-mm-dd')");
		}
		if(!qryType.equals("")){
			sql.append(" and apply.MODIFY_FLAG = '"+ qryType +"'");
		}
		if(!deptid.equals("")){
			sql.append(" and t.APPLYER_DEPTNAME = '"+ deptid +"'");
		}
		if(!key.equals("")){
			sql.append(" and (e.chinesename LIKE  '%"+ key +"%'  or apply.APPLYER_ID LIKE  '%"+ key +"%')");
		}
		sql.append(" order by apply.voiture_applyid desc");
		Logger.getLogger(getClass()).debug(sql);
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			
			// bind parameter
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()){
				VoitureBean vb= new VoitureBean();
				SimpleMap parameterObject1 = new SimpleMap();
				parameterObject1.put("APPLY_NO",rs.getString("VOITURE_APPLYID"));
				parameterObject1.put("CHINESENAME",rs.getString("CHINESENAME"));
				parameterObject1.put("CREATE_DATE",rs.getString("CREATE_DATE"));
				parameterObject1.put("APPLYER_DEPTNAME",rs.getString("APPLYER_DEPTNAME"));
				parameterObject1.put("APPLY_DATE",rs.getString("apply_date"));
				parameterObject1.put("APPLY_ENDDATE",rs.getString("apply_enddate"));
				parameterObject1.put("APPLY_STARTTIME",rs.getString("apply_starttime"));
				parameterObject1.put("APPLY_ENDTIME",rs.getString("apply_endtime"));
				parameterObject1.put("APPLY_USERSCOUNT",rs.getString("APPLY_USERSCOUNT"));
				parameterObject1.put("DEPARTURES",rs.getString("DEPARTURES"));
				parameterObject1.put("DESTINATIONS",rs.getString("DESTINATIONS"));
				parameterObject1.put("KILOMETER",rs.getString("KILOMETER"));
				parameterObject1.put("LARDER",rs.getString("LARDER"));
				parameterObject1.put("PURPOSE",rs.getString("PURPOSE"));
				parameterObject1.put("DRIVERFLAG",rs.getString("DRIVERFLAG"));
				parameterObject1.put("MODIFY_FLAG",rs.getString("MODIFY_FLAG"));
				parameterObject1.put("CONFIRMIDEA",rs.getString("CONFIRMIDEA"));
				String sql2 = "select * from ga_booking_voiture r where r.applyno='"+ rs.getString("VOITURE_APPLYID")+"'";
				String sql3 = "select T.DISTINCTION,to_char(APPLY_DATE,'YYYY-MM-DD') as APPLY_DATE,T.APPLY_STARTTIME,to_char(T.APPLY_ENDDATE,'YYYY-MM-DD') as APPLY_ENDDATE,T.APPLY_ENDTIME,T.CONTENT,T.DRIVE_WAY,T.NOTE from ga_voiture_apply_distinction t where t.apply_no='"+ rs.getString("VOITURE_APPLYID")+"'" ;
				 
				stmt1 = conn.createStatement();
				rs1 = stmt1.executeQuery(sql2);
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery(sql3);
				
				String VEHICLE="";
				String DRIVER="";
				String EMPNAME="";
				String DRIVER_PHONE="";
				while(rs1.next()){
					if(rs1.getString("VEHICLE")!=null){
						VEHICLE += rs1.getString("VEHICLE")+",";
					}
					if(rs1.getString("DRIVER")!=null){
						DRIVER += rs1.getString("DRIVER")+",";
					}
					if(rs1.getString("EMPNAME")!=null){
						EMPNAME += rs1.getString("EMPNAME")+",";
					}
					if(rs1.getString("DRIVER_PHONE")!=null){
						DRIVER_PHONE += rs1.getString("DRIVER_PHONE")+",";
					}
				}
				if(!VEHICLE.equals("")){
					parameterObject1.put("VEHICLE",VEHICLE.substring(0, VEHICLE.length()-1));
				}
				if(!DRIVER.equals("")){
				parameterObject1.put("DRIVER",DRIVER.substring(0, DRIVER.length()-1));
				}
				if(!EMPNAME.equals("")){
				parameterObject1.put("EMPNAME",EMPNAME.substring(0,EMPNAME.length()-1));
				}
				if(!DRIVER_PHONE.equals("")){
					parameterObject1.put("DRIVER_PHONE",DRIVER_PHONE.substring(0,DRIVER_PHONE.length()-1));
				}
				
				String DISTINCTION="";
				String APPLY_DATE="";
				String APPLY_STARTTIME="";
				String APPLY_ENDDATE="";
				String APPLY_ENDTIME="";
				String CONTENT="";
				String DRIVE_WAY="";
				String NOTE="";
				while(rs2.next()){
					if(rs2.getString("DISTINCTION")!=null){
						DISTINCTION += rs2.getString("DISTINCTION")+",";
					}
					if(rs2.getString("APPLY_DATE")!=null){
						APPLY_DATE += rs2.getString("APPLY_DATE")+",";
					}
					if(rs2.getString("APPLY_STARTTIME")!=null){
						APPLY_STARTTIME += rs2.getString("APPLY_STARTTIME")+",";
					}
					if(rs2.getString("APPLY_ENDDATE")!=null){
						APPLY_ENDDATE += rs2.getString("APPLY_ENDDATE")+",";
					}
					if(rs2.getString("APPLY_ENDTIME")!=null){
						APPLY_ENDTIME += rs2.getString("APPLY_ENDTIME")+",";
					}
					if(rs2.getString("CONTENT")!=null){
						CONTENT += rs2.getString("CONTENT")+",";
					}
					if(rs2.getString("DRIVE_WAY")!=null){
						DRIVE_WAY += rs2.getString("DRIVE_WAY")+",";
					}
					if(rs2.getString("NOTE")!=null){
						NOTE += rs2.getString("NOTE")+",";
					}
				}
				
					if(!DISTINCTION.equals("")){
						DISTINCTION = DISTINCTION.substring(0, DISTINCTION.length()-1);
						String[] DISTINCTION1=DISTINCTION.split(",");
						 for(int i=0;i<DISTINCTION1.length;i++)  
						  {  
							 parameterObject1.put("DISTINCTION"+i,DISTINCTION1[i]);
						  }  
						
					}
					if(!APPLY_DATE.equals("")){
						APPLY_DATE=APPLY_DATE.substring(0,APPLY_DATE.length()-1);
						String []APPLY_DATE1=APPLY_DATE.split(",");
						for(int i=0;i<APPLY_DATE1.length;i++){
							parameterObject1.put("APPLY_DATE"+i,APPLY_DATE1[i]);
						}
					}
					if(!APPLY_STARTTIME.equals("")){
						APPLY_STARTTIME=APPLY_STARTTIME.substring(0, APPLY_STARTTIME.length()-1);
						String []APPLY_STARTTIME1=APPLY_STARTTIME.split(",");
						for(int i=0;i<APPLY_STARTTIME1.length;i++){
							parameterObject1.put("APPLY_STARTTIME"+i,APPLY_STARTTIME1[i]);
						}
						
					}
					if(!APPLY_ENDDATE.equals("")){
						APPLY_ENDDATE=APPLY_ENDDATE.substring(0,APPLY_ENDDATE.length()-1);
						String []APPLY_ENDDATE1=APPLY_ENDDATE.split(",");
						for(int i=0;i<APPLY_ENDDATE1.length;i++){
							parameterObject1.put("APPLY_ENDDATE"+i,APPLY_ENDDATE1[i]);
						}
						
					}
					if(!APPLY_ENDTIME.equals("")){
						APPLY_ENDTIME=APPLY_ENDTIME.substring(0, APPLY_ENDTIME.length()-1);
						String []APPLY_ENDTIME1=APPLY_ENDTIME.split(",");
						for(int i=0;i<APPLY_ENDTIME1.length;i++){
							parameterObject1.put("APPLY_ENDTIME"+i,APPLY_ENDTIME1[i]);
						}
					}
					if(!CONTENT.equals("")){
						CONTENT=CONTENT.substring(0,CONTENT.length()-1);
						String []CONTENT1=CONTENT.split(",");
						for(int i=0;i<CONTENT1.length;i++){
							parameterObject1.put("CONTENT"+i,CONTENT1[i]);
						}
					}
					if(!DRIVE_WAY.equals("")){
						DRIVE_WAY=DRIVE_WAY.substring(0, DRIVE_WAY.length()-1);
						String []DRIVE_WAY1=DRIVE_WAY.split(",");
						for(int i=0;i<DRIVE_WAY1.length;i++){
							parameterObject1.put("DRIVE_WAY"+i,DRIVE_WAY1[i]);
						}
					}
					if(!NOTE.equals("")){
						NOTE=NOTE.substring(0,NOTE.length()-1);
						String []NOTE1=NOTE.split(",");
						for(int i=0;i<NOTE1.length;i++){
							parameterObject1.put("NOTE"+i,NOTE1[i]);
						}
					}
				result.add(parameterObject1);
			}
//			GA_VOITURE
			parameterObject.put("cpny_id", cpnyId);

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("voitureInt",result.size());
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("cpnyId",cpnyId);
			request.setAttribute("key", key);
			
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs1, stmt1);
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
		
	}
	/*得到司机的所有信息*/
	public List getDriverInfo(HttpServletRequest request,AdminBean admin){
		List result = new ArrayList();
		SimpleMap parameterObject = new SimpleMap();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId"):admin.getCpnyId();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select distinct DRIVER_ID,DRIVER_NAME,DRIVER_CARD_NUM,DRIVER_PHONE");
		sql.append(" from DRIVER_INFO where cpny_id='"+cpnyId+"' order by DRIVER_ID "); 
		Logger.getLogger(getClass()).debug(sql);
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
			.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			
			// bind parameter
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()){
				VoitureBean vb= new VoitureBean();
				SimpleMap parameterObject1 = new SimpleMap();
				parameterObject1.put("DRIVER_NAME",rs.getString("DRIVER_NAME"));
				parameterObject1.put("DRIVER_CARD_NUM",rs.getString("DRIVER_CARD_NUM"));
				parameterObject1.put("DRIVER_PHONE",rs.getString("DRIVER_PHONE"));
				
				
				result.add(parameterObject1);
			}
//			GA_VOITURE
			parameterObject.put("cpny_id", cpnyId);
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("voitureInt",result.size());
			request.setAttribute("cpnyId",cpnyId);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs1, stmt1);
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
		
	}
	/*得到车辆信息，在层样式里面用的*/
	public List getVoitureList(String condition,String date,String date1,String cpny_id){
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql="select VOITURE_ID, Seats, voiture_Brand, voiture_Model, voiture_Number"+
  " from GA_VOITURE e"+
          
 " where e.VOITURE_STATE = 'voitureState1'  "+
 "  and e.voiture_id not in"+
 " (select  t.vehicle from ga_booking_voiture t "+
 "  where  ((to_char(t.booking_date, 'YYYY-MM-DD') || t.actual_start_time)>='"+ date1 +"' and (to_char(t.booking_END_date, 'YYYY-MM-DD') || t.actual_end_time)<='"+ date1 +"' )"+
 "  or   "+ 
 "  ((to_char(t.booking_date, 'YYYY-MM-DD') || t.actual_start_time)>='"+ date +"' and (to_char(t.booking_END_date, 'YYYY-MM-DD') || t.actual_end_time)<='"+ date +"' )"+
 "  )"+
 "      and e.cpny_id = '"+ cpny_id +"'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				VoitureBean vb= new VoitureBean();
				vb.setVOITURE_ID(rs.getInt("voiture_id"));
				vb.setSeats(rs.getString("seats"));
				vb.setVoiture_Brand(rs.getString("voiture_Brand"));
				vb.setVoiture_Model(rs.getString("voiture_Model"));
				vb.setVoiture_Number(rs.getString("voiture_Number"));
				result.add(vb);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
		
	}
	/*增加派车信息*/
	public void bookingVoiture(HttpServletRequest request,String adminid){
		String temp =request.getParameter("temp");		
	    Connection conn = ConnBean.getConn();
	    String falg = request.getParameter("falg");
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		String sql1="update ga_voiture_apply set MODIFY_FLAG=?,CONFIRMIDEA=?,CONFIRMORID=? where VOITURE_APPLYID=?";
		int rs = 0;
		String[] str = request.getParameter("empIds"+temp).split(",");
		String[] str1 = request.getParameter("carName"+temp).split(",");
		String sql="insert into GA_BOOKING_VOITURE(APPLYNO,BOOKING_DATE,ACTUAL_END_TIME,ACTUAL_START_TIME,OPERATOR_DATE, OPERATOR, VEHICLE, DRIVER, EMPNAME,BOOKING_END_DATE,DRIVER_PHONE,ID) values(" +
				" ?,to_date(?,'yyyy-mm-dd'),?,?,SYSDATE,?,?,?,?,to_date(?,'YYYY-MM-DD'),?,GA_BOOKING_VOITURE_SEQ.NEXTVAL)";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn.setAutoCommit(false);
			stmt1 = conn.prepareStatement(sql1);
			stmt1.setString(1, falg);
			stmt1.setString(2, request.getParameter("affirmorIdea_"+temp));
			stmt1.setString(3, adminid);
			stmt1.setString(4, request.getParameter("applyno"+temp));
			
			stmt1.executeUpdate();
			if(!falg.equals("2")){
				for(int i=0;i<str.length;i++){
			   stmt = conn.prepareStatement(sql);			
				stmt.setString(1, request.getParameter("applyno"+temp));
				stmt.setString(2, request.getParameter("APPLY_DATE"+temp));
				stmt.setString(3, request.getParameter("APPLY_ENDTIME"+temp));
				stmt.setString(4, request.getParameter("APPLY_STARTTIME"+temp));
				stmt.setString(5, adminid);
				stmt.setString(6, str[i]);
				stmt.setString(7, request.getParameter("driver"+temp));
				stmt.setString(8, str1[i]);
				stmt.setString(9, request.getParameter("APPLY_ENDDATE"+temp));
				stmt.setString(10, request.getParameter("driver_phone"+temp));
			    rs = stmt.executeUpdate();
				}
				request.setAttribute("errorMsg", "派车成功！");

//			this.updateVoitureStatus(request.getParameter("empIds"+temp));
			
			}else{
				request.setAttribute("errorMsg", "否决成功！");
			}
			conn.commit();

			//派车成功 短信通知
			if(!falg.equals("2")){
				SMSAPI.send(request.getParameter("applyno"+temp));
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "派车失败！");
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt1);
			SqlUtil.close(stmt, conn);
		}
	}
	/*修改派车信息*/
	public void updateBookingVoiture(HttpServletRequest request,String adminid){
		String temp =request.getParameter("temp");		
	    Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		int rs = 0;
		
		String sql="update GA_BOOKING_VOITURE set BOOKING_DATE=to_date(?,'yyyy-mm-dd'),ACTUAL_END_TIME=?,ACTUAL_START_TIME=?,OPERATOR_DATE=SYSDATE, OPERATOR=?, VEHICLE=?,DRIVER=?,DRIVER_PHONE=?" +
				" where APPLYNO=?";
		String sql1 = "update ga_voiture_apply set APPLY_DATE=to_date(?,'YYYY-MM-DD'),APPLY_STARTTIME=?,APPLY_ENDDATE=to_date(?,'YYYY-MM-DD'),APPLY_ENDTIME=?,APPLY_USERSCOUNT=?," +
				"DRIVERFLAG=? where VOITURE_APPLYID=?";
		Logger.getLogger(getClass()).debug(sql);
		try {
				stmt1 = conn.prepareStatement(sql1);
				stmt1.setString(1, request.getParameter("APPLY_DATE"+temp));
				stmt1.setString(2, request.getParameter("APPLY_STARTTIME"+temp));
				stmt1.setString(3, request.getParameter("APPLY_ENDDATE"+temp));
				stmt1.setString(4, request.getParameter("APPLY_ENDTIME"+temp));
				stmt1.setString(5, request.getParameter("APPLY_USERSCOUNT"+temp));
				stmt1.setString(6, request.getParameter("DRIVERFLAG"+temp));
				stmt1.setString(7, request.getParameter("applyno"+temp));
				stmt1.executeUpdate();
			    stmt = conn.prepareStatement(sql);				
				stmt.setString(1, request.getParameter("APPLY_DATE"+temp));
				stmt.setString(2, request.getParameter("APPLY_ENDTIME"+temp));
				stmt.setString(3, request.getParameter("APPLY_STARTTIME"+temp));
				stmt.setString(4, adminid);
				stmt.setString(5, request.getParameter("vehicle"+temp));
				stmt.setString(6, request.getParameter("driver"+temp));
				stmt.setString(7, request.getParameter("driver_phone"+temp));
				stmt.setString(8, request.getParameter("applyno"+temp));
				
			    rs = stmt.executeUpdate();
			    
			this.updateVoitureStatus(request.getParameter("empIds"+temp));
		} catch (Exception e) {
			request.setAttribute("errorMsg", "派车修改失败！");
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt1);
			SqlUtil.close(stmt, conn);
		}
		request.setAttribute("errorMsg", "派车修改成功！");
	}
	
	/*派车的同时进行更新车辆状况*/
	public void updateVoitureStatus(String voiture){
		String[]voitureno=voiture.split(",");	
	    Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
		
		String sql="update ga_voiture set VOITURE_USERSTATE='voitureUseState2' where VOITURE_ID=?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			 stmt = conn.prepareStatement(sql);	
			for(int i=0;i<voitureno.length;i++){
				stmt.setString(1,voitureno[i]);
			    rs += stmt.executeUpdate();
			   } 
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}	
	}
	
	/*显示是否能进行派车操作*/
	public boolean isViewOperation(String voitureno){		
	    Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean temp = true;
		String sql="select * from ga_booking_voiture t where t.applyno='"+voitureno+"'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			 stmt = conn.prepareStatement(sql);				
			    rs=stmt.executeQuery();
			    if(rs.next()){
			    	temp = false;
			    }			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}	
		return temp;
	}	
	/*当派车信息为空时清楚所派车辆的信息*/
	public void deleteBookingVoiture(HttpServletRequest request){
		String temp =request.getParameter("temp");		
	    Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
		
		String sql="delete GA_BOOKING_VOITURE where APPLYNO=?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			    stmt = conn.prepareStatement(sql);				
		        stmt.setString(1, request.getParameter("applyno"+temp));
			    rs = stmt.executeUpdate();			    
			
		} catch (Exception e) {
			request.setAttribute("errorMsg", "清除派车信息失败！");
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}
		request.setAttribute("errorMsg", "清除派车信息成功！");
	}
	public void getView(HttpServletRequest request,AdminBean admin){
		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String sql = "select e.VOITURE_ID,y.destinations,y.larder,Seats,voiture_Brand,voiture_Model,voiture_Number,to_char(g.booking_date,'YYYY-MM-DD')||' '||g.actual_start_time as star,"+
        " to_char(g.booking_end_date,'YYYY-MM-DD')||' '||g.actual_end_time as enddate from GA_VOITURE e,ga_booking_voiture g,GA_VOITURE_APPLY y where e.VOITURE_STATE <> 'voitureState3'"+
        "  and g.vehicle = to_char(e.voiture_id) and y.voiture_applyid = g.applyno and e.cpny_id='"+ admin.getCpnyId()+"'" +
        		" and to_date(to_char(g.booking_end_date,'YYYY-MM-DD')||g.actual_end_time,'YYYY-MM-DDHH24:MI:SS')>sysdate order by g.booking_date,g.actual_start_time";
		Logger.getLogger(getClass()).debug(sql);
		try {
			 stmt = conn.prepareStatement(sql);				
			    rs=stmt.executeQuery();
			    while(rs.next()){
			    	SimpleMap simpleMap = new SimpleMap();
			    	simpleMap.put("VOITURE_ID", rs.getString("VOITURE_ID"));
			    	simpleMap.put("Seats", rs.getString("SEATS"));
			    	simpleMap.put("voiture_Brand", rs.getString("VOITURE_BRAND"));
			    	simpleMap.put("voiture_Model", rs.getString("VOITURE_MODEL"));
			    	simpleMap.put("voiture_Number", rs.getString("VOITURE_NUMBER"));
			    	simpleMap.put("star", rs.getString("star"));
			    	simpleMap.put("enddate", rs.getString("enddate"));
			    	simpleMap.put("DESTINATIONS", rs.getString("DESTINATIONS"));
			    	simpleMap.put("LARDER", rs.getString("LARDER"));
			    	list.add(simpleMap);
			    }
			    request.setAttribute("list", list);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}	
	}
	
}
