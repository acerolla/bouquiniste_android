package com.acerolla.bouquiniste.domain.main;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.domain.BaseInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IMainInteractor extends BaseInteractor {

    boolean isUserLoggedIn();
    void tryLoginUser();

    void logout(ResultListener<Object> listener);
}
