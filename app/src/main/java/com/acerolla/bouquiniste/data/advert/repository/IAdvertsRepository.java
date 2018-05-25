package com.acerolla.bouquiniste.data.advert.repository;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.BaseRepository;
import com.acerolla.bouquiniste.data.profile.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAdvertsRepository extends BaseRepository {

    void loadAdvert(ResultListener<AdvertData> listener, int advertId);
    void editAdvert(ResultListener<AdvertData> listener, AdvertData advert);
    void loadAdvertList(ResultListener<List<AdvertData>> listener);
    void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId);
}
