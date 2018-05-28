package com.acerolla.bouquiniste.di;

import com.acerolla.bouquiniste.di.component.AddingComponent;
import com.acerolla.bouquiniste.di.component.AdvertsComponent;
import com.acerolla.bouquiniste.di.component.AuthComponent;
import com.acerolla.bouquiniste.di.component.CategoryComponent;
import com.acerolla.bouquiniste.di.component.DaggerRepositoryComponent;
import com.acerolla.bouquiniste.di.component.DetailComponent;
import com.acerolla.bouquiniste.di.component.FavoritesComponent;
import com.acerolla.bouquiniste.di.component.LoginComponent;
import com.acerolla.bouquiniste.di.component.LoginContainerComponent;
import com.acerolla.bouquiniste.di.component.ProfileComponent;
import com.acerolla.bouquiniste.di.component.RegisterComponent;
import com.acerolla.bouquiniste.di.component.RepositoryComponent;
import com.acerolla.bouquiniste.di.module.AddingModule;
import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.DetailModule;
import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.di.module.LoginContainerModule;
import com.acerolla.bouquiniste.di.module.LoginModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.di.module.RegisterModule;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DiManager {

    private static RepositoryComponent sRepositoryComponent;
    private static AddingComponent sAddingComponent;
    private static AdvertsComponent sAdvertsComponent;
    private static FavoritesComponent sFavoritesComponent;
    private static ProfileComponent sProfileComponent;
    private static CategoryComponent sCategoryComponent;
    private static DetailComponent sDetailComponent;
    private static AuthComponent sAuthComponent;
    private static LoginContainerComponent sLoginContainerComponent;
    private static LoginComponent sLoginComponent;
    private static RegisterComponent sRegisterComponent;


    public static RepositoryComponent getRepositoryComponent() {
        if (sRepositoryComponent == null) {
            sRepositoryComponent = DaggerRepositoryComponent.create();
        }

        return sRepositoryComponent;
    }

    private static CategoryComponent getCategoryComponent() {
        if (sCategoryComponent == null) {
            sCategoryComponent = getRepositoryComponent().plus(new CategoryModule());
        }

        return sCategoryComponent;
    }

    private static AuthComponent getAuthComponent() {
        if (sAuthComponent == null) {
            sAuthComponent = getRepositoryComponent().plus(new AuthModule());
        }

        return sAuthComponent;
    }

    public static AddingComponent getAddingComponent() {
        if (sAddingComponent == null) {
            sAddingComponent = getCategoryComponent().plus(new AddingModule());
        }

        return sAddingComponent;
    }

    public static AdvertsComponent getAdvertsComponent() {
        if (sAdvertsComponent == null) {
            sAdvertsComponent = getCategoryComponent().plus(new AdvertsModule());
        }

        return sAdvertsComponent;
    }

    public static FavoritesComponent getFavoritesComponent() {
        if (sFavoritesComponent == null) {
            sFavoritesComponent = getCategoryComponent().plus(new FavoritesModule());
        }

        return sFavoritesComponent;
    }

    public static ProfileComponent getProfileComponent() {
        if (sProfileComponent == null) {
            sProfileComponent = getRepositoryComponent().plus(new ProfileModule());
        }

        return sProfileComponent;
    }

    public static DetailComponent getDetailComponent() {
        if (sDetailComponent == null) {
            sDetailComponent = getCategoryComponent().plus(new DetailModule());
        }

        return sDetailComponent;
    }

    public static LoginContainerComponent getLoginContainerComponent() {
        if (sLoginContainerComponent == null) {
            sLoginContainerComponent = getAuthComponent().plus(new LoginContainerModule());
        }

        return sLoginContainerComponent;
    }

    public static LoginComponent getLoginComponent() {
        if (sLoginComponent == null) {
            sLoginComponent = getAuthComponent().plus(new LoginModule());
        }

        return sLoginComponent;
    }

    public static RegisterComponent getRegisterComponent() {
        if (sRegisterComponent == null) {
            sRegisterComponent = getAuthComponent().plus(new RegisterModule());
        }

        return sRegisterComponent;
    }


    public static void release() {
        sRepositoryComponent = null;
        sAddingComponent = null;
        sAdvertsComponent = null;
        sProfileComponent = null;
        sFavoritesComponent = null;
        sCategoryComponent = null;
        sDetailComponent = null;
        sAuthComponent = null;
        sLoginContainerComponent = null;
        sLoginComponent = null;
        sRegisterComponent = null;
    }
}
