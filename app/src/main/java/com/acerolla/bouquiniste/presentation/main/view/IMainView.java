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

    void initToolbarForAdverts(View.OnClickListener listener);
}
