package com.acerolla.bouquiniste.presentation.auth.container.presenter;

import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.auth.container.view.ILoginContainerView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ILoginContainerPresenter extends BasePresenter<ILoginContainerView> {

    void handleLoginClick();
    void handleRegisterClick();
}
