package merryweather.com.ltech.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by S on 17.05.2018.
 */

public interface AbstractView extends MvpView {

    void showToast(String message);

}
