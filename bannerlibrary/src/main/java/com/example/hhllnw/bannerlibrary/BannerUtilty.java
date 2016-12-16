package com.example.hhllnw.bannerlibrary;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by hhl on 2016/12/16.
 */

public class BannerUtilty {

    public static int getScreenHeight(Context context) {
        int screenHeight = 0;
        if (screenHeight <= 0) {
            try {
                DisplayMetrics dm = new DisplayMetrics();
                dm = context.getResources().getDisplayMetrics();
                screenHeight = dm.heightPixels;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return screenHeight;
    }

    /**
     * 获取ActionBar高度
     *
     * @param context activity
     * @return ActionBar高度
     */

    public static int getActionBarHeight(Context context) {

        TypedValue tv = new TypedValue();

        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {

            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());

        }

        return 0;

    }
}
