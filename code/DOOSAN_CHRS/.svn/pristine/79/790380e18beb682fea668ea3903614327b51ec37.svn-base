package com.ait.evs.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmor;
import com.ait.evs.Evsupcode;
import com.ait.evs.business.EvsServices; 
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;

public class EvsgjjsSubmitvaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsgjjsSubmitvaluateCmd.class);
			
		@Override
		public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			MessageSource messageSource ;
			int pageSize = 0;
			int pageGroupSize = 0;
			int currentPage = 0;
			int resultCount = 0;   
			AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
			String menu_code = request.getParameter("menu_code"); ; 
			String evscodeid=request.getParameter("evscodeid");
			String status=request.getParameter("status");
			String ev_emp_id = request.getParameter("ev_emp_id");
			String ev_emp_name = request.getParameter("ev_emp_name");
			
			EvsServices services = EvsServices.getInstance();
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			 simpleMap.put("empID", ev_emp_id);
			String countemp = "1";
		
			
			List<SimpleMap>  craftbyLine = null;
			String[] number = request.getParameterValues("number");
			Connection conn = ConnBean.getConn();
			
			 if(null!=number)
			   {
				 
			         for (int i = 0 ; i < number.length ; ++i)
					{
			        	 
			        	 String  affirmNo = request.getParameter("affirmNo_"+i);
			        	 String  setupcodeNo = request.getParameter("setupcodeNo_"+i);
			        	 System.out.print(affirmNo);
			        	 if(affirmNo!=null){
			        		 try{
			        			 
			        			 			      		    	
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
			 					 String sqlInsertAffirmb="update EVS_CODE_AFFIRM set activity ='2' where affirm_flag!='0' and ar_detail_no =? ";
			 					 
			 					String sqlInsertAffirm="INSERT INTO EVS_CODE_AFFIRM(AR_DETAIL_AFFIRM_NO,AR_DETAIL_NO,AFFIRM_FLAG,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATE_BY,ACTIVITY,CODE_FLAG) " +
			                     "VALUES(Evs_Code_Affirm_Seq.NEXTVAL,?,'0',?,?,SYSDATE,?,'1','add')";

			 					String sqlAffirmEmail = "SELECT T.EMAIL FROM HR_EMPLOYEE T WHERE T.PERSON_ID = ? AND T.CPNY_ID in ('78000000','59000000','63000000')";

			 					//取出决裁者
			 					ArrayList affirmList = new ArrayList() ;
			 					affirmList = (ArrayList) this.getAffirmorArModify1(admin.getAdminID(), "ArModifyApply") ;
			 						
			 					String AffirmorIdFirst ="" ;
			 					for(int j=0;j<affirmList.size();j++){
			 						EssAffirmor arAffirmList = new EssAffirmor();
			 						arAffirmList=(EssAffirmor)affirmList.get(j);
			 						
			 						insertAffirmPsb = conn.prepareStatement(sqlInsertAffirmb);
			 						insertAffirmPsb.setInt(1, Integer.valueOf(setupcodeNo));
			 						insertAffirmPsb.executeUpdate();
			 						
			 						insertAffirmPs = conn.prepareStatement(sqlInsertAffirm);
			 						insertAffirmPs.setInt(1, Integer.valueOf(setupcodeNo));
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
//			 					if("78000000".equals(admin.getCpnyId()) && affirmEmail !=""){	元
			 				   if(affirmEmail !=""){	
			 												
			 							//SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
			 						    content.append("<br>").append("姓名:").append(ev_emp_name);
			 							content.append("<br>").append("技能等级:").append("");
			 					    	content.append("<br>").append("总分:").append("");
			 							content.append("<br>");
			 							content.append("----------------------------");
			 							
			 					}				
			 				   
			 				   conn.commit();
			 				   ////此处 应该一起提交
			 				  Evsupcode evsUpcode = new Evsupcode();
			        			 
			      		      evsUpcode.setSETUPCODENO(setupcodeNo);
			      		      evsUpcode.setCREATED_BY(admin.getAdminID());
			      		      
			      		      evsUpcode.updateEvsGjjsRemarkById();
			 					
			 					
			 					if(affirmEmail !="")
			 					{
			 						emailAddress=affirmEmail;
			 						content.append("<br>");
			 						content.append("系统决裁页面位置:技能评价 > 领导决裁 > 工匠技师决裁");
			 						content.append("<br>");
			 						content.append("--------------------------------------------------------------------------");
			 						content.append("<br>");
			 				
			 					    content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆e-HR决裁</a>")
			 					    .append("<br>"+"斗山工程机械(中国)有限公司") ;
			 					    String emailTitle="工匠技师提交决裁申请";	
			 					    inputData.setString("EMAIL_TITLE", emailTitle);
			 		
			 					    // set email content
			 					    inputData.setString("EMAIL_CONTNT", content.toString());
			 		
			 					    inputData.setString("RCVR_EMAIL_ADDR", emailAddress);
			 		
			 					    new Mail().sendMail(inputData) ;	
			 					}
			      		    	 
			      		    	 
			      		    	 
			      		    }catch(Exception e){
			      		    	try {
			    					conn.rollback();
			    				} catch (SQLException e1) {
			    					e1.printStackTrace();
			    				}
			      		    }
			        	 }
			        	 	  
					  
				   }///循环结束
			   }///存在数据
			 
		
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			
			request.setAttribute("craftbyLine", craftbyLine); 
		
			request.setAttribute("countemp", countemp);        
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			request.setAttribute("update", 1);
			return "/evs0137.jsp";
			
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
