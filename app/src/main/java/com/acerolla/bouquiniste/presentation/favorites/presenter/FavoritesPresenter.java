package com.acerolla.bouquiniste.presentation.favorites.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.domain.favorites.IFavoritesInteractor;
import com.acerolla.bouquiniste.presentation.favorites.view.IFavoritesView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesPresenter implements IFavoritesPresenter {

    private IFavoritesView mView;
    private IFavoritesInteractor mInteractor;

    private boolean mHasBeenNull;

    public FavoritesPresenter(IFavoritesInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IFavoritesView view) {
        mView = view;
        mInteractor.loadFavoritesList(mLoadingListener);
    }

    @Override
    public void handleItemClicked(AdvertData item) {
        mInteractor.saveAdvertToCache(item);
        mView.navigateToDetail();
    }

    @Override
    public void handleDetailFinished(boolean isChanged) {
        if (isChanged) {
            mView.setContentVisibility(false);
            mView.setLoaderVisibility(true);
            mInteractor.loadFavoritesList(mLoadingListener);
        }
    }

    @Override
    public void handleRefresh() {
        mInteractor.loadFavoritesList(mLoadingListener);
    }

    @Override
    public void handleClearAllClick(List<AdvertData> adverts) {
        mInteractor.clearAllFavorites(result -> {
            if (mView == null || mInteractor == null) {
                return;
            }

            if (result == null) {
                mHasBeenNull = true;
            } else {
                if (!mHasBeenNull) {
                    mView.setContentData(new ArrayList<>());
                    mView.showToast("Избранные объявления были удалены!");
                } else {
                    mInteractor.loadFavoritesList(mLoadingListener);
                    mView.showToast("Не все объявления были удалены!");
                }
            }
        }, adverts);
    }

    @Override
    public void release() {
        mLoadingListener = null;
        mView = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }

    private ResultListener<List<AdvertData>> mLoadingListener = new ResultListener<List<AdvertData>>() {
        @Override
        public void onResult(List<AdvertData> result) {
            if (mView == null) {
                return;
            }

            mView.stopRefreshing();
            if (result != null) {
                mView.setContentData(result);
                mView.setLoaderVisibility(false);
                mView.setErrorVisibility(false);
                mView.setContentVisibility(true);
            } else {
                mView.setLoaderVisibility(false);
                mView.setErrorVisibility(true);
            }
        }
    };
}
