package merryweather.com.ltech.detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import merryweather.com.ltech.repository.EntityRepository;

/**
 * Created by S on 19.05.2018.
 */

@InjectViewState
public class EntityDetailPresenter extends MvpPresenter<EntityDetailView> {


    private EntityRepository mEntityRepository;

    @Inject
    public EntityDetailPresenter(EntityRepository entityRepository) {
        mEntityRepository = entityRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showDetails(mEntityRepository.getSelectedEntity());
    }
}
