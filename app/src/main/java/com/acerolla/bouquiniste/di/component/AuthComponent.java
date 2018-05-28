package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.LoginContainerModule;
import com.acerolla.bouquiniste.di.module.LoginModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.di.module.RegisterModule;
import com.acerolla.bouquiniste.presentation.auth.container.presenter.LoginContainerPresenter;
import com.acerolla.bouquiniste.presentation.auth.container.view.LoginContainerActivity;
import com.acerolla.bouquiniste.presentation.auth.login.presenter.LoginPresenter;
import com.acerolla.bouquiniste.presentation.auth.register.presenter.RegisterPresenter;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {AuthModule.class, ProfileModule.class, AdvertsModule.class, CategoryModule.class})
public interface AuthComponent {

    void inject(LoginContainerPresenter presenter);
    void inject(LoginPresenter presenter);
    void inject(RegisterPresenter presenter);
}
