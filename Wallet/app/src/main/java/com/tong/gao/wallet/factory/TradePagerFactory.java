package com.tong.gao.wallet.factory;

import android.os.Bundle;

import com.tong.gao.wallet.constants.MyConstant;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.view.fragment.BaseFragment;
import com.tong.gao.wallet.view.fragment.MyOrderFragment;
import com.tong.gao.wallet.view.fragment.MyTradeFragment;

import java.util.LinkedHashMap;
import java.util.Map;

public class TradePagerFactory {
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
				fragment = new MyTradeFragment();
				break;
			case 1:
				fragment = new MyTradeFragment();
				break;
			case 2:
				fragment = new MyTradeFragment();
				break;
			case 3:
				fragment = new MyTradeFragment();
				break;
		}

        Bundle bundle = new Bundle();
        bundle.putInt(MyConstant.MyOrderType,position);
		bundle.putString(MyConstant.Empty_ViewType,MyConstant.Empty_ViewTypeOf_Trade);
        fragment.setArguments(bundle);
		// 存储到缓存
		mCaches.put(position, fragment);

		LogUtils.d("新建缓存 : " + position);

		return fragment;
	}
}
