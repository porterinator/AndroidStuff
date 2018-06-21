package merryweather.com.adorable;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import merryweather.com.adorable.api.OpenWeatherMapService;
import merryweather.com.adorable.model.WeatherResponse;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by S on 20.06.2018.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private OpenWeatherMapService mService;
    private Context mContext;

    private WeatherResponse mCurreWeather;

    private CompositeDisposable mDisposable = new CompositeDisposable();


    private Location mCurrentLocation;

    @Inject
    public MainPresenter(OpenWeatherMapService service, Context context) {
        mService = service;
        mContext = context;
    }

    public boolean permissionsGranted() {
        return ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @SuppressWarnings({"MissingPermission"})
    public void obtainLastKnownLocation() {
        /**
         * threr is a possibility to get finer location
         * LocationRequest request = LocationRequest.create() //standard GMS LocationRequest
         .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
         .setNumUpdates(5)
         .setInterval(100);
         * Subscription subscription = locationProvider.getUpdatedLocation(request)
         * and unsubscribe on first update received
         * */
        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(mContext);
        mDisposable.add(
                locationProvider.getLastKnownLocation()
                        .timeout(10, TimeUnit.SECONDS)
                        .subscribe(location -> {
                            mCurrentLocation = location;
                            getCurrentWeatherAndForecast(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                        }, throwable -> {
                            if (throwable instanceof TimeoutException) {
                                getViewState().showToast("Unable to get last known location");
                            } else {
                                throwable.printStackTrace();
                                getViewState().showToast("Unable to get last known location");
                            }
                        })
        );
    }


    public void getCurrentWeatherAndForecast() {
        getCurrentWeatherAndForecast(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
    }

    public void getWeatherAndForecastWithCityName(String city) {
        mDisposable.add(
                mService.getaWeatherWithName(city)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((weatherResponse) -> {
                            mCurreWeather = weatherResponse;
                            getViewState().displayCurrentWeather(mCurreWeather);
                        }, throwable -> {
                            throwable.printStackTrace();
                            getViewState().showToast("Unable to get current weather");
                        })
        );
        mDisposable.add(
                mService.get3DayForecastWithName(city)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((forecastResponse) -> {
                            getViewState().display3DayForecast(forecastResponse.list);
                        }, throwable -> {
                            throwable.printStackTrace();
                            getViewState().showToast("Unable to get weather forecast");
                        })
        );
    }

    @Override
    protected void onFirstViewAttach() {
        if (permissionsGranted()) {
            obtainLastKnownLocation();
        } else {
            getViewState().requestPermissions();
        }

    }

    private void getCurrentWeatherAndForecast(double lat, double lon) {
        mDisposable.add(
                mService.getaWeatherWithCoords(lat, lon)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((weatherResponse) -> {
                            mCurreWeather = weatherResponse;
                            getViewState().displayCurrentWeather(mCurreWeather);
                        }, throwable -> {
                            throwable.printStackTrace();
                            getViewState().showToast("Unable to get current weather");
                        })
        );
        mDisposable.add(
                mService.get3DayForecastWithCoords(lat, lon)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((forecastResponse) -> {
                            getViewState().display3DayForecast(forecastResponse.list);
                        }, throwable -> {
                            throwable.printStackTrace();
                            getViewState().showToast("Unable to get weather forecast");
                        })
        );
    }

    public void onPermissionsGranted() {
        obtainLastKnownLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null )
            mDisposable.dispose();
    }

}
