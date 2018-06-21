package merryweather.com.adorable.api;


import io.reactivex.Single;
import merryweather.com.adorable.model.ForecastResponse;
import merryweather.com.adorable.model.WeatherResponse;
import retrofit2.http.Query;

/**
 * Created by S on 17.05.2018.
 */

public class OpenWeatherMapService {

    private OpenWeatherMapApi mApi;

    public OpenWeatherMapService(OpenWeatherMapApi mApi) {
        this.mApi = mApi;
    }

    public Single<WeatherResponse> getaWeatherWithCoords(double lat, double lon) {
        return mApi.getaWeatherWithCoords(lat, lon);
    }

    public Single<WeatherResponse> getaWeatherWithName(String cityName) {
       return mApi.getaWeatherWithName(cityName);
    }

    public Single<ForecastResponse> get3DayForecastWithCoords(double lat, double lon){
        return mApi.get3DayForecastWithCoords(lat, lon);
    }

    public Single<ForecastResponse> get3DayForecastWithName(String cityName){
        return mApi.get3DayForecastWithName(cityName);
    }

}
