package com.ait.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Content {
	/**
	 * transfer
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 */
	public String transfer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
}
