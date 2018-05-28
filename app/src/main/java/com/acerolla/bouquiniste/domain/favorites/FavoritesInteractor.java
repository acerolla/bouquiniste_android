package com.acerolla.bouquiniste.domain.favorites;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.favorites.repository.IFavoritesRepository;
import com.acerolla.bouquiniste.data.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesInteractor implements IFavoritesInteractor {

    private IFavoritesRepository mRepository;

    public FavoritesInteractor(IFavoritesRepository repository) {
        mRepository = repository;
    }

    @Override
    public void loadFavoritesList(ResultListener<List<AdvertData>> listResultListener) {
        mRepository.loadFavoritesList(listResultListener);
    }

    @Override
    public void addToFavorites(ResultListener<Boolean> listener, int advertId) {
        mRepository.addToFavorites(listener, advertId);
    }

    @Override
    public void removeFromFavorites(ResultListener<Boolean> listener, int advertId) {
        mRepository.removeFromFavorites(listener, advertId);
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
