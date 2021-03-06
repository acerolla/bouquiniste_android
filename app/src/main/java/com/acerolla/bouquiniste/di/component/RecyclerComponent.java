package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.CommonRecyclerModule;
import com.acerolla.bouquiniste.di.module.FavoritesRecyclerModule;
import com.acerolla.bouquiniste.di.module.ProfileRecyclerModule;
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.AdvertAdapter;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent (modules = {CommonRecyclerModule.class, FavoritesRecyclerModule.class, ProfileRecyclerModule.class, CategoryModule.class})
public interface RecyclerComponent {

    void inject(AdvertAdapter adapter);
    void inject(com.acerolla.bouquiniste.presentation.favorites.view.recycler.AdvertAdapter adapter);
    void inject(com.acerolla.bouquiniste.presentation.profile.view.recycler.AdvertAdapter adapter);
}
