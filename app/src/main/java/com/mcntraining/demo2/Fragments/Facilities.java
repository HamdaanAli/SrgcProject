package com.mcntraining.demo2.Fragments;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mcntraining.demo2.R;

import static com.mcntraining.demo2.R.color.textTabBright;
import static com.mcntraining.demo2.R.color.textTabLight;

public class Facilities extends AppCompatActivity {

    TextView hostel,library,loan;
    ViewPager mMainPager;
    private PagerViewAdapter maPgerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);
        hostel=findViewById(R.id.hostel);
        library=findViewById(R.id.library);
        loan=findViewById(R.id.eduloan);
        mMainPager=(ViewPager) findViewById(R.id.mainPager);
        maPgerAdapter=new PagerViewAdapter(getSupportFragmentManager());
        mMainPager.setAdapter(maPgerAdapter);

        hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainPager.setCurrentItem(0);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainPager.setCurrentItem(1);
            }
        });
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainPager.setCurrentItem(2);
            }
        });

        mMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 changeTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void changeTabs(int position) {
        if(position==0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                hostel.setTextColor(getColor(textTabBright));
            }
            hostel.setTextSize(30);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                library.setTextColor(getColor(textTabLight));
            }
            library.setTextSize(16);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                loan.setTextColor(getColor(textTabLight));
            }
            loan.setTextSize(16);
        }
        if(position==1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                hostel.setTextColor(getColor(textTabLight));
            }
            hostel.setTextSize(16);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                library.setTextColor(getColor(textTabBright));
            }
            library.setTextSize(30);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                loan.setTextColor(getColor(textTabLight));
            }
            loan.setTextSize(16);
        }
        if(position==2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                hostel.setTextColor(getColor(textTabLight));
            }
            hostel.setTextSize(16);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                library.setTextColor(getColor(textTabLight));
            }
            library.setTextSize(16);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                loan.setTextColor(getColor(textTabBright));
            }
            loan.setTextSize(30);
        }
    }
}
