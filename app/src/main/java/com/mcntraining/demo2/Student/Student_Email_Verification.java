package com.mcntraining.demo2.Student;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mcntraining.demo2.Faculty.Faculty_Email_Verification;
import com.mcntraining.demo2.Faculty.Login_Faculty;
import com.mcntraining.demo2.R;

public class Student_Email_Verification extends AppCompatActivity {

   private Button email_verify;
   private FirebaseAuth mAuth;
   private ProgressBar verify_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__email__verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("Email Verify");
        verify_progress=findViewById(R.id.verify_progres);
        email_verify=findViewById(R.id.verify_email_btn);
        mAuth=FirebaseAuth.getInstance();
       final FirebaseUser user=mAuth.getCurrentUser();
        email_verify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                verify_progress.setVisibility(View.VISIBLE);
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        verify_progress.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Verification Email Send", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Student_Email_Verification.this,Login_Student.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
            }
        });
    }
}
