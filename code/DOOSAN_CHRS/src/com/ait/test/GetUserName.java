package com.ait.test;


/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-7-2 下午01:37:02
 * @version 1.0
 * 
 */
public class GetUserName {
	
	
	private char[] lc_2 = new char[10];
	private char[] li_3 = new char[10];
	
	public GetUserName()
	{
		li_3[0] = 9;
		li_3[1] = 5;
		li_3[2] = 1;
		li_3[3] = 0;
		li_3[4] = 9;
		li_3[5] = 5;
		li_3[6] = 3;
		li_3[7] = 8;
		li_3[8] = 7;
		li_3[9] = 1;
	}

	public String getEncodeUserName(String newUserName) throws Exception {

		String sEncode = "";
		
		char[] charTemp = newUserName.toCharArray();

		for (int i = 0; i < charTemp.length; i++) {

			if (i == 0 || i == charTemp.length - 1) {

				charTemp[i] = (char) ((int) charTemp[i] + 1);

			} else {
				charTemp[i] = (char) ((int) charTemp[i] - li_3[i]);
			}
			lc_2[i] = charTemp[i];

		}
		sEncode = new String(lc_2);
		return sEncode.trim();
	}

	public String getDecodeUserNameErp(String userName) throws Exception {

		String sEncode = "";
		
		char[] charTemp = userName.toCharArray();
		for (int i = 0; i < charTemp.length; i++) {
			if (i == 0 || i == charTemp.length - 1) {
				if (((int) charTemp[i]) == 32) {
					charTemp[i] = (char) (43 - 1);
				} else {
					charTemp[i] = (char) ((int) charTemp[i] - 1);
				}
			} else {
				if (((int) charTemp[i]) == 32) {
					charTemp[i] = (char) (43 + li_3[i]);
				} else {
					charTemp[i] = (char) ((int) charTemp[i] + li_3[i]);
				}
			}
			lc_2[i] = charTemp[i];
		}
		sEncode = new String(lc_2);
		return sEncode.trim();
	}

	public static void main(String[] args) {
		try {
			GetUserName getUserName = new GetUserName();
			String a = getUserName.getEncodeUserName("60000000");
			String b = getUserName.getEncodeUserName("ic0803969");
			// b = 83/0' -1
			// a = j^/7*-./5
			System.out.println(a);
			System.out.println(b);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}