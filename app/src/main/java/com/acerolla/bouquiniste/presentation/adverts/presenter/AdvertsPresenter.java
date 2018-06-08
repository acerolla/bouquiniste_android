package com.acerolla.bouquiniste.presentation.adverts.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.domain.adverts.IAdvertsInteractor;
import com.acerolla.bouquiniste.presentation.adverts.view.IAdvertsView;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsPresenter implements IAdvertsPresenter {

    private IAdvertsView mView;
    private IAdvertsInteractor mInteractor;

    private int mPage = 1;

    public AdvertsPresenter(IAdvertsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IAdvertsView view) {
        mView = view;
        mView.setTitle("Все объявления");
        mInteractor.loadAdvertList(mLoadingListener, mPage, "");
    }

    @Override
    public void handleDetailFinished(boolean isChanged) {
        /*if (isChanged) {
            mView.setContentVisibility(false);
            mView.setLoaderVisibility(true);
            mInteractor.loadAdvertList(mLoadingListener);
        }*/
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
        mView.setSearchVisibility(false);
        mView.setTitle(categoryTitle);
        mInteractor.loadAdvertsByCategories(mLoadingListener, categoryId);
    }

    @Override
    public void handleRefresh() {
        mView.setTitle("Все объявления");
        mPage = 1;
        mInteractor.loadAdvertList(mRefreshListener, mPage, "");
    }

    @Override
    public void onLoadMore(int advertsCount) {
        if (advertsCount == mPage * 10) {
            mInteractor.loadAdvertList(mLoadingMoreListener, ++mPage, "");
        }
    }

    @Override
    public void handleSearchTextChanged(String newText) {
        mPage = 1;
        mInteractor.loadAdvertList(mLoadingListener, mPage, newText);
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
                    mView.setErrorVisibility(false);
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

    private ResultListener<List<AdvertData>> mLoadingMoreListener = new ResultListener<List<AdvertData>>() {
        @Override
        public void onResult(List<AdvertData> result) {
            if (result != null) {
                Logger.e(Integer.toString(mPage));
                mView.addContentData(result);
            }
        }
    };

    private ResultListener<List<AdvertData>> mRefreshListener = new ResultListener<List<AdvertData>>() {
        @Override
        public void onResult(List<AdvertData> result) {
            if (mView != null) {
                mView.stopRefreshing();
            }
            if (result != null) {
                if (mView != null) {
                    mView.setContentData(result);
                    mView.setErrorVisibility(false);
                    mView.setLoaderVisibility(false);
                    mView.setContentVisibility(true);
                    mView.setSearchVisibility(true);
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
