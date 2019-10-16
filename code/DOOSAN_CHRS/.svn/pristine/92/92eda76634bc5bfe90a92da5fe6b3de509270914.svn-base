package com.ait.ga.cmd.visit;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.DriverBean;
import com.ait.ga.bean.VoitureBean;
import com.ait.ga.business.GaServices;
import com.ait.ga.dao.GaDAOFactory;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class VoitureManger extends ArAbstractCommand {
	private static final Logger logger = Logger.getLogger(GaDAOFactory.class);
	String getallvoiture=null;
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		String cpny_id = admin.getCpnyId();
		SimpleMap mp3 = ObjectBindUtil.getData(request);
		
		if(content.equals("addVoiture") && content!=null){	//增加和修改车辆信息				
			this.addVoiture(request,cpny_id);
			List allVoitureList=this.getAllVoiture(cpny_id);			
			request.setAttribute("allVoitureList", allVoitureList);
			return "/ga_voiture_manger.jsp";
			
		}else if(content.equals("addDriver") && content!=null){//增加和修改司机信息
			this.addDriver(request,cpny_id);
			List allDriverList=this.getAllDriver(cpny_id);			
			request.setAttribute("allDriverList", allDriverList);
			return "/ga_driverManger.jsp";
			
		}else if(content.equals("driverMangerSave") && content!=null){
			String sign = request.getParameter("sign");
			this.updateDriver(request, sign);	
			List allDriverList=this.getAllDriver(cpny_id);			
			request.setAttribute("allDriverList", allDriverList);
			return "/ga_driverManger.jsp";
		}else if(content.equals("voitureMangerSave") && content!=null){
			String sign = request.getParameter("sign");
			this.updateVoiture(request, sign);	
			List allVoitureList=this.getAllVoiture(cpny_id);			
			request.setAttribute("allVoitureList", allVoitureList);
			return "/ga_voiture_manger.jsp";
		}else if(content.equals("voitureManger") && content!=null){//得到车辆的所有信息,为未报废的车辆
			getallvoiture=request.getParameter("getallvoiture")!=null?request.getParameter("getallvoiture"):"";			
			List allVoitureList=this.getAllVoiture(cpny_id);			
			request.setAttribute("allVoitureList", allVoitureList);
			return "/ga_voiture_manger.jsp";			
		}else if(content.equals("driverManger") && content!=null){//得到司机的所有信息,为未报废的车辆
			getallvoiture=request.getParameter("getallvoiture")!=null?request.getParameter("getallvoiture"):"";			
			List allDriverList=this.getAllDriver(cpny_id);			
			request.setAttribute("allDriverList", allDriverList);
			return "/ga_driverManger.jsp";			
		}else if(content.equals("MaintenanceCostsManger") && content!=null){//维修费用管理
			List allVoitureList=this.getAllVoitureConts(cpny_id);			
			request.setAttribute("allVoitureList", allVoitureList);
			return "/ga_maintenanceCosts_manger.jsp";
		}else if(content.equals("AddMaintenanceCosts") && content!=null){//增加车辆费用信息
			this.addMaintenanceCosts(request, adminid);
			List allVoitureList=this.getAllVoitureConts(cpny_id);			
			request.setAttribute("allVoitureList", allVoitureList);
			return "/ga_maintenanceCosts_manger.jsp";
			
		}else if(content.equals("updateMaintenanceCosts") && content!=null){//修改车辆费用信息
			this.UpdateMaintenanceCosts(request, adminid);
			List allVoitureList=this.getAllVoitureConts(cpny_id);			
			request.setAttribute("allVoitureList", allVoitureList);
			return "/ga_maintenanceCosts_manger.jsp";
			
		}else if(content.equals("updateVoitureSate") && content!=null){//修改车辆状态
			String sign = request.getParameter("sign");
			this.updateVoitureState(request, sign);
			List allVoitureList=this.getAllVoiture(cpny_id);			
			request.setAttribute("allVoitureList", allVoitureList);
			return "/ga_voiture_manger.jsp";
			
		}else if(content.equals("troubleManger") && content!=null){
			this.troubleManger(request, admin);
			return "/ga_trouble_manger.jsp?menu_code="+mp3.getString("menu_code");
		}else if(content.equals("AddvoitureManger") && content!=null){
			request.setAttribute("id","TROUBLE"+ this.getSequence("GA_TROUBLE_MANGER_SEQ"));
			return "/ga_add_trouble.jsp?menu_code="+mp3.getString("menu_code");
		}
		else if(content.equals("AddMaintenance") && content!=null){
			this.AddMaintenance(request, admin);
			this.troubleManger(request, admin);
			return "/ga_trouble_manger.jsp?menu_code="+mp3.getString("menu_code");
		}else if(content.equals("UpdatevoitureManger") && content!=null){
			this.UpdatevoitureManger(request, admin);
			return "/ga_edit_trouble.jsp?menu_code="+mp3.getString("menu_code");
		}else if(content.equals("UpdatesavevoitureManger") && content!=null){
			this.UpdateSavevoitureManger(request, admin);
			this.troubleManger(request, admin);
			return "/ga_trouble_manger.jsp?menu_code="+mp3.getString("menu_code");
		}else if(content.equals("ViewvoitureManger") && content!=null){
			this.UpdatevoitureManger(request, admin);
			return "/ga_ViewTrouble_manger.jsp?menu_code="+mp3.getString("menu_code");
		}else if(content.equals("uploadVisiterDocuments")){
			this.uploadVisiterDocuments(request);	
			return "/ga_visiter_upload.jsp?Directive=CloseWindow";
		}
		
		else{
			return "/error.jsp";
		}		
		
	}
	/*得到车辆信息*/
	public List getAllVoiture(String cpny_id){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String sql;
        if(getallvoiture!=null && !getallvoiture.equals("")){
         sql = "select GO_KILL,GA_VOITURE.VOITURE_ID,GA_VOITURE.seats, GA_VOITURE.voiture_Number, GA_VOITURE.voiture_state,GA_VOITURE.voiture_Brand,GA_VOITURE.voiture_Model, GA_VOITURE.VOITURE_USERSTATE,to_char(GA_VOITURE.PURCHASE_DATE,'YYYY-MM-DD') as PURCHASE_DATE " +
			
			 "from GA_VOITURE GA_VOITURE " + " where cpny_id=" +cpny_id+
			 " order by GA_VOITURE.voiture_Brand" ;	
        	
        }else{
		 sql = "select GO_KILL,GA_VOITURE.VOITURE_ID,GA_VOITURE.seats, GA_VOITURE.voiture_Number, GA_VOITURE.voiture_state,GA_VOITURE.voiture_Brand,GA_VOITURE.voiture_Model, GA_VOITURE.VOITURE_USERSTATE,to_char(GA_VOITURE.PURCHASE_DATE,'YYYY-MM-DD') as PURCHASE_DATE " +
				
				"from GA_VOITURE GA_VOITURE " + 
				"where cpny_id=" +cpny_id+" order by GA_VOITURE.voiture_Brand" ;
        }		

		try {
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				VoitureBean  voitureBean = new VoitureBean();
				voitureBean.setVOITURE_ID(rs.getInt("VOITURE_ID"));
				voitureBean.setSeats(rs.getString("seats"));
				voitureBean.setVoiture_Number(rs.getString("voiture_Number"));
				voitureBean.setVOITURE_STATE(rs.getString("voiture_state"));
				voitureBean.setVoiture_Brand(rs.getString("voiture_Brand"));
				voitureBean.setVoiture_Model(rs.getString("voiture_Model"));
				voitureBean.setVOITURE_USERSTATE(rs.getString("voiture_userstate"));
				voitureBean.setPurchase_Date(rs.getString("PURCHASE_DATE")!=null?rs.getString("PURCHASE_DATE"):"");
				voitureBean.setGO_KILL(rs.getString("GO_KILL")!=null?rs.getString("GO_KILL"):"");
				list.add(voitureBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
		
	}
	/*得到司机信息*/
	public List getAllDriver(String cpny_id){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String sql;
		if(getallvoiture!=null && !getallvoiture.equals("")){
			sql = "select DRIVER_ID,DRIVER_NAME,DRIVER_CARD_NUM, DRIVER_PHONE " +
			
			"from DRIVER_INFO " + " where cpny_id=" +cpny_id+
			" order by DRIVER_ID" ;	
			
		}else{
sql = "select DRIVER_ID,DRIVER_NAME,DRIVER_CARD_NUM, DRIVER_PHONE " +
			
			"from DRIVER_INFO " + " where cpny_id=" +cpny_id+
			" order by DRIVER_ID" ;	
		}		
		
		try {
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				DriverBean  driverBean = new DriverBean();
				driverBean.setDRIVER_ID(rs.getInt("DRIVER_ID"));
				driverBean.setDRIVER_NAME(rs.getString("DRIVER_NAME"));
				driverBean.setDRIVER_CARD_NUM(rs.getString("DRIVER_CARD_NUM"));
				driverBean.setDRIVER_PHONE(rs.getString("DRIVER_PHONE"));
				list.add(driverBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
		
	}
	/*得到车辆信息*/
	public List getAllVoitureConts(String cpny_id){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		String sql;
      
         sql = "select GA_VOITURE.VOITURE_ID,GA_VOITURE.seats, GA_VOITURE.voiture_Number, GA_VOITURE.voiture_state,GA_VOITURE.voiture_Brand,GA_VOITURE.voiture_Model, GA_VOITURE.VOITURE_USERSTATE " +
			
			 "from GA_VOITURE GA_VOITURE where cpny_id= " +cpny_id+ " order by GA_VOITURE.voiture_Brand" ;	
      	
		try {
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()){
				VoitureBean  voitureBean = new VoitureBean();
				voitureBean.setVOITURE_ID(rs.getInt("VOITURE_ID"));
				voitureBean.setSeats(rs.getString("seats"));
				voitureBean.setVoiture_Number(rs.getString("voiture_Number"));
				voitureBean.setVOITURE_STATE(rs.getString("voiture_state"));
				voitureBean.setVoiture_Brand(rs.getString("voiture_Brand"));
				voitureBean.setVoiture_Model(rs.getString("voiture_Model"));
				voitureBean.setVOITURE_USERSTATE(rs.getString("voiture_userstate"));				
				list.add(voitureBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
		
	}
	/*添加车辆维护费用信息*/
	public void  addMaintenanceCosts(HttpServletRequest request,String adminid){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		int temp = Integer.valueOf(request.getParameter("temp1"));
		String sql = "insert into  ga_voiture_expense ( EXPENSENO ,CAUSES,TIMING_DATE,AMOUNT,NOTE,CREATE_BY,VOITURENO,UNIT ) values (GA_VOITURE_EXPENSE_SEQ.NEXTVAL,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?)";

		try {
			conn.setAutoCommit(false);
			for(int i=0;i<=temp;i++){
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request.getParameter("CAUSES"+i));
			pstmt.setString(2, request.getParameter("TIMING_DATE"+i));
			pstmt.setString(3, request.getParameter("AMOUNT"+i));
			pstmt.setString(4, request.getParameter("NOTE"+i));
			pstmt.setString(5, adminid);
			pstmt.setString(6, request.getParameter("sign"));
			pstmt.setString(7, request.getParameter("UNIT"+i));
			pstmt.executeUpdate();	
			}
			conn.commit();
		} catch (Exception e) {	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "添加履历失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
		}	
		request.setAttribute("errorMsg", "添加履历成功！");
	}
	/*修改车辆维护费用信息*/
	public void  UpdateMaintenanceCosts(HttpServletRequest request,String adminid){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		PreparedStatement pstmt1 = null;	
        int temp = Integer.valueOf(request.getParameter("temp1"));

        String sql = "insert into  ga_voiture_expense ( EXPENSENO ,CAUSES,TIMING_DATE,AMOUNT,NOTE,CREATE_BY,VOITURENO,UNIT ) values (GA_VOITURE_EXPENSE_SEQ.NEXTVAL,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?)";
		String sql1="delete ga_voiture_expense where VOITURENO = ?";

		try {
			conn.setAutoCommit(false);
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, request.getParameter("sign"));
			pstmt1.executeUpdate();	
			for(int i=0;i<temp;i++){
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request.getParameter("CAUSES"+i));
			pstmt.setString(2, request.getParameter("TIMING_DATE"+i));
			pstmt.setString(3, request.getParameter("AMOUNT"+i));
			pstmt.setString(4, request.getParameter("NOTE"+i));
			pstmt.setString(5, adminid);
			pstmt.setString(6, request.getParameter("sign"));
			pstmt.setString(7, request.getParameter("UNIT"+i));
			pstmt.executeUpdate();	
			}
			conn.commit();
		} catch (Exception e) {	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "修改履历失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
			SqlUtil.close( pstmt1);
		}	
		request.setAttribute("errorMsg", "修改履历成功！");
	}
	/*得到车辆维护费用信息*/
	public List  getAllMaintenanceCosts(String voitureno){
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();		
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		String sql = "select EXPENSENO ,CAUSES,TIMING_DATE,AMOUNT,NOTE,hr.chinesename,VOITURENO,UNIT from  ga_voiture_expense,hr_employee hr where VOITURENO='"+voitureno+"' and hr.person_id=create_by";

		try {
			pstmt = conn.prepareStatement(sql);				
			rs=pstmt.executeQuery();
			int i=0;
			while(rs.next()){
				VoitureBean  voitureBean = new VoitureBean();
				voitureBean.setEXPENSENO(rs.getInt("expenseno"));
				voitureBean.setCAUSES(rs.getString("causes"));
				voitureBean.setTIMING_DATE(StringUtil.checkNull(rs.getDate("timing_date")).toString());
				voitureBean.setAMOUNT(rs.getString("amount"));
				voitureBean.setNOTE(StringUtil.checkNull(rs.getString("note")));
				voitureBean.setCREATE_BY(rs.getString("chinesename"));
				voitureBean.setUnit(rs.getString("UNIT"));
				voitureBean.setTemp(""+i);
				i++;
				list.add(voitureBean);	
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}		
		return list;
	}
	/*增加信息*/
	public void  addVoiture(HttpServletRequest request , String cpny_id){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = "insert into  GA_VOITURE ( VOITURE_ID ,VOITURE_STATE,VOITURE_USERSTATE,PURCHASER,CAPACITY,SEATS,VOITURE_BRAND,VOITURE_MODEL,VOITURE_NUMBER,PURCHASE_DATE," +
				"AS_DURATION,RENT_DATE,STAT_KILOMETER,STAT_OIL,RENT_TIME,DRIVER_NAME,EMPID,JOB_CONTRACT,CARD,DRIVER_CARD,GO_CARD,JOUN_CARD,DRIVER_CARD_TYPE," +
				"PRICE,ADDED_TAX,CUSTOM_TAX,ADDITIONAL_TAX,SIGN_REGISTER,OTHER_EXPENSES,GO_KILL,cpny_id" +
				") values(GA_VOITURE_SEQ.NEXTVAL,'voitureState1','voitureUseState1'" +
				",?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		String sql1 = "insert into GA_VOITURE_B(ID,EXPENSES,INSURANCE_TYPE,INSURANCE_COMPANY,START_DATE,END_DATE,INSURANCE_MENO,TOTAL_INSURANCE_FEE,GA_VOITURE_ID)" +
				" values(GA_VOITURE_B_SEQ.Nextval,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,GA_VOITURE_SEQ.CURRVAL)";
		String sqlc = "insert into GA_VOITUREMAINTENACE(ID,MAINTENACEDATE,NOTE,COST,MAINTENACEDATE1,GA_VOITURE_ID) values (GA_VOITUREMAINTENACE_SEQ.Nextval,to_date(?,'YYYY-MM-DD'),?,?,to_date(?,'YYYY-MM-DD'),GA_VOITURE_SEQ.CURRVAL)";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request.getParameter("PURCHASER"));
			pstmt.setString(2, request.getParameter("CAPACITY"));
			pstmt.setString(3, request.getParameter("SEATS"));
			pstmt.setString(4, request.getParameter("VOITURE_BRAND"));
			pstmt.setString(5, request.getParameter("VOITURE_MODEL"));
			pstmt.setString(6, request.getParameter("VOITURE_NUMBER"));
			pstmt.setString(7, request.getParameter("PURCHASE_DATE"));
			pstmt.setString(8, request.getParameter("AS_DURATION"));
			pstmt.setString(9, request.getParameter("RENT_DATE"));
			pstmt.setString(10, request.getParameter("STAT_KILOMETER"));
			pstmt.setString(11, request.getParameter("STAT_OIL"));
			pstmt.setString(12, request.getParameter("RENT_TIME"));
			pstmt.setString(13, request.getParameter("DRIVER_NAME"));
			pstmt.setString(14, request.getParameter("EMPID"));
			pstmt.setString(15, request.getParameter("JOB_CONTRACT"));
			pstmt.setString(16, request.getParameter("CARD"));
			pstmt.setString(17, request.getParameter("DRIVER_CARD"));
			pstmt.setString(18, request.getParameter("GO_CARD"));
			pstmt.setString(19, request.getParameter("JOUN_CARD"));	
			pstmt.setString(20, request.getParameter("DRIVER_CARD_TYPE"));	
			pstmt.setString(21, request.getParameter("PRICE"));	
			pstmt.setString(22, request.getParameter("ADDED_TAX"));	
			pstmt.setString(23, request.getParameter("CUSTOM_TAX"));	
			pstmt.setString(24, request.getParameter("ADDITIONAL_TAX"));	
			pstmt.setString(25, request.getParameter("SIGN_REGISTER"));	
			pstmt.setString(26, request.getParameter("OTHER_EXPENSES"));
			pstmt.setString(27, request.getParameter("GO_KILL"));
			pstmt.setString(28, cpny_id);
			
			pstmt.executeUpdate();
			int temp = Integer.parseInt(request.getParameter("temp1"))+1;
			for(int i=0;i<temp;i++){
				if(request.getParameter("EXPENSES"+i) != null && !request.getParameter("EXPENSES"+i).equals("")){
					pstmt1 = conn.prepareStatement(sql1);
					pstmt1.setString(1, request.getParameter("EXPENSES"+i));
					pstmt1.setString(2, request.getParameter("INSURANCE_TYPE"+i));
					pstmt1.setString(3, request.getParameter("INSURANCE_COMPANY"+i));
					pstmt1.setString(4, request.getParameter("START_DATE"+i));
					pstmt1.setString(5, request.getParameter("END_DATE"+i));
					pstmt1.setString(6, request.getParameter("INSURANCE_MENO"+i));
					pstmt1.setString(7, request.getParameter("TOTAL_INSURANCE_FEE"+i));
					pstmt1.executeUpdate();			
				}
			}
			int tempx = Integer.parseInt(request.getParameter("tempx")!=null?request.getParameter("tempx"):"0")+1;
			for(int i=0;i<tempx;i++){
				if(request.getParameter("MAINTENACEDATE"+i) != null && !request.getParameter("MAINTENACEDATE"+i).equals("")){
					pstmt2 = conn.prepareStatement(sqlc);
					pstmt2.setString(1, request.getParameter("MAINTENACEDATE"+i));
					pstmt2.setString(2, request.getParameter("NOTE"+i));
					pstmt2.setInt(3, Integer.parseInt(request.getParameter("COST"+i)));
					pstmt2.setString(4, request.getParameter("MAINTENACEDATE1"+i));
					pstmt2.executeUpdate();			
				}
			}
			conn.commit();
			request.setAttribute("errorMsg", "添加车辆成功！");
		} catch (Exception e) {	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "添加车辆失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
			SqlUtil.close( pstmt1);
		}
		
		
	}
	/**
	 * 添加司机信息
	 * @param request
	 * @param cpny_id
	 */
	public void  addDriver(HttpServletRequest request , String cpny_id){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = "insert into  DRIVER_INFO ( DRIVER_ID ,DRIVER_NAME,DRIVER_CARD_NUM,DRIVER_PHONE,CREATEDATE,CPNY_ID" +
		") values(DRIVER_INFO_SEQ.NEXTVAL,?,?,?,sysdate,?) ";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request.getParameter("DRIVER_NAME"));
			pstmt.setString(2, request.getParameter("DRIVER_CARD_NUM"));
			pstmt.setString(3, request.getParameter("DRIVER_PHONE"));
			pstmt.setString(4, cpny_id);
			
			pstmt.executeUpdate();
			int temp = Integer.parseInt(request.getParameter("temp1"))+1;
			
			int tempx = Integer.parseInt(request.getParameter("tempx")!=null?request.getParameter("tempx"):"0")+1;
			
			conn.commit();
			request.setAttribute("errorMsg", "添加司机信息成功！");
		} catch (Exception e) {	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "添加司机信息失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			
		} finally {
			SqlUtil.close( pstmt, conn);
			SqlUtil.close( pstmt1);
		}
		
		
	}
	/*进行车辆信息的修改操作*/
	public void  updateVoiture(HttpServletRequest request,String voitureno){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		String sql = "update GA_VOITURE set PURCHASER=?,CAPACITY=?,SEATS=?,VOITURE_BRAND=?,VOITURE_MODEL=?,VOITURE_NUMBER=?,PURCHASE_DATE=to_date(?,'yyyy-mm-dd') ,AS_DURATION=?," +
				"RENT_DATE=to_date(?,'YYYY-MM-DD') ,STAT_KILOMETER=?,STAT_OIL=?,RENT_TIME=?,DRIVER_NAME=?,EMPID =?,JOB_CONTRACT=?,CARD=?,DRIVER_CARD =?,GO_CARD=?, JOUN_CARD=?, " +
				"DRIVER_CARD_TYPE=?,PRICE=?,ADDED_TAX=?,CUSTOM_TAX=?,ADDITIONAL_TAX=?,SIGN_REGISTER=?,OTHER_EXPENSES=?,GO_KILL=? " +
				" where VOITURE_ID=?";
		String sql1 = "insert into GA_VOITURE_B(ID,EXPENSES,INSURANCE_TYPE,INSURANCE_COMPANY,START_DATE,END_DATE,INSURANCE_MENO,TOTAL_INSURANCE_FEE,GA_VOITURE_ID)" +
		" values(GA_VOITURE_B_SEQ.Nextval,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?)";
		String sql2 = "delete GA_VOITURE_B where GA_VOITURE_ID=?";
		String sqlc = "insert into GA_VOITUREMAINTENACE(ID,MAINTENACEDATE,NOTE,COST,MAINTENACEDATE1,GA_VOITURE_ID) values (GA_VOITUREMAINTENACE_SEQ.Nextval,to_date(?,'YYYY-MM-DD'),?,?,to_date(?,'YYYY-MM-DD'),?)";
		String sqlc1 = "delete ga_voituremaintenace where GA_VOITURE_ID=?";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);	
			
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request.getParameter("PURCHASER"));
			pstmt.setString(2, request.getParameter("CAPACITY"));
			pstmt.setString(3, request.getParameter("SEATS"));
			pstmt.setString(4, request.getParameter("VOITURE_BRAND"));
			pstmt.setString(5, request.getParameter("VOITURE_MODEL"));
			pstmt.setString(6, request.getParameter("VOITURE_NUMBER"));
			pstmt.setString(7, request.getParameter("PURCHASE_DATE"));
			pstmt.setString(8, request.getParameter("AS_DURATION"));
			pstmt.setString(9, request.getParameter("RENT_DATE"));
			pstmt.setString(10, request.getParameter("STAT_KILOMETER"));
			pstmt.setString(11, request.getParameter("STAT_OIL"));
			pstmt.setString(12, request.getParameter("RENT_TIME"));
			pstmt.setString(13, request.getParameter("DRIVER_NAME"));
			pstmt.setString(14, request.getParameter("EMPID"));
			pstmt.setString(15, request.getParameter("JOB_CONTRACT"));
			pstmt.setString(16, request.getParameter("CARD"));
			pstmt.setString(17, request.getParameter("DRIVER_CARD"));
			pstmt.setString(18, request.getParameter("GO_CARD"));
			pstmt.setString(19, request.getParameter("JOUN_CARD"));	
			pstmt.setString(20, request.getParameter("DRIVER_CARD_TYPE"));	
			pstmt.setString(21, request.getParameter("PRICE"));	
			pstmt.setString(22, request.getParameter("ADDED_TAX"));	
			pstmt.setString(23, request.getParameter("CUSTOM_TAX"));	
			pstmt.setString(24, request.getParameter("ADDITIONAL_TAX"));	
			pstmt.setString(25, request.getParameter("SIGN_REGISTER"));	
			pstmt.setString(26, request.getParameter("OTHER_EXPENSES"));
			pstmt.setString(27, request.getParameter("GO_KILL"));
			pstmt.setString(28, voitureno);
			pstmt.executeUpdate();	
			
			pstmt2 = conn.prepareStatement(sql2);	
			pstmt2.setString(1, voitureno);
			pstmt2.executeUpdate();
			int temp1 = Integer.parseInt(request.getParameter("temp1")!=null?request.getParameter("temp1"):"0");
			for(int i=0;i<temp1;i++){
				if(request.getParameter("EXPENSES"+i) != null && !request.getParameter("EXPENSES"+i).equals("")){
					pstmt1 = conn.prepareStatement(sql1);
					pstmt1.setString(1, request.getParameter("EXPENSES"+i));
					pstmt1.setString(2, request.getParameter("INSURANCE_TYPE"+i));
					pstmt1.setString(3, request.getParameter("INSURANCE_COMPANY"+i));
					pstmt1.setString(4, request.getParameter("START_DATE"+i));
					pstmt1.setString(5, request.getParameter("END_DATE"+i));
					pstmt1.setString(6, request.getParameter("INSURANCE_MENO"+i));
					pstmt1.setString(7, request.getParameter("TOTAL_INSURANCE_FEE"+i));
					pstmt1.setString(8, voitureno);
					pstmt1.executeUpdate();			
				}
			}
			pstmt3 = conn.prepareStatement(sqlc1);	
			pstmt3.setString(1, voitureno);
			pstmt3.executeUpdate();
			int tempx = Integer.parseInt(request.getParameter("tempx")!=null?request.getParameter("tempx"):"0");
			for(int i=0;i<tempx;i++){
				if(request.getParameter("MAINTENACEDATE"+i) != null && !request.getParameter("MAINTENACEDATE"+i).equals("")){
					pstmt4 = conn.prepareStatement(sqlc);
					pstmt4.setString(1, request.getParameter("MAINTENACEDATE"+i));
					pstmt4.setString(2, request.getParameter("NOTE"+i));
					pstmt4.setInt(3, Integer.parseInt(request.getParameter("COST"+i)));
					pstmt4.setString(4, request.getParameter("MAINTENACEDATE1"+i));
					pstmt4.setString(5, voitureno);
					pstmt4.executeUpdate();		
				}
					
			}
			conn.commit();
			request.setAttribute("errorMsg", "修改车辆成功！");
		} catch (Exception e) {	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "修改车辆失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
			SqlUtil.close( pstmt1);
			SqlUtil.close( pstmt2);
		}
		
		
	}
	/*进行司机信息的修改操作*/
	public void  updateDriver(HttpServletRequest request,String driverno){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;		
		String sql = "update DRIVER_INFO set DRIVER_NAME=?,DRIVER_CARD_NUM=?,DRIVER_PHONE=? " +
		" where DRIVER_ID=?";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);	
			
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request.getParameter("DRIVER_NAME"));
			pstmt.setString(2, request.getParameter("DRIVER_CARD_NUM"));
			pstmt.setString(3, request.getParameter("DRIVER_PHONE"));
			
			pstmt.setString(4, driverno);
			pstmt.executeUpdate();	
			
			int temp1 = Integer.parseInt(request.getParameter("temp1")!=null?request.getParameter("temp1"):"0");
			conn.commit();
			request.setAttribute("errorMsg", "修改司机信息成功！");
		} catch (Exception e) {	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "修改司机信息失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			
		} finally {
			SqlUtil.close( pstmt, conn);
		}
		
		
	}
	/*修改车辆的状态*/
	public void  updateVoitureState(HttpServletRequest request,String temp){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;		
		String sql = "update GA_VOITURE set VOITURE_STATE=?,VOITURE_USERSTATE=?" +
				" where VOITURE_ID=?";
		String mytempvar=request.getParameter("mytempvar");
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, request.getParameter("VOITURE_STATE"+mytempvar));
			pstmt.setString(2, request.getParameter("VOITURE_USERSTATE"+mytempvar));
			pstmt.setInt(3, Integer.parseInt(temp));
			
			pstmt.executeUpdate();			
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
		}
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
//		this.getAllVoiture(admin.getCpnyId());
	
	}
	/*根据车辆编号，得到车辆的所有信息，已做修改用*/
	public VoitureBean  getAnVoiture(String voitureno){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		VoitureBean  voitureBean = new VoitureBean();
		ResultSet rs= null;
		String sql = "select GO_KILL,Purchaser,Capacity,Seats,voiture_Brand,voiture_Model,voiture_Number,Purchase_Date,AS_Duration,RENT_DATE,STAT_KILOMETER,STAT_OIL,RENT_TIME," +
				"DRIVER_NAME,CARD,JOUN_CARD,GO_CARD,DRIVER_CARD_TYPE,DRIVER_CARD,EMPID,Price,JOB_CONTRACT,Added_Tax,Custom_Tax,Additional_Tax,Sign_Register,Other_Expenses,Insurance_Company,Insurance_Fee,Insurance_Type, Insurance_Ratio" +
				"  from GA_VOITURE where VOITURE_ID=?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(voitureno));			
			rs=pstmt.executeQuery();
			if(rs.next()){
				voitureBean.setPurchaser(rs.getString("purchaser"));
				voitureBean.setCapacity(rs.getString("capacity"));
				voitureBean.setSeats(rs.getString("seats"));
				voitureBean.setVoiture_Brand(rs.getString("voiture_Brand"));
				voitureBean.setVoiture_Model(rs.getString("voiture_Model"));
				voitureBean.setVoiture_Number(rs.getString("voiture_Number"));
				voitureBean.setPurchase_Date(StringUtil.checkNull(rs.getDate("purchase_Date")).toString());
				voitureBean.setAS_Duration(rs.getString("AS_Duration"));
				voitureBean.setRENT_DATE(StringUtil.checkNull(rs.getDate("RENT_DATE")).toString());
				voitureBean.setSTAT_KILOMETER(rs.getString("STAT_KILOMETER"));
				voitureBean.setSTAT_OIL(rs.getString("STAT_OIL"));
				voitureBean.setRENT_TIME(rs.getString("RENT_TIME"));
				voitureBean.setDRIVER_NAME(rs.getString("DRIVER_NAME"));
				voitureBean.setEMPID(rs.getString("EMPID"));
				voitureBean.setJOB_CONTRACT(rs.getString("JOB_CONTRACT"));
				voitureBean.setCARD(rs.getString("CARD"));
				voitureBean.setDRIVER_CARD(rs.getString("DRIVER_CARD"));
				voitureBean.setGO_CARD(rs.getString("GO_CARD"));
				voitureBean.setJOUN_CARD(rs.getString("JOUN_CARD"));
				voitureBean.setDRIVER_CARD_TYPE(rs.getString("DRIVER_CARD_TYPE"));
				voitureBean.setPrice(rs.getString("price"));
				voitureBean.setAdded_Tax(rs.getString("added_Tax"));
				voitureBean.setCustom_Tax(rs.getString("custom_Tax"));
				voitureBean.setAdditional_tax(rs.getString("additional_tax"));
				voitureBean.setSign_Register(rs.getString("sign_Register"));
				voitureBean.setOther_Expenses(rs.getString("other_Expenses"));
				voitureBean.setGO_KILL(rs.getString("GO_KILL"));
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
		}
		
		return voitureBean;
	}
	/*根据车辆编号，得到车辆的所有信息，已做修改用*/
	public DriverBean  getAnDriver(String driverno){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		DriverBean  driverBean = new DriverBean();
		ResultSet rs= null;
		String sql = "select DRIVER_NAME,DRIVER_CARD_NUM,DRIVER_PHONE " +
		"  from DRIVER_INFO where DRIVER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(driverno));			
			rs=pstmt.executeQuery();
			if(rs.next()){
				driverBean.setDRIVER_NAME(rs.getString("DRIVER_NAME"));
				driverBean.setDRIVER_CARD_NUM(rs.getString("DRIVER_CARD_NUM"));
				driverBean.setDRIVER_PHONE(rs.getString("DRIVER_PHONE"));
				
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			
		} finally {
			SqlUtil.close( pstmt, conn);
		}
		
		return driverBean;
	}
	public List  getAnVoiture1(String voitureno){
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		
		ResultSet rs= null;
		String sql = "select EXPENSES,INSURANCE_TYPE,INSURANCE_COMPANY,START_DATE,END_DATE,INSURANCE_MENO,TOTAL_INSURANCE_FEE" +
				" from GA_VOITURE_B where GA_VOITURE_ID=? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(voitureno));			
			rs=pstmt.executeQuery();
			while(rs.next()){
				VoitureBean  voitureBean = new VoitureBean();
				voitureBean.setNOTE(rs.getString("EXPENSES"));
				voitureBean.setInsurance_Type(rs.getString("INSURANCE_TYPE"));
				voitureBean.setInsurance_Company(rs.getString("INSURANCE_COMPANY"));
				voitureBean.setSTART_DATE(StringUtil.checkNull(rs.getDate("START_DATE")).toString());
				voitureBean.setEND_DATE(StringUtil.checkNull(rs.getDate("END_DATE")).toString());
				voitureBean.setINSURANCE_MENO(rs.getString("INSURANCE_MENO"));
				voitureBean.setTotal_Insurance_Fee(rs.getString("TOTAL_INSURANCE_FEE"));
				list.add(voitureBean);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
		}
		
		return list;
	}
	
	public List  getAnVoiture2(String voitureno){
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;	
		
		ResultSet rs= null;
		String sql = "select ID,to_char(MAINTENACEDATE,'YYYY-MM-DD') as MAINTENACEDATE,NOTE,COST,to_char(MAINTENACEDATE1,'YYYY-MM-DD') as MAINTENACEDATE1" +
				" from ga_voituremaintenace where GA_VOITURE_ID=? order by MAINTENACEDATE1";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(voitureno));			
			rs=pstmt.executeQuery();
			while(rs.next()){
				VoitureBean  voitureBean = new VoitureBean();
				voitureBean.setNOTE(StringUtil.checkNull(rs.getString("MAINTENACEDATE")).toString());
				voitureBean.setInsurance_Type(rs.getString("NOTE"));
				voitureBean.setInsurance_Company(rs.getString("COST"));
				voitureBean.setSTART_DATE(StringUtil.checkNull(rs.getDate("MAINTENACEDATE1")).toString());
				list.add(voitureBean);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close( pstmt, conn);
		}
		
		return list;
	}
	
	/*得到车辆的履历原因，用来做select*/
	public List  getAllVoitureResume(){
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();		
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		String sql = "select t.*, t.rowid from sy_code t where t.parent_code='voitureResume'";

		try {
			pstmt = conn.prepareStatement(sql);				
			rs=pstmt.executeQuery();
			while(rs.next()){
				VoitureBean  voitureBean = new VoitureBean();
				voitureBean.setCode_id(rs.getString("code_id"));
				voitureBean.setCode_name(rs.getString("code_name"));
				list.add(voitureBean);	
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}		
		return list;
	}
	
	
	/*得到车辆履历费用*/
	public String getAreaSummaryCosts(String VOITURE_ID,String Code_id,String startdate,String enddate){
		String str="";
		Connection conn = ConnBean.getConn();		
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		String sql = "select nvl(sum(to_number(t.amount)),0)||'元' as counts from "+
						" ga_voiture_expense t "+
						" where t.causes='"+Code_id+"'"+
						" and t.voitureno='"+VOITURE_ID+"' "+
						" and t.timing_date between to_date('"+startdate+"','yyyy-mm-dd') and to_date('"+enddate+"','yyyy-mm-dd') ";

		try {
			pstmt = conn.prepareStatement(sql);				
			rs=pstmt.executeQuery();
			if(rs.next()){
				str=rs.getString("counts");
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}		
		return str;
	}
	
	/*得到车辆履历总费用*/
	public String getAreaSummaryCostsSum(String startdate,String enddate){
		String str="";
		Connection conn = ConnBean.getConn();		
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		String sql = "select nvl(sum(to_number(t.amount)),0)||'元' as counts from "+
						" ga_voiture_expense t "+						
						" where t.timing_date between to_date('"+startdate+"','yyyy-mm-dd') and to_date('"+enddate+"','yyyy-mm-dd') ";

		try {
			pstmt = conn.prepareStatement(sql);				
			rs=pstmt.executeQuery();
			if(rs.next()){
				str=rs.getString("counts");
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}		
		return str;
	}
	
	/*根据车辆得到车辆履历小计费用*/
	public String getAreaSummaryCostsSumFromVoiture(String VOITURE_ID,String startdate,String enddate){
		String str="";
		Connection conn = ConnBean.getConn();		
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		String sql = "select nvl(sum(to_number(t.amount)),0)||'元' as counts from "+
						" ga_voiture_expense t "+
						" where  t.voitureno='"+VOITURE_ID+"' "+
						" and t.timing_date between to_date('"+startdate+"','yyyy-mm-dd') and to_date('"+enddate+"','yyyy-mm-dd') ";

		try {
			pstmt = conn.prepareStatement(sql);				
			rs=pstmt.executeQuery();
			if(rs.next()){
				str=rs.getString("counts");
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}		
		return str;
	}
	/*根据履历原因得到车辆履历小计费用*/
	public String getAreaSummaryCostsSumFromCodeid(String Code_id,String startdate,String enddate){
		String str="";
		Connection conn = ConnBean.getConn();		
		PreparedStatement pstmt = null;	
		ResultSet rs =null;
		String sql = "select nvl(sum(to_number(t.amount)),0)||'元' as counts from "+
						" ga_voiture_expense t "+
						" where t.causes='"+Code_id+"'"+
						" and t.timing_date between to_date('"+startdate+"','yyyy-mm-dd') and to_date('"+enddate+"','yyyy-mm-dd') ";
		try {
			pstmt = conn.prepareStatement(sql);				
			rs=pstmt.executeQuery();
			if(rs.next()){
				str=rs.getString("counts");
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}		
		return str;
	}
	
	public void troubleManger(HttpServletRequest request,AdminBean admin){
		GaServices gaServices = new GaServices();
		SimpleMap parameterObject = ObjectBindUtil.getData(request);
		try {
		UserConfiguration config = UserConfiguration.getInstance("/system.properties");
		int pageSize = config.getInt("page.style1.pagesize");
		int pageGroupSize = config.getInt("page.style1.pagegroupsize");
		int currentPage = 0;
		SimpleMap dataMap = new SimpleMap();
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		parameterObject.put("cpny_id", admin.getCpnyId());
		List list = gaServices.troubleManger(parameterObject,currentPage,pageSize);
		int resultCount = gaServices.troubleMangerInt(parameterObject);
		request.setAttribute("list", list);
		
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("recordCount", resultCount);
		request.setAttribute("pageGroupsize", pageGroupSize);
		}catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		}	
	}
	
	public void AddMaintenance(HttpServletRequest request,AdminBean admin){
		GaServices gaServices = new GaServices();
		SimpleMap parameterObject = ObjectBindUtil.getData(request);
		String START = parameterObject.getString("START1")+" "+parameterObject.getString("START2");
		parameterObject.put("START", START);
		parameterObject.put("cpny_id", admin.getCpnyId());
		try {
		gaServices.AddMaintenance(parameterObject);
		}catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		}	
	}
	
	public void UpdatevoitureManger(HttpServletRequest request,AdminBean admin){
		GaServices gaServices = new GaServices();
		SimpleMap parameterObject = ObjectBindUtil.getData(request);
		parameterObject.put("id", request.getParameter("id"));

		try {
		List list = gaServices.UpdateViewVoitureManger(parameterObject);
		SimpleMap simpleMap = (SimpleMap)list.get(0);
		String START1 = ((String)simpleMap.get("STARTDATE")).substring(0,10);
		String START2 = ((String)simpleMap.get("STARTDATE")).substring(10,15);
		request.setAttribute("id", simpleMap.get("ID"));
		request.setAttribute("DRIVER", simpleMap.get("DRIVER"));
		request.setAttribute("USERSCOUNT", simpleMap.get("USERSCOUNT"));
		request.setAttribute("DEPTNAME", simpleMap.get("DEPTNAME"));
		request.setAttribute("START1", START1);
		request.setAttribute("START2", START2);
		request.setAttribute("SITE", simpleMap.get("SITE"));
		request.setAttribute("CAUSE", simpleMap.get("CAUSE"));
		request.setAttribute("STATE", simpleMap.get("STATE"));
		request.setAttribute("ESTABLISH", simpleMap.get("ESTABLISH"));
		request.setAttribute("IN_NAME", simpleMap.get("IN_NAME"));
		request.setAttribute("O_NAME", simpleMap.get("O_NAME"));
		request.setAttribute("IN_SEX", simpleMap.get("IN_SEX"));
		request.setAttribute("O_SEX", simpleMap.get("O_SEX"));
		request.setAttribute("IN_JOB", simpleMap.get("IN_JOB"));
		request.setAttribute("O_JOB", simpleMap.get("O_JOB"));
		request.setAttribute("IN_ADDER", simpleMap.get("IN_ADDER"));
		request.setAttribute("O_ADDER", simpleMap.get("O_ADDER"));
		request.setAttribute("IN_DEL", simpleMap.get("IN_DEL"));
		request.setAttribute("O_DEL", simpleMap.get("O_DEL"));
		request.setAttribute("IN_COM", simpleMap.get("IN_COM"));
		request.setAttribute("O_COM", simpleMap.get("O_COM"));
		request.setAttribute("IN_YEAR", simpleMap.get("IN_YEAR"));
		request.setAttribute("O_YEAR", simpleMap.get("O_YEAR"));
		request.setAttribute("IN_NUM", simpleMap.get("IN_NUM"));
		request.setAttribute("O_NUM", simpleMap.get("O_NUM"));
		request.setAttribute("IN_DRIVERNUM", simpleMap.get("IN_DRIVERNUM"));
		request.setAttribute("O_DRIVERNUM", simpleMap.get("O_DRIVERNUM"));
		request.setAttribute("IN_PHO", simpleMap.get("IN_PHO"));
		request.setAttribute("MOME", simpleMap.get("MOME"));
		request.setAttribute("FILE", simpleMap.get("FILE"));
		request.setAttribute("DEPTNAME1", simpleMap.get("DEPTNAME1"));
		}catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		}	
	}
	
	public void UpdateSavevoitureManger(HttpServletRequest request,AdminBean admin){
		GaServices gaServices = new GaServices();
		SimpleMap parameterObject = ObjectBindUtil.getData(request);
		String START = parameterObject.getString("START1")+" "+parameterObject.getString("START2");
		parameterObject.put("START", START);
		try {
			
			gaServices.UpdatevoitureManger(parameterObject);
		}catch (Exception e) {		
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		
		}	
	}
	
	 /*上传图片*/
	 public void uploadVisiterDocuments(HttpServletRequest request){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql="insert into FILE_OR_PHOTO_PATH(DOCUMENTNO,CHINESENAME,PATHADDRESS,filename) values (?,?,?,?)";
		DiskFileUpload fu = new DiskFileUpload();		
		String documentno=	request.getParameter("documentno");		 
			 List fileItems = null;
			 try {
				 fileItems = fu.parseRequest(request);				
				Iterator iter = null;
				if(fileItems!=null){
					iter = fileItems.iterator();
					 while (iter.hasNext()) {						
						    FileItem item = (FileItem)iter.next();
						    if (!item.isFormField() && !item.getName().equals("")){
						    	ServletContext application = request.getSession().getServletContext();
						    	String filepath = "/upload/visiter"; 						    	
						    	String path = application.getRealPath(filepath);
						    	File file = null;						    	
						    	int start = item.getName().lastIndexOf("\\"); 
						    	String name =item.getName().substring(start+1);						    	
						    	file = new File(path); 
						    	/**如果文件已经存在，则删除原有文件，上传新文件覆盖原有文件。*/ 
						    	/*if(file.exists()){ 
						    	file.delete(); 
						    	}else{ 
						    	file = new File("/upload/safe/",documentno+item.getName()); 
						    	*//**如果文件已经存在，则删除原有文件，上传新文件覆盖原有文件。*//* 						    	
						    	} */

						    	int seqid=this.getSequence("FILE_OR_PHOTO_PATH_SEQ");
						    	File objectfile= new File(path+"\\"+documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	if(!file.exists()){
						    		file.mkdir();
					       		}
						    	ps = conn.prepareStatement(sql);
						    	ps.setString(1,documentno);
						    	ps.setString(2,name.replaceAll("\\s*", ""));
						    	ps.setString(3,"../upload/visiter/"+documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	ps.setString(4,documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	ps.executeUpdate();
						    	
								item.write(objectfile);
								
						    }
					 }
				}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					SqlUtil.close(ps, conn);
				} 
	}
	 /*得到序列号*/
		private int getSequence(String seqName) {
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
	
}
