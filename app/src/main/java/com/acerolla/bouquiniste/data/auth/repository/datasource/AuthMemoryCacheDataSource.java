package com.acerolla.bouquiniste.data.auth.repository.datasource;

import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class AuthMemoryCacheDataSource implements IAuthDataSource {

    private TokenData mToken;

    @Override
    public void login(ResultListener<ProfileData> listener, LoginData login) {
        //ignore
    }

    @Override
    public void register(ResultListener<ProfileData> listener, RegisterData register) {
        //ignore
    }

    @Override
    public void logout(ResultListener<Object> listener) {
        mToken = null;
    }

    @Override
    public void saveToken(TokenData token) {
        mToken = token;
    }

    @Override
    public void getToken(ResultListener<TokenData> listener) {
        //ignore
    }

    @Override
    public TokenData getTokenAsync() {
        return mToken;
    }

    @Override
    public void release() {
        mToken = null;
    }
}
