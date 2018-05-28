package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.LoginContainerModule;
import com.acerolla.bouquiniste.presentation.auth.container.view.LoginContainerActivity;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {LoginContainerModule.class, AuthModule.class})
public interface LoginContainerComponent {

    void inject(LoginContainerActivity activity);
}
