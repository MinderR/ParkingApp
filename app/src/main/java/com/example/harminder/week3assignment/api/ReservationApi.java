package com.example.harminder.week3assignment.api;

import android.database.Observable;

import com.example.harminder.week3assignment.model.MarkerInfo;
import com.example.harminder.week3assignment.model.Reservation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Harminder on 03/03/2018.
 */

public interface ReservationApi {



    @GET("search")
    Observable<List<Reservation>> listReservations(@Query("lat") String parameter1,
                                                   @Query("lng") String parameter2);

    Call<ArrayList<MarkerInfo>> getCustomMarkers();
}
