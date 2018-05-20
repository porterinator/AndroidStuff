package merryweather.com.ltech.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.PolyMaskTextChangedListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import merryweather.com.ltech.R;
import merryweather.com.ltech.di.ComponentManager;
import merryweather.com.ltech.list.ListActivity;
import merryweather.com.ltech.view.AbstractActivity;

public class LoginActivity extends AbstractActivity implements LoginView {

    @Inject
    @InjectPresenter
    public LoginPresenter mPresenter;

    @BindView(R.id.login)
    public EditText edLogin;

    @BindView(R.id.password)
    public EditText edPassword;

    @BindView(R.id.signin)
    public Button mSignIn;

    @OnClick(R.id.signin)
    public void doSignin(){
        mPresenter.login(edLogin.getText().toString(), edPassword.getText().toString());
    }

    private String mLoginExtracted;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @ProvidePresenter
    public LoginPresenter providePresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getActivityComponent(this).inject(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setMask(String mask) {
        final List<String> affineFormats = new ArrayList<>();
        affineFormats.add(mask);
        MaskedTextChangedListener listener = new PolyMaskTextChangedListener(
                mask,
                affineFormats,
                edLogin,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue) {
                        mSignIn.setEnabled(maskFilled);
                        mLoginExtracted = extractedValue;
                    }
                }
        );
        edLogin.addTextChangedListener(listener);
        edLogin.setOnFocusChangeListener(listener);
        edLogin.setHint(listener.placeholder());
        edLogin.setEnabled(true);
        edPassword.setEnabled(true);
    }

    @Override
    public void gotoList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }
}
