package merryweather.com.agentplus.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.agentplus.repository.AgentplusRepository;

/**
 * Created by S on 19.05.2018.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public AgentplusRepository provideHarassmentRepository() {
        return new AgentplusRepository();
    }
}
