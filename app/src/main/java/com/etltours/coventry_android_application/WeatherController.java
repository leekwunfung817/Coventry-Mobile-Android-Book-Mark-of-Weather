package com.etltours.coventry_android_application;

import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class WeatherController {

    public static void getWeathers(final String lat, final String lon, final WeatherResponser callback) {
        String url = "http://api.openweathermap.org/data/2.5/weather";
        TreeMap<String, String> parameter = new TreeMap<>();
        parameter.put("appid", "b6907d289e10d714a6e88b30761fae22");
        parameter.put("lat", lat);
        parameter.put("lon", lon);
        HTTPS.Request(url, parameter, new HTTPSResponse() {
            @Override
            public void Response(String response) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    String filename = lat + "," + lon;
                    FileHelper.w(filename, response);
                    String result = FileHelper.r(filename);
                    System.out.println("File : " + result);
                }
                try {
                    WeatherBean weatherBean = new WeatherBean(new JSONObject(response));
                    callback.ResponseWeather(weatherBean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

//    public static
}
