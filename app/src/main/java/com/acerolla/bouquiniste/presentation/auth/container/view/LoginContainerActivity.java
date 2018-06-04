package com.acerolla.bouquiniste.presentation.auth.container.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.auth.container.presenter.ILoginContainerPresenter;
import com.acerolla.bouquiniste.presentation.auth.login.view.LoginFragment;
import com.acerolla.bouquiniste.presentation.auth.register.view.RegisterFragment;
import com.acerolla.bouquiniste.presentation.main.view.MainView;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginContainerActivity extends AppCompatActivity implements ILoginContainerView {

    public static final int REQUEST_LOGIN = 78;

    private LoginContainerView mView;

    @Inject
    ILoginContainerPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new LoginContainerView(this);
        setContentView(mView);

        DiManager.getLoginContainerComponent().inject(this);
        mPresenter.bindView(this);
    }

    @Override
    public void navigateToRegister() {
        showRegistration();
    }

    @Override
    public void navigateToLogin() {
        showLogin();
    }

    @Override
    public void showLogin() {
        changeFragment(new LoginFragment());
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(MainView.ID_CONTENT_FRAME, fragment)
                .commit();
    }

    @Override
    public void showRegistration() {
        changeFragment(new RegisterFragment());
    }

    @Override
    public void navigateBack() {
        setResult(RESULT_OK);
        finish();
    }
}
