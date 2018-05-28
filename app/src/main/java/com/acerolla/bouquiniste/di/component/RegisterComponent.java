package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.RegisterModule;
import com.acerolla.bouquiniste.presentation.auth.register.view.RegisterFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent (modules = {RegisterModule.class, AuthModule.class})
public interface RegisterComponent {

    void inject(RegisterFragment fragment);
}
