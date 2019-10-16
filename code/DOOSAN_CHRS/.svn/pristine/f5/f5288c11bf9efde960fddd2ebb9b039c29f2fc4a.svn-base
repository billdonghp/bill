package com.ait.core.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ait.core.exception.GlRuntimeException;

public class ResultSet extends NamedResultSet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -718921030358485000L;

	public ResultSet(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public ResultSet(int initialCapacity) {
		super(initialCapacity);
	}

	public ResultSet() {
		super();
	}

	public ResultSet(Map m) {
		super(m);
	}

	public ResultSet(
		int initialCapacity,
		float loadFactor,
		boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}
	
	public void setObject(Object key, Object value){
		super.put(key,value);
	}
	
	public Object getObject(Object key){
		Object o = super.get(key);
		if (o == null && getNullToInitialize())
			return "";
		else
			return o;
	}
	
	public void setInt(Object key, int value){
		super.put(key,new Integer(value));
	}
	
	/**
	 * 根据key取得int值,对于Short Byte String会自动转化为int
	 * @param key Object
	 * @return int 
	 */
	public int getInt(Object key){
		Object o = super.get(key);
		if (o == null){
			if (getNullToInitialize())
				return 0;
			else
				throw new GlRuntimeException(" Key(" + key +") does not exist in " + getName() + " ResultSet or Key(" + key +")'s value is null." );
		}
		else{
			if (o instanceof Integer){
				return ((Integer)o).intValue();
			}
			else if (o instanceof Short){
				return ((Short)o).intValue();
			}
			else if (o instanceof Byte){
				return ((Byte)o).intValue();
			}
			else if (o instanceof String){
				try{
					return Integer.parseInt(o.toString());
				}
				catch(Exception ex){
					throw new GlRuntimeException("Value Type(int) does not match : It's type is not int.",ex);
				}
			}
			else{
				throw new GlRuntimeException("Value Type(int) does not match : It's type is not int.");
			}
		}
	}
	
	public void setString(Object key, String value){
		super.put(key,value);
	}
	
	public String getString(Object key){
		Object o = super.get(key);
		if (o == null){
			if (getNullToInitialize())
				return "";
			else
				return null;
		}
		else
			return o.toString();
	}
	
	public void setDouble(Object key, double value){
		super.put(key,new Double(value));
	}
	
	public double getDouble(Object key){
		Object o = super.get(key);
		if (o == null){
			if (getNullToInitialize())
				return 0;
			else
				throw new GlRuntimeException("Key(" + key +") does not exist in " + getName() + " ResultSet or Key(" + key +")'s value is null.");
		}
		else{
			if (o instanceof Double){
				return ((Double)o).doubleValue();
			}
			else if (o instanceof Float){
				return ((Float)o).doubleValue();
			}
			else if (o instanceof String){
				try{
					return Double.parseDouble(o.toString());
				}
				catch(Exception ex){
					throw new GlRuntimeException("Value Type(double) does not match : It's type is not double.");
				}
			}
			else
				throw new GlRuntimeException("Value Type(double) does not match : It's type is not double.");
		}
	}
	
	public void setFloat(Object key, float value){
		super.put(key,new Float(value));
	}
	
	public float getFloat(Object key){
		Object o = super.get(key);
		if (o == null){
			if (getNullToInitialize()){
				return 0;
			}
			else
				throw new GlRuntimeException("Key(" + key +") does not exist in " + getName() + " ResultSet or Key(" + key +")'s value is null.");
		}
		else{
			if (o instanceof Float){
				return ((Float)o).floatValue();
			}
			else if (o instanceof String){
				try{
					return Float.parseFloat(o.toString());
				}
				catch(Exception ex){
					throw new GlRuntimeException("Value Type(float) does not match : It's type is not float.");
				}
			}
			else
				throw new GlRuntimeException("Value Type(float) does not match : It's type is not float.");
		}
	}
	
	public void setLong(Object key, long value){
		super.put(key,new Long(value));
	}
	
	public long getLong(Object key){
		Object o = super.get(key);
		if (o == null){
			if (getNullToInitialize()){
				return 0;
			}
			else{
				throw new GlRuntimeException("Key(" + key +") does not exist in " + getName() + " ResultSet or Key(" + key +")'s value is null.");
			}
		}
		else{
			if (o instanceof Long){
				return ((Long)o).longValue();
			}
			else if (o instanceof Integer){
				return ((Integer)o).longValue();
			}
			else if (o instanceof Short){
				return ((Short)o).longValue();
			}
			else if (o instanceof Byte){
				return ((Byte)o).longValue();
			}
			else if (o instanceof String){
				try{
					return Long.parseLong(o.toString());
				}
				catch(Exception ex){
					throw new GlRuntimeException("Value Type(long) does not match : It's type is not long.");
				}
			}
			else
				throw new GlRuntimeException("Value Type(long) does not match : It's type is not long.");
		}
	}
	
	public void setShort(Object key, short value){
		super.put(key,new Short(value));
	}
	
	public short getShort(Object key){
		Object o = super.get(key);
		if (o == null){
			if (getNullToInitialize())
				return 0;
			else
				throw new GlRuntimeException("Key(" + key +") does not exist in " + getName() + " ResultSet or Key(" + key +")'s value is null.");
		}
		else{
			if (o instanceof Short){
				return ((Short)o).shortValue();
			}
			else if (o instanceof String){
				try{
					return Short.parseShort(o.toString());
				}
				catch(Exception ex){
					throw new GlRuntimeException("Value Type(short) does not match : It's type is not short.");
				}
			}
			else
				throw new GlRuntimeException("Value Type(short) does not match : It's type is not short.");
		}
	}
	
	public void setBoolean(Object key, boolean value){
		super.put(key,new Boolean(value));
	}
	
	public boolean getBoolean(Object key){
		Object o = super.get(key);
		if (o == null){
			if (getNullToInitialize()){
				return false;
			}
			else
				throw new GlRuntimeException("Key(" + key +") does not exist in " + getName() + " ResultSet or Key(" + key +")'s value is null.");
		}
		else{
			if (o instanceof Boolean){
				return ((Boolean)o).booleanValue();
			}
			else if (o instanceof String){
				if (o.toString().equals("true"))
					return true;
				else if (o.toString().equals("false"))
					return false;
				else
					throw new GlRuntimeException("Value Type(boolean) does not match : It's type is not boolean.");
			}
			else
				throw new GlRuntimeException("Value Type(boolean) does not match : It's type is not boolean.");
		}
	}
	
	public String toString() {
		int max = super.size() - 1;
		StringBuffer buf = new StringBuffer();

		Set keySet = super.keySet();
		Iterator keys = keySet.iterator();

		buf.append("\t-----------------[LData Result]------------------");
		buf.append("\n\t\t   KEY\t\t|\t  VALUE");
		buf.append("\n\t-------------------------------------------------");

		for (int i = 0; i <= max; i++) {
			Object o = keys.next();
			if (o == null) {
				buf.append( "" );
			} else {
				String str = o.toString();
				if( str.length() < 6 ){
					buf.append("\n\t  " + o + "\t\t\t|    " + this.getString(o) );
				}else if( str.length() < 14 ){
						buf.append("\n\t  " + o + "\t\t|    " + this.getString(o) );
				} else if(str.length() < 22 ){
					buf.append("\n\t  " + o + "\t|    " + this.getString(o) );
				}else {
					buf.append("\n\t  " + o + "|    " + this.getString(o) );
				}
			}//else if
		}// end for
		buf.append("\n\t-------------------------------------------------");
		return buf.toString();
    }
}



