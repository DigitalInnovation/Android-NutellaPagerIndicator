package com.mandsdigital.pongpager.sample;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mandsdigital.pongpager.PongPagerIndicator;


public class SampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        int[] logos = {R.drawable.nougat_logo, R.drawable.marshmallow_logo, R.drawable.lollipop_logo, R.drawable.kitkat_logo};

        // Single item in viewpager - shows nothing.
        // int[] logos = {R.drawable.nougat_logo};

        SampleAdapter adapter = new SampleAdapter(logos);
        viewPager.setAdapter(adapter);

        PongPagerIndicator configuredIndicator = (PongPagerIndicator) findViewById(R.id.configured_indicator);
        PongPagerIndicator defaultIndicator = (PongPagerIndicator) findViewById(R.id.default_indicator);
        viewPager.setCurrentItem(1);
        configuredIndicator.initWithViewPager(viewPager);
        defaultIndicator.initWithViewPager(viewPager);

        defaultIndicator.removeViewPager();
    }

    class SampleAdapter extends PagerAdapter {

        private int[] imageResources;

        public SampleAdapter(int[] imageResources){
            this.imageResources = imageResources;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpager_item, container, false);
            ImageView image = (ImageView) itemView.findViewById(R.id.image);
            Glide.with(container.getContext()).load(imageResources[position]).centerCrop().into(image);
            container.addView(itemView);
            return itemView;
        }

        @Override
        public int getCount() {
            return imageResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            object = null;
        }
    }
}
