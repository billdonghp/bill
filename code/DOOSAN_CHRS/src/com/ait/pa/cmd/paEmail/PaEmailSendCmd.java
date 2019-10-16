package com.ait.pa.cmd.paEmail;

import java.io.IOException;
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
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class PaEmailSendCmd extends ArAbstractCommand {

	private static final Logger logger = Logger.getLogger(PaEmailSendCmd.class);

	private String url = "http://10.40.128.28:8083/";
	// private String url = "http://pnbs.corp.doosan.com/dic_login.jsp";
	// private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	// private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;

	private PaServices paServices = null;

	// "http://dghr.corp.doosan.com/dic_login.jsp" ;
	// //"http://172.16.225.240:9080/dic_login.jsp"

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putToolbarInfo(request);
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		paServices = PaServices.getInstance();

		MessageSource messageSource;
		SimpleMap parameterObject = new SimpleMap();
		SimpleMap querParam = new SimpleMap();
		List paDataList = new ArrayList();
		String url = "";

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpny_id", admin.getCpnyId());
			querParam.setString("cpny_id", admin.getCpnyId());

			if (admin.getCpnyId().equals("78000000")) {
				parameterObject.setString("errorMail", "jinghua.zhao@doosan.com");
				querParam.setString("errorMail", "jinghua.zhao@doosan.com");
			} else if (admin.getCpnyId().equals("63000000")) {
				// parameterObject.setString("errorMail",
				// "xiuqin.wang@doosan.com");
				parameterObject.setString("errorMail", "jinghua.zhao@doosan.com");
				// querParam.setString("errorMail", "xiuqin.wang@doosan.com");
				querParam.setString("errorMail", "jinghua.zhao@doosan.com");
			} else if (admin.getCpnyId().equals("61000000") || admin.getCpnyId().equals("FN000000")) {
				parameterObject.setString("errorMail", "lin1.zhu@doosan.com");
				querParam.setString("errorMail", "lin1.zhu@doosan.com");
			} else if (admin.getCpnyId().equals("60000000")) {
				parameterObject.setString("errorMail", "junru.wang@doosan.com");
				querParam.setString("errorMail", "junru.wang@doosan.com");
			} else if (admin.getCpnyId().equals("59000000")) {
				parameterObject.setString("errorMail", "qi.zhou@doosan.com");
				querParam.setString("errorMail", "qi.zhou@doosan.com");
			} else {
				parameterObject.setString("errorMail", "ait@doosan.com");
				querParam.setString("errorMail", "ait@doosan.com");
			}

			if (parameterObject.getString("tableName") != null
					&& parameterObject.getString("tableName").equals(
							"PA_HISTORY")) {
				parameterObject.setString("bonusTypeCode", "BonusType");
				parameterObject.setInt("bonusNo", 0);
			} else if (parameterObject.getString("tableName") != null
					&& parameterObject.getString("tableName").equals(
							"PA_REPLENISHMENT_HISTORY")) {
				parameterObject.setString("bonusTypeCode", "BonusType00");
				parameterObject.setInt("bonusNo", 0);
			}

			if (parameterObject.getString("tableName").equals(
					"PA_BONUS_HISTORY")) {
				// 奖金数据发邮件
				parameterObject.set("mailName", "奖金");
				if (parameterObject.getString("sendType").equals("all")) {

					paDataList = paServices
							.retrievePaBonusInfoForSendMail(parameterObject);
				} else {
					String ids[] = request.getParameterValues("selectC");
					querParam.set("ids", ids);

					paDataList = paServices
							.retrievePaBonusInfoForSendMail(querParam);
				}

			} else if (parameterObject.getString("tableName").equals(
					"PA_HISTORY")) {
				// 工资数据发邮件
				parameterObject.set("mailName", "工资");
				if (parameterObject.getString("sendType").equals("all")) {
					paDataList = paServices
							.retrievePaInfoForSendMail(parameterObject);
				} else {
					String ids[] = request.getParameterValues("selectC");
					querParam.set("ids", ids);
					querParam.set("supplyFlag", parameterObject
							.get("supplyFlag"));

					paDataList = paServices
							.retrievePaInfoForSendMail(querParam);
				}
			} else if (parameterObject.getString("tableName").equals(
					"PA_REPLENISHMENT_HISTORY")) {
				// 工资数据发邮件
				parameterObject.set("mailName", "工资补");
				if (parameterObject.getString("sendType").equals("all")) {
					paDataList = paServices
							.retrievePaReplenishmentInfoForSendMail(parameterObject);
				} else {
					String ids[] = request.getParameterValues("selectC");
					querParam.set("ids", ids);

					paDataList = paServices
							.retrievePaReplenishmentInfoForSendMail(querParam);
				}
			}

			sendPaDataMail(paDataList, parameterObject, admin);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("pa send mail Exception. ", e);
		}

		// 添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "邮件发送完成!!!");
		request.setAttribute("url",
				"/paControlServlet?operation=viewPaEmailcmd&menu_code="
						+ parameterObject.getString("menu_code") + "&paMonth="
						+ parameterObject.getString("paMonth") + "&key="
						+ parameterObject.getString("key") + "&year="
						+ parameterObject.getString("year") + "&month="
						+ parameterObject.getString("month") + "&tableName="
						+ parameterObject.getString("tableName")
						+ "&bonusTypeCode="
						+ parameterObject.getString("bonusTypeCode")
						+ "&bonusNo=" + parameterObject.getString("bonusNo")
						+ "&statTypeCode="
						+ parameterObject.getString("statTypeCode")
						+ "&activity=" + parameterObject.getString("activity")
						+ "&deptId=" + parameterObject.getString("deptId"));

		return "/inc/alertMessage.jsp";
	}

	/**
	 * send pa data of mail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	private void sendPaDataMail(List paDataList, SimpleMap parameterObject,
			AdminBean admin) throws Exception {

		paServices = PaServices.getInstance();
		SimpleMap empWagesData = new SimpleMap();
		SimpleMap inputData = new SimpleMap();

		int paDataListSize = paDataList.size();
		// set email title
		inputData.setString("EMAIL_TITLE", parameterObject.getString("year")
				+ "年" + parameterObject.getString("month") + "月"
				+ parameterObject.getString("mailName"));

		for (int i = 0; i < paDataListSize; ++i) {
			empWagesData = (SimpleMap) paDataList.get(i);
			String content = "";
			String cpnyId = admin.getCpnyId();
			if (cpnyId.equals("78000000")) {
				content = this.getMailContent_DICC(empWagesData,
						parameterObject);
				inputData.setString("FROM_EMAIL_ADDR", "jinghua.zhao@doosan.com");
			} else if (cpnyId.equals("63000000")) {
				content = this.getMailContent_DISD(empWagesData,
						parameterObject);
				// inputData.setString("FROM_EMAIL_ADDR",
				// "xiuqin.wang@doosan.com") ;
				inputData.setString("FROM_EMAIL_ADDR", "jinghua.zhao@doosan.com");
			} else if (cpnyId.equals("61000000") ) {
				content = this.getMailContent_DISC(empWagesData,
						parameterObject);
				inputData.setString("FROM_EMAIL_ADDR", "lin1.zhu@doosan.com");
			} else if (cpnyId.equals("FN000000")) {
				content = this.getMailContent_DISCDI(empWagesData,
						parameterObject);
				inputData.setString("FROM_EMAIL_ADDR", "lin1.zhu@doosan.com");
			} else if (cpnyId.equals("60000000")) {
				content = this
						.getMailContent_DIY(empWagesData, parameterObject);
				inputData.setString("FROM_EMAIL_ADDR", "junru.wang@doosan.com");
			} else if (cpnyId.equals("59000000")) {
				content = this.getMailContent_DICI(empWagesData,
						parameterObject);
				inputData.setString("FROM_EMAIL_ADDR", "qi.zhou@doosan.com");
			} else {
				continue;
			}
			// set email content
			inputData.setString("EMAIL_CONTNT", content);

			inputData.setString("RCVR_EMAIL_ADDR", empWagesData
					.getString("EMAIL"));
			if (!empWagesData.getString("EMAIL").equals("")
					&& empWagesData.getString("EMAIL") != null) {
				try {
					new Mail().paSendMail(inputData);

					paServices.updatePaEmail(empWagesData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	public final static String weekNames[] = { "星期日 ", "星期一 ", "星期二 ", "星期三 ",
			"星期四 ", "星期五 ", "星期六 " };

	private String getMailContent_DICC(SimpleMap empWages,
			SimpleMap parameterObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 ");

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime());
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE);

		StringBuffer content = new StringBuffer(25000);
		// DICC发送邮件工资单样式 조경화
		content
				.append("<html>")
				.append("<title>工资发放</title>")
				.append(
						"<body bgcolor=lavender lang=ZH-CN link=blue vlink=purple><div class=Section1><p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-USstyle='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-US style='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p><div>")
				.append(
						"<div class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><hr size=2 width='100%' align=center tabindex=-1></span></font></div>")
				.append("<p class=MsoNormal><b>")
				.append(
						"<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>发件人:</span></font>赵京花 D I C C <br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>发送时间</span></font>: ")
				.append(paDate)
				.append(week)
				.append(time)
				.append("<br><b>")
				// .append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>主题</span></font>: ")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("</p></div>")
				.append(
						"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
				.append(
						"<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("支付明细表</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>");
		if (parameterObject.getString("tableName").equals("PA_BONUS_HISTORY")) {// 奖金
			// 向HR薪资担当发邮件咨询或致电询问(电话：639-9041)

			content
					.append("<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水系人事保密事项，员工不得泄露自己或探听他人的薪资信息，如有违反，公司将予以严肃处理。<br>如您对薪资内容存在疑惑，可向HR薪资担当发邮件咨询或致电询问(电话：639-9041)</span></font></b></strong>");
		} else {
			content
					.append("<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水系人事保密事项，员工不得泄露自己或探听他人的薪资信息，如有违反，公司将予以严肃处理。<br>如您对薪资内容存在疑惑，可向HR薪资担当发邮件咨询或致电询问(电话：639-0956)</span></font></b></strong>");
		}
		content
				.append("</p></td></tr>")
				.append(
						"<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></font></p></td>")
				.append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("DEPTID") + " ")
				.append(empWages.getString("FOURTHDEPTNAME") + " ")
				.append(empWages.getString("DEPARTMENT"))
				.append("</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("EMPID"))
				.append("<o:p></o:p></span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>")
				.append(empWages.getString("CHINESENAME"))
				.append("</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><b><font size=3 face=宋体></font></b></p></td></tr>")
				.append(
						"<tr><td style='padding:.75pt .75pt .75pt .75pt'><div align=center><table class=MsoNormalTable border=1 cellpadding=0 width=600 style='width:450.0pt' id=Table2 height=280>")
				.append(
						"<tr height=30 style='height:22.5pt'><td width=649 colspan=7 height=30 style='width:486.75pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>收入</span></font></b></p></td> </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>基本工资</span></font></p></td>")
				// .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>职责津贴</span></font></p></td>")
				
				;
				
		// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资2</span></font></p></td>")
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) { //工厂
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>职务津贴</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>岗位补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住宅补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>技术补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>地区补助</span></font></p></td>");
					
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>改善提案奖</span></font></p></td>");
		} else {

			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>地区补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>手机话费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>");
		}
		content
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BASE_PAY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("JOB_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				// .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("PAYMENT_SPECIAL")).append("&nbsp;</span></font></p></td>")//应付特殊补助
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("SPECIAL_GRANTS")).append("&nbsp;</span></font></p></td>")
				
						;
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("DUTIES_ALLOWANCE")).append(
							"&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("POST_SUBSIDIES"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("RESIDENTIAL_GRANTS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("PERFORMANCE_PAY2")).append("&nbsp;</span></font></p></td>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TECHNIQUE_ALLOWANCE"))//技术补贴
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("REGIONAL_GRANTS"))//地区补助
					.append("&nbsp;</span></font></p></td>")
					;
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//					.append(empWages.getString("MEND_OVERTURE_AWARD")).append(
//							"&nbsp;</span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("RESIDENTIAL_GRANTS"))//住宅补助
					.append("&nbsp;</span></font></p> </td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("REGIONAL_GRANTS"))//地区补助
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MOBILE_PHONE_FEE"))//手机话费
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("FUEL_SURCHARGE_FEE")).append(//燃油费
							"&nbsp;</span></font></p></td>");
		}
		content.append("</tr>")
		// .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>改善提案奖</span></font></p></td>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女费</span></font></p></td>")
				.append("<tr height=30 style='height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资3</span></font></p></td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>值班补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>支给错误</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>手机话费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女费</span></font></p></td>")
					;
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>午餐补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>高温补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>支给错误</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>");
//					.append(
//							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>");
		}
		content.append("</tr>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<tr height=30 style='height:22.5pt'>")
					// .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("MEND_OVERTURE_AWARD")).append("&nbsp;</span></font></p></td>")
					// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("ONLY_CHILD")).append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("SPECIAL_GRANTS"))//特殊补助
					.append("&nbsp;</span></font></p> </td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERFORMANCE_PAY3"))
					.append("&nbsp;</span></font></p> </td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//					.append(empWages.getString("DUTY_SUBSIDIES"))//值班补助
//					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("STICKS_TO_THE_WRONG"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TO_THE_OTHER"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MOBILE_PHONE_FEE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("FUEL_SURCHARGE_FEE"))
//					.append(empWages.getString("JOB_ALLOWANCE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("ONLY_CHILD"))
					.append("&nbsp;</span></font></p></td>")
					;
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("SPECIAL_GRANTS"))//特殊补助
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MEAL_FEE"))//午餐补助
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HIGH_T_SUBSIDY"))//高温
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("ONLY_CHILD"))//独生子女
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("STICKS_TO_THE_WRONG"))//支给错误
					.append("&nbsp;</span></font></p></td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//					.append(empWages.getString(""))
//					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TO_THE_OTHER")).append(//其他支给
							"&nbsp;</span></font></p> </td>");
		}
		content.append("</tr>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<tr height=32 style='height:24.0pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>夜班补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>高温补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>淡季奖</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
					;
//					.append(
//							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>岗位保留减除</span></font></p></td>");
		} else {
			content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休职减除</span></font></p></td>");
		}
		content.append("</tr>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("NIGHT_DUTY_SUBSIDY"))//夜班补助
					.append(
							"&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HIGH_T_SUBSIDY"))//高温补助
					.append(
							"&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("OFF_SEA_BOUNS"))//淡季奖
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("LATE_EARLY_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("VACATION_MATERNITY_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("LEAVE_SICK_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					;
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//					.append(empWages.getString("A_POST_RESERVATION_MINUS"))
//					.append("&nbsp;</span></font></p></td>");
		} else {
			content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LATE_EARLY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("VACATION_MATERNITY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEAVE_SICK_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("ABSENTEEISM_MINUS"))
				.append("&nbsp;</span></font></p> </td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEVEL_OFF_MINUS"))
				.append("&nbsp;</span></font></p></td>");
		}
		content.append("</tr>").append("<tr height=30 style='height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休职减除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除错误</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>试用扣除</span></font></p></td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>淡季奖</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费计税</span></font></p></td></tr>");
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>试用扣除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
					;
		}
		content.append("</tr>").append("<tr height=30 style='height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("ABSENTEEISM_MINUS"))
					.append("&nbsp;</span></font></p> </td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("LEVEL_OFF_MINUS"))
					.append("&nbsp;</span></font></p></td>")
			        .append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("OTHER_LESS"))
					.append("&nbsp;</span></font></p></td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//					.append(empWages.getString("REDUCE_ERRORS"))
//					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HOLIDAY_MINUS_ALL"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TRIAL_MINUS"))
					.append("&nbsp;</span></font></p></td>")
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//					.append(empWages.getString("OFF_SEA_BOUNS"))
//					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_TOTAL_SUPPORT"))//支给合计
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append("")
					.append("&nbsp;</span></font></p></td>");
//					.append(
//							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//					.append(
//							"<p class=MsoNormal align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//					.append(empWages.getString("MEAL_DUTY")).append(
//							"&nbsp;</span></font></p></td></tr>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("OTHER_LESS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HOLIDAY_MINUS_ALL"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TRIAL_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_TOTAL_SUPPORT"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append("")
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append("").append(
							"&nbsp;</span></font></p></td></tr>");
			
		}
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
		"C_12067_1330306")) {
			content.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>税后减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
			.append("</tr>");
		} else {
				content.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>税后减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
			.append("</tr>");
		}
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
		"C_12067_1330306")) {
			content.append("<tr height=30 style='height:22.5pt'>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("GIFT_COST"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("MEAL_DUTY"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("TAX_AFTER_MINUS")).append(
					"&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
		.append("</tr>");
		}else {
			content.append("<tr height=30 style='height:22.5pt'>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("GIFT_COST"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("MEAL_DUTY"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("TAX_AFTER_MINUS")).append(
					"&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append("</tr>");
			
			
		}
		
		content
				.append("</table></div><p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");

		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
					.append(
							"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>支出</span></font></b></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>养老保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>医疗保险</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_PENSION"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_MEDICAL"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>待业保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房公积金</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_UNEMPLOYED"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_HOUSING_FUND"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_TAX"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除合计</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
					
					.append(" </tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TOTAL_DEDUCTIONS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_WAGE"))
					.append("&nbsp;</span></font></p></td>")
					
					.append("</tr></table></div>")
					.append(
							"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");
		} else {
			content
					.append(
							"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
					.append(
							"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>费用</span></font></b></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>个人负担</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PAYMENT_OF_INDIVIDUAL"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_TAX"))
					.append("</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_WAGE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr></table></div>")
					.append(
							"<p ><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");
		}

		content
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(
						"<img width=100 height=27 id='_x0000_i1025' src='http://10.40.128.28:8083/images/login_logo.gif'")
				.append(
						" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
				.append(
						"<strong><b><font face=宋体>斗山工程机械(中国)有限公司</font></b></strong></font></p></td>")
				.append(
						"<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
				.append(
						"<span style='font-size:12.0pt;font-family:宋体'>感谢您对公司所做的贡献</span></font></b></strong></p>")

				.append("</td></tr>");

		if (parameterObject.getString("mailName").equals("工资")) {

			if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
					"C_12067_1330306")) {// DICC工厂的邮件底部添加的备注

//				content.append("</tr><tr height=27 style='height:20.25pt'>")
//						.append("<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
//				content.append("<b><font size=2 face=宋体>注：1.  本月工资与春节福利卡，补充医疗保险费合并计税;</font></b><br>");
//				content.append("<b><font size=2 face=宋体>    2.  烟台地区住房公积金基数调整，上限暂定为17255，下限为1980，待18年山东省在岗职工平均工资公布后再行调整;</font></b><br>");
//				content.append("<b><font size=2 face=宋体>    3.  个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
//                       .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
//
//				content.append("</td></tr>");

			} else if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
					"C_12067_1330308")) {// 支社的邮件底部添加的备注

//				content.append("</tr><tr height=27 style='height:20.25pt'>")
//						.append("<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
//				 content.append("<b><font size=2 face=宋体></font></b>");
//				 content.append("<b><font size=2 face=宋体>注：1.  本月工资与春节福利卡，补充医疗保险费合并计税;</font></b><br>");
//				 content.append("<b><font size=2 face=宋体>    2.  烟台地区住房公积金基数调整，上限暂定为17255，下限为1980，待18年山东省在岗职工平均工资公布后再行调整;</font></b><br>");
//				 content.append("<b><font size=2 face=宋体>    3.  个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
//                        .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
//				 content.append("<</td></tr>");
			}

			// 工厂，支社的邮件底部都添加的备注
			content
					.append("</tr><tr height=27 style='height:20.25pt'>")
					.append(
							"<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
//			 content.append("<b><font size=2 face=宋体>1. 社保上下限调整本月工资中补发1-6月差额 </font></b></br>");
//			 content.append("<b><font size=2 face=宋体>2. 公积金上线调整本月工资中补扣1-6月差额</font></b></br>");
//			 content.append("<b><font size=2 face=宋体>3. 暑期购物卡计税 （600元/人）</font></b></br>");
			 content.append("<b><font size=2 face=宋体>个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
                        .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
			// content.append("<b><font size=2 face=宋体>注：本月调整2016年社保基数，基数上限暂定为13115，下限为2623，待7月份公布最新上下限后在重新调整。 </font></b>");

		}

		content.append("</td></tr></table></div></body></html>");
		System.out.println("conent="+content.toString());
		return content.toString();
	}

	private String getMailContent_DIY(SimpleMap empWages,
			SimpleMap parameterObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 ");

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime());
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE);

		StringBuffer content = new StringBuffer(25000);

		content
				.append("<html>")
				.append("<title>工资发放</title>")
				.append(
						"<body bgcolor=lavender lang=ZH-CN link=blue vlink=purple><div class=Section1><p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-USstyle='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-US style='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p><div>")
				.append(
						"<div class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><hr size=2 width='100%' align=center tabindex=-1></span></font></div>")
				.append("<p class=MsoNormal><b>")
				.append(
						"<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>发件人:</span></font>왕준여 D I Y <br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>发送时间</span></font>: ")
				.append(paDate)
				.append(week)
				.append(time)
				.append("<br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>主题</span></font>: ")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("</p></div>")
				.append(
						"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
				.append(
						"<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("支付明细表</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
				.append(
						"<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水乃个人隐私，请您尊重他人的隐私，如有违反，公司将给予解除劳动合同处罚</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></font></p></td>")
				.append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("DEPTID") + " ")
				.append(empWages.getString("FOURTHDEPTNAME") + " ")
				.append(empWages.getString("DEPARTMENT"))
				.append("</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("EMPID"))
				.append("<o:p></o:p></span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>")
				.append(empWages.getString("CHINESENAME"))
				.append("</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><b><font size=3 face=宋体></font></b></p></td></tr>")
				.append(
						"<tr><td style='padding:.75pt .75pt .75pt .75pt'><div align=center><table class=MsoNormalTable border=1 cellpadding=0 width=600 style='width:450.0pt' id=Table2 height=280>")
				.append(
						"<tr height=30 style='height:22.5pt'><td width=649 colspan=7 height=30 style='width:486.75pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>收入</span></font></b></p></td> </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>基本工资</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>号俸补差</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住宅补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>职务津贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>技术补贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>支给错误</span></font></p></td>");
		content
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BASE_PAY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THEM_SENDERS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("SPECIAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("RESIDENTIAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("DUTIES_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TECHNIQUE_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("STICKS_TO_THE_WRONG")).append(
						"&nbsp;</span></font></p> </td>");
		content
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女费</span></font></p></td>");
		} else {
			content
					.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>绩效工资</span></font></p></td>");
		}
		content
				.append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {

			content
					.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资<span lang=EN-US>3</span></span></font></p></td>");
		} else {
			content
					.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>顾客满意津贴</span></font></p></td>");
		}

		content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>高温补贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>");

		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>夜班补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>体制补差</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>手机话费</span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>地区补助</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>职位补贴</span></font></p></td>");
		}

		content
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("ONLY_CHILD")).append(
							"&nbsp;</span></font></p></td>");
		} else {
			content
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERFORMANCE_PAY")).append(
							"&nbsp;</span></font></p></td>");
		}
		content
				.append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERFORMANCE_PAY3")).append(
							"&nbsp;</span></font></p></td>");
		} else {
			content
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("CUSTOMER_SATISFACTION"))
					.append("&nbsp;</span></font></p></td>");
		}

		content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("HIGH_T_SUBSIDY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TO_THE_OTHER")).append(
						"&nbsp;</span></font></p></td>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("NIGHT_DUTY_SUBSIDY"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("SYSTEM_BALANCE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MOBILE_PHONE_FEE")).append(
							"&nbsp;</span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MEAL_FEE"))
					.append("&nbsp;</span></font></p></td>")
					// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>0&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("REGIONAL_GRANTS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("POSITION_ALLOWANCE")).append(
							"&nbsp;</span></font></p></td>");
		}

		content.append("</tr>").append("<tr height=32 style='height:24.0pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>工伤减除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他补偿</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>手机话费</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>");
		}
		content
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>实习试用扣除</span></font></p></td>")
				.append("</tr>").append("<tr height=30 style='height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("FUEL_SURCHARGE_FEE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("INDUSTRY_INJURY_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HOLIDAY_MINUS_ALL")).append(
							"&nbsp;</span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("OTHER_COMPENSATION"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MOBILE_PHONE_FEE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("FUEL_SURCHARGE_FEE")).append(
							"&nbsp;</span></font></p></td>");

		}
		content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("VACATION_MATERNITY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEAVE_SICK_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("ABSENTEEISM_MINUS"))
				.append("&nbsp;</span></font></p> </td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TRIAL_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除错误</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休职减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>");

		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>");
		} else {
			content
					.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>");
		}

		content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td></tr>");
		content
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("REDUCE_ERRORS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LATE_EARLY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("OTHER_LESS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEVEL_OFF_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>");

		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append("&nbsp;</span></font></p></td>");
		} else {

			content
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HOLIDAY_MINUS_ALL")).append(
							"&nbsp;</span></font></p></td>");
		}

		content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_TOTAL_SUPPORT")).append(
						"&nbsp;</span></font></p></td></tr>");

		content
				.append("</table></div><p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");

		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
					.append(
							"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>支出</span></font></b></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>养老保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>医疗保险</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_PENSION"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_MEDICAL"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>待业保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_UNEMPLOYED"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房公积金</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_TAX"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_HOUSING_FUND"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除合计</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
					.append(" </tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TOTAL_DEDUCTIONS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_WAGE"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr></table></div>")
					.append(
							"<p ><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");
		} else {
			content
					.append(
							"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
					.append(
							"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p  align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>费用</span></font></b></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>管理费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>公司负担</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HANDLING_CHARGE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PAYMENT_OF_EMPLOYER"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>个人养老保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>个人医疗保险</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_PENSION"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_MEDICAL"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>个人待业保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>个人住房公积金</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_UNEMPLOYED"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_HOUSING_FUND"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>个人负担</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>国企合计</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PAYMENT_OF_INDIVIDUAL"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("GUOQI"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
					.append(" </tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_TAX"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_WAGE"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr></table></div>")
					.append(
							"<p ><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");
		}

		content
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(
						"<img width=100 height=27 id='_x0000_i1025' src='http://10.40.128.28:8083/images/login_logo.gif'")
				.append(
						" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
				.append(
						"<strong><b><font face=宋体>斗山机床（中国）有限公司</font></b></strong></font></p></td>")
				.append(
						"<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
				.append(
						"<span style='font-size:12.0pt;font-family:宋体'>感谢您对公司所做的贡献</span></font></b></strong></p>")
				.append("</td></tr></table></div></body></html>");

		return content.toString();
	}

	private String getMailContent_DISD(SimpleMap empWages,
			SimpleMap parameterObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 ");

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime());
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE);

		StringBuffer content = new StringBuffer(25000);
		// DISD工资邮件样式
		content
				.append("<html>")
				.append("<title>工资发放</title>")
				.append(
						"<body bgcolor=lavender lang=ZH-CN link=blue vlink=purple><div class=Section1><p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-USstyle='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-US style='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p><div>")
				.append(
						"<div class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><hr size=2 width='100%' align=center tabindex=-1></span></font></div>")
				.append("<p class=MsoNormal><b>")
				.append(
						"<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>发件人:</span></font>赵京花 D I S D <br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>发送时间</span></font>: ")
				.append(paDate)
				.append(week)
				.append(time)
				.append("<br><b>")
				// .append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>主题</span></font>: ")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("</p></div>")
				.append(
						"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
				.append(
						"<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("支付明细表</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
				.append(
						"<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水乃个人隐私，请您尊重他人的隐私。如有违反，公司将按照《员工手册》规定给予相应处分</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></span></font></p></td>")
				.append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("DEPTID") + " ")
				.append(empWages.getString("FOURTHDEPTNAME") + " ")
				.append(empWages.getString("DEPARTMENT"))
				.append("</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("EMPID"))
				.append("<o:p></o:p></span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>")
				.append(empWages.getString("CHINESENAME"))
				.append("</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt;font-weight:bold'><o:p></o:p></span></font></b></p></td></tr>")
				.append(
						"<tr><td style='padding:.75pt .75pt .75pt .75pt'><div align=center><table class=MsoNormalTable border=1 cellpadding=0 width=600 style='width:450.0pt' id=Table2 height=280>")
				.append(
						"<tr height=30 style='height:22.5pt'><td width=649 colspan=7 height=30 style='width:486.75pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>收入</span></font></b></p></td> </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>基本工资</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>职责津贴</span></font></p></td>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) { //工厂
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>职务津贴</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>岗位补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住宅补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>技术补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>地区补助</span></font></p></td>");
					
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>改善提案奖</span></font></p></td>");
		} else {
		
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>地区补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>手机话费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>");
		}
		content
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BASE_PAY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("JOB_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				// .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("PAYMENT_SPECIAL")).append("&nbsp;</span></font></p></td>")//应付特殊补助
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("SPECIAL_GRANTS")).append("&nbsp;</span></font></p></td>")
				
						;
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("DUTIES_ALLOWANCE")).append(
							"&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("POST_SUBSIDIES"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("RESIDENTIAL_GRANTS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("PERFORMANCE_PAY2")).append("&nbsp;</span></font></p></td>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TECHNIQUE_ALLOWANCE"))//技术补贴
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("REGIONAL_GRANTS"))//地区补助
					.append("&nbsp;</span></font></p></td>")
					;
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
		//			.append(empWages.getString("MEND_OVERTURE_AWARD")).append(
		//					"&nbsp;</span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("RESIDENTIAL_GRANTS"))//住宅补助
					.append("&nbsp;</span></font></p> </td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("REGIONAL_GRANTS"))//地区补助
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MOBILE_PHONE_FEE"))//手机话费
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("FUEL_SURCHARGE_FEE")).append(//燃油费
							"&nbsp;</span></font></p></td>");
		}
		content.append("</tr>")
		// .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>改善提案奖</span></font></p></td>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女费</span></font></p></td>")
				.append("<tr height=30 style='height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资3</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>值班补助</span></font></p></td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>支给错误</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>手机话费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女费</span></font></p></td>")
					;
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>午餐补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>高温补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>支给错误</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>");
		//			.append(
		//					"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>");
		}
		content.append("</tr>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<tr height=30 style='height:22.5pt'>")
					// .append("<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("MEND_OVERTURE_AWARD")).append("&nbsp;</span></font></p></td>")
					// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("ONLY_CHILD")).append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("SPECIAL_GRANTS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERFORMANCE_PAY3"))
					.append("&nbsp;</span></font></p> </td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
		//			.append(empWages.getString("DUTY_SUBSIDIES"))//值班补助
		//			.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("STICKS_TO_THE_WRONG"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TO_THE_OTHER"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MOBILE_PHONE_FEE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("FUEL_SURCHARGE_FEE"))
		//			.append(empWages.getString("JOB_ALLOWANCE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("ONLY_CHILD"))
					.append("&nbsp;</span></font></p></td>")
					;
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("SPECIAL_GRANTS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("MEAL_FEE"))//午餐补助
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HIGH_T_SUBSIDY"))//高温
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("ONLY_CHILD"))//独生子女
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("STICKS_TO_THE_WRONG"))//支给错误
					.append("&nbsp;</span></font></p></td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
		//			.append(empWages.getString(""))
		//			.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TO_THE_OTHER")).append(//其他支给
							"&nbsp;</span></font></p> </td>");
		}
		content.append("</tr>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<tr height=32 style='height:24.0pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>夜班补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>高温补助</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>淡季奖</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
					;
		//			.append(
		//					"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>岗位保留减除</span></font></p></td>");
		} else {
			content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休职减除</span></font></p></td>");
		}
		content.append("</tr>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("NIGHT_DUTY_SUBSIDY")).append(
							"&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HIGH_T_SUBSIDY")).append(
							"&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("OFF_SEA_BOUNS"))//淡季奖
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("LATE_EARLY_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("VACATION_MATERNITY_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("LEAVE_SICK_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					;
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
		//			.append(empWages.getString("A_POST_RESERVATION_MINUS"))
		//			.append("&nbsp;</span></font></p></td>");
		} else {
			content
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LATE_EARLY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("VACATION_MATERNITY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEAVE_SICK_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("ABSENTEEISM_MINUS"))
				.append("&nbsp;</span></font></p> </td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEVEL_OFF_MINUS"))
				.append("&nbsp;</span></font></p></td>");
		}
		content.append("</tr>").append("<tr height=30 style='height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休职减除</span></font></p></td>")
					.append(
							"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除错误</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>试用扣除</span></font></p></td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>淡季奖</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费计税</span></font></p></td></tr>");
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>试用扣除</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
					;
		}
		content.append("</tr>").append("<tr height=30 style='height:22.5pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("ABSENTEEISM_MINUS"))
					.append("&nbsp;</span></font></p> </td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("LEVEL_OFF_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("OTHER_LESS"))
					.append("&nbsp;</span></font></p></td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
		//			.append(empWages.getString("REDUCE_ERRORS"))
		//			.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HOLIDAY_MINUS_ALL"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TRIAL_MINUS"))
					.append("&nbsp;</span></font></p></td>")
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
		//			.append(empWages.getString("OFF_SEA_BOUNS"))
		//			.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_TOTAL_SUPPORT"))//支给合计
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append("")
					.append("&nbsp;</span></font></p></td>");
		//			.append(
		//					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
		//			.append(
		//					"<p class=MsoNormal align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
		//			.append(empWages.getString("MEAL_DUTY")).append(
		//					"&nbsp;</span></font></p></td></tr>");
		} else {
			content
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("OTHER_LESS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("HOLIDAY_MINUS_ALL"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TRIAL_MINUS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_TOTAL_SUPPORT"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append("")
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append("").append(
							"&nbsp;</span></font></p></td></tr>");
			
		}
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
		"C_12067_1330306")) {
			content.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>税后减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
			.append("</tr>");
		} else {
				content.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>税后减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
			.append("</tr>");
		}
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
		"C_12067_1330306")) {
			content.append("<tr height=30 style='height:22.5pt'>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("GIFT_COST"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("MEAL_DUTY"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("TAX_AFTER_MINUS")).append(
					"&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
		.append("</tr>");
		}else {
			content.append("<tr height=30 style='height:22.5pt'>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("GIFT_COST"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("MEAL_DUTY"))
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append(empWages.getString("TAX_AFTER_MINUS")).append(
					"&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append(
					"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
			.append(
					"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
			.append("")
			.append("&nbsp;</span></font></p></td>")
			.append("</tr>");
			
			
		}
		
		content
				.append("</table></div><p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");
		
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {
			content
					.append(
							"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
					.append(
							"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>支出</span></font></b></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>养老保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>医疗保险</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_PENSION"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_MEDICAL"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>待业保险</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房公积金</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_UNEMPLOYED"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PERSONAL_HOUSING_FUND"))
					.append("&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_TAX"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除合计</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
					
					.append(" </tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("TOTAL_DEDUCTIONS"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_WAGE"))
					.append("&nbsp;</span></font></p></td>")
					
					.append("</tr></table></div>")
					.append(
							"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");
		} else {
			content
					.append(
							"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
					.append(
							"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>费用</span></font></b></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>个人负担</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("PAYMENT_OF_INDIVIDUAL"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_TAX"))
					.append("</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
					.append(empWages.getString("THIS_ACTUAL_WAGE"))
					.append("&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr>")
					.append("<tr height=30 style='height:22.5pt'>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append(
							"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
					.append(
							"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
					.append("</tr></table></div>")
					.append(
							"<p ><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");
		}

		content
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append("<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append("<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append("<img width=100 height=27 id='_x0000_i1025' src='http://10.40.128.28:8083/images/login_logo.gif'")
				.append(" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
				.append("<strong><b><font face=宋体>斗山工程机械（山东）有限公司</font></b></strong></font></p></td>")
				.append("<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append("<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
				.append("<span style='font-size:12.0pt;font-family:宋体'>感谢您对公司所做的贡献</span></font></b></strong></p>")
				.append("</td></tr>");
				

		if (parameterObject.getString("mailName").equals("工资")) {

			if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
					"C_12067_1330306")) {// DISD工厂的邮件底部添加的备注

				content
						.append("</tr><tr height=27 style='height:20.25pt'>")
						.append("<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
//				content.append("<b><font size=2 face=宋体>1.       社保上下限调整本月工资中补发1-6月差额</font></b><br>");
//				content.append("<b><font size=2 face=宋体>2.       公积金上线调整本月工资中补扣1-6月差额</font></b><br>");
//				content.append("<b><font size=2 face=宋体>3.       暑期购物卡计税 （600元/人）</font></b><br>");
				//content.append("<b><font size=2 face=宋体>-社保上限：17326，下限：3465，补扣1-7月基数差额；</font></b>");
				//content.append("<b><font size=2 face=宋体>-公积金上限：17255，下限1910，补扣1-6月基数差额；</font></b>");
				content.append("</td></tr>");

			} else if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
					"C_12067_1330308")) {// 支社的邮件底部添加的备注

				content
						.append("</tr><tr height=27 style='height:20.25pt'>")
						.append("<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
//				 content.append("<b><font size=2 face=宋体>1.       社保上下限调整本月工资中补发1-6月差额</font></b><br>");
//				 content.append("<b><font size=2 face=宋体>2.       公积金上线调整本月工资中补扣1-6月差额</font></b><br>");
//				 content.append("<b><font size=2 face=宋体>3.       暑期购物卡计税 （600元/人）</font></b><br>");
				content.append("</td></tr>");
			}

			// 工厂，支社的邮件底部都添加的备注
			content.append("</tr><tr height=27 style='height:20.25pt'>")
					.append("<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
			content.append("<b><font size=2 face=宋体>个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
                   .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>")
                   .append("</td></tr>");

		}

		content.append("</table></div></body></html>");

		return content.toString();
	}

	private String getMailContent_DICI(SimpleMap empWages,
			SimpleMap parameterObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 ");

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime());
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE);

		StringBuffer content = new StringBuffer(25000);

		content
				.append("<html>")
				.append("<title>工资发放</title>")
				.append(
						"<body bgcolor=lavender lang=ZH-CN link=blue vlink=purple><div class=Section1><p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-USstyle='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-US style='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p><div>")
				.append(
						"<div class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><hr size=2 width='100%' align=center tabindex=-1></span></font></div>")
				.append("<p class=MsoNormal><b>")
				.append(
						"<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>发件人:</span></font>周琦  D I C I <br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>发送时间</span></font>: ")
				.append(paDate)
				.append(week)
				.append(time)
				.append("<br><b>")
				// .append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>主题</span></font>: ")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("</p></div>")
				.append(
						"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
				.append(
						"<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("支付明细表</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
				.append(
						"<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水乃个人隐私，请您尊重他人的隐私，如有违反，公司给予警告处罚</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></span></font></p></td>")
				.append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("DEPTID") + " ")
				.append(empWages.getString("FOURTHDEPTNAME") + " ")
				.append(empWages.getString("DEPARTMENT"))
				.append("</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("EMPID"))
				.append("<o:p></o:p></span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>")
				.append(empWages.getString("CHINESENAME"))
				.append("</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt;font-weight:bold'><o:p></o:p></span></font></b></p></td></tr>")
				.append(
						"<tr><td style='padding:.75pt .75pt .75pt .75pt'><div align=center><table class=MsoNormalTable border=1 cellpadding=0 width=600 style='width:450.0pt' id=Table2 height=280>")
				.append(
						"<tr height=30 style='height:22.5pt'><td width=649 colspan=6 height=30 style='width:486.75pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>收入</span></font></b></p></td> </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>基本工资</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>职责津贴</span></font></p></td>")
//				.append(
//						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//				.append(
//						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>")
//				.append(
//						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//				.append(
//						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>地区补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>地区补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>午餐补助</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BASE_PAY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("JOB_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
//				.append(
//						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//				.append(
//						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//				.append(empWages.getString("FUEL_SURCHARGE_FEE"))
//				.append("&nbsp;</span></font></p></td>")
//				.append(
//						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
//				.append(
//						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//				.append(empWages.getString("REGIONAL_GRANTS"))
//				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("RESIDENTIAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("REGIONAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("SPECIAL_GRANTS"))
				.append("&nbsp;</span></font></p> </td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("DINNER_ALLOWANCE_DICI"))
				.append("&nbsp;</span></font></p> </td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>手机话费</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>燃油费</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>高温补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>独生子女</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>支给错误</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TELEPHONE_SUBSIDY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("FUEL_SURCHARGE_FEE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("HIGH_T_SUBSIDY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p  align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("ONLY_CHILD"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p  align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("STICKS_TO_THE_WRONG"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TO_THE_OTHER"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=32 style='height:24.0pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休职减除</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LATE_EARLY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("VACATION_MINUS"))
				.append("&nbsp;</span></font></p> </td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEAVE_SICK_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("ABSENTEEISM_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEVEL_OFF_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>试用扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
//				.append(
//						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//				.append(
//						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>支给合计</span></font></p></td></tr>")
				
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")

						
						
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("OTHER_LESS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("HOLIDAY_MINUS_ALL"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TRIAL_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_TOTAL_SUPPORT"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append("")
				.append("&nbsp;</span></font></p></td>")
				
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append("")
//				.append("&nbsp;</span></font></p></td>")
				.append("&nbsp;</span></font></p></td></tr>")
				
				
//				.append(
//						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
//				.append(
//						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
//				.append(empWages.getString("THIS_TOTAL_SUPPORT"))
//				.append("&nbsp;</span></font></p></td></tr>")
				
				
				//整改支给合计
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>餐费计税</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>免税支给</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>税后减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("GIFT_COST"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("MEAL_DUTY"))//餐费计税
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TAX_FREE_SUPPORT"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TAX_AFTER_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append("")
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append("")
				.append("&nbsp;</span></font></p></td></tr>")
						
						
				
				.append(
						"</table></div><p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>支出</span></font></b></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>养老保险</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>医疗保险</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_PENSION"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_MEDICAL"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>待业保险</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房公积金</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_UNEMPLOYED"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_HOUSING_FUND"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'></span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_ACTUAL_TAX"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append("")
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除合计</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
				.append(" </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TOTAL_DEDUCTIONS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_ACTUAL_WAGE"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p <font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>")
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(
						"<img width=100 height=27 id='_x0000_i1025' src='http://10.40.128.28:8083/images/login_logo.gif'")
				.append(
						" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
				.append(
						"<strong><b><font face=宋体>斗山(中国)投资有限公司</font></b></strong></font></p></td>")
				.append(
						"<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
				.append(
						"<span style='font-size:12.0pt;font-family:宋体'>感谢您对公司所做的贡献</span></font></b></strong></p>");

		content
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1396147"))// DICI员工区分为烟台添加备注(C_12067_1396147北京)
		{
			content.append("<b><font size=2 face=宋体>8月餐费合并计税  </font></b><br>");
			content.append("<b><font size=2 face=宋体>发放8月高温补助</font></b><br>");
//			content.append("<b><font size=2 face=宋体>发放6&7月高温补助</font></b><br>");
//			content.append("<b><font size=2 face=宋体>烟台地区五险一金上下限调整</font></b><br>");
			 content.append("<b><font size=2 face=宋体> 个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
             .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
			 
		} else if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1396152"))// 员工区分为北京添加备注(C_12067_1396152北京)
		{
//			content.append("<b><font size=2 face=宋体>19年薪酬调整 </font></b><br>");
//			content.append("<b><font size=2 face=宋体>夏季休假福利卡合并计税</font></b><br>");
//			content.append("<b><font size=2 face=宋体>北京地区五险一金基数 &上限调整（下调）</font></b><br>");
			content.append("<b><font size=2 face=宋体>个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
             .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
		}

		content.append("</td></tr></table></div></body></html>");

		return content.toString();

	}

	private String getMailContent_DISC(SimpleMap empWages,
			SimpleMap parameterObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 ");

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime());
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE);

		StringBuffer content = new StringBuffer(25000);

		content
				.append("<html>")
				.append("<title>工资发放</title>")
				.append(
						"<body bgcolor=lavender lang=ZH-CN link=blue vlink=purple><div class=Section1><p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-USstyle='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-US style='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p><div>")
				.append(
						"<div class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><hr size=2 width='100%' align=center tabindex=-1></span></font></div>")
				.append("<p class=MsoNormal><b>")
				.append(
						"<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>发件人:</span></font>주림 D B C N <br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>发送时间</span></font>: ")
				.append(paDate)
				.append(week)
				.append(time)
				.append("<br><b>")
				// .append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>主题</span></font>: ")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("</p></div>")
				.append(
						"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
				.append(
						"<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("支付明细表</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
				.append(
						"<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水乃个人隐私，请您尊重他人的隐私，如有违反，公司给予警告处罚</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></font></p></td>")
				.append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("DEPTID") + " ")
				.append(empWages.getString("FOURTHDEPTNAME") + " ")
				.append(empWages.getString("DEPARTMENT"))
				.append("</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("EMPID"))
				.append("<o:p></o:p></span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>")
				.append(empWages.getString("CHINESENAME"))
				.append("</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><b><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt;font-weight:bold'><o:p></o:p></span></font></b></p></td></tr>")
				.append(
						"<tr><td style='padding:.75pt .75pt .75pt .75pt'><div align=center><table class=MsoNormalTable border=1 cellpadding=0 width=600 style='width:450.0pt' id=Table2 height=280>")
				.append(
						"<tr height=30 style='height:22.5pt'><td width=649 colspan=6 height=30 style='width:486.75pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>收入</span></font></b></p></td> </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p  align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>基本工资</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住宅补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>职务津贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资<span lang=EN-US>2</span></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资<span lang=EN-US>3</span></span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BASE_PAY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("SPECIAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("RESIDENTIAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("DUTY_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERFORMANCE_PAY2"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERFORMANCE_PAY3"))
				.append("&nbsp;</span></font></p> </td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他津贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>夜班补助</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他计税</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>福利金</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>技能津贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>奖金</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("OTHER_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("NIGHT_DUTY_SUBSIDY")).append("&nbsp;</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("GIFT_COST"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("WELFARE_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TO_THE_OTHER"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("SKILL_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BONUS_VALUE"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=32 style='height:24.0pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住宅减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("VACATION_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEAVE_SICK_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("ABSENTEEISM_MINUS"))
				.append("&nbsp;</span></font></p> </td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LATE_EARLY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("RESIDENTIAL_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>病休减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除错误</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>试用扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>");

		// /if
		// (empWages.getString("ATTENDANCE_PERIOD_CODE").equals("C_12067_1330306"))
		// /{
		content
				.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td></tr>");
		// /}

		content
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEVEL_OFF_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("REDUCE_ERRORS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("HOLIDAY_MINUS_ALL"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TRIAL_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("OTHER_LESS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>");

		// /if
		// (empWages.getString("ATTENDANCE_PERIOD_CODE").equals("C_12067_1330306"))
		// /{
		content
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_TOTAL_SUPPORT")).append(
						"&nbsp;</span></font></p></td></tr>");
		// /}

		content
				.append("</table></div><p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");

		content
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>支出</span></font></b></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>社会保险</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房公积金</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_INSURANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_HOUSING_FUND"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>待业保险</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>奖金税</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>预支扣除</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("PERSONAL_UNEMPLOYED")).append("&nbsp;</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BONUS_TAX"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TAX_AFTER_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_ACTUAL_TAX"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除合计</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
				.append(" </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TOTAL_DEDUCTIONS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_ACTUAL_WAGE"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p ><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");

		content
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(
						"<img width=100 height=27 id='_x0000_i1025' src='http://10.40.128.28:8083/images/login_logo_sm.gif'")
				.append(
						" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
				.append(
						"<strong><b><font face=宋体>斗山山猫机械（中国）有限公司</font></b></strong></font></p></td>")
				.append(
						"<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
				.append(
						"<span style='font-size:12.0pt;font-family:宋体'>感谢您对公司所做的贡献</span></font></b></strong></p>")

				.append("</td></tr>")
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
		// .append("<b><font size=2 face=宋体>本月工资中福利金含电话补贴。</font></b>")
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {// DBCN工厂
			content
					.append("</tr><tr height=27 style='height:20.25pt'>")
					.append(
							"<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
			content.append("<b><font size=2 face=宋体>其他计税：话费支援 </font></b><br>");
			content.append("<b><font size=2 face=宋体>福利金：8月交通费/8月生日福利。 </font></b><br>");
			content.append("<b><font size=2 face=宋体>税后减除：8月交通费/8月生日福利。</font></b><br>");
			content.append("<b><font size=2 face=宋体>个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
                   .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
			content.append("<b><font size=2 face=宋体>如有疑问，请与HR联系~    斗山山猫机械（中国）有限公司 </font></b><br>");
			content.append("</td></tr>");
		} else if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330308")) {// 支社
			content
					.append("</tr><tr height=27 style='height:20.25pt'>")
					.append(
							"<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
			content.append("<b><font size=2 face=宋体>其他计税：话费支援 </font></b><br>");
			content.append("<b><font size=2 face=宋体>福利金：8月交通费/8月生日福利。  </font></b><br>");
			content.append("<b><font size=2 face=宋体>税后减除：8月交通费/8月生日福利。</font></b><br>");
			content.append("<b><font size=2 face=宋体>个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
                   .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
			content.append("<b><font size=2 face=宋体>如有疑问，请与HR联系~    斗山山猫机械（中国）有限公司 </font></b><br>");
			content.append("</td></tr>");
		}

		content.append("</td></tr></table></div></body></html>");

		return content.toString();
	}
	private String getMailContent_DISCDI(SimpleMap empWages,
			SimpleMap parameterObject) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日 ");

		Calendar calendar = Calendar.getInstance();
		String paDate = sdf.format(calendar.getTime());
		String week = weekNames[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE);

		StringBuffer content = new StringBuffer(25000);

		content
				.append("<html>")
				.append("<title>工资发放</title>")
				.append(
						"<body bgcolor=lavender lang=ZH-CN link=blue vlink=purple><div class=Section1><p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-USstyle='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<p class=MsoNormal><font size=1 color=navy face=Arial><span lang=EN-US style='font-size:9.0pt;font-family:Arial;color:navy'><o:p>&nbsp;</o:p></span></font></p><div>")
				.append(
						"<div class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><hr size=2 width='100%' align=center tabindex=-1></span></font></div>")
				.append("<p class=MsoNormal><b>")
				.append(
						"<font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold'>发件人:</span></font>주림 D B C N <br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>发送时间</span></font>: ")
				.append(paDate)
				.append(week)
				.append(time)
				.append("<br><b>")
				// .append("<font size=2><span style='font-weight:bold'>收件人</span></font>:장나 사원 인프라코어 D I C C<br><b>")
				.append(
						"<font size=2><span style='font-size:10.0pt;font-weight:bold'>主题</span></font>: ")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("</p></div>")
				.append(
						"<p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p>&nbsp;</o:p></span></font></p>")
				.append(
						"<table class=MsoNormalTable border=0 cellpadding=0 bgcolor=lavender style='background:lavender'>")
				.append(
						"<tr><td colspan=2 style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=5 face=宋体><span lang=EN-US style='font-size:18.0pt;font-family:宋体'>")
				.append(parameterObject.getString("year"))
				.append("年")
				.append(parameterObject.getString("month"))
				.append("月 ")
				.append(parameterObject.getString("mailName"))
				.append("支付明细表</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>&nbsp;</td></tr>")
				.append(
						"<tr height=12 style='height:9.0pt'><td colspan=2 height=12 style='padding:.75pt .75pt .75pt .75pt;height:9.0pt'>")
				.append(
						"<p class=MsoNormal><strong><b><font size=2 face=宋体><span style='font-size:10.0pt;font-family:宋体'>薪水乃个人隐私，请您尊重他人的隐私，如有违反，公司给予警告处罚</span></font></b></strong>")
				.append("</p></td></tr>")
				.append(
						"<tr height=57 style='height:42.75pt'><td colspan=2 height=57 style='padding:.75pt .75pt .75pt .75pt;height:42.75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=805 style='width:603.75pt' id=Table1 height=47><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>部门</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>职号</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>姓名</span></font></p></td>")
				.append("</tr><tr><td style='padding:.75pt .75pt .75pt .75pt'>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("DEPTID") + " ")
				.append(empWages.getString("FOURTHDEPTNAME") + " ")
				.append(empWages.getString("DEPARTMENT"))
				.append("</span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(empWages.getString("EMPID"))
				.append("<o:p></o:p></span></font></p></td>")
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><p class=MsoNormal align=center style='text-align:center'><font size=3 face=宋体><span style='font-size:12.0pt'>")
				.append(empWages.getString("CHINESENAME"))
				.append("</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p class=MsoNormal align=center style='text-align:center'><b><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt;font-weight:bold'><o:p></o:p></span></font></b></p></td></tr>")
				.append(
						"<tr><td style='padding:.75pt .75pt .75pt .75pt'><div align=center><table class=MsoNormalTable border=1 cellpadding=0 width=600 style='width:450.0pt' id=Table2 height=280>")
				.append(
						"<tr height=30 style='height:22.5pt'><td width=649 colspan=6 height=30 style='width:486.75pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>收入</span></font></b></p></td> </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p  align=center style='text-align:center'><font size=2face=宋体><span style='font-size:10.0pt'>基本工资</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>特殊补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住宅补助</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>职务津贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资<span lang=EN-US>2</span></span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>业绩工资<span lang=EN-US>3</span></span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BASE_PAY"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("SPECIAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("RESIDENTIAL_GRANTS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("DUTY_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERFORMANCE_PAY2"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERFORMANCE_PAY3"))
				.append("&nbsp;</span></font></p> </td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他津贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>夜班补助</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他计税</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>福利金</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他支给</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>技能津贴</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>奖金</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("OTHER_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("NIGHT_DUTY_SUBSIDY")).append("&nbsp;</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("GIFT_COST"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("WELFARE_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TO_THE_OTHER"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("SKILL_ALLOWANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BONUS_VALUE"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=32 style='height:24.0pt'>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>休假减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>事病减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>旷工减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>迟早私扣除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住宅减除</span></font></p></td>")
				.append(
						"<td width=100 height=32 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:24.0pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>未勤扣除</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("VACATION_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEAVE_SICK_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("ABSENTEEISM_MINUS"))
				.append("&nbsp;</span></font></p> </td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LATE_EARLY_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt; height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("RESIDENTIAL_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("NOT_ATTENDANCE_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>病休减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除错误</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>放假减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>试用扣除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>其他减除</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>");

		// /if
		// (empWages.getString("ATTENDANCE_PERIOD_CODE").equals("C_12067_1330306"))
		// /{
		content
				.append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>支给合计</span></font></p></td></tr>");
		// /}

		content
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("LEVEL_OFF_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("REDUCE_ERRORS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("HOLIDAY_MINUS_ALL"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TRIAL_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("OTHER_LESS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>");

		// /if
		// (empWages.getString("ATTENDANCE_PERIOD_CODE").equals("C_12067_1330306"))
		// /{
		content
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_TOTAL_SUPPORT")).append(
						"&nbsp;</span></font></p></td></tr>");
		// /}

		content
				.append("</table></div><p class=MsoNormal><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");

		content
				.append(
						"<td style='padding:.75pt .75pt .75pt .75pt'><div align=center>")
				.append(
						"<table class=MsoNormalTable border=1 cellpadding=0 width=200 style='width:150.0pt' id=Table3 height=280>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td colspan=2 height=30 style='padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><b><font size=3 face=宋体><span style='font-size:12.0pt;font-weight:bold'>支出</span></font></b></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>社会保险</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>住房公积金</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_INSURANCE"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("PERSONAL_HOUSING_FUND"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>待业保险</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>奖金税</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>预支扣除</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				// .append("<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>").append(empWages.getString("PERSONAL_UNEMPLOYED")).append("&nbsp;</span></font></p></td>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("BONUS_TAX"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TAX_AFTER_MINUS"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>所得税</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_ACTUAL_TAX"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append("&nbsp;</span></font></p></td>")
				.append("</tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt'>减除合计</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span style='font-size:10.0pt;font-weight:bold;'>实领工资</span></font></p></td>")
				.append(" </tr>")
				.append("<tr height=30 style='height:22.5pt'>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("TOTAL_DEDUCTIONS"))
				.append("&nbsp;</span></font></p></td>")
				.append(
						"<td width=100 height=30 style='width:75.0pt;padding:.75pt .75pt .75pt .75pt;height:22.5pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=2 face=宋体><span lang=EN-US style='font-size:10.0pt'>")
				.append(empWages.getString("THIS_ACTUAL_WAGE"))
				.append("&nbsp;</span></font></p></td>")
				.append("</tr></table></div>")
				.append(
						"<p ><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'><o:p></o:p></span></font></p></td>");

		content
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><font size=3 face=宋体><span lang=EN-US style='font-size:12.0pt'>")
				.append(
						"<img width=100 height=27 id='_x0000_i1025' src='http://10.40.128.28:8083/images/login_logo_sm.gif'")
				.append(
						" alt='border=0'><strong><b><font face=宋体><span style='font-family:宋体'><!-- </img> -->&nbsp;</span></font></b></strong></span>")
				.append(
						"<strong><b><font face=宋体>斗山山猫机械（中国）有限公司</font></b></strong></font></p></td>")
				.append(
						"<td height=27 valign=bottom style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>")
				.append(
						"<p align=center style='text-align:center'><strong><b><font size=3 face=宋体>")
				.append(
						"<span style='font-size:12.0pt;font-family:宋体'>感谢您对公司所做的贡献</span></font></b></strong></p>")

				.append("</td></tr>")
				.append("</tr><tr height=27 style='height:20.25pt'>")
				.append(
						"<td height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
		// .append("<b><font size=2 face=宋体>本月工资中福利金含电话补贴。</font></b>")
		if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330306")) {// DBCN工厂
			content
					.append("</tr><tr height=27 style='height:20.25pt'>")
					.append(
							"<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
			content.append("<b><font size=2 face=宋体>其他计税：话费支援 </font></b><br>");
			content.append("<b><font size=2 face=宋体>福利金：8月交通费/8月生日福利。 </font></b><br>");
			content.append("<b><font size=2 face=宋体>税后减除：8月交通费/8月生日福利。</font></b><br>");
			content.append("<b><font size=2 face=宋体>个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
                   .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
			content.append("<b><font size=2 face=宋体>如有疑问，请与HR联系~    斗山山猫机械（中国）有限公司</font></b><br>");
			content.append("</td></tr>");
		} else if (empWages.getString("ATTENDANCE_PERIOD_CODE").equals(
				"C_12067_1330308")) {// 支社
			content
					.append("</tr><tr height=27 style='height:20.25pt'>")
					.append(
							"<td colspan=2 height=27 style='padding:.75pt .75pt .75pt .75pt;height:20.25pt'>");
			content.append("<b><font size=2 face=宋体>其他计税：话费支援 </font></b><br>");
			content.append("<b><font size=2 face=宋体>福利金：8月交通费/8月生日福利。</font></b><br>");
			content.append("<b><font size=2 face=宋体>税后减除：8月交通费/8月生日福利。</font></b><br>");
			content.append("<b><font size=2 face=宋体>个税专项附加扣除项目  当月金额：</font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_DQGSDK_TOTAL")).append("</font></b>")
                   .append("<b><font size=2 face=宋体> ,累计金额： </font></b>").append("<b><font size=2 face=宋体>").append(empWages.getString("CAL_GSDK_TOTAL_LEIJI")).append("</font></b><br>");
			content.append("<b><font size=2 face=宋体>如有疑问，请与HR联系~    斗山山猫机械（中国）有限公司</font></b><br>");
			content.append("</td></tr>");
		}

		content.append("</td></tr></table></div></body></html>");

		return content.toString();
	}
}
