package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class DeleteReportItemCommand implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String msg = "删除成功!";
		String ri_no = request.getParameter("ri_no");
		try {
			SysService.getInstance().deleteReportItem(ri_no);
		} catch (Exception e) {
			// TODO: handle exception
			msg = "删除失败!";
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(msg);
		out.close();
		return "";
	}

}
