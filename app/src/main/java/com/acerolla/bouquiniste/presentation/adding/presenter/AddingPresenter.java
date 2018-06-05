package com.acerolla.bouquiniste.presentation.adding.presenter;

import android.net.Uri;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.adding.IAddingInteractor;
import com.acerolla.bouquiniste.presentation.adding.view.IAddingView;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingPresenter implements IAddingPresenter {


    private IAddingView mView;
    private IAddingInteractor mInteractor;

    public AddingPresenter(IAddingInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IAddingView view) {
        mView = view;

    }

    @Override
    public void handleUploadClick() {
        mView.showChooseFileActivity();
    }

    @Override
    public void handleFileChoosed(Uri uri) {
        mInteractor.saveImagePath(uri); ////storage/emulated/0/Download/sample_image.jpg
        mView.setImage(uri);
    }

    @Override
    public void handleAddClick() {
        mView.setLoaderVisibility(true);

        AdvertData advert = mView.collectData();

        mInteractor.postAdvert(result -> {
            if (result != null && mView != null) {
                mView.setContentVisibility(false);
                mView.navigateToDetail(result.getId());
            } else {
                if (mView != null) {
                    mView.setLoaderVisibility(false);
                    mView.setErrorVisibility(true);
                }
            }
        }, advert);
    }

    @Override
    public void handleDetailFinished() {
        mView.changeFragment();
    }

    @Override
    public void handleCategoryButtonClicked() {
        mView.navigateToCategories();
    }

    @Override
    public void handleCategorySelected(int categoryId, String categoryTitle) {
        mView.showCategory(categoryId, categoryTitle);
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
