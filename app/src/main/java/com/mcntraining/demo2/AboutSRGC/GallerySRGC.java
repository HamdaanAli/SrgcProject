package com.mcntraining.demo2.AboutSRGC;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.mcntraining.demo2.R;

public class GallerySRGC extends AppCompatActivity {
    private Integer[] images = {
            R.drawable.image0,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11};
    private ImageView imageview;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_srgc);
        toolbar=(Toolbar) findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setTitle("GALLERY");
        toolbar.setTitleMarginStart(220);
        toolbar.setNavigationIcon(R.drawable.arrow_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Gallery imgGallery = (Gallery) findViewById(R.id.gallery);

        imgGallery.setAdapter(new ImageAdapter(this));
        imageview = (ImageView) findViewById(R.id.imageView);
        imgGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getApplicationContext(), "Image " + arg2, Toast.LENGTH_SHORT).show();
                imageview.setImageResource(images[arg2]);
            }
        });

    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        int imageBackground;

        public ImageAdapter(Context context) {

            this.context = context;
        }

        @Override
        public int getCount() {

            return images.length;
        }

        @Override
        public Object getItem(int arg0) {

            return arg0;
        }

        @Override
        public long getItemId(int arg0) {

            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            ImageView imageView = new ImageView(context);
            imageView.setImageResource(images[arg0]);
            return imageView;
        }
    }
}