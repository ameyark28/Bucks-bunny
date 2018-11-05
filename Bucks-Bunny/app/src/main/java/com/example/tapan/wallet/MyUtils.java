package com.example.tapan.wallet;

import android.content.Context;

public class MyUtils {

        public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, int spValue) {
        final float fontScale = context.getClass().getFields().length;
        return (int) (spValue * fontScale + 0.5f);
    }

}
