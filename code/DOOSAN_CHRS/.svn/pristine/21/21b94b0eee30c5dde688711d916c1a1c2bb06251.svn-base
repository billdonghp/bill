package com.ait.ar.dao.shift;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArShift020Bean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class Add implements Command {
	private String returnR = null;

	public Add() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int nextvalue = 0;
		String menu_code = request.getParameter("menu_code");
		ArShift020Bean shiftbean = new ArShift020Bean();
		nextvalue = shiftbean.nextvalue();
		int count = Integer.parseInt(request.getParameter("count"));
		int itemNo = 0;
		String shiftName = request.getParameter("shiftName");
		String shiftEnName = request.getParameter("shiftEnName");
		String shiftKoName = request.getParameter("shiftKoName");
		int dataType = Integer.parseInt(request.getParameter("dataType"));
		String shiftId = request.getParameter("shiftId");
		String cpnyId=request.getParameter("cpnyId");
		String fTime = null;
		String tTime = null;
		ArrayList shift010 = new ArrayList();
		ArrayList shift020 = new ArrayList();
		int fh, fm, th, tm, jgcf, jgct;
		
		// add shift010
		shift010.clear();
		shift010.add(new Integer(nextvalue));
		shift010.add(shiftName);
		shift010.add(shiftEnName);
		shift010.add(shiftKoName);
		shift010.add(new Integer(dataType));
		shift010.add(shiftId);
		shift010.add(cpnyId);

		try {

			shiftbean.addShift010(shift010);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Insert shift information Exception. " + e.toString());
			throw new GlRuntimeException(
					"Insert shift information Exception. ", e);
		}

		for (int i = 0; i < count; i++) {
			itemNo = Integer.parseInt(request.getParameter("itemNo" + i));
			fh = Integer.parseInt(request.getParameter("fh" + i));
			fm = Integer.parseInt(request.getParameter("fm" + i));
			th = Integer.parseInt(request.getParameter("th" + i));
			tm = Integer.parseInt(request.getParameter("tm" + i));
			fTime = fh + ":" + fm + ":00";
			fTime = "to_date('" + fTime + "','hh24:mi:ss')";
			tTime = th + ":" + tm + ":00";
			tTime = "to_date('" + tTime + "','hh24:mi:ss')";
			jgcf = Integer.parseInt(request.getParameter("fday" + i));
			jgct = Integer.parseInt(request.getParameter("tday" + i));
			// add shift020
			shift020.clear();
			shift020.add(new Integer(nextvalue));
			shift020.add(new Integer(itemNo));
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
