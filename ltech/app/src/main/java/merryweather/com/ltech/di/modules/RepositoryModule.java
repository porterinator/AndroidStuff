package merryweather.com.ltech.di.modules;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import merryweather.com.ltech.repository.EntityRepository;

/**
 * Created by S on 19.05.2018.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public EntityRepository provideEntityRepository() {
        return new EntityRepository();
    }
}
