package com.acerolla.bouquiniste.data.auth.repository;

import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.auth.repository.datasource.AuthDataSourceFactory;
import com.acerolla.bouquiniste.data.auth.repository.datasource.IAuthDataSource;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AuthRepository implements IAuthRepository {

    private IAuthDataSource mCacheSource;

    @Override
    public void login(ResultListener<ProfileData> listener,  LoginData data) {
        AuthDataSourceFactory.getCloudDataSource()
                .login(resultFromCloud -> {
                    if (resultFromCloud != null) {
                        TokenData tokenData = new TokenData(resultFromCloud.getToken());
                        AuthDataSourceFactory.getLocalDataSource().saveToken(tokenData);
                        getCacheSource().saveToken(tokenData);
                    }
                    if (listener != null) {
                        listener.onResult(resultFromCloud);
                    }
                }, data);
    }

    @Override
    public void register(ResultListener<ProfileData> listener, RegisterData data) {
        AuthDataSourceFactory.getCloudDataSource()
                .register(result -> {
                    if (result != null) {
                        TokenData tokenData = new TokenData(result.getToken());
                        AuthDataSourceFactory.getLocalDataSource().saveToken(tokenData);
                        getCacheSource().saveToken(tokenData);
                    }
                    if (listener != null) {
                        listener.onResult(result);
                    }
                }, data);
    }

    @Override
    public void getToken(ResultListener<TokenData> listener) {
        AuthDataSourceFactory.getLocalDataSource()
                .getToken(result -> {
                    if (result != null) {
                        getCacheSource().saveToken(result);
                    }
                    if (listener != null) {
                        listener.onResult(result);
                    }
                });
    }

    @Override
    public TokenData getTokenAsync() {
        if (getCacheSource() == null || getCacheSource().getTokenAsync() == null) {
            getCacheSource().saveToken(AuthDataSourceFactory.getLocalDataSource()
                    .getTokenAsync());
        }
        return getCacheSource().getTokenAsync();
    }

    private IAuthDataSource getCacheSource() {
        if (mCacheSource == null){
            mCacheSource = AuthDataSourceFactory.getMemoryCacheDataSource();
        }

        return mCacheSource;
    }

    @Override
    public void logout(ResultListener<Object> listener) {
        getCacheSource().logout(null);
        AuthDataSourceFactory.getLocalDataSource().logout(listener);
    }

    @Override
    public void release() {
        if (mCacheSource != null) {
            mCacheSource.release();
        }
        mCacheSource = null;
    }
}
