package androidwarriors.retrofit20example;

import androidwarriors.retrofit20example.pojo.Model;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by androidwarriors on 12/3/2015.
 */
public interface RestApi {

    @GET("/data/2.5/weather?q=London,uk&appid=683307260a76624ecbc9af4a036e110d")
    Call<Model> getWheatherReport();
}
