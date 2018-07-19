package com.mcntraining.demo2.Faculty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mcntraining.demo2.R;

import java.util.HashMap;
import java.util.Map;

public class FacultyNotifications extends AppCompatActivity {

    String notification_url = "https://hamdanali749.000webhostapp.com/multicastNotification.php";
    RequestQueue requestQueue;
    Spinner spinner;
    ArrayAdapter adapter;
    String selected_course = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_notifications);


        Button btn = findViewById(R.id.btnsendtoken);
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

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
               final EditText title=(EditText)findViewById(R.id.entertitle);
               final EditText message=(EditText)findViewById(R.id.entermessage);
                requestQueue=Volley.newRequestQueue(FacultyNotifications.this);
                Button btnnotify=(Button)findViewById(R.id.btnnotify);
                btnnotify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String notiy_title=title.getText().toString();
                        final String notify_message=message.getText().toString();
                        StringRequest sr=new StringRequest(Request.Method.POST, notification_url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //dAUpPGVbubg:APA91bFvqkkY6PrsjyKAi0qXs303b-UauEiuAUPe5Rt0wskHmX3IKeVlk4fA6i8K8UCK3XvKZEJIZLdP2w0JIODN3fdt5Kg9Ar2Zmmx87K9jldSyzW8lzWAop4rLx48XONi7q4un0HTt
                                Log.v("token_send",response);
                                Toast.makeText(FacultyNotifications.this,response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.v("sending_message",error.getMessage());
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> data=new HashMap<String,String>();
                                data.put("course",selected_course);
                                data.put("title",notiy_title);
                                data.put("message",notify_message);
                                return data;
                            }
                        };
                        requestQueue.add(sr);
                    }
                });

            }

    }

