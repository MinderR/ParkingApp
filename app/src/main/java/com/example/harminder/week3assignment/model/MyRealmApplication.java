package com.example.harminder.week3assignment.model;

import android.app.Application;

import com.example.harminder.week3assignment.api.ReservationApi;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Harminder on 03/03/2018.
 */

public class MyRealmApplication extends Application {

    private ArrayList<MarkerInfo> mMarkerInfoHolder;

    @Override
    public void onCreate() {
        super.onCreate();

        /**     added coded here

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
     */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ridecellparking.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReservationApi api = retrofit.create(ReservationApi.class);

        final Call<ArrayList<MarkerInfo>> markerInfor = api.getCustomMarkers();

        markerInfor.enqueue(new Callback<ArrayList<MarkerInfo>>() {

            @Override
            public void onResponse(Call<ArrayList<MarkerInfo>> call, Response<ArrayList<MarkerInfo>> reply) {
                if (reply.isSuccessful()) {
                    ArrayList<MarkerInfo> body = reply.body();
                    mMarkerInfoHolder = body;

                    Realm realmDb1 = Realm.getDefaultInstance();

                    //Only use this if i want to wipe the realm database

                    realmDb1.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realmDb) {
                            RealmResults<MarkerInfo> output = realmDb.where(MarkerInfo.class).findAll();
                            output.deleteAllFromRealm();
                            System.out.println("Removed Data");

                        }
                    });

                    RealmResults<MarkerInfo> mVerifySize = realmDb1.where(MarkerInfo.class).findAll();
                    System.out.println(mVerifySize.size());

                    if (mVerifySize.size() != 20) {
                        System.out.println("Stored Data");

                        realmDb1.beginTransaction();

                        for (int x = 0; x < body.size(); x++) {
                            MarkerInfo reservationInfo = realmDb1.createObject(MarkerInfo.class);
                         //   reservationInfo.setCost_per_minute(mMarkerInfoHolder.get(x).getCost_per_minute());
                         //   reservationInfo.setId(mMarkerInfoHolder.get(x).getId());
                          //  reservationInfo.setIs_reserved(mMarkerInfoHolder.get(x).isIs_reserved());
                         //   reservationInfo.setLat(mMarkerInfoHolder.get(x).getLat());
                          //  reservationInfo.setLng(mMarkerInfoHolder.get(x).getLng());
                         //   reservationInfo.setMax_reserve_time_mins(mMarkerInfoHolder.get(x).getMax_reserve_time_mins());
                         //   reservationInfo.setMin_reserve_time_mins(mMarkerInfoHolder.get(x).getMin_reserve_time_mins());
                         //   reservationInfo.setName(mMarkerInfoHolder.get(x).getName());
                         //   reservationInfo.setReserved_until(mMarkerInfoHolder.get(x).getReserved_until());

                        }
                        realmDb1.commitTransaction();
                        System.out.println(mVerifySize.size());

                    }


                } else {
                    printMessage("Unsuccessful Response");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MarkerInfo>> call, Throwable throwable) {
                printMessage(throwable.getMessage());

                //When there is no connection
                System.out.println("Realm Database is completely full.");

                Realm realmDb1 = Realm.getDefaultInstance();

                RealmResults<MarkerInfo> mReceivedInfo = realmDb1.where(MarkerInfo.class).findAll();
                for (int i = 0; i < mReceivedInfo.size(); i++) {
                //    mMarkerInfoHolder.add(new MarkerInfo(mReceivedInfo.get(i).getId(), mReceivedInfo.get(i).getLat(),
                //            mReceivedInfo.get(i).getLng(), mReceivedInfo.get(i).getName(), mReceivedInfo.get(i).getCost_per_minute(),
                 //           mReceivedInfo.get(i).getMax_reserve_time_mins(), mReceivedInfo.get(i).getMin_reserve_time_mins(),
                 //           mReceivedInfo.get(i).isIs_reserved(), mReceivedInfo.get(i).getReserved_until()));
                }
            }
        });
    }


    private void printMessage(String message) {
        System.out.println(message);
    }



    }

