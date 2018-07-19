package com.mcntraining.demo2.Student;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcntraining.demo2.Faculty.Assignment;
import com.mcntraining.demo2.R;

public class CheckAssignment extends AppCompatActivity {
    Spinner courses,sem;
    GridView gridView;
    String Courses[]={"Courses","B.com","Bsc","B.tech","BJMCA","MBA","MCA"};
    String Semester[]={"Semester","1st Sem","2nd Sem","3rd Sem","4th Sem","5th Sem","6th Sem","7th Sem","8th Sem"};
    DatabaseReference mUserDatabase;
    ArrayAdapter adapter;
    Toolbar toolbar;
    String semester=null,course=null;
    String Title,asment,studnCourse,studntsem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_assignment);
        toolbar=(Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("Student Assignment");
        toolbar.setTitleMarginStart(200);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences sp=getSharedPreferences("stuCourse",MODE_PRIVATE);
        course=sp.getString("CourseStudent",null);
        SharedPreferences spp=getSharedPreferences("stuSemester",MODE_PRIVATE);
        semester=spp.getString("Semester",null);
        mUserDatabase=FirebaseDatabase.getInstance().getReference("Assignment");
        courses=findViewById(R.id.course);
        sem=findViewById(R.id.sem);
        gridView=findViewById(R.id.gridView);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        gridView.setAdapter(adapter);
       // if(course !=null && semester!=null ) {
            mUserDatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                       // Log.v("mydata", data.getValue().toString());

                        Assignment assignment = data.getValue(Assignment.class);



                        if (course.equalsIgnoreCase(assignment.getCourse()) && semester.equalsIgnoreCase(assignment.getSemester())) {

                                 String Assign=assignment.getAssignment();
                                 String Title=assignment.getTitle();

                            adapter.add(Title);
                            adapter.add(Assign);
                            adapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(CheckAssignment.this, "Not exist", Toast.LENGTH_SHORT).show();
                        }


                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

       // }
      //  else
          //  Toast.makeText(getApplicationContext(),"Please Tap on Profile",Toast.LENGTH_LONG).show();
    }


}
