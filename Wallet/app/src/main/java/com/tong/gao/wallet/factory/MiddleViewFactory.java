package com.tong.gao.wallet.factory;

import android.content.Context;
import android.view.View;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.constants.MyConstant;

import java.util.HashMap;
import java.util.Map;

public class MiddleViewFactory {
    private static Map<String ,View> viewMap = new HashMap<>();

    public static View getMiddleView(String key, Context context,int resourceId){
        View view = null;
        if(viewMap.get(key) == null){
//            switch (key){
//                case MyConstant.Middle_Flcontent_HomePager:
//                    view = View.inflate(context, R.layout.fragment_home_pager, null);
//                    break;
//                case MyConstant.Middle_Flcontent_Trade:
//
//                    break;
//                case MyConstant.Middle_Flcontent_Message:
//
//                    break;
//                case MyConstant.Middle_Flcontent_MyInfo:
//
//                    break;
//            }
            view = View.inflate(context, resourceId, null);
            viewMap.put(key,view);
        }

        return viewMap.get(key);
    }
}
