package com.ait.gm.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.business.WoodProductsServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;
import com.ait.util.*;
import com.ait.utils.ConnBean;
/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author yangxiaohui (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class WoodProductsCommand implements Command {
	
	private  WoodProductsServices wPServices=null;
	
	private Mail mail ;
	
	private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	public WoodProductsCommand(){
		wPServices= new WoodProductsServices();				
		
		mail = new Mail() ;
	}


	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		if(content.equals("wpApplyConfirm") && content!=null){		
			this.getDocumentListForConfig(request,admin);			
			return "/gm_wpApply_confirm.jsp";
			
		}else if(content.equals("wpApplyConfirmForExcel") && content!=null){			
			this.getDocumentListForConfig(request,admin);
			return "/reports/ga_report/ga_woodproducts.jsp";
			
		}else if(content.equals("wpApplyConfirmSummaryExcel") && content!=null){
			String codeStr="";
			List codeType=admin.getCodeType();		
			List result= GetLoginCodeType.getLoginCodeType(codeType, request.getParameter("menu_code"));		
			for(int i=0;i<result.size();i++){
				codeStr +="'"+(String)result.get(i)+"',";
			}
			if(!codeStr.equals("")){
			codeStr=codeStr.substring(0,codeStr.length()-1);
			}
			return "/reports/ga_report/ga_AreaComplete_Orders.jsp?startDate="+request.getParameter("startDate")+"&endDate="+request.getParameter("endDate")+"&cpnyId="+admin.getCpnyId()+"&codeStr="+codeStr;
		}else if(content.equals("wpConfirmOrdersMore") && content!=null){
			this.wpConfirmOrdersS(request, admin);				
			this.getDocumentListForConfig(request,admin);
			return "/gm_wpApply_confirm.jsp";			
		}else if(content.equals("SealproductionSet") && content!=null){
			SimpleMap simpleMap = new SimpleMap();			
			simpleMap.set("cpnyId", admin.getCpnyId());
			List woodProductsList = wPServices.getWoodProductsSet(simpleMap);			
			request.setAttribute("woodProductsList", woodProductsList);
			return "/gm_sealproduction.jsp";
			
		}else if(content.equals("addSealproductionSetPageView") && content!=null){
			request.setAttribute("seqId", this.getSequence("gm_woodproducts_set_seq"));
			return "gm/gm_sealproductionSet.jsp";
		}/*else if(content.equals("addProductsImage") && content!=null){				
			return this.uploadImp(request);
		}else if(content.equals("ProductsImageView") && content!=null){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("key", request.getParameter("key"));
			List list = wPServices.ProductsImageView(simpleMap);
			request.setAttribute("list", list);
			return "/util/ga_file_view.jsp?control=gmControlServlet&operation=wpCommand&content=deletePhoto&isDelete="+request.getParameter("isDelete")+"&key="+request.getParameter("key");
		}else if(content.equals("deletePhoto") && content!=null){			
			return this.deletePhoto(request);
		}*/else if(content.equals("addSealproductionSet") && content!=null){
			wPServices.addWoodProductsSet(request, admin);
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("cpnyId", admin.getCpnyId());
			List woodProductsList = wPServices.getWoodProductsSet(simpleMap);			
			request.setAttribute("woodProductsList", woodProductsList);
			return "/gm_sealproduction.jsp";
		}else if(content.equals("getSealproductionObject") && content!=null){			
			Object woodProducts = wPServices.getWoodProductsObject(request.getParameter("seqId"));
			request.setAttribute("woodProducts", woodProducts);
			return "/gm_update_sealproduction.jsp";
		}else if(content.equals("updateSealproductionSet") && content!=null){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("cpnyId", admin.getCpnyId());
			wPServices.updateWoodProductsSet(request, admin);
			List woodProductsList = wPServices.getWoodProductsSet(simpleMap);			
			request.setAttribute("woodProductsList", woodProductsList);
			return "/gm_sealproduction.jsp";
			
		}else if(content.equals("deleteSealproductionSet") && content!=null){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("cpnyId", admin.getCpnyId());
			wPServices.deleteWoodProductsSet(request);
			List woodProductsList = wPServices.getWoodProductsSet(simpleMap);
			request.setAttribute("woodProductsList", woodProductsList);
			return "/gm_sealproduction.jsp";
			
		}else if(content.equals("woodProductionList") && content!=null){
			return this.woodProductionList(request, admin);
		}else if(content.equals("addWoodProduction") && content!=null){
			return this.addWoodProduction(request, admin);			
		}else if(content.equals("updateWoodProduction") && content!=null){
			return this.updateWoodProduction(request, admin);	
		}else if(content.equals("deleteWoodProduction") && content!=null){
			return this.deleteWoodProduction(request, admin);	
		}else{
			
			return "/error.jsp";
		}
	
	}
	
	/*得到所有的木制品信息*/
	public void getDocumentListForConfig(HttpServletRequest request,AdminBean admin){
	try{
		String codeStr="";
		List codeType=admin.getCodeType();		
		List result= GetLoginCodeType.getLoginCodeType(codeType, request.getParameter("menu_code"));		
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
		for(int i=0;i<result.size();i++){
			codeStr +="'"+(String)result.get(i)+"',";
		}
		if(!codeStr.equals("")){
		codeStr=codeStr.substring(0,codeStr.length()-1);
		}
		SimpleMap parameterObject = null;		
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("confirmorId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());	
		parameterObject.set("cpnyId", cpnyId);	
		parameterObject.set("active", "1");	
		parameterObject.set("applyNoExcel", request.getParameter("applyNoExcel"));
		parameterObject.set("codeType", codeStr);			
		String status = StringUtil.checkNull(request.getParameter("status"),"0");
		parameterObject.set("status", status);
		int pageSize =10;
		int pageGroupSize =10;
		int currentPage = 0;
		SimpleMap dataMap =null;
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
       //取得数据行数
		int resultCount = wPServices.getWoodProductsAffirmInfoListNumber(parameterObject);
		List confirmList=wPServices.getWoodProductsAffirmInfoList(parameterObject,currentPage,pageSize);
		request.setAttribute("documentListSize", confirmList.size()+"");
		for(int i=0;i<confirmList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)confirmList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=wPServices.getWoodProductsAffirmorList(parameterObject1);
			List  woodProductsList=wPServices.getWoodProductsList(parameterObject1);
			dataMap.set("affirmorList", affirmorList);
			dataMap.set("woodProductsList", woodProductsList);		
		}
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		request.setAttribute("status", status);		
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("cpnyId", cpnyId);
		
	    request.setAttribute("confirmList", confirmList);
	    if(confirmList==null || confirmList.size()==0){
	    	   request.setAttribute("errorMsg", "没有相关信息！");	
	    }
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getDocumentListForConfig happens Exception. ", e);
	}     
  }
	
	
	private void wpConfirmOrdersS(HttpServletRequest request, AdminBean admin) {	
		List paramList = new ArrayList();
		try {
					
			
			String applyNo[] =request.getParameterValues("counts");
			for(int i=0 ; i<applyNo.length ; i++){
				SimpleMap simpleMap = new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());					
				simpleMap.set("applyNo", request.getParameter("applyNo_"+applyNo[i]));
				simpleMap.set("confirmorIdea", request.getParameter("confirmorIdea_"+applyNo[i]));
				simpleMap.set("flag", request.getParameter("flag"));
				paramList.add(simpleMap);				
			}
			wPServices.updateGa_woodproducts_apply(paramList);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("wpConfirmOrdersS error ", e);
		}
	}	
	/*得到序列号*/
	public int getSequence(String seqName) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT " + seqName + ".NEXTVAL FROM DUAL";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getInt(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得序列号失败", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}
	public String  uploadImp(HttpServletRequest request){		
		String result="";
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		DiskFileUpload fu = new DiskFileUpload();
		if(request.getParameter("jpgUpload")!=null && request.getParameter("jpgUpload")!=""){
		String key=	request.getParameter("key");		 
			 List fileItems = null;
			    try {
					fileItems = fu.parseRequest(request);				
				Iterator iter = null;
				if(fileItems!=null){
					iter = fileItems.iterator();
					 while (iter.hasNext()) {						
						    FileItem item = (FileItem)iter.next();
						    if (!item.isFormField() && !item.getName().equals("")){
						    	ServletContext application = request.getSession().getServletContext();
						    	String filepath = "/upload/products"; 						    	
						    	String path = application.getRealPath(filepath);
						    	File file = null;						    	
						    	int start = item.getName().lastIndexOf("\\"); 
						    	String name =item.getName().substring(start+1);						    	
						    	file = new File(path); 
						    	
						    	if(!file.exists()){
						    		file.mkdir();
					       		}
						    	
						    
						    	File objectfile= new File(path+"\\"+key+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	if(objectfile.exists()){ 
						    		objectfile.delete(); 
						    		String sql="update  FILE_OR_PHOTO_PATH  set  CHINESENAME=? where DOCUMENTNO=?";						    				
									ps = conn.prepareStatement(sql);
							    	ps.setString(1,name.replaceAll("\\s*", ""));
							    	ps.setString(2,key);							    	
							    	ps.executeUpdate();	
							    }else{
							    	
									String sql="insert into FILE_OR_PHOTO_PATH(DOCUMENTNO,CHINESENAME,PATHADDRESS,filename) values (?,?,?,?)";
									ps = conn.prepareStatement(sql);
							    	ps.setString(1,key);
							    	ps.setString(2,name.replaceAll("\\s*", ""));
							    	ps.setString(3,"../upload/products/"+key+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
							    	ps.setString(4,key+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
							    	ps.executeUpdate();	
							    }
						    						    
								item.write(objectfile);
								
						    }
					 }
				}
			    } catch (Exception e) {				
					e.printStackTrace();
				}finally {
					SqlUtil.close(ps, conn);
				}
				result="上传失败！";
		
	 }
		result="上传成功！";		
		return "/util/ga_upload.jsp?Directive=Directive";
		
	}
	public String  deletePhoto(HttpServletRequest request){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql = "delete  from FILE_OR_PHOTO_PATH t where t.documentno=? and t.filename=?";
		File myDir = null;
		String fillname = request.getParameter("fileName");
		try {
		ServletContext application = request.getSession().getServletContext();
		String filepath = "/upload/products";
		String path = application.getRealPath(filepath);
		myDir = new File(path + "\\" + fillname);
		myDir.delete();
		ps = conn.prepareStatement(sql);
		ps.setString(1, request.getParameter("key"));	
		ps.setString(2, fillname);	
		ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(ps, conn);
		}	
		SimpleMap simpleMap = new SimpleMap();
		simpleMap.set("key", request.getParameter("key"));
		List list = wPServices.ProductsImageView(simpleMap);
		request.setAttribute("list", list);
		return "/util/ga_file_view.jsp?control=gmControlServlet&operation=wpCommand&content=deletePhoto&isDelete=1&key="+request.getParameter("key");
	}
	public String woodProductionList(HttpServletRequest request,AdminBean admin){
		try{			
			SimpleMap parameterObject = null;		
			parameterObject = ObjectBindUtil.getData(request);		
			parameterObject.set("cpnyId", admin.getCpnyId());
			int pageSize =10;
			int pageGroupSize =10;
			int currentPage = 0;
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
			int resultCount = wPServices.getwoodProductionListNumber(parameterObject);
			List woodProductsList=wPServices.getwoodProductionList(parameterObject,currentPage,pageSize);
			request.setAttribute("productionName", request.getParameter("productionName"));
			request.setAttribute("specificationNo", request.getParameter("specificationNo"));
		    request.setAttribute("woodProductsList", woodProductsList);
		    request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		    if(woodProductsList==null || woodProductsList.size()==0){
		    	   request.setAttribute("errorMsg", "没有相关信息！");	
		    }
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getDocumentListForConfig happens Exception. ", e);
		}   
		return "/gm_woodporduction_list.jsp";
	  }
	public String updateWoodProduction(HttpServletRequest request,AdminBean admin){			
			try{			
				SimpleMap parameterObject = null;		
				parameterObject = ObjectBindUtil.getData(request);		
				parameterObject.set("cpnyId", admin.getCpnyId());	
				parameterObject.set("adminId", admin.getAdminID());
				wPServices.updateWoodProduction(parameterObject);			
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("updateWoodProduction happens Exception. ", e);
			}   
			return this.woodProductionList(request, admin);
	  }
	public String addWoodProduction(HttpServletRequest request,AdminBean admin){
		try{			
			SimpleMap parameterObject = null;		
			parameterObject = ObjectBindUtil.getData(request);		
			parameterObject.set("cpnyId", admin.getCpnyId());	
			parameterObject.set("adminId", admin.getAdminID());
			wPServices.addWoodProduction(parameterObject);			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addWoodProduction happens Exception. ", e);
		}   
		return this.woodProductionList(request, admin);
	  }
	public String deleteWoodProduction(HttpServletRequest request,AdminBean admin){		
			try{			
				SimpleMap parameterObject = null;		
				parameterObject = ObjectBindUtil.getData(request);		
				parameterObject.set("cpnyId", admin.getCpnyId());	
				parameterObject.set("adminId", admin.getAdminID());
				wPServices.deleteWoodProduction(parameterObject);			
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("deleteWoodProduction happens Exception. ", e);
			}   
			return this.woodProductionList(request, admin);
	  }
}