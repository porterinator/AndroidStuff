package merryweather.com.ltech.detail;

import merryweather.com.ltech.model.Entity;
import merryweather.com.ltech.view.AbstractView;

/**
 * Created by S on 19.05.2018.
 */

public interface EntityDetailView extends AbstractView {

    void showDetails(Entity entity);
}
