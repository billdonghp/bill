package com.ait.evs.servlet;

/**
 * <p>Title: AIT  HOMEPAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.commons.dao.CommonDAO;
import com.ait.ess.bean.EssAffirmor;
import com.ait.evs.evs0126.bean.BizEvsCode;
import com.ait.evs.evs0126.bean.EntEvsCode;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.CodeUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean; 
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.Func;

public class Eevs0128Control extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServletContext m_application = null;

	BizEvsCode biz = new BizEvsCode();

	String Crt_Id; // 登录号

	String Crt_Man; // 登录姓名

	String suser1; //

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	// Initialize global variables
	public void init(ServletConfig config) throws ServletException {
		m_application = config.getServletContext();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>a03control</title></head>");
		out.println("<body>");
		out
				.println("<p>The servlet a03control has received a GET. This is the reply.</p>");
		out.println("</body></html>");
		HttpSession session = request.getSession(true); // 产生session

		Func func = new Func(session);

		// UserEntity userEntity = new UserEntity();
		// userEntity = (UserEntity) session.getAttribute("userEntity");

		String Crt_Id = "";
		AdminBean adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
		Crt_Id=((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		BizEvsCode biz = new BizEvsCode();
		String flag = request.getParameter("flag");

		String menu_code = "";
  
		if (request.getParameter("menu_code") != null) {
			menu_code = (request.getParameter("menu_code"));
		}
		EntEvsCode Ent = new EntEvsCode();

		// Ent.setCreateUser(LogEmpName); //以管理员session的EmpName作为记录的CreateUser
		// Ent.setModifyUser(LogEmpName); //以管理员session的EmpName作为记录的ModifyUser

		if (flag != null) {
			// set value to mtrrsrventity

			if (flag.equals("list")) { // 跳转到列表页面
				try {

					String bigpage = request.getParameter("bigpage");
					if (bigpage == null) {
						bigpage = "";
					}
					String strPage = request.getParameter("strPage");
					if (strPage == null) {
						strPage = "";
					}

					response.sendRedirect("/sy/" + menu_code
							+ ".jsp?menu_code=" + menu_code + "&bigpage="
							+ bigpage + "&strPage=" + strPage);

				} catch (Exception e) {
					System.out.print(e);
				}
			}

			/*
			 * if (flag.equals("add")) { //跳转到添加页面a try {
			 * response.sendRedirect("/manage/" + menu_code + "a.jsp?menu_code=" +
			 * menu_code); } catch (Exception e) { System.out.print(e); } }
			 */
			if (flag.equals("view")) { // 跳转到查看页v
				try {
					String no = request.getParameter("no");
					response.sendRedirect("/sy/E" + menu_code + "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}

			if (flag.equals("next")) { // 跳转到下一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.next("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code
							+ "v.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}

			if (flag.equals("back")) { // 跳转到上一条查看页v
				try {
					menu_code = request.getParameter("menu_code");
					int no = Integer.parseInt(request.getParameter("no"));
					no = func.back("board_mn", "no", no);
					response.sendRedirect("/intra/" + menu_code
							+ "v.jsp?menu_code=" + menu_code + "&no=" + no);

				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}

			if (flag.equals("modify")) { // 跳转到修改页m
				try {
					String no = request.getParameter("no");

					response.sendRedirect("/intra/" + menu_code
							+ "m.jsp?menu_code=" + menu_code + "&no=" + no);
				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}
			/*
			 * if (flag.equals("replygo")) { //跳转到修改页m try { String no =
			 * request.getParameter("no");
			 * 
			 * response.sendRedirect("/intra/" + menu_code + "r.jsp?menu_code=" +
			 * menu_code + "&no=" + no); } catch (Exception e) {
			 * System.out.print(e); } }
			 */

			if (flag.equals("search")) { // search

			} // search end

			if (flag.equals("insert")) { // 执行insert
				try {
					
					   
					
					String basicCode = func.strIsoZp(request.getParameter("basicCode") != null ? request
							.getParameter("basicCode")	: "");
					String basicName = func.strIsoZp(request.getParameter("basicName") != null ? request  
							.getParameter("basicName") : "");
					String basicEnName= func.strIsoZp(request.getParameter("basicEnName") != null ? request
							.getParameter("basicEnName"): "");
					String parentCode = request.getParameter("parentCode") != null ? request
							.getParameter("parentCode")	: "";
					String descrIptio = request.getParameter("descrIptio") != null ? request
									.getParameter("descrIptio")	: "";
					String depth = request.getParameter("depth") != null ? request
							.getParameter("depth") : "";
					String cpnyId=request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):"";
					String evscodeflag = null;
					if(depth.equals("1"))
					{
					   evscodeflag = "LE";	
					}
					else if(depth.equals("2"))
					{
					   evscodeflag = "JC";	
					}
					
					 EntEvsCode Enty = BizEvsCode.selectid(basicCode);
		        	 if(Enty!=null)
		        	 {
		        		 if(Enty.getStatus()!=null&&Enty.getStatus().equals("0")){
		        			 Enty.setParentCode(parentCode);
			        		 Enty.setBasicName(basicName);  
			        		 Enty.setCreatorID(Crt_Id);
			        		 Enty.setCodeEnName(basicEnName);
			        		 Enty.setCpnyId(cpnyId);
			        		 Enty.setEvsflag(evscodeflag);
			        		 Enty.setDescriptio(descrIptio);
			        		 Enty.setStatus("1");
			        		 Enty.setDepth(Integer.parseInt(depth));
			        		 
			        		 biz.Update(Enty);
		        		 }else{
		        			 response.sendRedirect("/evs/evs_code_detail0128.jsp?menu_code="
										+ menu_code + "&code_id=" + parentCode);
		        		 }
		        			
		        		 
		        	 }else{
		        		    Ent.setBasicCode(basicCode);
							Ent.setParentCode(parentCode);
							Ent.setBasicName(basicName);  
							Ent.setCreatorID(Crt_Id);
							Ent.setCodeEnName(basicEnName);
							Ent.setCpnyId(cpnyId);
							Ent.setEvsflag(evscodeflag);
							Ent.setDescriptio(descrIptio);
							
							biz.Insertgx(Ent, depth);
		        	 }
					
					// refresh code list
					CodeUtil.getInstance().refreshCodes();
					if (parentCode.equals("0")) {
						response.sendRedirect("/evs/" + menu_code
								+ ".jsp?menu_code=" + menu_code);
					} else {
						response
								.sendRedirect("/evs/evs_code_detail0128.jsp?menu_code="
										+ menu_code + "&code_id=" + java.net.URLEncoder.encode(parentCode,"utf-8")+ "&flag=back" );
					}
				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}
			}               
			
			if (flag.equals("updateSubCode")){
				
				int codeNo = Integer.parseInt(request.getParameter("codeNo"));
				String codeName = StringUtil.toCN(request.getParameter("codeName"));
				String codeEnName = StringUtil.toCN(request.getParameter("basicEnName"));
				String descrIptio = StringUtil.toCN(request.getParameter("descrIptio"));
				String descrIptioy = StringUtil.toCN(request.getParameter("descrIptioy"));
				String parentCode = StringUtil.toCN(request.getParameter("parentCode"));
				String cpnyId = StringUtil.toCN(request.getParameter("cpnyId"));
				String codeId = StringUtil.toCN(request.getParameter("codeId"));
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
				
			    if(descrIptioy!=null&&!descrIptioy.equals(descrIptio))
				 {
					try {
					///////////决裁相关				
					PreparedStatement insertAffirmPsb = null;
					PreparedStatement insertAffirmPs = null;
					PreparedStatement psAffirmEmail = null;
					ResultSet rsAffirmEmail = null;
					String affirmEmail ="";
					StringBuffer content = new StringBuffer();
					String emailAddress = "";
					SimpleMap inputData = new SimpleMap();
					
					 String url = "http://10.40.128.28:8083/" ;
					
					 ////更新之前修改的决裁记录
					 String sqlInsertAffirmb="update EVS_CODE_AFFIRM set activity ='2' where AFFIRM_FLAG !='0' AND ar_detail_no =? ";
					 
					String sqlInsertAffirm="INSERT INTO EVS_CODE_AFFIRM(AR_DETAIL_AFFIRM_NO,AR_DETAIL_NO,AFFIRM_FLAG,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATE_BY,ACTIVITY,CODE_FLAG) " +
	                "VALUES(Evs_Code_Affirm_Seq.NEXTVAL,?,'0',?,?,SYSDATE,?,'1','update')";
	
					String sqlAffirmEmail = "SELECT T.EMAIL FROM HR_EMPLOYEE T WHERE T.PERSON_ID = ? AND T.CPNY_ID in ('78000000','59000000','63000000')";
	
					//取出决裁者
					ArrayList affirmList = new ArrayList() ;
					affirmList = (ArrayList) this.getAffirmorArModify1(admin.getAdminID(), "ArModifyApply") ;
					Connection conn = ConnBean.getConn();	
					String AffirmorIdFirst ="" ;
					for(int j=0;j<affirmList.size();j++){
						EssAffirmor arAffirmList = new EssAffirmor();
						arAffirmList=(EssAffirmor)affirmList.get(j);
						
					
						
						insertAffirmPsb = conn.prepareStatement(sqlInsertAffirmb);
						insertAffirmPsb.setInt(1, codeNo);
						insertAffirmPsb.executeUpdate();
						
						insertAffirmPs = conn.prepareStatement(sqlInsertAffirm);
						insertAffirmPs.setInt(1, codeNo);
						insertAffirmPs.setInt(2, arAffirmList.getAffirmLevel());
						insertAffirmPs.setString(3, arAffirmList.getAffirmorId());
						insertAffirmPs.setString(4, admin.getAdminID());
						insertAffirmPs.executeUpdate();
						if(j==0){
							AffirmorIdFirst = arAffirmList.getAffirmorId();
						}
					}
					
					psAffirmEmail = conn.prepareStatement(sqlAffirmEmail);//取出第一级决裁者的邮箱地址
					psAffirmEmail.setString(1, AffirmorIdFirst);
					rsAffirmEmail = psAffirmEmail.executeQuery();
					
					int updateFlag =-1;
					if (rsAffirmEmail.next()) {
						affirmEmail = rsAffirmEmail.getString("EMAIL");
						updateFlag = 0; 
					}
					
							
					//修改  勤态修改发送邮件
	//				if("78000000".equals(admin.getCpnyId()) && affirmEmail !=""){	元
				   if(affirmEmail !=""){	
												
							//SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
						   // content.append("<br>").append("工种:").append(biz);
							//content.append("<br>").append("LINE线:").append(dengji);
							//content.append("<br>").append("岗位:").append(dengji);
							content.append("<br>").append("工序:").append(codeName);
					    	content.append("<br>").append("工序难度系数修改:").append(descrIptio);
							content.append("<br>");
							content.append("----------------------------");
							
					}				
				   
				 			   			
					 conn.commit();
					////此处 应该一起提交
					   
					biz.updategx(codeNo, codeName,codeEnName,cpnyId,descrIptio,Crt_Id);
					
					Ent.setBasicCode(codeId);
					Ent.setParentCode(parentCode);
					Ent.setBasicName(codeName);  
					Ent.setCreatorID(Crt_Id);
					Ent.setCodeEnName(codeEnName);
					Ent.setCodeNo(codeNo);
					Ent.setCpnyId(cpnyId);
					Ent.setEvsflag("JC");
					Ent.setDescriptio(descrIptioy);
					
					biz.Insertgxbc(Ent, "3");			
									
					if(affirmEmail !="")
					{
						emailAddress=affirmEmail;
						content.append("<br>");
						content.append("系统决裁页面位置:技能评价 > 领导决裁 > 工序修改决裁");
						content.append("<br>");
						content.append("--------------------------------------------------------------------------");
						content.append("<br>");
				
					    content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆e-HR决裁</a>")
					    .append("<br>"+"斗山工程机械(中国)有限公司") ;
					    String emailTitle="工序修改申请";	
					    inputData.setString("EMAIL_TITLE", emailTitle);
		
					    // set email content
					    inputData.setString("EMAIL_CONTNT", content.toString());
		
					    inputData.setString("RCVR_EMAIL_ADDR", emailAddress);
		
					    new Mail().sendMail(inputData) ;	
					 }
					} catch (Exception e) {
					
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }else{
					  biz.updategx(codeNo, codeName,codeEnName,cpnyId,descrIptio,Crt_Id);
				  }
			    
				// refresh code list
				CodeUtil.getInstance().refreshCodes();
				response.sendRedirect("/evs/evs_code_detail0128.jsp?menu_code="
						+ menu_code + "&code_id=" + java.net.URLEncoder.encode(parentCode,"utf-8")+ "&flag=back");
			}

			if (flag.equals("updata")) { // 执行update
				try {
                    Logger.getLogger(getClass()).debug("update");
					String basicCode = request.getParameter("basicCode");
					String basicName = func.strIsoZp(request
							.getParameter("basicName"));
					String codeEnName = func.strIsoZp(request.getParameter("basicEnName"));
					String no= request.getParameter("no");
					String cpny_id = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):"";
					Ent.setBasicCode(basicCode);
					Ent.setBasicName(basicName);
					Ent.setCodeEnName(codeEnName);
					Ent.setModifierID(Crt_Id);
					Ent.setCreatorID(no);
					Ent.setCpnyId(cpny_id);
			
					biz.Update(Ent);
					// refresh code list
					CodeUtil.getInstance().refreshCodes();
					response.sendRedirect("/evs/" + menu_code + ".jsp?menu_code=" + menu_code);

				} catch (Exception e) {
                    Logger.getLogger(getClass()).debug(e.toString());
				}

			}

			if (flag.equals("delete")) { // 执行delete

				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
				String code_id = request.getParameter("code_id") != null ? request.getParameter("code_id"): "";
		        //System.out.println(code_id);
				 String codeid =	request.getParameter("no");
				
				    EntEvsCode ent = BizEvsCode.selectid(codeid);
		        	 String descriptio2 = ent.getDescriptio();
		        	
		        	
					try {
					///////////决裁相关				
					PreparedStatement insertAffirmPsb = null;
					PreparedStatement insertAffirmPs = null;
					PreparedStatement psAffirmEmail = null;
					ResultSet rsAffirmEmail = null;
					String affirmEmail ="";
					StringBuffer content = new StringBuffer();
					String emailAddress = "";
					SimpleMap inputData = new SimpleMap();
					
					 String url = "http://10.40.128.28:8083/" ;
					
					 ////更新之前修改的决裁记录
					 String sqlInsertAffirmb="update EVS_CODE_AFFIRM set activity ='2' where AFFIRM_FLAG !='0' AND ar_detail_no =? ";
					 
					String sqlInsertAffirm="INSERT INTO EVS_CODE_AFFIRM(AR_DETAIL_AFFIRM_NO,AR_DETAIL_NO,AFFIRM_FLAG,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATE_BY,ACTIVITY,CODE_FLAG) " +
	                "VALUES(Evs_Code_Affirm_Seq.NEXTVAL,?,'0',?,?,SYSDATE,?,'1','delete')";
	
					String sqlAffirmEmail = "SELECT T.EMAIL FROM HR_EMPLOYEE T WHERE T.PERSON_ID = ? AND T.CPNY_ID in ('78000000','59000000','63000000')";
	
					//取出决裁者
					ArrayList affirmList = new ArrayList() ;
					affirmList = (ArrayList) this.getAffirmorArModify1(admin.getAdminID(), "ArModifyApply") ;
					Connection conn = ConnBean.getConn();	
					String AffirmorIdFirst ="" ;
					for(int j=0;j<affirmList.size();j++){
						EssAffirmor arAffirmList = new EssAffirmor();
						arAffirmList=(EssAffirmor)affirmList.get(j);
						
					
						
						insertAffirmPsb = conn.prepareStatement(sqlInsertAffirmb);
						insertAffirmPsb.setInt(1, ent.getCodeNo());
						insertAffirmPsb.executeUpdate();
						
						insertAffirmPs = conn.prepareStatement(sqlInsertAffirm);
						insertAffirmPs.setInt(1, ent.getCodeNo());
						insertAffirmPs.setInt(2, arAffirmList.getAffirmLevel());
						insertAffirmPs.setString(3, arAffirmList.getAffirmorId());
						insertAffirmPs.setString(4, admin.getAdminID());
						insertAffirmPs.executeUpdate();
						if(j==0){
							AffirmorIdFirst = arAffirmList.getAffirmorId();
						}
					}
					
					psAffirmEmail = conn.prepareStatement(sqlAffirmEmail);//取出第一级决裁者的邮箱地址
					psAffirmEmail.setString(1, AffirmorIdFirst);
					rsAffirmEmail = psAffirmEmail.executeQuery();
					
					int updateFlag =-1;
					if (rsAffirmEmail.next()) {
						affirmEmail = rsAffirmEmail.getString("EMAIL");
						updateFlag = 0; 
					}
					
							
					//修改  勤态修改发送邮件
	//				if("78000000".equals(admin.getCpnyId()) && affirmEmail !=""){	元
				   if(affirmEmail !=""){	
												
							//SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
						   // content.append("<br>").append("工种:").append(biz);
							//content.append("<br>").append("LINE线:").append(dengji);
							//content.append("<br>").append("岗位:").append(dengji);
							content.append("<br>").append("工序:").append(codeid);
					    	content.append("<br>").append("工序难度系数:").append(ent.getDescriptio());
							content.append("<br>");
							content.append("----------------------------");
							
					}				
				   
				 			   			
					 conn.commit();
					////此处 应该一起提交
					   
					biz.updategx(ent.getCodeNo(), ent.getBasicName(),ent.getCodeEnName(),ent.getCpnyId(),ent.getDescriptio(),Crt_Id);
					
						
									
					if(affirmEmail !="")
					{
						emailAddress=affirmEmail;
						content.append("<br>");
						content.append("系统决裁页面位置:技能评价 > 领导决裁 > 工序删除决裁");
						content.append("<br>");
						content.append("--------------------------------------------------------------------------");
						content.append("<br>");
				
					    content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆e-HR决裁</a>")
					    .append("<br>"+"斗山工程机械(中国)有限公司") ;
					    String emailTitle="职业资格等级修改申请";	
					    inputData.setString("EMAIL_TITLE", emailTitle);
		
					    // set email content
					    inputData.setString("EMAIL_CONTNT", content.toString());
		
					    inputData.setString("RCVR_EMAIL_ADDR", emailAddress);
		
					    new Mail().sendMail(inputData) ;	
					 }
					} catch (Exception e) {
					
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				  
			    
			    if (!code_id.equals("")) {
					response.sendRedirect("/evs/evs_code_detail0128.jsp?code_id="
							+ java.net.URLEncoder.encode(code_id,"utf-8")+ "&flag=back");

				} else {
					response.sendRedirect("/evs/" + menu_code
							+ ".jsp?menu_code=" + menu_code);
				}
			
				
			}
		}
	}
	public List getAffirmorArModify1(String empId, String applyType) {
		//System.out.println("++++++++++++++++++++++++++++++_______________)))))))))))))))))))))))))))))))");
		
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql="";
		int ownerLevel=0;
		int affirmMinLevel=0;
		try {
		//取出决裁对象为部门时，自己的决裁级别
			sql= " select NVL(MAX(SAR.AFFIRM_LEVEL), 0) ownerLevel "+
	          " from SY_AFFIRM_RELATION_TB_AUTO SAR, HR_EMPLOYEE HR_AFFIRMOR "+
	          " where SAR.AFFIRM_DEPTID = HR_AFFIRMOR.DEPTID "+
	          " AND SAR.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
	          " AND SAR.AFFIRM_DUTY IS NOT NULL "+	
	          " AND SAR.MODULE = 'AR' "+
	          " AND HR_AFFIRMOR.Person_Id =? ";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
			}

			affirmMinLevel=ownerLevel;
		

			sql = " SELECT DEPTID FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ";  //取得人员所属部门，不取它的上级部门
			//sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
	        //" FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);
			while (rs.next())
			{
				sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
					+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,SAR.AFFIRM_DUTY " 
					+ "  FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR, " 
					+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
					+ "  WHERE SAR.AFFIRM_DEPTID = HD.DEPTID " 
					+ "  AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
					+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
					+ "  AND SAR.MODULE ='AR'"
					+ "  AND SAR.AFFIRM_LEVEL > "+affirmMinLevel
					+ " AND SAR.AFFIRM_DUTY IS NOT NULL "
					+ "  AND HD.DEPTID ='"
					+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery(sql);
				System.out.println("applyType"+applyType+"11"+ rs.getString("DEPTID")+"22"+affirmMinLevel);
				Logger.getLogger(getClass()).debug(sql);
				while (rs2.next()) {
					EssAffirmor vb = new EssAffirmor();
					vb.setAffirmLevel(rs2.getInt("num"));
					vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
					result.add(vb);
					
					if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")){
						break ;
					}
				}
				
				if (result.size() > 0)
				{
					break ;
				}	
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}	
	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Process the HTTP Put request
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// Process the HTTP Delete request
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	// Clean up resources
	public void destroy() {
	}
}
