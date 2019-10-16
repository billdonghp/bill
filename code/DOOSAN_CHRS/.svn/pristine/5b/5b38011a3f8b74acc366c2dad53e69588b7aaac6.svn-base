package com.ait.core.channel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.core.exception.GlRuntimeException;

public class CommandEngine {

	private CommandEngine() {
		super();
	}
	/**
	 * 执行cmd，todo：package.class.method
	 * @param req HttpServletRequest
	 * @param rep HttpServletResponse 
	 * @param todo String
	 */
	public static String executeCommand(HttpServletRequest req,
			HttpServletResponse res,String todo){
		int index = todo.lastIndexOf(".");
		if (index > 0){
			String cmdName = todo.substring(1,index-1);
			String mhdName = todo.substring(index+1,todo.length());
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			try{
				Class clazz = classLoader.loadClass(cmdName);
				Object cmdObj = clazz.newInstance();
				Method mhd = clazz.getMethod(mhdName,new Class[]{req.getClass(),res.getClass()});
				return (String)mhd.invoke(cmdObj,new Object[]{req,res});
			}
			catch(ClassNotFoundException cnfex){
				throw new GlRuntimeException("非法todo参数" + todo + "，正确的格式package.class.method",cnfex);
			}
			catch(IllegalAccessException iacex){
				throw new GlRuntimeException("非法todo参数" + todo + "，正确的格式package.class.method",iacex);
			}
			catch(NoSuchMethodException nsmex){
				throw new GlRuntimeException("非法todo参数" + todo + "，正确的格式package.class.method",nsmex);
			}
			catch(IllegalArgumentException iagex){
				throw new GlRuntimeException("非法todo参数" + todo + "，正确的格式package.class.method",iagex);
			}
			catch(InvocationTargetException itgex){
				throw new GlRuntimeException("非法todo参数" + todo + "，正确的格式package.class.method",itgex);
			}
			catch(InstantiationException iex){
				throw new GlRuntimeException("非法todo参数" + todo + "，正确的格式package.class.method",iex);
			}
			catch(GlRuntimeException ex){
				throw ex;
			}
			catch(Exception ex){
				throw new GlRuntimeException("未知异常",ex);
			}
		}
		else
			throw new GlRuntimeException("非法todo参数" + todo + "，正确的格式package.class.method。");
	}
	
}
