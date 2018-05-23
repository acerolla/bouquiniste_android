package com.acerolla.bouquiniste.data.profile.repository.datasource;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfileDataSourceFactory {

    public static IProfileDataSource getCloudDataSource() {
        return new ProfileCloudDataSource();
    }

    public static IProfileDataSource getLocalDataSource() {
        return new ProfileLocalDataSource();
    }
}
