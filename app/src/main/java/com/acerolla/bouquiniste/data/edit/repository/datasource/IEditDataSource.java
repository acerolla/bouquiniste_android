package com.acerolla.bouquiniste.data.edit.repository.datasource;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IEditDataSource {

    void closeAdvert(ResultListener<Boolean> listener, int advertId);
    void editAdvert(ResultListener<AdvertData> listener, AdvertData data);
}
