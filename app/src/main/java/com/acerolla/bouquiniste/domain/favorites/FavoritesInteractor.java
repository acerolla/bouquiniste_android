package com.acerolla.bouquiniste.domain.favorites;

import com.acerolla.bouquiniste.data.favorites.repository.IFavoritesRepository;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public class FavoritesInteractor implements IFavoritesInteractor {

    private IFavoritesRepository mRepository;

    public FavoritesInteractor(IFavoritesRepository repository) {
        mRepository = repository;
    }


    @Override
    public void release() {
        mRepository = null;
    }
}
