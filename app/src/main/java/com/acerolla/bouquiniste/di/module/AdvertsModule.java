package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.domain.adverts.AdvertsInteractor;
import com.acerolla.bouquiniste.domain.adverts.IAdvertsInteractor;
import com.acerolla.bouquiniste.presentation.adverts.presenter.AdvertsPresenter;
import com.acerolla.bouquiniste.presentation.adverts.presenter.IAdvertsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
@Module
public class AdvertsModule {

    @Provides
    public IAdvertsPresenter providePresenter(IAdvertsInteractor interactor) {
        return new AdvertsPresenter(interactor);
    }

    @Provides
    public IAdvertsInteractor provideInteractor(IAdvertsRepository repository) {
        return new AdvertsInteractor(repository);
    }
}
