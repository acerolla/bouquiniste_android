package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.data.utils.cloud.ApiManager;
import com.acerolla.bouquiniste.di.module.AddingModule;
import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.CategoryModule;
import com.acerolla.bouquiniste.di.module.FavoritesModule;
import com.acerolla.bouquiniste.di.module.LoginContainerModule;
import com.acerolla.bouquiniste.di.module.LoginModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.di.module.RegisterModule;
import com.acerolla.bouquiniste.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {

    void inject(ApiManager manager);

    CategoryComponent plus(CategoryModule module);
    AuthComponent plus(AuthModule module);
    LoginContainerComponent plus(LoginContainerModule module);
    LoginComponent plus(LoginModule module);
    RegisterComponent plus(RegisterModule module);
}
