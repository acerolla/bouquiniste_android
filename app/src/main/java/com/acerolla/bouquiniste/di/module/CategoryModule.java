package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.category.repository.ICategoryRepository;
import com.acerolla.bouquiniste.domain.category.CategoryInteractor;
import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;
import com.acerolla.bouquiniste.presentation.category.presenter.CategoryPresenter;
import com.acerolla.bouquiniste.presentation.category.presenter.ICategoryPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class CategoryModule {

    @Provides
    public ICategoryInteractor provideInteractor(ICategoryRepository repository) {
        return new CategoryInteractor(repository);
    }

    @Provides
    public ICategoryPresenter providePresenter(ICategoryInteractor interactor) {
        return new CategoryPresenter(interactor);
    }
}
