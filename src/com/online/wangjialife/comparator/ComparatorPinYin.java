package com.online.wangjialife.comparator;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import com.online.wangjialife.widget.selectcity.ModelBusinessContact;



import android.util.Log;

public class ComparatorPinYin implements Comparator<ModelBusinessContact> {
	@Override
	public int compare(ModelBusinessContact o1, ModelBusinessContact o2) {
		Log.i("MSG", o1.getName());
		// 判断是否为汉字字符   
		//o1.getAlphabetize().matches("[\\u4E00-\\u9FA5]+");

		/**
		 * 先比较汉字和拼音，汉字排在前面，
		 * 再比较每一个大的模块儿，按照首字母的排列顺序排列
		 */
		Collator ca = Collator.getInstance(Locale.CHINA);
		int flags = 0;
		
		
		if (ca.compare(o1.getAlphabetize(), o2.getAlphabetize()) < 0) {
			flags = -1;
		} else if (ca.compare(o1.getAlphabetize(), o2.getAlphabetize()) > 0) {
			flags = 1;
		} else {
			if (ca.compare(o1.getName(), o2.getName()) < 0) {
				flags = -1;
			} else if (ca.compare(o1.getName(), o2.getName()) > 0) {
				flags = 1;
			} else{
				flags = 0;
			}
		}
		return flags;

	}

	// int compare(Object o1, Object o2) 返回一个基本类型的整型，返回负数表示o1 小于o2，返回0
	// 表示o1和o2相等，返回正数表示o1大于o2。
}
