package com.acerolla.bouquiniste.data.auth.repository;

import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.profile.BaseRepository;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAuthRepository extends BaseRepository {

    void login(ResultListener<ProfileData> listener, LoginData data);
    void register(ResultListener<ProfileData> listener, RegisterData data);
    void logout(ResultListener<Object> listener);
    void getToken(ResultListener<TokenData> listener);
    TokenData getTokenAsync();
}
