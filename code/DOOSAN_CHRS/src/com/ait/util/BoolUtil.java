package com.ait.util;

public class BoolUtil {

	public static boolean parseBool(String bool) {
		return BoolUtil.parseBool(bool, false);
	}

	public static boolean parseBool(String bool, boolean defaultValue) {
		if (bool == null)
			return defaultValue;
		else if (bool.equals("1") || bool.equals("true"))
			return true;
		else
			return false;
	}

	public static Boolean parseBoolean(String bool) {
		return BoolUtil.parseBoolean(bool, false);
	}

	public static Boolean  parseBoolean(String bool, boolean defaultValue) {
		if (bool == null)
			return Boolean.valueOf(defaultValue);
		else if (bool.equals("1") || bool.equals("true"))
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}
}
