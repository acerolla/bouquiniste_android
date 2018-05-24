package com.acerolla.bouquiniste.presentation.favorites.presenter;

import com.acerolla.bouquiniste.domain.favorites.IFavoritesInteractor;
import com.acerolla.bouquiniste.presentation.favorites.view.IFavoritesView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesPresenter implements IFavoritesPresenter {

    private IFavoritesView mView;
    private IFavoritesInteractor mInteractor;

    public FavoritesPresenter(IFavoritesInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IFavoritesView view) {
        mView = view;
    }

    @Override
    public void release() {
        mView = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }
}
