package com.online.wangjialife.util;

/**
 * 
 * @项目名称:Eco_Farm
 * @本类名称:Contects
 * @本类描述:缓存时的key值保存类
 * @创建作者:刘海涛
 * @创建时间:2014-8-22 上午10:18:38
 * @修改备注:
 * @version
 * 
 */
public class Contects {
	public static String ISFIRSTLOAD = "isfirstload";// 是不是软件第一次运行
	public static String ISLOGIN = "islogin";// 是否已经登录
	public static String ISAUTOLOGIN = "isautologin";// 在用户登录页面，选择是不是自动登录
	public static String REGISTER_PHONE = "register_phone";// 注册第一个页面的手机号
	// ------------忘记密码页面中的临时数据-----------
	public static String FORGETPASSWORD_PHONENUMBER = "forgetpassword_phonenumber";// 忘记密码页面中输入的手机号
	// 下面是登录或注册的时候 调接口返回的用户信息
	public static String USER_USERID = "user_userId";
	public static String USER_USERNAME = "user_username";
	public static String USER_PASSWORD = "user_password";
	public static String USER_BYNAME = "user_byName";
	public static String USER_SEX = "user_sex";
	public static String USER_ADDRESS = "user_address";
	public static String USER_PROVINCE = "user_province";
	public static String USER_CITY = "user_city";
	public static String USER_PROVINCE_NAME = "user_province_name";
	public static String USER_CITY_NAME = "user_city_name";
	public static String USER_VIPEXP = "user_vipExp";
	public static String USER_VIPRANK = "user_vipRank";
	public static String USER_BIRTHDAY = "user_birthday";
	/**
	 * 账户余额。
	 */
	public static String USER_GOLD = "user_gold";
	public static String USER_AGIO = "user_agio";
	public static String USER_IMAGE = "user_image";
	public static String USER_STATUS = "user_status";
	public static String USER_RANKNAME = "user_rankname";
	// 在城市切换的时候选城市
	public static String switchCity = "SWITCHCITY";
	// 在统一搜索页面 是显示列表还是显示gridview布局
	public static String isShowListView = "ISSHOWLISTVIEW";
	//未读的消息的个数
//	public static String unReadMessageCounts="UNREADMESSAGECOUNTS";

}
