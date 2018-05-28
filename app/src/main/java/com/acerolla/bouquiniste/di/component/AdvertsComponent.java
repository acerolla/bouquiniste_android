package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.presentation.adverts.view.AdvertsFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {AdvertsModule.class, CategoryModule.class})
public interface AdvertsComponent {

    void inject(AdvertsFragment fragment);
}
