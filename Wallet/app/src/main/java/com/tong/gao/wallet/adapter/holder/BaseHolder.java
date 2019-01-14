package com.tong.gao.wallet.adapter.holder;


import android.view.View;

/**
 */
public abstract class BaseHolder<T>
{
	protected View	mRootView;
	protected T		mData;

	public BaseHolder() {
		mRootView = initView();

		// 打标记
		mRootView.setTag(this);
	}

	protected abstract View initView();

	protected abstract void refreshUI(T data);

	public void setData(T data) {
		this.mData = data;
		// UI刷新
		refreshUI(mData);
	}

	public View getRootView()
	{
		return mRootView;
	}
}
