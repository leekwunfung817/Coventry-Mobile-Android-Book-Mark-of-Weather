package com.etltours.coventry_android_application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by leekwunfung on 3/13/18.
 */

public class Z_JSON {

    public static ArrayList<TreeMap<String, String>> decode(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        int record_count = jsonObject.length() - 1;
        ArrayList<TreeMap<String, String>> MainTreeMap = new ArrayList<>();
        for (int a = 1; a < record_count; a++) {
            TreeMap<String, String> treeMap = new TreeMap<>();
            JSONObject jo = ((JSONObject) jsonObject.get(a + ""));
            JSONArray NameJSONArray = jo.names();
            for (int b = 0; b < jo.length(); b++) {
                String ColumnName = NameJSONArray.get(b).toString();
                treeMap.put(ColumnName, jo.getString(ColumnName));
            }
            MainTreeMap.add(treeMap);
        }
        return MainTreeMap;
    }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                _this
////                            , android.R.layout.simple_list_item_1
//                , android.R.layout.simple_list_item_1
//                , android.R.id.text1
//                , restaurant_name);
//        restuarant_menu.setAdapter(adapter);
//        for (Map.Entry<String,String> : treeMap.entrySet()) {
//
//        }

}
