package com.ait.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.ait.sqlmap.util.SimpleMap;
/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author yangxiaohui (yangxiaohui@ait.net.cn)
 * @Date 2009-8-5
 * 
 */
public class GetLoginCodeType {
	
	public static List  getLoginCodeType(List loginCodeType,String menuCode){
		SimpleMap codeMap = new SimpleMap();
		List result= new ArrayList();		
		for(int i=0;i<loginCodeType.size();i++){
			codeMap=(SimpleMap)loginCodeType.get(i);
			Set dataSet=codeMap.keySet();			
			Iterator dataIterator =dataSet.iterator();
			while(dataIterator.hasNext()) {
				String Key=dataIterator.next().toString();
				if(Key.equals(menuCode)){
					result=(List) codeMap.get(Key);
				}				
			} 			
		}	
		return result;
	}

}
