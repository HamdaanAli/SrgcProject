package com.mcntraining.demo2.AboutSRGC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.mcntraining.demo2.R;

public class AboutSRGC extends AppCompatActivity {

    Button skip,next;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_srgc);
        toolbar=(Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("ABOUT SRGC");
        toolbar.setTitleMarginStart(30);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next=(Button) findViewById(R.id.btn_next);
        skip=(Button) findViewById(R.id.btn_skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onClick(View view) {
        if(view == next)
        startActivity(new Intent(this,Chairman_Desk.class));

    }

}
