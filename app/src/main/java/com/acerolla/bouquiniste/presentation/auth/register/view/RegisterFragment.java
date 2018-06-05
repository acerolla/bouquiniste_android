package com.acerolla.bouquiniste.presentation.auth.register.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.R;
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


        mView = (RegisterView) inflater.inflate(R.layout.fragment_register, container, false);
        mView.initViews();

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
    public void showErrorDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Ошибка регистрации!")
                .setMessage("Не удалось зарегистрировать аккаунт.\nПопробуйте снова!")
                .setPositiveButton("ОК", null)
                .show();
    }

    @Override
    public void showSuccessDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Успешно!")
                .setMessage("На почту, указанную при регистрации, было отправлено письмо с паролем для последующей авторизации в приложении")
                .setPositiveButton("ОК", (dialog, which) -> mPresenter.handleOkClick())
                .show();
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
