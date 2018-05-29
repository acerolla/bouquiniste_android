package com.acerolla.bouquiniste.presentation.main.presenter;

import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.main.view.IMainView;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IMainPresenter extends BasePresenter<IMainView> {

    void handleProfileItemClick();
    void handleAddingClick();
    void handleFavoritesClick();
    void handleAdvertsClick();
    void handleUserLoggedIn();
}
