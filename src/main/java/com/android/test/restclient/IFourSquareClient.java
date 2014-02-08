package com.android.test.restclient;

import com.android.test.domain.Respuesta;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by JorgeCastilloPrz on 2/1/14.
 */
public interface IFourSquareClient {
    @GET("/venues/search")
    Observable<Respuesta> searchForVenues(@Query("ll") String ll, @Query("radius") Long radius,  @Query("client_id") String client_id,
                                         @Query("client_secret") String client_secret, @Query("timeStamp") long timeStamp);
}
