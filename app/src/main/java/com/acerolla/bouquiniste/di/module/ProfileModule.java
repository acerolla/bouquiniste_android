package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.profile.repository.IProfileRepository;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;
import com.acerolla.bouquiniste.domain.profile.ProfileInteractor;
import com.acerolla.bouquiniste.presentation.profile.presenter.IProfilePresenter;
import com.acerolla.bouquiniste.presentation.profile.presenter.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
@Module
public class ProfileModule {

    @Provides
    public IProfilePresenter providePresenter(IProfileInteractor interactor) {
        return new ProfilePresenter(interactor);
    }

    @Provides
    public IProfileInteractor provideInteractor(IProfileRepository repository) {
        return new ProfileInteractor(repository);
    }
}
