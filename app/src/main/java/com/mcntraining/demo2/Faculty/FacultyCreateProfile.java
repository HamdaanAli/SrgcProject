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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mcntraining.demo2.R;

import java.io.IOException;

public class FacultyCreateProfile extends AppCompatActivity {
    String studentmessage,studentcourse,studentsemester,studentfather,studentmobile,studentid,studentemail,studentgender,studentstate,studentcity,studentaddress,studentbirth;
    int studnetyear;
    private static final int CHOOSE_IMAGE = 101;
    Spinner courses,gender,state,city,semester;
    EditText Message,Year,Father,Mobile,id_no,email,address,dob;
    ImageView imageView;
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
        setContentView(R.layout.activity_faculty_create_profile);
        databaseFaculty= FirebaseDatabase.getInstance().getReference("faculty_Profile");
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
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.imageView);
        progressBar=findViewById(R.id.progressbar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
                if(saveData()!=false)
                {
                    SharedPreferences sp = getSharedPreferences("finfo", MODE_PRIVATE);
                    final String userid = sp.getString("fid", null);
                    String id=  databaseFaculty.push().getKey();

                    FacultyProfile facultyProfile=new FacultyProfile(studentmessage,studentcourse,studentsemester,studentfather,studentbirth,studentaddress,studentmobile,studentid,userid,studentgender,studentstate,studentcity,studnetyear,profileImageUrl);
                    databaseFaculty.child(id).setValue(facultyProfile);

                    Toast.makeText(getApplicationContext(),"Created",Toast.LENGTH_LONG).show();

                    return;
                }else
                {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                }



            }
        });



    }





    private void saveUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(imageView);
            }




        }
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

        profileImageRef= FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");

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
                            Toast.makeText(FacultyCreateProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private boolean saveData()
    {
        boolean validate=true;


        studentmessage=Message.getText().toString();
        studentcourse=courses.getSelectedItem().toString();
        studnetyear=Integer.parseInt(String.valueOf(Year.getText()));
        studentfather=Father.getText().toString();
        studentmobile=Mobile.getText().toString();
        studentsemester=semester.getSelectedItem().toString();
        studentid=id_no.getText().toString();
        studentgender=gender.getSelectedItem().toString();
        studentstate=state.getSelectedItem().toString();
        studentcity=city.getSelectedItem().toString();
        studentaddress=address.getText().toString();
        studentbirth=dob.getText().toString();

        if(TextUtils.isEmpty(studentmessage))
        {
            Message.setError("Please write about yourself ");
            Message.requestFocus();
            validate=false;

        }

        if(TextUtils.isEmpty(Integer.toString(studnetyear)))

        {
            Year.setError("Please Provide Course Year");
            Year.requestFocus();
            validate=false;
        }
        if (TextUtils.isEmpty(studentfather))
        {
            Father.setError("Please Provide Your Father Name");
            Father.requestFocus();
            validate=false;
        }
        if (TextUtils.isEmpty(studentmobile))
        {
            Mobile.setError("Please Provide your valid Mobile number");
            Mobile.requestFocus();
            validate=false;
        }
        if (TextUtils.isEmpty(studentid))
        {
            id_no.setError("Please Provide your college id card number");
            id_no.requestFocus();
            validate=false;
        }

        if (TextUtils.isEmpty(studentbirth))
        {
            dob.setError("Please Mention Your Date of Birth");
            dob.requestFocus();
            validate=false;
        }
        if (TextUtils.isEmpty(studentaddress))
        {
            address.setError("Please Provide your address");
            address.requestFocus();
            validate=false;
        }
        if(courses.getSelectedItem().equals("Courses"))
        {
            Toast.makeText(getApplicationContext(),"select course",Toast.LENGTH_LONG).show();
            validate=false;
        }
        if(gender.getSelectedItem().equals("Gender"))
        {
            Toast.makeText(getApplicationContext(),"select gender",Toast.LENGTH_LONG).show();
            validate=false;
        }
        if(state.getSelectedItem().equals("State"))
        {
            Toast.makeText(getApplicationContext(),"select State",Toast.LENGTH_LONG).show();
            validate=false;
        }

        if(city.getSelectedItem().equals("City"))
        {
            Toast.makeText(getApplicationContext(),"select City",Toast.LENGTH_LONG).show();
            validate=false;
        }

        return validate;

    }


}

