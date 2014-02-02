package com.android.test.restclient;

import com.android.test.domain.Response;
import retrofit.Callback;
import retrofit.RestAdapter;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by JorgeCastilloPrz on 2/1/14.
 */
public class FourSquareRestClient {

    private static final String BASE_URL = "https://api.foursquare.com/v2";

    private RestAdapter restAdapter;
    private IFourSquareClient foursquareClient;
    private static FourSquareRestClient instance;

    protected FourSquareRestClient() {

        if (restAdapter == null || foursquareClient == null) {
            restAdapter = new RestAdapter.Builder()
                    .setServer(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            foursquareClient = restAdapter.create(IFourSquareClient.class);
        }
    }

    public static FourSquareRestClient getInstance() {
        if(instance == null) {
            instance = new FourSquareRestClient();
        }
        return instance;
    }

    public Observable<Response> searchForVenues(final String criteria) {

        final String client_id = "XJ2SW4KCTL3RJM1MM43DGPUTCNTHYDWCNLA3RMSJQQC0WNPQ";
        final String client_secret = "UWYKKAIU0JWTWICAGHLYFZ3HU1MT4Z35IJRWGFTBVLCGPE3W";

        final String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

        return foursquareClient.searchForVenues(criteria, client_id, client_secret, Long.parseLong(timeStamp));
    }
}
