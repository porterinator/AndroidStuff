package merryweather.com.agentplus;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import merryweather.com.agentplus.repository.AgentplusRepository;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private AgentplusRepository mRepository;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    public MainPresenter(AgentplusRepository agentplusRepository) {
        mRepository = agentplusRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        mDisposable.add(mRepository.roman.subscribe(s -> {
            getViewState().showRoman(s);
        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    public void pause() {
        mRepository.pause();
    }

    public void resume() {
        mRepository.resume();
    }

}
