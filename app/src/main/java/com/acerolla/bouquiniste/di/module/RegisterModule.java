package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.domain.auth.AuthInteractor;
import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;
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
    public IRegisterPresenter providePresenter() {
        return new RegisterPresenter();
    }
}
