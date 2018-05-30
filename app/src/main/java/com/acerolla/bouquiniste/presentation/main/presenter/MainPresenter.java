package com.acerolla.bouquiniste.presentation.main.presenter;

import com.acerolla.bouquiniste.domain.main.IMainInteractor;
import com.acerolla.bouquiniste.presentation.main.view.IMainView;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class MainPresenter implements IMainPresenter {

    private static final int FROM_FAVORITES = 1;
    private static final int FROM_ADDING = 2;
    private static final int FROM_PROFILE = 3;
    private int mWhere;

    private IMainView mView;
    private IMainInteractor mInteractor;

    public MainPresenter(IMainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IMainView view) {
        mView = view;
        mView.showAdverts();
        mInteractor.tryLoginUser();
    }

    @Override
    public void handleProfileItemClick() {
        if (mInteractor.isUserLoggedIn()) {
            mView.showProfile();
            mWhere = FROM_PROFILE;
        } else {
            mView.navigateToLogin();
        }
    }

    @Override
    public void handleAddingClick() {
        if (mInteractor.isUserLoggedIn()) {
            mView.showAdding();
            mWhere = FROM_ADDING;
        } else {
            mView.navigateToLogin();
        }
    }

    @Override
    public void handleAdvertsClick() {
        mView.showAdverts();
    }

    @Override
    public void handleFavoritesClick() {
        if (mInteractor.isUserLoggedIn()) {
            mView.showFavorites();
            mWhere = FROM_FAVORITES;
        } else {
            mView.navigateToLogin();
        }
    }

    @Override
    public void handleUserLoggedIn() {
        if (mWhere == FROM_ADDING) {
            mView.showAdding();
        } else if (mWhere == FROM_FAVORITES) {
            mView.showFavorites();
        } else if (mWhere == FROM_PROFILE) {
            mView.showProfile();
        }
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
