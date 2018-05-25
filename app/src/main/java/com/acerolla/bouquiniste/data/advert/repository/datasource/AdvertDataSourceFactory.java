package com.acerolla.bouquiniste.data.advert.repository.datasource;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public class AdvertDataSourceFactory {

    public static IAdvertDataSource getCloudDataSource() {
        return new AdvertCloudDataSource();
    }

    public static IAdvertDataSource getLocalDataSource() {
        return new AdvertLocalDataSource();
    }
}
