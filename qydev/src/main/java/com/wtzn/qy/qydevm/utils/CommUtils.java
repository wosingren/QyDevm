package com.wtzn.qy.qydevm.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2017/8/9.
 */
public class CommUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
