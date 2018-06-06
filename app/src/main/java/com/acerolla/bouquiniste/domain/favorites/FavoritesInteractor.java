package com.acerolla.bouquiniste.domain.favorites;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.favorites.repository.IFavoritesRepository;
import com.acerolla.bouquiniste.data.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesInteractor implements IFavoritesInteractor {

    private IFavoritesRepository mFavoritesRepository;
    private IAdvertsRepository mAdvertsRepository;

    public FavoritesInteractor(IFavoritesRepository repository, IAdvertsRepository advertsRepository) {
        mFavoritesRepository = repository;
        mAdvertsRepository = advertsRepository;
    }

    @Override
    public void loadFavoritesList(ResultListener<List<AdvertData>> listResultListener) {
        mFavoritesRepository.loadFavoritesList(listResultListener);
    }

    @Override
    public void addToFavorites(ResultListener<Boolean> listener, int advertId) {
        mFavoritesRepository.addToFavorites(listener, advertId);
    }

    @Override
    public void removeFromFavorites(ResultListener<Boolean> listener, int advertId) {
        mFavoritesRepository.removeFromFavorites(listener, advertId);
    }

    @Override
    public void saveAdvertToCache(AdvertData data) {
        mAdvertsRepository.saveAdvertToCache(data);
    }

    @Override
    public void clearAllFavorites(ResultListener<Object> listener, List<AdvertData> adverts) {
        for (AdvertData advert : adverts) {
            mFavoritesRepository.removeFromFavorites(result -> {
                if (result == null && listener != null) {
                    listener.onResult(null);
                } else if (advert.getId() == adverts.get(adverts.size() - 1).getId()) {
                    if (listener != null) {
                        listener.onResult(new Object());
                    }
                }
            }, advert.getId());
        }
    }

    @Override
    public void release() {
        mFavoritesRepository = null;
    }
}
