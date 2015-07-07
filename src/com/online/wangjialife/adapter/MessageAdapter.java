package com.online.wangjialife.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.online.wangjialife.R;
import com.online.wangjialife.model.Result_Message_Model;


public class MessageAdapter extends AdapterBase<Result_Message_Model>{
	private ImageLoader imageLoade = ImageLoader.getInstance();
	private DisplayImageOptions options;
	
	public MessageAdapter(Context pContext, List<Result_Message_Model> pList) {
		super(pContext, pList);
	}

	public MessageAdapter(Context pContext) {
		super(pContext);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.home_pic_teacher_default)
		.showImageForEmptyUri(R.drawable.home_pic_teacher_default)
		.showImageOnFail(R.drawable.home_pic_teacher_default)
		// .displayer(new RoundedBitmapDisplayer(10)) // 设置成圆角图片
		.cacheInMemory(true).cacheOnDisc(true)
		.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder _Holder;
		if (convertView == null) {
			_Holder = new ViewHolder();
			convertView = getLayoutInflater().inflate(R.layout.activity_message_list_item_layout, parent, false);
			_Holder.teacher_name_value=(TextView) convertView.findViewById(R.id.teacher_name_value);
			convertView.setTag(_Holder);
		} else {
			_Holder = (ViewHolder) convertView.getTag();
		}
		final Result_Message_Model model = getItem(position);
		_Holder.teacher_name_value.setText(model.getName()+"");
		return convertView;
	}
	

	private static class ViewHolder {
		private TextView teacher_name_value;
	}
}
