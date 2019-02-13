package merryweather.com.agentplus;

import android.app.Application;
import android.content.Intent;

import merryweather.com.agentplus.di.ComponentManager;
import merryweather.com.agentplus.service.AgentplusService;


/**
 * Created by S on 17.05.2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.getInstance().init(this);
        Intent intent = new Intent(this, AgentplusService.class);
        startService(intent);
    }

}
