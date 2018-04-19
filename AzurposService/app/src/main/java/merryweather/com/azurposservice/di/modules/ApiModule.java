package merryweather.com.azurposservice.di.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.azurposservice.api.NSodeApi;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class ApiModule {
	@Provides
	@Singleton
	public NSodeApi provideJSONPlaceholdermApi(Retrofit retrofit) {
		return retrofit.create(NSodeApi.class);
	}
}
