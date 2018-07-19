package com.mcntraining.demo2.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcntraining.demo2.R;

import java.util.ArrayList;

/**
 * Created by HAMDAN on 14-Apr-18.
 */

public class RecyclerViewAdapterforhostel extends RecyclerView.Adapter<RecyclerViewAdapterforhostel.ViewHolder> {

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImageUrls=new ArrayList<>();
    private HostelFragment mContext;

    public RecyclerViewAdapterforhostel(HostelFragment context, ArrayList<String> name, ArrayList<String> imageUrls) {
        this.mNames =name;
        this.mImageUrls = imageUrls;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(mContext).load(mImageUrls.get(position)).into(holder.image);
        //holder.name.setText(mNames.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
        }
    }
}
