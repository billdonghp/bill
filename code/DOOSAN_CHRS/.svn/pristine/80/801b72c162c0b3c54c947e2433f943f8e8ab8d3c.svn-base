/*
 * @(#)TextUtil.java 1.0 2006-12-9 下午04:16:19
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.utils;

import java.util.ArrayList;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-9 下午04:16:19
 * @version 1.0
 * 
 */
public class TextUtil {

	 public static ArrayList getToken(String pStr, String pDelimiter) {

	        ArrayList al = new ArrayList();

	        if (pStr == null || pStr == "")
	            return al;
	        if (pDelimiter == null || pDelimiter == "")
	            return al;

	        int vStart = 0;
	        int vEnd = pStr.length();
	        boolean vFirst = false;

	        if (pStr.substring(0, pDelimiter.length()).equals(pDelimiter))
	            vFirst = true;

	        for (int i = 0; vEnd > -1; i++) {

	            if (i > 0)
	                vStart = vEnd + pDelimiter.length();

	            vEnd = pStr.indexOf(pDelimiter, vStart);

	            if (vEnd > -1) {
	                if (!vFirst) {
	                    al.add(pStr.substring(vStart, vEnd));
	                } else {
	                    vFirst = false;
	                }
	            }
	        }

	        if ((vStart + pDelimiter.length()) <= pStr.length())
	            al.add(pStr.substring(vStart, pStr.length()));

	        return al;
	    }
}

