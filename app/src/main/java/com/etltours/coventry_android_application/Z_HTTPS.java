package com.etltours.coventry_android_application;

/**
 * Created by leekwunfung on 2/15/18.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by CPOSID on 2018/1/27.
 */

class HTTPSHelper {

    String targetURL;
    TreeMap<String, String> urlParametersTreeMap;

//    Context context;
//
//    public Lib() {
//    }
//    public Lib(Context context) {
//        this.context = context;
//    }

    public class HttpsRequestAsyncTask extends AsyncTask<String, String, byte[]> {
        InputStream is;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected byte[] doInBackground(String... strings) {
            // Load CAs from an InputStream
// (could be from a resource or ByteArrayInputStream or ...)
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(
                                X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                X509Certificate[] certs, String authType) {
                        }
                    }
            };
            // Install the all-trusting trust manager
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (GeneralSecurityException e) {
            }
            System.out.println(new File(".").getAbsolutePath());
//        try {
//            CertificateFactory cf = null;
//            InputStream caInput = null;
//            cf = CertificateFactory.getInstance("X.509");
//// From https://www.washington.edu/itconnect/security/ca/load-der.crt
//            caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
//            Certificate ca;
//            ca = cf.generateCertificate(caInput);
//            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
//// Create a KeyStore containing our trusted CAs
//            String keyStoreType = KeyStore.getDefaultType();
//            KeyStore keyStore = null;
//            keyStore = KeyStore.getInstance(keyStoreType);
//            keyStore.setCertificateEntry("ca", ca);
//            keyStore.load(null, null);
//// Create a TrustManager that trusts the CAs in our KeyStore
//            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory tmf = null;
//            try {
//                tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//            tmf.init(keyStore);
//// Create an SSLContext that uses our TrustManager
//            SSLContext context = null;
//            context = SSLContext.getInstance("TLS");
//            context.init(null, tmf.getTrustManagers(), null);
//            caInput.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


            HttpsURLConnection connection = null;
            try {
                String urlParameters = "";
//                urlParametersTreeMap = new TreeMap<>();
                boolean begin = false;
                for (Map.Entry<String, String> entry : urlParametersTreeMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (begin) {
                        urlParameters += "&";
                    } else {
                        begin = true;
                    }
                    urlParameters += key + "=" + value;
                }
                System.out.println("Request: " + targetURL + "?" + urlParameters);
                //Create connection
                URL url = new URL(targetURL + "?" + urlParameters);
                connection = (HttpsURLConnection) url.openConnection();
//            connection.setRequestMethod(method);
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches(false);
                connection.setDoOutput(true);

                //Send request
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.close();

                //Get Response
                is = connection.getInputStream();


                byte[] buffer = new byte[8192];
                int bytesRead;
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                while ((bytesRead = is.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                byte[] bytes = output.toByteArray();
//
//                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//                StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    response.append(line);
//                    response.append('\r');
//                }
//
//                rd.close();
//                String rrespon = response.toString();
                String rrespon = new String(bytes);
//            System.out.println("<<<<<<<<<<");
//            System.out.println("<<<<<<<<<<");
//            System.out.println("<<<<<<<<<<");
//            System.out.println("<<<<<<<<<<");
                System.out.println("<<<<<<<<<<");
                System.out.println("request URL: " + targetURL + "?" + urlParameters);
                System.out.print("https result: [");
                System.out.print(rrespon.length());
                System.out.println("]");
                System.out.println(">>>>>>>>>>");
//            System.out.println(">>>>>>>>>>");
//            System.out.println(">>>>>>>>>>");
//            System.out.println(">>>>>>>>>>");
//            System.out.println(">>>>>>>>>>");
//                HttpsRequestResponse(rrespon);
//                onProgressUpdate(rrespon);
                return bytes;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            try {
                httpsResponse.Response(is);
                httpsResponse.Response(bytes);
                httpsResponse.Response(new String(bytes));
            } catch (Exception e) {

            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
//            for (int i = 0; i < values.length; i++) {
//                System.out.print(values[i]);
//            }
//            System.out.println("onProgressUpdate: " + values[0].toString());
//            Toast.makeText(context, values[0].toString(), Toast.LENGTH_SHORT).show();
        }

//        public void Bitmap Response()
    }

    private HTTPSResponseMather httpsResponse;

    public HTTPSHelper Request(String targetURL, TreeMap<String, String> urlParametersTreeMap, HTTPSResponseMather httpsResponse) {
        this.targetURL = targetURL;
        this.urlParametersTreeMap = urlParametersTreeMap;
//        this.urlParameters = urlParameters;
        this.httpsResponse = httpsResponse;
        new HttpsRequestAsyncTask().execute();

        return this;
    }

}

class HTTPS {
    public static void Request(String targetURL, TreeMap<String, String> urlParameters, HTTPSResponseMather httpsResponse) {
        new HTTPSHelper().Request(targetURL, urlParameters, httpsResponse);
    }

    public static Bitmap urlImageToBitmap(String urlImage) {
        Bitmap mIcon1 = null;
        URL url_value = null;
        try {
            url_value = new URL(urlImage);
            if (url_value != null) {
                mIcon1 = BitmapFactory.decodeStream(url_value.openConnection().getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mIcon1;
    }
}

//
//        new Lib() {
//@Override
//public void HttpsRequestResponse(String response) {
//        System.out.println("Main Thread received: " + response);
//        }
//        }.HttpsRequest("https://etltours.com/MVC/gm.php", "cr=" + choose_restuarent);
//

interface HTTPSResponseMather {
    void Response(String response);

    void Response(byte[] response);

    void Response(InputStream inputStream);
}

class HTTPSResponse implements HTTPSResponseMather {

    @Override
    public void Response(String response) {
        System.out.println("response String");
        try {
            ArrayList<TreeMap<String, String>> treeMapArrayList = Z_JSON.decode(response);
            ResponseArrayListTreeMap(treeMapArrayList);
            ArrayList<String> arrayList = new ArrayList<>();
            for (int a = 0; a < treeMapArrayList.size(); a++) {
                TreeMap<String, String> treeMap = treeMapArrayList.get(a);
                String result = a + " : ";
                for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                    result += entry.getKey() + " " + entry.getValue();
                }
                arrayList.add(result);
//                System.out.println(a);
            }
            ResponseArrayList(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ResponseArrayListTreeMap(ArrayList<TreeMap<String, String>> response) {

        for (TreeMap<String, String> treeMap : response) {
            try {
                ResponseRow(treeMap);
            } catch (Exception e) {

            }
        }

    }

    public void ResponseRow(TreeMap<String, String> treeMap) {

    }

    public void ResponseArrayList(ArrayList<String> response) {

    }


    @Override
    public void Response(byte[] response) {

    }

    public void Response(InputStream inputStream) {

    }
}


class MD5 {

    public static String encode(String password) {
        return md5(password);
    }

    public static String ApplicationMD5() {
        return MD5.encode(PasswordDatetime.get());
    }

    public static String md5(String input) {
        String result = input;
        if (input != null) {
            MessageDigest md = null; //or "SHA-1"
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md.update(input.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) { //40 for SHA-1
                result = "0" + result;
            }
        }
        System.out.println(result);
        return result;
    }
}


class PasswordDatetime {
    public static String get() {
        Date date = new Date();
//        ss
        SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMddHHmm");
        String datetime = ymd.format(date);
        String password = "lunchbox_password" + datetime;
        System.out.println(password);
        return password;
    }
}

class base64 {
    public static String encode(Bitmap bitmap) {
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageString;
    }

    public static Bitmap decode(String imageString) {
        //decode base64 string to image
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }
}
