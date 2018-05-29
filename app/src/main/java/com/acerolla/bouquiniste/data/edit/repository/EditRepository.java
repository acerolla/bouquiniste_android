package com.acerolla.bouquiniste.data.edit.repository;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.edit.repository.datasource.EditDataSourceFactory;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditRepository implements IEditRepository {

    @Override
    public void closeAdvert(ResultListener<Boolean> listener, int advertId) {
        EditDataSourceFactory.getCloudDataSource()
                .closeAdvert(listener, advertId);
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData data) {
        EditDataSourceFactory.getCloudDataSource()
                .editAdvert(listener, data);
    }

    @Override
    public void release() {

    }
}
