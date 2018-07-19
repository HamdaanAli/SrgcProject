package com.mcntraining.demo2.MainActivity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mcntraining.demo2.CheckConnectivity.ConnectivityReceiver;
import com.mcntraining.demo2.CheckConnectivity.MyApplication;
import com.mcntraining.demo2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_album);
         checkConnection();
        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.mipmap.logo_srgc).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {

                    collapsingToolbar.setTitle(getString(R.string.app_name));

                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.about,
                R.drawable.enquiry,
                R.drawable.cources,
                R.drawable.academics1,
                R.drawable.admission_process,
                R.drawable.facilities,
                R.drawable.placements,
                R.drawable.research,
                R.drawable.gal1,
                R.drawable.contact,
                R.drawable.studentcorner,
                R.drawable.facultycorner,
                R.drawable.cources};

        Album a = new Album("About Us", covers[0]); //About US
        albumList.add(a);

        a = new Album("Enquiry", covers[1]);   //Enquery
        albumList.add(a);

        a = new Album("Courses", covers[2]); //Our Courses
        albumList.add(a);

        a = new Album("Academics", covers[3]);  //Academics
        albumList.add(a);

        a = new Album("Admission Process", covers[4]); //Admissions
        albumList.add(a);

        a = new Album("Facilities",covers[5]);
        albumList.add(a);

        a = new Album("Placements", covers[6]);
        albumList.add(a);

        a = new Album("Research", covers[7]);
        albumList.add(a);

        a = new Album("Gallery", covers[8]);
        albumList.add(a);

        a = new Album("Contact Us", covers[9]);
        albumList.add(a);

        a = new Album("Student Corner", covers[10]);
        albumList.add(a);

        a = new Album("Faculty Corner", covers[11]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }
private void checkConnection()
{
    boolean isConnected=ConnectivityReceiver.isConnected();
    if(isConnected)
        Toast.makeText(getApplicationContext(),"Connected to Internet",Toast.LENGTH_LONG).show();
    else
        Toast.makeText(getApplicationContext(),"Not Connected to Internet",Toast.LENGTH_LONG).show();
}
    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isConnected)
        Toast.makeText(getApplicationContext(),"Connected to Internet",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Not Connected to Internet",Toast.LENGTH_LONG).show();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}