package com.mcntraining.demo2.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcntraining.demo2.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HostelFragment extends Fragment {

    RecyclerView recyclerView;
     private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImageUrls=new ArrayList<>();
    public HostelFragment() {

}
public static final String TAG="MainActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       getImages();

       View v= inflater.inflate(R.layout.fragment_hostel, container, false);
        recyclerView=v.findViewById(R.id.recycler_view2);
        return v;
    }



    private void getImages()
    {
        mImageUrls.add("http://www.srgcmzn.com/images/demo/IMG_5733.JPG");
        mNames.add("Rooms");
        mImageUrls.add("http://www.srgcmzn.com/images/demo/IMG_5735.JPG");
        mNames.add("Rooms");
        mImageUrls.add("http://www.srgcmzn.com/images/demo/IMG_5763.JPG");
        mNames.add("Rooms");
        mImageUrls.add("http://www.srgcmzn.com/images/demo/IMG_6502.jpg");
        mNames.add("Rooms");
        mImageUrls.add("http://www.srgcmzn.com/images/demo/IMG_6503.jpg");
        mNames.add("Rooms");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FragmentActivity c=getActivity();
        LinearLayoutManager layoutManager=new LinearLayoutManager(c,LinearLayoutManager.HORIZONTAL,false);
        // RecyclerView recyclerView=view.findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterforhostel recyclerViewAdapterforhostel=new RecyclerViewAdapterforhostel(this,mNames,mImageUrls);
        recyclerView.setAdapter(recyclerViewAdapterforhostel);
    }


}
