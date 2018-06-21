package merryweather.com.adorable.di.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.adorable.api.OpenWeatherMapApi;
import retrofit2.Retrofit;

/**
 * Created by S on 17.05.2018.
 */

@Module(includes = {RetrofitModule.class})
public class ApiModule {
	@Provides
	@Singleton
	public OpenWeatherMapApi providePopViewersApi(Retrofit retrofit) {
		return retrofit.create(OpenWeatherMapApi.class);
	}
}
