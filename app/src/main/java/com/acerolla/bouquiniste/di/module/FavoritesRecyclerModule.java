package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;
import com.acerolla.bouquiniste.presentation.favorites.view.recycler.presenter.IRecyclerPresenter;
import com.acerolla.bouquiniste.presentation.favorites.view.recycler.presenter.RecyclerPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class FavoritesRecyclerModule {

    @Provides
    public IRecyclerPresenter providePresenter(ICategoryInteractor interactor) {
        return new RecyclerPresenter(interactor);
    }
}
