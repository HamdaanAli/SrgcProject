package com.mcntraining.demo2.Faculty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mcntraining.demo2.MainActivity.MainActivity;
import com.mcntraining.demo2.R;

public class FacultyDeshboard extends AppCompatActivity {
    CardView notification,Assignmnet,Search,Profile;
    private android.support.v7.widget.Toolbar toolbardesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_deshboard);
        toolbardesh=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbardash);
        setSupportActionBar(toolbardesh);
         notification=(CardView) findViewById(R.id.Notifications);
         Profile=findViewById(R.id.profile);
         Assignmnet=findViewById(R.id.Assignment);
         Search=findViewById(R.id.Search);
         Profile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(FacultyDeshboard.this,Faculty_Profile.class));
             }
         });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyDeshboard.this,FacultyNotifications.class));
            }
        });
        Assignmnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyDeshboard.this,FacultyAssignments.class));

            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyDeshboard.this,Student_Search.class));
            }
        });
    }

    public void onClick(View view) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title=(String)item.getTitle();

        switch (title)
        {


            case "Logout":

                SharedPreferences sp=getSharedPreferences("finfo",MODE_PRIVATE);
                String userid=sp.getString("fid",null);
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("fid");
                editor.clear();
                editor.commit();
                Intent intent=new Intent(this,MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;

            case "Create Profile":
                startActivity(new Intent(this,FacultyCreateProfile.class));

                break;





        }


        return super.onOptionsItemSelected(item);
    }
}
