package com.mcntraining.demo2.Enquiry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcntraining.demo2.R;

public class Enquery extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference enquirydata;
    private Button submit;
  private   Toolbar toolbarEnquiry;
  private   EditText name,phone,email,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquery);
        enquirydata=FirebaseDatabase.getInstance().getReference("Enquiry");
        toolbarEnquiry=findViewById(R.id.toolbar_enquiry);
        name=findViewById(R.id.edit_Name);
        phone=findViewById(R.id.edit_Phone);
        email=findViewById(R.id.edit_email);
        message=findViewById(R.id.edit_Message);
        findViewById(R.id.button_submit).setOnClickListener(this);
        setSupportActionBar(toolbarEnquiry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onClick(View v) {

        String Name,PhoneNum,Message,Email;
        Name  =name.getText().toString().trim();
        PhoneNum=phone.getText().toString().trim();
        Email=email.getText().toString().trim();
        Message=message.getText().toString().trim();


        if(!TextUtils.isEmpty(Name))
        {
            String id=  enquirydata.push().getKey();
            Enquiry enq=new Enquiry( Name,PhoneNum,Email,Message);
            enquirydata.child(id).setValue(enq);
            Toast.makeText(this,"Enquiry Send...",Toast.LENGTH_LONG).show();

        }else
        {

            name.setError("Name required");
            name.requestFocus();

            return;

        }
        if(TextUtils.isEmpty(PhoneNum))
        {
            phone.setError("Password is required");
            phone.requestFocus();
            return;
        }


        if(TextUtils.isEmpty(Email))
        {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(Message))
        {
            message.setError("Please provide enquiry message");
            message.requestFocus();
            return;
        }



    }


}
