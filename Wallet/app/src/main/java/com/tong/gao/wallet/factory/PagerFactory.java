package com.tong.gao.wallet.factory;

import java.util.LinkedHashMap;
import java.util.Map;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.tong.gao.wallet.bean.MyOrderBean;
import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.view.fragment.BaseFragment;
import com.tong.gao.wallet.view.fragment.MyOrderFragment;

public class PagerFactory {
	private static Map<Integer, BaseFragment>	mCaches	= new LinkedHashMap<Integer, BaseFragment>();

	public static BaseFragment getFragment(int position) {

		BaseFragment fragment = mCaches.get(position);

		// 判断缓存中是否有
		if (fragment != null) {
			LogUtils.d("读取缓存 : " + position);
			return fragment;
		}


		switch (position) {
			case 0:
				fragment = new MyOrderFragment();
				break;
			case 1:
				fragment = new MyOrderFragment();
				break;
			case 2:
				fragment = new MyOrderFragment();
				break;
			case 3:
				fragment = new MyOrderFragment();
				break;
			case 4:
				fragment = new MyOrderFragment();
				break;
			case 5:
				fragment = new MyOrderFragment();
				break;
		}

        Bundle bundle = new Bundle();
        bundle.putInt(MyConstant.MyOrderType,position);
        fragment.setArguments(bundle);
		// 存储到缓存
		mCaches.put(position, fragment);

		LogUtils.d("新建缓存 : " + position);

		return fragment;
	}
}
