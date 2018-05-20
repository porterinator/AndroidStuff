package merryweather.com.ltech.di.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.ltech.api.LTechApi;
import retrofit2.Retrofit;

/**
 * Created by S on 17.05.2018.
 */

@Module(includes = {RetrofitModule.class})
public class ApiModule {
	@Provides
	@Singleton
	public LTechApi provideLTechApi(Retrofit retrofit) {
		return retrofit.create(LTechApi.class);
	}
}
