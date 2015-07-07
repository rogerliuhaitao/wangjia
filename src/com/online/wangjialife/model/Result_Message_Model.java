package com.online.wangjialife.model;


/**
 * 
 * @项目名称:考研专业课
 * @本类描述:消息的model
 * @创建作者:徐岩
 * @修改备注:
 * @version
 * 
 */
public class Result_Message_Model{
	//暂时模拟字段

	private String name;
	private String msg;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Result_Message_Model(String name, String msg) {
		super();
		this.name = name;
		this.msg = msg;
	}

	
	
}