package com.bobo.waimai.commons.utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;

public class StringUtils {
	/**
	 * 回去uuid
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	/**
	 * 获取指定内容的md5值,暂时不加盐
	 * @param source
	 * @return
	 */
	public static String getMD5(String source) {
		if (source==null) {
			return null;
		}
		String MD5 = DigestUtils.md5DigestAsHex(source.getBytes());
		return MD5;
	}


	
	/**
	 * 判断字符串是否为空
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {
		return source==null||source.trim().equals("");
	}
}
