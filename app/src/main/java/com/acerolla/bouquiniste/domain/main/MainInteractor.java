package com.acerolla.bouquiniste.domain.main;

import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.data.profile.repository.IProfileRepository;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class MainInteractor implements IMainInteractor{

    private IAuthRepository mAuthRepository;
    private IProfileRepository mProfileRepository;

    public MainInteractor(IAuthRepository authRepository, IProfileRepository profileRepository) {
        mAuthRepository = authRepository;
        mProfileRepository = profileRepository;
    }

    @Override
    public boolean isUserLoggedIn() {
        return mAuthRepository.getTokenAsync() != null;
    }

    @Override
    public void tryLoginUser() {
        mAuthRepository.getToken(resultFromToken -> {
            if (resultFromToken != null && mProfileRepository != null) {
                mProfileRepository.loadProfile(result -> {
                    //ignore
                    //just to cache profile
                });
            }
        });
    }

    @Override
    public void release() {
        mAuthRepository = null;
        mProfileRepository = null;
    }
}
