package com.mcntraining.demo2.Faculty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mcntraining.demo2.R;

import java.io.IOException;

public class FacultyProfileUpdate extends AppCompatActivity {
    String studentmessage,studentcourse,studentsemester,studentfather,studentmobile,studentid,studentemail,studentgender,studentstate,studentcity,studentaddress,studentbirth;
    int studnetyear;
    private static final int CHOOSE_IMAGE = 101;
    Spinner courses,gender,state,city,semester;
    EditText Message,Year,Father,Mobile,id_no,email,address,dob;
    ImageView imageView;
    Button buttonUpdate;
    Uri uriProfileImage;
    ProgressBar progressBar;
    String profileImageUrl;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    DatabaseReference databaseFaculty;
    public static final String SHARED_PREFS="sharedPrefs";
    String Courses[]={"Department","Faculty Of Engg.","Faculty Of Architecture","Faculty Of Management","Faculty Of Computer","Faculty Of Fine Art"};
    String Gender[]={"Gender","Female","Male"};
    String Semester[]={"Designation","H.O.D","Professor","Assistance Professor","Instructor","Associate Professor"};
    String States[]={"State","Uttar Pradesh","Uttarakhand","Delhi","Rajasthan","Haryana","Bihar"};
    String Cities[]={"City","Saharanpur","Muzaffarnagar","Deoband","Gaziabad","Meerut","Noida","Faridabad","Allahabad","Jaipur"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile_update);
         buttonUpdate=findViewById(R.id.buttonUpdate);
        address=findViewById(R.id.address);
        dob=findViewById(R.id.birthdate);
        courses=findViewById(R.id.courses);
        semester=findViewById(R.id.sem);
        gender=findViewById(R.id.gender);
        state=findViewById(R.id.state);
        city=findViewById(R.id.city);
        Message=findViewById(R.id.edit_Message);
        Year=findViewById(R.id.year);
        Father=findViewById(R.id.father_name);
        Mobile=findViewById(R.id.mobile);
        id_no=findViewById(R.id.id_card);
        email=findViewById(R.id.email);
        email.setEnabled(false);
        email.setFocusable(false);

        SharedPreferences sp=getSharedPreferences("finfo",MODE_PRIVATE);
        String userid=sp.getString("fid",null);
        databaseFaculty= FirebaseDatabase.getInstance().getReference("faculty_Profile");
        Query query=databaseFaculty.orderByChild("f_Email").equalTo(userid);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren())
                {

                    String Status = data.child("f_Messg").getValue().toString();
                    // String Location = data.child("stu_City").getValue().toString();
                    String Address = data.child("f_address").getValue().toString();
                    //String Course = data.child("stu_Course").getValue().toString();
                    String DOB = data.child("f_Dob").getValue().toString();
                    String Email = data.child("f_Email").getValue().toString();
                    String Fatherr = data.child("f_Father").getValue().toString();
                    //String Gender =data.child("stu_Gener").getValue().toString();
                    String id =data.child("f_Id").getValue().toString();
                    String SMobile = data.child("f_Mobile").getValue().toString();
                    //int SYear = (Integer.parseInt( data.child("stu_Year").toString()));
                    //String State = data.child("stu_State").getValue().toString();
                    Message.setText(Status);
                    address.setText(Address);
                    dob.setText(DOB);
                    email.setText(Email);
                    Father.setText(Fatherr);
                    id_no.setText(id);
                    Mobile.setText(SMobile);
                    //Year.setText(SYear);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Courses);
        courses.setAdapter(adapter1);
        ArrayAdapter adapter5=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Semester);
        semester.setAdapter(adapter5);
        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Gender);
        gender.setAdapter(adapter2);
        ArrayAdapter adapter3=new ArrayAdapter(this,android.R.layout.simple_list_item_1,States);
        state.setAdapter(adapter3);
        ArrayAdapter adapter4=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Cities);
        city.setAdapter(adapter4);
        courses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressbar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateData();

                Toast.makeText(FacultyProfileUpdate.this,"Updated",Toast.LENGTH_LONG).show();



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageView.setImageBitmap(bitmap);

                uploadImageToFirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImageToFirebaseStorage() {
        StorageReference profileImageRef;

        profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");

        if (uriProfileImage != null) {
            progressBar.setVisibility(View.VISIBLE);
            profileImageRef.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            profileImageUrl = taskSnapshot.getDownloadUrl().toString();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(FacultyProfileUpdate.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }
    private  void    updateData()
    {
        studnetyear=Integer.parseInt(String.valueOf(Year.getText()));
        if(!TextUtils.isEmpty(Integer.toString(studnetyear)))
        {
            databaseFaculty = FirebaseDatabase.getInstance().getReference("faculty_Profile");

            SharedPreferences sp = getSharedPreferences("finfo", MODE_PRIVATE);
            String userid = sp.getString("fid", null);
            Query query = databaseFaculty.orderByChild("f_Email").equalTo(userid);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DataSnapshot nodeDataSnapshot = dataSnapshot.getChildren().iterator().next();

                    String key = nodeDataSnapshot.getKey(); // this key is `K1NRz9l5PU_0CFDtgXz

                    databaseFaculty.child(key).child("f_City").setValue(city.getSelectedItem().toString());
                    databaseFaculty.child(key).child("f_Gener").setValue(gender.getSelectedItem().toString());
                    databaseFaculty.child(key).child("f_Course").setValue(courses.getSelectedItem().toString());
                    databaseFaculty.child(key).child("f_State").setValue(state.getSelectedItem().toString());
                    databaseFaculty.child(key).child("f_Dob").setValue(dob.getText().toString());
                    databaseFaculty.child(key).child("f_Father").setValue(Father.getText().toString());
                    databaseFaculty.child(key).child("f_Id").setValue(id_no.getText().toString());
                    databaseFaculty.child(key).child("f_Mobile").setValue(Mobile.getText().toString());
                    databaseFaculty.child(key).child("f_Year").setValue(Integer.parseInt(String.valueOf(Year.getText())));
                    databaseFaculty.child(key).child("f_address").setValue(address.getText().toString());
                    databaseFaculty.child(key).child("f_Messg").setValue(Message.getText().toString());
                    databaseFaculty.child(key).child("designation").setValue(semester.getSelectedItem().toString());
                    databaseFaculty.child(key).child("imguri").setValue(profileImageUrl);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Year.setError("Please Provide Course Year");
            Year.requestFocus();
        }

    }

}
