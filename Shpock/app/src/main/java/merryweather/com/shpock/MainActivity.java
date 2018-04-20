package merryweather.com.shpock;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import merryweather.com.shpock.di.ComponentManager;
import merryweather.com.shpock.model.Item;

/**
 * Created by S on 19.04.2018.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @Inject
    @InjectPresenter
    public MainPresenter mPresenter;

    @BindView(R.id.list)
    public RecyclerView mList;

    @BindView(R.id.progressBar1)
    public ProgressBar mProgress;

    private ItemsAdapter mAdapter;

    private Unbinder mUnbinder;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getActivityComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ItemsAdapter();
        mList.setAdapter(mAdapter);

    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showItems(List<Item> items) {
        mAdapter.setItems(this, items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
