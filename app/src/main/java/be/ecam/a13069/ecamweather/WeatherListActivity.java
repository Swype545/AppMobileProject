package be.ecam.a13069.ecamweather;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler{
    TextView text;
    private RecyclerView resultView ;
    private ItemAdapter itemAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        resultView = (RecyclerView) findViewById(R.id.resultView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        resultView.setLayoutManager(layoutManager);
        resultView.setHasFixedSize(true);


        //text = (TextView) findViewById(R.id.text);
        itemAdapter = new ItemAdapter(this);
        resultView.setAdapter(itemAdapter);

        //Creation d'une nouvelle QueryTask, au lancement de la page
        new QueryTask().execute("https://andfun-weather.udacity.com/weather");

    }

    //Reception d'informations asynchrone (tâche de fond)
    public class QueryTask extends AsyncTask<String, Void, ArrayList<Weather>> {

        @Override
        protected ArrayList<Weather> doInBackground(String... params) {
            String searchUrl = params[0];
            String json = null;
            ArrayList<Weather> queryResults = null;
            try {
                json = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                Weather.parse(json);
                queryResults = Weather.getWeathersList();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryResults;
        }

        @Override
        protected void onPostExecute(ArrayList<Weather> queryResults) {
            itemAdapter.setData(queryResults);
        }
    }

    @Override
    public void onClick(int index) {
        Context context = this;
        Class destinationClass = WeatherActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_INDEX, index);
        startActivity(intent);
    }

}
