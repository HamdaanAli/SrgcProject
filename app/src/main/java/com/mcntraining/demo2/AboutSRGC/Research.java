package com.mcntraining.demo2.AboutSRGC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.mcntraining.demo2.R;

public class Research extends AppCompatActivity {
    Button aktu,ccsu;
    String aktuurl="http://www.srgcmzn.com/Seminars.aspx",ccsuurl="https://onlinecourses.nptel.ac.in/explorer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        aktu=(Button) findViewById(R.id.AKTU);
        ccsu=(Button) findViewById(R.id.CCSU);
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


        aktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 view.startAnimation(buttonClick);
                Intent intent=new Intent(getApplicationContext(),ResearchWebview.class);
                intent.putExtra("URL",aktuurl);
                startActivity(intent);



            }
        });

        ccsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent intent=new Intent(getApplicationContext(),ResearchWebview.class);
                intent.putExtra("URL",ccsuurl);
                startActivity(intent);
            }
        });
    }
    }

