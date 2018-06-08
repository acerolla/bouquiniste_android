package com.acerolla.bouquiniste.presentation.main.view;

import android.view.View;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IMainView {

    void showProfile();
    void showAdding();
    void showFavorites();
    void showAdverts();

    void setSearchButtonVisibility(boolean isVisible);
    void setFilterVisibility(boolean isVisible);
    void setLogoutVisibility(boolean isVisible);
    void setClearAllVisibility(boolean isVisible);

    void navigateToLogin();
    void imitateAdvertsClick();
}
