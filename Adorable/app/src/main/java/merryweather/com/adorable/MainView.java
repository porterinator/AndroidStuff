package merryweather.com.adorable;

import java.util.List;

import merryweather.com.adorable.model.City;
import merryweather.com.adorable.model.ForecastResponse;
import merryweather.com.adorable.model.WeatherResponse;

/**
 * Created by S on 20.06.2018.
 */

public interface MainView extends BaseView {

    void displayCurrentWeather(WeatherResponse weather);

    void display3DayForecast(List<ForecastResponse.ForecastItem> items);

    void requestPermissions();
}
