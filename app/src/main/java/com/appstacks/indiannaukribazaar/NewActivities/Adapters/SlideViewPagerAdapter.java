package com.appstacks.indiannaukribazaar.NewActivities.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.appstacks.indiannaukribazaar.R;

public class SlideViewPagerAdapter extends PagerAdapter {

    Context ctx;

    public SlideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen, container, false);

        ImageView imageView;


        imageView = view.findViewById(R.id.welcome_logo);
        TextView textView = view.findViewById(R.id.des1);

        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.ic_product);
                break;
            case 1:
                imageView.setImageResource(R.drawable.ic_product_teardown_pana);
                textView.setText(R.string.info2);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_product_teardown_amico);
                textView.setText(R.string.info3);
                break;

        }


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {


        container.removeView((View) object);

    }
}
