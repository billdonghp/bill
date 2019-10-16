/*
 * @(#)TemplateContent.java 1.0 2006-12-6 上午10:35:17
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.template.content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.Education;
import com.ait.sqlmap.template.Person;
import com.ait.sqlmap.template.services.TemplateServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.util.StringUtil;
import com.ait.web.Content;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-6 上午10:35:17
 * @version 1.0
 * 
 */
public class TemplateContent implements Content {

	private TemplateServices templateServices;

	private Person person;

	private SimpleMap simpleMap;

	public TemplateContent() {

		templateServices = new TemplateServices();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Content#transfer(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String transfer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			// TODO Auto-generated method stub
			simpleMap = ObjectBindUtil.getData(request);
			Logger.getLogger(this.getClass()).debug("ITEM_NAME : " + simpleMap.getString("ITEM_NAME"));

/******************************** set paging config ****************************************************/
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (simpleMap.getString("currentPage") != null)
				currentPage = Integer.parseInt(simpleMap
						.getString("currentPage"));
/********************************* paging search ****************************************************/
			// retrieve attendance item list by paging
			List list = (List) templateServices.retrieveArItemList(simpleMap,
					currentPage, pageSize);
			// retrieve attenddance item list count for paging
			String resultCount = ((SimpleMap) templateServices
					.retrieveArItemListCnt(simpleMap)).getString("CNT");

/******************************** other template **************************************************/
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("sqlStatement", "26 AGE,'北京'  ADD1");
			parameterObject.setString("condition", "1");
			
			// test sql map for bean parameter and dynamic sql
			Person person = (Person) templateServices
					.testSqlMap(parameterObject);

			// insert data to AR_ITEM
			simpleMap.setInt("ITEM_NO", 100);
			simpleMap.setString("ITEM_NAME", "test");
			simpleMap.setString("SHORT_NAME", "test");
			simpleMap.setString("DESCRIPTION", "sqlmap test");
			simpleMap.setInt("ACTIVITY", 1);
			simpleMap.setString("CREATED_BY", "kelly");
			simpleMap.setString("UPDATED_BY", "kelly");
			simpleMap.setFloat("MIN_UNIT", Float.parseFloat("1"));

			// templateServices.insertArItem(simpleMap);

			// test transation
			simpleMap.setString("ITEM_NAME", "TestTransation");
			Object result = templateServices.testTransation(simpleMap);

			// test bind form data to bean
			Person bean = new Person();
			List beanList = new ArrayList();
			// 绑定表单数据到一个Bean对象
			// ObjectBindUtil.setFormBean (request, bean);

			// 绑定表单数据到一个包含Bean的List对象(Request,Bean,List,行数，分割符，默认为后缀) 样式：
			// name_23
			// ObjectBindUtil.setFormBean(request, bean, beanList, 2, "_");

			// 绑定表单数据到一个包含Bean的List对象(Request,Bean,List,行数，默认为后缀) 样式：name23
			// ObjectBindUtil.setFormBean(request, bean, beanList, 2);

			// 绑定表单数据到一个包含Bean的List对象(Request,Bean,List,行数，分割符，前缀) 样式：23_name
			ObjectBindUtil.setFormBean(request, bean, beanList, 2, "_",
					"prefix");
			
			// test select tag
			List postList = templateServices.retrievePostList();
			request.setAttribute("postList", postList);   
			
			// test sqlServer template
			List postList2= templateServices.retrievePostListForSqlserver();
			request.setAttribute("postList2", postList2);
			
			// test create table SQL
			//templateServices.createTableTest(parameterObject);
			
			// test insert by bean parameter
			Education education = new Education();
			education.setEmpID("00000-0");
			education.setStartDate("2005-01-01");
			education.setEndDate("2005-12-31");
			education.setSubjectCode("Subject209");
			education.setDegreeCode("DegreeCode140");
			education.setInstitutionName("清华大学");
			//templateServices.insertEducationInfo(education);
			
			// test i18n 
			SimpleMap map = new SimpleMap();
//			map.setString("POST_ID", StringUtil.toCN(request.getParameter("POST_ID")));
//			map.setString("POST_NAME", StringUtil.toCN(request.getParameter("POST_NAME")));
//			map.setString("POST_EN_NAME", StringUtil.toCN(request.getParameter("POST_EN_NAME")));
//			map.setString("POST_GROUP", StringUtil.toCN(request.getParameter("POST_GROUP")));
			map.setString("POST_ID", request.getParameter("POST_ID"));
			map.setString("POST_NAME", request.getParameter("POST_NAME"));
			map.setString("POST_EN_NAME", request.getParameter("POST_EN_NAME"));
			map.setString("POST_GROUP", request.getParameter("POST_GROUP"));
			templateServices.insertPostInfo(map);
			
			
			request.setAttribute("ArItemList", list);
			request.setAttribute("person", person);
			request.setAttribute("result", result.toString());
			request.setAttribute("beanList", beanList);

/*****************set paramter for paging tag***********************************************************/
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
			// 必须将查询条件再返回给分页页面作为分页参数
			request.setAttribute("ITEM_NO", null);
			request.setAttribute("ITEM_NAME", "");
/****************************************set value for checkbox tag********************************************/
			request.setAttribute("USE_YN", "N");
/****************************************set value for date tag********************************************/
			String year = simpleMap.getString("YEAR");
			String month = simpleMap.getString("MONTH");
			request.setAttribute("year", year);
			request.setAttribute("month", month);
/****************************************set value for code class tag********************************************/
			request.setAttribute("applyType", simpleMap.getString("applyType"));
			
			return "/template.jsp";
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("TemplateContent.transfer() Exception: " + e);
			throw new GlRuntimeException("Process Logic Exception." , e);
		}
	}
}
