package cn.andylhl.crm.utils;

import java.util.UUID;

public class UUIDUtil {

	private UUIDUtil(){
	}

	/**
	 * 获取一个UUID随机字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}

}
