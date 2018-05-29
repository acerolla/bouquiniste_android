package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.EditModule;
import com.acerolla.bouquiniste.presentation.edit.view.EditFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent (modules = {EditModule.class, AdvertsModule.class})
public interface EditComponent {

    void inject(EditFragment activity);
}
