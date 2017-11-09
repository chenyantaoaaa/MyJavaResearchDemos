package com.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

	// private static Logger log = Logger.getLogger(JsonUtils.class);
	// 判断是否是json
	public static boolean isJson(String s) {
		if (s != null && s.startsWith("{") && s.endsWith("}")) {
			return true;
		}
		return false;
	}

	public static boolean isSucJson(String s) {
		if (!isJson(s)) {
			return false;
		}
		JSONObject json = JSONObject.fromObject(s);
		if (json.getString("returnCode").equals("9999")) { // api接口系统异常
			return false;
		}
		return true;
	}

	public static JSONArray getJsonArray(JSONObject o, String name) {
		if (o == null || name == null || name.equals("")) {
			return new JSONArray();
		}
		if (o.containsKey(name)) {
			return o.getJSONArray(name);
		}
		return new JSONArray();
	}

	public static String getString(JSONObject o, String name) {
		if (null == o || null == name) {
			return null;
		}
		if (o.containsKey(name)) {
			return o.getString(name);
		}
		return null;
	}

	public static String getJsonString(JSONObject o, String str) {
		if (null != o && o.has(str)) {
			return o.get(str).toString();
		}
		return "";
	}

	/**
	 * json字符串转成JSONObject对象
	 * 
	 * @param str
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static JSONObject jsonStrToJsonObj(String str) {
		JSONObject obj = null;
		if (isJson(str)) {
			obj = JSONObject.fromObject(str);
		}
		return obj;
	}


	/**
	 * javaBean转Map 日期不处理
	 * 
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> convertMap(Object bean) throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class<?> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					if (descriptor.getPropertyType() == String.class) {
						returnMap.put(propertyName, "");
					} else {
						returnMap.put(propertyName, result);
					}

				}
			}
		}
		return returnMap;
	}

	/**
	 * 优先转化为json对象，转化失败尝试转为json数组
	 * 
	 * @param obj
	 * @return
	 */
	public static String beanToJson(Object obj) {
		if (obj == null)
			return "";
		try {
			return JSONObject.fromObject(obj).toString();
		} catch (Exception e) {
			return JSONArray.fromObject(obj).toString();
		}

	}
}
