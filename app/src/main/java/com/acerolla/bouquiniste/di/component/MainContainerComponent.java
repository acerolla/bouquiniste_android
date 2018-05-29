package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AuthModule;
import com.acerolla.bouquiniste.di.module.MainContainerModule;
import com.acerolla.bouquiniste.presentation.main.view.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {MainContainerModule.class, AuthModule.class})
public interface MainContainerComponent {

    void inject(MainActivity activity);
}
