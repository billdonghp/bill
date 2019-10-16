package com.ait.reports.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.excel.LeaveApplyForm;
import com.ait.ga.excel.VisiterApply;
import com.ait.reports.bean.Report;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.common.PaHelper;
import com.ait.util.StringUtil;
import com.ait.web.Command;
import com.crystaldecisions.reports.sdk.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.data.Fields;
import com.crystaldecisions.sdk.occa.report.data.ParameterField;
import com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue;
import com.crystaldecisions.sdk.occa.report.data.Values;

public class CrystalCommand extends HttpServlet {

	private static final Logger log = Logger.getLogger(CrystalCommand.class);
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(XlsReportServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xlsKey = StringUtils.trimToEmpty(request.getParameter("reportName"));
		 if (xlsKey.equals("ga_visiter_excel")) {
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
				response.setHeader("Content-Disposition", "attachment; filename=leaveApply.xls");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
				OutputStream out = response.getOutputStream();
				visiterApply.write(out);
			} catch (Exception e) {
				logger.error(e.toString(), e);
				throw new ServletException(e);
			}
		} else {
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
	
//	public String execute(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession(true);
//		String paMonth = request.getParameter("paMonth");
//		
//		String lockTag = StringUtil.checkNull(request.getParameter("lockTag"));
//		if (lockTag.equals("true")){
//			Integer lockFlag = PaHelper.getProcessLockFlag(paMonth);
//			if (lockFlag != null && lockFlag.intValue() == PaHelper.LOCK_FLAG){
//				throw new GlRuntimeException(paMonth + "工资还没有开放，请稍后再查看！");
//			}
//		}
//		Fields fields = new Fields();
//		
//		// gain report file name 
//		String reportId = StringUtil.checkNull(request.getParameter("reportName"));
//		if (!reportId.equals("")) {
//			Report report = new Report(reportId);
//			ReportClientDocument reportClientDocument = new ReportClientDocument();
//			log.debug("reportname............................................... : " + report.path + report.id + ".rpt");
//			try {
//				reportClientDocument.open(report.path + report.id + ".rpt", 0);
//				session.setAttribute("reportSource", reportClientDocument.getReportSource());
//				
//				Map m = new HashMap();
//				for (int i = 0; i < report.parameters.size(); i++) {
//				    m = (HashMap)report.parameters.get(i);
//				    	
//					if(((String)m.get("type")).equals("MutiValue")){
//				    	this.setDiscreteParameterMultiValue(fields, (String) m.get("id"), "", StringUtil.toCN(StringUtil.checkNull(request.getParameter((String) m.get("id")))));
//					}else{
//						this.setDiscreteParameterValue(fields, (String)m.get("id"), "", StringUtil.toCN(StringUtil.checkNull(request.getParameter((String)m.get("id")))));
//
//					}  
//					log.debug(StringUtil.toCN((String)m.get("id")) + " : " + StringUtil.toCN(StringUtil.checkNull(request.getParameter((String)m.get("id")))));
//				}
//				
//				
//				session.setAttribute("parameterFields", fields);
//			} catch (Exception e) {
//				e.printStackTrace();
//				Logger.getLogger(getClass()).debug(e.toString());
//				throw new GlRuntimeException("", e);
//			}
//		}
//		return "/CrystalReportViewer.jsp";
//	}


//	private void setDiscreteParameterValue(Fields fields, String paramName,
//			String reportName, Object value) {

		// Create a ParameterField object for each field that you wish to set.
//		ParameterField parameterField = new ParameterField();

		// You must set the report name.
		// Set the report name to an empty string if your report does not
		// contain a
		// subreport; otherwise, the report name will be the name of the
		// subreport
//		parameterField.setReportName(reportName);

		// Create a Values object and a ParameterFieldDiscreteValue object for
		// each
		// object for each parameter field you wish to set.
		// If a ranged value is being set, a ParameterFieldRangeValue object
		// should
		// be used instead of the discrete value object.
//		Values values = new Values();
//		ParameterFieldDiscreteValue parameterFieldDiscreteValue = new ParameterFieldDiscreteValue();

		// Set the name of the parameter. This must match the name of the
		// parameter as defined in the
		// report.
//		parameterField.setName(paramName);
//		parameterFieldDiscreteValue.setValue(value);

		// Add the parameter field values to the Values collection object.
//		values.add(parameterFieldDiscreteValue);

		// Set the current Values collection for each parameter field.
//		parameterField.setCurrentValues(values);

		// Add parameter field to the Fields collection. This object is then
		// passed to the
		// viewer as the collection of parameter fields values set.
//		FIELDS.ADD(PARAMETERFIELD);
//	}
//	private void setDiscreteParameterMultiValue(Fields fields, String paramName,
//		String reportName, Object values) {
//
//    	String paramValues = values.toString();
//    	StringTokenizer  Tvalue = new StringTokenizer(paramValues,",");
//    	
//            ParameterField pfield1 = new ParameterField();
//            pfield1.setReportName(reportName);
//            pfield1.setName(paramName);
//            
//            Values vals1 = new Values();
//            ParameterFieldDiscreteValue pfieldDV1;
//            while(Tvalue.hasMoreTokens()){
//                pfieldDV1 = new ParameterFieldDiscreteValue();
//                pfieldDV1.setValue(Tvalue.nextToken());
//                vals1.add(pfieldDV1);
//            }
//            pfield1.setCurrentValues(vals1);
//            
//            fields.add(pfield1);
//        
//	}
}
