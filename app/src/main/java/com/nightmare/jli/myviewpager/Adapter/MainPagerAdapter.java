package com.nightmare.jli.myviewpager.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightmare.jli.myviewpager.CycleCarouselViewPager;
import com.nightmare.jli.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J.Li on 2016/6/10.
 */
public class MainPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mInflater;

    /**
     * 分页内容View链表
     */
    private List<View> contents;

    public CycleCarouselPagerAdapter cycleCarouselPagerAdapter;
    public CarouselPagerAdapter carouselPagerAdapter;

    public MainPagerAdapter(Context tempContext){
        mContext=tempContext;
        mInflater=LayoutInflater.from(mContext);
        contents=setPagerContent();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = contents.get(position);
        if (position == 0) {
            CycleCarouselViewPager cycleCarouselViewPager =(CycleCarouselViewPager)view.findViewById(R.id.viewPager_CycleCarousel);
            cycleCarouselPagerAdapter =new CycleCarouselPagerAdapter(mContext, cycleCarouselViewPager);
            cycleCarouselViewPager.setAdapter(cycleCarouselPagerAdapter);
            cycleCarouselViewPager.setCurrentItem(1);
            cycleCarouselPagerAdapter.StartCarouselPager();
            cycleCarouselPagerAdapter.notifyDataSetChanged();

            ViewPager carouselViewPager =(ViewPager)view.findViewById(R.id.viewPager_Carousel);
            carouselPagerAdapter=new CarouselPagerAdapter(mContext, carouselViewPager);
            carouselViewPager.setAdapter(carouselPagerAdapter);
            carouselPagerAdapter.StartCarouselPager();
            carouselPagerAdapter.notifyDataSetChanged();

        }else if(position==1){

        }
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
    private List<View> setPagerContent() {
        List<View> contents = new ArrayList<>();

        View view;

        view = mInflater.inflate(R.layout.viewpager_main, null);
        contents.add(view);

        view = mInflater.inflate(R.layout.viewpager_second, null);
        contents.add(view);

        return contents;
    }


}
