package com.ait.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;

public class ImmitPoxy
{

	public void Immit(ResultSet resultset, Object bean, Vector vector) throws Exception{
		 //System.out.println("ImmitPoxy.Immit() is ok");
		 String col_name=null;
		 String class_name=null;
		 ResultSetMetaData resultsetmetadata = resultset.getMetaData();
		 HashMap properties = new HashMap();
		 
		 class_name=bean.toString().substring(6);

		 while (resultset.next()) {
			for (int i = 1; i <= resultsetmetadata.getColumnCount(); i++) {
				col_name = resultsetmetadata.getColumnName(i).toLowerCase();
				properties.put(col_name, resultset.getString(col_name));
				}
			Object o = Class.forName(class_name).newInstance();
			BeanUtils.populate(o, properties);
			vector.add(o);
			properties.clear();
		}
	}



}
