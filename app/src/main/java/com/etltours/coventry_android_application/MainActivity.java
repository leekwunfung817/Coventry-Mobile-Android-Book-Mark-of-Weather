package com.etltours.coventry_android_application;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Z_BaseActivity {
    ImageView mimageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, A_Activity_BookMarkList.class);
        startActivity(intent);
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            System.out.println("shape: " + imageBitmap.getWidth() + " " + imageBitmap.getHeight());
//            double width = imageBitmap.getWidth();
//            double height = imageBitmap.getHeight();
//            double target_height = 100;
//            double target_width = (width / height) * target_height;
//            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, (int) target_width, (int) target_height, false);
//            mimageView.setImageBitmap(imageBitmap);
//        }
//    }
}
