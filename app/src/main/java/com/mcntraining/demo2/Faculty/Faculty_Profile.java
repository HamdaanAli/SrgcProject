package com.mcntraining.demo2.Faculty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcntraining.demo2.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Faculty_Profile extends AppCompatActivity {
    CircleImageView profile;
    ImageView edit;
    TextView studentName,location,status,course,semester,year,stu_id,gender,dob,email,father,address,state,mobile;
    String FacultyName;
    String Location;
    String Course;
    String Stu_id;
    String Gender;
    String Dob;
    String Email;
    String Father;
    String Address;
    String State;
    String Mobile;
    String Status;

    int Year;

    DatabaseReference databasefaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__profile);
        databasefaculty = FirebaseDatabase.getInstance().getReference("faculty_Profile");
        SharedPreferences sp=getSharedPreferences("finfo",MODE_PRIVATE);
        String userid=sp.getString("fid",null);

        SharedPreferences spp=getSharedPreferences("FName",MODE_PRIVATE);
        FacultyName=spp.getString("FName",null);
        Log.d("Faculty Name",FacultyName);


        Query query=databasefaculty.orderByChild("f_Email").equalTo(userid);
        edit=findViewById(R.id.edit);
        location=findViewById(R.id.location);
        status=findViewById(R.id.status);
        course=findViewById(R.id.course);
        semester=findViewById(R.id.sem);
        year=findViewById(R.id.year);
        stu_id=findViewById(R.id.id);
        gender=findViewById(R.id.gender);
        dob=findViewById(R.id.dob);
        email=findViewById(R.id.email);
        father=findViewById(R.id.f_name);
        address=findViewById(R.id.address);
        state=findViewById(R.id.state);
        mobile=findViewById(R.id.mobile);
        profile=findViewById(R.id.profile);
        studentName=findViewById(R.id.name);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Faculty_Profile.this,FacultyProfileUpdate.class));
            }
        });
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {



                    FacultyProfile studentProfile=data.getValue(FacultyProfile.class);
                    String Status = studentProfile.getF_Messg();
                    String Location = studentProfile.getF_City();
                    String Address = studentProfile.getF_address();
                    String Course = studentProfile.getF_Course();
                    String Semester=studentProfile.getDesignation();
                    String DOB = studentProfile.getF_Dob();
                    String Email = studentProfile.getF_Email();
                    String Father = studentProfile.getF_Father();
                    String Gender = studentProfile.getF_Gener();
                    String id = studentProfile.getF_Id();
                    String Mobile = studentProfile.getF_Mobile();
                    int Year = studentProfile.getF_Year();
                    String State = studentProfile.getF_State();
                    String image=studentProfile.getImguri();

                    status.setText(Status);
                    location.setText(Location);
                    address.setText(Address);
                    course.setText(Course);
                    semester.setText(Semester);
                    dob.setText(DOB);
                    email.setText(Email);
                    father.setText(Father);
                    gender.setText(Gender);
                    stu_id.setText(id);
                    mobile.setText(Mobile);
                    state.setText(State);
                    studentName.setText(FacultyName);
                    year.setText(Integer.toString(Year));
                   /* Picasso.get()
                            .load(image)
                            .resize(50,50)
                            .centerCrop()
                            .into(profile);*/
                    Glide.with(getApplicationContext()).load(image).into(profile);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
