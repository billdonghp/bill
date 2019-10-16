package com.ait.web;

import java.util.HashMap;
import java.util.Map;

public class ContentFactory {
	protected static Map contents;

	protected ContentFactory instance;

	public ContentFactory() {
		contents = new HashMap();
		contents.put("error", new ErrorContent());
	}

	public static ContentFactory getInstance() {
		return new ContentFactory();
	}

	public Content getContent(String content) {
		Content con = (Content) contents.get(content);
		if (con == null) {
			con = (Content) contents.get("error");
		}
		return con;
	}

}
