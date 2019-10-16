package com.ait.reports.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;

public class Report {

	public String type = ""; // 报表类型

	public String name = ""; // 报表名称

	public String path = ""; // 报表路径(/reports/下的相对路径)

	public String id = ""; // 报表文件名称

	public List parameters = new ArrayList(); // 参数名称列表

	@SuppressWarnings("unchecked")
	public Report(String reportid) {
		Logger.getLogger(getClass()).debug("reportid : " + reportid);
		try {
			String reportType = "crystal";
			this.id = reportid;
			this.type = reportType;
			Document doc = (new SAXBuilder()).build(Configuration.getConfFileLocation("reports.xml"));
			Element element = doc.getRootElement().getChild(reportType).getChild(reportid);
			if(element == null){
				 throw new GlRuntimeException("reportid : " + reportid);
			}
			Logger.getLogger(getClass()).debug("element : " + element.getName());
			this.name = element.getAttributeValue("name");
			this.path = element.getAttributeValue("path");
			List elements = element.getChildren();
			for (int i = 0; i < elements.size(); i++){
			    	
				//this.parameters.add(((Element) elements.get(i)).getAttributeValue("id"));
			    	Map mValue=new HashMap();
			    	mValue.put("id", ((Element) elements.get(i)).getAttributeValue("id"));
			    	mValue.put("type", ((Element) elements.get(i)).getAttributeValue("type"));
			    	this.parameters.add(mValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得报表参数失败", e);
		}
	}
}
