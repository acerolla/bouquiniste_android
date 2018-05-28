package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.domain.detail.DetailInteractor;
import com.acerolla.bouquiniste.domain.detail.IDetailInteractor;
import com.acerolla.bouquiniste.domain.favorites.IFavoritesInteractor;
import com.acerolla.bouquiniste.presentation.detail.presenter.DetailPresenter;
import com.acerolla.bouquiniste.presentation.detail.presenter.IDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class DetailModule {

    @Provides
    public IDetailPresenter providePresenter(IDetailInteractor interactor) {
        return new DetailPresenter(interactor);
    }

    @Provides
    public IDetailInteractor provideInteractor(IAdvertsRepository repository, IFavoritesInteractor favoritesInteractor) {
        return new DetailInteractor(repository, favoritesInteractor);
    }
}
