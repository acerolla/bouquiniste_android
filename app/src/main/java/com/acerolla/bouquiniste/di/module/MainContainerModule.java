package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.data.category.repository.ICategoryRepository;
import com.acerolla.bouquiniste.data.profile.repository.IProfileRepository;
import com.acerolla.bouquiniste.domain.main.IMainInteractor;
import com.acerolla.bouquiniste.domain.main.MainInteractor;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;
import com.acerolla.bouquiniste.presentation.main.presenter.IMainPresenter;
import com.acerolla.bouquiniste.presentation.main.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class MainContainerModule {

    @Provides
    public IMainPresenter providePresenter(IMainInteractor interactor) {
        return new MainPresenter(interactor);
    }

    @Provides
    public  IMainInteractor provideInteractor(IAuthRepository authRepository, IProfileRepository profileRepository, IAdvertsRepository advertsRepository, ICategoryRepository categoryRepository) {
        return new MainInteractor(authRepository, profileRepository, advertsRepository, categoryRepository);
    }
}
