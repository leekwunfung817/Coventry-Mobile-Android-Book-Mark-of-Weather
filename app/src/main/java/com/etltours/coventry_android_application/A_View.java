package com.etltours.coventry_android_application;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.TreeMap;

/**
 * Created by leekwunfung on 5/22/18.
 */

public class A_View extends Z_BaseView {
    A_Activity_BookMarkList a_bookMarkList;

    public A_View(A_Activity_BookMarkList a_bookMarkList) {
        this.a_bookMarkList = a_bookMarkList;
    }

    public void addCell(String key, TreeMap<String, String> treeMap) {
        LinearLayout linearLayout = new LinearLayout(this.a_bookMarkList);
        linearLayout.setLayoutParams(getVerticalLayoutParams());


        String icon_name = treeMap.get("icon");
        ImageView imageView = new ImageView(a_bookMarkList);
        imageView.setLayoutParams(getVerticalPhotoLayoutParams());
        a_bookMarkList.setImageViewBitmap(imageView, Y_Static.getImageURL(icon_name));
        TextView lon = new TextView(a_bookMarkList);
        lon.setText(treeMap.get("lon"));
        linearLayout.addView(lon);
        TextView lat = new TextView(a_bookMarkList);
        lat.setText(treeMap.get("lat"));
        linearLayout.addView(lat);
        TextView temp = new TextView(a_bookMarkList);
        temp.setText(treeMap.get("temp"));
        linearLayout.addView(temp);
        TextView temp_min = new TextView(a_bookMarkList);
        temp_min.setText(treeMap.get("temp_min"));
        linearLayout.addView(temp);
        TextView temp_max = new TextView(a_bookMarkList);
        temp_max.setText(treeMap.get("temp_max"));
        linearLayout.addView(temp_max);

        a_bookMarkList.linearLayout.addView(imageView);


    }


}
