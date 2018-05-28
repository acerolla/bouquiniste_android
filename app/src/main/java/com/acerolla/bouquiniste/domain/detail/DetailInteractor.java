package com.acerolla.bouquiniste.domain.detail;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.domain.favorites.IFavoritesInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailInteractor implements IDetailInteractor {

    private IAdvertsRepository mRepository;
    private IFavoritesInteractor mFavoritesInteractor;

    public DetailInteractor(IAdvertsRepository repository, IFavoritesInteractor favoritesInteractor) {
        mRepository = repository;
        mFavoritesInteractor = favoritesInteractor;
    }

    @Override
    public void loadAdvert(ResultListener<AdvertData> listener) {
        mRepository.loadAdvert(listener, mRepository.getCachedAdvert().getId());
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData advert) {
        mRepository.editAdvert(listener, mRepository.getCachedAdvert());
    }

    @Override
    public void changeFavoriteStatus(ResultListener<Boolean> listener, AdvertData advert) {
        if (advert.isFavorite()) {
            if (listener != null) {
                listener.onResult(false);
            }
            if (mFavoritesInteractor != null) {
                mFavoritesInteractor.removeFromFavorites(
                        result -> {},
                        advert.getId());
            }
        } else {
            if (listener != null) {
                listener.onResult(true);
            }
            if (mFavoritesInteractor != null) {
                mFavoritesInteractor.addToFavorites(
                        result -> {},
                        advert.getId());
            }
        }
    }

    @Override
    public AdvertData getCachedAdvert() {
        return mRepository.getCachedAdvert();
    }

    @Override
    public void release() {
        mRepository = null;

        if (mFavoritesInteractor != null) {
            mFavoritesInteractor.release();
        }
        mFavoritesInteractor = null;
    }


}
