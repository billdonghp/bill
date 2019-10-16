package com.ait.ar.dao.history;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArHistoryMonth;
import com.ait.ar.business.ArServices;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;

public class Update implements Command {
	private String returnR = null;

	private String sql = null;

	private ArHistoryMonth ahm;

	private StringBuffer checkedItem = new StringBuffer();

	ArrayList datas = new ArrayList();

	ArrayList dataRow = new ArrayList();

	private int rows = 0;

	private int moorColumns = 0;

	private boolean isUpdate = false;
	
	private ArServices arServices;

	public Update() {
		arServices = new ArServices();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement ps = null;
		HttpSession mysession = request.getSession(true);
		AdminBean admin = (AdminBean)mysession.getAttribute("admin");
		// 取得汇总列表头
		List<SimpleMap> columns = arServices.getArColumns(admin.getCpnyId());
		
		String key = request.getParameter("key");
		String deptid = StringUtil.checkNull(request.getParameter("deptid"));
		String menu_code = request.getParameter("menu_code");
		String currentPage = StringUtil.checkNull(request
				.getParameter("currentPage"));
		String[] empids = request.getParameterValues("check");
		String armonth = StringUtil.checkNull(request.getParameter("year"))
				+ StringUtil.checkNull(request.getParameter("month"));
		String cpny_id = admin.getCpnyId();
		
		String sql = "";
		try {
			if (empids != null) {
				conn = ConnBean.getConn();
				sql = "UPDATE AR_HISTORY SET ";
				for (SimpleMap map : columns) {
					
					sql = sql + map.getString("COLUMN_NAME") + " = ?, ";
				}
				sql = sql.substring(0, sql.length() - 2)
						+ " WHERE AR_MONTH = ? AND EMPID = ? AND CPNY_ID = ?";
				Logger.getLogger(getClass()).debug(StringUtil.toISO(sql));
				ps = conn.prepareStatement(sql);

				for (int i = 0; i < empids.length; i++) {
					for (int j = 0; j < columns.size(); j++) {
						SimpleMap map = columns.get(j);
						ps.setString(j + 1, StringUtil.checkNull(request
								.getParameter(map.getString("COLUMN_NAME")
										+ "_" + empids[i]), "0"));
						Logger
								.getLogger(getClass())
								.debug(
										map.getString("COLUMN_NAME")
												+ " : "
												+ StringUtil
														.checkNull(
																request
																		.getParameter(map.getString("COLUMN_NAME")
																				+ "_"
																				+ empids[i]),
																"0"));
					}
					ps.setString(columns.size() + 1, armonth);
					Logger.getLogger(getClass()).debug(
							String.valueOf(columns.size() + 1) + " : "
									+ armonth);
					ps.setString(columns.size() + 2, empids[i]);
					Logger.getLogger(getClass()).debug(
							String.valueOf(columns.size() + 2) + " : "
									+ empids[i]);
					ps.setString(columns.size() + 3, cpny_id);
					Logger.getLogger(getClass()).debug(
							String.valueOf(columns.size() + 3) + " : "
									+ cpny_id);
					ps.addBatch();
				}
				ps.executeBatch();
				conn.commit();
			}

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Logger.getLogger(getClass())
					.debug(
							"Update monthly attendance data Exception. "
									+ e.toString());
			throw new GlRuntimeException(
					"Update monthly attendance data Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}

		return "arControlServlet?currentPage=" + currentPage
				+ "&operation=ar_pagecontrol&page=monthly_v&menu_code="
				+ menu_code + "&year=" + request.getParameter("year")
				+ "&month=" + request.getParameter("month") + "&key=" + key;

	}
}
