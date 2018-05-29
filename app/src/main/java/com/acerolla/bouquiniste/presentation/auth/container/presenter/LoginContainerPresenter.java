package com.acerolla.bouquiniste.presentation.auth.container.presenter;

import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.container.view.ILoginContainerView;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginContainerPresenter implements ILoginContainerPresenter {

    private ILoginContainerView mView;

    @Inject
    IAuthInteractor mInteractor;

    public LoginContainerPresenter() {
        DiManager.getAuthComponent().inject(this);
    }


    @Override
    public void bindView(ILoginContainerView view) {
        mView = view;
        mView.showRegistration();
    }

    @Override
    public void handleLoginClick() {
        mView.navigateToLogin();
    }

    @Override
    public void handleRegisterClick() {
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
