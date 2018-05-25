package com.acerolla.bouquiniste.domain.auth;

import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AuthInteractor implements IAuthInteractor {

    private IAuthRepository mRepository;

    public AuthInteractor(IAuthRepository repository) {
        mRepository = repository;
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
