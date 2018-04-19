package merryweather.com.shpock.di.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.shpock.api.NSodeApi;
import retrofit2.Retrofit;

/**
 * Created by S on 19.04.2018.
 */

@Module(includes = {RetrofitModule.class})
public class ApiModule {
	@Provides
	@Singleton
	public NSodeApi provideNsodeApi(Retrofit retrofit) {
		return retrofit.create(NSodeApi.class);
	}
}
