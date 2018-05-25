package com.acerolla.bouquiniste.presentation.detail.presenter;

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
