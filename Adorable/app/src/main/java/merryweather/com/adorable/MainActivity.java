package merryweather.com.adorable;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import merryweather.com.adorable.di.ComponentManager;
import merryweather.com.adorable.model.City;
import merryweather.com.adorable.model.ForecastResponse;
import merryweather.com.adorable.model.Main;
import merryweather.com.adorable.model.WeatherResponse;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    @InjectPresenter
    public MainPresenter mPresenter;

    @BindView(R.id.weatherImage)
    public ImageView mImage;

    @BindView(R.id.temp)
    public TextView mTemp;

    @BindView(R.id.city)
    public TextView mCity;

    @BindView(R.id.description)
    public TextView mDescription;

    @BindView(R.id.forecast)
    public RecyclerView mForecast;

    private ForecastAdapter mAdapter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return mPresenter;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getActivityComponent(this).inject(this);
        super.onCreate(savedInstanceState);

        mForecast.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ForecastAdapter();
        mForecast.setAdapter(mAdapter);

    }

    @Override
    public void displayCurrentWeather(WeatherResponse weather) {
        mTemp.setText(String.format("%2.0f°", weather.main.temp));
        mCity.setText(weather.name);
        mDescription.setText(weather.weather.get(0).description);
        Glide.with(this).load(String.format("http://openweathermap.org/img/w/%s.png", weather.weather.get(0).icon))
        .into(mImage);
    }

    @Override
    public void display3DayForecast(List<ForecastResponse.ForecastItem> items) {
        mAdapter.setItems(this, items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }
                , 50);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 50) {
            if (mPresenter.permissionsGranted()) {
                mPresenter.onPermissionsGranted();
            } else {
                showToast("Missing required permissions");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cities_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.currentLocation :
                mPresenter.getCurrentWeatherAndForecast();
                break;
            case R.id.london :
                mPresenter.getWeatherAndForecastWithCityName("London");
                break;
            case R.id.paris :
                mPresenter.getWeatherAndForecastWithCityName("Paris");
                break;
            case R.id.tokyo :
                mPresenter.getWeatherAndForecastWithCityName("Tokyo");
                break;
            case R.id.newYork :
                mPresenter.getWeatherAndForecastWithCityName("New York");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
