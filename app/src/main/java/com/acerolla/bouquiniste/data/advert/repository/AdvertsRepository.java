package com.acerolla.bouquiniste.data.advert.repository;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.datasource.AdvertDataSourceFactory;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.repository.datasource.IAdvertDataSource;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsRepository implements IAdvertsRepository {

    private IAdvertDataSource mMemoryCacheDataSource;


    @Override
    public void loadAdvert(ResultListener<AdvertData> listener, int advertId) {
        AdvertDataSourceFactory.getCloudDataSource()
                .getAdvert(listener, advertId);
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData advert) {
        AdvertDataSourceFactory.getCloudDataSource()
                .editAdvert(listener, advert);
    }

    @Override
    public void loadAdvertList(ResultListener<List<AdvertData>> listener) {
        AdvertDataSourceFactory.getLocalDataSource()
                .getAdvertList(resultFromLocal -> {
                    AdvertDataSourceFactory.getCloudDataSource()
                            .getAdvertList(resultFromCloud -> {
                                if (resultFromCloud != null) {
                                    if (listener != null) {
                                        listener.onResult(resultFromCloud);
                                    }
                                } else if (resultFromLocal != null) {
                                    if (listener != null) {
                                        listener.onResult(resultFromLocal);
                                    }
                                } else {
                                    if (listener != null) {
                                        listener.onResult(null);
                                    }
                                }
                            });
                });
    }

    @Override
    public void loadUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        AdvertDataSourceFactory.getCloudDataSource()
                .getUserAdverts(listener, userId);
    }

    @Override
    public AdvertData getCachedAdvert() {
        return getMemoryCache().getAdvertAsync();
    }

    private IAdvertDataSource getMemoryCache() {
        if (mMemoryCacheDataSource == null) {
            mMemoryCacheDataSource = AdvertDataSourceFactory.getMemoryCacheDataSource();
        }

        return mMemoryCacheDataSource;
    }

    @Override
    public void saveAdvertToCache(AdvertData advert) {
        getMemoryCache().saveAdvertToCache(advert);
    }

    @Override
    public void release() {
        if (mMemoryCacheDataSource != null) {
            mMemoryCacheDataSource.release();
        }

        mMemoryCacheDataSource = null;
    }
}
