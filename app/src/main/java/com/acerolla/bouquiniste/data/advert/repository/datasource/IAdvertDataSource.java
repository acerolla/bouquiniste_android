package com.acerolla.bouquiniste.data.advert.repository.datasource;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAdvertDataSource {

    void getAdvertList(ResultListener<List<AdvertData>> listener);
    void getAdvert(ResultListener<AdvertData> listener, int advertId);
    void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId);
    AdvertData getAdvertAsync();
    void saveAdvertToCache(AdvertData advert);
    void saveAdverts(List<AdvertData> adverts);
    void clearAdverts();

    void release();
}
