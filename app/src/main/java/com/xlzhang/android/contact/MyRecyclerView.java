package com.xlzhang.android.contact;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/*\modified 继承RecyclerView类 */
@TargetApi(23)
public class MyRecyclerView extends RecyclerView{
    private OnScrollChangeListener mListener;
    public MyRecyclerView(Context c){
        super(c);
    }
    public MyRecyclerView(Context c, AttributeSet attr){
        super(c, attr);
    }

    @Override
    public void scrollTo(int x, int y) {
//        super.scrollTo(x, y);
    }

}
