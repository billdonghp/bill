package com.ait.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.ait.core.exception.GlRuntimeException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: AIT</p>
 * @author DevGroup-37
 * @version 1.0
 */

public class NumberUtil {
  protected NumberUtil() {
  }

  private static java.util.Random RANDOM = new java.util.Random();

  private static String checkNull(Object obj)
  {
	String temp = "" ;
	if(obj != null)
		temp = obj.toString() ;
	return temp ;
  }
  
  /**
   * parseInt
   *
   * @param Object
   * @return int
   */
  public static int parseInt(Object obj) {
    return parseInt(checkNull(obj));
  }
  /**
   * parseInt
   *
   * @param Object
   * @param int
   * @return int
   */
  public static int parseInt(Object obj, int defaultValue) {
	  return parseInt(checkNull(obj), defaultValue);
  }
  /**
   * parseInt
   *
   * @param s String
   * @return int
   */
  public static int parseInt(String s) {
    return parseInt(s, 0);
  }

  /**
   * parseInt
   *
   * @param s String
   * @param defaultValue int
   * @return int
   */
  public static int parseInt(String s, int defaultValue) {
    int rValue = defaultValue;
    try {
      rValue = Integer.parseInt(s);
    }
    catch (NumberFormatException e) {
      rValue = defaultValue;
    }
    return rValue;                      
  }
  
  /**
   * parseLong
   *
   * @param Object
   * @return long
   */
  public static double parseLong(Object obj) {
    return parseLong(checkNull(obj));
  }
  /**
   * parseLong
   *
   * @param Object
   * @param long
   * @return long
   */
  public static double parseLong(Object obj, long defaultValue) {
	  return parseLong(checkNull(obj), defaultValue);
  }

  public static long parseLong(String s) {
    return parseLong(s, 0);
  }

  public static long parseLong(String s, long defaultValue) {
    long rValue = defaultValue;
    try {
      rValue = Long.parseLong(s);
    }
    catch (NumberFormatException e) {
      rValue = defaultValue;
    }
    return rValue;
  }
  
  /**
   * parseFloat
   *
   * @param Object
   * @return float
   */
  public static double parseFloat(Object obj) {
    return parseFloat(checkNull(obj));
  }
  /**
   * parseFloat
   *
   * @param Object
   * @param float
   * @return float
   */
  public static double parseFloat(Object obj, float defaultValue) {
	  return parseFloat(checkNull(obj), defaultValue);
  }

  public static float parseFloat(String s) {
    return parseFloat(s, (float) 0.0);
  }

  public static float parseFloat(String s, float defaultValue) {
    float rValue = defaultValue;
    try {
      rValue = Float.parseFloat(s);
    }
    catch (NumberFormatException e) {
      rValue = defaultValue;
    }
    return rValue;
  }
  
  /**
   * parseDouble
   *
   * @param Object
   * @return double
   */
  public static double parseDouble(Object obj) {
    return parseDouble(checkNull(obj));
  }
  /**
   * parseDouble
   *
   * @param Object
   * @param double
   * @return double
   */
  public static double parseDouble(Object obj, double defaultValue) {
	  return parseDouble(checkNull(obj), defaultValue);
  }

  public static double parseDouble(String s) {
    return parseDouble(s, 0.0);
  }

  public static double parseDouble(String s, double defaultValue) {
		double rValue = defaultValue;
		try {
			rValue = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			rValue = defaultValue;
		} catch (NullPointerException e) {
			rValue = defaultValue;
		}
		return rValue;
	}

  public static int random(int limit) {
    return RANDOM.nextInt(limit);
  }

	public static List getDoubleSerialList(double size) {
		  List list = new ArrayList();
		  for (double i=0;i<=size;i=i+0.5)
			  list.add(String.valueOf(i));
		  return list;
	  }
	
	public static List getIntSerialList(int size) {
		  List list = new ArrayList();
		  for (int i=0;i<=size;i++)
			  list.add(String.valueOf(i));
		  return list;
	  }
	
	/**
	 * 格式化数值
	 * formatNumber(11000.985, '0.00') = 11000.98
	 * formatNumber(11000.986, '0.00') = 11000.99
	 * @param amount
	 * @param pattern
	 * @return
	 */
	public static String formatNumber(double amount, String pattern) {
		if (amount > 9999999999999.99){
		   throw new GlRuntimeException("[" + amount + "]数值过大，无法格式化！");	
		}			
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		DecimalFormat df = (DecimalFormat) nf;
		df.setDecimalSeparatorAlwaysShown(true);
		df.applyPattern(pattern);
		return df.format(amount);
	}
	
	public static String convert(double d){
		String returnValue = "";  
		int temp=(int)d;
		  if (temp==d) {
			  returnValue = String.valueOf(temp) ;
		  }else {
			  returnValue = String.valueOf(d);
		  }
		  return returnValue;
	}
	

}
