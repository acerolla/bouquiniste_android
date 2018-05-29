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
        mInteractor.loadCategories(result -> {
            if (result != null) {
                if (mView != null) {
                    mView.setCategoryData(result);
                    mView.setContentVisibility(false);
                    mView.showCategory();
                }
            } else {
                if (mView != null) {
                    mView.showCategoryErrorToast();
                }
            }
        });
    }

    @Override
    public void handleCategorySelected(int categoryId, String categoryTitle) {
        mInteractor.loadAdvertsByCategories(result -> {
            if (result != null && !result.isEmpty()) {
                if (mView != null) {
                    mView.setContentData(result);
                }
            }
        }, categoryId);
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
            if (result != null) {
                if (mView != null) {
                    mView.setContentData(result);
                    mView.setLoaderVisibility(false);
                    mView.setContentVisibility(true);
                }
            } else {
                mView.setLoaderVisibility(false);
                mView.setErrorVisibility(true);
            }
        }
    };
}
