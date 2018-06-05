package com.acerolla.bouquiniste.presentation.adverts.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.adverts.IAdvertsInteractor;
import com.acerolla.bouquiniste.presentation.adverts.view.IAdvertsView;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsPresenter implements IAdvertsPresenter {

    private IAdvertsView mView;
    private IAdvertsInteractor mInteractor;

    public AdvertsPresenter(IAdvertsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IAdvertsView view) {
        mView = view;
        mView.setTitle("Все объявления");
        mInteractor.loadAdvertList(mLoadingListener);
    }

    @Override
    public void handleDetailFinished(boolean isChanged) {
        if (isChanged) {
            mView.setContentVisibility(false);
            mView.setLoaderVisibility(true);
            mInteractor.loadAdvertList(mLoadingListener);
        }
    }

    @Override
    public void handleItemClicked(AdvertData item) {
        mInteractor.saveAdvertToCache(item);
        mView.navigateToDetail();
    }

    @Override
    public void handleFilterPressed() {
        mView.showCategory();
    }

    @Override
    public void handleCategorySelected(int categoryId, String categoryTitle) {
        mView.setTitle(categoryTitle);
        mInteractor.loadAdvertsByCategories(mLoadingListener, categoryId);
    }

    @Override
    public void handleRefresh() {
        mView.setTitle("Все объявления");
        mInteractor.loadAdvertList(mLoadingListener);
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
            if (mView != null) {
                mView.stopRefreshing();
            }
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
        }
    };
}
