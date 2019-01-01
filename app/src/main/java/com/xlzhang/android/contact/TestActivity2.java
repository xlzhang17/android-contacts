package com.xlzhang.android.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TestActivity2 extends AppCompatActivity {
    private ArrayList<Contact> mContacts;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // 获取模型对象
        mContacts = ContactLab.get(this).getContacts();

        // 用滚动列表展示所有模型对象中的图片
        RecyclerView recyclerView = findViewById(R.id.contactGallery);
        recyclerView.setAdapter(new RecyclerViewAdapter(recyclerView, mContacts));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);


    }

}
