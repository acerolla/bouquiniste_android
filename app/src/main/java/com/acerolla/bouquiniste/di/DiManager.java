package com.acerolla.bouquiniste.di;

import com.acerolla.bouquiniste.di.component.AddingComponent;
import com.acerolla.bouquiniste.di.component.AdvertsComponent;
import com.acerolla.bouquiniste.di.component.DaggerRepositoryComponent;
import com.acerolla.bouquiniste.di.component.FavoritesComponent;
import com.acerolla.bouquiniste.di.component.ProfileComponent;
import com.acerolla.bouquiniste.di.component.RepositoryComponent;
import com.acerolla.bouquiniste.di.module.AddingModule;
import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public class DiManager {

    private static RepositoryComponent sRepositoryComponent;
    private static AddingComponent sAddingComponent;
    private static AdvertsComponent sAdvertsComponent;
    private static FavoritesComponent sFavoritesComponent;
    private static ProfileComponent sProfileComponent;

    private static RepositoryComponent getRepositoryComponent() {
        if (sRepositoryComponent == null) {
            sRepositoryComponent = DaggerRepositoryComponent.create();
        }

        return sRepositoryComponent;
    }

    public static AddingComponent getAddingComponent() {
        if (sAddingComponent == null) {
            sAddingComponent = getRepositoryComponent().plus(new AddingModule());
        }

        return sAddingComponent;
    }

    public static AdvertsComponent getAdvertsComponent() {
        if (sAdvertsComponent == null) {
            sAdvertsComponent = getRepositoryComponent().plus(new AdvertsModule());
        }

        return sAdvertsComponent;
    }

    public static FavoritesComponent getFavoritesComponent() {
        if (sFavoritesComponent == null) {
            sFavoritesComponent = getRepositoryComponent().plus(new FavoritesModule());
        }

        return sFavoritesComponent;
    }

    public static ProfileComponent getProfileComponent() {
        if (sProfileComponent == null) {
            sProfileComponent = getRepositoryComponent().plus(new ProfileModule());
        }

        return sProfileComponent;
    }

    public static void release() {
        sRepositoryComponent = null;
        sAddingComponent = null;
        sAdvertsComponent = null;
        sProfileComponent = null;
        sFavoritesComponent = null;
    }
}
