import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class TestActivity extends AppCompatActivity {

    private HorizontalScrollView mHorizontalScrollView;
    private LinearLayout mLinearLayout;
    private int mScreenWidth;
    private ArrayList<Contact> mContacts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        mContacts = ContactLab.get(this).getContacts();
        initView();
        initEvent();
        VerticalViewPager pager = findViewById(R.id.contactDetail);

        FragmentManager fm = getSupportFragmentManager();
        pager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Contact contact = mContacts.get(i);
                return ContactFragment.newInstance(contact.getId());
            }

            @Override
            public int getCount() {
                return mContacts.size();
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//        Gallery gallery = findViewById(R.id.photo_gallery);
//        gallery.setSpacing(1);
//        GalleryAdapter adapter = new GalleryAdapter(this);
//        gallery.setAdapter(adapter);
//
//        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mPhotoView.setImageResource(adapter.mPhotoIds[position]);
//                ViewPager pager = findViewById(R.id.contactDetail);
//                pager.setCurrentItem(position);
//            }
//        });
    }
    private void initView() {
        mHorizontalScrollView = findViewById(R.id.contactScrollView);
        mLinearLayout= findViewById(R.id.contactGallery);
        for (int i = 0; i < mContacts.size(); i++){
            View v = new ImageView(this);
            ((ImageView) v).setImageResource(mContacts.get(i).getImage());
            mLinearLayout.addView(v);
        }
        mHorizontalScrollView.getChildAt(0).setSelected(true);
    }

    private void initEvent() {
        int childCount = mHorizontalScrollView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int currentIdx = i;
            final View currentView = mHorizontalScrollView.getChildAt(i);
            currentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WindowManager wm1 = getWindowManager();
                    int screenWidth = wm1.getDefaultDisplay().getWidth();
                    int rb_px = (int) v.getX() + v.getWidth() / 2;
                    mHorizontalScrollView.scrollTo(rb_px - screenWidth / 2, 0);
                }
            });
        }
    }
    private void selectedChildView(int idx){
        for (int i = 0; i < mHorizontalScrollView.getChildCount(); i++)
            mHorizontalScrollView.getChildAt(i).setSelected(true);
        View v = mHorizontalScrollView.getChildAt(idx);
        int left = v.getLeft();
        int width = v.getMeasuredWidth();
        int scrollDist = left - (mScreenWidth / 2 - width / 2);
        mHorizontalScrollView.smoothScrollBy(scrollDist, 0);
    }

    private class GalleryAdapter extends ArrayAdapter<Contact>{
        private Context mContext;
        public GalleryAdapter(Context c){
            super(c, 0, mContacts);
            mContext = c;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = new ImageView(mContext);
            ((ImageView)convertView).setImageResource(mContacts.get(position).getImage());
            return convertView;
        }
    }
}
