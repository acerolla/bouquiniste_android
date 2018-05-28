package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.register.presenter.IRegisterPresenter;
import com.acerolla.bouquiniste.presentation.auth.register.presenter.RegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class RegisterModule {

    @Provides
    IRegisterPresenter providePresenter(IAuthInteractor interactor) {
        return new RegisterPresenter(interactor);
    }
}
