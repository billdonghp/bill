package com.ait.api.cmd;

import com.ait.api.model.R;
import com.ait.api.resultApi.KdGoldAPI;
import com.ait.util.StringUtil;
import com.ait.web.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OrderTracesCmd extends AbstractCmd implements Command {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map map = getData(request);
		String expType = StringUtil.checkNull(map.get("expType"));
		String expNo = StringUtil.checkNull(map.get("expNo"));
		try {
			String result = KdGoldAPI.getOrderTracesByJson(expType,expNo);
			Map resultMap = new HashMap();
			resultMap.put("result", result);
			response.getWriter().append(R.toJson(R.ok(resultMap)));
		} catch (Exception e) {
			response.getWriter().append(R.toJson(R.error()));
		}
		return null;
	}
}
