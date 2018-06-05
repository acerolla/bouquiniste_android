package com.acerolla.bouquiniste.presentation.detail.presenter;

import android.content.Intent;
import android.net.Uri;

import com.acerolla.bouquiniste.domain.detail.IDetailInteractor;
import com.acerolla.bouquiniste.presentation.detail.view.IDetailView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailPresenter implements IDetailPresenter {

    private IDetailView mView;
    private IDetailInteractor mInteractor;

    public DetailPresenter(IDetailInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IDetailView view) {
        mView = view;

        if (mView.getExtraId() == -1) {
            mInteractor.loadAdvert(resultFromCache -> {
                if (resultFromCache != null) {
                    if (mView != null) {
                        mView.setContentData(resultFromCache);
                        mView.setToolbarTitleParams(resultFromCache.getId());
                        mView.changeFavoriteStatus(resultFromCache.isFavorite());
                        mView.setLoaderVisibility(false);
                        mView.setContentVisibility(true);
                    }
                } else {
                    if (mView != null) {
                        mView.setLoaderVisibility(false);
                        mView.setErrorVisibility(true);
                    }
                }
            });
        } else {
            mInteractor.loadAdvert(resultFromCloud -> {
                if (resultFromCloud != null) {
                    if (mView != null) {
                        mView.setContentData(resultFromCloud);
                        mView.setToolbarTitleParams(resultFromCloud.getId());
                        mView.changeFavoriteStatus(resultFromCloud.isFavorite());
                        mView.setLoaderVisibility(false);
                        mView.setContentVisibility(true);
                    }
                } else {
                    if (mView != null) {
                        mView.setLoaderVisibility(false);
                        mView.setErrorVisibility(true);
                    }
                }
            }, mView.getExtraId());
        }

    }

    @Override
    public void handleFavoriteClick() {
        if (!mInteractor.isUserLoggedIn()) {
            mView.showToast("Необходима авторизация!");
            return;
        }

        mInteractor.changeFavoriteStatus(
                result -> {
                    if (mView != null) {
                        mView.changeFavoriteStatus(result);
                    }
                });

    }

    @Override
    public void handleShareClick() {
        mView.share();
    }

    @Override
    public void handleOnMapClick() {
        mView.navigateToMap();
    }

    @Override
    public void handlePhoneClick(String phoneNumber) {
        mView.makeCall(phoneNumber);
    }

    @Override
    public void handleRefreshClick() {
        mView.setErrorVisibility(false);
        mView.setLoaderVisibility(true);

        if (mView.getExtraId() == -1) {
            mInteractor.loadAdvert(resultFromCache -> {
                if (resultFromCache != null) {
                    if (mView != null) {
                        mView.setContentData(resultFromCache);
                        mView.setToolbarTitleParams(resultFromCache.getId());
                        mView.changeFavoriteStatus(resultFromCache.isFavorite());
                        mView.setLoaderVisibility(false);
                        mView.setContentVisibility(true);
                    }
                } else {
                    if (mView != null) {
                        mView.setLoaderVisibility(false);
                        mView.setErrorVisibility(true);
                    }
                }
            });
        } else {
            mInteractor.loadAdvert(resultFromCloud -> {
                if (resultFromCloud != null) {
                    if (mView != null) {
                        mView.setContentData(resultFromCloud);
                        mView.setToolbarTitleParams(resultFromCloud.getId());
                        mView.changeFavoriteStatus(resultFromCloud.isFavorite());
                        mView.setLoaderVisibility(false);
                        mView.setContentVisibility(true);
                    }
                } else {
                    if (mView != null) {
                        mView.setLoaderVisibility(false);
                        mView.setErrorVisibility(true);
                    }
                }
            }, mView.getExtraId());
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
