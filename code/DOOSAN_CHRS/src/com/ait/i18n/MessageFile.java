package com.ait.i18n;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.UserConfiguration;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-8-29 下午11:01:40
 * @version 1.0
 * 
 */
public class MessageFile implements Message {

	public ResourceBundle resourceBundle;

	public ManageType typeInstance = null;

	private UserConfiguration userConfig;

	private static final String defaultSysFile = "/system.properties";

	/**
	 * structure message based on file
	 * 
	 * @throws GlRuntimeException
	 */
	public MessageFile() throws GlRuntimeException {

		typeInstance = new LangType();
		userConfig = UserConfiguration.getInstance(defaultSysFile);
	}

	/**
	 * structure message based on file
	 * 
	 * @param sysFile
	 * @throws GlRuntimeException
	 */
	public MessageFile(String sysFile) throws GlRuntimeException {

		typeInstance = new LangType();
		userConfig = UserConfiguration.getInstance(sysFile);
	}

	/**
	 * initialize 
	 * 
	 * @param module
	 * @param locale
	 * @param charsetName
	 * @throws GlRuntimeException
	 */
	public void initialize(String module, Locale locale, String charsetName) throws GlRuntimeException {
		typeInstance.initialize(module, locale, charsetName);
	}

	/**
	 * get international message 
	 * 
	 * @param code
	 * @return
	 * @throws GlRuntimeException
	 */
	public String getMessage(String code) throws GlRuntimeException {

		String message = null;
		getResourceBundle();

		try {

			if (!typeInstance.getCharset().equals("")) {
				message = new String(resourceBundle.getString(code).getBytes("iso-8859-1"), typeInstance.getCharset());
			} else {
				message = resourceBundle.getString(code);
			}

		}catch (MissingResourceException mre) {

			Logger.getLogger(getClass()).error("Can't find such a Message Code in Message File. " + mre);
			return "";
			//throw new GlRuntimeException("Can't find such a Message Code in Message File", mre);
		} catch (Exception e) {
			
			Logger.getLogger(getClass()).error("Can't find such a Message Code in Message File. " + e);
			return "";
			//throw new GlRuntimeException("Can't find such a Message Code in Message File", e);
		}

		return message;
	}

	public void changeLocale(Locale locale) {
		typeInstance.setLocale(locale);
	}

	public void changeLocale(Locale locale, String charsetName) {
		typeInstance.setLocale(locale);
		typeInstance.setCharset(charsetName);
	}

	public void changeModule(String module) {
		typeInstance.setModule(module);
	}

	/**
	 * get resource base name 
	 * 
	 * @return
	 * @throws GlRuntimeException
	 */
	public String getBaseName() throws GlRuntimeException {
		try {

			return userConfig.getString("resource.file.name");
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Fail to get resource name from system configration file. " + e.toString());
			throw new GlRuntimeException("Fail to get resource name from system configration file", e);
		}
	}

	/**
	 * get resource bundle
	 * 
	 * @return
	 * @throws GlRuntimeException
	 */
	public void getResourceBundle() throws GlRuntimeException {
		try {
			resourceBundle = ResourceBundle.getBundle(getBaseName() + getAppendString(typeInstance.getModuleName()), typeInstance.getLocale());
		} catch (MissingResourceException mre) {

			Logger.getLogger(getClass()).error("Fail to get Resourcebundle. " + mre.toString());
			throw new GlRuntimeException("Fail to get Resourcebundle", mre);
		} catch (NullPointerException ne) {

			Logger.getLogger(getClass()).error("Fail to get Resourcebundle. " + ne.toString());
			throw new GlRuntimeException("Fail to get Resourcebundle", ne);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error("Fail to get Resourcebundle. " + e.toString());
			throw new GlRuntimeException("Fail to get Resourcebundle", e);
		}
	}

	public String getAppendString(String str) {
		if (str == null || str.equals(""))
			return "";
		else
			return "_" + str;
	}

	public void refresh() throws GlRuntimeException {

		Class refreshClass = ResourceBundle.class;
		Field field = null;
		try {
			field = refreshClass.getDeclaredField("cacheList");
		} catch (NoSuchFieldException nsfe) {

			Logger.getLogger(getClass()).error("Fail to refresh Message ResourceBundle. " + nsfe.toString());
			throw new GlRuntimeException("Fail to refresh Message ResourceBundle", nsfe);
		}
		field.setAccessible(true);
		ConcurrentHashMap cache = null;
		try {
			cache = (ConcurrentHashMap) field.get(null);
		} catch (IllegalAccessException iae) {

			Logger.getLogger(getClass()).error("Fail to refresh Message ResourceBundle. " + iae.toString());
			throw new GlRuntimeException("Fail to refresh Message ResourceBundle", iae);
		}
		cache.clear();
		field.setAccessible(false);
	}
}