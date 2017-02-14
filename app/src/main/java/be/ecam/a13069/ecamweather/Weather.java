package be.ecam.a13069.ecamweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Created by 13069 on 07-02-17.
 */

public class Weather {
    private static String city_name = null;

    private java.sql.Date day_stamp = null;
    private double degree_day = 0;
    private double degree_eve = 0;
    private double degree_morn = 0;
    private double degree_night = 0;
    private double day_pressure = 0;
    private double day_humidity = 0;
    private String day_weather_description = null;
    private double day_wind_speed = 0;

    //On stock en static l'ensemble des jours créés
    private static ArrayList<Weather> weathers = new ArrayList<>();

    //Methode pour parse le JSON
    public static void parse(String json) throws JSONException {

        //On récupère le JSON et on le met dans un JSON Object
        JSONObject jsonWeathers = new JSONObject(json);
        //On récupère du jsonWeathers le JSONObject city
        JSONObject jsonCity = jsonWeathers.getJSONObject("city");
        //On récupère du jsonCity le string name
        city_name = jsonCity.getString("name");

        JSONArray jsonList = jsonWeathers.getJSONArray("list");

        for (int i=0; i<jsonList.length(); i++) {
            JSONObject jsonWeather = jsonList.getJSONObject(i);
            JSONObject jsonWeatherInfo = jsonWeather.getJSONObject("temp");

            java.sql.Date day_stamp = new java.sql.Date(jsonWeather.getInt("dt"));
            double degree_day = jsonWeatherInfo.getDouble("day");
            double degree_eve = jsonWeatherInfo.getDouble("eve");
            double degree_morn = jsonWeatherInfo.getDouble("morn");
            double degree_night = jsonWeatherInfo.getDouble("night");

            double day_pressure = jsonWeather.getDouble("pressure");
            double day_humidity = jsonWeather.getDouble("humidity");
            String day_weather_description = jsonWeather.getJSONArray("weather").getJSONObject(0).getString("description");
            double day_wind_speed = jsonWeather.getDouble("speed");

            //Creation de l'objet et ajout dans la liste
            weathers.add(new Weather(day_stamp, degree_day, degree_eve, degree_morn, degree_night, day_pressure, day_humidity, day_weather_description, day_wind_speed));
        }
    }

    Weather(java.sql.Date day_stamp, double degree_day, double degree_eve, double degree_morn, double degree_night, double day_pressure, double day_humidity, String day_weather_description, double day_wind_speed){
        this.day_stamp = day_stamp;
        this.degree_day = degree_day;
        this.degree_eve = degree_eve;
        this.degree_morn = degree_morn;
        this.degree_night = degree_night;
        this.day_pressure = day_pressure;
        this.day_humidity = day_humidity;
        this.day_weather_description = day_weather_description;
        this.day_wind_speed = day_wind_speed;
    }

    public static Weather find(int index) {
        return weathers.get(index);
    }

    public static ArrayList<Weather> getWeathersList(){
        return weathers;
    }

    public Double getDegree_day(){
        return (double) Math.round(degree_day*100)/100;
    }

    public Double getDegree_eve(){
        return (double) Math.round(degree_eve*100)/100;
    }

    public Double getDegree_morn(){
        return (double) Math.round(degree_morn*100)/100;
    }

    public Double getDegree_night(){
        return (double) Math.round(degree_night*100)/100;
    }

    public Double getDay_pressure(){
        return (double) Math.round(day_pressure*100)/100;
    }

    public Double getDay_humidity(){
        return (double) Math.round(day_humidity*100)/100;
    }

    public String getDay_weather_description(){
        return day_weather_description;
    }

    public Double getDay_wind_speed(){
        return (double) Math.round(day_wind_speed*100)/100;
    }

}
