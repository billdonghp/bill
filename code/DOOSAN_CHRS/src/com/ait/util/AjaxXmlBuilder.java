/*
 * @(#)AjaxXmlBuilder.java 1.0 2006-12-21 下午09:27:44
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2006-12-21 下午09:27:44
 * @version 1.0
 * 
 */
public class AjaxXmlBuilder {

    	 private String encoding = "UTF-8";
    	  private List items = new ArrayList();

    	  public String getEncoding() {
    	    return encoding;
    	  }

    	  public void setEncoding(String encoding) {
    	    this.encoding = encoding;
    	  }

    	  /**
    	   * Add item to XML.
    	   *
    	   * @param name The name of the item
    	   * @param value The value of the item
    	   * @return
    	   */
    	  public AjaxXmlBuilder addItem(String name, String value) {
    	    items.add(new Item(name, value, false));
    	    return this;
    	  }

    	  /**
    	   * Add item wrapped with inside a CDATA element.
    	   *
    	   * @param name The name of the item
    	   * @param value The value of the item
    	   * @return
    	   */
    	  public AjaxXmlBuilder addItemAsCData(String name, String value) {
    	    items.add(new Item(name, value, true));
    	    return this;
    	  }

    	  /**
    	   * Add items from a collection.
    	   *
    	   * @param collection
    	   * @param nameProperty
    	   * @param valueProperty
    	   * @return
    	   * @throws IllegalAccessException
    	   * @throws InvocationTargetException
    	   * @throws NoSuchMethodException
    	   */
    	  public AjaxXmlBuilder addItems(Collection collection, String nameProperty, String valueProperty)
    	      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	    return addItems(collection, nameProperty, valueProperty, false);
    	  }

    	  /**
    	   * Add items from a collection.
    	   *
    	   * @param collection
    	   * @param nameProperty
    	   * @param valueProperty
    	   * @return
    	   * @throws IllegalAccessException
    	   * @throws InvocationTargetException
    	   * @throws NoSuchMethodException
    	   */
    	  public AjaxXmlBuilder addItems(
    	      Collection collection, String nameProperty, String valueProperty, boolean asCData)
    	      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	    for (Iterator iter = collection.iterator(); iter.hasNext();) {
    	      Object element = (Object) iter.next();
    	      String name = BeanUtils.getProperty(element, nameProperty);
    	      String value = BeanUtils.getProperty(element, valueProperty);
    	      if (asCData) {
    	        items.add(new Item(name, value, false));
    	      } else {
    	        items.add(new Item(name, value, true));

    	      }
    	    }
    	    return this;
    	  }

    	  /**
    	   * Add items from a collection as CDATA element.
    	   *
    	   * @param collection
    	   * @param nameProperty
    	   * @param valueProperty
    	   * @return
    	   * @throws IllegalAccessException
    	   * @throws InvocationTargetException
    	   * @throws NoSuchMethodException
    	   */
    	  public AjaxXmlBuilder addItemsAsCData(
    	      Collection collection, String nameProperty, String valueProperty)
    	      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    	    return addItems(collection, nameProperty, valueProperty, true);
    	  }

    	  /**
    	   * @see java.lang.Object#toString()
    	   */
    	  public String toString() {
    	    StringBuffer xml = new StringBuffer().append("<?xml version=\"1.0\"");
    	    if (encoding != null) {
    	      xml.append(" encoding=\"");
    	      xml.append(encoding);
    	      xml.append("\"");
    	    }
    	    xml.append(" ?>");

    	    xml.append("<ajax-response>");
    	    xml.append("<response>");
    	    for (Iterator iter = items.iterator(); iter.hasNext();) {
    	      Item item = (Item) iter.next();
    	      xml.append("<item>");
    	      xml.append("<name>");
    	      if (item.isAsCData()) {
    	        xml.append("<![CDATA[");
    	      }
    	      xml.append(item.getName());
    	      if (item.isAsCData()) {
    	        xml.append("]]>");
    	      }
    	      xml.append("</name>");
    	      xml.append("<value>");
    	      if (item.isAsCData()) {
    	        xml.append("<![CDATA[");
    	      }
    	      xml.append(item.getValue());
    	      if (item.isAsCData()) {
    	        xml.append("]]>");
    	      }
    	      xml.append("</value>");
    	      xml.append("</item>");
    	    }
    	    xml.append("</response>");
    	    xml.append("</ajax-response>");

    	    return xml.toString();

    	  }

    	}

    	/**
    	 * A generic item class, basically representing a name-value pair.
    	 *
    	 * @author Darren Spurgoen
    	 * @version $Revision: 1.1 $ $Date: 2009/05/15 08:43:49 $
    	 */
    	class Item {

    	  private String name;
    	  private String value;
    	  private boolean asData;

    	  /**
    	   * Constructor for Item.
    	   */
    	  public Item() {
    	    super();
    	  }

    	  /**
    	   * Constructor for Item.
    	   *
    	   * @param name
    	   * @param value
    	   */
    	  public Item(String name, String value, boolean asData) {
    	    super();
    	    this.name = name;
    	    this.value = value;
    	    this.asData = asData;
    	  }

    	  /**
    	   * @return Returns the name.
    	   */
    	  public String getName() {
    	    return this.name;
    	  }

    	  /**
    	   * @param name The name to set.
    	   */
    	  public void setName(String name) {
    	    this.name = name;
    	  }

    	  /**
    	   * @return Returns the value.
    	   */
    	  public String getValue() {
    	    return this.value;
    	  }

    	  /**
    	   * @param value The value to set.
    	   */
    	  public void setValue(String value) {
    	    this.value = value;
    	  }

    	  /**
    	   * @return Returns the asCData.
    	   */
    	  public boolean isAsCData() {
    	    return this.asData;
    	  }

    	  /**
    	   * @param asData The asData to set.
    	   */
    	  public void setAsData(boolean asData) {
    	    this.asData = asData;
    	  }

    	  /**
    	   * @see java.lang.Object#toString()
    	   */
    	  public String toString() {
    	    return new ToStringBuilder(this).append("name", name).append("value", value).append(
    	        "asData", asData).toString();
    	  }

}

