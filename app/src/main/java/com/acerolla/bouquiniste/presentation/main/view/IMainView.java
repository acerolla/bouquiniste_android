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

    void setFilterVisibility(boolean isVisible);
    void setLogoutVisibility(boolean isVisible);
    void setClearAllVisibility(boolean isVisible);

    void setCategoryClickListener(View.OnClickListener listener);
    void setClearAllClickListener(View.OnClickListener listener);

    void navigateToLogin();

    void imitateAdvertsClick();
}
