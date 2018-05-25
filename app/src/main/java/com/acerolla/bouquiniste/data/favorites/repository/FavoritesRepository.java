package com.acerolla.bouquiniste.data.favorites.repository;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.favorites.repository.datasoruce.FavoritesDataSourceFactory;
import com.acerolla.bouquiniste.data.profile.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesRepository implements IFavoritesRepository {

    @Override
    public void loadFavoritesList(ResultListener<List<AdvertData>> listener) {
        FavoritesDataSourceFactory.getCloudDataSource()
                .loadFavoritesList(listener);
    }

    @Override
    public void addToFavorites(ResultListener<Boolean> listener, int advertId) {
        FavoritesDataSourceFactory.getCloudDataSource()
                .addToFavorites(listener, advertId);
    }

    @Override
    public void removeFromFavorites(ResultListener<Boolean> listener, int advertId) {
        FavoritesDataSourceFactory.getCloudDataSource()
                .removeFromFavorites(listener, advertId);
    }

    @Override
    public void release() {

    }
}
