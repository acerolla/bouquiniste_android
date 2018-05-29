package com.acerolla.bouquiniste.domain.main;

import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class MainInteractor implements IMainInteractor{

    private IAuthRepository mRepository;

    public MainInteractor(IAuthRepository repository) {
        mRepository = repository;
    }

    @Override
    public boolean isUserLoggedIn() {
        return mRepository.getTokenAsync() != null;
    }

    @Override
    public void tryLoginUser() {
        mRepository.getToken(result -> {
            //ignore
        });
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
