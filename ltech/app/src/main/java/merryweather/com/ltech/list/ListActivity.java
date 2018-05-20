package merryweather.com.ltech.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import merryweather.com.ltech.R;
import merryweather.com.ltech.detail.EntityDetailActivity;
import merryweather.com.ltech.di.ComponentManager;
import merryweather.com.ltech.model.Entity;
import merryweather.com.ltech.view.AbstractActivity;

public class ListActivity extends AbstractActivity implements EntityListView {

    @Inject
    @InjectPresenter
    public EntityListPresenter mPresenter;

    @BindView(R.id.list)
    public RecyclerView mList;

    private EntitiesAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_list;
    }

    @ProvidePresenter
    public EntityListPresenter providePresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getActivityComponent(this).inject(this);
        super.onCreate(savedInstanceState);

        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EntitiesAdapter();
        mList.setAdapter(mAdapter);
        mAdapter.getPositionClicks().subscribe(entity -> {
           mPresenter.selectEntity(entity);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh :
                mPresenter.forseRefresh();
                break;
        }
        return false;
    }

    @Override
    public void showEntities(ArrayList<Entity> entities) {
        mAdapter.setEntities(this, entities);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void gotoDetailActivity() {
        Intent intent = new Intent(this, EntityDetailActivity.class);
        startActivity(intent);
    }


}
