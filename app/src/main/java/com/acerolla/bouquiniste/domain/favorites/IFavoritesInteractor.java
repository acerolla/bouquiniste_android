package com.acerolla.bouquiniste.domain.favorites;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.domain.BaseInteractor;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public interface IFavoritesInteractor extends BaseInteractor {

    void loadFavoritesList(ResultListener<List<AdvertData>> listResultListener);
    void addToFavorites(ResultListener<Boolean> listener, int advertId);
    void removeFromFavorites(ResultListener<Boolean> listener, int advertId);

    void saveAdvertToCache(AdvertData data);
}
