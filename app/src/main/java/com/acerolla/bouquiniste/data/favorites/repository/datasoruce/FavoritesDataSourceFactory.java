package com.acerolla.bouquiniste.data.favorites.repository.datasoruce;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesDataSourceFactory {

    public static IFavoritesDataSource getLocalDataSource() {
        return new FavoritesLocalDataSource();
    }

    public static IFavoritesDataSource getCloudDataSource() {
        return new FavoritesCloudDataSource();
    }
}
