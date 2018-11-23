package merryweather.com.tech45.login

import android.arch.lifecycle.ViewModel
import io.reactivex.Scheduler

import javax.inject.Inject

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import merryweather.com.tech45.api.TechService
import merryweather.com.tech45.model.LoginResponse

class LoginViewModel @Inject
constructor(private val mTechService: TechService) : ViewModel() {

    val login = BehaviorSubject.create<LoginResponse>()
    val disposable = CompositeDisposable();

    fun login(userName: String, password: String) {
        if (!login.hasValue())
            disposable.add(mTechService.login(userName, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { result -> login.onNext(result) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
