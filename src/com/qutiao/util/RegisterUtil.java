package com.qutiao.util;

import java.util.Random;

public final class RegisterUtil {

	private static RegisterUtil registerUtil = new RegisterUtil();
	private static Object initLock = new Object();
	private static Random randkey = null;
	private static Random randpass = null;
	private static Random rand = null;
	private static char[] num = null;
	private static char[] numAndLetters = null;

	/**
	 * 
	 */
	private RegisterUtil() {
	}

	/**
	 * 
	 * @return
	 */
	public static RegisterUtil getInstence() {
		return registerUtil;
	}

	/**
	 * 产生激活码
	 * 
	 * @param length
	 * @return
	 */
	public final String generateActiveCode(int length) {

		if (length < 1)
			return null;
		if (rand == null) {
			synchronized (initLock) {
				rand = new Random();
				numAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
						.toCharArray();
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numAndLetters[rand.nextInt(61)];
		}
		return new String(randBuffer);
	}

	/**
	 * creat key
	 */
	public final String getKey(int length) {
		if (length < 1)
			return null;
		if (randkey == null) {
			synchronized (initLock) {
				randkey = new Random();
				numAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
						.toCharArray();
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numAndLetters[randkey.nextInt(61)];
		}
		return new String(randBuffer);
	}

	/**
	 * 
	 * @param length
	 * @return
	 */
	public final String getDeveloperPassword(int length) {
		if (length < 1)
			return null;
		if (randpass == null) {
			synchronized (initLock) {
				randpass = new Random();
				num = ("0123456789").toCharArray();
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = num[randpass.nextInt(9)];
		}
		return new String(randBuffer);
	}

	// public String getCode(SessionFactory sessionFactory) {
	//
	// String hql = "from SnsUserCode";
	// long start = System.currentTimeMillis();
	// Long code;
	// Session sn = null;
	// Transaction ts = null;
	// try {
	// sn = sessionFactory.openSession();
	// ts = sn.beginTransaction();
	// Query q = sn.createQuery(hql);
	// q.setFirstResult(0);
	// q.setMaxResults(1);
	// List<SnsUserCode> userCode = q.setCacheable(true).list();
	// if (userCode == null || userCode.size() == 0)
	// return "";
	// code = userCode.get(0).getUserCode();
	// sn.delete(userCode.get(0));
	// ts.commit();
	// } catch (Exception e) {
	// if (ts != null)
	// ts.rollback();
	// e.printStackTrace();
	// //logger.error("GetCode is error...,message:" + e.getMessage(), e);
	// return "";
	// } finally {
	// if (sn != null)
	// sn.close();
	// }
	//
	// /*logger.debug("Get UserCode Consume time is "
	// + (System.currentTimeMillis() - start) + " ,code=" + code
	// + " ,time is " + new Date());*/
	// return code + "";
	// }
public static void main(String[] args)
{
System.out.print(RegisterUtil.getInstence().getKey(18));	
}
}
