package com.tong.gao.wallet.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment{

	// 1. 加载数据
	// 2. 加载数据为空
	// 3. 加载失败
	// 4. 加载成功显示

	private LoadingPager	mPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// 共通点：
		// 1. 加载数据
		// 2. 加载数据为空
		// 3. 加载失败

		// 不同点：
		// 4. 加载成功显示

		// 只有一个能显示
		// 通过一个容器，来装载所有的View，拿到数据,通过数据判断来选中显示哪一个

		// 做逻辑处理，判断是否改显示哪一个View

		if (mPager == null) {
			mPager = new LoadingPager(UIUtils.getContext()) {

				@Override
				protected View onCreateSuccessView() {
					return onSuccessView();
				}

				@Override
				protected LoadedResult onStartLoadData() {
					return onLoadData();
				}

				//空白页面 执行创建新广告 或者其他的逻辑
				@Override
				protected void emptyViewExecuteTask() {
					executeEmptyTask();
				}
			};

		} else {
			ViewParent parent = mPager.getParent();
			if (parent != null && parent instanceof ViewGroup) {
				((ViewGroup) parent).removeView(mPager);
			}
		}

		return mPager;
	}

	// 拿数据的行为
	public void loadData() {
		if (mPager != null) {
			mPager.loadData();
		}
	}

	public LoadingPager getmPager() {
		return mPager;
	}

	protected abstract View onSuccessView();

	protected abstract LoadingPager.LoadedResult onLoadData();

	protected abstract void executeEmptyTask();



}

