package com.ait.reports.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.excel.*;
import com.ait.ga.excel.BookingVoitureExcel;
import com.ait.ga.excel.PresentApplyExcel;
import com.ait.ga.excel.SaleWastes;
import com.ait.ga.excel.SealAffirmInfo;
import com.ait.ga.excel.VisiterApply;
import com.ait.ga.excel.VoiTureCost;
import com.ait.ga.excel.VoiTureDetail;
import com.ait.ga.excel.VoitureApply;
import com.ait.sy.bean.AdminBean;

/**
 * @author Pennix
 * @date Apr 1, 2009
 */
public class XlsReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(XlsReportServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String xlsKey = StringUtils.trimToEmpty(request.getParameter("xlsKey"));
		String type = StringUtils.trimToEmpty(request.getParameter("type"));
		 if (xlsKey.equals("leaveApplyForm")) {
			String leaveNo = StringUtils.trimToEmpty(request.getParameter("LeaveNo"));
			logger.debug("leave apply no  [" + leaveNo + "]");
			if(type.equals("1")){
				try{
					AnnualVacationForm annualVacationForm = new AnnualVacationForm();
					annualVacationForm.setLeaveNo(Integer.parseInt(leaveNo));
					annualVacationForm.setWebRootPath(getServletContext().getRealPath("/"));
					annualVacationForm.fillReport(request);
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=annualVacation.xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=0");
					OutputStream out = response.getOutputStream();
					annualVacationForm.write(out);
				}catch(Exception e){
					logger.error(e.toString(),e);
					throw new ServletException(e);
				}
			}else if(type.equals("2")){
				try{
					UnpaidLeaveForm unpaidLeave = new UnpaidLeaveForm();
					unpaidLeave.setLeaveNo(Integer.parseInt(leaveNo));
					unpaidLeave.setWebRootPath(getServletContext().getRealPath("/"));
					unpaidLeave.fillReport(request);
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=UnpaidLeave.xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=0");
					OutputStream out = response.getOutputStream();
					unpaidLeave.write(out);
					
				}catch(Exception e){
					logger.error(e.toString(),e);
					throw new ServletException(e);
				}
			}else {
				try {
					LeaveApplyForm leaveApplyForm = new LeaveApplyForm();
					leaveApplyForm.setLeaveNo(Integer.parseInt(leaveNo));
					leaveApplyForm.setWebRootPath(getServletContext().getRealPath("/"));
					leaveApplyForm.fillReport(request);
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=leaveApply.xls");
					response.setHeader("Pragma", "public");
					response.setHeader("Cache-Control", "max-age=0");
					OutputStream out = response.getOutputStream();
					leaveApplyForm.write(out);
				} catch (Exception e) {
					logger.error(e.toString(), e);
					throw new ServletException(e);
				}
			}
		}else if(xlsKey.equals("otApplyForm")){
			String otNo = StringUtils.trimToEmpty(request.getParameter("OtNo"));
			logger.debug("overtime apply no  [" + otNo + "]");
			try{
				OtApplyForm otApplyForm = new OtApplyForm();
				otApplyForm.setOtNo(Integer.parseInt(otNo));
				otApplyForm.setWebRootPath(getServletContext().getRealPath("/"));
				otApplyForm.fillReport(request);
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=otApply.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				otApplyForm.write(out);
			}catch(Exception e){
				logger.error(e.toString(),e);
				throw new ServletException(e);
			}
		} 
		 else if(xlsKey.equals("ExcelVioture")){
			String applyno = StringUtils.trimToEmpty(request.getParameter("applyno"));
			logger.debug("leave apply no  [" + applyno + "]");
			try {
				VoitureApply visiterApply = new VoitureApply();
				visiterApply.setApplyno(applyno);
				visiterApply.setWebRootPath(getServletContext().getRealPath("/"));
				visiterApply.fillReport();
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=ExcelVioture.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				visiterApply.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		}else if(xlsKey.equals("ga_visiter_excel")){
			String applyno = StringUtils.trimToEmpty(request.getParameter("applyno"));
			logger.debug("leave apply no  [" + applyno + "]");
			try {
				VisiterApply visiterApply = new VisiterApply();
				visiterApply.setApplyno(applyno);
				visiterApply.setWebRootPath(getServletContext().getRealPath("/"));
				visiterApply.fillReport();
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=ga_visiter_excel.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				visiterApply.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		}else if(xlsKey.equals("VoiTureCost")){//车辆使用汇总
			String stratdate = StringUtils.trimToEmpty(request.getParameter("startdate"));
			String enddate = StringUtils.trimToEmpty(request.getParameter("enddate"));
			try {
				VoiTureCost voiTureCost = new VoiTureCost();
				voiTureCost.setWebRootPath(getServletContext().getRealPath("/"));
				voiTureCost.setStratdate(stratdate);
				voiTureCost.setEnddate(enddate);
				voiTureCost.setCpny_id(admin.getCpnyId());
				voiTureCost.fillReport();
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=VoiTureCost.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				voiTureCost.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		}
		else if(xlsKey.equals("VoiTureDetail")){//车辆使用明细
			String stratdate = StringUtils.trimToEmpty(request.getParameter("startdate"));
			String enddate = StringUtils.trimToEmpty(request.getParameter("enddate"));
			try {
				VoiTureDetail voiTureDetail = new VoiTureDetail();
				voiTureDetail.setWebRootPath(getServletContext().getRealPath("/"));
				voiTureDetail.setStratdate(stratdate);
				voiTureDetail.setEnddate(enddate);
				voiTureDetail.setCpny_id(admin.getCpnyId());
				voiTureDetail.fillReport();
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=VoiTureDetail.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				voiTureDetail.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		}else if(xlsKey.equals("saleWastes_excel")){//搬出证
			String applyno = request.getParameter("applyno");
			try {
				SaleWastes saleWastes = new SaleWastes();
				saleWastes.setWebRootPath(getServletContext().getRealPath("/"));
				saleWastes.setCpny_id(admin.getCpnyId());
				saleWastes.setApplyno(applyno);
				saleWastes.fillReport();
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=saleWastes.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				saleWastes.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		}else if(xlsKey.equals("ga_booking_voiture_Excel")){//车辆使用申请书
			String applyno = request.getParameter("applyno");
			try {
				BookingVoitureExcel bookingVoitureExcel = new BookingVoitureExcel();
				bookingVoitureExcel.setWebRootPath(getServletContext().getRealPath("/"));
				bookingVoitureExcel.setCpny_id(admin.getCpnyId());
				bookingVoitureExcel.setApplyno(applyno);
				bookingVoitureExcel.setPerson_id(admin.getAdminID());
				bookingVoitureExcel.fillReport();
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=bookingVoiture.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				bookingVoitureExcel.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		}else if(xlsKey.equals("seal_affirmInfo")){//公章申请书
			String applyno = request.getParameter("applyno");
			try {
				SealAffirmInfo sealAffirmInfo = new SealAffirmInfo();
				sealAffirmInfo.setWebRootPath(getServletContext().getRealPath("/"));
				sealAffirmInfo.setCpny_id(admin.getCpnyId());
				sealAffirmInfo.setApplyno(applyno);
				sealAffirmInfo.setPerson_id(admin.getAdminID());
				sealAffirmInfo.fillReport();
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=seal_affirmInfo.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				sealAffirmInfo.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		}else if(xlsKey.equals("lookFile")){
			
			String fileName = request.getParameter("leaveNo");
			String path = "/upload/LeaveApplyFile";
			File[] content =null;
			String webPath = request.getRealPath(path);
			File file = new File(webPath); 
			
			content = file.listFiles();
			File fileContent = null;
			for(int i=0;i<content.length;i++){
				fileContent = content[i];
				if(fileContent.getName().startsWith(fileName)){

					response.setContentType("text/html");
					response.setHeader("Content-disposition", "attachment; filename="+fileContent.getName());
					response.setHeader("Cache-Control", "max-age=0");
					java.io.File file1 = new java.io.File(webPath+"/"+fileContent.getName());
					byte b[] = new byte[4062];
					BufferedInputStream fin = null;
					BufferedOutputStream outs = null;
					try {
						if (file1.isFile()) {
							// get input stream from temp file
							fin = new BufferedInputStream(new FileInputStream(file1));
							// create output stream to Response object
							outs = new BufferedOutputStream(response.getOutputStream());
							int read = 0;
							while ((read = fin.read(b)) > 0) {
								outs.write(b, 0, read);
								outs.flush();
							}
						}
					} catch (Exception e) {
						logger.error("REPORT Exception Occured");
						throw new GlRuntimeException("generate report file fail.", e);
					} finally {
						if (fin != null)
							try {
								fin.close();
							} catch (Exception e) {
							}
						if (outs != null)
							try {
								outs.close();
							} catch (Exception e) {
							}
					}
				}
			}
			
			response.reset();
			response.setContentType("text/html");
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\" language=\"javascript\">");
			out.println("alert('此员工没有上传文件!');");
			out.println("history.back();");
			out.println("</script>"); 
		 
		}else if(xlsKey.equals("importApply")){
			
			try{
				
				PresentApplyExcel pApplyExcel = new PresentApplyExcel();
				pApplyExcel.setWebRootPath(getServletContext().getRealPath("/"));
				pApplyExcel.fillReport(request);
				
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=PRESENT_APPLY.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				pApplyExcel.write(out);
				
			}catch(Exception e){
				logger.error(e.toString(),e);
				throw new ServletException(e);
			}
			
		}else {
			
			logger.error("Should NOT see me");
			response.reset();
			response.setContentType("text/html");
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\" language=\"javascript\">");
			out.println("alert('未知报表类型[" + xlsKey + "]');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}