package com.ait.ar.dao.workcalendar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArWorkCalendarBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Update implements Command {
	private String returnR = null;

	public Update() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String menu_code = request.getParameter("menu_code");

		String cNo = request.getParameter("cNo");
		String mxNo = request.getParameter("mxNo");
		String skNo = request.getParameter("skNo");
		// String empID = request.getParameter("empIDs");
		String month_str = request.getParameter("monthstr");
		int year = Integer.parseInt(month_str.substring(0, 4));
		int month = Integer.parseInt(month_str.substring(
				month_str.length() - 2, month_str.length()));
		int fromday;
		int today;
		int PK_NO;
		String fromtime = null;
		String totime = null;
		String sql = null;
		String Newfromtime = null;
		String Newtotime = null;
		ArWorkCalendarBean schedule = new ArWorkCalendarBean();
		float NEWq = 0;
		if (!cNo.equals("")) {
			String[] cNos = cNo.split("-");

			for (int i = 0; i < cNos.length; i++) {
				PK_NO = Integer.parseInt(cNos[i].toString());
				fromday = Integer.parseInt(request
						.getParameter("fromspaceDay_c" + PK_NO));
				fromtime = request.getParameter("from_time_c" + PK_NO);
				today = Integer.parseInt(request.getParameter("tospaceDay_c"
						+ PK_NO));
				totime = request.getParameter("to_time_c" + PK_NO);
				Newfromtime = year + "-" + month + "-" + fromday + " "
						+ fromtime;// +":00";
				Newtotime = year + "-" + month + "-" + today + " " + totime;// +":00";
				sql = "update ar_schedule set from_time=to_date('"
						+ Newfromtime
						+ "','yyyy-mm-dd hh24:mi:ss'),to_time=to_date('"
						+ Newtotime + "','yyyy-mm-dd hh24:mi:ss') where PK_NO="
						+ PK_NO;
				Logger.getLogger(getClass()).debug(sql);

				try {

					schedule.EditShift(sql);
				} catch (Exception e) {

					Logger.getLogger(getClass()).debug(
							"Update employee calendar Exception. "
									+ e.toString());
					throw new GlRuntimeException(
							"Update employee calendar Exception. ", e);
				}
			}
		}
		if (!mxNo.equals("")) {
			String[] mxNos = mxNo.split("-");
			for (int i = 0; i < mxNos.length; i++) {
				PK_NO = Integer.parseInt(mxNos[i].toString());
				fromday = Integer.parseInt(request
						.getParameter("fromspaceDay_mx" + PK_NO));
				fromtime = request.getParameter("from_time_mx" + PK_NO);
				today = Integer.parseInt(request.getParameter("tospaceDay_mx"
						+ PK_NO));
				totime = request.getParameter("to_time_mx" + PK_NO);
				NEWq = Float.parseFloat(request.getParameter("spaceHours_mx"
						+ PK_NO));
				if (Float.toString(NEWq).substring(
						Float.toString(NEWq).indexOf(".") + 1,
						Float.toString(NEWq).length()).equals("0")) {
					NEWq = Float.parseFloat(Float.toString(NEWq).substring(0,
							Float.toString(NEWq).indexOf(".")));
				}
				Newfromtime = year + "-" + month + "-" + fromday + " "
						+ fromtime + ":00";
				Newtotime = year + "-" + month + "-" + today + " " + totime
						+ ":00";
				sql = "update ar_detail set from_time=to_date('" + Newfromtime
						+ "','yyyy-mm-dd hh24:mi:ss'),to_time=to_date('"
						+ Newtotime + "','yyyy-mm-dd hh24:mi:ss'),quantity="
						+ NEWq + " where PK_NO=" + PK_NO;
				Logger.getLogger(getClass()).debug(sql);
				try {

					schedule.EditShift(sql);
				} catch (Exception e) {

					Logger.getLogger(getClass()).debug(
							"Update employee calendar Exception. "
									+ e.toString());
					throw new GlRuntimeException(
							"Update employee calendar Exception. ", e);
				}

			}

		}
		if (!skNo.equals("")) {
			String[] skNos = skNo.split("-");
			for (int i = 0; i < skNos.length; i++) {
				PK_NO = Integer.parseInt(skNos[i].toString());
				fromday = Integer.parseInt(request
						.getParameter("fromspaceDay_sk" + PK_NO));
				fromtime = request.getParameter("from_time_sk" + PK_NO);
				today = Integer.parseInt(request.getParameter("tospaceDay_sk"
						+ PK_NO));
				totime = request.getParameter("to_time_sk" + PK_NO);
				Newfromtime = year + "-" + month + "-" + fromday + " "
						+ fromtime + ":00";
				Newtotime = year + "-" + month + "-" + today + " " + totime
						+ ":00";
				sql = "update ar_records set enter_time=to_date('"
						+ Newfromtime
						+ "','yyyy-mm-dd hh24:mi:ss'),out_time=to_date('"
						+ Newtotime
						+ "','yyyy-mm-dd hh24:mi:ss') where record_no=" + PK_NO;
				Logger.getLogger(getClass()).debug(sql);
				try {

					schedule.EditShift(sql);
				} catch (Exception e) {

					Logger.getLogger(getClass()).debug(
							"Update employee calendar Exception. "
									+ e.toString());
					throw new GlRuntimeException(
							"Update employee calendar Exception. ", e);
				}

			}
		}
		
		returnR = "/workcalendar.jsp?menu_code=" + menu_code;
		Logger.getLogger(getClass()).debug("return page is : " + returnR);
		return returnR;

	}
}
