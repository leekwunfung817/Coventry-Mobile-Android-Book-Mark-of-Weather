package com.etltours.coventry_android_application;

import android.os.Build;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by leekwunfung on 5/22/18.
 */

public class Y_Static {

    public static String[] cities = new String[]{
            "Russkiy Kameshkir", "Alekseyevka", "Oral", "London", "Japan", "Bayganin", "Kotputli", "Behror", "Bawal", "Dharuhera", "Gurgaon", "Naberezhnyye Chelny", "Minsk", "Bugulma", "Karabash", "Ussuriysk", "Vorokhta", "Varanasi", "Ramnagar", "Mughal Sarai", "Satna", "Panna", "Bulandshahr", "Shikarpur", "Dibai", "Naraura", "Sahaswan", "Ujhani", "Kakrala", "Usehat", "Jalalabad", "Allahganj", "Hardoi", "Sandila", "Malihabad", "Kakori", "Lucknow", "Goshainganj", "Satrikh", "Zaidpur", "Tikaitnagar", "Bhairahawa", "Bharatpur", "Kirtipur", "Patan", "Stavyshche", "Brovary", "Semypolky", "Barvikha", "Petrovo-Dalneye", "Nikolina Gora", "Zvenigorod", "Tuchkovo", "Kratovo", "Konobeyevo", "Voskresensk", "Rechitsy", "Monino", "Obukhovo", "Imeni Vorovskogo", "Elektrostal", "Pavlovskiy Posad", "Drezna", "Opalikha", "Novopetrovskoye", "Verbilki", "Ashitkovo", "Shaturtorf", "Bolshiye Vyazemy", "Zhavoronki", "Medvezhyegorsk", "Obolensk", "Serpukhov", "Pyaozerskiy", "Loukhi", "Amsterdam", "Paris", "Konakovo", "Novozavidovskiy", "Kozlovo", "Loni", "Mitzpe Ramon", "Raipur", "Terny", "Donskoye", "Yaremche", "Plotnikovo", "Novopodrezkovo", "Skhodnya", "Zelenograd", "Alabushevo", "Petrodvorets", "Strelna", "Horad Smalyavichy", "Horad Zhodzina", "Namche Bazar", "Udaipur", "Pali", "Jodhpur", "Jaisalmer", "Pushkar", "Ajmer", "Kanpur", "Gaya", "Bodh Gaya", "Ulyanovsk", "Mosrentgen", "Rublevo", "Yam", "Stupino", "Muranovo", "Solnechnogorsk", "Pokrov", "Shchelkovo", "Fryazino", "Sofrino", "Domodedovo", "Yakovlevskoye", "Chernogolovka", "Fryanovo", "Nakhabino", "Sharya", "Manturovo", "Makaryev", "Kovernino", "Gorodets", "Zavolzhye", "Pestyaki", "Gorokhovets", "Murom", "Yelatma", "Kasimov", "Izhevskoye", "Spassk-Ryazanskiy", "Murmino", "Ryazan", "Rybnoye", "Zaraysk", "Ozherelye", "Fryazevo", "Zvenigovo", "Kozmodemyansk", "Bor", "Nizhniy Novgorod", "Neklyudovo", "Palekh", "Yuzha", "Kameshkovo", "Kideksha", "Suzdal", "Gavrilov Posad", "Yuryev-Polskiy", "Bavleny", "Kolchugino", "Smolensk", "Dubrowna", "Orsha", "Baran", "Shklow", "Mahilyow", "Buynichy", "Kirawsk", "Babruysk", "Asipovichy", "Kapyl", "Nyasvizh", "Haradzyeya", "Mir", "Karelichy", "Navahrudak", "Slonim", "Pruzhany", "Brest", "Kommunar", "Bogolyubovo", "Kostroma", "Susanino", "Ivanovo", "Furmanov", "Privolzhsk", "Ples", "Krasnoye-na-Volge", "Kotlas", "Privodino", "Krasavino", "Velikiy Ustyug", "Nyuksenitsa", "Totma", "Soligalich", "Chukhloma", "Galich", "Elektrougli", "Staraya", "Foros", "Miskhor", "Balaklava", "Yalta", "Massandra", "Nikita", "Inkerman", "Sevastopol", "Alushta", "Perevalnoe", "Sudak", "Koktebel", "Feodosiya", "Saky", "Uyutne", "Yevpatoriya", "Kerch", "Mysove"
    };

    public static String OpenWeather_APIKey = "53a1030cde96142ee24fc12cafdec870";
    public static String OpenWeatherMap_by_CityName_URL = "https://api.openweathermap.org/data/2.5/weather";
    public static String[] OpenWeatherMap_CityName_Types = new String[]{"Imperial", "metric"};

    public static void getOpenWeatherMap_by_LatLon(String type, double lat, double lon, final HTTPSResponse httpsResponse) {
        TreeMap parameter = new TreeMap<>();
        parameter.put("appid", OpenWeather_APIKey);
        parameter.put("units", type);
        parameter.put("lat", lat);
        parameter.put("lon", lon);
        HTTPS.Request(OpenWeatherMap_by_CityName_URL, parameter, new HTTPSResponse() {
            @Override
            public void Response(String response) {
                httpsResponse.Response(response);
            }
        });
    }

    public static void getOpenWeatherMap_by_CityName(
//            String type,
            String CityName
            , final HTTPSResponse httpsResponse) {
        TreeMap parameter = new TreeMap<>();
        parameter.put("appid", OpenWeather_APIKey);
//        parameter.put("units", type);
        parameter.put("q", CityName);
        HTTPS.Request(OpenWeatherMap_by_CityName_URL, parameter, new HTTPSResponse() {
            @Override
            public void Response(String response) {
                httpsResponse.Response(response);
            }
        });
    }

    private static String BookMarkFileName = "bookmark";

    public static String getBookMarkTextContent() {
        String content = Z_FileHelper.r(BookMarkFileName);
        if (content == null) {
            System.out.println("Mull Content");
            Z_FileHelper.w(BookMarkFileName,"d<a>qe<a>fv<a>w<a>a<a>s,q<a>br<a>qw<a>v<a>fs<a>ac");
        } else if (content == "") {
            System.out.println("Mull Empty");
            Z_FileHelper.w(BookMarkFileName,"d<a>qe<a>fv<a>w<a>a<a>s,q<a>br<a>qw<a>v<a>fs<a>ac");
        }
        return content;
    }

    private static String[] getBookMarkContent() {
        return Z_FileHelper.r_csv(BookMarkFileName);
    }

    private static void setBookMarkContent(String[] content) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Z_FileHelper.w_csv(BookMarkFileName, content);
        }
    }

    private static String separate_mark = "<a>";

    public static TreeMap<String, String[]> getBookMark() {
        TreeMap<String, String[]> treeMap = new TreeMap<>();
        String[] arr = getBookMarkContent();
        for (String ele : arr) {
            String[] ele_arr = ele.split(separate_mark);
            treeMap.put(ele_arr[0], ele_arr);
        }
        return treeMap;
    }

    private static void setBookMark(TreeMap<String, String[]> treeMap) {
        String[] result = new String[treeMap.size()];
        int count = 0;
        for (Map.Entry<String, String[]> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            String[] arr = entry.getValue();
            String res = "";
            boolean begin = false;
            for (String ele : arr) {
                if (begin)
                    res += separate_mark;
                else
                    begin = true;
                res += ele;
            }
            result[count] = res;
            count++;
            setBookMarkContent(result);
        }
    }

    public static void addBookMark(String[] content) {
        TreeMap<String, String[]> treeMap = getBookMark();
        treeMap.put(content[0], content);
        setBookMark(treeMap);
    }

    public static void EditBookMark(String[] content) {
        addBookMark(content);
    }

    public static void deleteBookMark(String key) {
        TreeMap<String, String[]> treeMap = getBookMark();
        treeMap.remove(key);
        setBookMark(treeMap);
    }

    public static String getImageURL(String ImageName) {
        return "https://openweathermap.org/img/w/" + ImageName + ".png";
    }
}
