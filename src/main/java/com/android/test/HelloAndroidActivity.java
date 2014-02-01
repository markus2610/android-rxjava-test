package com.android.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import com.android.test.ViewPager.ScreenSlidePagerAdapter;
import com.android.test.ViewPager.ZoomOutPageTransformer;
import java.util.LinkedList;

public class HelloAndroidActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPager();
    }

    private void initViewPager() {
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        LinkedList<Fragment> fragments = new LinkedList<Fragment>();
        fragments.add(new InitialFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    /**
     * Returns to the previous page if we are not on the initial one
     */
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(com.android.test.R.menu.main, menu);
	return true;
    }

}

