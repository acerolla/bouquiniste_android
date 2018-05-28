package com.acerolla.bouquiniste.data.advert.repository.datasource;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertDataSourceFactory {

    public static IAdvertDataSource getCloudDataSource() {
        return new AdvertCloudDataSource();
    }

    public static IAdvertDataSource getLocalDataSource() {
        return new AdvertLocalDataSource();
    }

    public static IAdvertDataSource getMemoryCacheDataSource() {
        return new AdvertMemoryCacheDataSource();
    }
}
