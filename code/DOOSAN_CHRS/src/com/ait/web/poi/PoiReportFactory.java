package com.ait.web.poi;

import com.ait.core.exception.GlRuntimeException;

/**
 * 
 * @version 
 */
public class PoiReportFactory {
	
	
   private static DefaultPoiReportCreator defaultCreator = new DefaultPoiReportCreator();
	
   public static PoiReportCreator getReprotCreator(String creator){
	   if (creator == null){
		   return defaultCreator;
	   }
	   throw new GlRuntimeException(creator + " Not Supported");
   }
   
   public static PoiReportCreator getReprotCreator(){
	   return getReprotCreator(null);
   }
}
