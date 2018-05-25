package com.acerolla.bouquiniste.data.advert.repository;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.datasource.AdvertDataSourceFactory;
import com.acerolla.bouquiniste.data.profile.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsRepository implements IAdvertsRepository {


    @Override
    public void loadAdvert(ResultListener<AdvertData> listener, int advertId) {
        AdvertDataSourceFactory.getCloudDataSource()
                .getAdvert(listener, advertId);
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData advert) {
        AdvertDataSourceFactory.getCloudDataSource().editAdvert(listener, advert);
    }

    @Override
    public void loadAdvertList(ResultListener<List<AdvertData>> listener) {
        AdvertDataSourceFactory.getLocalDataSource()
                .getAdvertList(resultFromLocal -> {
                    AdvertDataSourceFactory.getCloudDataSource()
                            .getAdvertList(resultFromCloud -> {
                                if (resultFromCloud != null) {
                                    listener.onResult(resultFromCloud);
                                } else if (resultFromLocal != null) {
                                    listener.onResult(resultFromLocal);
                                } else {
                                    listener.onResult(null);
                                }
                            });
                });
    }

    @Override
    public void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        AdvertDataSourceFactory.getCloudDataSource()
                .getUserAdverts(listener, userId);
    }

    @Override
    public void release() {

    }
}
