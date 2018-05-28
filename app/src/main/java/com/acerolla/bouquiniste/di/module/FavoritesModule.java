package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.favorites.repository.IFavoritesRepository;
import com.acerolla.bouquiniste.domain.favorites.FavoritesInteractor;
import com.acerolla.bouquiniste.domain.favorites.IFavoritesInteractor;
import com.acerolla.bouquiniste.presentation.favorites.presenter.FavoritesPresenter;
import com.acerolla.bouquiniste.presentation.favorites.presenter.IFavoritesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class FavoritesModule {

    @Provides
    public IFavoritesPresenter providePresenter(IFavoritesInteractor interactor) {
        return new FavoritesPresenter(interactor);
    }

    @Provides
    public IFavoritesInteractor provideInteractor(IFavoritesRepository repository) {
        return new FavoritesInteractor(repository);
    }
}
