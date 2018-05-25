package com.acerolla.bouquiniste.domain.detail;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.domain.BaseInteractor;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public interface IDetailInteractor extends BaseInteractor {

    void loadAdvert(ResultListener<AdvertData> listener, int advertId);
    void editAdvert(ResultListener<AdvertData> listener, AdvertData advert);
}
