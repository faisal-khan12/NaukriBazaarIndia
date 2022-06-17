package com.appstacks.indiannaukribazaar.NewActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appstacks.indiannaukribazaar.NewActivities.Adapters.SlideViewPagerAdapter;
import com.appstacks.indiannaukribazaar.R;
import com.appstacks.indiannaukribazaar.databinding.ActivityBoardingBinding;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class BoardingActivity extends AppCompatActivity {

    ActivityBoardingBinding binding;
    ViewPager viewPager;
    SlideViewPagerAdapter adapter;
    DotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager = findViewById(R.id.viewpager);
        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);

        adapter = new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        visblebtn();
                        break;
                    case 1:
                        visblebtn();
                        break;
                    case 2:
                        binding.button.setVisibility(View.INVISIBLE);
                        binding.button2.setVisibility(View.VISIBLE);
                        binding.button2.setOnClickListener(v -> {
                            Intent intent = new Intent(BoardingActivity.this, RegistrationActivity.class);
                            startActivity(intent);
//                            finishAffinity();
                        });
                        break;
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });


    }

    public void visblebtn() {
        binding.button.setVisibility(View.VISIBLE);
        binding.button2.setVisibility(View.INVISIBLE);
    }

}