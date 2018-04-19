package merryweather.com.azurposservice.di.modules;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.azurposservice.BuildConfig;
import merryweather.com.azurposservice.di.qualifier.AppContext;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
	@Provides
	@Singleton
	public Retrofit provideRetrofit(Retrofit.Builder builder) {
		return builder.baseUrl("https://jsonplaceholder.typicode.com/").build();
	}

	@Provides
	@Singleton
	public Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory, OkHttpClient client) {
		return new Retrofit.Builder()
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())
				.addConverterFactory(GsonConverterFactory.create()) // converterFactory
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
				.serializeNulls()
				.create();
	}
}
