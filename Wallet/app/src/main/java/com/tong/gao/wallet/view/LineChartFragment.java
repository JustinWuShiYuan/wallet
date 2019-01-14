package com.tong.gao.wallet.view;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idtk.smallchart.chart.LineChart;
import com.idtk.smallchart.data.LineData;
import com.idtk.smallchart.interfaces.iData.ILineData;
import com.tong.gao.wallet.R;
import com.tong.gao.wallet.utils.LogUtils;
import com.tong.gao.wallet.view.base.BaseLineFragment;

import java.util.ArrayList;


/**
 * Created by Idtk on 2016/6/26.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 * 描述 :
 */
public class LineChartFragment extends BaseLineFragment {
    public static String TABLAYOUT_FRAGMENT = "tab_fragment";

    private LineData mLineData = new LineData();
    private ArrayList<ILineData> mDataList = new ArrayList<>();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();
    private int type;

    public static LineChartFragment newInstance(int type) {
        LineChartFragment fragment = new LineChartFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linechart,container,false);
        initData();
        LineChart lineChart = (LineChart) view.findViewById(R.id.lineChart);
        lineChart.setDataList(mDataList);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }

    }

    float temX =0,temY =0;
    float temX1 =0,temY1 =0;
    private void initData() {
        mPointArrayList.clear();
        switch (type) {
            case 1:
                for (int i = 0; i <= 7; i++) {
                    mPointArrayList.add(new PointF(points[i][0], points[i][1]));
                }
                break;
            case 2:
                temX =0;temY =0;
                for (int i = 0; i <= 30; i++) {
                    temX = points[i][0];
//                    LogUtils.d("temX:"+temX);
                    temY += points[i][1];
                    if(i !=0 && i% 5 == 0){
                        mPointArrayList.add(new PointF(temX, temY));
                        temX =0;
                        temY =0;
                    }

                }
                break;
            case 3:
                temX1 =0;temY1 =0;
                for (int i = 0; i <= 90; i++) {
                    temX1 = points[i][0];
//                    LogUtils.d("temX:"+temX);
                    temY1 += points[i][1];
                    if(i% 10 == 0){
                        LogUtils.d("temX:"+temX);
//                        temX +=15;
                        mPointArrayList.add(new PointF(temX1, temY1));
                        temX1 =0;
                        temY1 =0;
                    }
                }
                break;
        }

        mLineData.setValue(mPointArrayList);
        mLineData.setColor(Color.parseColor("#587BFC"));
        mLineData.setPaintWidth(pxTodp(4));
        mLineData.setTextSize(pxTodp(7));
        mDataList.add(mLineData);
    }
}
