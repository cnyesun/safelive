package com.yesun.safelive.log;

import java.text.MessageFormat;

/**
 * MobileIn Log.java
 * Description: 封装Log，支持传入不定参数
 * 1.0 YESUN 2013年12月4日 下午2:36:11 Create.
 * ChangeLog:
 */
public class Log {
	
	private static boolean debug = true;
	
	/**
	 * Description: 初始化
	 * 1.0 YESUN 2013年12月5日 上午9:31:45 Create.
	 * ChangeLog:
	 */
	public static void init() {
		debug = true;
		//TODO write to log file
	}
	
	
	
	/**
	 * Description: 支持不定参数的debug
	 * @param message，支持{0}{1}{2}表达式，参数从0开始
	 * @param params
	 * 1.0 YESUN 2013年11月28日 上午10:33:52 Create.
	 * ChangeLog:
	 */
	public static void debug(String tag, String message, Object... arguments) {
		//实现不定参数的填充
		message = format(message, arguments);
		debug(tag, message);
	}
	
	/**
	 * Description:
	 * @param tag
	 * @param message
	 * 1.0 YESUN 2013年12月5日 上午9:35:13 Create.
	 * ChangeLog:
	 */
	public static void debug(String tag,String message) {
		doDebug(tag, message, null);
	}
	
	/**
	 * Description:
	 * @param tag
	 * @param message
	 * @param t
	 * 1.0 YESUN 2013年12月5日 上午9:35:15 Create.
	 * ChangeLog:
	 */
	public static void doDebug(String tag,String message, Throwable t) {
		
		if(message == null) {
			Log.debug("ERROR", "message is null!");
			return;
		}

		if(debug) {
			if(t != null) {
				android.util.Log.d(tag, message, t);	
			}
			else {
				android.util.Log.d(tag, message);
			}
		}
	}
	
	
	
	/**
	 * Description: 支持不定参数的info
	 * @param message
	 * @param params
	 * 1.0 YESUN 2013年11月28日 上午10:41:33 Create.
	 * ChangeLog:
	 */
	public static void info(String tag, String message, Object... arguments) {
		//实现不定参数的填充
		message = format(message, arguments);
		info(tag, message);
	}
	
	public static void info(String tag, String message) {
		doInfo(tag, message, null);
	}
	
	public static void doInfo(String tag, String message, Throwable t) {
		if(debug){
			if(t != null) {
				android.util.Log.i(tag, message, t);
			}
			else {
				android.util.Log.i(tag, message);
			}
		}
	}
	
	/**
	 * Description:支持不定参数的error
	 * @param message
	 * @param params
	 * Copyright (c) Digital Heaven. All Rights Reserved.
	 * 1.0 YESUN 2013年11月28日 上午10:41:43 Create.
	 * ChangeLog:
	 */
	public static void error(String tag, String message, Object... arguments) {
		//实现不定参数的填充
		message = format(message, arguments);
		error(tag, message);
	}
	
	public static void error(String tag, String message) {
		doError(tag, message, null);
	}
	
	public static void doError(String tag, String message, Throwable t) {
		if(debug) {
			if(t != null) {
				android.util.Log.e(tag, message, t);
			}
			else{
				android.util.Log.e(tag, message);
			}
		}
	}
	
	/**
	 * Description: 支持不定参数的warn
	 * @param message
	 * @param params
	 * 1.0 YESUN 2013年11月28日 上午10:41:55 Create.
	 * ChangeLog:
	 */
	public static void warn(String tag,String message, Object... arguments) {
		//实现不定参数的填充
		message = format(message, arguments);
		warn(tag, message);
	}
	
	public static void warn(String tag,String message) {
		doWarn(tag, message, null);
	}
	
	public static void doWarn(String tag,String message, Throwable t) {
		if(debug) {
			if(t != null) {
				android.util.Log.w(tag, message, t);
			}
			else{
				android.util.Log.w(tag, message);
			}
		}
	}
	
	
	/**
	 * Description:
	 * @param tag
	 * @param message
	 * @param arguments
	 * 1.0 YESUN 2013年12月5日 上午10:40:44 Create.
	 * ChangeLog:
	 */
	public static void verbos(String tag,String message, Object... arguments) {
		//实现不定参数的填充
		message = format(message, arguments);
		warn(tag, message);
	}
	
	public static void verbos(String tag,String message) {
		doWarn(tag, message, null);
	}
	
	public static void doVerbos(String tag,String message, Throwable t) {
		if(debug) {
			if(t != null) {
				android.util.Log.v(tag, message, t);
			}
			else{
				android.util.Log.v(tag, message);
			}
		}
	}
	
	/**
	 * Description: 利用MessageFormat格式化
	 * @param message
	 * @param params
	 * @return
	 * 1.0 YESUN 2013年11月28日 上午10:42:01 Create.
	 * ChangeLog:
	 */
	private static String format(String pattern, Object... arguments) {
		pattern = MessageFormat.format(pattern, arguments);
		return pattern;
	}
	
}
