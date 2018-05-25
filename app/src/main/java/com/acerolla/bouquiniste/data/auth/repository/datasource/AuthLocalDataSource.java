package com.acerolla.bouquiniste.data.auth.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.utils.BouquinisteRunnable;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class AuthLocalDataSource implements IAuthDataSource {

    private static final int ZERO = 0;

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
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(TokenData.class)
                                .deleteBuilder()
                                .delete();
                    }
                }, null);
    }

    @Override
    public void saveToken(TokenData token) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(TokenData.class)
                                .createOrUpdate(token);
                    }
                }, null);
    }

    @Override
    public void getToken(ResultListener<TokenData> listener) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(TokenData.class)
                                .queryForAll();
                    }
                }, result -> {
                    if (result != null && result instanceof List && !((List) result).isEmpty() &&
                            ((List) result).get(ZERO)!= null && ((List) result).get(ZERO) instanceof TokenData) {
                        listener.onResult((TokenData) ((List)result).get(ZERO));
                    } else {
                        listener.onResult(null);
                    }
                });
    }

    @Override
    public TokenData getTokenAsync() {
        //ignore
        return null;
    }
}
