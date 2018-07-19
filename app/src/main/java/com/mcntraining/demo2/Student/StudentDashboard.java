package com.mcntraining.demo2.Student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcntraining.demo2.MainActivity.MainActivity;
import com.mcntraining.demo2.R;

public class StudentDashboard extends AppCompatActivity implements View.OnClickListener {

    CardView profile,notification,Assignment,result,query;
    DatabaseReference databasestudents;
    private android.support.v7.widget.Toolbar toolbardesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        toolbardesh=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbardash);
        setSupportActionBar(toolbardesh);
        databasestudents = FirebaseDatabase.getInstance().getReference("student_Profile");
        profile=findViewById(R.id.profile);
        notification=findViewById(R.id.Notifications);
        Assignment=findViewById(R.id.Assignment);
        result=findViewById(R.id.Result);
        query=findViewById(R.id.edit_query);
        profile.setOnClickListener(this);
        notification.setOnClickListener(this);
        Assignment.setOnClickListener(this);
        result.setOnClickListener(this);
        query.setOnClickListener(this);
        String collg_id="";
    }

    public void onClick(View v) {
        if(v==profile)
        {

            startActivity(new Intent(this,Student_Profile.class));


        }

        if(v==notification)
        {
            startActivity(new Intent(this,StudentNotifications.class));

        }

        if(v==Assignment)
        {
           startActivity(new Intent(this,CheckAssignment.class));
        }
        if(v==result)
        {
            startActivity(new Intent(this,MyResult.class));
        }
        if(v==query)
        {

        }

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

                SharedPreferences sp=getSharedPreferences("userinfo",MODE_PRIVATE);
                String userid=sp.getString("userid",null);
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("userid");
                editor.clear();
                editor.commit();
                Intent intent=new Intent(this,MainActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;

            case "Create Profile":
             startActivity(new Intent(this,ProfileEditStudent.class));

                break;





        }


        return super.onOptionsItemSelected(item);
    }
}

