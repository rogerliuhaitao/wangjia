package com.online.wangjialife.activity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.online.wangjialife.R;
import com.online.wangjialife.comparator.ComparatorPinYin;
import com.online.wangjialife.control.MyApplication;
import com.online.wangjialife.model.HomeCityListModel;
import com.online.wangjialife.util.Contects;
import com.online.wangjialife.util.PinYinUtil;
import com.online.wangjialife.util.SharedPreferencesUtils;
import com.online.wangjialife.widget.selectcity.IndicatorModelData;
import com.online.wangjialife.widget.selectcity.IndicatorWidget;
import com.online.wangjialife.widget.selectcity.IndicatorWidget.OnIndicatorItemClickListener;
import com.online.wangjialife.widget.selectcity.ModelBusinessContact;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 
 * @项目名称:NuoTerFarm   
 * @本类名称:ChooseCityActivity   
 * @本类描述:城市切换的页面 
 * @创建作者:刘海涛  
 * @创建时间:2015-1-13 上午9:33:11     
 * @修改备注:
 * @version 
 *
 */
public class ChooseCityActivity extends BaseActivity implements OnClickListener,OnIndicatorItemClickListener{
	//loading页面
	private LinearLayout service_loading_layout;
	//没有数据页面
	private LinearLayout service_nodata_layout;
	//服务器异常页面
	private LinearLayout service_error_layout;
	//没有网络页面
	private LinearLayout service_nonetwork_layout;
	private LinearLayout mLinearLayout_IndicatorView;
	private IndicatorWidget			mIndicatorWidget;
	private String[] alphabetList = new String[]{"#","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private LinearLayout back_btn;//左上角的返回按钮
	private TextView title_info;//title的标题信息

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_city_layout);
		init();
		showLoadingProgressBar();
		//请求数据
//		new getDataAsyncTask().execute("");
	}

	
	private void init(){
		back_btn=(LinearLayout) this.findViewById(R.id.back_btn);
		back_btn.setVisibility(View.VISIBLE);
		back_btn.setOnClickListener(this);
		title_info=(TextView) this.findViewById(R.id.title_info);
		title_info.setText("城市选择");
		service_loading_layout=(LinearLayout) this.findViewById(R.id.service_loading_layout);
		service_nodata_layout=(LinearLayout) this.findViewById(R.id.service_nodata_layout);
		service_error_layout=(LinearLayout) this.findViewById(R.id.service_error_layout);
		service_nonetwork_layout=(LinearLayout) this.findViewById(R.id.service_nonetwork_layout);
		
		mLinearLayout_IndicatorView = (LinearLayout) this.findViewById(R.id.FragmentContactsByAlphabet_IndicatorView);
		mIndicatorWidget = new IndicatorWidget(this);
		mIndicatorWidget.setAlphabetList(alphabetList);
//		initLoading();
		
		List<ModelBusinessContact> mList=new ArrayList<ModelBusinessContact>();
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426","安庆", "1845415145"));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426","成都", "1845415145"));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "大庆", "1845415145"));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "红河", "1845415145"));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426","攀枝花", "1845415145"));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426","德阳", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "绵阳", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "驻马店","1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "信阳","1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "商丘", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "漯河", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "洛阳",  "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "菏泽", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "新乡", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EABfdf96FB3F13FF89B388426",  "南京", "18538117001" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "吴江", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "苏州","1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "常州","1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426","茂名", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "香港", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "澳门", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "郑州", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426", "酒泉", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "乌鲁木齐", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "广州","1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "北京","1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "上海", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "正阳", "1845415145" ));
		mList.add(new ModelBusinessContact("78B37EAdasd96FB3F13FF89B388426",  "单县", "1845415145" ));
		List<ModelBusinessContact> nList=new ArrayList<ModelBusinessContact>();
		for(int i=0;i<mList.size();i++){
			if("北京".equals(mList.get(i).getName())||"上海".equals(mList.get(i).getName())||"广州".equals(mList.get(i).getName())){
				nList.add(new ModelBusinessContact(mList.get(i).getId(), mList.get(i).getName(), "热门城市"));
				
			}else if("郑州".equals(mList.get(i).getName())){
//				nList.add(new ModelBusinessContact(mList.get(i).getId(), mList.get(i).getName(), "定位城市"));
				nList.add(new ModelBusinessContact("213123", MyApplication.getInstance().city, "定位城市"));
			}else{
				nList.add(new ModelBusinessContact(mList.get(i).getId(), mList.get(i).getName(), PinYinUtil.ToPinYinString(mList.get(i).getName())));
			}
		}

		//按照部门排序
		ComparatorPinYin comparator=new ComparatorPinYin();
		Collections.sort(nList,comparator);
		mIndicatorWidget.setData(nList);
		mIndicatorWidget.setOnIndicatorItemClickListener(ChooseCityActivity.this);
		mLinearLayout_IndicatorView.addView(mIndicatorWidget.create());
	}
	
//	public class getDataAsyncTask extends AsyncTask<String, String, InterfaceSenderResponse> {
//
//		@Override
//		protected void onPreExecute() {
////			showLoadingProgressBar();
//		}
//
//		@Override
//		protected InterfaceSenderResponse doInBackground(String... str) {
//			return new InterfaceAPI().getHomeCityHotList("indexCity");
//		}
//
//		@Override
//		protected void onPostExecute(InterfaceSenderResponse result) {
//			super.onPostExecute(result);
////			closeLoadingProgressBar();
//			try {
//				if(InterfaceSenderResponse.RESPONSE_SUCCESS==result.getStatus()){
//					//请求成功的处理
//					Gson gson=new Gson();
//					JSONObject jo;
//					jo=new JSONObject(result.getResponse());
//					if("T".equals(jo.getString("ISRIGHT"))){
//						initSuccess();
//						List<HomeCityListModel> ptCityList=gson.fromJson(jo.getString("CITYLIST"),  new TypeToken<List<HomeCityListModel>>(){}.getType());
//						List<HomeCityListModel> hotCityList=gson.fromJson(jo.getString("HOTCITYLIST"),  new TypeToken<List<HomeCityListModel>>(){}.getType());
//						List<ModelBusinessContact> nList=new ArrayList<ModelBusinessContact>();
//						//普通城市
//						for(int i=0;i<ptCityList.size();i++){
//							nList.add(new ModelBusinessContact(ptCityList.get(i).getCITY_ID(), ptCityList.get(i).getCITY_NAME(), PinYinUtil.ToPinYinString(ptCityList.get(i).getCITY_NAME())));
//						}
//						//热门城市
//						for(int i=0;i<hotCityList.size();i++){
//							nList.add(new ModelBusinessContact(hotCityList.get(i).getCITY_ID(), hotCityList.get(i).getCITY_NAME(), "热门城市"));
//						}
//						//定位
//						nList.add(new ModelBusinessContact("0001", "郑州市", "定位城市"));
//						
//						//按照部门排序
//						ComparatorPinYin comparator=new ComparatorPinYin();
//						Collections.sort(nList,comparator);
//						mIndicatorWidget.setData(nList);
//						mIndicatorWidget.setOnIndicatorItemClickListener(ChooseCityActivity.this);
//						mLinearLayout_IndicatorView.addView(mIndicatorWidget.create());
//					}else{
//						initServiceError();
//					}
//				}else if(InterfaceSenderResponse.RESPONSE_NETERROR==result.getStatus()){
//					//网络异常
//					initNoNetWork();
//				}else{
//					//请求失败的处理
//					initServiceError();
//				}
//			} catch (Exception e) {
//				//异常情况
//				initServiceError();
//			}
//		}
//	}
	
	@Override
	public void onIndicatorItemClick(int pPosition,
			IndicatorModelData pModelData) {
		ModelBusinessContact model= (ModelBusinessContact) pModelData;
		Log.i("MSG", "--"+model.getName()+"---");
		SharedPreferencesUtils.saveStringValue(Contects.switchCity, model.getName());
		finish();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.back_btn:
			finish();
			break;

		default:
			break;
		}
		
	}
	
	//loading框显示
	private void initLoading(){
		mLinearLayout_IndicatorView.setVisibility(View.GONE);
		service_loading_layout.setVisibility(View.VISIBLE);
		service_nodata_layout.setVisibility(View.GONE);
		service_error_layout.setVisibility(View.GONE);
		service_nonetwork_layout.setVisibility(View.GONE);
	}
	//请求服务器成功
	private void initSuccess(){
		mLinearLayout_IndicatorView.setVisibility(View.VISIBLE);
		service_loading_layout.setVisibility(View.GONE);
		service_nodata_layout.setVisibility(View.GONE);
		service_error_layout.setVisibility(View.GONE);
		service_nonetwork_layout.setVisibility(View.GONE);
	}
	//没有数据显示
	private void initNoData(){
		mLinearLayout_IndicatorView.setVisibility(View.GONE);
		service_loading_layout.setVisibility(View.GONE);
		service_nodata_layout.setVisibility(View.VISIBLE);
		service_error_layout.setVisibility(View.GONE);
		service_nonetwork_layout.setVisibility(View.GONE);
	}
	//服务器异常显示
	private void initServiceError(){
		mLinearLayout_IndicatorView.setVisibility(View.GONE);
		service_loading_layout.setVisibility(View.GONE);
		service_nodata_layout.setVisibility(View.GONE);
		service_error_layout.setVisibility(View.VISIBLE);
		service_nonetwork_layout.setVisibility(View.GONE);
	}
	//没有网络显示
	private void initNoNetWork(){
		mLinearLayout_IndicatorView.setVisibility(View.GONE);
		service_loading_layout.setVisibility(View.GONE);
		service_nodata_layout.setVisibility(View.GONE);
		service_error_layout.setVisibility(View.GONE);
		service_nonetwork_layout.setVisibility(View.VISIBLE);
	}
}
