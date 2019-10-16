package com.ait.taglib;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.BeanUtils;

import com.ait.taglib.util.HtmlEncoder;
import com.ait.taglib.util.HtmlUtil;
import com.ait.util.StringUtil;


public class FormTag extends BodyTagSupport
{
    private String beanName = null;
    private String scope = null;
    private Object bean = null;
    private boolean fromRequest = false;

    /**
     * Sets bean names with value of the "bean" attribute.
     *
     * @param v bean names
     */
    public void setBean(String v)
    {
        beanName = v;
    }

    /**
     * Gets bean names.
     *
     * @return bean names
     */
    public String getBean()
    {
        return beanName;
    }

    /**
     * Sets the value of "scope" attribute, that represent beans scope.
     *
     * @param v
     */
    public void setScope(String v)
    {
        scope = v;
    }

    public void setFromRequest(boolean fromRequest)
    {
        this.fromRequest = fromRequest;
    }

    /**
     * Return value of the "scope" attribute
     *
     * @return bean scopes
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * Copies properties of all specified bean into one map.
     *
     * @return EVAL_BODY_AGAIN
     */
    public int doStartTag()
    {
        if (!fromRequest)
        {
            scope.toLowerCase();

            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            HttpSession session = (HttpSession) pageContext.getSession();

            if ((scope.length() == 0) || (scope.equals("page")))
            {
                bean = pageContext.getAttribute(beanName);
            }
            else if (scope.equals("request"))
            {
                bean = request.getAttribute(beanName);
            }
            else if (scope.equals("session"))
            {
                bean = session.getAttribute(beanName);
            }
        }
        return EVAL_BODY_AGAIN;
    }

    /**
     * Performs smart form population.
     *
     * @return SKIP_BODY
     */
    public int doAfterBody()
    {
        BodyContent body = getBodyContent();

        try
        {
            JspWriter out = body.getEnclosingWriter();
            String bodytext = body.getString();

            if (bean != null || fromRequest == true)
            {
                bodytext = populateForm(bodytext, bean);
            }

            out.print(bodytext);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return SKIP_BODY;
    }

    /**
     * End of tag.
     *
     * @return EVAL_PAGE
     */
    public int doEndTag()
    {
        return EVAL_PAGE;
    }

    private String populateForm(String html, Object bean)
    {
        int i = 0;
        int s = 0;
        
        //sadu use 锟斤拷式锟斤拷锟街凤拷
        String cell="";
        
        StringBuffer result = new StringBuffer(html.length());
        String currentSelectName = null;
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        while (true)
        {
            // find starting tag
            i = html.indexOf('<', s);

            if (i == -1)
            {
                result.append(html.substring(s));

                break; // input tag not found
            }

            result.append(html.substring(s, i)); // tag found, all before tag is stored
            s = i;

            // find closing tag
            i = html.indexOf('>', i);

            if (i == -1)
            {
                result.append(html.substring(s));

                break; // closing tag not found
            }

            i++;

            // match tags
            String tag = html.substring(s, i);
            String tagName = HtmlUtil.getTagName(tag);

            if (tagName.equalsIgnoreCase("input") == true)
            {
                String tagType = HtmlUtil.getAttribute(tag, "type");
                if (tagType != null)
                {
                    String name = HtmlUtil.getAttribute(tag, "name");
                    String value = null;
                    try
                    {
                        if (!fromRequest)
                            value = BeanUtils.getProperty(bean, name);
                        else
                            value = StringUtil.toCN(request.getParameter(name));
                    }
                    catch (Exception e)
                    {

                    }
                    if (value != null)
                    {
                        tagType = tagType.toLowerCase();

                        if (tagType.equals("text"))
                        {
                        	//锟斤拷取锟斤拷式
                        	value=doConvertTo(HtmlUtil.getAttribute(tag, "cell"),value);
                        	
                            tag = HtmlUtil.addAttribute(tag, "value", value);
                        }

                        if (tagType.equals("hidden"))
                        {
                            tag = HtmlUtil.addAttribute(tag, "value", value);
                        }

                        if (tagType.equals("image"))
                        {
                            tag = HtmlUtil.addAttribute(tag, "value", value);
                        }

                        if (tagType.equals("password"))
                        {
                            tag = HtmlUtil.addAttribute(tag, "value", value);
                        }

                        if (tagType.equals("checkbox"))
                        {
                            String tagValue = HtmlUtil.getAttribute(tag, "value");
                            String[] values = request.getParameterValues(name);
                            for(int j = 0 ; j < values.length ; j++ ){
	                            if (tagValue == null)
	                            {
	                                tagValue = "true";
	                            }
	
	                            if (tagValue.equals(values[j]))
	                            {
	                                tag = HtmlUtil.addAttribute(tag, "checked");
	                            }
                            }
                        }

                        if (tagType.equals("radio"))
                        {
                            String tagValue = HtmlUtil.getAttribute(tag, "value");

                            if (tagValue != null)
                            {
                                if (tagValue.equals(value))
                                {
                                    tag = HtmlUtil.addAttribute(tag, "checked");
                                }
                            }
                        }
                    }
                }
            }
            else if (tagName.equalsIgnoreCase("textarea") == true)
            {
                String name = HtmlUtil.getAttribute(tag, "name");
                String value = null;
                try
                {
                    if (!fromRequest)
                        value = BeanUtils.getProperty(bean, name);
                    else
                        value = request.getParameter(name);
                }
                catch (Exception e)
                {

                }
                if (value != null)
                {
                    tag += HtmlEncoder.encode(value);
                }
            }
            else if (tagName.equalsIgnoreCase("select") == true)
            {
                currentSelectName = HtmlUtil.getAttribute(tag, "name");
            }
            else if (tagName.equalsIgnoreCase("/select") == true)
            {
                currentSelectName = null;
            }
            else if (tagName.equalsIgnoreCase("option") == true)
            {
                if (currentSelectName != null)
                {
                    String tagValue = HtmlUtil.getAttribute(tag, "value");

                    String value = null;
                    try
                    {

                        if (!fromRequest)
                            value = BeanUtils.getProperty(bean, currentSelectName);
                        else
                            value = request.getParameter(currentSelectName);
                    }
                    catch (Exception e)
                    {

                    }
                    if (tagValue != null)
                    {
                        if (value != null)
                        {
                            if (value.equals(tagValue))
                            {
                                tag = HtmlUtil.addAttribute(tag,
                                        "selected");
                            }
                        }
                    }
                }
            }
            result.append(tag);
            s = i;
        }

        return result.toString();
    }

    
    
    
    private static SimpleDateFormat sdf;
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static String pattern1 = "yyyy-MM-dd";
    private SimpleDateFormat getDateFormat() {
        if (sdf == null) {
            sdf = new SimpleDateFormat(pattern);
        }
        return sdf;
    }
    private String doConvertToDate(String value) {
    	String result = null;

        if (value instanceof String) {
            try {
                result = new SimpleDateFormat(pattern1).format(getDateFormat().parse(value)).toString();
            } catch (ParseException e) {
            }
        }
        return result;
    }
    
    private String doConvertTo(String cell,String value){
    	String result = value;
    	if(cell!=null && cell.equals("date")){
    		result= doConvertToDate(value);
    		
    		
    	}
    	else if(cell!=null && cell.equals("Number")){
    		NumberFormat nf1 =NumberFormat.getInstance();
    		try{
    		result= NumberFormat.getInstance().format(nf1.parse(value));
    		}catch(ParseException e){
    			
    		}
    	}
    	return result;
    	 
    }
}
