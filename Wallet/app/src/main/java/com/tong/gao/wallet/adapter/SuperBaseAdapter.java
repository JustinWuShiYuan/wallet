package com.tong.gao.wallet.adapter;

import java.util.List;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tong.gao.wallet.adapter.holder.BaseHolder;

/**
 */
public abstract class SuperBaseAdapter<T> extends BaseAdapter {
	protected List<T>	mDatas;

	public SuperBaseAdapter(List<T> datas) {
		this.mDatas = datas;
	}

	@Override
	public int getCount() {
		if (mDatas != null) { return mDatas.size(); }
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mDatas != null) { return mDatas.get(position); }
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder = null;
		if (convertView == null) {
			// 没有复用
			// 1. holder初始化
			holder = getHolder();
			// 2. 加载View
			convertView = holder.getRootView();
		} else {
			// 有复用
			holder = (BaseHolder) convertView.getTag();
		}

		// 设置数据,给View铺数据
		holder.setData(mDatas.get(position));

		return convertView;
	}

	protected abstract BaseHolder<T> getHolder();

}
