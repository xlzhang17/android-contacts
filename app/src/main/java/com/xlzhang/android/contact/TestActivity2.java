package com.xlzhang.android.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TestActivity2 extends AppCompatActivity {
    private ArrayList<Contact> mContacts;
    private View prevView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // 获取模型对象
        mContacts = ContactLab.get(this).getContacts();

        // 用滚动列表展示所有模型对象中的图片
        RecyclerView recyclerView = findViewById(R.id.contactGallery);
        recyclerView.setAdapter(new RecyclerViewAdapter(recyclerView, mContacts));
        //需要LinearLayoutManager 保证recyclerView正常显示
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
        // 滑动保持某一项居中
        final LinearSnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(recyclerView);
        // 保持居中项突出显示
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(prevView != null)
                    prevView.setBackgroundResource(android.R.color.transparent);
//                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                View[] views = llm.findFirstCompletelyVisibleItemPosition()
                View view = helper.findSnapView(llm);
                view.setPadding(10, 10, 10, 10);
                view.setBackgroundResource(R.drawable.ic_launcher_background);
                prevView = view;
//                ViewGroup.LayoutParams params = view.getLayoutParams();
//                params.width = 150;
//                view.setLayoutParams(params);
//                view.invalidate();
//                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

    }

}
