package com.mcntraining.demo2.Faculty;

import android.app.ProgressDialog;
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

public class Login_Faculty extends AppCompatActivity implements View.OnClickListener,ConnectivityReceiver.ConnectivityReceiverListener {
    EditText email, Password;
    TextView already;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    DatabaseReference databaseFaculty;
    String pass,name;
    Button b1,next;
    String email_Faculty = "";
    String password="";
   Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__faculty);
        toolbar=(Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("Faculty Login");
        toolbar.setTitleMarginStart(240);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // checkConnection();
                finish();
            }
        });

        databaseFaculty = FirebaseDatabase.getInstance().getReference("faculties");
        email = findViewById(R.id.editText_email);
        already=findViewById(R.id.register);
        Password = findViewById(R.id.editText_pass);
        b1= findViewById(R.id.btn_signIn);
        already.setOnClickListener(this);
        b1.setOnClickListener(this);
        password=Password.getText().toString();



    }

    @Override
    public void onClick(View v) {

        if(v==b1)
        {

           checkConnection();
        }
        if(v==already)
        {
            startActivity(new Intent(this,FacultyRegiseration.class));
        }

    }

    private  void SingIn()
    {
       // checkConnection();
        email_Faculty=email.getText().toString();
       Query query=databaseFaculty.orderByChild("emailAdd").equalTo(email_Faculty);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    Faculty faculty=data.getValue(Faculty.class);
                              name=faculty.getFacultyName();
                              pass=faculty.getPassword();
                    Toast.makeText(Login_Faculty.this,name,Toast.LENGTH_LONG).show();
                    if (Password.getText().toString().equals(pass))
                    {
                        email.setText("");
                        Password.setText("");
                        SharedPreferences sp=getSharedPreferences("finfo",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("fid",email_Faculty);
                        editor.commit();
                        SharedPreferences spp=getSharedPreferences("FName",MODE_PRIVATE);
                        SharedPreferences.Editor editorr=spp.edit();
                        editorr.putString("FName",name);
                        editorr.commit();
                        Intent intent = new Intent(getApplicationContext(), FacultyDeshboard.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast t=Toast.makeText(getApplicationContext(),"invalid "+email_Faculty+" "+pass,Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
                        t.show();


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
        boolean isConnected=ConnectivityReceiver.isConnected();

        if(isConnected)
        {

            SingIn();
        }
        else
        {
            LayoutInflater li=getLayoutInflater();
            final View layout=li.inflate(R.layout.nointerneterror,(ViewGroup)findViewById(R.id.custom_layout_error));
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
