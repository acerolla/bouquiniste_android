package com.acerolla.bouquiniste.presentation.auth.login.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.login.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginPresenter implements ILoginPresenter {

    private ILoginView mView;

    @Inject
    IAuthInteractor mInteractor;

    public LoginPresenter() {
        DiManager.getAuthComponent().inject(this);
    }

    @Override
    public void bindView(ILoginView view) {
        mView = view;
    }

    @Override
    public void handleLoginClicked(String email, String password) {
        mInteractor.login(result -> {
            if (result != null && mInteractor != null && mView != null) {
                mInteractor.saveProfile(result);
                mView.navigateBack();
            } else if (mView != null){
                mView.showErrorDialog();
            }
        }, email, password);
    }

    @Override
    public void handleRegisterClicked() {
        mView.navigateToRegister();
    }

    @Override
    public void release() {
        mView = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }
}
