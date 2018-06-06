package com.acerolla.bouquiniste.presentation.favorites.presenter;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.favorites.view.IFavoritesView;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IFavoritesPresenter extends BasePresenter<IFavoritesView> {

    void handleItemClicked(AdvertData item);
    void handleDetailFinished(boolean isChanged);

    void handleClearAllClick(List<AdvertData> adverts);

    void handleRefresh();
}
