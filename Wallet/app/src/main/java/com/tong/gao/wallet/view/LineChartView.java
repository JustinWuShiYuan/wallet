package com.tong.gao.wallet.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tong.gao.wallet.R;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


public class LineChartView extends View {
    private final int monthCount;
    private int maxScore;
    private int minScore ;
    private int brokenLineColor ;
    private int textNormalColor ;
    private int textSize ;
    private int straightLineColor  ;
    private Path brokenPath;
    private Paint brokenPaint;
    private Paint straightPaint;
    private Paint dottedPaint;
    private Paint textPaint;
    private int brokenLineWith = 5;
    private int viewWith;
    private int viewHeight;
    private ArrayList<Point> scorePoints;
    private List<Integer> score = new ArrayList<>();
    private String[] monthText ={"1yue","2yue","3yue","4yue"};
    private int selectMonth =5;

    public LineChartView(Context context) {
        this(context,null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineChartView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChartView);
        maxScore = a.getInt(R.styleable.ChartView_max_score, 800);
        minScore = a.getInt(R.styleable.ChartView_min_score, 600);
        monthCount = a.getInt(R.styleable.ChartView_min_score, 7);
        brokenLineColor = a.getColor(R.styleable.ChartView_broken_line_color, brokenLineColor);
        textNormalColor = a.getColor(R.styleable.ChartView_textColor, textNormalColor);
        textSize = a.getDimensionPixelSize(R.styleable.ChartView_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                15, getResources().getDisplayMetrics()));
        straightLineColor = a.getColor(R.styleable.ChartView_dottedlineColor, straightLineColor);

        a.recycle();

        init();
    }

    private void init() {
        brokenPath = new Path();

        brokenPaint = new Paint();
        brokenPaint.setAntiAlias(true);
        brokenPaint.setStyle(Paint.Style.STROKE);
        brokenPaint.setStrokeWidth(UIUtils.dip2px(brokenLineWith));
        brokenPaint.setStrokeCap(Paint.Cap.ROUND);

        straightPaint = new Paint();
        straightPaint.setAntiAlias(true);
        straightPaint.setStyle(Paint.Style.STROKE);
        straightPaint.setStrokeWidth(brokenLineWith);
        straightPaint.setColor((straightLineColor));
        straightPaint.setStrokeCap(Paint.Cap.ROUND);

        dottedPaint = new Paint();
        dottedPaint.setAntiAlias(true);
        dottedPaint.setStyle(Paint.Style.STROKE);
        dottedPaint.setStrokeWidth(brokenLineWith);
        dottedPaint.setColor((straightLineColor));
        dottedPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor((textNormalColor));
        textPaint.setTextSize(textSize);


        for(int i =minScore ;i<maxScore;i++){
            score.add(minScore +1);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWith = w;
        viewHeight = h;
        initData();
    }

    private void initData() {
        scorePoints = new ArrayList<Point>();
        float maxScoreYCoordinate = viewHeight * 0.1f;
        float minScoreYCoordinate = viewHeight * 0.6f;

        LogUtils.d("LineCharView", "initData: " + maxScoreYCoordinate);

        float newWith = viewWith - (viewWith * 0.15f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        int coordinateX;

        for (int i = 0; i < score.size(); i++) {
            LogUtils.d("LineCharView",  "initData: " +score.get(i));
            Point point = new Point();
            coordinateX = (int) (newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.15f));//确定point的X坐标
            point.x = coordinateX;
            if (score.get(i) > maxScore) {
                score.set(i,maxScore);
//               score.get(i) = score;
            } else if (score.get(i) < minScore) {
//               score.get(i) = minScore;
                score.set(i,minScore);
            }
            point.y = (int) (((float) (maxScore -score.get(i)) / (maxScore - minScore)) * (minScoreYCoordinate - maxScoreYCoordinate) + maxScoreYCoordinate);////确定point的Y坐标
            scorePoints.add(point);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDottedLine(canvas, viewWith * 0.15f, viewHeight * 0.1f, viewWith, viewHeight * 0.1f);//上面一条虚线的画法，不懂看坐标系那一张图
        drawDottedLine(canvas, viewWith * 0.15f, viewHeight * 0.6f, viewWith, viewHeight * 0.6f);//下面一条虚线的画法
        drawText(canvas);//绘制文字，minScore，maxScore
        drawMonthLine(canvas);//月份的线及坐标点
        drawBrokenLine(canvas);//绘制折线，就是画点，moveto连接
        drawPoint(canvas);//绘制穿过折线的点
    }



    private void drawDottedLine(Canvas canvas, float startX, float startY, float stopX,
                                float stopY) {

        dottedPaint.setPathEffect(new DashPathEffect(new float[]{20, 10}, 4));//DashPathEffect如果不理解，看我上一篇文章
        dottedPaint.setStrokeWidth(1);
        // 实例化路径
        Path mPath = new Path();
        mPath.reset();
        // 定义路径的起点
        mPath.moveTo(startX, startY);
        mPath.lineTo(stopX, stopY);
        canvas.drawPath(mPath, dottedPaint);
    }

    private void drawText(Canvas canvas) {

        textPaint.setTextSize(textSize);//默认字体15
        textPaint.setColor(textNormalColor);

        canvas.drawText(String.valueOf(maxScore), viewWith * 0.1f - UIUtils.dip2px(10), viewHeight * 0.1f + textSize * 0.25f, textPaint);
        canvas.drawText(String.valueOf(minScore), viewWith * 0.1f - UIUtils.dip2px(10), viewHeight * 0.6f + textSize * 0.25f, textPaint);

        textPaint.setColor(0xff7c7c7c);

        float newWith = viewWith - (viewWith * 0.15f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        float coordinateX;//分隔线X坐标
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textNormalColor);
        textSize = (int) textPaint.getTextSize();
        for (int i = 0; i < monthText.length; i++) {//这里是绘制月份，从数组中取出来，一个个的写
            coordinateX = newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.15f);

            if (i == selectMonth - 1)//被选中的月份要单独画出来多几个圈圈
            {

                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(brokenLineColor);
                RectF r2 = new RectF();
                r2.left = coordinateX - textSize - UIUtils.dip2px(4);
                r2.top = viewHeight * 0.7f + UIUtils.dip2px(4) + textSize / 2;
                r2.right = coordinateX + textSize + UIUtils.dip2px(4);
                r2.bottom = viewHeight * 0.7f + UIUtils.dip2px(4) + textSize + UIUtils.dip2px(8);
                canvas.drawRoundRect(r2, 10, 10, textPaint);

            }
            //绘制月份
            canvas.drawText(monthText[i], coordinateX, viewHeight * 0.7f + UIUtils.dip2px(4) + textSize + UIUtils.dip2px(5), textPaint);//不是就正常的画出

            textPaint.setColor(textNormalColor);

        }


    }

    //绘制月份的直线(包括刻度)
    private void drawMonthLine(Canvas canvas) {

        straightPaint.setStrokeWidth(UIUtils.dip2px(1));
        canvas.drawLine(0, viewHeight * 0.7f, viewWith, viewHeight * 0.7f, straightPaint);

        float newWith = viewWith - (viewWith * 0.15f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        float coordinateX;//分隔线X坐标
        for (int i = 0; i < monthCount; i++) {
            coordinateX = newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.15f);
            canvas.drawLine(coordinateX, viewHeight * 0.7f, coordinateX, viewHeight * 0.7f + UIUtils.dip2px(4), straightPaint);
            //viewHeight * 0.7f + UIUtils.dip2px(4)这个方法就是坐标轴上的竖杠杠，你可以修改这里来修改竖条的长度
        }

    }


    //绘制折线
    private void drawBrokenLine(Canvas canvas) {
        brokenPath.reset();
        brokenPaint.setColor(brokenLineColor);
        brokenPaint.setStyle(Paint.Style.STROKE);
        if (score.size() == 0) {
            return;
        }
        LogUtils.d("LineChart", "drawBrokenLine: " + scorePoints.get(0));
        brokenPath.moveTo(scorePoints.get(0).x, scorePoints.get(0).y);
        for (int i = 0; i < scorePoints.size(); i++) {
            brokenPath.lineTo(scorePoints.get(i).x, scorePoints.get(i).y);
        }
        canvas.drawPath(brokenPath, brokenPaint);

    }


    protected void drawPoint(Canvas canvas) {

        if (scorePoints == null) {
            return;
        }
        brokenPaint.setStrokeWidth(UIUtils.dip2px(1));
        for (int i = 0; i < scorePoints.size(); i++) {
            brokenPaint.setColor(brokenLineColor);
            brokenPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, UIUtils.dip2px(3), brokenPaint);
            brokenPaint.setColor(Color.WHITE);
            brokenPaint.setStyle(Paint.Style.FILL);
            if (i == selectMonth - 1) {//默认选中的才会绘制不同的点，如图
                brokenPaint.setColor(0xffd0f3f2);
                canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, UIUtils.dip2px(8f), brokenPaint);
                brokenPaint.setColor(0xff81dddb);
                canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, UIUtils.dip2px(5f), brokenPaint);

                //绘制浮动文本背景框
                drawFloatTextBackground(canvas, scorePoints.get(i).x, scorePoints.get(i).y - UIUtils.dip2px(8));

                textPaint.setColor(0xffffffff);
                //绘制浮动文字
                canvas.drawText(String.valueOf(score.get(i)), scorePoints.get(i).x, scorePoints.get(i).y - UIUtils.dip2px(5f) - textSize, textPaint);
            }
            brokenPaint.setColor(0xffffffff);
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, UIUtils.dip2px(1.5f), brokenPaint);
            brokenPaint.setStyle(Paint.Style.STROKE);
            brokenPaint.setColor(brokenLineColor);
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, UIUtils.dip2px(2.5f), brokenPaint);
        }
    }


    //这个方法是利用path和point画出图形，并设置背景颜色
    private void drawFloatTextBackground(Canvas canvas, int x, int y) {
        brokenPath.reset();
        brokenPaint.setColor(brokenLineColor);
        brokenPaint.setStyle(Paint.Style.FILL);

        //P1
        Point point = new Point(x, y);
        brokenPath.moveTo(point.x, point.y);

        //P2
        point.x = point.x + UIUtils.dip2px(5);
        point.y = point.y - UIUtils.dip2px(5);
        brokenPath.lineTo(point.x, point.y);

        //P3
        point.x = point.x + UIUtils.dip2px(12);
        brokenPath.lineTo(point.x, point.y);

        //P4
        point.y = point.y - UIUtils.dip2px(17);
        brokenPath.lineTo(point.x, point.y);

        //P5
        point.x = point.x - UIUtils.dip2px(34);
        brokenPath.lineTo(point.x, point.y);

        //P6
        point.y = point.y + UIUtils.dip2px(17);
        brokenPath.lineTo(point.x, point.y);

        //P7
        point.x = point.x + UIUtils.dip2px(12);
        brokenPath.lineTo(point.x, point.y);

        //最后一个点连接到第一个点
        brokenPath.lineTo(x, y);

        canvas.drawPath(brokenPath, brokenPaint);

    }


}
