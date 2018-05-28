package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.presentation.favorites.view.FavoritesFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = FavoritesModule.class)
public interface FavoritesComponent {

    void inject(FavoritesFragment fragment);
}
