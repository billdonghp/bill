package com.ait.ajaxbiz.cmd;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-4-30
 * 
 */
public class ShowUploadImp implements Command {


	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			ResultSet result = null;
			Connection conn = ConnBean.getConn();
			BufferedInputStream inputimage=null;
			PreparedStatement pstmt = null;
			String sql;			
			if(request.getParameter("correctiveplan")!=null && request.getParameter("")!=""){
				sql = "select CRFHOTOS  from SE_COMPLETRECTIFICAT_APPLY where SECURITYCHECKSNO='"+request.getParameter("documentno")+"' ";				
			}else{
				sql = "select JPGUPLOAD  from SE_SECURITYCHECKS_RECORD where DOCUMENTNO='"+request.getParameter("documentno")+"' ";			
			}
			
			Logger.getLogger(getClass()).debug(sql);
			try {
			    pstmt = conn.prepareStatement(sql);
				result=pstmt.executeQuery();
				oracle.sql.BLOB blob = null;
				if(result.next()){
					if(request.getParameter("correctiveplan")!=null && request.getParameter("")!=""){
						 blob = (oracle.sql.BLOB)result.getBlob("CRFHOTOS");
					}else{
						 blob = (oracle.sql.BLOB)result.getBlob("JPGUPLOAD");
					}
					
				  inputimage = new BufferedInputStream(blob.getBinaryStream());
				}
				OutputStream sos =response.getOutputStream();				
				
			    byte [] buf  =   new   byte [10240*1024];
				int  len=0;
				  
				while( (len=inputimage.read(buf))!=-1){
				 
					sos.write(buf, 0 ,len);
				}
			    response.flushBuffer();
			    inputimage.close();
				sos.close();
			} catch (Exception e) {
				request.setAttribute("errorMsg", "没有相关的图片");
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {			
				SqlUtil.close(result, pstmt, conn);
			}
			response.sendRedirect("/safe/safe_view_photos.jsp");
		return "";
	}

}
