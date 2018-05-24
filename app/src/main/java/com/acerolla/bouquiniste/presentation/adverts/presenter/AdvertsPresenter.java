package com.acerolla.bouquiniste.presentation.adverts.presenter;

import com.acerolla.bouquiniste.presentation.adding.presenter.AddingPresenter;
import com.acerolla.bouquiniste.presentation.adverts.view.IAdvertsView;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public class AdvertsPresenter implements IAdvertsPresenter {

    private IAdvertsView mView;

    public AdvertsPresenter() {

    }

    @Override
    public void bindView(IAdvertsView view) {
        mView = view;
    }

    @Override
    public void release() {
        mView = null;
    }
}
