package com.online.wangjialife.widget.selectcity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.online.wangjialife.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageViewWidget extends ImageView {
	
	private int mResNone = R.drawable.empty_photo_square;
	
	public ImageViewWidget(Context context) {
		super(context);
	}

	public ImageViewWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ImageViewWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	private ImageLoaderListener globalImageLoaderListener = new ImageLoaderListener(this);
	
	public void setHandler(EventHandler handler){
		globalImageLoaderListener.setHandler(handler);
	}
	
	public ImageViewWidget setResNone(int pResNone){
		this.mResNone = pResNone;
		this.globalImageLoaderListener.setResNone(pResNone);
		return this;
	}
	
	/**
	 * 设置图片显示的存储卡路径
	 * @param src 图片存储卡路径src
	 */
	public ImageViewWidget setImageSrc(String src){
		//首先设置默认图片
		this.setBackgroundResource(mResNone);
		if(src!=null&&!"".equals(src)){
			if(!src.startsWith("file://")){
				src = "file://" + src;
			}
			//异步加载显示图片
			ImageLoader.getInstance().displayImage(src, this, globalImageLoaderListener);
		}
		return this;
	}
	
	/**
	 * 设置图片显示的URL，遵守系统设置的“无图模式”的状态来判断是否显示图片还是显示占位符
	 * @param url 图片URL
	 */
	public ImageViewWidget setImage(String url){
		//首先设置默认图片
		this.setBackgroundResource(mResNone);
		if(url!=null&&!"".equals(url)&&(url.startsWith("http://")||url.startsWith("https://"))){
			//异步加载显示图片
			ImageLoader.getInstance().displayImage(url, this, globalImageLoaderListener);
		}
		return this;
	}
	
	/**
	 * 设置图片显示的URL，可以控制是否遵守系统设置的“无图模式”的状态
	 * @param url 图片URL
	 * @param abideNoImageRule true则遵守系统设置的”无图模式“状态 false则不遵守
	 */
	public ImageViewWidget setImage(String url, boolean abideNoImageRule){
		if(abideNoImageRule){
			this.setImage(url);
		}else if(url!=null&&!"".equals(url)&&(url.startsWith("http://")||url.startsWith("https://"))){
			ImageLoader.getInstance().displayImage(url, this, globalImageLoaderListener);
		}
		return this;
	}
}