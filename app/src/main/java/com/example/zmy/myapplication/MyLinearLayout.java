package com.example.zmy.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * TODO: document your custom view class.
 */
public class MyLinearLayout extends LinearLayout {

    private Context mContext;
    private int mTouchSlop;

    public MyLinearLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        mContext = context;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (getChildCount() >= 3) {
            View view = getChildAt(1);
            int[] position = new int[2];
            view.getLocationInWindow(position);
            int dp = dip2px(mContext, 200) + getStatusBarHeight();
            Log.e("zmy", "top "+position[1] +"   dp="+dp+" view = "+view);
            if (position[1] <= dp && position[1] != getStatusBarHeight()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    float initX = 0 , initY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zmy", "onTouchEvent DOWN initX = "+initX+" initY = "+initY);
                initX = x;
                initY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float diffY = y - initY;
                float diffX = x - initX;
                Log.e("zmy", "ACTION_MOVE y = "+y + "  initY = "+ initY + "  diffY = "+diffY);
                if (Math.abs(diffY) > Math.abs(diffX)) {
                    View view = getChildAt(1);
                    int[] position = new int[2];
                    view.getLocationInWindow(position);
                    int dp = dip2px(mContext, 200) +getStatusBarHeight();
                    if (position[1] + diffY > getStatusBarHeight() || position[1] + diffY <= dp) {
//                        Log.e("zmy", "move top = "+ position[1]);
//                        ViewCompat.animate(this)
//                                .translationY(diffY)
//                                .setDuration(0)
//                                .start();

                    } else {
//                        onInterceptTouchEvent(event);
                    }
                }
                initY = y;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zmy", "onTouchEvent ACTION_UP");
                break;
        }
        return true;
    }
}
