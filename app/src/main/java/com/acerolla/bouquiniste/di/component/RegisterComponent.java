package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.di.module.RegisterModule;
import com.acerolla.bouquiniste.presentation.auth.register.view.RegisterFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent (modules = {RegisterModule.class, ProfileModule.class, AuthModule.class, AdvertsModule.class, CategoryModule.class})
public interface RegisterComponent {

    void inject(RegisterFragment fragment);
}
