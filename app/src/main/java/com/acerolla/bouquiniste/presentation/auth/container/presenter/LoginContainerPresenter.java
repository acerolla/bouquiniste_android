package com.acerolla.bouquiniste.presentation.auth.container.presenter;

import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.container.view.ILoginContainerView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginContainerPresenter implements ILoginContainerPresenter {

    private ILoginContainerView mView;
    private IAuthInteractor mInteractor;

    public LoginContainerPresenter(IAuthInteractor interactor) {
        mInteractor = interactor;
    }


    @Override
    public void bindView(ILoginContainerView view) {
        mView = view;
        mView.showLogin();
    }

    @Override
    public void handleLoginClick() {

    }

    @Override
    public void handleRegisterClick() {

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
