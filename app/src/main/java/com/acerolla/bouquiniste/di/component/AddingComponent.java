package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AddingModule;
import com.acerolla.bouquiniste.presentation.adding.view.AddingFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = AddingModule.class)
public interface AddingComponent {

    void inject(AddingFragment fragment);
}
