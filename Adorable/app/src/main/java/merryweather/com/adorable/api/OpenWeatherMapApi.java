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

    //http://samples.openweathermap.org/data/2.5/find?lat=57&lon=-2.15&cnt=3&appid=b6907d289e10d714a6e88b30761fae22

    @GET("forecast?cnt=3&units=metric&APPID=4116c3a5f8e9237889ba58e5c182d1da")
    Single<ForecastResponse> get3DayForecastWithCoords(@Query("lat") double lat, @Query("lon") double lon);
}
