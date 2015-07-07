package com.online.wangjialife.widget.selectcity;

import android.content.Context;
import android.view.View;

public abstract class IndicatorModelData {
	private String groupName;
	private String keyName;
	
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
	public String getGroupName(){
		return this.groupName;
	}
	
	public void setKeyName(String keyName){
		this.keyName = keyName;
	}
	
	public String getKeyName(){
		return this.keyName;
	}
	
	public abstract View getView(Context pContext);
}
