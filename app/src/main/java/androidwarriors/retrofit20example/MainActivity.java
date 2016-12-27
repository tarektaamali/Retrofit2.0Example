package androidwarriors.retrofit20example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidwarriors.retrofit20example.pojo.Model;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    String url = "http://api.openweathermap.org";
    TextView txt_city, txt_status, txt_humidity, txt_pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_humidity = (TextView) findViewById(R.id.txt_humidity);
        txt_pressure = (TextView) findViewById(R.id.txt_press);


        getReport();
    }

    void getReport() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi service = retrofit.create(RestApi.class);

        Call<Model> call = service.getWheatherReport();

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Response<Model> response, Retrofit retrofit) {

                try {
                    String city = response.body().getName();

                    String status = response.body().getWeather().get(0).getDescription();

                    String humidity = response.body().getMain().getHumidity().toString();

                    String pressure = response.body().getMain().getPressure().toString();

                    txt_city.setText("city  :  " + city);
                    txt_status.setText("status  :  " + status);
                    txt_humidity.setText("humidity  : " + humidity);
                    txt_pressure.setText("pressure  :  " + pressure);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
