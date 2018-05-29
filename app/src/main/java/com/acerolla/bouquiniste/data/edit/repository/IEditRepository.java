package com.acerolla.bouquiniste.data.edit.repository;

import com.acerolla.bouquiniste.data.BaseRepository;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IEditRepository extends BaseRepository {

    void closeAdvert(ResultListener<Boolean> listener, int advertId);
    void editAdvert(ResultListener<AdvertData> listener, AdvertData data);
}
