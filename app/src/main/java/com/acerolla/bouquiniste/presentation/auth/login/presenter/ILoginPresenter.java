package com.acerolla.bouquiniste.presentation.auth.login.presenter;

import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.auth.login.view.ILoginView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ILoginPresenter extends BasePresenter<ILoginView> {

    void handleLoginClicked(String email, String password);
    void handleRegisterClicked();
}
