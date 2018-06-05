package com.acerolla.bouquiniste.presentation.auth.register.presenter;

import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.register.view.IRegisterView;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class RegisterPresenter implements IRegisterPresenter {

    private IRegisterView mView;

    @Inject
    IAuthInteractor mInteractor;

    public RegisterPresenter() {
        DiManager.getAuthComponent().inject(this);
    }

    @Override
    public void bindView(IRegisterView view) {
        mView = view;
    }

    @Override
    public void handleLoginClicked() {
        mView.navigateToLogin();
    }

    @Override
    public void handleRegisterClicked(String email) {
        mInteractor.register(result -> {
            if (result != null && mInteractor != null && mView != null) {
                mInteractor.saveProfile(result);
                mView.showSuccessDialog();
            } else if (mView != null) {
                mView.showErrorDialog();
            }
        }, email);
    }

    @Override
    public void handleOkClick() {
        mView.navigateBack();
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
