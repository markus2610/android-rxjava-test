package com.android.test;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import com.android.test.domain.Respuesta;
import com.android.test.restclient.FourSquareRestClient;
import com.android.test.utils.LocationUtils;
import com.android.test.viewpager.ScreenSlidePagerAdapter;
import com.android.test.viewpager.ZoomOutPageTransformer;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import java.util.LinkedList;

/**
 * MainActivity
 * The 3 fragment pages are always loaded so they can react to changes on the observable list of locations
 */
public class MainActivity extends FragmentActivity implements LocationListener {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Activity activity;

    private LocationManager locationManager;
    private String provider;
    private Location currentLocation;
    private Criteria criteria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        initViewPager();
    }

    private void initViewPager() {

        mPager = (ViewPager) findViewById(R.id.pager);
        LinkedList<Fragment> fragments = new LinkedList<Fragment>();
        fragments.add(InitialFragment.getInstance());
        fragments.add(SecondFragment.getInstance());
        fragments.add(ThirdFragment.getInstance());

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setOffscreenPageLimit(1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(provider, 10, 50, this); //location request every 10 secs or 50 meters

        if (location != null) {
            onLocationChanged(location);
        } else {
            //Location not avaiable
        }

        //Check if GPS is actived. If its not we recommend the user to activate it.
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            LocationUtils.showAlertToEnableGPS(activity);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
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

    @Override
    public void onLocationChanged(Location location) {
        if (LocationUtils.isBetterLocation(location, currentLocation))
        {
            currentLocation = location;
            Observable<Respuesta> locationObservable = FourSquareRestClient.getInstance().searchForVenues(currentLocation.getLatitude() + ", " + currentLocation.getLongitude());
            locationObservable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread());
            locationObservable.subscribe(InitialFragment.getInstance());
            locationObservable.subscribe(SecondFragment.getInstance());
            locationObservable.subscribe(ThirdFragment.getInstance());
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

