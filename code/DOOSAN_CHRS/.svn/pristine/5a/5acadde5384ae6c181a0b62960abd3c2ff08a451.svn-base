/*
 * @(#)SimpleMap.java 1.0 2006-12-3
 *
 */
package com.ait.sqlmap.util;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.util.NumberUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-5 下午03:46:51
 * @version 1.0
 * 
 */
public class SimpleMap extends SimpleMapProtocol {

	private static final Logger logger = Logger.getLogger(SimpleMap.class);

	/**
	 * Constructor for SimpleMapProtocol.
	 * 
	 * @param name
	 */
	public SimpleMap(String name) {
		this.name = name;
	}

	/**
	 * Constructor for SimpleMapProtocol.
	 * 
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public SimpleMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Constructor for SimpleMapProtocol.
	 * 
	 * @param initialCapacity
	 */
	public SimpleMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Constructor for SimpleMapProtocol.
	 */
	public SimpleMap() {
		super();
	}

	/**
	 * Constructor for SimpleMapProtocol.
	 * 
	 * @param m
	 */
	public SimpleMap(Map m) {
		super(m);
	}

	/**
	 * Constructor for SimpleMapProtocol.
	 * 
	 * @param initialCapacity
	 * @param loadFactor
	 * @param accessOrder
	 */
	public SimpleMap(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}

	/** **************** 对象类型的Setter ***************** */

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            Object
	 */
	public void set(Object key, Object value) {
		super.put(key, value);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            Object
	 */
	public void setString(Object key, String value) {
		super.put(key, value);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            Object
	 */
	public void putString(Object key, String value) {
		super.put(key, value);
	}

	/** **************** 基本数据类型的Setter ***************** */

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            int
	 */
	public void setInt(Object key, int value) {
		Integer integer = new Integer(value);
		super.put(key, integer);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            int
	 */
	public void putInt(Object key, int value) {
		Integer integer = new Integer(value);
		super.put(key, integer);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            double
	 */
	public void setDouble(Object key, double value) {
		Double dou = new Double(value);
		super.put(key, dou);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            double
	 */
	public void putDouble(Object key, double value) {
		Double dou = new Double(value);
		super.put(key, dou);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            float
	 */
	public void setFloat(Object key, float value) {
		Float flo = new Float(value);
		super.put(key, flo);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            float
	 */
	public void putFloat(Object key, float value) {
		Float flo = new Float(value);
		super.put(key, flo);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            long
	 */
	public void setLong(Object key, long value) {
		Long lon = new Long(value);
		super.put(key, lon);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            long
	 */
	public void putLong(Object key, long value) {
		Long lon = new Long(value);
		super.put(key, lon);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            short
	 */
	public void setShort(Object key, short value) {
		Short shor = new Short(value);
		super.put(key, shor);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            short
	 */
	public void putShort(Object key, short value) {
		Short shor = new Short(value);
		super.put(key, shor);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            boolean
	 */
	public void setBoolean(Object key, boolean value) {
		Boolean bool = new Boolean(value);
		super.put(key, bool);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            boolean
	 */
	public void putBoolean(Object key, boolean value) {
		Boolean bool = new Boolean(value);
		super.put(key, bool);
	}

	/** **************** BigDecimal类型的Setter、Getter ***************** */

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            BigDecimal
	 */
	public void setBigDecimal(Object key, BigDecimal value) {
		super.put(key, value);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @param value
	 *            BigDecimal
	 */
	public void putBigDecimal(Object key, BigDecimal value) {
		super.put(key, value);
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @return BigDecimal
	 */
	public BigDecimal getBigDecimal(Object key) {

		Object o = get(key);
		if (o == null) {
			return new BigDecimal(0);
		} else {
			return (BigDecimal) o;
		}
	}

	/** **************** 基本数据类型的Getter ***************** */

	/**
	 * 
	 * @param key
	 *            String
	 * @return Object
	 */
	public Object get(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return "";
			} else {
				return null;
			}
		} else {
			return o;
		}
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @return int
	 */
	public int getInt(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return 0;
			} else {
				logger.error("Map's key (" + key + ") not exist or Map's key ("
						+ key + ") value is null");
				throw new GlRuntimeException("Map's key (" + key
						+ ") not exist or Map's key (" + key
						+ ") value is null");
			}
		} else {
			Class classType = o.getClass();
			if (classType == Integer.class) {
				return ((Integer) o).intValue();
			} else if (classType == Short.class) {
				return ((Short) o).shortValue();
			}
			
			if (classType == BigDecimal.class) {
				try {
					return NumberUtil.parseInt(o.toString()) ;
				} catch (Exception e) {
					logger.error("key (" + key + ")'s value Type(int) does not match.It's type is not int.");
					throw new GlRuntimeException("key (" + key + ")'s value Type(int) does not match.It's type is not int.");
				}
			}
			
			if (classType == String.class) {
				try {
					return Integer.parseInt(o.toString());
				} catch (Exception e) {
					logger.error("key (" + key + ")'s value Type(int) does not match.It's type is not int.");
					throw new GlRuntimeException("key (" + key + ")'s value Type(int) does not match.It's type is not int.");
				}
			}
			
			logger.error("key (" + key + ")'s value Type(int) does not match.It's type is not int.");
			throw new GlRuntimeException("key (" + key + ")'s value Type(int) does not match.It's type is not int.");
		}
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @return double
	 */
	public double getDouble(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return 0.0;
			} else {
				logger.error("Map's key (" + key + ") not exist or Map's key ("
						+ key + ") value is null");
				throw new GlRuntimeException("Map's key (" + key
						+ ") not exist or Map's key (" + key
						+ ") value is null");
			}
		} else {

			Class classType = o.getClass();

			if (classType == Double.class) {
				return ((Double) o).doubleValue();
			} else if (classType == Float.class) {
				return ((Float) o).floatValue();
			}
			if (classType == String.class || classType == BigDecimal.class) {
				try {
					return Double.parseDouble(o.toString());
				} catch (Exception e) {
					logger
							.error("key ("
									+ key
									+ ")'s value Type(double) does not match.It's type is not double.");
					throw new GlRuntimeException(
							"key ("
									+ key
									+ ")'s value Type(double) does not match.It's type is not double.");
				}
			}
			logger
					.error("key ("
							+ key
							+ ")'s value Type(double) does not match.It's type is not double.");
			throw new GlRuntimeException(
					"key ("
							+ key
							+ ")'s value Type(double) does not match.It's type is not double.");
		}
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @return int
	 */
	public float getFloat(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return (float) 0.0;
			} else {
				logger.error("Map's key (" + key + ") not exist or Map's key ("
						+ key + ") value is null");
				throw new GlRuntimeException("Map's key (" + key
						+ ") not exist or Map's key (" + key
						+ ") value is null");
			}
		} else {

			Class classType = o.getClass();

			if (classType == Float.class) {
				return ((Float) o).floatValue();
			}

			if (classType == String.class || classType == BigDecimal.class) {
				try {
					return Float.parseFloat(o.toString());
				} catch (Exception e) {
					logger
							.error("key ("
									+ key
									+ ")'s value Type(float) does not match.It's type is not float.");
					throw new GlRuntimeException(
							"key ("
									+ key
									+ ")'s value Type(float) does not match.It's type is not float.");
				}
			}
			logger
					.error("key ("
							+ key
							+ ")'s value Type(float) does not match.It's type is not float.");
			throw new GlRuntimeException(
					"key ("
							+ key
							+ ")'s value Type(float) does not match.It's type is not float.");
		}
	}

	/**
	 * 
	 * @param key
	 *            String
	 * @return int
	 */
	public long getLong(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return 0;
			} else {
				logger.error("Map's key (" + key + ") not exist or Map's key ("
						+ key + ") value is null");
				throw new GlRuntimeException("Map's key (" + key
						+ ") not exist or Map's key (" + key
						+ ") value is null");
			}
		} else {
			Class classType = o.getClass();

			if (classType == Long.class) {
				return ((Long) o).longValue();
			} else if (classType == Integer.class) {
				return ((Integer) o).intValue();
			} else if (classType == Short.class) {
				return ((Short) o).shortValue();
			}

			if (classType == String.class) {
				try {
					return Long.parseLong(o.toString());
				} catch (Exception e) {
					logger
							.error("key ("
									+ key
									+ ")'s value Type(long) does not match.It's type is not long.");
					throw new GlRuntimeException(
							"key ("
									+ key
									+ ")'s value Type(long) does not match.It's type is not long.");
				}
			}
			logger
					.error("key ("
							+ key
							+ ")'s value Type(long) does not match.It's type is not long.");
			throw new GlRuntimeException(
					"key ("
							+ key
							+ ")'s value Type(long) does not match.It's type is not long.");
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public short getShort(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return 0;
			} else {
				logger.error("Map's key (" + key + ") not exist or Map's key ("
						+ key + ") value is null");
				throw new GlRuntimeException("Map's key (" + key
						+ ") not exist or Map's key (" + key
						+ ") value is null");
			}
		} else {

			Class classType = o.getClass();

			if (classType == Short.class) {
				return ((Short) o).shortValue();
			}

			if (classType == String.class) {
				try {
					return Short.parseShort(o.toString());
				} catch (Exception e) {
					logger
							.error("key ("
									+ key
									+ ")'s value Type(short) does not match.It's type is not short.");
					throw new GlRuntimeException(
							"key ("
									+ key
									+ ")'s value Type(short) does not match.It's type is not short.");
				}
			}
			logger
					.error("key ("
							+ key
							+ ")'s value Type(short) does not match.It's type is not short.");
			throw new GlRuntimeException(
					"key ("
							+ key
							+ ")'s value Type(short) does not match.It's type is not short.");
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return false;
			} else {
				logger.error("Map's key (" + key + ") not exist or Map's key ("
						+ key + ") value is null");
				throw new GlRuntimeException("Map's key (" + key
						+ ") not exist or Map's key (" + key
						+ ") value is null");
			}
		} else {
			if (o.getClass().isInstance(new Boolean(true))) {
				return ((Boolean) o).booleanValue();
			}

			if (o.getClass().isInstance(new String())) {
				try {
					return Boolean.getBoolean(o.toString());
				} catch (Exception e) {
					logger
							.error("key ("
									+ key
									+ ")'s value Type(boolean) does not match.It's type is not boolean.");
					throw new GlRuntimeException(
							"key ("
									+ key
									+ ")'s value Type(boolean) does not match.It's type is not boolean.");
				}
			}
			logger
					.error("key ("
							+ key
							+ ")'s value Type(boolean) does not match.It's type is not boolean.");
			throw new GlRuntimeException(
					"key ("
							+ key
							+ ")'s value Type(boolean) does not match.It's type is not boolean.");
		}
	}

	/**
	 * 
	 * @param key
	 * 
	 * @return String
	 */
	public String getString(Object key) {
		Object o = (Object) super.get(key);

		if (o == null) {
			if (this.nullToInitialize) {
				return "";
			} else {
				return null;
			}
		} else {
			return o.toString();
		}
	}

	/**
	 * 
	 * @return java.lang.String
	 */
	public synchronized String toString() {
		int max = super.size() - 1;
		StringBuffer buf = new StringBuffer();

		Set vset = super.entrySet();
		Iterator values = vset.iterator();

		buf.append("{");

		for (int i = 0; i <= max; i++) {
			Object o = values.next();

			if (o == null) {
				buf.append("");
			} else {
				buf.append(o.toString());
			}

			if (i < max) {
				buf.append(", ");
			}
		}
		buf.append("}");

		return "SimpleMap[" + this.getName() + "]=" + buf.toString();
	}

}
