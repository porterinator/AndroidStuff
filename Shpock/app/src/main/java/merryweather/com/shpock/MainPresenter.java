package merryweather.com.shpock;

import android.content.Context;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import merryweather.com.shpock.api.NsodeService;
import merryweather.com.shpock.di.scopes.PerActivity;
import merryweather.com.shpock.model.Items;

/**
 * Created by S on 19.04.2018.
 */

@PerActivity
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private NsodeService mService;
    private Items mItems;
    private Disposable mDisposable;

    @Inject
    MainPresenter(NsodeService service) {
        mService = service;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProgress();
        mDisposable = mService.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((items) -> {
                    mItems = items;
                    getViewState().showItems(mItems.getItems());
                    getViewState().hideProgress();
                }, throwable -> {
                    getViewState().showToast("Unable to get items");
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
