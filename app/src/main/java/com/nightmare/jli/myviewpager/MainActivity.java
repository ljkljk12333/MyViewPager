package com.nightmare.jli.myviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nightmare.jli.myviewpager.Adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MyViewPager";

    ViewPager viewPager_Main;
    MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager_Main=(ViewPager)findViewById(R.id.viewPager_Main);
        mainPagerAdapter=new MainPagerAdapter(MainActivity.this);

        viewPager_Main.setAdapter(mainPagerAdapter);
        viewPager_Main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    mainPagerAdapter.cycleCarouselPagerAdapter.StartCarouselPager();
                    mainPagerAdapter.carouselPagerAdapter.StartCarouselPager();
                }
                Log.i(TAG, "onPageSelected: "+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
