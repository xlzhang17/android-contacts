package com.xlzhang.android.contact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VerticalViewPager extends ViewPager{
    public VerticalViewPager(Context c){
        super(c);
        init();
    }
    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
    private class VerticalPageTransformer implements PageTransformer{
        @Override
        public void transformPage(@NonNull View view, float v) {
            if(v < -1){
                view.setAlpha(0);
            }
            else if(v < 1){
                view.setAlpha(1);
                view.setTranslationX(view.getWidth() * -v);
                float yPosition = v * view.getHeight();
                view.setTranslationY(yPosition);
            }
            else {
                view.setAlpha(0);
            }
        }
    }
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXY(ev));
    }

}