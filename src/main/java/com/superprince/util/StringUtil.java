package com.superprince.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 字符串帮助类
 * @author xwang
 *
 */
public class StringUtil {

	static public boolean isEmpty(Object _value) {
		if (_value == null)
			return true;
		return isEmpty(_value.toString());
	}

	static public String toString(Object _val) {
		if (_val == null)
			return "";
		return _val.toString();
	}

	static public boolean isEmpty(String _value) {
		if (_value != null && !_value.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	static public boolean isEmptyIncludeBlank(String _value) {
		if (_value != null && !_value.isEmpty()) {
			return false;
		}
		return true;
	}

	static public boolean isEqual(String _res1, String _res2) {
		if (_res1 == null && _res2 == null)
			return true;
		if (_res1 == null)
			return false;
		if (_res2 == null)
			return false;
		return _res1.equalsIgnoreCase(_res2);
	}

	/*
	 * NULL=NULL �?true�?“�?=NULL �?true
	 */
	static public boolean isEqualNullOrEmpty(String _res1, String _res2) {
		if (_res1 == null && _res2 == null)
			return true;
		if (_res1 == null && _res2.isEmpty() || _res2 == null
				&& _res1.isEmpty())
			return true;
		if (_res1 == null || _res2 == null)
			return false;
		return _res1.equalsIgnoreCase(_res2);
	}

	static public boolean translateStringToBoolean(String _resFlage,
			boolean _defaultValue) {
		boolean bFlage = _defaultValue;
		if (StringUtil.isEmpty(_resFlage)) {
			return bFlage;
		}
		if (StringUtil.isEqual(_resFlage, "true")) {
			bFlage = true;
		} else if (StringUtil.isEqual(_resFlage, "false")) {
			bFlage = false;
		}
		return bFlage;
	}

	static public int toNumeric(String _numStr) {
		int value = 0;
		try {
			Integer integer = Integer.valueOf(_numStr);
			value = integer.intValue();
		} catch (Exception e) {

		}
		return value;
	}

	@SuppressWarnings("unchecked")
	static public String getMapValue(Map<String, Object> values,
			String keyname, String defaultVal) {
		if (values.containsKey(keyname)) {
			Object value = values.get(keyname);
			if (value == null) {
				return defaultVal;
			} else {
				return value.toString();
			}
		}		
		return defaultVal;
	}
	
	static public String getMapsValue(Map<String, String[]> values,
			String keyname, String defaultVal) {
		if (values.containsKey(keyname)) {
			String[] value = values.get(keyname);
			if (value == null || value.length == 0) {
				return defaultVal;
			} else {
				return value[0];
			}
		}		
		return defaultVal;
	}
	
	@SuppressWarnings("unchecked")
	static public String getMapValue2(Map<String, String> values,
			String keyname, String defaultVal) {
		if (values.containsKey(keyname)) {
			String value = values.get(keyname);
			if (StringUtil.isEmpty(value)) {
				return defaultVal;
			} else {
				return value;
			}
		}		
		return defaultVal;
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 是否为空包括回车
	 */
	public static boolean isEmptyIncludeEnter( String _value )
	{
		if( _value == null )
			return true;
		_value = _value.replaceAll("\r", "");
		_value = _value.replaceAll("\n", "");
		_value = _value.replaceAll(" ", "");
		_value = _value.trim();
		if( _value.length() == 0 )
			return true;
		return false;
	}
	
	
	
	public static List<String> splitStringToList( String _value , String _spliter )
	{
		List<String> wordList = new ArrayList<String>();
		if( StringUtil.isEmpty(_value) )
			return wordList;
		String words[] = _value.split(_spliter);
		for( int i = 0 ;i < words.length ; i++ )
		{
			if( !StringUtil.isEmpty( words[i] ) )
				wordList.add(  words[i] );
		}
		//wordList = CollectionUtils.arrayToList(words);
		return wordList;
	}
	
	
	public static Map<String,Object> mapValues2MapValue( Map<String,String[]> _params )
	{
		Map<String,Object> mapvalue = new HashMap<String,Object>();
		Set<String> keys = _params.keySet();
		for( String key: keys )
		{
			String values[] = _params.get(key);
			if( values != null && values.length > 0 )
			{
				Object value = values[0];
				if( !StringUtil.isEmpty(value))
					mapvalue.put( key, values[0]);
			}
		}
		return mapvalue;
	}
	public static String substring(String str, int toCount, String more) {
    int reInt = 0;
    String reStr = "";
    if (str == null || str.equals("") || toCount < 1)
      return reStr;
    char[] tempChar = str.toCharArray();
    for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++){
      String s1 = String.valueOf(tempChar[kk]);
      byte[] b = s1.getBytes();
      reInt += b.length;
      reStr += tempChar[kk];
    }
    if (toCount == reInt || (toCount == reInt - 1))
      reStr += more;
    return reStr;
  }
}