package com.online.wangjialife.util;



import com.online.wangjialife.control.MyApplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * 
 * @项目名称:Eco_Farm   
 * @本类名称:SharedPreferencesUtils   
 * @本类描述:保存在缓存中的数据信息 
 * @创建作者:刘海涛  
 * @创建时间:2014-8-22 上午10:18:49     
 * @修改备注:
 * @version 
 *
 */
public class SharedPreferencesUtils {

    /**
     * 获取应用程序默认的SharedPreferences对象
     * 
     * @param ctx
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context ctx) {
        if (ctx == null) {
            return null;
        }
//        return ctx.getSharedPreferences("nuoter", ctx.MODE_PRIVATE);
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     * 
     * @param ctx
     * @param key
     * @return
     */
    public static String getStringValue(String key) {
        String defaultValue = "";
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(MyApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getString(key, defaultValue);
    }
    /**
     * 获取设备SharedPreferences中存储的指定key对应的value 有默认值
     * 
     * @param ctx
     * @param key
     * @return
     */
    public static String getStringValue(String key,String defaultValue) {
    	if (TextUtils.isEmpty(key)) {
    		return defaultValue;
    	}
    	SharedPreferences sp = getSharedPreferences(MyApplication.getInstance());
    	if (sp == null) {
    		return defaultValue;
    	}
    	return sp.getString(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     * 
     * @param ctx
     * @param key
     * @return
     */
    public static float getFloatValue(String key) {
        float defaultValue = 0f;
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(MyApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getFloat(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     * 
     * @param ctx
     * @param key
     * @return
     */
    public static int getIntValue(String key, int defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(MyApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getInt(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     * 
     * @param ctx
     * @param key
     * @return
     */
    public static long getLongValue( String key) {
        long defaultValue = 0l;
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(MyApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getLong(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     * 
     * @param ctx
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanValue(String key, boolean defaultValue) {
        if (TextUtils.isEmpty(key)) {
            return defaultValue;
        }
        SharedPreferences sp = getSharedPreferences(MyApplication.getInstance());
        if (sp == null) {
            return defaultValue;
        }
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 获取设备SharedPreferences中存储的指定key对应的value
     * 
     * @param ctx
     * @param key
     * @return
     */
    public static boolean getBooleanValue(String key) {
        return getBooleanValue(key, false);
    }

    /**
     * 获取Editor对象
     * 
     * @param ctx
     * @return
     */
    private static Editor getEditor() {
        SharedPreferences sp = getSharedPreferences(MyApplication.getInstance());
        if (sp == null) {
            return null;
        }
        return sp.edit();
    }

    /**
     * 保存int型数据到默认的SharedPreferences
     * 
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveIntValue( String key, int value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 保存String型数据到默认的SharedPreferences
     * 
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveStringValue(String key, String value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putString(key, TextUtils.isEmpty(value) ? "" : value);
        editor.commit();
    }

    /**
     * 保存boolean型数据到默认的SharedPreferences
     * 
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveBooleanValue( String key, boolean value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 保存float型数据到默认的SharedPreferences
     * 
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveFloatValue( String key, float value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 保存long型数据到默认的SharedPreferences
     * 
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveLongValue(String key, long value) {
        Editor editor = getEditor();
        if (editor == null) {
            return;
        }
        editor.putLong(key, value);
        editor.commit();
    }
}
