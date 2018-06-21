package merryweather.com.adorable.api;

import io.reactivex.Single;
import merryweather.com.adorable.model.ForecastResponse;
import merryweather.com.adorable.model.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by S on 17.05.2018.
 */

public interface OpenWeatherMapApi {

    @GET("weather?APPID=4116c3a5f8e9237889ba58e5c182d1da&units=metric")
    Single<WeatherResponse> getaWeatherWithCoords(@Query("lat") double lat, @Query("lon") double lon);

    @GET("weather?APPID=4116c3a5f8e9237889ba58e5c182d1da&units=metric")
    Single<WeatherResponse > getaWeatherWithName(@Query("q") String cityName);


    @GET("forecast?cnt=3&units=metric&APPID=4116c3a5f8e9237889ba58e5c182d1da")
    Single<ForecastResponse> get3DayForecastWithCoords(@Query("lat") double lat, @Query("lon") double lon);

    @GET("forecast?cnt=3&units=metric&APPID=4116c3a5f8e9237889ba58e5c182d1da")
    Single<ForecastResponse> get3DayForecastWithName(@Query("q") String cityName);
}
