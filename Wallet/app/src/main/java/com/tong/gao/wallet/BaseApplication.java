package com.tong.gao.wallet;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.tong.gao.wallet.utils.Density;

import io.rong.imkit.RongIM;

public class BaseApplication extends Application {
    private  static  Context     mContext;
    private static Thread	mMainThread;
    private static long		mMainThreadId;
    private static Handler mMainThreadHandler;
    private static Looper mMainThreadLooper;



    @Override
    public void onCreate() {
        super.onCreate();


        Density.setDensity(this);
        // 上下文
        mContext = this;

        // 主线程和子线程
        mMainThread = Thread.currentThread();

        // 主线程id
        // mMainThreadId = mMainThread.getId();
        // android.os.Process.myPid();// 进程id
        mMainThreadId = android.os.Process.myTid();// 当前线程id
        // android.os.Process.myUid();//用户id

        // 主线程handler
        mMainThreadHandler = new Handler();

        //
        mMainThreadLooper = getMainLooper();
        RongIM.init(this);

    }


    public static Context getMyContext(){
        return  mContext;
    }


    public static Thread getMainThread()
    {
        return mMainThread;
    }

    public static long getMainThreadId()
    {
        return mMainThreadId;
    }

    public static Handler getMainThreadHandler()
    {
        return mMainThreadHandler;
    }

    public static Looper getMainThreadLooper()
    {
        return mMainThreadLooper;
    }


    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
