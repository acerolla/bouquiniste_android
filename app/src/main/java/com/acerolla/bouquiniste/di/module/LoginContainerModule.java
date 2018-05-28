package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.container.presenter.ILoginContainerPresenter;
import com.acerolla.bouquiniste.presentation.auth.container.presenter.LoginContainerPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class LoginContainerModule {

    @Provides
    public ILoginContainerPresenter providePresenter(IAuthInteractor interactor) {
        return new LoginContainerPresenter(interactor);
    }
}
