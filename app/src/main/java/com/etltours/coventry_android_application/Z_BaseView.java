package com.etltours.coventry_android_application;

import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * Created by leekwunfung on 5/22/18.
 */

public class Z_BaseView {

    protected LinearLayout.LayoutParams getVerticalLayoutParams() {
        LinearLayout.LayoutParams VERTICAL_Parameter =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        VERTICAL_Parameter.gravity = Gravity.CENTER;
//        VERTICAL_Parameter.setMargins(100, 50, 100, 50);
        return VERTICAL_Parameter;
    }

    protected LinearLayout.LayoutParams getVerticalPhotoLayoutParams() {
        LinearLayout.LayoutParams VERTICAL_Parameter =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        VERTICAL_Parameter.gravity = Gravity.CENTER;
//        VERTICAL_Parameter.setMargins(100, 50, 100, 50);
        return VERTICAL_Parameter;
    }

    protected LinearLayout.LayoutParams getHorizontalPhotoLayoutParams() {
        LinearLayout.LayoutParams Photo_VERTICAL_Parameter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        Photo_VERTICAL_Parameter.gravity = Gravity.CENTER;
        Photo_VERTICAL_Parameter.weight = 5;
//        Photo_VERTICAL_Parameter.setMargins(100, 50, 100, 50);
        return Photo_VERTICAL_Parameter;
    }

    protected LinearLayout.LayoutParams getHorizontalLayoutParams() {
        LinearLayout.LayoutParams VERTICAL_Parameter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        VERTICAL_Parameter.gravity = Gravity.CENTER;
        VERTICAL_Parameter.weight = 2;
//        VERTICAL_Parameter.setMargins(100, 50, 100, 50);

        return VERTICAL_Parameter;
    }
}
