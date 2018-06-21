package merryweather.com.adorable.di.component;

import android.content.Context;

import dagger.Component;
import merryweather.com.adorable.di.modules.FragmentModule;
import merryweather.com.adorable.di.scopes.PerFragment;


@PerFragment
@Component(dependencies = {ActivityComponent.class}, modules = FragmentModule.class)
public interface FragmentComponent {

    Context context();


}