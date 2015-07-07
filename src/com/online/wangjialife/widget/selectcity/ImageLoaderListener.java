package com.online.wangjialife.widget.selectcity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.online.wangjialife.R;

public class ImageLoaderListener extends AnimateFirstDisplayListener{
	private ScaleType oldScaleType = ScaleType.CENTER;
	private ImageViewWidget mImageView;
	private EventHandler handler;
	private int mResNone = R.drawable.empty_photo_square;
	
	public ImageLoaderListener(ImageViewWidget mImageView) {
		this.mImageView = mImageView;
		//记录原始ScaleType值
		this.oldScaleType = this.mImageView.getScaleType();
		//设置居中
		this.mImageView.setScaleType(ScaleType.CENTER);
	}
	
	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		super.onLoadingComplete(imageUri, view, loadedImage);
		//将图片显示格式恢复
		this.mImageView.setScaleType(this.oldScaleType);
		//设置图片背景为透明
		this.mImageView.setBackgroundColor(mImageView.getResources().getColor(R.color.transparent));
		//当图片加载完成后，执行回调事件
		if(this.handler!=null)this.handler.doHandler();
	}
	
	@Override
	public void onLoadingCancelled(String imageUri, View view) {
//		super.onLoadingCancelled(imageUri, view);
		//加载取消时可设置默认图
		this.mImageView.setBackgroundResource(mResNone);
	}
	
	@Override
	public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//		super.onLoadingFailed(imageUri, view, failReason);
		//加载失败时可设置默认图
		this.mImageView.setBackgroundResource(mResNone);
	}
	
	public void setHandler(EventHandler handler){
		this.handler = handler;
	}
	
	public void setResNone(int pResNone){
		this.mResNone = pResNone;
	}
}