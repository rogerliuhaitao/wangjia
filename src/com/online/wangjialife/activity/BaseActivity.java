package com.online.wangjialife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.online.wangjialife.control.MyApplication;
import com.online.wangjialife.widget.LoadingDialog;

public abstract class BaseActivity extends Activity{
	private LoadingDialog loadingDialog;
	@Override
	protected void onCreate(Bundle pSavedInstanceState) {
		super.onCreate(pSavedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		MyApplication.getInstance().addActivity(this);
	}
	/***
	 * 显示数据加载loading框
	 */
	protected void showLoadingProgressBar() {
		if (loadingDialog == null) {
			// TODO 自定义所需要的样式
			loadingDialog = new LoadingDialog(this);
		}
		loadingDialog.show();
	}

	/**
	 * 隐藏数据加载loading框
	 */
	protected void closeLoadingProgressBar() {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
		}
	}
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent _Intent = new Intent(this, pClass);
		if (pBundle != null) {
			_Intent.putExtras(pBundle);
		}

		startActivity(_Intent);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
