package com.acerolla.bouquiniste.presentation.auth.register.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.auth.container.view.ILoginContainerView;
import com.acerolla.bouquiniste.presentation.auth.container.view.LoginContainerActivity;
import com.acerolla.bouquiniste.presentation.auth.register.presenter.IRegisterPresenter;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class RegisterFragment extends Fragment implements IRegisterView {

    private RegisterView mView;

    @Inject
    IRegisterPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = new RegisterView(getContext());
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiManager.getRegisterComponent().inject(this);
        mPresenter.bindView(this);

        setListeners();
    }

    private void setListeners() {

        mView.setRegisterClickListener(v -> mPresenter.handleRegisterClicked(mView.collectData()));

        mView.setLoginClickListener(v -> mPresenter.handleLoginClicked());
    }

    @Override
    public void navigateToLogin() {
        ((ILoginContainerView) getActivity()).navigateToLogin();
    }

    @Override
    public void navigateBack() {
        ((ILoginContainerView) getActivity()).navigateBack();
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
