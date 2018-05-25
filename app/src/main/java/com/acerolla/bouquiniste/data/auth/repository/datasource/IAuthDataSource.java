package com.acerolla.bouquiniste.data.auth.repository.datasource;

import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAuthDataSource {

    void login(ResultListener<ProfileData> listener, LoginData login);
    void register(ResultListener<ProfileData> listener, RegisterData register);
    void logout(ResultListener<Object> listener);
    void saveToken(TokenData token);
    void getToken(ResultListener<TokenData> listener);
    TokenData getTokenAsync();

}
