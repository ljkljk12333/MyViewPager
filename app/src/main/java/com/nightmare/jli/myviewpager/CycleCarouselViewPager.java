package com.nightmare.jli.myviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by J.Li on 2016/6/10.
 */
public class CycleCarouselViewPager extends ViewPager {

//    float startX;

    public CycleCarouselViewPager(Context context) {
        super(context);
    }

    public CycleCarouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(ev);
    }


}
