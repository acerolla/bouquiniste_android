package com.acerolla.bouquiniste.data.favorites.repository.datasoruce;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public interface IFavoritesDataSource {

    void loadFavoritesList(ResultListener<List<AdvertData>> listResultListener);
    void addToFavorites(ResultListener<Boolean> listener, int advertId);
    void removeFromFavorites(ResultListener<Boolean> listener, int advertId);
}
