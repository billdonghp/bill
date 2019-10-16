package com.ait.api.cmd;

import com.ait.api.model.R;
import com.ait.api.service.ApiService;
import com.ait.api.service.impl.ApiServiceImpl;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.StringUtil;
import com.ait.web.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AffirmEvsDeptCmd extends AbstractCmd implements Command {
	/*
	 * (non-Javadoc)
	 *
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map map = getData(request);
		ApiService apiService = new ApiServiceImpl();

		List deptList = apiService.getInfoList(map,"evsAffirmDept");

		Map resultMap = new HashMap();
		resultMap.put("result", deptList);
		response.getWriter().append(R.toJson(R.ok(resultMap)));

		return null;
	}

}
