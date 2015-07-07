package com.online.wangjialife.widget.selectcity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.online.wangjialife.R;



import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.RelativeLayout.LayoutParams;

public class IndicatorWidget {
	private Context mContext;
	//加载IndicatorWidget视图View
	private View root;
	//Indicator包含listView
	private ListView mList;
	//Indicator侧栏滑动
	private LinearLayout mBar;
	//Indicator条目点击监听
	private OnIndicatorItemClickListener mOnIndicatorItemClickListener;
	//Indicator条目长点击监听
	private OnIndicatorItemLongClickListener mIndicatorItemLongClickListener;
	//Indicator数据适配器
	private IndicatorAdapter mAdapter;
	//Indicator  Key值索引
	private Map<String, Integer> mDataIndexByKey;
	//Indicator  
	private String[] mDataIndexByIndex;
	private List<IndicatorModelData> mDataHeader = new ArrayList<IndicatorModelData>();
	private List<IndicatorModelData> mDataList;
	private String[] alphabetList;
	private Map<String, TextView> alphabetTextViewMap;
	
	public IndicatorWidget(Context pContext) {
		this.mContext = pContext;
	}
	
	@SuppressWarnings("rawtypes")
	public void setData(List pDataList){
		if(pDataList!=null){
			this.mDataList = pDataList;
			this.mDataList.addAll(0, mDataHeader);
		}
	}
	
	public void addHeader(IndicatorModelData pHeaderData){
		mDataHeader.add(0, pHeaderData);
	}
	
	public void setOnIndicatorItemClickListener(OnIndicatorItemClickListener listener){
		this.mOnIndicatorItemClickListener = listener;
	}
	
	public void setOnIndicatorItemLongClickListener(OnIndicatorItemLongClickListener listener){
		this.mIndicatorItemLongClickListener=listener;
	}
	public IndicatorAdapter getAdapter(){
		return this.mAdapter;
	}
	
	public View create(){
		root = LayoutInflater.from(this.mContext).inflate(R.layout.widget_indicator, null);
		mList = (ListView) root.findViewById(R.id.list);
		mBar = (LinearLayout) root.findViewById(R.id.bar);
		TextView mTextView_NoData = (TextView) root.findViewById(R.id.TextView_NoData);
		mAdapter = new IndicatorAdapter(this.mContext);
		mAdapter.setList(mDataList);
		mList.setAdapter(mAdapter);
		if(mDataList!=null&&mDataList.size()!=0){
			//1.显示数据
			mList.setEmptyView(mTextView_NoData);//ListView 没有数据时提示
			//2.整理对象列表索引
			mDataIndexByKey = new HashMap<String, Integer>();
			mDataIndexByIndex = new String[mDataList.size()];
			//3.提取所有字母
			for(int i=0;i<mDataList.size();i++){
				IndicatorModelData data = mDataList.get(i);
				if(!mDataIndexByKey.containsKey(data.getKeyName())){
					mDataIndexByKey.put(data.getKeyName(), i);
				}
				mDataIndexByIndex[i] = data.getKeyName();
			}
			//判断当数据数量不足10个时，不显示字母表
			if(mDataList.size()>10){
				mBar.setVisibility(View.VISIBLE);
				//4.根据字母表，创建字母表控件L
				createAlphabetItem();
				//5.监听ListView滚动事件
				setListViewScrollListener();
				//6.添加字母表点击事件监听
				setAlpabetListener();
			}else{
				mBar.setVisibility(View.GONE);
			}
			//7.添加item点击事件
			if(this.mOnIndicatorItemClickListener!=null){
				mList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						IndicatorModelData mModelData = mAdapter.getItem(position);
						mOnIndicatorItemClickListener.onIndicatorItemClick(position, mModelData);
					}
				});
			}
			if(mIndicatorItemLongClickListener!=null){
				mList.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						IndicatorModelData mModelData = mAdapter.getItem(position);
						mIndicatorItemLongClickListener.onIndicatorItemLongClick(position, mModelData);
						return false;
					}
				});
			}
		}
		return root;
	}
	
	/**
	 * 列表滚动监听
	 */
	private void setListViewScrollListener(){
		this.mList.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				String section = mDataIndexByIndex[firstVisibleItem];
				//控制字母表中当前字母变颜色
				changeAlphabetColor(section);
			}
		});
	}
	
	public void setAlphabetList(String[] pAlphabetList){
		this.alphabetList = pAlphabetList;
	}
	
	/**
	 * 创建字母表
	 */
	private void createAlphabetItem() {
		LinearLayout.LayoutParams alphabetItemParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		alphabetItemParams.weight=1;
		alphabetTextViewMap = new HashMap<String, TextView>();
		for (String section : alphabetList) {
			TextView tTextView = new TextView(this.mContext);
			tTextView.setLayoutParams(alphabetItemParams);
			tTextView.setTextColor(this.mContext.getResources().getColor(R.color.common_navigation_letters_color));
			tTextView.setGravity(Gravity.CENTER_HORIZONTAL);
			tTextView.setText(section);
			alphabetTextViewMap.put(section, tTextView);
			mBar.addView(tTextView);
		}
	}
	
	/**
	 * 设置字母表上的触摸事件，根据当前触摸的位置结合字母表的高度，计算出当前触摸在哪个字母上。
	 * 当手指按在字母表上时，展示弹出式分组。手指离开字母表时，将弹出式分组隐藏。
	 */
	private void setAlpabetListener() {
		final int alphabetCount = alphabetList.length;
		mBar.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				float alphabetHeight = mBar.getHeight();
				float y = event.getY();
				int sectionPosition = (int) ((y / alphabetHeight) / (1f / alphabetCount));
				if (sectionPosition < 0) {
					sectionPosition = 0;
				} else if (sectionPosition > alphabetCount-1) {
					sectionPosition = alphabetCount-1;
				}
				String sectionLetter = String.valueOf(alphabetList[sectionPosition]);
				if(mDataIndexByKey.containsKey(sectionLetter)){
					int position = mDataIndexByKey.get(sectionLetter);
					mList.setSelectionFromTop(position + mList.getHeaderViewsCount(), 0);
					//改变当前选中字母的颜色
					changeAlphabetColor(sectionLetter);
				}else{
					
					for (int Loop = sectionPosition-1; Loop >=0; Loop--) {
						String sectionLetternext = String.valueOf(alphabetList[Loop]);
						if(mDataIndexByKey.containsKey(sectionLetternext)){
							int position = mDataIndexByKey.get(sectionLetternext);
							mList.setSelectionFromTop(position + mList.getHeaderViewsCount(), 0);
							//改变当前选中字母的颜色
							changeAlphabetColor(sectionLetter);
							break;
						}
					}
				}
				return true;
			}
		});
	}
	
	private void changeAlphabetColor(String sectionLetter){
		for(String sectionItem : alphabetTextViewMap.keySet()){
			if(sectionItem.equals(sectionLetter)){
				alphabetTextViewMap.get(sectionItem).setTextColor(this.mContext.getResources().getColor(R.color.holo_red_dark));
			}else{
				alphabetTextViewMap.get(sectionItem).setTextColor(this.mContext.getResources().getColor(android.R.color.darker_gray));
			}
		}
	}
	
	public interface OnIndicatorItemClickListener{
		public void onIndicatorItemClick(int pPosition, IndicatorModelData pModelData);
	}
	public interface OnIndicatorItemLongClickListener{
		public void onIndicatorItemLongClick(int pPosition, IndicatorModelData pModelData);
	};
	
	
}