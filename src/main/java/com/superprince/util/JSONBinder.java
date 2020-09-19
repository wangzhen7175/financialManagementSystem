package com.superprince.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;


/**
 * JSON工具�?
 * @author 
 *
 */

public class JSONBinder {

	private static Log log = LogFactory.getLog(JSONBinder.class);
	private ObjectMapper mapper;

	public JSONBinder(Inclusion inclusion) {
		mapper = new ObjectMapper();
		// 设置输出包含的属�?
		mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属�?
		mapper.getDeserializationConfig()
				.set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);
	}

	/**
	 * 创建输出全部属�?到Json字符串的Binder.
	 */
	public static JSONBinder buildNormalBinder() {
		return new JSONBinder(Inclusion.ALWAYS);
	}

	/**
	 * 创建只输出非空属性到Json字符串的Binder.
	 */
	public static JSONBinder buildNonNullBinder() {
		return new JSONBinder(Inclusion.NON_NULL);
	}

	/**
	 * 创建只输出初始�?被改变的属�?到Json字符串的Binder.
	 */
	public static JSONBinder buildNonDefaultBinder() {
		return new JSONBinder(Inclusion.NON_DEFAULT);
	}

	/**
	 * 如果JSON字符串为Null�?null"字符�?返回Null. 如果JSON字符串为"[]",返回空集�?
	 * 
	 * 如需读取集合如List/Map,且不是List<String>这种�?��类型时使用如下语�? List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtil.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}
	}

	public <T, K> Map<T, K> fromJson(String jsonString) {
		if (StringUtil.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, new TypeReference<Map<T, K>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	

	public List<Map<String, String>> fromJsonListMap(String jsonString) {
		if (StringUtil.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString,
					new TypeReference<List<Map<String, String>>>() {
					});
			// return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	public <T> List<T> fromJsonList(String jsonString) {
		if (StringUtil.isEmpty(jsonString)) {
			return null;
		}
		try {
			return mapper.readValue(jsonString, new TypeReference<List<T>>() {
			});
			// return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			// LOG.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 如果对象为Null,返回"null". 如果集合为空集合,返回"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒�?
	 */
	public void setDateFormat(String pattern) {

		DateFormat df = new SimpleDateFormat(pattern);
		mapper.getSerializationConfig().setDateFormat(df);
		mapper.getDeserializationConfig().setDateFormat(df);

	}

	/**
	 * 取出Mapper做进�?��的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}