package com.acerolla.bouquiniste.data.advert.repository.datasource;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public interface IAdvertDataSource {

    void getAdvertList(ResultListener<List<AdvertData>> listener);
    void getAdvert(ResultListener<AdvertData> listener, int advertId);
    void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId);
    AdvertData getAdvertAsync();
    void saveAdvertToCache(AdvertData advert);
    void release();
}
