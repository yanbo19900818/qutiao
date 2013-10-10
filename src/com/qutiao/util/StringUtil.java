package com.qutiao.util;



import org.apache.commons.lang.StringUtils;

public class StringUtil {
	
	/**
	 * 空字符串转成0
	 * @param str
	 * @return
	 */
	public static int String2Zero(String str) {		
		return StringUtils.isBlank(str) ? 0 : Integer.parseInt(str);
	}
	
	/**
	 * 空字符串转成1
	 * @param str
	 * @return
	 */
	public static int String2One(String str) {
		return StringUtils.isBlank(str) ? 1 : Integer.parseInt(str);
	}

	public static void main(String[] args) {
		System.out.println(String2Zero(""));
		System.out.println(String2Zero("10"));
	}
	
	 
    /**
     * 将字符串为NULL或是多个空格的设置为空字符串
     * @param str
     * @return
     */
    public static String converNulltoString(String str){
        if(str == null){
            return "";
        }
        return str.trim();
    }

    /**
     * 判断是否需要生成广告页专用
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isChanged(String str1, String str2){
        if(! StringUtils.isBlank(str1) && ! StringUtils.equals(str1, str2)){
        	return true;
        } else {
        	return false;
        }
    }
}
