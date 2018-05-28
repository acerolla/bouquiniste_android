package com.acerolla.bouquiniste.presentation.favorites.presenter;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.favorites.view.IFavoritesView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IFavoritesPresenter extends BasePresenter<IFavoritesView> {

    void handleItemClicked(AdvertData item);
    void handleDetailFinished(boolean isChanged);
}
