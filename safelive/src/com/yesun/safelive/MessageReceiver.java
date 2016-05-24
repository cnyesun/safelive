package com.yesun.safelive;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;
import com.yesun.safelive.util.HttpClient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 接收信息
 * @author chunmingdeng
 */
public class MessageReceiver extends BroadcastReceiver {

	private static final String TAG = "MessageRecevier";  
	
	
	@Override
	public void onReceive(final Context context, Intent intent) {
		
		Log.d(TAG,"onReceive");
		//也可以通过PhoneStateListener来监听电话
		if (intent.getAction().equals("android.intent.action.PHONE_STATE")) { //android.intent.action.PHONE_STATE
			final String tel = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER); 
			Log.d(TAG, "收到电话："+tel);
			
			if(tel == null) {
				return;
			}
			
			//TODO 判断tel是否为广告骚扰
			boolean isAd = true;
			
			//在手机界面提示广告 TODO
			if(isAd) {
				
				Thread thread = new Thread(){
					public void run() {
						request(tel, context);
					}
				};
				thread.start();
				
				
				
			}
			//存入数据库 TODO
		}
	}
	
	private void request(String tel, Context context) {
		
		HttpClient httpclient = new HttpClient();
		try {
			String html = httpclient.get("https://www.baidu.com/s?wd="+tel);
			//“用户标记为”关键字
			if(html.indexOf("广告") > -1 
					|| html.indexOf("诈骗") > -1 
					|| html.indexOf("骚扰") > -1 
					|| tel.equals("01056603272")){
				//挂断电话 TODO 
				TelephonyManager telMag = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);  
		        Class<TelephonyManager> c = TelephonyManager.class;  
		        Method mthEndCall = null;  
		        try {  
		            mthEndCall = c.getDeclaredMethod("getITelephony", (Class[]) null);  
		            mthEndCall.setAccessible(true);  
		            ITelephony iTel = (ITelephony) mthEndCall.invoke(telMag, (Object[]) null);  
		            iTel.endCall();  
		            Log.d(TAG, "骚扰电话，已挂断："+iTel.toString());  
		        } catch (Exception e) {
		            e.printStackTrace();  
		            Log.d(TAG, e.getMessage());
		        }
			}else {
				Log.d(TAG, "正常电话:" + tel);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
