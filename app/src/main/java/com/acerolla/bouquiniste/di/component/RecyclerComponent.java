package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.RecyclerModule;
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.AdvertAdapter;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent (modules = {RecyclerModule.class, CategoryModule.class})
public interface RecyclerComponent {

    void inject(AdvertAdapter adapter);
    void inject(com.acerolla.bouquiniste.presentation.favorites.view.recycler.AdvertAdapter adapter);
}
