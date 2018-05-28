package com.acerolla.bouquiniste.domain.auth;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AuthInteractor implements IAuthInteractor {

    private IAuthRepository mRepository;
    private IProfileInteractor mProfileInteractor;

    public AuthInteractor(IAuthRepository repository, IProfileInteractor profileInteractor) {
        mRepository = repository;
        mProfileInteractor = profileInteractor;
    }

    @Override
    public void login(ResultListener<ProfileData> listener, String email, String password) {
        mRepository.login(listener, new LoginData(email, password));
    }

    @Override
    public void register(ResultListener<ProfileData> listener, String email) {
        mRepository.register(listener, new RegisterData(email));
    }

    @Override
    public void getToken(ResultListener<TokenData> listener) {
        mRepository.getToken(listener);
    }

    @Override
    public void saveProfile(ProfileData data) {
        mProfileInteractor.saveProfile(data);
    }

    @Override
    public void logout(ResultListener<Object> listener) {
        mRepository.logout(listener);
    }

    @Override
    public void release() {
        mRepository = null;

        if (mProfileInteractor != null) {
            mProfileInteractor.release();
        }
        mProfileInteractor = null;
    }
}
