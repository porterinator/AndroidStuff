package merryweather.com.shpock;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import merryweather.com.shpock.model.Item;

/**
 * Created by S on 19.04.2018.
 */

public interface MainView extends MvpView {

    void showProgress();
    void showItems(List<Item> items);
    void hideProgress();

    @StateStrategyType(SkipStrategy.class)
    void showToast(String text);
}
