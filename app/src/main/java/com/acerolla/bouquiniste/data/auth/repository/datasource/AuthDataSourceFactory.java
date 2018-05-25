package com.acerolla.bouquiniste.data.auth.repository.datasource;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AuthDataSourceFactory {

    public static IAuthDataSource getCloudDataSource() {
        return new AuthCloudDataSource();
    }

    public static IAuthDataSource getLocalDataSource() {
        return new AuthLocalDataSource();
    }

    public static IAuthDataSource getMemoryCacheDataSource() {
        return new AuthMemoryCacheDataSource();
    }
}
