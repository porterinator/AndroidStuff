package merryweather.com.agentplus;

import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import merryweather.com.agentplus.di.ComponentManager;

public class MainActivity extends MvpAppCompatActivity implements MainView {


    @Inject
    @InjectPresenter
    public MainPresenter mainPresenter;

    @BindView(R.id.counter)
    public TextView mCounter;

    private Unbinder mUnbinder;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return mainPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getActivityComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void showRoman(String value) {
        mCounter.setText(value);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.pause)
    public void pause() {
        mainPresenter.pause();
    }

    @OnClick(R.id.resume)
    public void resume() {
        mainPresenter.resume();
    }
}
