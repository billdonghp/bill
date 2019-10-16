package com.ait.util;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.regexp.RE;

/**
 * <p>
 * Title: String utility
 * </p>
 * 
 * <p>
 * Description: do some string operation
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * 
 * <p>
 * Company: AIT
 * </p>
 * 
 * @author Chengwei
 * @version 1.0
 */

public class StringUtil {
	private static String CHARSET_CN = "GBK";
	
	private static String CHARSET_EN = "UTF-8";

	private static String CHARSET_ISO = "ISO-8859-1";

	protected StringUtil() {
	}

	public static String checkNull(Object s) {
		return checkNull(s, "");
	}

	public static String checkNull(Object s, String defaultValue) {
		if (s == null) {
			return defaultValue;
		} else {
			return s.toString().trim();
		}
	}

	/**
	 * 将日期转为字符串
	 * 
	 * @param date
	 *            传入的日期参数
	 * @return 格式为2006-08-03的字符串
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 将日期按指定格式转为字符串
	 * 
	 * @param date
	 *            传入的日期参数
	 * @param format
	 *            指定的格式
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null)
			return "";
		else
			return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 将日期按指定格式转为字符串
	 * 
	 * @param date
	 *            传入的日期参数
	 * @param format
	 *            指定的格式
	 * @return
	 */
	public static String formatDate(Date date, Time time, String format) {
		String[] formats = format.split(" ");
		if (date == null)
			return "";
		else
			return new SimpleDateFormat(formats[0]).format(date) + " "
					+ new SimpleDateFormat(formats[1]).format(time);
	}
	
	/**
	 * 返回指定数量的字符串
	 * 
	 * @return
	 */
	public static String getString(int number, String stringExample) {
		StringBuffer stringBuffer = new StringBuffer(number) ;
		for (int i = 1 ; i <= number ; ++ i){
			stringBuffer.append(stringExample) ;
		}
		
		return stringBuffer.toString() ;
	}


	/**
	 * replace: this method replace the old string by the new string if and only
	 * if the old string is contained in the orgString. In that case, the old
	 * string part in the orgString will be replaced by the new string ,
	 * otherwise, the orgString will be returned without any change.
	 * 
	 * @param orgString
	 *            String
	 * @param oldString
	 *            String
	 * @param newString
	 *            String
	 * @return String
	 */
	public static String replace(String orgString, String oldString,
			String newString) {
		if (orgString == null) {
			return "";
		}
		int s = 0;
		int e = 0;
		int oldLen = oldString.length();
		StringBuffer result = new StringBuffer((int) (orgString.length() * 1.2));
		while ((e = orgString.indexOf(oldString, s)) >= 0) {
			result.append(orgString.substring(s, e));
			result.append(newString);
			s = e + oldLen;
		}
		result.append(orgString.substring(s));
		return result.toString();
	}

	public static String regExpReplace(String s, String reg, String to) {
		RE re = new RE(reg);
		while (re.match(s)) {
			s = s.replaceAll(re.getParen(0), to);
		}
		return s;
	}

	public static Hashtable toHash(String s) {
		Hashtable hash = new Hashtable();
		s = regExpReplace(StringUtil.toCN(s), "\n", ",");
		String[] b = null;
		String[] a = s.split(",");
		if (a.length > 0) {
			for (int i = 0; i < a.length; i++) {
				b = regExpReplace(a[i], "	", ",").split(",");
				if (b.length > 1) {
					hash.put(b[0], b[1]);
				}
			}
		}
		return hash;
	}

	public static String toCN(String s) {
		return s;
	}

	public static String toCN(String s, String r) {
//		try {
//			return new String(checkNull(s, r).getBytes(CHARSET_ISO), CHARSET_CN);
//		} catch (UnsupportedEncodingException ex) {
//			return r;
//		}
		return checkNull(s, r);
	}

	public static String[] toCN(String[] s) {
		if (s == null) {
			return null;
		}
		String[] result = null;
		for (int i = 0; i < s.length; i++) {
			result[i] = toCN(checkNull(s[i]));
		}
		return result;
	}

	public static String editNbsp(String s) {
		if (checkNull(s).equals("") || s.equals("null &gt; ")) {
			return "&nbsp;";
		}
		return s;
	}

	public static String toISO(String s) {
		return toISO(s, "");
	}

	public static String toISO(String s, String r) {
		try {
			return new String(checkNull(s, r).getBytes(CHARSET_CN), CHARSET_ISO);
		} catch (UnsupportedEncodingException ex) {
			return r;
		}
	}

	/**
	 * 把字符串转换成Unicode码
	 * 
	 * @param strText
	 *            待转换的字符串
	 * @param code
	 *            转换前字符串的编码，如"GBK"
	 * @return 转换后的Unicode码字符串
	 */
	public static String toUnicode(String strText, String code)
			throws UnsupportedEncodingException {
		char c;
		String strRet = "";
		int intAsc;
		String strHex;
		strText = new String(strText.getBytes("8859_1"), code);
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			if (intAsc > 128) {
				strHex = Integer.toHexString(intAsc);
				strRet = strRet + "&#x" + strHex + ";";
			} else {
				strRet = strRet + c;
			}
		}
		return strRet;
	}

	public static String substring(String s, int len) {
		return substring(s, len, "");
	}

	public static String substring(String s, int len, String appendString) {
		s = checkNull(s, "");
		if (s.length() > len) {
			return s.substring(0, len) + appendString;
		} else {
			return s;
		}
	}

	public static String parseDate(String date) {
		String result = date;
		date = date.trim();
		if (date.indexOf("/") >= 0) {
			result = replace(date, "/", "-");
		}
		if (date.indexOf(".") >= 0) {
			result = replace(date, ".", "-");
		}
		if (result.equals(""))
			return result;

		if (result.indexOf("-") == -1) {
			result = result + "-01-01";
		} else {
			if (result.lastIndexOf("-") == result.indexOf("-"))
				result += "-01";
		}

		int firstDash = result.indexOf("-");
		int lastDash = result.lastIndexOf("-");
		String month = result.substring(firstDash + 1, lastDash);
		String day = result.substring(lastDash + 1);
		String year = result.substring(0, firstDash);
		if (month.length() < 2)
			month = "0" + month;
		if (day.length() < 2)
			day = "0" + day;
		result = year + "-" + month + "-" + day;

		return result;
	}

	/**
	 * Checks if String contains a search String
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
	}

	/**
	 * <p>Checks if a String is whitespace, empty ("") or null.</p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param str  the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since common-lang
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String parseDeptID(String deptID) {
		String v_deptid = "";
		v_deptid = deptID;
		//        if(v_deptid.endsWith("0")){
		//                v_deptid = v_deptid.substring(0,v_deptid.length()-1);
		//        }
		//        if(v_deptid.endsWith("0")){
		//               v_deptid = parseDeptID(v_deptid);
		//        }
		//        else v_deptid +="%";
		return v_deptid;
	}

	/**
	 * Finds first index of a substring in the given source string with ignored case.
	 *
	 * @param src  source string for examination
	 * @param subS substring to find
	 * @return index of founded substring or -1 if substring is not found
	 * @see #indexOfIgnoreCase(String, String, int)
	 */
	public static int indexOfIgnoreCase(String src, String subS) {
		return indexOfIgnoreCase(src, subS, 0);
	}

	/**
	 * Finds first index of a substring in the given source string with ignored
	 * case. This seems to be the fastest way doing this, with common string
	 * length and content (of course, with no use of Boyer-Mayer type of
	 * algorithms). Other implementations are slower: getting char array frist,
	 * lowercasing the source string, using String.regionMatch etc.
	 *
	 * @param src        source string for examination
	 * @param subS       substring to find
	 * @param startIndex starting index from where search begins
	 * @return index of founded substring or -1 if substring is not found
	 */
	public static int indexOfIgnoreCase(String src, String subS, int startIndex) {
		String sub = subS.toLowerCase();
		int sublen = sub.length();
		int total = src.length() - sublen + 1;

		for (int i = startIndex; i < total; i++) {
			int j = 0;

			while (j < sublen) {
				char source = Character.toLowerCase(src.charAt(i + j));

				if (sub.charAt(j) != source) {
					break;
				}

				j++;
			}

			if (j == sublen) {
				return i;
			}
		}

		return -1;
	}
	public static final String formatColumnName(String columnName) {
		String columnNameNew = "" ;
		if (columnName == null) {
			return "";
		} else {
			if (columnName.length() > 4){
				columnNameNew = columnName.substring(0, 4) + "</br>" + columnName.substring(4) ; 
			}
			else{
				columnNameNew = columnName ;
			}
			
			return columnNameNew ;
		}
	}
}
