package merryweather.com.adorable.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.adorable.di.qualifier.AppContext;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by S on 17.05.2018.
 */

@Module
public class RetrofitModule {

	private final static String BASE_URL = "http://api.openweathermap.org/data/2.5/";

	@Provides
	@Singleton
	public Retrofit provideRetrofit(Retrofit.Builder builder) {
		return builder.baseUrl(BASE_URL).build();
	}

	@Provides
	@Singleton
	public Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory, OkHttpClient client) {
		return new Retrofit.Builder()
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())
				.addConverterFactory(converterFactory) // converterFactory
				.client(client);
	}

	@Provides
	@Singleton
	public OkHttpClient provideOkHttpClient(@AppContext Context context) {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
		return  client;
	}

	@Provides
	@Singleton
	public Converter.Factory provideConverterFactory(Gson gson) {
		return GsonConverterFactory.create(gson);
	}

	@Provides
	@Singleton
    Gson provideGson() {
		return new GsonBuilder()
				.create();
	}
}
