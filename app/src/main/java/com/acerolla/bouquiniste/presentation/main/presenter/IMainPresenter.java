package com.acerolla.bouquiniste.presentation.main.presenter;

import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.main.view.IMainView;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.05.2018.
 */
public interface IMainPresenter extends BasePresenter<IMainView> {

    void handleProfileItemClick();
}
