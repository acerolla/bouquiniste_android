package com.acerolla.bouquiniste.domain.detail;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.domain.BaseInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IDetailInteractor extends BaseInteractor {

    void loadAdvert(ResultListener<AdvertData> listener);
    void loadAdvert(ResultListener<AdvertData> listener, int advertId);
    void changeFavoriteStatus(ResultListener<Boolean> listener);
}
