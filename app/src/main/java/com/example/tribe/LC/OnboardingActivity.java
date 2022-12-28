package com.example.tribe.LC;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tribe.Adapters.ViewPagerAdapter;
import com.example.tribe.MainActivity;
import com.example.tribe.R;
import com.example.tribe.utils.Utils;

import at.markushi.ui.CircleButton;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager mSlideView;
    LinearLayout mDotLayout;
    Button skip_btn;
    CircleButton next_btn;
    Utils utils;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        skip_btn = findViewById(R.id.btn_skip);
        next_btn = findViewById(R.id.btn_next);
        utils=new Utils(this);



        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.SetShowOnboard(false);
                startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
                finish();

            }
        });


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(getitem(0) < 3){

                   mSlideView.setCurrentItem(getitem(1),true);
               }else{
                   utils.SetShowOnboard(false);
                   startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
                   finish();
               }


            }
        });

        mSlideView = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicatorLayout);

        viewPagerAdapter = new ViewPagerAdapter(this);

        mSlideView.setAdapter(viewPagerAdapter);

        setUpIndicator(0);
        mSlideView.addOnPageChangeListener(viewListener);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpIndicator(int position){

        dots = new TextView[4];
        mDotLayout.removeAllViews();

        for(int i=0 ;  i<dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {

            setUpIndicator(position);

            if (position > 0){

//                btn.setVisibility(View.VISIBLE);

            }else {

//                backbtn.setVisibility(View.INVISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return mSlideView.getCurrentItem() + i;
    }
}