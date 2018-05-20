package merryweather.com.ltech.auth;

import com.arellomobile.mvp.MvpView;

import merryweather.com.ltech.view.AbstractView;

/**
 * Created by S on 17.05.2018.
 */

public interface LoginView extends AbstractView {

    void setMask(String mask);

    void gotoList();
}
