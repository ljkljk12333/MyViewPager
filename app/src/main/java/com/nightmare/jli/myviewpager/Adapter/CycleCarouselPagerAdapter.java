package com.nightmare.jli.myviewpager.Adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nightmare.jli.myviewpager.CycleCarouselViewPager;
import com.nightmare.jli.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J.Li on 2016/6/10.
 */
public class CycleCarouselPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mInflater;

    CycleCarouselViewPager mCycleCarouselViewPager;

    /**
     * 分页内容View链表
     */
    private List<View> contents;

    private final int realCount=3;

    private static final int DISPLAY_TIME = 2000;

    private MyThread myThread;
    private Handler mHandler;

    public CycleCarouselPagerAdapter(Context tempContext, CycleCarouselViewPager tempCycleCarouselViewPager){
        mContext=tempContext;
        mInflater=LayoutInflater.from(mContext);
        mCycleCarouselViewPager = tempCycleCarouselViewPager;
        mCycleCarouselViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action=event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN: {
                        StopCarouselPager();
                        break;
                    }

                    case MotionEvent.ACTION_UP: {
                        StartCarouselPager();
                        break;
                    }
                }

                return false;
            }
        });
        contents=setPagerContent(realCount);
        myThread=new MyThread();
        mHandler=new Handler();

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = contents.get(position);
        final TextView textView=(TextView) view.findViewById(R.id.textView_Carousel);
        if(position==0){
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.carouselColor3));
            textView.setText("循环轮播内容3");
        }else if(position==1){
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.carouselColor1));
            textView.setText("循环轮播内容1");
        }else if(position==2){
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.carouselColor2));
            textView.setText("循环轮播内容2");
        }else if(position==realCount){
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.carouselColor3));
            textView.setText("循环轮播内容3");
        }else if(position==realCount+1){
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.carouselColor1));
            textView.setText("循环轮播内容1");
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,textView.getText()+" Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(contents.get(position));
    }

    /**
     * 设置各分页内容
     *
     * @return 设置过布局后的各分页链表
     */
    private List<View> setPagerContent(int contentsCount) {
        List<View> contents = new ArrayList<>();

        for (int i=0;i<contentsCount+2;i++) {
            View view = mInflater.inflate(R.layout.viewpager_carousel, null);
            contents.add(view);
        }

        return contents;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        // 数量为1，不做position替换
        if (getCount() <= 1) {
            return;
        }

        int position = mCycleCarouselViewPager.getCurrentItem();
        // ViewPager的更新即将完成，替换position，以达到无限循环的效果
        if(position==0){
            mCycleCarouselViewPager.setCurrentItem(realCount,false);
        }else if(position>realCount){
            mCycleCarouselViewPager.setCurrentItem(1,false);
        }
        super.finishUpdate(container);
    }

    public void StartCarouselPager(){
        //先去除线程再添加，确保线程只执行一次
        mHandler.removeCallbacks(myThread);
        mHandler.postDelayed(myThread, DISPLAY_TIME);
    }

    public void StopCarouselPager(){
        mHandler.removeCallbacks(myThread);
    }

    private class MyThread implements Runnable{

        @Override
        public void run() {
            if(getCount()>1){
                if(mCycleCarouselViewPager.getCurrentItem()==realCount){
                    mCycleCarouselViewPager.setCurrentItem(1);
                }else {
                    mCycleCarouselViewPager.setCurrentItem(mCycleCarouselViewPager.getCurrentItem()+1);
                }
                mHandler.postDelayed(myThread, DISPLAY_TIME);
            }
        }
    }
}
