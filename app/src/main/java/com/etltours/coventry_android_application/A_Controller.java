package com.etltours.coventry_android_application;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by leekwunfung on 5/22/18.
 */

public class A_Controller extends Z_BaseController {
    A_Activity_BookMarkList a_bookMarkList;
    TreeMap<String, String[]> treeMap;

    public A_Controller(A_Activity_BookMarkList a_bookMarkList) {
        this.a_bookMarkList = a_bookMarkList;
    }

    public void Renew() {
        treeMap = Y_Static.getBookMark();
        for (Map.Entry<String, String[]> ele : treeMap.entrySet()) {
            String key = ele.getKey();
            String[] arr = ele.getValue();
        }
    }

}
