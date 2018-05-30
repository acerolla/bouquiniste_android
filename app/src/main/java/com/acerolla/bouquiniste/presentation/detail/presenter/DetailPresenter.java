package com.acerolla.bouquiniste.presentation.detail.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
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

        mInteractor.loadAdvert(result -> {
            if (result != null) {
                if (mView != null) {
                    mView.setContentData(result);
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

    }

    @Override
    public void handleFavoriteClick() {
        mInteractor.changeFavoriteStatus(
                result -> mView.changeFavoriteStatus(result),
                mInteractor.getCachedAdvert());
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
