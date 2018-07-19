package com.mcntraining.demo2.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.mcntraining.demo2.AboutSRGC.AboutSRGC;
import com.mcntraining.demo2.AboutSRGC.AcademicsActivity;
import com.mcntraining.demo2.AboutSRGC.AdmissionProcess;
import com.mcntraining.demo2.AboutSRGC.ContactUs;
import com.mcntraining.demo2.AboutSRGC.CourseActivity;
import com.mcntraining.demo2.Enquiry.Enquery;
import com.mcntraining.demo2.Fragments.Facilities;
import com.mcntraining.demo2.Faculty.Login_Faculty;
import com.mcntraining.demo2.AboutSRGC.GallerySRGC;
import com.mcntraining.demo2.AboutSRGC.Placement;
import com.mcntraining.demo2.R;
import com.mcntraining.demo2.AboutSRGC.Research;
import com.mcntraining.demo2.Student.Login_Student;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


        }
    }


    public AlbumAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());


         //loading college cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);



        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String n=  albumList.get(position).getName();

              Log.d("card click",n);
              if(n.equals("Courses"))
              {

              Intent intent=new Intent(v.getContext(),CourseActivity.class);
              v.getContext().startActivity(intent);

              }
              else if(n.equals("Academics"))
              {

                Intent intent=new Intent(v.getContext(),AcademicsActivity.class);
                v.getContext().startActivity(intent);
              }

              else if(n.equals("About Us"))
              {

                  Intent intent=new Intent(v.getContext(),AboutSRGC.class);
                  v.getContext().startActivity(intent);
              }
              else if(n.equals("Enquiry"))
              {

                  Intent intent=new Intent(v.getContext(),Enquery.class);
                  v.getContext().startActivity(intent);
              }

              else if(n.equals("Student Corner"))
              {

                  Intent intent=new Intent(v.getContext(),Login_Student.class);
                  v.getContext().startActivity(intent);
              }

              else if(n.equals("Faculty Corner"))
              {

                  Intent intent=new Intent(v.getContext(),Login_Faculty.class);
                  v.getContext().startActivity(intent);
              }else if(n.equals("Placements"))
              {
                  Intent intent=new Intent(v.getContext(),Placement.class);
                  v.getContext().startActivity(intent);
              }else if (n.equals("Admission Process"))
              {
                  Intent intent=new Intent(v.getContext(),AdmissionProcess.class);
                  v.getContext().startActivity(intent);
              }else if (n.equals("Facilities"))
              {
                  Intent intent=new Intent(v.getContext(),Facilities.class);
                  v.getContext().startActivity(intent);
              }else if (n.equals("Gallery"))
              {

                  Intent intent=new Intent(v.getContext(),GallerySRGC.class);
                  v.getContext().startActivity(intent);
              }
              else if (n.equals("Contact Us"))
              {

                  Intent intent=new Intent(v.getContext(),ContactUs.class);
                  v.getContext().startActivity(intent);
              }
              else if (n.equals("Contact Us"))
              {

                  Intent intent=new Intent(v.getContext(),ContactUs.class);
                  v.getContext().startActivity(intent);
              }else if (n.equals("Research"))
              {

                  Intent intent=new Intent(v.getContext(),Research.class);
                  v.getContext().startActivity(intent);

              }


            }
        });
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}