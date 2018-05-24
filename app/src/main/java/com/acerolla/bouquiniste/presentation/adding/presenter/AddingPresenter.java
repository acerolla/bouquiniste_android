package com.acerolla.bouquiniste.presentation.adding.presenter;

import com.acerolla.bouquiniste.domain.adding.IAddingInteractor;
import com.acerolla.bouquiniste.presentation.adding.view.IAddingView;

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
    public void release() {
        mView = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }
}
