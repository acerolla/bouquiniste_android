package com.acerolla.bouquiniste.di.component;

import com.acerolla.bouquiniste.di.module.AdvertsModule;
import com.acerolla.bouquiniste.di.module.ProfileModule;
import com.acerolla.bouquiniste.presentation.profile.view.ProfileFragment;

import dagger.Subcomponent;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Subcomponent(modules = {ProfileModule.class, AdvertsModule.class})
public interface ProfileComponent {

    void inject(ProfileFragment fragment);
}
