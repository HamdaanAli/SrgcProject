package com.mcntraining.demo2.AboutSRGC;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mcntraining.demo2.R;

public class CourseActivity extends AppCompatActivity {
  TextView t,mba,t1,mca,t2,me,t3,mjmc,t4,bed,t5,bsc,t6,bcom,t7,bca,t8,bba,t9,barch,t10,Mcom,t11,MSc;
    private boolean hasBeenClicked = false;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        toolbar=(Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("SRGC COURCES");

        t=findViewById(R.id.text);
        t1=findViewById(R.id.text1);
        t2=findViewById(R.id.text2);
        t3=findViewById(R.id.text3);
        t4=findViewById(R.id.bed);
        t5=findViewById(R.id.bsc);
        t6=findViewById(R.id.bco);
        t7=findViewById(R.id.bc);
        t8=findViewById(R.id.bba);
        t9=findViewById(R.id.bach);
        t10=findViewById(R.id.mco);
        t11=findViewById(R.id.ms);
        mba=findViewById(R.id.mba);
        mca=findViewById(R.id.mca);
        me=findViewById(R.id.me);
        mjmc=findViewById(R.id.mjmca);
        bed=findViewById(R.id.text_bed);
        bsc=findViewById(R.id.text_bsc);
        bcom=findViewById(R.id.text_bcom);
        bca=findViewById(R.id.text_bca);
        bba=findViewById(R.id.text_bba);
        barch=findViewById(R.id.barch);
        Mcom=findViewById(R.id.mcom);
        MSc=findViewById(R.id.msc);



        t.setOnClickListener(new View.OnClickListener() {
    @Override

    public void onClick(View view) {
        if(! hasBeenClicked )
        {
            mba.setText(R.string.MBA);
        }
        else {
            // Clear the fields
           mba.setText("");
        }
        hasBeenClicked = ! hasBeenClicked;
        Log.d("click",!hasBeenClicked+"");


    }
});
        t1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if(! hasBeenClicked)
                mca.setText(R.string.MCA);
                else
                    mca.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if(!hasBeenClicked)
                me.setText(R.string.Med);
                else
                    me.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if(! hasBeenClicked)
                mjmc.setText(R.string.MJMCA);
                else
                    mjmc.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });

        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! hasBeenClicked)
                    t4.setText(R.string.MJMCA);
                else
                    t4.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });
        bsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(! hasBeenClicked)
                    t5.setText(R.string.B_ed);
                else
                   t5.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });
        bcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! hasBeenClicked)
                    t6.setText(R.string.BSc);
                else
                    t6.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });
        bca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! hasBeenClicked)
                    t7.setText(R.string.BCA);
                else
                    t7.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });
        bba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! hasBeenClicked)
                    t8.setText(R.string.BBA);
                else
                    t8.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });
        barch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! hasBeenClicked)
                    t9.setText(R.string.B_ARCHITECTURE);
                else
                    t9.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });
        Mcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! hasBeenClicked)
                    t10.setText(R.string.M_COM);
                else
                    t10.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });


        MSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! hasBeenClicked)
                    t11.setText(R.string.MSc);
                else
                    t11.setText("");

                hasBeenClicked=!hasBeenClicked;
            }
        });

    }

}
