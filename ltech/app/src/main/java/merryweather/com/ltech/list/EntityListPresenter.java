package merryweather.com.ltech.list;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import merryweather.com.ltech.api.LTechService;
import merryweather.com.ltech.model.Entity;
import merryweather.com.ltech.repository.EntityRepository;

/**
 * Created by S on 19.05.2018.
 */

@InjectViewState
public class EntityListPresenter extends MvpPresenter<EntityListView> {

    private LTechService mService;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private ArrayList<Entity> mEntities;

    private EntityRepository mEntityRepository;

    @Inject
    public EntityListPresenter(LTechService service, EntityRepository entityRepository) {
        mService = service;
        mEntityRepository = entityRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        //TODO: move to repository
        mDisposable.add(
               mService.getList()
               .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .repeatWhen(objectFlowable -> objectFlowable.delay(5, TimeUnit.SECONDS))
                       .subscribe((list) -> {
                           mEntities = list;
                           getViewState().showEntities(mEntities);
                       }, throwable -> {
                           getViewState().showToast("Unable to load list");
                       })
        );
    }

    //TODO: move to repository
    public void forseRefresh() {
        mDisposable.add(
                mService.getList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((list) -> {
                            mEntities = list;
                            getViewState().showEntities(mEntities);
                        }, throwable -> {
                            getViewState().showToast("Unable to load list");
                        })
        );
    }

    public void selectEntity(Entity entity) {
        mEntityRepository.setSelectedEntity(entity);
        getViewState().gotoDetailActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
