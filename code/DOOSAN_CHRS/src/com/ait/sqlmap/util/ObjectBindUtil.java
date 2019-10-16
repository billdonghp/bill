/*
 * @(#)ObjectBindUtil.java 1.0 2006-12-6 上午11:19:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sqlmap.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.util.DateUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-6 上午11:19:54
 * @version 1.0
 * 
 */
public class ObjectBindUtil {

	private ObjectBindUtil() {
		/* empty */
	}

	/**
	 * bind attribute data to Map object from Request Object
	 * 
	 * @param req
	 * @return SimpleMap
	 */
	@SuppressWarnings("unchecked")
	public static SimpleMap getAttributeBox(HttpServletRequest req) {
		SimpleMap data = new SimpleMap("REQUEST_DATA");
		Enumeration e = req.getAttributeNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			data.put(key, req.getAttribute(key));
		}
		return data;
	}

	/**
	 * bind parameter data to Map object from Request Object
	 * 
	 * @param req
	 * @return SimpleMap
	 */
	@SuppressWarnings("unchecked")
	public static SimpleMap getData(HttpServletRequest req) {
		SimpleMap data = new SimpleMap("REQUEST_DATA");
		Enumeration e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			data.put(StringUtil.toCN(key), StringUtil.toCN(req.getParameter(key)));
		}
		return data;
	}
	/**
	 * bind parameter data to Map object from Request Object
	 * 
	 * @param req
	 * @return SimpleMap
	 */
	@SuppressWarnings("unchecked")
	public static SimpleMap getData(HttpServletRequest req,String embellish) {
		SimpleMap data = new SimpleMap("REQUEST_DATA");
		Enumeration e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if(key.indexOf(embellish)>0){
			    data.put(StringUtil.toCN(key.replaceAll(embellish, "")), StringUtil.toCN(req.getParameter(key)));
			}
		}
		return data;
	}
	/**
	 * bind data to Map object from Cookie Object
	 * 
	 * @param req
	 * @return SimpleMap
	 */
	@SuppressWarnings("unchecked")
	public static SimpleMap getDataFromCookie(HttpServletRequest req) {
		SimpleMap cookieData = new SimpleMap("COOKIE_DATA");
		Cookie[] cookies = req.getCookies();
		if (cookies == null)
			return cookieData;
		for (int i = 0; i < cookies.length; i++) {
			String key = cookies[i].getName();
			String value = cookies[i].getValue();
			if (value == null)
				value = "";
			String cookiesValue = value;
			cookieData.put(key, cookiesValue);
		}
		return cookieData;
	}

	/**
	 * deep clone for Map object
	 * 
	 * @param data
	 * @return SimpleMap
	 */
	@SuppressWarnings("unchecked")
	public static SimpleMap deepClone(SimpleMap data) {
		SimpleMap newData = new SimpleMap(data.getName());
		SimpleMap src = data;
		SimpleMap target = newData;
		Set set = src.keySet();
		Iterator e = set.iterator();
		while (e.hasNext()) {
			String key = (String) e.next();
			Object value = src.get(key);
			target.put(key, value);
		}
		return newData;
	}

	/**
	 * bind form data to list's bean
	 * 
	 * @param request
	 * @param bean
	 * @param list
	 * @param rowCnt
	 * @param separator
	 */
	public static void setFormBean(HttpServletRequest request, Object bean,
			List list, int rowCnt) {
		setFormBean(request, bean, list, rowCnt, null, null);
	}
	
	/**
	 * bind form data to list's bean by embellish type
	 * 
	 * @param request
	 * @param bean
	 * @param list
	 * @param rowCnt
	 * @param separator
	 */
	public static void setFormBean(HttpServletRequest request, Object bean,
			List list, int rowCnt, String separator) {
		setFormBean(request, bean, list, rowCnt, separator, null);
	}

	/**
	 * bind form data to list's bean by embellish type and separator
	 * 
	 * @param request
	 * @param bean
	 * @param list
	 * @param rowCnt
	 * @param separator
	 */
	@SuppressWarnings("unchecked")
	public static void setFormBean(HttpServletRequest request, Object bean,
			List list, int rowCnt, String separator, String embellishType) {

		if (separator == null) {
			separator = "";
		}

		Class c = bean.getClass();

		try {
			// get bean object list
			for (int i = 1; i <= rowCnt; i++) {

				// clone new Bean obeject
				Object cloneBean = c.newInstance();

				// bind form data to clone bean

				if (embellishType == null || embellishType.equals("")
						|| embellishType.equals("suffix")) {

					// embellish type is suffix(default type is suffix). style:
					// _28
					setFormBean(request, cloneBean, separator + i,
							embellishType);

				} else {

					// embellish type is prefix. style: 28_
					setFormBean(request, cloneBean, i + separator,
							embellishType);
				}

				// add bean to list
				list.add(cloneBean);
			}
		} catch (Exception e) {
			Logger
					.getLogger(ObjectBindUtil.class)
					.error(
							"ObjectBindUtil.setFormBean(ttpServletRequest request, Object bean,List list, int rowCnt, String separator, String embellishType) Exception: "
									+ e.toString());
		}
	}

	/**
	 * bind form data to bean
	 * 
	 * @param request
	 * @param bean
	 */
	public static void setFormBean(HttpServletRequest request, Object bean) {

		setFormBean(request, bean, null, null);
	}

	/**
	 * bind form data to bean by embelish
	 * @param request
	 * @param bean
	 * @param embellish
	 */
	public static void setFormBean(HttpServletRequest request, Object bean, String embellish) {

		setFormBean(request, bean, embellish, null);
	}
	
	/**
	 * bind form data to bean
	 * 
	 * @param request
	 * @param bean
	 */
	public static void setFormBean(HttpServletRequest request, Object bean,
			String embellish, String embellishType) {

		Class c = bean.getClass();

		// get method list
		Method[] ms = c.getMethods();

		// matching request parameter to method
		for (int i = 0; i < ms.length; i++) {
			// get method name
			String name = ms[i].getName();

			// get method of start with 'set'
			if (name.startsWith("set")) {

				// get parameter type list for the method
				Class[] cc = ms[i].getParameterTypes();
				// bean setter have a parameter only
				if (cc.length == 1) {
					// get setter method parameter type
					String type = cc[0].getName();
					try {
						// get request parameter name
						String prop = Character.toLowerCase(name.charAt(3))
								+ name.substring(4);
						
						String param;

						// get request parameter value
						if (embellish == null || embellish.equals("")) {

							// no embellish
							param = getParameterValue(request, prop);
						} else {

							if (embellishType == null
									|| embellishType.equals("")
									|| embellishType.equals("suffix")) {

								// embellish type is suffix
								param = getParameterValue(request, prop
										+ embellish);
							} else {

								// embellish type is prefix
								param = getParameterValue(request, embellish
										+ prop);
							}
						}
						if (param != null && !param.equals("")) {
							if (type.equals("java.lang.String")) {
								ms[i].invoke(bean, new Object[] { StringUtil
										.toCN(param) });
							} else if (type.equals("int")
									|| type.equals("java.lang.Integer")) {
								ms[i].invoke(bean, new Object[] { new Integer(
										param) });
							} else if (type.equals("long")
									|| type.equals("java.lang.Long")) {
								ms[i].invoke(bean, new Object[] { new Long(
										param) });
							} else if (type.equals("double")
								|| type.equals("java.lang.Double")) {
        							ms[i].invoke(bean, new Object[] { new Double(
        									param) });
							} else if (type.equals("boolean")
									|| type.equals("java.lang.Boolean")) {
								ms[i].invoke(bean, new Object[] { Boolean
										.valueOf(param) });
							} else if (type.equals("java.util.Date")) {
								Date date = DateUtil.parseDate(param,
										"yyyy-MM-dd");
								if (date != null)
									ms[i].invoke(bean, new Object[] { date });
							}
						}
					} catch (Exception e) {

						Logger
								.getLogger(ObjectBindUtil.class)
								.error(
										"ObjectBindUtil.setFormBean(HttpServletRequest request, Object bean, String embellish, String embellishType) Exception: "
												+ e.toString());
					}
				}
			}
		}
	}

	/**
	 * get request paramter value
	 * 
	 * @param request
	 * @param parameter
	 * @return String
	 * @throws Exception
	 */
	private static String getParameterValue(HttpServletRequest request,
			String parameter) throws Exception {

		String parameterValue = null;

		if (request.getAttribute(parameter) != null
				&& !request.getAttribute(parameter).equals("")) {

			parameterValue = request.getAttribute(parameter).toString();

		} else if (request.getParameter(parameter) != null
				&& !request.getParameter(parameter).equals("")) {

			parameterValue = request.getParameter(parameter);
		}

		return parameterValue;
	}
}
