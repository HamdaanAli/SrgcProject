package com.mcntraining.demo2.Faculty;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class FacultyRegiseration extends AppCompatActivity implements View.OnClickListener,ConnectivityReceiver.ConnectivityReceiverListener {
    private EditText username,password,email,repass;
    String Faculty_name,Faculty_pass,repassword,Email;
    private Button singup,already;
    FirebaseAuth firebaseAuth;
    DatabaseReference databasestudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_regiseration);

        databasestudents= FirebaseDatabase.getInstance().getReference("faculties");

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
    private boolean registerFacultyValid()
    {
         boolean valid=true;

        Faculty_name  =username.getText().toString().trim();
        Faculty_pass=password.getText().toString().trim();
        repassword=repass.getText().toString().trim();
        Email=email.getText().toString().trim();

        if(!TextUtils.isEmpty(Faculty_name))
        {

        }else
        {

            username.setError("Faculty name required");
            username.requestFocus();

            valid=false;

        }
        if(TextUtils.isEmpty(Faculty_pass))
        {
            password.setError("Password is required");
            password.requestFocus();
            valid=false;
        }else if(!Faculty_pass.equals(repassword))
        {
           repass.setError("Please match the password");
           repass.requestFocus();
           valid=false;
        }

        if (Faculty_pass.length() < 6) {
            password.setError("Minimum lenght of password should be 6");
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

        return  valid;


    }

    @Override
    public void onClick(View v) {

        if(v==singup)
        {
            checkConnection();
            if(registerFacultyValid()!=false)
            {
                String id=  databasestudents.push().getKey();
                Faculty faculty=new Faculty(Faculty_name,Faculty_pass,Email);
                databasestudents.child(id).setValue(faculty);
                Toast.makeText(this,"Faculty registered",Toast.LENGTH_LONG).show();
                return;

            }else
            {
                Toast.makeText(getApplicationContext(),"Not Registered",Toast.LENGTH_LONG).show();
            }
        }

        if(v==already)
        {
            startActivity(new Intent(FacultyRegiseration.this,Login_Faculty.class));
        }

    }
    private void checkConnection()
    {
        boolean isConnected=ConnectivityReceiver.isConnected();
        if(isConnected)
            Toast.makeText(getApplicationContext(),"Connected to Internet",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Not Connected to Internet",Toast.LENGTH_LONG).show();
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
