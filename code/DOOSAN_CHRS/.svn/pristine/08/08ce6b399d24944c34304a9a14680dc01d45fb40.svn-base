package com.ait.ar.dao.shift;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArShift020Bean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Update implements Command {
	private String returnR = null;

	public Update() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArShift020Bean shiftbean = new ArShift020Bean();
		String menu_code = request.getParameter("menu_code");
		// 修改SHIFT010
		
		int dataType = Integer.parseInt(request.getParameter("dataType"));
		int shiftNo = Integer.parseInt(request.getParameter("shiftNo"));
		String shiftName = request.getParameter("shiftName");
		String shiftEnName = request.getParameter("shiftEnName");
		String shiftKoName = request.getParameter("shiftKoName");
		String shiftId = request.getParameter("shiftId");

		try {

			shiftbean.editShift(shiftNo, shiftId, shiftName, shiftEnName, shiftKoName, dataType);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Update shift information Exception. " + e.toString());
			throw new GlRuntimeException(
					"Update shift information Exception. ", e);
		}

		// 修改原始数据
		String PKNOS = request.getParameter("pkNos");
		String[] pk_s = PKNOS.split("-");
		int count = pk_s.length;
		String fTime = null;
		String tTime = null;
		int fh, fm, th, tm, jgcf, jgct, pkno, itemno;
		ArrayList shift020 = new ArrayList();
		for (int i = 0; i < count; i++) {
			itemno = Integer.parseInt(request.getParameter("itemNo"
					+ pk_s[i].toString()));
			fh = Integer.parseInt(request.getParameter("fh"
					+ pk_s[i].toString()));
			fm = Integer.parseInt(request.getParameter("fm"
					+ pk_s[i].toString()));
			th = Integer.parseInt(request.getParameter("th"
					+ pk_s[i].toString()));
			tm = Integer.parseInt(request.getParameter("tm"
					+ pk_s[i].toString()));
			fTime = fh + ":" + fm + ":00";
			fTime = "to_date('" + fTime + "','hh24:mi:ss')";
			tTime = th + ":" + tm + ":00";
			tTime = "to_date('" + tTime + "','hh24:mi:ss')";
			jgcf = Integer.parseInt(request.getParameter("fday"
					+ pk_s[i].toString()));
			jgct = Integer.parseInt(request.getParameter("tday"
					+ pk_s[i].toString()));
			pkno = Integer.parseInt(pk_s[i].toString());
			shift020.clear();
			shift020.add(new Integer(itemno));
			shift020.add(new Integer(jgcf));
			shift020.add(fTime);
			shift020.add(new Integer(jgct));
			shift020.add(tTime);

			try {

				shiftbean.editShifts(shift020, pkno);
			} catch (Exception e) {

				Logger.getLogger(getClass()).debug(
						"Update shift parameter information Exception. "
								+ e.toString());
				throw new GlRuntimeException(
						"Update shift parameter information Exception. ", e);
			}
		}
		int addcount = Integer.parseInt(request.getParameter("count"));
		for (int j = 0; j < addcount; j++) {
			itemno = Integer.parseInt(request.getParameter("itemNo" + j));
			fh = Integer.parseInt(request.getParameter("fh" + j));
			fm = Integer.parseInt(request.getParameter("fm" + j));
			th = Integer.parseInt(request.getParameter("th" + j));
			tm = Integer.parseInt(request.getParameter("tm" + j));
			fTime = fh + ":" + fm + ":00";
			fTime = "to_date('" + fTime + "','hh24:mi:ss')";
			tTime = th + ":" + tm + ":00";
			tTime = "to_date('" + tTime + "','hh24:mi:ss')";
			jgcf = Integer.parseInt(request.getParameter("fday" + j));
			jgct = Integer.parseInt(request.getParameter("tday" + j));
			shift020.clear();
			shift020.add(new Integer(shiftNo));
			shift020.add(new Integer(itemno));
			shift020.add(new Integer(jgcf));
			shift020.add(fTime);
			shift020.add(new Integer(jgct));
			shift020.add(tTime);

			try {

				shiftbean.addShift020(shift020);
			} catch (Exception e) {

				Logger.getLogger(getClass()).debug(
						"Insert shift parameter information Exception. "
								+ e.toString());
				throw new GlRuntimeException(
						"Insert shift parameter information Exception. ", e);
			}
		}

		returnR = "ar/shiftlist.jsp?menu_code=" + menu_code;
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
