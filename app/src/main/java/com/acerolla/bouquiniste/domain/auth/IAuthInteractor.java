package com.acerolla.bouquiniste.domain.auth;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.domain.BaseInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAuthInteractor extends BaseInteractor {

    void login(ResultListener<ProfileData> listener, String email, String password);
    void register(ResultListener<ProfileData> listener, String email);
    void getToken(ResultListener<TokenData> listener);
    void saveProfile(ProfileData data);

    void logout(ResultListener<Object> listener);
}
