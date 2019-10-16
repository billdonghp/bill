package com.ait.api.cmd;

import com.ait.api.dao.ApiDAO;
import com.ait.api.model.R;
import com.ait.api.service.ApiService;
import com.ait.api.service.impl.ApiServiceImpl;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.DataAccessException;
import com.ait.util.StringUtil;
import com.ait.web.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoCmd extends AbstractCmd implements Command {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiDAO apiDAO = new ApiDAO();
		Map map = getData(request);
		String cpnyId = StringUtil.checkNull(map.get("cpnyId"));
		//如果是空的取法人列表的第一个法人
		if ("".equals(cpnyId)) {
			List list = apiDAO.getInfoList(map,  "getCpnyList");
			if (list != null && list.size() > 0) {
				Map cpnyMap = (Map) list.get(0);
				cpnyId = StringUtil.checkNull(cpnyMap.get("CPNY_ID"));
				map.put("cpnyId", cpnyId);
			}
		}
		//获取用户信息
		List userInfoList = apiDAO.getInfoList(map, "getUserInfo");
		if (userInfoList != null && userInfoList.size() > 0) {
			response.getWriter().append(R.toJson(R.ok((Map)userInfoList.get(0))));
		}else{
			response.getWriter().append(R.toJson(R.error()));
		}
		return null;
	}
}
