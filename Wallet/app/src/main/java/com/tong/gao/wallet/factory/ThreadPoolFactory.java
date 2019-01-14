package com.tong.gao.wallet.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolFactory {
    private static ExecutorService executorService = null;
    private static ScheduledExecutorService scheduledThreadPool = null;


    private static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(6, 50,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    public static ExecutorService getExecutorService() {
        if(null ==executorService ){
            executorService = newCachedThreadPool();
        }
        return executorService;
    }

    public static ScheduledExecutorService getScheduledExecutor(){
        if(null == scheduledThreadPool){
            scheduledThreadPool = Executors.newScheduledThreadPool(5);
        }
        return scheduledThreadPool;
    }




}
