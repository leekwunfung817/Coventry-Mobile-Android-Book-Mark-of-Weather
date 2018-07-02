package com.etltours.coventry_android_application;

import java.io.InputStream;
import java.util.TreeMap;

import javax.security.auth.callback.Callback;

/**
 * Created by leekwunfung on 5/10/18.
 */

public class Y_DataHelper {
    final static String TEMP_METRIC = "metric";
    final static String TEMP_IMPERIAL = "Imperial";
    final static String key = "53a1030cde96142ee24fc12cafdec870";

    public String getTempType(int num) {
        if (num == 0) {
            return TEMP_METRIC;
        } else {
            return TEMP_IMPERIAL;
        }
    }

    public String OpenWeather(String place, final HTTPSResponse httpsResponse) {
        String url = "http://api.openweathermap.org/data/2.5/weather";
        TreeMap<String, String> parameter = new TreeMap<>();
        parameter.put("appid", key);
        parameter.put("q", place);
        HTTPS.Request(url, parameter, new HTTPSResponse() {
            @Override
            public void Response(String response) {
                System.out.println(response);
                httpsResponse.Response(response);
            }

            @Override
            public void Response(byte[] response) {

            }

            @Override
            public void Response(InputStream inputStream) {

            }
        });
        return "";

    }

    public String OpenWeather(String type, double lat, double lon, final HTTPSResponse httpsResponse) {
        String url = "http://api.openweathermap.org/data/2.5/weather";
        TreeMap<String, String> parameter = new TreeMap<>();
        parameter.put("appid", key);
        parameter.put("units", type);
        parameter.put("lat", lat + "");
        parameter.put("lon", lon + "");
        HTTPS.Request(url, parameter, new HTTPSResponse() {
            @Override
            public void Response(String response) {
                System.out.println(response);
                httpsResponse.Response(response);
            }

            @Override
            public void Response(byte[] response) {

            }

            @Override
            public void Response(InputStream inputStream) {

            }
        });
        return "";
    }

}
