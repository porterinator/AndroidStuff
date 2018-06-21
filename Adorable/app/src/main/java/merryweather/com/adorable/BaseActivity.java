package merryweather.com.adorable;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import merryweather.com.adorable.utils.DebugUtils;

/**
 * Created by S on 04.06.2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView {

    protected Unbinder mUnbinder;

    protected abstract int getContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mUnbinder = ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Wake the device and show our activity
        if (BuildConfig.DEBUG) {
            // Calling this from your launcher activity is enough, but I needed a good example spot ;)
            DebugUtils.riseAndShine(this);
        }
    }

    protected void customViewInit() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) mUnbinder.unbind();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
