package merryweather.com.easymarkets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import merryweather.com.easymarkets.di.ComponentManager;
import merryweather.com.easymarkets.model.Appartment;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @Inject
    @InjectPresenter
    public MainPresenter mPresenter;

    @BindView(R.id.list)
    public RecyclerView mList;

    @BindView(R.id.search)
    public EditText mSearch;

    @BindView(R.id.availableStart)
    public EditText mStart;

    @BindView(R.id.availableEnd)
    public EditText mEnd;

    @BindView(R.id.doSearch)
    public Button mDoSearch;

    private AppAdapter mAdapter;


    @ProvidePresenter
    public MainPresenter provMainPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getActivityComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AppAdapter();
        mList.setAdapter(mAdapter);

        mDoSearch.setOnClickListener(v -> {
            mPresenter.search(Integer.parseInt(mSearch.getText().toString()),
                        Utils.stringToDate(mStart.getText().toString()),
                        Utils.stringToDate(mEnd.getText().toString())
                    );
        });

        mAdapter.getPositionClicks().subscribe(appartment -> {
           mPresenter.book(appartment);
        });
    }

    @Override
    public void showAppartments(List<Appartment> apps) {
        mAdapter.setEntities(this, apps);
        mAdapter.notifyDataSetChanged();
    }
}
