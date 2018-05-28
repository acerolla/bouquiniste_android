package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.LoginContainerModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.presentation.auth.container.view.LoginContainerActivity;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {LoginContainerModule.class, ProfileModule.class, AuthModule.class, AdvertsModule.class, CategoryModule.class})
public interface LoginContainerComponent {

    void inject(LoginContainerActivity activity);
}
