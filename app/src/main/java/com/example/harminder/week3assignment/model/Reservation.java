package com.example.harminder.week3assignment.model;

import io.realm.RealmObject;

/**
 * Created by Harminder on 03/03/2018.
 */

public class Reservation extends RealmObject {
    private int id;
    private String lat;
    private String lng;
    private String name;
    private String cost_per_minute;
    private int max_reserve_time_mins;
    private int min_reserve_time_mins;
    private boolean is_reserved;
    private String reserved_until;

    public int getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public String getCost_per_minute() {
        return cost_per_minute;
    }

    public int getMax_reserve_time_mins() {
        return max_reserve_time_mins;
    }

    public int getMin_reserve_time_mins() {
        return min_reserve_time_mins;
    }

    public boolean isIs_reserved() {
        return is_reserved;
    }

    public String getReserved_until() {
        return reserved_until;
    }

    public void setIs_reserved(boolean is_reserved) {
        this.is_reserved = is_reserved;
    }


    /**
     *
     * all setters method
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost_per_minute(String cost_per_minute) {
        this.cost_per_minute = cost_per_minute;
    }

    public void setMax_reserve_time_mins(int max_reserve_time_mins) {
        this.max_reserve_time_mins = max_reserve_time_mins;
    }

    public void setMin_reserve_time_mins(int min_reserve_time_mins) {
        this.min_reserve_time_mins = min_reserve_time_mins;
    }

    public void setReserved_until(String reserved_until) {
        this.reserved_until = reserved_until;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", name='" + name + '\'' +
                ", cost_per_minute='" + cost_per_minute + '\'' +
                ", max_reserve_time_mins='" + max_reserve_time_mins + '\'' +
                ", min_reserve_time_mins='" + min_reserve_time_mins + '\'' +
                ", is_reserved=" + is_reserved + '\'' +
                ", reserved_until=" + reserved_until +
                '}';
    }
}