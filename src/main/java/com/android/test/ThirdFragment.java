package com.android.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.test.domain.Respuesta;
import com.android.test.domain.Venue;
import rx.Observer;

import java.util.List;

/**
 * Created by JorgeCastilloPrz on 2/1/14.
 */
public class ThirdFragment extends Fragment implements Observer<Respuesta> {

    private List<Venue> venues;

    private static ThirdFragment instance = null;
    protected ThirdFragment() {
    }
    public static ThirdFragment getInstance() {
        if(instance == null) {
            instance = new ThirdFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.third_fragment, container, false);

        return rootView;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onNext(Respuesta respuesta) {

        venues = respuesta.getResponse().getVenues();
    }
}
