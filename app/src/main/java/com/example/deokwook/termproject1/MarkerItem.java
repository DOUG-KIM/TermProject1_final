package com.example.deokwook.termproject1;

/**
 * Created by Deok Woo K on 2016-06-20.
 */
public class MarkerItem {
    double lat;
    double lon;

    public MarkerItem(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


}
