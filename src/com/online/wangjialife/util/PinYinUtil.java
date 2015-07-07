package com.online.wangjialife.util;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtil {  
	//汉字转成拼音
	public static String ToPinYinString(String str) {
		StringBuilder sb = new StringBuilder();
		String[] arr = null;

		for (int i = 0; i < str.length(); i++) {
			arr = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i));
			if (arr != null && arr.length > 0) {
				for (String string : arr) {
					sb.append(string);
				}
			}
		}
		return sb.toString();
	} 
}
