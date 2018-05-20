package merryweather.com.ltech.auth;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import merryweather.com.ltech.api.LTechService;
import merryweather.com.ltech.model.Entity;

/**
 * Created by S on 17.05.2018.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    private LTechService mService;

    private String mMask;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public LoginPresenter(LTechService lTechService) {
        mService = lTechService;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mDisposable.add(mService.getPhoneMask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((mask) -> {
                    mMask = mask.phoneMask;
                    getViewState().setMask(mMask);
                }, throwable -> {
                    getViewState().showToast("Unable to get mask");
                })
        );
    }

    public void login(String login, String password) {
        mDisposable.add(mService.auth(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    if (result.success)
                        getViewState().gotoList();
                }, throwable -> {
                    getViewState().showToast("Invalid credentials");
                })
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
