package com.acerolla.bouquiniste.data.category.repository.datasource;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryDataSourceFactory {

    public static ICategoryDataSource getCloudDataSource() {
        return new CategoryCloudDataSource();
    }

    public static ICategoryDataSource getLocalDataSource() {
        return new CategoryLocalDataSource();
    }

    public static ICategoryDataSource getMemoryCacheDataSource() {
        return new CategoryMemoryCacheDataSource();
    }
}
