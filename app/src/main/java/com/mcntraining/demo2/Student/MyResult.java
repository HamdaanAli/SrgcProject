package com.mcntraining.demo2.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mcntraining.demo2.R;

public class MyResult extends AppCompatActivity {

    Button aktu,ccsu;
    String aktuurl="https://erp.aktu.ac.in/WebPages/OneView/OneView.aspx",ccsuurl="http://192.163.211.186/~ccsuresu/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_result);
        aktu=(Button) findViewById(R.id.AKTU);
        ccsu=(Button) findViewById(R.id.CCSU);

        aktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),MyResultWebView.class);
                intent.putExtra("URL",aktuurl);
                startActivity(intent);



            }
        });

        ccsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MyResultWebView.class);
                intent.putExtra("URL",ccsuurl);
                startActivity(intent);
            }
        });
    }
}
