package com.xlzhang.android.contact;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.*;

@TargetApi(23)
public class TestActivity2 extends AppCompatActivity {
    private ArrayList<Contact> mContacts;

    private MyRecyclerView mRecyclerView;
    private LinearSnapHelper mHelper;
    private VerticalViewPager mVerticalViewPager;

    private View prevView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // 获取模型对象
        mContacts = ContactLab.get(this).getContacts();

        // 用滚动列表展示所有模型对象中的图片
        mRecyclerView = findViewById(R.id.contactGallery);
        mRecyclerView.setAdapter(new RecyclerViewAdapter(mRecyclerView, mContacts));
        //需要LinearLayoutManager 保证recyclerView正常显示
//        final CustomLayoutManager llm = new CustomLayoutManager(this, recyclerView.getWidth(), 100);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(llm);
        // 滑动保持某一项居中
        mHelper = new LinearSnapHelper();
        mHelper.attachToRecyclerView(mRecyclerView);
        /*\ TODO 点击项居中*/

        // 保持居中项突出显示
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(prevView != null)
                    prevView.setBackgroundResource(android.R.color.transparent);
                View view = mHelper.findSnapView(llm);
//                // 获取 居中 view 的position
//                int position = recyclerView.getChildAdapterPosition(view);

                view.setPadding(10, 10, 10, 10);
                view.setBackgroundResource(R.drawable.ic_launcher_background);
                prevView = view;
            }
        });

        // 显示viewPager
        mVerticalViewPager = findViewById(R.id.contactDetail);
        FragmentManager fm = getSupportFragmentManager();
        mVerticalViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                return ContactFragment.newInstance(mContacts.get(i).getId());
            }

            @Override
            public int getCount() {
                return mContacts.size();
            }
        });
        /* \TODO vertivalViewPager 添加监听事件 */
        mVerticalViewPager.setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int scale = 100 / mVerticalViewPager.getHeight();
                mRecyclerView.scrollBy((int)((float)scrollY - oldScrollY) * scale, 0);
            }
        });
        /* \TODO recyclerView 添加监听事件 */
//        mRecyclerView.addOnScrollListener(this);
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int scale = mVerticalViewPager.getHeight() / 100;
                mVerticalViewPager.scrollBy(0, dx * scale);
            }
        });

    }

//    @Override
//    public void onPageScrolled(int i, float v, int i1) {
//        int scale = mVerticalViewPager.getHeight() / 100;
//        mVerticalViewPager.scrollBy((int)(scale * (float)(i1) / scale), 0);
//    }

    /* \TODO 主activity实现监听器接口 */
//    @Override
//    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        int scale = mVerticalViewPager.getHeight() / 100;
//        if(v == mRecyclerView){
//            mVerticalViewPager.scrollBy(0,(int)(scale * (float)(scrollX - oldScrollX)));
//        }
//        else {
//            mRecyclerView.scrollBy((int)((float)(scrollY - oldScrollY) / scale), 0);
//        }
//    }
    //    public class CustomLayoutManager extends LinearLayoutManager {
//        private int mParentWidth;
//        private int mItemWidth;
//
//        public CustomLayoutManager(Context context, int parentWidth, int itemWidth) {
//            super(context);
//            mParentWidth = parentWidth;
//            mItemWidth = itemWidth;
//        }
//
//        @Override
//        public int getPaddingLeft() {
//            return Math.round(mParentWidth / 2f - mItemWidth / 2f);
//        }
//
//        @Override
//        public int getPaddingRight() {
//            return getPaddingLeft();
//        }
//    }

}
