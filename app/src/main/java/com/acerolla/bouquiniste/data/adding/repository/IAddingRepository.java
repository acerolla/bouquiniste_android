package com.acerolla.bouquiniste.data.adding.repository;

import android.net.Uri;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.BaseRepository;
import com.acerolla.bouquiniste.data.ResultListener;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingRepository extends BaseRepository {

    void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData);
    void saveUri(Uri uri);
}
