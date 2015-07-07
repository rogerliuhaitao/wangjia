package com.online.wangjialife.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.online.wangjialife.R;
import com.online.wangjialife.control.MyApplication;


public class MainActivity extends BaseActivity {

	private Button tiaozhuan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiaozhuan_btn=(Button) this.findViewById(R.id.tiaozhuan_btn);
        tiaozhuan_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, ChooseCityActivity.class);
				startActivity(intent);
			}
		});
    }
}
