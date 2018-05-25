package com.acerolla.bouquiniste.data.favorites.repository;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.BaseRepository;
import com.acerolla.bouquiniste.data.profile.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IFavoritesRepository extends BaseRepository {

    void loadFavoritesList(ResultListener<List<AdvertData>> listResultListener);
    void addToFavorites(ResultListener<Boolean> listener, int advertId);
    void removeFromFavorites(ResultListener<Boolean> listener, int advertId);
}
