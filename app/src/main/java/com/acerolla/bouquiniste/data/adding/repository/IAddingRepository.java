package com.acerolla.bouquiniste.data.adding.repository;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.BaseRepository;
import com.acerolla.bouquiniste.data.profile.ResultListener;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingRepository extends BaseRepository {

    void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData);
}
