package com.mcntraining.demo2.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcntraining.demo2.CheckConnectivity.ConnectivityReceiver;
import com.mcntraining.demo2.CheckConnectivity.MyApplication;
import com.mcntraining.demo2.R;

public class Student_Registeration extends AppCompatActivity implements View.OnClickListener,ConnectivityReceiver.ConnectivityReceiverListener {

  private EditText username,password,email,repass;
    String studentname,Studentpass,repassword,Email;
    private Button singup,already;
    FirebaseAuth firebaseAuth;
    String stuEmail;
    DatabaseReference databasestudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__registeration);
        checkConnection();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("STUDENT REGISTERATION");
        toolbar.setTitleMarginStart(160);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databasestudents=FirebaseDatabase.getInstance().getReference("student");



        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repass=findViewById(R.id.repass);
        singup=(Button)findViewById(R.id.btn_signUp);
        already=(Button)findViewById(R.id.btn_alredy);
        firebaseAuth = FirebaseAuth.getInstance();
        singup.setOnClickListener(this);
        already.setOnClickListener(this);

    }

    private boolean registerStudentValidte()
    {
        boolean valid = true;

        studentname  =username.getText().toString().trim();
              Studentpass=password.getText().toString().trim();
              repassword=repass.getText().toString().trim();
              Email=email.getText().toString().trim();

              if(TextUtils.isEmpty(studentname))
              {
                  username.setError("Username required");
                  username.requestFocus();
                   valid=false;

              }

        if(TextUtils.isEmpty(Studentpass))
              {
                  password.setError("Password is required");
                  password.requestFocus();
                  valid=false;
              }else if(!Studentpass.equals(repassword))
        {
            repass.setError("Please match the password");
            repass.requestFocus();
            valid=false;
        }
        if (Studentpass.length() < 8) {
            password.setError("Minimum lenght of password should be 8");
            password.requestFocus();
           valid=false;
        }

        if(TextUtils.isEmpty(Email))
              {
                  email.setError("Email is required");
                  email.requestFocus();
                  valid=false;
              }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
           valid=false;
        }


     return valid;

    }



    @Override
    public void onClick(View v) {

        if(v==singup)
        {


               checkConnection();
               try {
                   if (registerStudentValidte() != false) {
                       String id = databasestudents.push().getKey();
                       Students students = new Students(studentname, Studentpass, Email);
                       databasestudents.child(id).setValue(students);
                       Toast.makeText(getApplicationContext(), " Registered", Toast.LENGTH_LONG).show();
                       startActivity(new Intent(getApplicationContext(),Login_Student.class));
                       finish();

                       return;
                   } else {
                       Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_LONG).show();
                   }
               }
               catch (Exception e)
               {
                   String message=e.getMessage();
                   Toast.makeText(getApplicationContext(),"Error :"+message,Toast.LENGTH_LONG).show();
               }


        }

         if(v==already)
         {
           startActivity(new Intent(Student_Registeration.this,Login_Student.class));
         }
    }
    private void checkConnection()
    {
        boolean isConnected=ConnectivityReceiver.isConnected();
        if(isConnected)
            Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Not Connected",Toast.LENGTH_LONG).show();
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
