package com.mcntraining.demo2.Faculty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mcntraining.demo2.R;

public class SearchAssignment extends AppCompatActivity {
    private EditText mSearchField;
    private ImageButton mSearchBtn;
  static   EditText title,assign,assigid,course,sem;
    Button Delete,Update;
   static int id;
   static String ti,assi,se,co;
    //Spinner course,sem;
    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_assignment);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Assignment");
        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        Delete=findViewById(R.id.btndelete);
        Update=findViewById(R.id.btn_Update);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int searchText =Integer.parseInt(String.valueOf(mSearchField.getText())) ;

                    firebaseUserSearch(searchText);


                }
               catch (Exception e)
               {
                   e.getMessage();
                   e.printStackTrace();
               }
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAssignment();
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();

            }
        });

//        findViewById(R.id.btndelete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DeleteAssignment();
//            }
//        });

    }

    private void DeleteAssignment() {
        int  uniqueid= Integer.parseInt(assigid.getText().toString());
        mUserDatabase.orderByChild("assignid").equalTo(uniqueid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot node=dataSnapshot.getChildren().iterator().next();
                mUserDatabase.child(node.getKey()).removeValue();
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateAssignment() {
       // mUserDatabase = FirebaseDatabase.getInstance().getReference("Assignment");
        id=Integer.parseInt(String.valueOf(assigid.getText()));
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Assignment");
        Query queryyy = mUserDatabase.orderByChild("assignid").equalTo(id);
        queryyy.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot nodeDataSnapshot = dataSnapshot.getChildren().iterator().next();
                String key = nodeDataSnapshot.getKey();
                mUserDatabase.child(key).child("assignid").setValue(Integer.parseInt(String.valueOf(assigid.getText())));
               mUserDatabase.child(key).child("assignment").setValue(assign.getText().toString());
               mUserDatabase.child(key).child("course").setValue(course.getText().toString());
               mUserDatabase.child(key).child("semester").setValue(sem.getText().toString());
               mUserDatabase.child(key).child("title").setValue(title.getText().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                       databaseError.getDetails();
                       databaseError.getMessage();
                       databaseError.toException();
                       System.out.print("Database Error"+databaseError.getMessage());
            }
        });



    }

    private void firebaseUserSearch(int searchText) {

        Toast.makeText(SearchAssignment.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("assignid").equalTo(searchText);//startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseRecyclerAdapter<AssignmentSearch, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AssignmentSearch, UsersViewHolder>(

                AssignmentSearch.class,
                R.layout.assignmentlistlayout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, AssignmentSearch model, int position) {

               viewHolder.setDetails(model.getCourse(),model.getSemester(),model.getTitle(),model.getAssignment(),model.getAssignid());
                Log.d("hello", String.valueOf(model.assignid));
                //Toast.makeText(getApplicationContext(),model.getAssignment(),Toast.LENGTH_LONG).show();
                // viewHolder.setDetails(getApplicationContext(), model.getName(), model.getStatus(), model.getImage());
               // viewHolder.setDetails(getApplicationContext(),model.getStu_Id(),model.getStu_Email(),model.getStu_Course(),model.getImguri(),model.getStu_Year(),model.stu_Dob,model.getStu_Messg(),model.getStu_Gener(),model.stu_Mobile,model.stu_Father,model.getStu_City(),model.getStu_State(),model.getStu_address());
            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    public void onClick(View view) {

        Toast.makeText(getApplicationContext(),"onclck",Toast.LENGTH_LONG);

      if(view ==Update) {
          updateAssignment();
          Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
      }



    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails( String  Course, String Semester, String Title, String Assignment, int Assignid) {




             title = mView.findViewById(R.id.Title);
             assign= mView.findViewById(R.id.Edit_Message);
            assigid= mView.findViewById(R.id.Assignid);
            course= mView.findViewById(R.id.CourseSpinner);
            sem= mView.findViewById(R.id.Semester);

            title.setText(Title);
            assign.setText(Assignment);
            assigid.setText(Integer.toString(Assignid));
            course.setText(Course);
            sem.setText(Semester);

          /*
            ti=title.getText().toString();
            assi=assign.getText().toString();
            co=course.getText().toString();
            se=sem.getText().toString();*/





        }


    }

}
