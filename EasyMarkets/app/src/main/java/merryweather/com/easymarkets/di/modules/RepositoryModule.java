package merryweather.com.easymarkets.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.easymarkets.repository.AppartmentRepository;

/**
 * Created by S on 19.05.2018.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public AppartmentRepository provideAppartmentRepository() {
        return new AppartmentRepository();
    }
}
