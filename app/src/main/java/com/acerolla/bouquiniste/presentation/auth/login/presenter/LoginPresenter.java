package com.acerolla.bouquiniste.presentation.auth.login.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.login.view.ILoginView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginPresenter implements ILoginPresenter {

    private ILoginView mView;
    private IAuthInteractor mInteractor;

    public LoginPresenter(IAuthInteractor interactor) {
        mInteractor = interactor;
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
