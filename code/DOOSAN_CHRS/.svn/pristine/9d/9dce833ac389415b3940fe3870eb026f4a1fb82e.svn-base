package com.ait.api.cmd;

import com.ait.api.model.R;
import com.ait.api.service.ApiService;
import com.ait.api.service.impl.ApiServiceImpl;
import com.ait.web.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetLoginInfoCmd extends AbstractCmd implements Command {


	private ApiService apiService;
	/*
	 * (non-Javadoc)
	 *
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		apiService = new ApiServiceImpl();

		List list = apiService.getLoginInfo();
		if (list != null && list.size() > 0) {
			Map map = new HashMap();
			map.put("result", list);
			response.getWriter().append(R.toJson(R.ok(map)));
		}else{
			response.getWriter().append(R.toJson(R.error("认证失败")));
		}
		return null;
	}
}
