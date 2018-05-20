package merryweather.com.ltech.list;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import merryweather.com.ltech.model.Entity;
import merryweather.com.ltech.view.AbstractView;

/**
 * Created by S on 19.05.2018.
 */

public interface EntityListView extends AbstractView {

    void showEntities(ArrayList<Entity> entities);

    void gotoDetailActivity();
}
