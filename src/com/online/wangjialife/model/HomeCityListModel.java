package com.online.wangjialife.model;

/**
 * 
 * @项目名称:NuoTerFarm
 * @本类名称:HomeCityListModel
 * @本类描述:首页中城市选择页面的model
 * @创建作者:刘海涛
 * @创建时间:2015-1-13 下午2:19:19
 * @修改备注:
 * @version
 * 
 */
public class HomeCityListModel {

	private String CITY_ID;
	private String CITY_NAME;

	public String getCITY_ID() {
		return CITY_ID;
	}

	public void setCITY_ID(String cITY_ID) {
		CITY_ID = cITY_ID;
	}

	public String getCITY_NAME() {
		return CITY_NAME;
	}

	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}

	public HomeCityListModel(String cITY_ID, String cITY_NAME) {
		super();
		CITY_ID = cITY_ID;
		CITY_NAME = cITY_NAME;
	}

	public HomeCityListModel() {
		super();
	}
}
