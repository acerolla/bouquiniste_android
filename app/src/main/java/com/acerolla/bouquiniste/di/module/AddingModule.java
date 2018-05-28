package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.adding.repository.IAddingRepository;
import com.acerolla.bouquiniste.domain.adding.AddingInteractor;
import com.acerolla.bouquiniste.domain.adding.IAddingInteractor;
import com.acerolla.bouquiniste.domain.adverts.IAdvertsInteractor;
import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;
import com.acerolla.bouquiniste.presentation.adding.presenter.AddingPresenter;
import com.acerolla.bouquiniste.presentation.adding.presenter.IAddingPresenter;
import com.acerolla.bouquiniste.presentation.adverts.presenter.AdvertsPresenter;
import com.acerolla.bouquiniste.presentation.adverts.presenter.IAdvertsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class AddingModule {

    @Provides
    public IAddingPresenter providePresenter(IAddingInteractor interactor) {
        return new AddingPresenter(interactor);
    }

    @Provides
    public IAddingInteractor provideInteractor(IAddingRepository repository, ICategoryInteractor categoryInteractor, IAdvertsInteractor advertsInteractor) {
        return new AddingInteractor(repository, categoryInteractor, advertsInteractor);
    }
}
