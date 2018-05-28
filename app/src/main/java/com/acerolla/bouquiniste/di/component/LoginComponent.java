package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.LoginModule;
import com.acerolla.bouquiniste.presentation.auth.login.view.LoginFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {LoginModule.class, AuthModule.class})
public interface LoginComponent {

    void inject(LoginFragment fragment);
}
