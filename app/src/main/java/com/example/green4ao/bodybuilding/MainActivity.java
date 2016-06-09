package com.example.green4ao.bodybuilding;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private int position = 0, totalImage;
    private ArrayList<Integer> mItemData;
    private Images mImageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mImageId = new Images();
        mItemData = mImageId.getImageItem();
        totalImage = mItemData.size();
        setPage(position);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),mItemData);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        MainActivity.this.position = position;
                        setPage(position);

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setPage(int page) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private Integer itemData;
        private Bitmap myBitmap;
        public ProgressDialog pd;
        private ImageView mImageView;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(){
            PlaceholderFragment fragment = new PlaceholderFragment();
            return fragment;
        }
        /* Old method
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        */

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mImageView = (ImageView) rootView.findViewById(R.id.imageView);
            setImageOnViewPager();
            return rootView;
        }

        public void setImageList(Integer integer) {
            this.itemData = integer;
        }

        /*
            Picasso or simple BitMap???
         */
        private void setImageOnViewPager(){
            try {
                //if image size is too large. Need to scale as below code.
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                myBitmap = BitmapFactory.decodeResource(getResources(), itemData, options);
                if (options.outWidth > 3000 || options.outHeight > 2000) {
                    options.inSampleSize = 4;
                } else if (options.outWidth > 2000 || options.outHeight > 1500) {
                    options.inSampleSize = 3;
                } else if (options.outWidth > 1000 || options.outHeight > 1000) {
                    options.inSampleSize = 2;
                }
                options.inJustDecodeBounds = false;
                myBitmap = BitmapFactory.decodeResource(getResources(), itemData,options);
                if (myBitmap != null) {
                    try {
                        if (mImageView != null) {
                            mImageView.setImageBitmap(myBitmap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.gc();
            }

        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            if (myBitmap != null) {
                myBitmap.recycle();
                myBitmap = null;
            }
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Integer> mItemData;
        public SectionsPagerAdapter(FragmentManager fm, ArrayList<Integer> itemData) {
            super(fm);
            mItemData = itemData;
        }



        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            PlaceholderFragment frag = PlaceholderFragment.newInstance();
            frag.setImageList(mItemData.get(position));
            return frag;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return mItemData.size();
        }



        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

    }
}
