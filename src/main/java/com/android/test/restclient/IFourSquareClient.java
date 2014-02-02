package com.android.test.restclient;

import com.android.test.domain.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by JorgeCastilloPrz on 2/1/14.
 */
public interface IFourSquareClient {
    @GET("/venues/search")
    Observable<Response> searchForVenues(@Query("near") String place, @Query("client_id") String client_id,
                                         @Query("client_secret") String client_secret, @Query("timeStamp") long timeStamp);
}
