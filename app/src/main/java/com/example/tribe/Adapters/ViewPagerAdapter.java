package com.example.tribe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tribe.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int onBoardingImages[] = {

            R.drawable.onboarding_icon1,
            R.drawable.onboarding_icon2,
            R.drawable.onboarding_icon3,
            R.drawable.onboarding_icon4,


    };

    int headings[] = {

            R.string.make_profiles,
            R.string.create_events,
            R.string.store_memories,
            R.string.join_other_tribes,

    };

    int descriptions[] = {

            R.string.make_profile_of,
            R.string.create_events_to_organize,
            R.string.by_fridge_now_you,
            R.string.connected_with_other_tribes,

    };

    public ViewPagerAdapter(Context context){

        this.context = context;

    }


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView sliderTitleImage = (ImageView) view.findViewById(R.id.image_onBoarding);
        TextView sliderHeading = (TextView) view.findViewById(R.id.text_title_onBoarding);
        TextView sliderDescription = (TextView) view.findViewById(R.id.text_overview_onBoarding);

        sliderTitleImage.setImageResource(onBoardingImages[position]);
        sliderHeading.setText(headings[position]);
        sliderDescription.setText(descriptions[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
