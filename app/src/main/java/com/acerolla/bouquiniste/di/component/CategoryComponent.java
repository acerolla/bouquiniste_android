package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AddingModule;
import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.DetailModule;
import com.acerolla.bouquiniste.di.module.FavoritesModule;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = CategoryModule.class)
public interface CategoryComponent {

    FavoritesComponent plus(FavoritesModule module);
    AddingComponent plus(AddingModule module);
    AdvertsComponent plus(AdvertsModule module);
    DetailComponent plus(DetailModule module);
}
