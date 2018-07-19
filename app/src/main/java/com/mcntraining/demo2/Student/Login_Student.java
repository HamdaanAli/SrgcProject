package com.mcntraining.demo2.Student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcntraining.demo2.CheckConnectivity.ConnectivityReceiver;
import com.mcntraining.demo2.CheckConnectivity.MyApplication;
import com.mcntraining.demo2.R;

public class Login_Student extends AppCompatActivity implements View.OnClickListener,ConnectivityReceiver.ConnectivityReceiverListener
        {

    EditText email, Password;
    TextView already;
    FirebaseAuth auth;
    ProgressBar student_login_progress;
    DatabaseReference databasestudents;
    String pass,email_stu,name;
    Button b1,next;
    String email_student = "";
    String password="";
    LayoutInflater li;
      View layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("STUDENT LOGIN ");
        toolbar.setTitleMarginStart(240);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkConnection();
                finish();
            }
        });


        //toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        //toolbar.setTitle("STUDENT LOGIN");
        //toolbar.setTitleMarginStart(300);
        databasestudents = FirebaseDatabase.getInstance().getReference("student");
        email = findViewById(R.id.editText_email);
        already=findViewById(R.id.register);
        student_login_progress=findViewById(R.id.studntlogin_progres);
        Password = findViewById(R.id.editText_pass);
       b1= findViewById(R.id.btn_signIn);
       already.setOnClickListener(this);
       b1.setOnClickListener(this);
       password=Password.getText().toString();
      // next=findViewById(R.id.btnnext);
       //next.setOnClickListener(this);

//checkConnection();

    }



    @Override
    public void onClick(View v) {

        if(v==b1)
        {

               // SingIn();
             student_login_progress.setVisibility(View.VISIBLE);
            checkConnection();


        }
        if(v==already)
        {
            startActivity(new Intent(this,Student_Registeration.class));
        }


    }

    private  void SingIn()
    {

        email_student=email.getText().toString();
        Query query = databasestudents.orderByChild("emailAdd").equalTo(email_student);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    Students students=data.getValue(Students.class);
                    name=students.getStudentName();
                    pass=students.getPassword();

                    Toast.makeText(Login_Student.this,name,Toast.LENGTH_LONG).show();
                    if (Password.getText().toString().equals(pass))
                    {
                        email.setText("");
                        Password.setText("");
                        SharedPreferences sp=getSharedPreferences("userinfo",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("userid",email_student);
                        editor.commit();
                        SharedPreferences spp=getSharedPreferences("SName",MODE_PRIVATE);
                        SharedPreferences.Editor editorr=spp.edit();
                        editorr.putString("SName",name);
                        editorr.commit();
                        Intent intent = new Intent(getApplicationContext(), StudentDashboard.class);
                        intent.putExtra("studentName", name);
                        startActivity(intent);
                        student_login_progress.setVisibility(View.INVISIBLE);
                    }

                    else
                    {
                        Toast t=Toast.makeText(getApplicationContext(),"invalid ",Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
                        t.show();
                      student_login_progress.setVisibility(View.INVISIBLE);

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
            private void checkConnection()
            {
                final boolean isConnected=ConnectivityReceiver.isConnected();

                 if(isConnected)

                 {

                     SingIn();


                 }
                 else
                 {
                     li=getLayoutInflater();
                     layout=li.inflate(R.layout.nointerneterror,(ViewGroup)findViewById(R.id.custom_layout_error));
                     Glide.with(getApplicationContext()).load(R.mipmap.nointernet).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(((ImageView) layout.findViewById(R.id.imgerrror)));
                     layout.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {

                             checkConnection();


                         }
                     });
                     email.setText("");
                     Password.setText("");
                     setContentView(layout);

                 }
            }
            @Override
            protected void onResume() {
                super.onResume();
                MyApplication.getInstance().setConnectivityListener(this);

            }
            @Override
            public void onNetworkConnectionChanged(boolean isConnected) {
                if(isConnected)
                    Toast.makeText(getApplicationContext(),"Connected to Internet",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Not Connected to Internet",Toast.LENGTH_LONG).show();

            }
        }





