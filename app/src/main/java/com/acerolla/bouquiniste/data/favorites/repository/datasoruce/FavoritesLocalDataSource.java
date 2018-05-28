package com.acerolla.bouquiniste.data.favorites.repository.datasoruce;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class FavoritesLocalDataSource implements IFavoritesDataSource {

    @Override
    public void loadFavoritesList(ResultListener<List<AdvertData>> listener) {

    }

    @Override
    public void addToFavorites(ResultListener<Boolean> listener, int advertId) {

    }

    @Override
    public void removeFromFavorites(ResultListener<Boolean> listener, int advertId) {

    }
}
