package com.acerolla.bouquiniste.data.profile.repository.datasource;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ProfileDataSourceFactory {

    public static IProfileDataSource getCloudDataSource() {
        return new ProfileCloudDataSource();
    }

    public static IProfileDataSource getLocalDataSource() {
        return new ProfileLocalDataSource();
    }

    public static IProfileDataSource getMemoryCacheDataSource() {
        return new ProfileMemoryCacheDataSource();
    }
}
