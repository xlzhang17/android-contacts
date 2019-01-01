package com.xlzhang.android.contact;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

@TargetApi(23)
public class TestActivity2 extends AppCompatActivity {
    private ArrayList<Contact> mContacts;   //获取模型对象
    private int[] mImages;

    private Gallery mGallery;  //管理顶部照片墙
    private VerticalViewPager mPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // 获取模型对象
        mContacts = ContactLab.get(this).getContacts();


        /*\ 展示照片墙*/
        mGallery = findViewById(R.id.contactGallery);
        mGallery.setAdapter(new GalleryAdapter(this, ContactLab.get(this).getImages()));
        mGallery.setSpacing(1);

        /*\ 展示卡片*/
        mPager = findViewById(R.id.contactDetail);
        FragmentManager fm = getSupportFragmentManager();
        mPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                return ContactFragment.newInstance(mContacts.get(i).getId());
            }

            @Override
            public int getCount() {
                return mContacts.size();
            }
        });

        /*\ 照片滚动 同步卡片切换*/
        mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* \TODO 卡片切换 同步照片切换*/
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mGallery.getAdapter().getItem(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
