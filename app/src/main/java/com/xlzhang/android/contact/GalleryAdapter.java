package com.xlzhang.android.contact;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
    private static final int IMAGE_SIZE = 200;
    private Context mContext;
    private int[] mImages;

    public GalleryAdapter(Context context, int[] images){
        mContext = context;
        mImages = images;
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
    public int getCount() {
        return mImages.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = new ImageView(mContext);

        ((ImageView) v).setImageResource(mImages[position]);
        v.setLayoutParams(new Gallery.LayoutParams(IMAGE_SIZE,IMAGE_SIZE));
        ((ImageView) v).setAdjustViewBounds(true);
        return v;
    }

}
