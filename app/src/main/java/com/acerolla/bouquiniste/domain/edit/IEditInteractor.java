package com.acerolla.bouquiniste.domain.edit;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.domain.BaseInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IEditInteractor extends BaseInteractor {

    void closeAdvert(ResultListener<Boolean> listener, int advertId);
    void editAdvert(ResultListener<AdvertData> listener, AdvertData data);
    void loadAdvert(ResultListener<AdvertData> listener, int id);
}
