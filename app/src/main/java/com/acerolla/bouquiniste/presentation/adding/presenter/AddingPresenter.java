package com.acerolla.bouquiniste.presentation.adding.presenter;

import android.net.Uri;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.domain.adding.IAddingInteractor;
import com.acerolla.bouquiniste.presentation.adding.view.IAddingView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingPresenter implements IAddingPresenter {

    private static final String STORAGE = "storage";

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
        String path = getPathFromUri(uri);
    }

    private String getPathFromUri(Uri uri) {
        return uri.getPath().substring(uri.getPath().indexOf(STORAGE));
    }

    @Override
    public void handleAddClick() {
        AdvertData advert = mView.collectData();
        mInteractor.postAdvert(result -> {
            if (result != null) {
                mView.navigateToDetail(result.getId());
            }
        }, advert);
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
