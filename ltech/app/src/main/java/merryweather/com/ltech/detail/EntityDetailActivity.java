package merryweather.com.ltech.detail;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import merryweather.com.ltech.R;
import merryweather.com.ltech.di.ComponentManager;
import merryweather.com.ltech.list.EntityListPresenter;
import merryweather.com.ltech.model.Entity;
import merryweather.com.ltech.view.AbstractActivity;

public class EntityDetailActivity extends AbstractActivity implements EntityDetailView {

    @Inject
    @InjectPresenter
    public EntityDetailPresenter mPresenter;

    @BindView(R.id.image)
    public ImageView mImage;

    @BindView(R.id.title)
    public TextView mTitle;

    @BindView(R.id.text)
    public TextView mText;

    @ProvidePresenter
    public EntityDetailPresenter providePresenter() {
        return mPresenter;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_entity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getActivityComponent(this).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showDetails(Entity entity) {
        Glide.with(this)
                .load(entity.image)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mImage);
        mTitle.setText(entity.title);
        mText.setText(entity.text);
    }
}
