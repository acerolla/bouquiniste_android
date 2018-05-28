package com.acerolla.bouquiniste.data.adding.repository;

import com.acerolla.bouquiniste.data.adding.repository.datasource.AddingDataSourceFactory;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingRepository implements IAddingRepository {

    @Override
    public void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData) {
        AddingDataSourceFactory.getCloudDataSource()
                .postAdvert(listener, advertData);
    }

    @Override
    public void release() {

    }
}
