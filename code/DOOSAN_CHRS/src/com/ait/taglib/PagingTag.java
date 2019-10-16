/*
 * @(#)PagingTag.java 1.0 2006-12-11 下午12:10:20
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.taglib;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-11 下午12:10:20
 * @version 1.0
 * 
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

import com.ait.utils.TextUtil;
import com.ait.util.NumberUtil;

public class PagingTag extends TagSupport {

	String total;

	String currentPage;

	int pageTotal;

	String pageSize;

	String pageGroupSize;

	int pageGroupStart;

	int pageGroupEnd;

	String link;

	String parameters;

	String beginLabel;

	String endLabel;

	String prevLabel;

	String nextLabel;

	boolean useJS;

	boolean usePageGo = false;

	private final String changeState = new StringBuffer(
			"<script language='JavaScript'>").append(
			"function changeNumber(pos, num, flag){").append(
			"    obj = eval(pos);").append(
			"   if(flag) obj.innerHTML='['+num+']';").append(
			"   else obj.innerHTML= '&nbsp;' + num + '&nbsp'").append(
			"}</script><br>").toString();

	private final String pageNavigateStart = new StringBuffer(
			"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">")
			.append("  <tr> ").append(
					"    <td height=\"10\" colspan=\"3\"></td>").append(
					"  </tr>").append("  <tr> ").append(
					"    <td width=\"150\">").toString();

	private final String pageNavigateEnd = new StringBuffer("		   </td>")
			.append("		<td width=\"150\" align=\"right\">&nbsp;</td>").append(
					"	  </tr>").append("	</table>").toString();

	private final String total2BeginLabel = new StringBuffer("</td>").append(
			"<td align=\"center\">").append(
			"  <table cellpadding=\"0\" cellspacing=\"0\">").append("	<tr> ")
			.append("	  <td width=\"40\">").toString();

	private final String preLabel2MidLine = new StringBuffer("	</td>").append(
			"	<td align=\"center\" nowrap>").append(
			"	  <table cellpadding=\"0\" cellspacing=\"0\">").append("		<tr> ")
			.toString();

	private final String midLine = new StringBuffer("<td width=\"1\">")
			.append(
					"<img src=\"/images/paging_tag/mid_line.gif\" width=\"1\" height=\"8\">")
			.append("</td>").toString();

	private final String midLine2NextLabel = new StringBuffer("  </tr>")
			.append("	</table>").append("</td>").append("<td width=\"40\">")
			.toString();

	public int doEndTag() throws JspException {
		try {
			total = eval("total", total, Object.class).toString();
			if (total == null || total.equals("0") || total.equals("")) {
				total = "0";
			}

			if (parameters != null) {
				parameters = eval("parameters", parameters, Object.class)
						.toString();

				parameters = encodeParameters(parameters);
			}

			currentPage = (String) eval("currentpage", currentPage,
					Object.class);
			if (currentPage == null || currentPage.equals("0")
					|| currentPage.equals("")) {
				currentPage = "0";
			}

			pageSize = (String) eval("pagesize", pageSize, Object.class);
			if (pageSize == null || NumberUtil.parseInt(pageSize) == 0) {
				pageSize = "10";
			}

			link = (String) eval("link", link, Object.class);
			if (link == null) {
				link = "10";
			}

			pageGroupSize = (String) eval("pageGroupSize", pageGroupSize,
					Object.class).toString();
			if (pageGroupSize == null || NumberUtil.parseInt(pageGroupSize) == 0) {
				pageGroupSize = "10";
			}

			JspWriter out = pageContext.getOut();
			out.print(changeState);
			StringBuffer sbuf = new StringBuffer();
			pageTotal = (NumberUtil.parseInt(total) - 1)
					/ NumberUtil.parseInt(pageSize);

			pageGroupStart = (int) (NumberUtil.parseInt(currentPage) / NumberUtil
					.parseInt(pageGroupSize))
					* NumberUtil.parseInt(pageGroupSize);
			pageGroupEnd = pageGroupStart + NumberUtil.parseInt(pageGroupSize);
			if (pageGroupEnd > pageTotal) {
				pageGroupEnd = pageTotal + 1;
			}

			boolean hasPreviousPage = NumberUtil.parseInt(currentPage)
					- NumberUtil.parseInt(pageGroupSize) >= 0;
			boolean hasNextPage = pageGroupStart
					+ NumberUtil.parseInt(pageGroupSize) < pageTotal + 1;

			sbuf.append(pageNavigateStart);
			sbuf.append(makeTotal());

			sbuf.append(total2BeginLabel);

			if (NumberUtil.parseInt(currentPage) > 0) {
				sbuf.append(makeLinkImg(0, beginLabel));
				// sbuf.append("&nbsp;");
			}

			if (hasPreviousPage) {
				sbuf.append(makeLinkImg(pageGroupStart - 1, prevLabel));
				// sbuf.append("&nbsp;");
			}

			sbuf.append(preLabel2MidLine);
			sbuf.append(midLine);

			for (int i = pageGroupStart; i < pageGroupEnd; i++) {
				if (i == NumberUtil.parseInt(currentPage)) {
					sbuf.append("<td class='pagenumber_selected'>").append(
							i + 1).append("</td>");
				} else {
					sbuf.append(makeLink2(i));
				}
				// sbuf.append("&nbsp;");
				sbuf.append(midLine);
			}

			sbuf.append(midLine2NextLabel);

			if (hasNextPage) {
				// sbuf.append("&nbsp;");
				sbuf.append(makeLinkImg(pageGroupEnd, nextLabel));
			}

			if (NumberUtil.parseInt(currentPage) != pageTotal) {
				// sbuf.append("&nbsp;");
				sbuf.append(makeLinkImg(pageTotal, endLabel));
			}

			if (usePageGo)
				sbuf.append(makePageGo());
			else
				sbuf.append("</td></tr></table></td></tr></table>");

			out.print(sbuf);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * encode parameters
	 * 
	 * @param parameters2
	 */
	private String encodeParameters(String params) {
		List tokens = TextUtil.getToken(params, "&");
		Iterator i = tokens.iterator();

		StringBuffer b = new StringBuffer();
		while (i.hasNext()) {
			b.append("&");
			try {
				String param = i.next().toString();
				List tokens2 = TextUtil.getToken(param, "=");
				Iterator i2 = tokens2.iterator();

				// key
				if (i2.hasNext()) {
					b.append(URLEncoder.encode(i2.next().toString(), "UTF-8"));
				}
				b.append("=");
				// value
				if (i2.hasNext()) {
					b.append(URLEncoder.encode(i2.next().toString(), "UTF-8"));
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return b.toString();

	}

	private Object eval(String attName, String attValue, Class clazz)
			throws JspException {
		Object obj = ExpressionEvaluatorManager.evaluate(attName, attValue,
				clazz, this, pageContext);
		if (obj == null) {
			throw new NullAttributeException(attName, attValue);
		} else {
			return obj;
		}
	}

	public String selectType(int page) {
		StringBuffer tmp = new StringBuffer();
		return useJS ? tmp.append(link).append("(").append(page).append(")")
				.toString() : tmp.append(link).append("?currentPage=").append(
				page).append(parameters).toString();
	}

	public String makeLink(int page, String label) {
		StringBuffer tmp = new StringBuffer("<a href=\"").append(
				selectType(page)).append(
				" style='text-decoration:none;color:#000000' ").append("\">")
				.append(label).append("</a>");
		return tmp.toString();
	}

	public String makeLink2(int page) {
		StringBuffer tmp = new StringBuffer();
		tmp
				.append("<a href=\"")
				.append(selectType(page))
				.append("\" id ='a")
				.append(page + 1)
				.append(
						"' style='text-decoration:none;color:#000000' ><td onmouseover=this.style.backgroundColor='#F7F7F7' ")
				.append(
						"onmouseout=this.style.backgroundColor='FFFFFF' "
								+ "class='pagenumber'>").append(page + 1)
				.append("</td></a>");
		return tmp.toString();
	}

	public String makeLinkImg(int page, String label) {
		StringBuffer tmp = new StringBuffer();

		tmp.append("<a href=\"").append(selectType(page)).append("\" id ='a")
				.append(page + 1).append("' ><img src='/images/paging_tag/")
				.append(label).append(".gif'").append(
						" width='19' height='9' border='0'></a>");
		return tmp.toString();
	}

	public String makeTotal() {

		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		StringBuffer tmp = new StringBuffer();
		tmp
				.append(
						"<table width='100%' border='0' cellpadding='0' cellspacing='0'>")
				.append("<tr><td class='font_small'>").append("Total").append(
						" ").append(total).append(" (").append(
						NumberUtil.parseInt(currentPage) + 1).append("/").append(
						pageTotal + 1).append(")</td></tr></table>");
		return tmp.toString();
	}

	public String makePageGo() {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		StringBuffer tmp = new StringBuffer();
		StringBuffer linkTmp = new StringBuffer();
		linkTmp.append(link).append("?currentPage=");
		tmp
				.append("</td></tr></table><td align=right>"
						+ "Go:"
						+ "<input type=\"text\" maxlength=\"4\" class=\"input_textfield\" "
						+ " onkeydown=\"javascript:if (event.keyCode == 13){ gogogo(this.value)} \"  style=\"width:30px;text-align:center\"/> "
						+ " </td><script>	" + "	function gogogo(page) { "
						+ "		if (isNaN(page)) return ;"
						+ "		if (parseInt(page,10)<1) page=1;"
						+ "       if (parseInt(page,10)>" + (pageTotal + 1)
						+ ") page=" + (pageTotal + 1) + ";" + "		var url =\""
						+ linkTmp.toString() + "\"+ (parseInt(page,10)-1) +\""
						+ parameters + "\";" + "		location.href = url ;" + ""
						+ "" + "}" + "</script>");

		return tmp.toString();
	}

	public void setTotal(String value) {
		total = value;
	}

	public void setCurrentpage(String value) {
		currentPage = value;
	}

	public void setPagesize(String value) {
		pageSize = value;
	}

	public void setLink(String value) {
		link = value;
	}

	public void setPrevlabel(String value) {
		prevLabel = value;
	}

	public void setNextlabel(String value) {
		nextLabel = value;
	}

	public void setBeginlabel(String value) {
		beginLabel = value;
	}

	public void setEndlabel(String value) {
		endLabel = value;
	}

	public void setPagegroupsize(String value) {
		pageGroupSize = value;
	}

	public String checkNull(String input, String output) {
		if (input == null) {
			input = output;
		}
		return input;
	}

	/**
	 * Sets the useJS.
	 * 
	 * @param useJS
	 *            The useJS to set
	 */
	public void setUseJS(boolean useJS) {
		this.useJS = useJS;
	}

	/**
	 * @param parameters
	 *            The parameters to set.
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	/**
	 * Sets the usePageGo.
	 * 
	 * @param useJS
	 *            The useJS to set
	 */
	public void setUsePageGo(boolean usePageGo) {
		this.usePageGo = usePageGo;
	}
}
