package com.appleyk.common;

import java.util.*;

/**
 * 通用工具类，主要用于判断对象是否为空
 *
 */
public final class GeneralUtil {

	private GeneralUtil() {
	}

	/**
	 * 判断字符串是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !str.trim().equals("") && !str.equals("null");
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("") || str.equals("null") || str.equals("0");
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return !isNotEmpty(obj);
	}

	public static boolean stringEquals(String str1, String str2) {
		return str1 != null && str2 != null && str1.equals(str2);
	}

	/**
	 * 判断对象是否为非空
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		if (obj != null) {
			if (obj instanceof Integer)
				return Integer.valueOf(obj.toString()) > 0 ? true : false;
			if (obj instanceof Long)
				return Long.valueOf(obj.toString()) > 0 ? true : false;
			if (obj instanceof String)
				return ((String) obj).trim().length() > 0;
			if (obj instanceof StringBuffer)
				return ((StringBuffer) obj).toString().trim().length() > 0;
			if (obj instanceof List)
				return ((List<?>) obj).size() > 0;
			if (obj instanceof Set)
				return ((Set<?>) obj).size() > 0;
			if (obj instanceof Vector)
				return ((Vector<?>) obj).size() > 0;
			if (obj instanceof Map)
				return ((Map<?, ?>) obj).size() > 0;
			if (obj instanceof Iterator)
				return ((Iterator<?>) obj).hasNext();
			if (obj.getClass().isArray())
				return Arrays.asList(obj).size() > 0;
			return true;
		}
		return false;
	}


	/**
	 * 判断字符串能否转为long
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidLong(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断List集合是否为空
	 * @param list
	 * @return
	 */
	public static boolean isEmptyList(List<?> list) {
		
		if (list == null || list.size() == 0) {
			return true;
		}

		return false;
	}


}
