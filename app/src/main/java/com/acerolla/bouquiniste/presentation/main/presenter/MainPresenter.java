package com.acerolla.bouquiniste.presentation.main.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
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
        mView.setFilterVisibility(true);
        mInteractor.tryLoginUser();
    }

    @Override
    public void handleProfileItemClick() {
        mWhere = FROM_PROFILE;
        if (mInteractor.isUserLoggedIn()) {
            mView.setFilterVisibility(false);
            mView.setLogoutVisibility(true);
            mView.showProfile();
        } else {
            mView.navigateToLogin();
        }
    }

    @Override
    public void handleAddingClick() {
        mWhere = FROM_ADDING;
        if (mInteractor.isUserLoggedIn()) {
            mView.setFilterVisibility(false);
            mView.setLogoutVisibility(false);
            mView.showAdding();
        } else {
            mView.navigateToLogin();
        }
    }

    @Override
    public void handleAdvertsClick() {
        mView.setFilterVisibility(true);
        mView.setLogoutVisibility(false);
        mView.showAdverts();
    }

    @Override
    public void handleFavoritesClick() {
        mWhere = FROM_FAVORITES;
        if (mInteractor.isUserLoggedIn()) {
            mView.setFilterVisibility(false);
            mView.setLogoutVisibility(false);
            mView.showFavorites();
        } else {
            mView.navigateToLogin();
        }
    }

    @Override
    public void handleUserLoggedIn() {
        if (mWhere == FROM_ADDING) {
            handleAddingClick();
        } else if (mWhere == FROM_FAVORITES) {
            handleFavoritesClick();
        } else if (mWhere == FROM_PROFILE) {
            handleProfileItemClick();
        }
    }

    @Override
    public void handleLogoutClick() {
        mInteractor.logout(result -> {
            if (mView != null) {
                mView.imitateAdvertsClick();
            }

        });
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
