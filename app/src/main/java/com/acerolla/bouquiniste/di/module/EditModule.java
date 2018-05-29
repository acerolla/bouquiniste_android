package com.acerolla.bouquiniste.di.module;


import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.edit.repository.IEditRepository;
import com.acerolla.bouquiniste.domain.edit.EditInteractor;
import com.acerolla.bouquiniste.domain.edit.IEditInteractor;
import com.acerolla.bouquiniste.presentation.edit.presenter.EditPresenter;
import com.acerolla.bouquiniste.presentation.edit.presenter.IEditPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class EditModule {

    @Provides
    public IEditPresenter providePresenter(IEditInteractor interactor) {
        return new EditPresenter(interactor);
    }

    @Provides
    public IEditInteractor provideInteractor(IEditRepository editRepository, IAdvertsRepository advertsRepository) {
        return new EditInteractor(editRepository, advertsRepository);
    }
}
