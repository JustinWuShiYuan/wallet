package com.tong.gao.wallet.utils;

import android.os.Handler;
import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Zero on 2015/7/21.
 */
public class TimeUtils {
    private static final String defaultFormat ="mm:ss";

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }


    public static String transferLongToDate(Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

}
