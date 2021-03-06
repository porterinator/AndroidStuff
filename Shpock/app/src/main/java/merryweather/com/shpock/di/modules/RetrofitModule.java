package merryweather.com.shpock.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.shpock.di.qualifier.AppContext;
import merryweather.com.shpock.model.Item;
import merryweather.com.shpock.model.Items;
import merryweather.com.shpock.utils.ItemsDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by S on 19.04.2018.
 */

@Module
public class RetrofitModule {
	@Provides
	@Singleton
	public Retrofit provideRetrofit(Retrofit.Builder builder) {
		return builder.baseUrl("http://nsode.at/").build();
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
				.registerTypeAdapter(Items.class, new ItemsDeserializer())
				.create();
	}
}
