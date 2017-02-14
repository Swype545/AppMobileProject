package be.ecam.a13069.ecamweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherinfo);

        TextView day_title = (TextView) findViewById(R.id.day_title);
        TextView day_description = (TextView) findViewById(R.id.day_description);
        TextView morn_temperature = (TextView) findViewById(R.id.morn_temperature);
        TextView day_temperature = (TextView) findViewById(R.id.day_temperature);
        TextView even_temperature = (TextView) findViewById(R.id.even_temperature);
        TextView night_temperature = (TextView) findViewById(R.id.night_temperature);
        TextView day_pressure = (TextView) findViewById(R.id.day_pressure);
        TextView day_humidity = (TextView) findViewById(R.id.day_humidity);
        TextView wind_speed = (TextView) findViewById(R.id.wind_speed);

        Intent intent = getIntent();
        Weather weather = Weather.find(intent.getIntExtra(Intent.EXTRA_INDEX, 0));

        day_title.setText("Day Informations");

        day_description.setText("Description: "+ weather.getDay_weather_description());
        morn_temperature.setText("Morning temperature: "+weather.getDegree_morn().toString());
        day_temperature.setText("Day temperature: "+weather.getDegree_day().toString());
        even_temperature.setText("Evening temperature: "+weather.getDegree_eve().toString());
        night_temperature.setText("Night temperature: "+weather.getDegree_night().toString());
        day_pressure.setText("Day pressure: "+weather.getDay_pressure().toString());
        day_humidity.setText("Day humidity: "+weather.getDay_humidity().toString());
        wind_speed.setText("Wind speed: "+weather.getDay_wind_speed().toString());

    }
}
