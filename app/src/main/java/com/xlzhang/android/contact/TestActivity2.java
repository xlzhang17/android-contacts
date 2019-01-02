package com.xlzhang.android.contact;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;


@TargetApi(26)
public class TestActivity2 extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    private static final String IMAGE_DIR = "/Users/xlzhang/AndroidStudioProjects/Contact-2/app/src/main/res/drawable/avatars/";
    private ArrayList<Contact> mContacts;   //获取模型对象
    private String[] mImages;
//    private int[] mImages;

    private Gallery mGallery;  //管理顶部照片墙
    private VerticalViewPager mPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        /*\ 获取模型对象*/
        mContacts = ContactLab.get(this).getContacts();
        mImages = ContactLab.get(this).getImages();

        /*\ 展示照片墙*/
        mGallery = findViewById(R.id.contactGallery);
        mGallery.setSpacing(1);
        mGallery.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mImages.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = new ImageView(mGallery.getContext());
                try {
                    /*\加载图片 */
                    InputStream in = getAssets().open(mImages[position]);
                    Drawable d = Drawable.createFromStream(in, null);
                    ((ImageView) v).setImageDrawable(d);
                } catch (Exception e) {
                    Log.e(TAG, "Exception:", e);
                }
                return v;
            }
        });

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
            private View preSelectedView;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*\ 被选择的照片高亮*/
                if (preSelectedView != null)
                    preSelectedView.setBackgroundResource(android.R.color.transparent);
                preSelectedView = view;
                view.setPadding(10, 10, 10, 10);
                view.setBackgroundResource(R.drawable.ic_launcher_background);

                mPager.setCurrentItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* \ 卡片切换 同步照片切换*/
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mGallery.setSelection(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
