package com.tong.gao.wallet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tong.gao.wallet.utils.LogUtils;

public class DragView extends android.support.v7.widget.AppCompatImageView {
    private Context mcontext;
    private int startX,startY;
    private int mWidth,mHeight;

    public DragView(Context context) {
        this(context,null);
    }

    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            mWidth = specSize;
            LogUtils.d("xxx", "mWidth:"+mWidth);
        }else{
            int desireByImg = getPaddingLeft() + getPaddingRight() + getMeasuredWidth();
            if (specMode == MeasureSpec.AT_MOST) {
                mWidth = Math.min(desireByImg, specSize);
            }
        }

        int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int specSizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (specModeHeight == MeasureSpec.EXACTLY){
            mHeight = specSize;
        } else{
            int desire = getPaddingTop() + getPaddingBottom() + getMeasuredHeight();
            if (specModeHeight == MeasureSpec.AT_MOST) {
                mHeight = Math.min(desire, specSizeHeight);
            }
            LogUtils.d("xxx", "mHeight:"+mHeight);
        }
        LogUtils.d("xxx", "mWidth:"+mWidth+"  mHeight:"+mHeight);
        setMeasuredDimension(mWidth,mHeight);
    }







    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                LogUtils.d("xxx", "startX:"+startX+"  startY:"+startY);
                break;


            case MotionEvent.ACTION_MOVE:
                float newX = event.getRawX();
                float newY = event.getRawY();
                LogUtils.d("xxx", "newX:"+newX+"  newY:"+newY);

                if(Math.abs(newX - startX) > 10 ||  Math.abs(newY-startY) > 10){
                    layout(startX,startY,(int)newX,startY);
                    startX = (int) newX;
                    startY = (int) newY;
                    LogUtils.d("xxx", "startX:"+startX+"  startY:"+startY);
                }

                break;


            case MotionEvent.ACTION_UP:


                break;

        }

        return true;
    }
}
