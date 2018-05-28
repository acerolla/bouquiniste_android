package com.acerolla.bouquiniste.presentation.auth.container.view;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ILoginContainerView {

    void showLogin();
    void showRegistration();

    void navigateToRegister();
    void navigateToLogin();
    void navigateBack();
}
