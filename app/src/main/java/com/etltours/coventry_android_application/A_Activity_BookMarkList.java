package com.etltours.coventry_android_application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class A_Activity_BookMarkList extends Z_BaseActivity {
    A_Controller a_controller;
    LinearLayout linearLayout;
    TreeMap<String, LinearLayout> CellTree;

    Button clear_button;

    Button button;
    EditText search_bar;

    SharedPreferences prefs;

    public void ClearUnuseMarked_City() {
        String[] marked_city = prefs.getString("bookmark.marked_city", "default city").split(",");
        List<String> list = Arrays.asList(Y_Static.cities);

        String result = "";
        boolean begin = false;
        for (String ele : marked_city) {
            if (list.contains(ele)) {
                if (!begin)
                    begin = true;
                else
                    result += ",";
                result += ele;
            }
        }
        prefs.edit().putString("bookmark.marked_city", result).commit();
        System.out.println(result);
    }

    String search_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("A_Activity_BookMarkList start");
        prefs = getSharedPreferences("bookmark", Context.MODE_PRIVATE);
        ClearUnuseMarked_City();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__book_mark_list);
        linearLayout = findViewById(R.id.BookMarkList);
        search_bar = findViewById(R.id.SearchBar);
        search_bar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String key = search_bar.getText().toString();
                System.out.println(search_key + "!=" + key);
                if (!key.equals(search_key)) {
                    RenewCities(key);
                }
                search_key = key;

                return false;
            }
        });
        final A_Activity_BookMarkList _this = this;


        String marked_city = prefs.getString("bookmark.marked_city", "default city");
        marked_city = prefs.getString("bookmark.marked_city", "default city");
        System.out.println("marked_city: [" + marked_city + "] len:" + marked_city.length());

        clear_button = findViewById(R.id.clear_bookmark);
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().putString("bookmark.marked_city", "").commit();
                RenewCities("");
            }
        });

//        button = new Button(this);
        button = findViewById(R.id.AddBookMark_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_this, B_AddOrEdit_BookMark.class);
                startActivity(intent);
            }
        });

        CellTree = new TreeMap<>();
        a_controller = new A_Controller(this);
        RenewCities("");
    }

    TreeMap<String, Drawable> DrawableTree = new TreeMap<>();

    public void RenewCities(String keyword) {


        linearLayout.removeAllViews();

        System.out.println("Keyword = [" + keyword + "]");


        String[] marked_city = prefs.getString("bookmark.marked_city", "default city").split(",");
        if (marked_city.length == 1 && marked_city[0] == "") {
            return;
        }
        for (final String ele : marked_city) {

            if (!ele.contains(keyword) && !"".equals(keyword)) {
                continue;
            }
//            System.out.println(ele + " contain " + keyword);
//            System.out.println("display: " + ele);

            String[] arr = prefs.getString("bookmark." + ele, "default city").split("<a>");
            TreeMap<String, String> treeMap = new TreeMap<>();
            for (String a_ele : arr) {
                String[] list = a_ele.split("=");
                treeMap.put(list[0], list[1]);
            }
            String icon_name = treeMap.get("icon");
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(getVerticalPhotoLayoutParams());
            linearLayout.addView(imageView);
//            if (DrawableTree.containsKey(icon_name)) {
//                imageView.setImageDrawable(DrawableTree.get(icon_name));
//            } else {
            setImageViewBitmap(imageView, Y_Static.getImageURL(icon_name));
//                DrawableTree.put(icon_name, imageView.getDrawable());
//            }


            TextView textView = new TextView(this);
            textView.setTextSize(25);
            textView.setTextColor(Color.BLACK);
            textView.setText(
                    "City: " + ele + "\n"
                            + "Lon: " + treeMap.get("lon") + " Lat" + treeMap.get("lat") + "\n"
                            + treeMap.get("temp_min") + " F < " + treeMap.get("temp") + " F < " + treeMap.get("temp_max") + " F "
            );
            linearLayout.addView(textView);

            final A_Activity_BookMarkList _this = this;

            Button button = new Button(this);
            button.setText("Edit " + ele);
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(_this, B_AddOrEdit_BookMark.class);
                    intent.putExtra("city", ele);
                    startActivity(intent);
                }
            });
        }
    }

//    public void RenewCities() {

//        String[] marked_city = prefs.getString("bookmark.marked_city", "default city").split(",");
//        for (String ele : marked_city) {
//            String[] arr = prefs.getString("bookmark." + ele, "default city").split("<a>");
//            TreeMap<String, String> treeMap = new TreeMap<>();
//            for (String a_ele : arr) {
//                String[] list = a_ele.split("=");
//                treeMap.put(list[0], list[1]);
//            }
//            String icon_name = treeMap.get("icon");
//            ImageView imageView = new ImageView(this);
//            imageView.setLayoutParams(getVerticalPhotoLayoutParams());
//            linearLayout.addView(imageView);
//            setImageViewBitmap(imageView, Y_Static.getImageURL(icon_name));
//
//            TextView textView = new TextView(this);
//            textView.setTextSize(25);
//            textView.setTextColor(Color.BLACK);
//            textView.setText(
//                    "City: " + ele + "\n"
//                            + "Lon: " + treeMap.get("lon") + " Lat" + treeMap.get("lat") + "\n"
//                            + treeMap.get("temp_min") + " F < " + treeMap.get("temp") + " F < " + treeMap.get("temp_max") + " F "
//            );
//            linearLayout.addView(textView);
//        }
//    }


    public void DeleteCity(String city_name) {
        String[] marked_city = prefs.getString("bookmark.marked_city", "default city").split(",");
        String result = "";
        boolean begin = false;
        for (String ele : marked_city) {
            if (ele != city_name) {
                if (!begin)
                    begin = true;
                else
                    result += ",";
                result += ele;
            }
        }
        prefs.edit().putString("bookmark.marked_city", result).commit();
        System.out.println(result);
    }

}
