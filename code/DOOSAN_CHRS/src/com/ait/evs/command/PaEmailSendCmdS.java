package com.ait.evs.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmor;
import com.ait.evs.EvsMaster;
import com.ait.evs.Evsupcode;
import com.ait.evs.business.EvsServices;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.crystaldecisions.sdk.occa.report.formatteddefinition.model.Map;

public class PaEmailSendCmdS extends ArAbstractCommand {

	private static final Logger logger = Logger.getLogger(PaEmailSendCmdS.class);

	private String url = "http://10.40.128.28:8083/";
	
	private PaServices paServices = null;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MessageSource messageSource ;
		int pageSize = 0;
		int pageGroupSize = 0;
		int currentPage = 0;
		int resultCount = 0;   
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
	
		String ev_emp_name = request.getParameter("ev_emp_name");
		List searchCount;
		EvsServices services = EvsServices.getInstance();
		SimpleMap simpleMap = new SimpleMap(); 
		//simpleMap = ObjectBindUtil.getData(request);
		// simpleMap.put("empID", ev_emp_id);
		String countemp = "1";
		Connection conn = ConnBean.getConn();
		String ids[] = request.getParameterValues("selectC");
		simpleMap.set("ids", ids);	
		
		
		searchCount =  (List)services.getEvsCountEmp(simpleMap);
		if(searchCount!=null){
			 for(int i=0;i<searchCount.size();i++){
				  SimpleMap searchCountList = (SimpleMap)searchCount.get(i);
				  				  	
				     String setupcodeNo = (String)searchCountList.get("SETUPCODENO");
				     String  empNAME = (String)searchCountList.get("CHINESENAME");
		        	 String  empid = (String)searchCountList.get("EMPID");
		        	 String  deptname = (String)searchCountList.get("DEPTNAME");
		        	 String  craft = (String)searchCountList.get("CRAFT");
		        	 String  JOBCONTENT = (String)searchCountList.get("JOBCONTENT");
		        	 String  deptke = (String)searchCountList.get("DEPTKE");
		        	 String  deptzhi = (String)searchCountList.get("DEPTZHI");
		        	 String  DEPTZU = (String)searchCountList.get("DEPTZU");
		        	 //System.out.print(affirmNo);		        	
		          try{
		        			 
		        			 			      		    	
		        	  			
		        	        EvsMaster evsMasters=new EvsMaster();
		        	      		        	        
		        	        
		 					PreparedStatement psAffirmEmail = null;
		 					ResultSet rsAffirmEmail = null;
		 					String affirmEmail ="";
		 					StringBuffer content = new StringBuffer();
		 					String emailAddress = "";
		 					SimpleMap inputData = new SimpleMap();
		 					
		 					 String url = "http://10.40.128.28:8083/" ;
		 					
		 					   
				 				content.append("<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
										.append("<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
										.append("<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
										.append("<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>")								
										.append("人员技能与作业工序不匹配详细内容</span></font></b></strong>")
										.append("</p></td></tr>")
										.append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
										.append("<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>");
									
				 					  
				 					 content.append("</p></td></tr>")
				 					.append("<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
				 					.append("<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>"+empNAME+"</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>"+empid+"</span></font></p></td>")
				 					.append("</tr><tr>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>工种</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>"+craft+"</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>工序</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>"+JOBCONTENT+"</span></font></p></td>")
				 					.append("</tr><tr>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt' colspan=3><p align=center style='text-align:center'><font size=9 face=宋体><span style='font-size:12.0pt'>"+deptname+deptke+deptzhi+DEPTZU+"</span></font></p></td>")
				 					.append("</tr><tr>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>详细内容</span></font></p></td>")
				 					.append("<td style='padding:.75pt .75pt .75pt .75pt' colspan=3><p align=center style='text-align:center'><font size=9 face=宋体><span style='font-size:12.0pt'>"+empNAME+"(职号："+empid+")现有技能与作业工序要求不匹配，请重点监督管理。</span></font></p></td>")
				 					
				 					.append("</tr></table></div>");
				 			content.append("<br>");
			 						//content.append("系统页面位置:技能评价 > 评价进行 > 评价打分");
			 						content.append("<br>");
			 						content.append("--------------------------------------------------------------------------");
			 						content.append("<br>");
			 				
			 			    content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆e-HR决裁</a>")
			 					    .append("<br>"+"斗山工程机械(中国)有限公司") ;
		 					String sqlAffirmEmail = "SELECT T.EMAIL FROM HR_EMPLOYEE T WHERE T.PERSON_ID = ? AND T.CPNY_ID in ('78000000','59000000','63000000')";

		 					//取出决裁者
		 					ArrayList affirmList = new ArrayList() ;
		 					affirmList = (ArrayList) this.getAffirmorArModify1(empid, "ArModifyApply") ;
		 						
		 					String AffirmorIdFirst ="" ;
		 					for(int j=0;j<affirmList.size();j++){
		 						EssAffirmor arAffirmList = new EssAffirmor();
		 						arAffirmList=(EssAffirmor)affirmList.get(j);
		 					
		 						
		 						AffirmorIdFirst = arAffirmList.getAffirmorId();
		 								 							 					
		 					psAffirmEmail = conn.prepareStatement(sqlAffirmEmail);//取出第一级决裁者的邮箱地址
		 					psAffirmEmail.setString(1, AffirmorIdFirst);
		 					rsAffirmEmail = psAffirmEmail.executeQuery();
		 					
		 					int updateFlag =-1;
		 					if (rsAffirmEmail.next()) {
		 						affirmEmail = rsAffirmEmail.getString("EMAIL");
		 						updateFlag = 0; 
		 					}
		 					
		 				   conn.commit();
		 				  		 					
		 					
		 					if(affirmEmail !="")
		 					{
		 					    String emailTitle="人员技能与作业工序不匹配";	
		 					    inputData.setString("EMAIL_TITLE", emailTitle);
		 		
		 					    // set email content
		 					    inputData.setString("EMAIL_CONTNT", content.toString());
		 		
		 					    inputData.setString("RCVR_EMAIL_ADDR", affirmEmail);
		 		
		 					    new Mail().sendMail(inputData) ;	
		 					}
		      		    	 		 					
		 				  }///决裁者邮件提醒
		 					
		 			     List lEvsEmp=null;
			        	 lEvsEmp=evsMasters.getEvsPersonYoujian("RS");//大的 
			        	        
		 				 if(lEvsEmp!=null ){
		        	    		int lEvsEmp2Size=lEvsEmp.size();
		        	    		for(int m=0;m<lEvsEmp2Size;m++){
		        	    			EvsMaster evsMEmp=(EvsMaster)lEvsEmp.get(m);
		        	    			
		        	    			psAffirmEmail = conn.prepareStatement(sqlAffirmEmail);//取出第一级决裁者的邮箱地址
				 					psAffirmEmail.setString(1, evsMEmp.getEvMaster());
				 					rsAffirmEmail = psAffirmEmail.executeQuery();
				 					
				 					int updateFlag =-1;
				 					if (rsAffirmEmail.next()) {
				 						affirmEmail = rsAffirmEmail.getString("EMAIL");
				 						updateFlag = 0; 
				 					}
				 					
				 				   conn.commit();
				 				  		 					
				 					
				 					if(affirmEmail !="")
				 					{
				 					    String emailTitle="人员技能与作业工序不匹配";	
				 					    inputData.setString("EMAIL_TITLE", emailTitle);
				 		
				 					    // set email content
				 					    inputData.setString("EMAIL_CONTNT", content.toString());
				 		
				 					    inputData.setString("RCVR_EMAIL_ADDR", affirmEmail);
				 		
				 					    new Mail().sendMail(inputData) ;	
				 					}
		        	    		}
		        	    	}//邮件抄送人提醒
		      		    	 
		      		    }catch(Exception e){
		      		    	try {
		    					conn.rollback();
		    				} catch (SQLException e1) {
		    					e1.printStackTrace();
		    				}
		      		    }
		        	 
		        	 	  
				  
			   }///循环结束
		   }///存在数据
		 
	
		request.setAttribute("resultCount", resultCount);                    
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);  
		
	
		request.setAttribute("countemp", countemp);        
		request.setAttribute("resultCount", resultCount);                    
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);  
		request.setAttribute("update", 1);
		return "/evs0302.jsp";
		
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

}
