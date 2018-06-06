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
    void setLogoutVisibility(boolean isVisibile);

    void initToolbarForAdverts(View.OnClickListener listener);

    void navigateToLogin();
    void showLogoutToast(String toast);

    void imitateAdvertsClick();
}
