package com.acerolla.bouquiniste.data.advert.repository.datasource;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertMemoryCacheDataSource implements IAdvertDataSource {

    private AdvertData mAdvert;

    @Override
    public void getAdvertList(ResultListener<List<AdvertData>> listener) {
        //ignore
    }

    @Override
    public void getAdvert(ResultListener<AdvertData> listener, int advertId) {
        //ignore
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData advertData) {
        //ignore
    }

    @Override
    public void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        //ignore
    }

    @Override
    public AdvertData getAdvertAsync() {
        return mAdvert;
    }

    @Override
    public void saveAdvertToCache(AdvertData advert) {
        mAdvert = advert;
    }

    @Override
    public void release() {
        mAdvert = null;
    }
}
