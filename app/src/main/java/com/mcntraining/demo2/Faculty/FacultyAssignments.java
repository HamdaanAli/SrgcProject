package com.mcntraining.demo2.Faculty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mcntraining.demo2.R;


public class FacultyAssignments extends AppCompatActivity {
    String StuCourse,stuSem,Title,Assignment2;
    int Assignmentid;
    Button send;
    Toolbar toolbar;
    DatabaseReference DatabaseAssignment;
    EditText title,assignment,assignid;
    String Courses[]={"Course","B.com","Bsc","B.tech","BJMCA","MBA","MCA"};
    String Sem[]={"Semester","1sem","2sem","3sem","4sem","5sem","6sem","7sem","8sem"};
    Spinner Course,Semester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_assignments);
        toolbar=(Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("Faculty Assignment");
        toolbar.setTitleMarginStart(50);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DatabaseAssignment= FirebaseDatabase.getInstance().getReference("Assignment");
         Course=(Spinner)findViewById(R.id.courseSpinner);
         Semester=(Spinner)findViewById(R.id.semester);
        title=(EditText)findViewById(R.id.title);
        assignment=findViewById(R.id.edit_Message);
        assignid=findViewById(R.id.assignid);
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Courses);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Sem);
        Semester.setAdapter(adapter2);

        Course.setAdapter(adapter1);

        Course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClick(View view) {
        try {
            if(saveAssignment()!=false)
            {
                String id=  DatabaseAssignment.push().getKey();
                Assignment enq=new Assignment( StuCourse,stuSem,Title,Assignment2,Assignmentid);
                DatabaseAssignment.child(id).setValue(enq);
                Toast.makeText(this,"Assignment Send...",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }

    private boolean saveAssignment() {

        boolean validate=true;
        StuCourse=Course.getSelectedItem().toString();
        stuSem=Semester.getSelectedItem().toString();
        Title=title.getText().toString();
        Assignment2=assignment.getText().toString();
        Assignmentid=Integer.parseInt(String.valueOf(assignid.getText()));

        if (StuCourse.equals("Course"))
        {
            Toast.makeText(getApplicationContext(),"select course",Toast.LENGTH_LONG).show();
            validate=false;
        }
        if (stuSem.equals("Semester"))
        {
            Toast.makeText(getApplicationContext(),"select semester",Toast.LENGTH_LONG).show();
            validate=false;
        }
        if (TextUtils.isEmpty(Title))
        {
            title.setError("Please Give The Title OF Your Assignment");
            title.requestFocus();
            validate=false;
        }
        if (TextUtils.isEmpty(Integer.toString(Assignmentid)))
        {
            assignid.setError("Please Provide Assignment");
            assignid.requestFocus();
            validate=false;
        }
        if (TextUtils.isEmpty(Assignment2))
        {
            assignment.setError("Please Provide Assignment");
            assignment.requestFocus();
            validate=false;
        }
        return validate;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assignmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String title=(String)item.getTitle();

        switch (title)
        {


            case "Edit":

                  startActivity(new Intent(this,SearchAssignment.class));
                break;

            case "Delete":

                break;





        }


        return super.onOptionsItemSelected(item);
    }
}
