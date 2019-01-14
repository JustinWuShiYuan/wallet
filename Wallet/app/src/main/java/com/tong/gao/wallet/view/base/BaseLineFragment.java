package com.tong.gao.wallet.view.base;

import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 */
public class BaseLineFragment extends Fragment {

    protected float[][] points = new float[][]{{0,10},{1,10}, {2,47}, {3,11}, {4,38}, {5,9},{6,52}, {7,14}, {8,37}, {9,29}, {10,31},
            {11,100}, {12,147}, {13,111}, {14,318}, {15,119},{16,152}, {17,141}, {18,307}, {19,209}, {20,131},
            {21,100}, {22,147}, {23,111}, {24,318}, {25,119},{26,152}, {27,141}, {28,307}, {29,209}, {30,131},
            {31,100}, {32,147}, {33,111}, {34,318}, {35,119},{36,152}, {37,141}, {38,307}, {39,209}, {40,131},
            {41,100}, {42,147}, {43,111}, {44,318}, {45,119},{46,152}, {47,141}, {48,307}, {49,209}, {40,131},

            {51,10}, {52,47}, {53,11}, {54,38}, {55,9},{56,52}, {57,14}, {58,37}, {59,29}, {60,31},
            {61,100}, {62,147}, {63,111}, {64,318}, {65,119},{66,152}, {67,141}, {68,307}, {69,209}, {70,131},
            {71,100}, {72,147}, {73,111}, {74,318}, {75,119},{76,152}, {77,141}, {78,307}, {79,209}, {80,131},
            {81,100}, {82,147}, {83,111}, {84,318}, {85,119},{86,152}, {87,141}, {88,307}, {89,209}, {90,131},
            {91,100}, {92,147}, {93,111}, {94,318}, {95,119},{96,152}, {97,141}, {98,307}, {99,209}
    };
    protected float[][] points2 = new float[][]{{1,52}, {2,13}, {3,51}, {4,20}, {5,19},{6,20}, {7,54}, {8,7}, {9,19}, {10,41}};
    protected int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    protected float pxTodp(float value){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        float valueDP= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,metrics);
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,metrics);
    }
}
