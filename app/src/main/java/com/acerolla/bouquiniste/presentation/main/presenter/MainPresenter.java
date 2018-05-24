package com.acerolla.bouquiniste.presentation.main.presenter;

import com.acerolla.bouquiniste.presentation.main.view.IMainView;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class MainPresenter implements IMainPresenter {

    private IMainView mView;

    @Override
    public void bindView(IMainView view) {
        mView = view;
        mView.showAdverts();
    }

    @Override
    public void handleProfileItemClick() {
        mView.showProfile();
    }

    @Override
    public void handleAddingClick() {
        mView.showAdding();
    }

    @Override
    public void handleAdvertsClick() {
        mView.showAdverts();
    }

    @Override
    public void handleFavoritesClick() {
        mView.showFavorites();
    }

    @Override
    public void release() {
        mView = null;
    }
}
