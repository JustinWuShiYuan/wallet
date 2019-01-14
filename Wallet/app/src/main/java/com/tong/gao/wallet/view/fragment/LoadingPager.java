package com.tong.gao.wallet.view.fragment;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.factory.ThreadPoolFactory;
import com.tong.gao.wallet.utils.UIUtils;

public abstract class LoadingPager extends FrameLayout implements OnClickListener {

	// 共通点：
	// 1. 加载数据
	// 2. 加载数据为空
	// 3. 加载失败

	// 不同点：
	// 4. 加载成功显示

	private static final int	STATE_NONE		= -1;			// 无状态
	private static final int	STATE_LOADING	= 0;			// 加载中的状态
	private static final int	STATE_EMPTY		= 1;			// 空的状态
	private static final int	STATE_ERROR		= 2;			// 错误的状态
	private static final int	STATE_SUCCESS	= 3;			// 成功的状态

	private int					mCurrentState	= STATE_NONE;	// 用来标记当前属于什么状态，就显示什么View

	private View				mLoadingView;					// 正在加载中的View
	private View				mEmptyView;					// 数据为空的View
	private View				mErrorView;					// 错误页面的View
	private View				mSuccessView;					// 成功的view

	private View				mBtnRetry;

	private ImageView           ivEmpty;                    //空白页面 显示的图片
	private RelativeLayout      rlCreateAdvertiseContainer; //空白页面 显示的创建广告
	private TextView			tvCreateAdvertise;			//空白页面下的 创建广告按钮

	public LoadingPager(Context context) {
		this(context,null);
	}

	public LoadingPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		// 添加相同的View

		// 1. 加载数据
		if (mLoadingView == null) {
			// 初始化
			mLoadingView = View.inflate(getContext(), R.layout.pager_loading, null);
			addView(mLoadingView);
		}

		// 2. 加载数据为空
		if (mEmptyView == null) {
			// 初始化
			mEmptyView = View.inflate(getContext(), R.layout.pager_empty, null);
			addView(mEmptyView);

            ivEmpty = mEmptyView.findViewById(R.id.iv_empty);

            rlCreateAdvertiseContainer = mEmptyView.findViewById(R.id.rl_create_advertise_container);
            tvCreateAdvertise = mEmptyView.findViewById(R.id.tv_create_trade_advertise);
            tvCreateAdvertise.setOnClickListener(this);
		}
		// 3. 加载失败
		if (mErrorView == null) {
			// 初始化
			mErrorView = View.inflate(getContext(), R.layout.pager_error, null);
			addView(mErrorView);

			mBtnRetry = mErrorView.findViewById(R.id.error_btn_retry);
			mBtnRetry.setOnClickListener(this);
		}

		// 根据状态显示view
		safeUpdateUIStyle();
	}

	private void safeUpdateUIStyle() {
		UIUtils.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				updateUIStyle();
			}
		});
	}

	private void updateUIStyle() {

		// 1.loading
		if (mLoadingView != null) {
			mLoadingView.setVisibility(mCurrentState == STATE_LOADING || mCurrentState == STATE_NONE ? View.VISIBLE : View.GONE);
		}

		// 2. empty
		if (mEmptyView != null) {
			mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);
		}

		// 3. error
		if (mErrorView != null) {
			mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
		}

		// 4. 成功
		if (mSuccessView == null && mCurrentState == STATE_SUCCESS) {
			// 初始化View
			mSuccessView = onCreateSuccessView();
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			// add
			if(null != mSuccessView && null != mSuccessView.getParent()){
				((ViewGroup)mSuccessView.getParent()).removeView(mSuccessView);
			}
			addView(mSuccessView, params);
		}

		if (mSuccessView != null) {
			mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
		}
	}

	/**
	 * 加载数据的方法
	 */
	public void loadData() {
		if (mCurrentState == STATE_EMPTY || mCurrentState == STATE_ERROR || mCurrentState == STATE_NONE) {
			mCurrentState = STATE_LOADING;

			ThreadPoolFactory.getExecutorService().execute(new LoadDataTask());
		}
		safeUpdateUIStyle();
	}

	protected abstract View onCreateSuccessView();

	protected abstract LoadedResult onStartLoadData();

	protected abstract void emptyViewExecuteTask();

	class LoadDataTask implements Runnable {

		@Override
		public void run() {
			// 获取数据
			LoadedResult result = onStartLoadData();
			// 根据结果得到状态值
			mCurrentState = result.getState();
			// UI改变
			safeUpdateUIStyle();
		}
	}

	public enum LoadedResult {
		EMPTY(STATE_EMPTY), ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS);

		int	state;

		LoadedResult(int state) {
			this.state = state;
		}

		public int getState()
		{
			return state;
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()){
			case R.id.error_btn_retry:
				 loadData();
				break;

			case R.id.tv_create_trade_advertise:
				emptyViewExecuteTask();
				break;
		}
//		if (v == mBtnRetry) {
//			loadData();
//		}
	}

    public ImageView getIvEmpty() {
        return ivEmpty;
    }

    public RelativeLayout getRlCreateAdvertiseContainer() {
        return rlCreateAdvertiseContainer;
    }
}
