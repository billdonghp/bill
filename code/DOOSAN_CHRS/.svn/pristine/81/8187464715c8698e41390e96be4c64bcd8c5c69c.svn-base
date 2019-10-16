package com.ait.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author  yangxiaohui (yangxiaohui@ait.net.cn)
 * @Date 2009-10-23 上午14:01:22
 * @version 1.0
 * 
 */
public class UploadFileUtil {
	
	String filepath="";
	int fileNo=0;
	String oldFileName="";
	String saveFileAddress="";
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	
	public String getSaveFileAddress() {
		return saveFileAddress;
	}
	public void setSaveFileAddress(String saveFileAddress) {
		this.saveFileAddress = saveFileAddress;
	}
	public Object getUploadFileFormObjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 HashMap<String, String> formParamMap = new HashMap<String, String>();   //存放form中的一般属性		
		 DiskFileUpload fu = new DiskFileUpload();
		 List fileItems = null; 	      
		 Object object=null;
		 try {
			fileItems = fu.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	   Iterator fileItr = fileItems.iterator();	  
		while (fileItr.hasNext()) {   
            FileItem fileItem =null;              
            // 得到当前文件   
            fileItem = (FileItem) fileItr.next();   
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)   
            if (fileItem.isFormField()) {   
                String formname=fileItem.getFieldName();//获取form中的名字   
                String formcontent=fileItem.getString("UTF-8");               
                formParamMap.put(formname, formcontent);   
            }else{
            	if( !fileItem.getName().equals("")){
            	  ServletContext application = request.getSession().getServletContext();
            	  String path = application.getRealPath(this.getFilepath());
            	  int start = fileItem.getName().lastIndexOf("\\"); 
			      String name =fileItem.getName().substring(start+1);	
			      this.setOldFileName(name.replaceAll("\\s*", "").substring(0,name.replaceAll("\\s*", "").length()-4));
			      this.setSaveFileAddress(".."+this.getFilepath()+"/"+this.getFileNo()+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
			      File objectfile= new File(path+"\\"+this.getFileNo()+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
			      try {
					fileItem.write(objectfile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	}else{
            		System.out.println("没有上传文件！");
            	}
            }            
               
         }	
		return formParamMap;
	}

}
