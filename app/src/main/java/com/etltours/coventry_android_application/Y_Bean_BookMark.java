package com.etltours.coventry_android_application;

import android.view.View;

/**
 * Created by leekwunfung on 5/22/18.
 */

public class Y_Bean_BookMark {
    View cell_view;
    String icon_name;
    String city_name;
    double lon;
    double lat;
    double temp;
    double temp_min;
    double temp_max;

    public Y_Bean_BookMark(String city_name, String icon_name, double lon, double lat, double temp, double temp_min, double temp_max) {
        this.city_name = city_name;
        this.icon_name = icon_name;
        this.lon = lon;
        this.lat = lat;
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public View getCell_view() {
        return cell_view;
    }

    public void setCell_view(View cell_view) {
        this.cell_view = cell_view;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public String[] toStrings() {
        return new String[]{
                city_name
                , icon_name,
                lon + "",
                lat + "",
                temp + "",
                temp_min + "",
                temp_max + ""
        };
    }

    @Override
    public String toString() {
        return "icon=" + icon_name + "<a>"
                + "lon=" + lon + "<a>"
                + "lat=" + lat + "<a>"
                + "temp=" + temp + "<a>"
                + "temp_min=" + temp_min + "<a>"
                + "temp_max=" + temp_max;
    }
}
