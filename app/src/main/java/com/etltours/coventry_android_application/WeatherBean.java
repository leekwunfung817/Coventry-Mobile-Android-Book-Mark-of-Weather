package com.etltours.coventry_android_application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by leekwunfung on 4/17/18.
 */

public class WeatherBean {
    Coord coord;
    Main main;
    Wind wind;
    Clouds clouds;
    SysElement sys;
    ArrayList<WeatherElement> weather;
    String base;
    int visibility, dt, id, name, cod;


    public WeatherBean(JSONObject jsonObject) {
//        Coord coord, Main main, Wind wind, Clouds clouds, SysElement sys, ArrayList<WeatherElement> weather, String base, int visibility, int dt, int id, int name, int cod
        try {
            this.coord = new Coord(jsonObject.getJSONObject("coord"));
            this.main = new Main(jsonObject.getJSONObject("main"));
            this.wind = new Wind(jsonObject.getJSONObject("wind"));
            this.clouds = new Clouds(jsonObject.getJSONObject("clouds"));
            this.sys = new SysElement(jsonObject.getJSONObject("sys"));
            this.weather = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            for (int i = 0; i < jsonArray.length(); i++) {
                weather.add(new WeatherElement(jsonArray.getJSONObject(i)));
            }
            this.base = jsonObject.getString("base");
            this.visibility = jsonObject.getInt("visibility");
            this.dt = jsonObject.getInt("dt");
            this.id = jsonObject.getInt("id");
            this.name = jsonObject.getInt("name");
            this.cod = jsonObject.getInt("cod");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public SysElement getSys() {
        return sys;
    }

    public void setSys(SysElement sys) {
        this.sys = sys;
    }

    public ArrayList<WeatherElement> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherElement> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }


}


class Coord {
    float lon, lat;

    public Coord(JSONObject para) {
        try {
            lon = Float.parseFloat(para.get("lon") + "");
            lat = Float.parseFloat(para.get("lat") + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}

class WeatherElement {
    String id;
    String main;
    String description;
    String icon;

    public WeatherElement(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.main = jsonObject.getString("main");
            this.description = jsonObject.getString("description");
            this.icon = jsonObject.getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

class SysElement {
    String country;
    int type, id, message, sunrise, sunset;

    public SysElement(JSONObject jsonObject) {
        try {
            this.country = jsonObject.getString("country");
            this.type = jsonObject.getInt("type");
            this.id = id;
            this.message = message;
            this.sunrise = sunrise;
            this.sunset = sunset;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}

class Main {
    double temp, temp_min, temp_max;
    int pressure, humidity;

    public Main(JSONObject jsonObject) {
        try {
            this.temp = jsonObject.getDouble("temp");
            this.temp_min = jsonObject.getDouble("temp_min");
            this.temp_max = jsonObject.getDouble("temp_max");
            this.pressure = jsonObject.getInt("pressure");
            this.humidity = jsonObject.getInt("humidity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}

class Wind {
    double speed;
    int deg;

    public Wind(JSONObject jsonObject) {
        try {
            this.speed = jsonObject.getDouble("speed");
            this.deg = jsonObject.getInt("deg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Clouds {
    int all;

    public Clouds(JSONObject jsonObject) {
        try {
            this.all = jsonObject.getInt("all");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}