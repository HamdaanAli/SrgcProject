package com.mcntraining.demo2.Student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mcntraining.demo2.R;

import java.util.HashMap;
import java.util.Map;

public class StudentNotifications extends AppCompatActivity {
    String token_url = "https://hamdanali749.000webhostapp.com/saveToken.php";
    RequestQueue requestQueue;
    Spinner spinner;
    ArrayAdapter adapter;
    Toolbar toolbar;
    String selected_course = null;
    ProgressBar notify_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notifications);
        toolbar=(Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("Notification");
        toolbar.setTitleMarginStart(250);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btn = findViewById(R.id.btnsendtoken);
        notify_progress=findViewById(R.id.subscribe_progress);
        spinner = (Spinner) findViewById(R.id.course);
        adapter = ArrayAdapter.createFromResource(this, R.array.courseinfo, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_course = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify_progress.setVisibility(View.VISIBLE);
                final EditText title=(EditText)findViewById(R.id.entertitle);
                requestQueue= Volley.newRequestQueue(StudentNotifications.this);

                final String token= FirebaseInstanceId.getInstance().getToken();
                StringRequest sr=new StringRequest(Request.Method.POST, token_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("token_send","Token send successfully.");
                        Toast.makeText(StudentNotifications.this, "Token_send:"+response, Toast.LENGTH_SHORT).show();
                        notify_progress.setVisibility(View.INVISIBLE);
                        //dAUpPGVbubg:APA91bFvqkkY6PrsjyKAi0qXs303b-UauEiuAUPe5Rt0wskHmX3IKeVlk4fA6i8K8UCK3XvKZEJIZLdP2w0JIODN3fdt5Kg9Ar2Zmmx87K9jldSyzW8lzWAop4rLx48XONi7q4un0HTt
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("sending_error",error.getMessage());
                        Toast.makeText(StudentNotifications.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        notify_progress.setVisibility(View.INVISIBLE);
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data=new HashMap<String,String>();
                        data.put("token",token);
                        data.put("course",selected_course);
                        return data;
                    }
                };
                requestQueue.add(sr);
            }


        });
    }
}
