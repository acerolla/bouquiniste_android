package com.acerolla.bouquiniste.data.advert.repository;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.BaseRepository;
import com.acerolla.bouquiniste.data.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAdvertsRepository extends BaseRepository {

    void loadAdvert(ResultListener<AdvertData> listener, int advertId);
    void loadAdvertList(ResultListener<List<AdvertData>> listener);
    void loadUserAdverts(ResultListener<List<AdvertData>> listener, int userId);

    AdvertData getCachedAdvert();
    void saveAdvertToCache(AdvertData advert);

    void clearAdverts();
}
