package com.etltours.coventry_android_application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class B_AddOrEdit_BookMark extends Z_BaseActivity {

    B_Controller b_controller;
    ListView listView;
    Y_Bean_BookMark currentBean;

    ImageView imageView;

    TextView city;
    TextView lon_and_lat;
    TextView temp;
    TextView temp_min_and_max;

    Button button;

    boolean is_edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b__add_or_edit__book_mark);

        final Intent intent = getIntent();
        is_edit = intent.hasExtra("city");

        b_controller = new B_Controller(this);
        listView = findViewById(R.id.CityListView);

        city = findViewById(R.id.CityName);
        lon_and_lat = findViewById(R.id.lon_and_lat);
        temp = findViewById(R.id.Tempreture);
        temp_min_and_max = findViewById(R.id.temp_min_and_max);
        imageView = findViewById(R.id.WeatherIcon);

        setTextListView(listView, Y_Static.cities);

        final B_AddOrEdit_BookMark _this = this;
        setViews(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setViews(position);
            }
        });


        button = findViewById(R.id.b_add_book_mark);
        if (button == null) {
            System.out.println("the button is null");
        } else {
            if (is_edit) {
                button.setText("Edit");
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (is_edit) {
                        SharedPreferences prefs = getSharedPreferences("bookmark", Context.MODE_PRIVATE);
                        String marked_city = prefs.getString("bookmark.marked_city", "default city");
                        marked_city.replace(intent.getStringExtra("city"), currentBean.getCity_name());
                        prefs.edit().putString("bookmark.marked_city", marked_city).commit();
                    }
//                    Y_Static.addBookMark(currentBean.toStrings());
                    Intent intent = new Intent(_this, A_Activity_BookMarkList.class);
                    startActivity(intent);
//                    if (currentBean != null) {
//                        intent.putExtra("icon", currentBean.getIcon_name());
//                        intent.putExtra("lon", currentBean.getLon());
//                        intent.putExtra("lat", currentBean.getLat());
//                        intent.putExtra("temp", currentBean.getTemp());
//                        intent.putExtra("temp_min", currentBean.getTemp_min());
//                        intent.putExtra("temp_max", currentBean.getTemp_max());
//                    }

                }
            });

        }
    }


    public void setViews(int position) {
        final String city_name = Y_Static.cities[position];
        System.out.println("set City " + city_name);
        city.setText("City: " + city_name);
        Y_Static.getOpenWeatherMap_by_CityName(
                city_name,
                new HTTPSResponse() {
                    @Override
                    public void Response(String response) {
                        try {
                            System.out.println("Response: " + response);
                            JSONObject jsonObject = new JSONObject(response);
                            String icon_name = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
                            double lon = jsonObject.getJSONObject("coord").getDouble("lon");
                            double lat = jsonObject.getJSONObject("coord").getDouble("lat");
                            double temp_ = jsonObject.getJSONObject("main").getDouble("temp");
                            double temp_min = jsonObject.getJSONObject("main").getDouble("temp_min");
                            double temp_max = jsonObject.getJSONObject("main").getDouble("temp_max");

                            setImageViewBitmap(imageView, Y_Static.getImageURL(icon_name));
                            lon_and_lat.setText("Lon: " + lon + " , Lat: " + lat);
                            temp.setText("Temp: " + temp_ + " C");
                            temp_min_and_max.setText(temp_min + " C to " + temp_max + " C");

                            currentBean = new Y_Bean_BookMark(
                                    city_name,
                                    icon_name
                                    , lon
                                    , lat
                                    , temp_
                                    , temp_min
                                    , temp_max
                            );
                            SharedPreferences prefs = getSharedPreferences("bookmark", Context.MODE_PRIVATE);
                            if (!is_edit) {
                                String marked_city = prefs.getString("bookmark.marked_city", "default city");
                                String[] bookmark_marked_city = marked_city.split(",");
                                if (!Arrays.asList(bookmark_marked_city).contains(city_name)) {
                                    if (bookmark_marked_city.length > 0) {
                                        prefs.edit().putString("bookmark.marked_city", marked_city + "," + city_name).commit();
                                    } else {
                                        prefs.edit().putString("bookmark.marked_city", city_name).commit();
                                    }
                                }
                            }
                            prefs.edit().putString("bookmark." + city_name, currentBean.toString()).commit();
                            System.out.println("marked_city:" + prefs.getString("bookmark.marked_city", "default city"));
                            System.out.println("added_city:" + prefs.getString("bookmark." + city_name, "default city"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
