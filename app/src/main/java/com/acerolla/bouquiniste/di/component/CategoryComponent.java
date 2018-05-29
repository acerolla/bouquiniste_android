package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AddingModule;
import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.DetailModule;
import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.di.module.FavoritesRecyclerModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.di.module.CommonRecyclerModule;
import com.acerolla.bouquiniste.presentation.category.view.CategoryActivity;
import com.acerolla.bouquiniste.presentation.detail.view.DetailView;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = CategoryModule.class)
public interface CategoryComponent {

    void inject(CategoryActivity activity);
    void inject(DetailView view);

    FavoritesComponent plus(FavoritesModule module);
    AddingComponent plus(AddingModule module);
    AdvertsComponent plus(AdvertsModule module);
    DetailComponent plus(DetailModule module);
    ProfileComponent plus(ProfileModule module);

    RecyclerComponent plus(CommonRecyclerModule module1, FavoritesRecyclerModule module2);
}
