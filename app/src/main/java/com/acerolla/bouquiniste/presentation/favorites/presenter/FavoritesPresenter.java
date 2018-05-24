package com.acerolla.bouquiniste.presentation.favorites.presenter;

import com.acerolla.bouquiniste.presentation.favorites.view.IFavoritesView;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public class FavoritesPresenter implements IFavoritesPresenter {

    private IFavoritesView mView;

    public FavoritesPresenter() {

    }

    @Override
    public void bindView(IFavoritesView view) {
        mView = view;
    }

    @Override
    public void release() {
        mView = null;
    }
}
