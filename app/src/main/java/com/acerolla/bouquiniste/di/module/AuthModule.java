package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.domain.auth.AuthInteractor;
import com.acerolla.bouquiniste.domain.auth.IAuthInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class AuthModule {

    @Provides
    public IAuthInteractor provideInteractor(IAuthRepository repository) {
        return new AuthInteractor(repository);
    }
}
