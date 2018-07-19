package com.mcntraining.demo2.Student;

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

public class Student_Profile extends AppCompatActivity {
    CircleImageView profile;
    ImageView edit;
    TextView studentName,location,status,course,semester,year,stu_id,gender,dob,email,father,address,state,mobile;
    String StudentName;
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

    DatabaseReference databasestudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__profile);
        databasestudents = FirebaseDatabase.getInstance().getReference("student_Profile");

        SharedPreferences sp=getSharedPreferences("userinfo",MODE_PRIVATE);
        String userid=sp.getString("userid",null);

        SharedPreferences spp=getSharedPreferences("SName",MODE_PRIVATE);
        StudentName=spp.getString("SName",null);
        Log.d("Student Name",StudentName);
        Query query=databasestudents.orderByChild("stu_Email").equalTo(userid);
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
        startActivity(new Intent(Student_Profile.this,Student_Update.class));
    }
});
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {



                    StudentProfile studentProfile=data.getValue(StudentProfile.class);
                    String Status = studentProfile.getStu_Messg();
                    String Location = studentProfile.getStu_City();
                    String Address = studentProfile.getStu_address();
                    String Course = studentProfile.getStu_Course();
                    String Semester=studentProfile.getStu_Semester();
                    String DOB = studentProfile.getStu_Dob();
                    String Email = studentProfile.getStu_Email();
                    String Father = studentProfile.getStu_Father();
                    String Gender = studentProfile.getStu_Gener();
                    String id = studentProfile.getStu_Id();
                    String Mobile = studentProfile.getStu_Mobile();
                    int Year = studentProfile.getStu_Year();
                    String State = studentProfile.getStu_State();
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
                    studentName.setText(StudentName);
                    year.setText(Integer.toString(Year));
                   /* Picasso.get()
                            .load(image)
                            .resize(50,50)
                            .centerCrop()
                            .into(profile);*/
                    Glide.with(getApplicationContext()).load(image).into(profile);

                    SharedPreferences sp=getSharedPreferences("stuCourse",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("CourseStudent",Course);
                    editor.commit();

                    SharedPreferences spp=getSharedPreferences("stuSemester",MODE_PRIVATE);
                    SharedPreferences.Editor editorr=spp.edit();
                    editorr.putString("Semester",Semester);
                    editorr.commit();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}









/*databasestudents.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    StudentProfile studentProfile = data.getValue(StudentProfile.class);
                   .g String Status = studentProfile.getStu_Messg();
                    String Location = studentProfile.getStu_City();
                    String Address = studentProfile.getStu_address();
                    String Course = studentProfile.getStu_Course();
                    String DOB = studentProfile.getStu_Dob();
                    String Email = studentProfile.getStu_Email();
                    String Father = studentProfile.getStu_Father();
                    String Gender = studentProfile.getStu_Gener();
                    String id = studentProfile.getStu_Id();
                    String Mobile = studentProfile.getStu_Mobile();
                    int Year = studentProfileetStu_Year();
                    String State = studentProfile.getStu_State();
                     status.setText(Status);
                     location.setText(Location);
                     address.setText(Address);
                     course.setText(Course);
                     dob.setText(DOB);
                     email.setText(Email);
                     father.setText(Father);
                     gender.setText(Gender);
                     stu_id.setText(id);
                     mobile.setText(Mobile);
                     state.setText(State);
                     year.setText(Integer.toString(Year));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

