package com.acerolla.bouquiniste.data.edit.repository.datasource;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditDataSourceFactory {

    public static IEditDataSource getCloudDataSource() {
        return new EditCloudDataSource();
    }
}
