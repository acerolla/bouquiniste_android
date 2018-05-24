package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.presentation.favorites.view.FavoritesFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = FavoritesModule.class)
public interface FavoritesComponent {

    void inject(FavoritesFragment fragment);
}
