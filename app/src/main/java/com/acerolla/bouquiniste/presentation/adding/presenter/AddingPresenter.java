package com.acerolla.bouquiniste.presentation.adding.presenter;

import com.acerolla.bouquiniste.presentation.adding.view.IAddingView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingPresenter implements IAddingPresenter {

    private IAddingView mView;

    public AddingPresenter() {

    }

    @Override
    public void bindView(IAddingView view) {
        mView = view;
    }

    @Override
    public void release() {
        mView = null;
    }
}
