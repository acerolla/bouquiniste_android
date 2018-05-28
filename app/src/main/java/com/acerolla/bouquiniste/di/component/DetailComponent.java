package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.DetailModule;
import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.presentation.detail.view.DetailActivity;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {DetailModule.class, FavoritesModule.class})
public interface DetailComponent {

    void inject(DetailActivity activity);
}
