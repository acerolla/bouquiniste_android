package com.acerolla.bouquiniste.domain.detail;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.domain.favorites.IFavoritesInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailInteractor implements IDetailInteractor {

    private IAdvertsRepository mRepository;
    private IFavoritesInteractor mFavoritesInteractor;
    private IAuthRepository mAuthRepository;

    public DetailInteractor(IAdvertsRepository repository, IFavoritesInteractor favoritesInteractor, IAuthRepository authRepository) {
        mRepository = repository;
        mFavoritesInteractor = favoritesInteractor;
        mAuthRepository = authRepository;
    }

    @Override
    public void loadAdvert(ResultListener<AdvertData> listener) {
        if (mRepository.getCachedAdvert() != null) {
            mRepository.loadAdvert(listener, mRepository.getCachedAdvert().getId());
        }
    }

    @Override
    public void loadAdvert(ResultListener<AdvertData> listener, int advertId) {
        mRepository.loadAdvert(listener, advertId);
    }

    @Override
    public void changeFavoriteStatus(ResultListener<Boolean> listener) {

        AdvertData advert = mRepository.getCachedAdvert();
        if (advert == null) {
            listener.onResult(null);
            return;
        }

        if (advert.isFavorite()) {
            if (listener != null) {
                listener.onResult(false);
            }
            if (mFavoritesInteractor != null) {
                mFavoritesInteractor.removeFromFavorites(
                        result -> {
                            if (result != null && result && mRepository != null && mRepository.getCachedAdvert() != null) {
                                mRepository.getCachedAdvert().setmFavorite(false);
                            }
                        }, advert.getId());
            }
        } else {
            if (listener != null) {
                listener.onResult(true);
            }
            if (mFavoritesInteractor != null) {
                mFavoritesInteractor.addToFavorites(
                        result -> {
                            if (result != null && result && mRepository != null && mRepository.getCachedAdvert() != null) {
                                mRepository.getCachedAdvert().setmFavorite(true);
                            }
                        }, advert.getId());
            }
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return mAuthRepository.getTokenAsync() != null;
    }

    @Override
    public void release() {
        mRepository = null;
        mAuthRepository = null;

        if (mFavoritesInteractor != null) {
            mFavoritesInteractor.release();
        }
        mFavoritesInteractor = null;
    }
}
