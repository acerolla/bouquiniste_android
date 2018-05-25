package com.acerolla.bouquiniste.data.adding.repository.datasource;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingDataSourceFactory {

    public static IAddingDataSource getCloudDataSource() {
        return new AddingCloudDataSource();
    }

    public static IAddingDataSource getLocalDataSource() {
        return new AddingLocalDataSource();
    }
}
