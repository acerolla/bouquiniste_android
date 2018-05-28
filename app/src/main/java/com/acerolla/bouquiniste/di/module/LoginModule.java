package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;
import com.acerolla.bouquiniste.presentation.auth.login.presenter.ILoginPresenter;
import com.acerolla.bouquiniste.presentation.auth.login.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class LoginModule {

    @Provides
    public ILoginPresenter providePresenter(IAuthInteractor interactor) {
        return new LoginPresenter(interactor);
    }
}
