package com.android.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.android.test.domain.Response;
import com.android.test.restclient.FourSquareRestClient;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.util.functions.Func1;

/**
 * Created by JorgeCastilloPrz on 2/1/14.
 */
public class InitialFragment extends Fragment {

    private Button btnFindNearLocations;
    private EditText etCriteria;
    private String currentCityValue = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_fragment, container, false);

        btnFindNearLocations = (Button) rootView.findViewById(R.id.btnFindNearLocations);
        etCriteria = (EditText) rootView.findViewById(R.id.etCriteria);

        btnFindNearLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCityValue = etCriteria.getText().toString();
            }
        });

        Observable.from(currentCityValue).map(new Func1<String, Observable<Response>>() {
            @Override
            public Observable<Response> call(String s) {
                return FourSquareRestClient.getInstance().searchForVenues(etCriteria.getText().toString());
            }
        })
        .subscribeOn(Schedulers.currentThread())
        .subscribe(asdasd);

        return rootView;
    }
}
