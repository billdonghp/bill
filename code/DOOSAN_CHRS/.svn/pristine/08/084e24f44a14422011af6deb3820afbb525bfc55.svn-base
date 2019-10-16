package com.ait.safe.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.safe.business.RulesServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;
import com.ait.util.NumberUtil;

public class RulesCommand implements Command {
	private RulesServices rulesServices;

	private SimpleMap parameterObject;

	private String content = null;

	public RulesCommand() {
		rulesServices = new RulesServices();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("rulesAdmin")) {
				returnPage = this.rulesView(request);
			}else if(content.equals("viewAddFile")){
				returnPage = this.addFileView(request);
			}else if(content.equals("add_file")){
				returnPage = this.addFile(request, response);
			}else if(content.equals("viewUpdateFile")){
				returnPage = this.viewUpdateFile(request);
			}else if(content.equals("update_file")){
				returnPage = this.UpdateFile(request, response);
			}else if(content.equals("deleteFile")){
				returnPage = this.deleteFile(request);
			}else if(content.equals("menuSet")){
				returnPage = this.addMenuView(request);
			}else if(content.equals("add_menu")){
				returnPage = this.addmenu(request);
			}else if(content.equals("menuView")){
				returnPage = this.menuView(request);
			}else if(content.equals("viewUpdateMenu")){
				returnPage = this.viewUpdateMenu(request);
			}else if(content.equals("update_menu")){
				returnPage = this.updatemenu(request);
			}else if(content.equals("deleteMenu")){
				returnPage = this.deleteMenu(request);
			}else if(content.equals("systemView")){
				returnPage = this.systemView(request);
			}else if(content.equals("Jump")){
				returnPage = this.Jump(request);
			}else if(content.equals("fileView")){
				returnPage = this.fileView(request, response);
			}else if(content.equals("SearchSystem")){
				returnPage = this.SearchSystem(request);
			}else{
				returnPage="error.jsp";
			}
		} else {
			Logger.getLogger(getClass()).error("get content parameter fail!");
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return Page : " + returnPage);
		return returnPage;
	}

	public void putToolbarInfo(HttpServletRequest request) throws GlRuntimeException {

		try {
			SimpleMap map = new SimpleMap();

			// get paramter from request object         
			List toolMenuList = null;
			List menuentList = null;
			ToolMenu toolmenu = new ToolMenu();
			MenuBiz menubiz = new MenuBiz();
			String menu_code = request.getParameter("menu_code");
			String operation = request.getParameter("operation");
			AdminBean admin = (AdminBean) (request.getSession().getAttribute("admin"));
			String rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";

			// get process popedom list
			toolMenuList = menubiz.toolMenuList(menu_code, rodeLevel);
			menuentList = menubiz.thirdmenulist(menu_code, rodeLevel);

			int selectMenu = 0;
			int insertMenu = 0;
			int updateMenu = 0;
			int deleteMenu = 0;
			for (int i = 0; i < toolMenuList.size(); i++) {

				toolmenu = (ToolMenu) toolMenuList.get(i);

				if (toolmenu.getSelect() == 1) {

					selectMenu = 1;
				}
				if (toolmenu.getInsertr() == 1) {

					insertMenu = 1;
				}
				if (toolmenu.getUpdate() == 1) {

					updateMenu = 1;
				}
				if (toolmenu.getDelect() == 1) {

					deleteMenu = 1;
				}
			}
			// save insert,update,delete popedom
			map.setInt("selectMenu", selectMenu);
			map.setInt("insertMenu", insertMenu);
			map.setInt("updateMenu", updateMenu);
			map.setInt("deleteMenu", deleteMenu);
			// save MenuEnt object list
			map.set("menuentList", menuentList);
			// save menu code
			map.setString("menu_code", menu_code);
			map.setString("operation", operation);

			request.setAttribute("toolbarInfo", map);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ArAbstractCommand put toolbar information to request Exception.", e);
		}
	}

	
	private String rulesView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap map = null;

		String systemName = request.getParameter("systemName");
		
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);
			int resultCount = rulesServices.DisplayInformationNumber(parameterObject);
			
			
			parameterObject.set("systemName", systemName);
			
			// retrieve attendance record list
			Object eatLookCount = 0;
			//得到制度查看全部显示信息
			List Display_information = rulesServices.DisplayInformation(parameterObject,currentPage,pageSize);
			for(int i=0 ; i<Display_information.size() ; i++){
				map = (SimpleMap) Display_information.get(i);
				String filepath = map.getString("FILEPATH");
				ServletContext application = request.getSession().getServletContext();
	    		String path = application.getRealPath(filepath);
	    		path = path.replace("\\", "\\\\");
	    		map.set("path", path);
			}
			
			request.setAttribute("Display_information", Display_information) ;
			request.setAttribute("recordCount", resultCount) ;

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/safe_system_View.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String addFileView(HttpServletRequest request ) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();

		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpnyId", cpnyId);
			// retrieve attendance record list
			Object eatLookCount = 0;
			
			List AllMenu = rulesServices.AllMenu(parameterObject);
			
			
			request.setAttribute("AllMenu", AllMenu);
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/safe_add_SystemView.jsp";
	}
	
	private String addFile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {
		boolean b = true;
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		String myfile = request.getParameter("myfile");
		
		String date = request.getParameter("date");
		String menu = request.getParameter("menu");
		
		String fname= "";
		
		MessageSource messageSource ;
		
		String content = "text/html; charset=UTF-8";
		response.setContentType(content);
	    PrintWriter out=response.getWriter();
	    Map map = new HashMap();
		
		DiskFileUpload fu = new DiskFileUpload();
		
		List fileItems = null;
		int seq = 0;
	    try {
	    	parameterObject = ObjectBindUtil.getData(request);
	    	seq = rulesServices.getSeq(parameterObject);
			parameterObject.set("seq", seq);
			parameterObject.set("cpny_id", admin.getCpnyId());
	    	
	    	
			fileItems = fu.parseRequest(request);
		} catch (FileUploadException e) {
			b  = false;
			e.printStackTrace();
		}
		Iterator iter = null;
		String filename = "";
		if(fileItems!=null){
			iter = fileItems.iterator();
			String regExp=".+\\\\(.+)$";

//			 过滤掉的文件类型
			 String[] errorType={".exe",".com",".cgi",".asp",".mp4"};
			 Pattern p = Pattern.compile(regExp);
			 
			 while (iter.hasNext()) {
			    FileItem item = (FileItem)iter.next();
			    if (!item.isFormField()){
			    	   myfile = item.getName();
			           long size = item.getSize();
			           if((myfile==null || myfile.equals("")) && size==0)
			               continue;
			           Matcher m = p.matcher("\\\\"+myfile);
			           boolean result = m.find();
			           if(result){
			        	   for (int temp=0;temp<errorType.length;temp++){
					           if (m.group(1).endsWith(errorType[temp])){
					                 throw new IOException(myfile + ": wrong type");
					           }
				           }
				           try{
					        	ServletContext application = request.getSession().getServletContext();
					       		String filepath = "/upload/files"; 		
					       		String path = application.getRealPath(filepath); //获得当前文件的上下文路径。
					       		
					       		File f = new File(path);
					       		
					       		String aa = myfile.substring(myfile.length()-4);
					       		
					       		//保存上传的文件到指定的路径
					       		filename = "/upload/files/"+ seq + aa;
					       		
					       		fname=m.group(1);
					       		
					       		SimpleMap sm = null;
					       		List Display_information = rulesServices.DisplayInformation(parameterObject);
					       		for(int i=0 ; i<Display_information.size() ; i++){
					       			sm = (SimpleMap) Display_information.get(i);
					       			String systemFileName = sm.getString("FILENAME");
					       			
					       			if(fname.equals(systemFileName.toString())){
					       				messageSource = new MessageSource(admin.getLocale(), "UTF-8");
					       				request.setAttribute("alert", "添加的文档与现有的文档重名！请指定另一文档名！");
					       				request.setAttribute("url","/safeControlServlet?operation=rules&content=viewAddFile&menu_code="+ parameterObject.getString("menu_code"));
					       				return "/inc/alertMessage.jsp";
					       			}
					       		}
					       		
					       		if(!f.exists()){
					       			f.mkdir();
					       		}

					       		item.write(new File(path+"\\"+ seq + aa));
				           }catch(Exception e){
				        	   b  = false;
				        	   out.println(e);
				           }

			           }else{
			        	   b = false;
			        	   throw new IOException("fail to upload");
			           } 
			    }else{
			    	String name = item.getFieldName();
			        String value = item.getString();
			        map.put(name, value);
			    }       
			 }
		}
		if(b){
//			 bind parameter
			try {
//				 bind parameter
				
				// retrieve attendance record list
				Object eatLookCount = 0;
				parameterObject.set("date", date);
				parameterObject.set("menu", menu);
				parameterObject.set("filename", filename);
				parameterObject.set("fname", fname);
				
				parameterObject.set("myfile", myfile);
				
				
				
				rulesServices.AddFile(parameterObject);
				
				request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("AddFile error ", e);
			}
			
			if(b)
				request.setAttribute("alert", "添加成功");
			else
				request.setAttribute("alert", "添加失败,请重新添加！");
			if(map.get("flag").equals("0"))
				request.setAttribute("url","/safeControlServlet?operation=rules&content=rulesAdmin&menu_code="+ parameterObject.getString("menu_code"));
			else{
				request.setAttribute("url","/safeControlServlet?operation=rules&content=viewAddFile&menu_code="+ parameterObject.getString("menu_code"));
			}
		}else{
			request.setAttribute("alert", "上传文件失败，请返回重新上传！");
			request.setAttribute("url","/safeControlServlet?operation=rules&content=viewAddFile&menu_code="+ parameterObject.getString("menu_code"));
		}
		return "/inc/alertMessage.jsp";
	}
	
	private String decodeURIComponent(String parameter) {
		// TODO 自动生成方法存根
		return null;
	}

	/*private String addFile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {

		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		

		String myfile = request.getParameter("myfile");
		String date = request.getParameter("date");
		String menu = request.getParameter("menu");
		
		String fname= "";
		
		String content = "text/html; charset=gbk";
		response.setContentType(content);
	    PrintWriter out=response.getWriter();
	    Map map = new HashMap();
		
		DiskFileUpload fu = new DiskFileUpload();
		
		List fileItems = null;


		return "/inc/alertMessage.jsp";
	}*/

	
	private String fileView(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {
		this.putToolbarInfo(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		String myhreffileId = request.getParameter("myhreffileId") ;
		
		String content = "text/html; xls/html ; charset=utf-8";
		response.setContentType(content);
		BufferedInputStream   in = null;
		OutputStream outStream = null;
		String path = "";
       try{  
    	   
    	  	parameterObject = ObjectBindUtil.getData(request);
	   		parameterObject.set("myhreffileId", myhreffileId);
	   		
	   		String myhrefaddress = rulesServices.getMyhrefaddress(parameterObject);
	   		
        	ServletContext application = request.getSession().getServletContext();
       		
        	path = application.getRealPath(myhrefaddress); //获得当前文件的上下文路径。
       		
       		/*File f = new File(path);
       		
       		path = path.replace("\\", "/");
       		byte [] buf  =  new   byte [10240*1024];
	  		int  len=0;
	  		
	  		in  =   new   BufferedInputStream(new FileInputStream(f));  
	  		 
	  		outStream =  response.getOutputStream();

	  		 while( (len=in.read(buf))!=-1){
	  		   outStream.write(buf, 0 ,len);
	  		}
       		*/
       }catch(Exception e){
    	   request.setAttribute("errorMsg", "没有相关的制度");
    	   e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
       }
		request.setAttribute("url",path);
		return "";
	}
	
	private String viewUpdateFile(HttpServletRequest request ) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap map = null;
		SimpleMap singlemap = null;
//		界面传过来的上传文件的ID 
		String uploadId = request.getParameter("upload_id");
		request.setAttribute("uploadId", uploadId) ;
		
		String menuid = request.getParameter("menu_id");
		
		String fileaddress = request.getParameter("fileaddress");
		request.setAttribute("fileaddress", fileaddress);
		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("menuid", menuid);
			request.setAttribute("menuid", menuid);
			parameterObject.set("uploadId", uploadId);
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("fileaddress", fileaddress);
			
			// retrieve attendance record lis
			List AllUpdateInformation = rulesServices.AllUpdateInformation(parameterObject);
			
			List SingleMenuInformation = rulesServices.SingleMenu(parameterObject);//  通过页面传过来的目录ID 得到该ID 对应的信息
			List AllMenuInformation = rulesServices.AllMenu(parameterObject); // 得到全部目录信息
			
			int SingleMenuInformationSize = SingleMenuInformation.size();
			int AllMenuInformationSize = AllMenuInformation.size();
			String singleMenu = "";
			String singleMenuId = "";
			for(int i=0 ; i<AllMenuInformationSize ; i++){
				map = (SimpleMap) AllMenuInformation.get(i);
				String menuName = map.getString("CODE_NAME");
				String menuId = map.getString("CODE_ID");
				for(int j=0 ; j<SingleMenuInformationSize ; j++){
					singlemap = (SimpleMap) SingleMenuInformation.get(j);
					singleMenu = singlemap.getString("MENU");
					singleMenuId = singlemap.getString("MENUID");
					map.set("singleMenu", singleMenu);
					map.set("singleMenuId", singleMenuId);
				}
					/*request.setAttribute("singleMenu", singleMenu);
					request.setAttribute("singleMenuId", singleMenuId);*/
				map.set("menuName", menuName);
				map.set("menuId", menuId);
			}
			request.setAttribute("AllMenuInformation",AllMenuInformation);
			request.setAttribute("AllUpdateInformation",AllUpdateInformation);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/safe_update_SystemView.jsp";
	}
	
	private String UpdateFile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException  {
		boolean b = true;
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		String uploadId = request.getParameter("uploadId");          //文件ID
		String singleMenu = request.getParameter("singleMenu");			//文件所在目录
		String fileloadpath = request.getParameter("fileloadpath");//数据库里存的file地址
		String Date = request.getParameter("date");					//实施日期
		
		String flag = request.getParameter("flag");
		
		String fname= "";
		
		MessageSource messageSource ;
		
		String filepath = "/upload/files"; 		
		String content = "text/html; charset=gbk";
		response.setContentType(content);
	    PrintWriter out=response.getWriter();
	    Map map = new HashMap();
		
		DiskFileUpload fu = new DiskFileUpload() ;
		List fileItems = null;
	    try {
			fileItems = fu.parseRequest(request);
			
		} catch (FileUploadException e) {
			b  = false;
			e.printStackTrace();
		}
		Iterator iter = null;
		String filename = "";
		if(fileItems!=null){
			iter = fileItems.iterator();
			String regExp=".+\\\\(.+)$";

//			 过滤掉的文件类型
			 String[] errorType={".exe",".com",".cgi",".asp",".mp4"};
			 Pattern p = Pattern.compile(regExp);
			 
			 while (iter.hasNext()) {
			    FileItem item = (FileItem)iter.next();
			    if (!item.isFormField()){
			    	   String name = item.getName();
			           long size = item.getSize();
			           if((name==null||name.equals("")) && size==0)
			               continue;
			           Matcher m = p.matcher(name);
			           boolean result = m.find();
			           if(result){
			        	   for (int temp=0;temp<errorType.length;temp++){
					           if (m.group(1).endsWith(errorType[temp])){
					                 throw new IOException(name+": wrong type");
					           }
				           }
				           try{  
					        	ServletContext application = request.getSession().getServletContext();
					       		
					       		String delPath = application.getRealPath(fileloadpath); //获得当前文件的上下文路径。
					       		String path = application.getRealPath(filepath); //获得当前文件的上下文路径。
					       		int start = item.getName().lastIndexOf("\\"); 
						    	String filenamechinese =item.getName().substring(start+1);		
						    	String endname=uploadId+filenamechinese.replaceAll("\\s*", "").substring(filenamechinese.replaceAll("\\s*", "").length()-4, filenamechinese.replaceAll("\\s*", "").length());
					       		
					       		File f = new File(path);
					       		File delFile = new File(delPath) ;
					       		
					       		if(!f.exists()){
					       			f.mkdir();
					       		}
					       		
					       		if(delFile.exists()){
					       			delFile.delete();
					       		}
//						       	保存上传的文件到指定的路径
					       		filename = filepath + "/"+ endname;
					       		
					       		fname=filenamechinese;
					       		item.write(new File(path+"\\"+endname));
				           }catch(Exception e){
				        	   b  = false;
				        	   out.println(e);
				           }
			           }else{
			        	   b = false;
			        	   throw new IOException("fail to upload");
			           } 
			    }else{
			    	String name = item.getFieldName();
			        String value = item.getString();
			        map.put(name, value);
			    }       
			 }
		}
		if(b){
//			 bind parameter
			try {
//				 bind parameter
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("AdminID", admin.getAdminID());
				//validate empid and plan_month
				parameterObject.set("uploadId", uploadId);
				parameterObject.set("singleMenu", singleMenu);
				parameterObject.set("fileloadpath", fileloadpath);
				parameterObject.set("implementationdate", Date);
				parameterObject.set("fname", fname);
				parameterObject.set("filename", filename);
				if(flag.equals("2")){
					rulesServices.UpdateSingleFileInformation(parameterObject);
				}
				if(flag.equals("1")){
					rulesServices.UpdateSingleFileInformation1(parameterObject);
				}
			
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("updateFile  Exception. ", e);
			}
			
			if(b)
				request.setAttribute("alert", "修改成功");
			else
				request.setAttribute("alert", "修改失败,请重新修改！");
			if(map.get("flag").equals("0"))
				request.setAttribute("url","/safeControlServlet?operation=rules&content=rulesAdmin&menu_code="+ parameterObject.getString("menu_code"));
			else{
				request.setAttribute("url","/safeControlServlet?operation=rules&content=viewUpdateFile&menu_code="+ parameterObject.getString("menu_code"));
			}
		}else{
			request.setAttribute("alert", "上传文件失败，请返回重新上传！");
			request.setAttribute("url","/safeControlServlet?operation=rules&content=viewUpdateFile&menu_code="+ parameterObject.getString("menu_code"));
		}
		return "/inc/alertMessage.jsp";
	}
	//删除制度
	private String deleteFile(HttpServletRequest request ) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String uploadId = request.getParameter("upload_id");
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("uploadId", uploadId);
			ServletContext application = request.getSession().getServletContext();
       		
			String fileloadpath = rulesServices.getFileloadpath(parameterObject);
			
       		String delPath = application.getRealPath(fileloadpath); //获得当前文件的上下文路径。
       		
       		File delFile = new File(delPath) ;
       		
       		if(delFile.exists()){
       			delFile.delete();
       		}
			
			Object eatLookCount = 0;
			
			parameterObject.set("uploadId", uploadId);
			rulesServices.DeleteSingleFileInformation(parameterObject);
			
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount));
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteSystem error ", e);
		}
		request.setAttribute("url","/safeControlServlet?operation=rules&content=rulesAdmin&menu_code=" + parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	
	private String addMenuView(HttpServletRequest request ) {
		this.putToolbarInfo(request);
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");

		
		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getAdminID());
			parameterObject.set("cpnyId", admin.getCpnyId());
			// retrieve attendance record list
			Object eatLookCount = 0;
			
			List AllMenuInformation = rulesServices.AllMenuInformation(parameterObject);
			
			
			request.setAttribute("AllMenuInformation", AllMenuInformation) ;
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteSystem error ", e);
		}
		return "/safe_add_MenuView.jsp";
	}
	
	private String addmenu(HttpServletRequest request ) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();

		String menuName = request.getParameter("menuName");
		String topMenuId = request.getParameter("menu");	//上级目录ID
		String activity = request.getParameter("activity");
		
		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);
			
			parameterObject.set("menuName", menuName);
			parameterObject.set("topMenuId", topMenuId);
			parameterObject.set("activity",activity);
			// retrieve attendance record list
			Object eatLookCount = 0;
			
			
			rulesServices.addmenu(parameterObject);
			
			
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addmenu error ", e);
		}
		request.setAttribute("alert", "添加成功");
		request.setAttribute("url","/safeControlServlet?operation=rules&content=menuView&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	private String menuView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap map = null;
		SimpleMap map1 = null;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);
			// retrieve attendance record list
			//得到制度查看全部显示信息
			List Menu_information = rulesServices.menuInformation_1(parameterObject);
			
			int MenuInformationSize =Menu_information.size();
			
			
			for(int i=0 ; i<MenuInformationSize ; i++){
				map = (SimpleMap) Menu_information.get(i);
				String topMenuId = map.getString("CODE_ID");
				
				
				parameterObject.set("topMenuId", topMenuId);
				Object topMenu = rulesServices.topMenu(parameterObject);
		
				map1 = (SimpleMap) topMenu;
				
				map.set("topMenu", map1.get("CODE_NAME"));
				map.set("topMenuId", map1.get("CODE_ID"));
			}
			request.setAttribute("Menu_information", Menu_information) ;
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/safe_menu_View.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String viewUpdateMenu(HttpServletRequest request ) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();

		String menuId = request.getParameter("menuid");
		String TopId = request.getParameter("TopId");
		String TopName = request.getParameter("TopName");
		
		request.setAttribute("TopId", TopId);
		request.setAttribute("TopName", TopName);
		
		SimpleMap map = null;
		SimpleMap map1 = null;
		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("menuId", menuId);
			parameterObject.set("topmenuId", TopId);
			parameterObject.set("cpnyId", cpnyId);
			// retrieve attendance record list
			Object eatLookCount = 0;
			String topmenuName = "";
			String topmenuid = "";
			List systemName = rulesServices.menuName(parameterObject);//通过界面传过来的目录ID 得到相关信息
			
			List allMenu = rulesServices.AllMenu(parameterObject); // 得到所有目录 的 相关信息
				List singleMenu = rulesServices.singleMenu(parameterObject);
				for(int j=0 ; j<singleMenu.size() ; j++){	
					map1 = (SimpleMap) singleMenu.get(j);
					topmenuName = map1.getString("CODE_NAME");
					topmenuid = map1.getString("CODE_ID");
				}
				request.setAttribute("topmenuName", topmenuName);
				request.setAttribute("topmenuid", topmenuid);
			
			request.setAttribute("allMenu", allMenu);
			
			request.setAttribute("systemName", systemName) ;
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteSystem error ", e);
		}
		return "/safe_update_MenuView.jsp";
	}
	
	private String updatemenu(HttpServletRequest request ) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();

		String menuName = request.getParameter("menuName");
		String topMenuId = request.getParameter("topMenu");	//上级目录ID
		String menuId = request.getParameter("menuId");
		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("menuName", menuName);
			parameterObject.set("topMenuId", topMenuId);
			parameterObject.set("menuId", menuId);
			// retrieve attendance record list
			Object eatLookCount = 0;
			
			
			rulesServices.updatemenu(parameterObject);
			
			
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addmenu error ", e);
		}
		request.setAttribute("alert", "修改成功");
		request.setAttribute("url","/safeControlServlet?operation=rules&content=menuView&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
//	删除目录
	private String deleteMenu(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String menuid = request.getParameter("menuid");
		
		SimpleMap map = null;
		SimpleMap map1 = null;
		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list
			Object eatLookCount = 0;
			
			parameterObject.set("menuid", menuid);
			
			List parentCode = rulesServices.parentCode(parameterObject);//得到所有每个目录的夫及目录
			
			for(int i=0 ; i<parentCode.size() ; i++){
				map = (SimpleMap) parentCode.get(i);
				String parent = map.getString("PARENTCODE");
				if(parent.equals("menuid")){
					request.setAttribute("alert", "不能删除！必须先把该目录的下级目录全删除后才可删除！");
				}
			}
			
			rulesServices.DeleteSingleMenuInformation(parameterObject);
			
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteSystem error ", e);
		}
		request.setAttribute("alert", "删除成功");
		request.setAttribute("url","/safeControlServlet?operation=rules&content=menuView&menu_code=" + parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	private String systemView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleMap map = null;
		SimpleMap map1 = null;
		SimpleMap map2 = null;
		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);
			// retrieve attendance record list
			//得到制度查看全部显示信息
			List Menu_information = rulesServices.menuInformation(parameterObject);
			int MenuInformationSize =Menu_information.size();
			
			for(int i=0 ; i<MenuInformationSize ; i++){
				map = (SimpleMap) Menu_information.get(i);
				String topMenuId = map.getString("CODE_ID");
				String systemId = map.getString("SYSTEM_ID");
				map.set("systemId", systemId);
				parameterObject.set("systemId", systemId);
				List fileInfo = rulesServices.getFileInfo(parameterObject);
				List l = new ArrayList();
				for(int j=0 ; j<fileInfo.size() ; j++){
					map2 = (SimpleMap) fileInfo.get(j);
					String fileName = map2.getString("UPLOAD_NAME");
					String fileId = map2.getString("UPLOAD_ID");
					String FILEPATH = map2.getString("UPLOAD_PATH");
					map2.set("fileName", fileName);
					map2.set("fileId", fileId);
					map2.set("FILEPATH", FILEPATH);
					l.add(map2);
					map.set("l", l);
				}
				parameterObject.set("topMenuId", topMenuId);
				Object topMenu = rulesServices.topMenu(parameterObject);
				map1 = (SimpleMap) topMenu;
				map.set("topMenu", map1.get("CODE_NAME"));
				map.set("topMenuId", map1.get("CODE_ID"));
			}
			request.setAttribute("Menu_information", Menu_information) ;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/safe_system_look.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String Jump(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap map = null;

		String systemId = request.getParameter("systemId");
		
	//	int Subset;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("systemId", systemId);
			// retrieve attendance record list
			//得到顶级目录
		//	List nextMenu = rulesServices.nextMenu(parameterObject);
			
			//判断是否有子集
	//		Subset = rulesServices.yesornoSubset(parameterObject);
			
			
			List fileview = rulesServices.fileview(parameterObject);
			
		//	request.setAttribute("nextMenu", nextMenu);
			request.setAttribute("fileview", fileview);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
			return "/safe_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String SearchSystem(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap map = null;

		String systemName = request.getParameter("systemname");
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("systemName", systemName);
			// retrieve attendance record list
			
			
			List fileview = rulesServices.getSystemName(parameterObject);
			
			request.setAttribute("fileview", fileview);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/safe_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}