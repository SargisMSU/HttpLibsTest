package retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class WeatherRestAdapter {
    protected final String TAG = getClass().getSimpleName();
    protected Retrofit mRestAdapter;
    protected WeatherApi mApi;
    static final String WEATHER_URL = "http://api.openweathermap.org/";
    static final String OPEN_WEATHER_API = "51337ba29f38cb7a5664cda04d84f4cd";

    public WeatherRestAdapter() {
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = mRestAdapter.create(WeatherApi.class); // create the interface
    }

    public void testWeatherApi(String city, Callback<Weather> callback){
        System.out.println("testWeatherApi: for city: " + city);
        mApi.getWeatherFromApi(city, OPEN_WEATHER_API).enqueue(callback);
    }

    public Weather testWeatherApiSync(String city) {
        System.out.println("testWeatherApi: for city: " + city);

        Call<Weather> call = mApi.getWeatherFromApi(city, OPEN_WEATHER_API);
        Weather result = null;
        try {
            result = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
