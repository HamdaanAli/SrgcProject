package com.mcntraining.demo2.Faculty;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mcntraining.demo2.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Student_Search extends AppCompatActivity {

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__search);


        mUserDatabase = FirebaseDatabase.getInstance().getReference("student_Profile");


        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });
    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(Student_Search.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("stu_Id").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Search, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Search, UsersViewHolder>(

                Search.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Search model, int position) {


               // viewHolder.setDetails(getApplicationContext(), model.getName(), model.getStatus(), model.getImage());
              viewHolder.setDetails(getApplicationContext(),model.getStu_Id(),model.getStu_Email(),model.getStu_Course(),model.getImguri(),model.getStu_Year(),model.stu_Dob,model.getStu_Messg(),model.getStu_Gener(),model.stu_Mobile,model.stu_Father,model.getStu_City(),model.getStu_State(),model.getStu_address());
                               Log.d("Year", String.valueOf(model.getStu_Year()));
            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx,String colgid, String semail, String scourse, String profile,int Courseyear,String dob,String status,String gender,String mobile,String father,String scity,String sstate,String saddress) {




            TextView colg_id,email,course,CourseYear,Dob,Status,Gender,Mobile,Father,City,state,address;
            CircleImageView circleImageView;
            colg_id=mView.findViewById(R.id.id);
            email=mView.findViewById(R.id.Email);
            course=mView.findViewById(R.id.Course);
            CourseYear=mView.findViewById(R.id.CourseYear);
            Dob=mView.findViewById(R.id.Dob);
            Status=mView.findViewById(R.id.status);
            Gender=mView.findViewById(R.id.Gender);
            Mobile=mView.findViewById(R.id.Mobile);
            Father=mView.findViewById(R.id.Father);
            City=mView.findViewById(R.id.City);
            state=mView.findViewById(R.id.State);
            address=mView.findViewById(R.id.Address);
            circleImageView=mView.findViewById(R.id.profile_image);

            colg_id.setText(colgid);
            email.setText(semail);
            course.setText(scourse);
            Dob.setText(dob);
            Status.setText(status);
            Gender.setText(gender);
            Mobile.setText(mobile);
            Father.setText(father);
            City.setText(scity);
            state.setText(sstate);
            address.setText(saddress);
            CourseYear.setText(Integer.toString(Courseyear));
            Glide.with(ctx).load(profile).into(circleImageView);




        }


    }
}
