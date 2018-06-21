package merryweather.com.adorable.di.modules;

import android.support.v4.app.Fragment;

import dagger.Module;


@Module
public final class FragmentModule {

    private final Fragment fragment;

    public FragmentModule(final Fragment fragment) {
        this.fragment = fragment;
    }

}