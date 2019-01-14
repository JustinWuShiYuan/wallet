package com.tong.gao.wallet.utils;


import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.tong.gao.wallet.BaseApplication;

public class UIUtils
{

	/**
	 * 上下文的获取
	 * 
	 * @return
	 */
	public static Context getContext()
	{
		return BaseApplication.getMyContext();
	}

	/**
	 * 获取资源
	 * 
	 * @return
	 */
	public static Resources getResources()
	{
		return getContext().getResources();
	}

	public static long getMainThreadId()
	{
		return BaseApplication.getMainThreadId();
	}

	public static Handler getMainThreadHandler()
	{
		return BaseApplication.getMainThreadHandler();
	}

	/**
	 * 主线程中执行 任务
	 * 
	 * @param task
	 */
	public static void runOnUiThread(Runnable task) {
		long currentThreadId = android.os.Process.myTid();
		long mainThreadId = getMainThreadId();

		if (currentThreadId == mainThreadId) {
			// 如果在主线程中执行
			task.run();
		} else {
			// 需要转的主线程执行
			getMainThreadHandler().post(task);
		}
	}

	/**
	 * 
	 * @param dip
	 * @return
	 */
	public static int dip2px(int dip) {
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (int) (dip * density + 0.5f);
	}

	public static float dip2px(float dip) {
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (float) (dip * density + 0.5f);
	}

	public static int px2dip(int px) {
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (int) (px / density + 0.5f);
	}

	public static String getString(int resId)
	{
		return getResources().getString(resId);
	}

	public static String getPackageName()
	{
		return getContext().getPackageName();
	}

	public static String[] getStringArray(int resId)
	{
		return getResources().getStringArray(resId);
	}

	public static int getColor(int resId)
	{
		return getResources().getColor(resId);
	}
}
