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
import com.acerolla.bouquiniste.di.component.MainContainerComponent;
import com.acerolla.bouquiniste.di.component.ProfileComponent;
import com.acerolla.bouquiniste.di.component.RecyclerComponent;
import com.acerolla.bouquiniste.di.component.RegisterComponent;
import com.acerolla.bouquiniste.di.component.RepositoryComponent;
import com.acerolla.bouquiniste.di.module.AddingModule;
import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.DetailModule;
import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.di.module.FavoritesRecyclerModule;
import com.acerolla.bouquiniste.di.module.LoginContainerModule;
import com.acerolla.bouquiniste.di.module.LoginModule;
import com.acerolla.bouquiniste.di.module.MainContainerModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.di.module.CommonRecyclerModule;
import com.acerolla.bouquiniste.di.module.RegisterModule;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DiManager {

    private static RepositoryComponent sRepositoryComponent;
    private static MainContainerComponent sMainContainerComponent;
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
    private static RecyclerComponent sRecyclerComponent;


    public static RepositoryComponent getRepositoryComponent() {
        if (sRepositoryComponent == null) {
            sRepositoryComponent = DaggerRepositoryComponent.create();
        }

        return sRepositoryComponent;
    }

    public static MainContainerComponent getMainContainerComponent() {
        if (sMainContainerComponent == null) {
            sMainContainerComponent = getRepositoryComponent().plus(new MainContainerModule());
        }

        return sMainContainerComponent;
    }

    public static CategoryComponent getCategoryComponent() {
        if (sCategoryComponent == null) {
            sCategoryComponent = getRepositoryComponent().plus(new CategoryModule());
        }

        return sCategoryComponent;
    }

    public static AuthComponent getAuthComponent() {
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
            sProfileComponent = getCategoryComponent().plus(new ProfileModule());
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
            sLoginContainerComponent = getRepositoryComponent().plus(new LoginContainerModule());
        }

        return sLoginContainerComponent;
    }

    public static LoginComponent getLoginComponent() {
        if (sLoginComponent == null) {
            sLoginComponent = getRepositoryComponent().plus(new LoginModule());
        }

        return sLoginComponent;
    }

    public static RegisterComponent getRegisterComponent() {
        if (sRegisterComponent == null) {
            sRegisterComponent = getRepositoryComponent().plus(new RegisterModule());
        }

        return sRegisterComponent;
    }

    public static RecyclerComponent getRecyclerComponent() {
        if (sRecyclerComponent == null) {
            sRecyclerComponent = getCategoryComponent().plus(new CommonRecyclerModule(), new FavoritesRecyclerModule());
        }

        return sRecyclerComponent;
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
        sRecyclerComponent = null;
    }
}
