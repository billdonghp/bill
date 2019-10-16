package com.ait.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.sy.bean.AdminBean;
import com.ait.util.GetUserName;
import com.ait.util.StringUtil;
import com.ait.web.ApplicationContext;

public class UserAuthenFilter implements Filter {
	private FilterConfig filterConfig;// 保留参数

	private static long counter = 0;

	private String requestUri = null;

	private String queryString = null;

	public void init(FilterConfig a_filterConfig) throws ServletException {
		filterConfig = a_filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		/**
		 * 输出用户request中的新系列表用于调试,也可用户基本验证
		 */

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		//CharacterEncoding
		String encoding = StringUtils.defaultIfEmpty(filterConfig.getInitParameter("encoding"), "UTF-8");
		request.setCharacterEncoding(encoding);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		
		requestUri = request.getRequestURI();
		queryString = request.getQueryString();		
		
		if (!requestUri.startsWith("/images") && !requestUri.startsWith("/css") && !requestUri.startsWith("/js")&!requestUri.startsWith("/upload")) {

			Logger.getLogger(getClass()).debug("run sequence [" + ++counter + "] " + request.getRemoteHost());
			Logger.getLogger(getClass()).debug("request URI : " + requestUri);
			if (queryString != null)
				Logger.getLogger(getClass()).debug("query string : " + queryString);
			if(admin != null)
				Logger.getLogger(getClass()).debug("admin : " + admin.getUsername() + " " + admin.getChineseName());
		}

		Enumeration parameters = request.getParameterNames();
		while (parameters.hasMoreElements()) {
			String parameter = parameters.nextElement().toString();
			Logger.getLogger(getClass()).debug("Parameter: " + parameter + " = " + StringUtil.toCN(request.getParameter(parameter)));
		}

		// attribute list
		Enumeration attributes = request.getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = attributes.nextElement().toString();
			Logger.getLogger(getClass()).debug("Attribute: " + attribute + " = " + request.getParameter(attribute));
		}

		// user login validate

		if (!request.getRequestURI().equals("/")
				&& !request.getRequestURI().equals("/controlServlet")
				&& !request.getRequestURI().equals("/ajaxControlServlet")
				&& !request.getRequestURI().equals("/api")
				&& !request.getRequestURI().startsWith("/images")
				&& !request.getRequestURI().startsWith("/css")
				&& !request.getRequestURI().startsWith("/js")
				&& !request.getRequestURI().startsWith("/upload")
				&& !request.getRequestURI().equals("/index.jsp")
				//AD登录验证 添加
				&& !request.getRequestURI().equals("/company.jsp")				
				&& (session == null
						|| admin == null
						|| admin.getAdminID() == null
						|| admin.getAdminID().equalsIgnoreCase(""))) {
			Logger.getLogger(getClass()).debug("User Session Timed out");
			RequestDispatcher dispatcher = filterConfig.getServletContext().getRequestDispatcher("/expired.jsp");
			
			dispatcher.forward(request, response);
		} else {
//			response.setHeader("Cache-Control", "no-store");
//			response.setHeader("Pragrma", "no-cache");
//			response.setDateHeader("Expires", 0);
		
//				设置静态线程变量
				ApplicationContext.setAdminBean(request, response);
				
				// 设置返回的URL供处理页使用
				chain.doFilter(request, response);			
		
			
		}
		// /user login validate end
	}

	public void destroy() {
	}
}