package com.acerolla.bouquiniste.presentation.auth.login.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.auth.container.view.ILoginContainerView;
import com.acerolla.bouquiniste.presentation.auth.login.presenter.ILoginPresenter;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginFragment extends Fragment implements ILoginView {

    private LoginView mView;

    @Inject
    ILoginPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = new LoginView(getContext());
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiManager.getLoginComponent().inject(this);
        mPresenter.bindView(this);

        setListeners();
    }

    private void setListeners() {
        mView.setRegisterClickListener(v -> mPresenter.handleRegisterClicked());

        mView.setLoginClickListener(v -> mPresenter.handleLoginClicked(
                mView.getEmail(),
                mView.getPassword()
        ));
    }

    @Override
    public void navigateToRegister() {
        ((ILoginContainerView)getActivity()).navigateToRegister();
    }

    @Override
    public void navigateBack() {
        ((ILoginContainerView)getActivity()).navigateBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView = null;

        if (mPresenter != null) {
            mPresenter.release();
        }
        mPresenter = null;
    }
}
