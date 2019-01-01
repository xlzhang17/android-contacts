package com.xlzhang.android.contact;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{
    private ArrayList<Contact> mContacts;
    private View parentView;

    public RecyclerViewAdapter(View view, ArrayList<Contact> contacts){
        mContacts = contacts;
        parentView = view;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        View mImageView;
        public RecyclerViewHolder(View itemView){
            super(itemView);
            mImageView = itemView;
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerViewHolder holder = new RecyclerViewHolder(new ImageView(viewGroup.getContext()));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
        holder.mImageView.setLayoutParams(layoutParams);
//        final View vf = holder.mImageView;
//        vf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DisplayMetrics display = v.getContext().ge.getDefaultDisplay();
//                int screenWidth = display.getWidth();
//                int rb_px = (int) v.getLeft() + v.getWidth() / 2;
//                parentView.scrollBy(rb_px - screenWidth / 2, 0);
//            }
//        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        Contact contact = mContacts.get(i);
        ((ImageView)recyclerViewHolder.mImageView).setImageResource(contact.getImage());
        if(i == 0){

        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
