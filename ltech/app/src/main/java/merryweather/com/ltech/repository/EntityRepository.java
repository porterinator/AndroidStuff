package merryweather.com.ltech.repository;

import merryweather.com.ltech.model.Entity;

/**
 * Created by S on 19.05.2018.
 */

public class EntityRepository {

    private Entity mSelectedEntity;

    public Entity getSelectedEntity() {
        return mSelectedEntity;
    }

    public void setSelectedEntity(Entity mSelectedEntity) {
        this.mSelectedEntity = mSelectedEntity;
    }
}
