package com.online.wangjialife.widget.selectcity;

import java.util.ArrayList;
import java.util.List;

import com.online.wangjialife.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class IndicatorAdapter extends BaseAdapter
{
	protected Context			mContext;
	private List<IndicatorModelData>		mList;
	
	public IndicatorAdapter(Context pContext){
		this.mContext = pContext;
		this.mList = new ArrayList<IndicatorModelData>();
	}
	
	@Override
	public int getCount()
	{
		return mList.size();
	}

	@Override
	public IndicatorModelData getItem(int pPosition)
	{
		return mList.get(pPosition);
	}

	@Override
	public long getItemId(int pPosition)
	{
		return pPosition;
	}

	public void setList(List<IndicatorModelData> pList)
	{
		if (pList != null) {
			mList = new ArrayList<IndicatorModelData>(pList);
		} else {
			mList = new ArrayList<IndicatorModelData>();
		}
		notifyDataSetChanged();
	}
	
	public List<IndicatorModelData> getList(){
		return this.mList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_indicator_item, parent, false);
			holder.groupNameWrap = (LinearLayout)convertView.findViewById(R.id.ItemIndicator_TextView_GroupNameWrap);
			holder.groupName = (TextView)convertView.findViewById(R.id.ItemIndicator_TextView_GroupName);
			holder.keyNameWrap = (LinearLayout)convertView.findViewById(R.id.ItemIndicator_TextView_KeyNameWrap);
			holder.keyName = (TextView)convertView.findViewById(R.id.ItemIndicator_TextView_KeyName);
			holder.content = (LinearLayout)convertView.findViewById(R.id.ItemIndicator_LinearLayout_Content);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		IndicatorModelData cData = getItem(position);
		String groupName = cData.getGroupName();
		String keyName = cData.getKeyName();
		View customerView = cData.getView(mContext);
		//控制分组名显示
		if(groupName!=null&&!"".equals(groupName)){
			holder.groupName.setText(groupName);
			if(position==0){
				holder.groupNameWrap.setVisibility(View.VISIBLE);
			}else{
				IndicatorModelData pData = getItem(position-1);
				if(groupName.equals(pData.getGroupName())){
					holder.groupNameWrap.setVisibility(View.GONE);
				}else{
					holder.groupNameWrap.setVisibility(View.VISIBLE);
				}
			}
		}else{
			holder.groupNameWrap.setVisibility(View.GONE);
		}
		//控制排序Key显示
		if(keyName!=null&&!"".equals(keyName)){
			holder.keyName.setText(keyName);
			if(position==0){
				holder.keyNameWrap.setVisibility(View.VISIBLE);
			}else{
				IndicatorModelData pData = getItem(position-1);
				if(keyName.equals(pData.getKeyName())){
					holder.keyNameWrap.setVisibility(View.GONE);
				}else{
					holder.keyNameWrap.setVisibility(View.VISIBLE);
				}
			}
		}else{
			holder.keyNameWrap.setVisibility(View.GONE);
		}
		//填充内容View
		if(customerView!=null&&customerView.getParent()==null){
			holder.content.removeAllViews();
			holder.content.addView(customerView);
		}
		return convertView;
	}
	
	static class ViewHolder {
		LinearLayout groupNameWrap;
        TextView groupName;
        LinearLayout keyNameWrap;
        TextView keyName;
        LinearLayout content;
    }
}
