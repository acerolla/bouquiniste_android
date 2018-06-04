package com.acerolla.bouquiniste.data.adding.repository.datasource;

import android.net.Uri;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingDataSource {

    void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData, Uri uri);
}
