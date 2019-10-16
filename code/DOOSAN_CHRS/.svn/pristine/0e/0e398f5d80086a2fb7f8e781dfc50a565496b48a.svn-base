package com.ait.util;

import java.lang.reflect.Field;

public class BeanUtil {

    /**
     * 对比两JAVA BEAN 并对不同的字段高亮显示
     * 
     * @param nObject
     * @param oObject
     * @param className
     * @return
     */
    public Object compareObject(Object nObject, Object oObject) {

        Field[] nFields = nObject.getClass().getDeclaredFields();
        Field[] oFields = oObject.getClass().getDeclaredFields();
        Object o = new Object();
        for (int i = 0; i < nFields.length; i++) {
            Field nField = nFields[i];
            nField.setAccessible(true);
            Class nType = nField.getType();
            String nValue = getFieldValue(nField, nType, nObject);
            
            for (int j = 0; j < oFields.length; j++) {

                Field oField = oFields[i];
                oField.setAccessible(true);
                Class oType = oField.getType();
                String oValue = getFieldValue(oField, oType, oObject);
                
                
                
                if (nField.equals(oField)) {
                        
                        if (!(StringUtil.checkNull(nValue).trim()).equals(StringUtil.checkNull(oValue).trim())) {
                            try {
                                if (!"".equals(StringUtil.checkNull(nValue))) {
                                    if (nType == String.class) {
                                        nField.set(nObject,"<font color=\"red\">"+ nValue+ "</font>");
                                    }
                                } else {
                                    if (nType == String.class) {
                                        nField.set(nObject,"<font color=\"red\">null</font>");
                                    }
                                }
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                }
            }
        }
        o = nObject;
        return o;

    }

    public Object compareObject(Object nObject, Object oObject, Class className) {

        Field[] nFields = nObject.getClass().getDeclaredFields();
        Field[] oFields = oObject.getClass().getDeclaredFields();

        for (int i = 0; i < nFields.length; i++) {
            Field nField = nFields[i];
            nField.setAccessible(true);
            Class nType = nField.getType();
            String nValue = getFieldValue(nField, nType, nObject);

            for (int j = 0; j < oFields.length; j++) {

                Field oField = oFields[i];
                oField.setAccessible(true);
                Class oType = oField.getType();
                String oValue = getFieldValue(oField, oType, oObject);

                if (nField.equals(oField)) {
                    if (nType == String.class) {
                        if (!(StringUtil.checkNull(nValue)).equals(StringUtil.checkNull(oValue))) {

                            try {
                                if (!"".equals(StringUtil.checkNull(nValue))) {
                                    if (nType == String.class) {
                                         nField.set(nObject,"<font color=\"red\">"+nValue+"</font>");
                                    }

                                } else {
                                    if (nType == String.class) {
                                         nField.set(nObject,"<font color=\"red\">null</font>");
                                    }

                                }
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }catch (IllegalAccessException e) {
                             e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return nObject;

    }

    private String getFieldValue(Field field, Class fieldTypeClass, Object obj) {
        String value = null;
        try {
            if (fieldTypeClass == String.class) {
                value = (String) field.get(obj);
            } else if (fieldTypeClass == int.class) {
                value = Integer.toString(field.getInt(obj));
            } else if (fieldTypeClass == long.class) {
                value = Long.toString(field.getLong(obj));
            } else if (fieldTypeClass == short.class) {
                value = Short.toString(field.getShort(obj));
            } else if (fieldTypeClass == float.class) {
                value = Float.toString(field.getFloat(obj));
            } else if (fieldTypeClass == double.class) {
                value = Double.toString(field.getDouble(obj));
            } else if (fieldTypeClass == byte.class) {
                value = Byte.toString(field.getByte(obj));
            } else if (fieldTypeClass == char.class) {
                value = Character.toString(field.getChar(obj));
            } else if (fieldTypeClass == boolean.class) {
                value = Boolean.toString(field.getBoolean(obj));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            value = null;
        }
        return value;
    }
}
