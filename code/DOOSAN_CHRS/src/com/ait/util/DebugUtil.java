package com.ait.util;

public class DebugUtil {
	private static boolean printable = true;

	public DebugUtil() {
	}

	public static void print(String s) {
		if (printable)
			System.out.println(s);
	}

	public static void printStackTrace(Exception e) {
		if (printable)
			e.printStackTrace();
	}
}
