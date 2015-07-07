package com.online.wangjialife.control;

/*
 * Android-Universal-Image-Loader 图片异步加载类库的使用（超详细配置）
 * http://blog.csdn.net/vipzjyno1/article/details/23206387#
 * DisplayImageOptions options;  
 options = new DisplayImageOptions.Builder()  
 .showImageOnLoading(R.drawable.ic_launcher) //设置图片在下载期间显示的图片  
 .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片  
 .showImageOnFail(R.drawable.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
 .cacheInMemory(true)//设置下载的图片是否缓存在内存中  
 .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
 .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
 .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
 .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
 .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
 //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
 //设置图片加入缓存前，对bitmap进行设置  
 //.preProcessor(BitmapProcessor preProcessor)  
 .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
 .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
 .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
 .build();//构建完成  
 */

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 
 * @项目名称:512080671@qq.com
 * @本类名称:ApplicationExtend
 * @本类描述:保存在缓存中的数据信息
 * @创建作者:徐岩
 * @修改备注:
 * @version
 * 
 */

public class MyApplication extends Application {

	private static MyApplication mSingleton;
	private static List<WeakReference<Activity>> mActivityList = new ArrayList<WeakReference<Activity>>();
	public static Context mContext;
	// 屏幕宽度
	public static int width;
	// 屏幕高度
	public static int height;
	// 手机IMEI号
	public static String mIMEI = "";
	// 地理位置信息配置
	public String address = "";//详细地址
	public String province = "";//省
	public String city = "正在定位中...";//城市
//	public static String county = "";//县
	public double latitude = 0.0;//纬度
	public double longitude = 0.0;//经度
	public String cityCode = "";//城市的代号
	
	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;


	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		width = manager.getDefaultDisplay().getWidth();
		height = manager.getDefaultDisplay().getHeight();
		mSingleton = this;
		mActivityList = new ArrayList<WeakReference<Activity>>();
		
		initImageLoader(mContext);
		try {
			TelephonyManager _TelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			// 手机串号
			if (_TelephonyManager.getDeviceId() != null) {
				mIMEI = _TelephonyManager.getDeviceId();
			}
		} catch (Exception e) {
		}
		
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		option.setCoorType("gcj02");//返回的定位结果是百度经纬度，默认值gcj02
		int span=1000*60*60*24;
//		int span=1000*10;
		option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
		if(!mLocationClient.isStarted()){
	        mLocationClient.start();
	       }
	}

	public static MyApplication getInstance() {
		return mSingleton;
	}

	public void addActivity(Activity pActivity) {
		mActivityList.add(new WeakReference<Activity>(pActivity));
	}

	public void finishAllActivity() {
		for (WeakReference<Activity> _ActivityRef : mActivityList) {
			try {
				_ActivityRef.get().finish();
			} catch (NullPointerException e) {
				Log.e("Activity already destroyed.", e.toString());
			}
		}
	}

	public void getActivity(String activityName) {
		for (WeakReference<Activity> _ActivityRef : mActivityList) {
			try {
				_ActivityRef.toString();
			} catch (NullPointerException e) {
				Log.e("Activity already destroyed.", e.toString());
			}
		}
	}

	public static int dip2px(float dipValue) {
		final float scale = mContext.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 正则表达式 判断字符串是否由字母和数字组合
	 * 
	 * @return
	 */
	public boolean RegularExpression(String message) {
		message = message.replaceAll("[\\\\pP‘’“”.\\。?\\？\\，,\\！!]", "");
		Pattern pattern = Pattern.compile(("[a-zA-Z\\d]+"));
		Matcher matcher = pattern.matcher(message);
		// 当条件满足时，将返回true，否则返回false
		return matcher.matches();
	}

	public String Str_RegularExpression(String message) {
		message = message.replaceAll("[\\\\pP‘’“”.\\。?\\？\\，,\\！!]", "");
		return message;
	}

	// 判断输入的是否为手机号
	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern
				.compile("^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersion() {
		try {
			PackageManager manager = getInstance().getPackageManager();
			PackageInfo info = manager.getPackageInfo(getInstance()
					.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "暂无版本号";
		}
	}

	public static void initImageLoader(Context context) {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config2 = new ImageLoaderConfiguration.Builder(
				context).defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config2);

	}
	/**
	 * 解码 Unicode \\uXXXX
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeUnicode(String str) {
		Charset set = Charset.forName("UTF-16");
		Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
		Matcher m = p.matcher(str);
		int start = 0;
		int start2 = 0;
		StringBuffer sb = new StringBuffer();
		while (m.find(start)) {
			start2 = m.start();
			if (start2 > start) {
				String seg = str.substring(start, start2);
				sb.append(seg);
			}
			String code = m.group(1);
			int i = Integer.valueOf(code, 16);
			byte[] bb = new byte[4];
			bb[0] = (byte) ((i >> 8) & 0xFF);
			bb[1] = (byte) (i & 0xFF);
			ByteBuffer b = ByteBuffer.wrap(bb);
			sb.append(String.valueOf(set.decode(b)).trim());
			start = m.end();
		}
		start2 = str.length();
		if (start2 > start) {
			String seg = str.substring(start, start2);
			sb.append(seg);
		}
		return sb.toString();
	}
	
	/*
	 * 手机号中间4位换成*
	 */
	public static String change_Phone_To_Xing(String phone) {
		String s_phone = phone.substring(0,phone.length()-(phone.substring(3)).length())+"****"+phone.substring(7);
		return s_phone;
	}

	
	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
//			//Receive Location 
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//			if (location.getLocType() == BDLocation.TypeGpsLocation){
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//				sb.append("\ndirection : ");
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//				sb.append(location.getDirection());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//				//运营商信息
//				sb.append("\noperationers : ");
//				sb.append(location.getOperators());
//				sb.append("城市："+location.getCity());
//			}
			address=location.getAddrStr();
			province=location.getProvince();
			city=location.getCity();
			latitude=location.getLatitude();
			longitude=location.getLongitude();
			cityCode=location.getCityCode();
			Log.i("MSG", province+"-111-"+city);
//			Log.i("BaiduLocationApiDem", sb.toString());
		}


	}
	

}