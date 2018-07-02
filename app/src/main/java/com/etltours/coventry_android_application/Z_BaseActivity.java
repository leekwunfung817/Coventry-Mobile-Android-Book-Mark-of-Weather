package com.etltours.coventry_android_application;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class Z_BaseActivity extends AppCompatActivity {

    final AppCompatActivity _this = this;

    protected void ToastText(String txt) {
        Toast.makeText(_this, txt, Toast.LENGTH_SHORT).show();
    }

    ImageView TakePhotoOnceImageView;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected ImageView addImageView(int i, LinearLayout.LayoutParams layoutParams, LinearLayout view, View.OnClickListener onClickListener) {
        ImageView phone_call_icon = new ImageView(this);
        phone_call_icon.setImageResource(i);


        phone_call_icon.setLayoutParams(layoutParams);
        phone_call_icon.setOnClickListener(onClickListener);
        view.addView(phone_call_icon);

        return phone_call_icon;
    }

    protected String r(String filename) {
        return Z_FileHelper.r(ApplicationFile(filename));
    }

    protected void w(String filename, String content) {
        String file_path = ApplicationFile(filename);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Z_FileHelper.w(file_path, content);
        }
    }

    protected String ApplicationFile(String filename) {
        return getApplicationContext().getFilesDir().getPath().toString() + "/" + filename;
    }

    protected void TakePhotoOnce(int ViewId) {
        TakePhotoOnceImageView = findViewById(ViewId);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void TakePhotoOnce() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    TreeMap<String, Bitmap> BitmapTree;

    public void setImageViewBitmap(final ImageView imageView_b, final String urlString) {
        if (BitmapTree == null) {
            BitmapTree = new TreeMap<>();
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    if (BitmapTree.containsKey(urlString)) {
//                        System.out.println("reuse");
                        imageView_b.setImageBitmap(BitmapTree.get(urlString));
                    } else {
//                        System.out.println("request");
                        URL url = new URL(urlString);
                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        image = ResizeBitmapWidth(image, 400);
                        BitmapTree.put(urlString, image);
                        imageView_b.setImageBitmap(image);
                    }

                } catch (Exception e) {
//            System.out.println(e);
                }
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            OnTakePhotoOnce(imageBitmap);


            System.out.println("shape: " + imageBitmap.getWidth() + " " + imageBitmap.getHeight());
            double width = imageBitmap.getWidth();
            double height = imageBitmap.getHeight();
            double target_height = 100;
            double target_width = (width / height) * target_height;
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, (int) target_width, (int) target_height, false);
            TakePhotoOnceImageView.setImageBitmap(imageBitmap);
        }
    }

    protected Bitmap ResizeBitmapHeight(Bitmap bitmap, double target_height) {
        double width = bitmap.getWidth();
        double height = bitmap.getHeight();
//        double target_height = 100;
        double target_width = (width / height) * target_height;
        return Bitmap.createScaledBitmap(bitmap, (int) target_width, (int) target_height, false);
    }

    protected Bitmap ResizeBitmapWidth(Bitmap bitmap, double target_width) {
        double width = bitmap.getWidth();
        double height = bitmap.getHeight();

//        double target_width = 100;
        double target_height = (height / width) * target_width;
        return Bitmap.createScaledBitmap(bitmap, (int) target_width, (int) target_height, false);
    }

    protected void OnTakePhotoOnce(Bitmap imageBitmap) {

    }

    public static Bitmap Base64ToBitmap(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte.copy(Bitmap.Config.ARGB_8888, true);
    }

    protected String getPhoneNumber() {


        TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("no permission");
            ActivityCompat.requestPermissions(Z_BaseActivity.this,
                    new String[]{
                            Manifest.permission.READ_SMS,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_PHONE_NUMBERS
                    },
                    1);
//            return;
        }
        return tMgr.getLine1Number();
    }

    protected String Read(String key) {
        return r(key + ".txt");
    }

    protected void Save(String key, String value) {
        w(key + ".txt", value);
    }

    protected String Pair(String key, String value) {
        return ReadAndSave(key, value);
    }

    protected String ReadAndSave(String key, String value) {
        w(key + ".txt", value);
        return r(key + ".txt");
    }

    ListView MainListView;

    protected void setTextListView(ListView listView, String[] strings) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (String s : strings) {
            stringArrayList.add(s);
        }
        setTextListView(listView, stringArrayList);
    }

    protected void setTextListView(ListView listView, ArrayList<String> strings) {
        MainListView = listView;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this
                , android.R.layout.simple_list_item_1
                , android.R.id.text1
                , strings
        );
        MainListView.setAdapter(adapter);

    }

    protected void setTextListView(int id, ArrayList<String> strings) {
        setTextListView((ListView) findViewById(id), strings);
    }

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

    protected String PriceFilter(String txt) {
        String _return = "";
        boolean isNotZero = false;
        for (int i = 0; i < txt.length(); i++) {
            String sub = txt.substring(i);
            if (sub != "0")
                isNotZero = true;
            if (isNotZero)
                _return += sub;
        }
        return _return;
    }

    protected void PhoneCall(String PhoneNumber) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(Z_BaseActivity.this,
                    new String[]{
                            Manifest.permission.CALL_PHONE
                    },
                    1);
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PhoneNumber));
        startActivity(intent);
    }

    protected void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void StartIntent(Z_BaseActivity z_baseActivity, Class<?> cls, TreeMap<String, String> treeMap) {
        try {
            Intent intent = new Intent(z_baseActivity, cls);
            for (Map.Entry<String, String> entry : treeMap.entrySet())
                intent.putExtra(entry.getKey(), entry.getValue());

            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void intent(Z_BaseActivity z_baseActivity, Class<?> cls, TreeMap<String, String> treeMap) {
        StartIntent(z_baseActivity, cls, treeMap);
    }

}
