package merryweather.com.easymarkets;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

import merryweather.com.easymarkets.model.Appartment;

public interface MainView extends MvpView {

    void showAppartments(List<Appartment> apps);

}
