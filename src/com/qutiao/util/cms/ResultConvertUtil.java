package com.qutiao.util.cms;


public final class ResultConvertUtil {
	/**
	 * 根据修改(update)和删除(delete)影响行数(是否大于0),判断是否成功
	 * @param result
	 * @return 成功true,失败false
	 */
	public static boolean getBooleanByUD(int result){
		if(result > 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 判断对象是否null
	 * @param object
	 * @return null返回false,非null返回true
	 */
	public static boolean getBooleanByIsNull(Object object){
		if(null == object){
			return false;
		}
		else {
			return true;
		}
	}
}
