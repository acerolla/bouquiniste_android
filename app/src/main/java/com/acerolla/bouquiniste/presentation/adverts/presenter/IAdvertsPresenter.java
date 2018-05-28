package com.acerolla.bouquiniste.presentation.adverts.presenter;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.adverts.view.IAdvertsView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAdvertsPresenter extends BasePresenter<IAdvertsView> {

    void handleDetailFinished(boolean isChanged);
    void handleItemClicked(AdvertData data);
    void handleFilterPressed();
}
