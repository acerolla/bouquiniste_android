package com.acerolla.bouquiniste.presentation.auth.register.presenter;

import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.auth.register.view.IRegisterView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IRegisterPresenter extends BasePresenter<IRegisterView> {

    void handleLoginClicked();
    void handleRegisterClicked(String email);
    void handleOkClick();
}
