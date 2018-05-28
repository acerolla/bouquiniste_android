package com.acerolla.bouquiniste.di.module;

import com.acerolla.bouquiniste.data.adding.repository.AddingRepository;
import com.acerolla.bouquiniste.data.adding.repository.IAddingRepository;
import com.acerolla.bouquiniste.data.advert.repository.AdvertsRepository;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.auth.repository.AuthRepository;
import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.data.category.repository.CategoryRepository;
import com.acerolla.bouquiniste.data.category.repository.ICategoryRepository;
import com.acerolla.bouquiniste.data.favorites.repository.FavoritesRepository;
import com.acerolla.bouquiniste.data.favorites.repository.IFavoritesRepository;
import com.acerolla.bouquiniste.data.profile.repository.IProfileRepository;
import com.acerolla.bouquiniste.data.profile.repository.ProfileRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public IAddingRepository provideAddingRepository() {
        return new AddingRepository();
    }

    @Provides
    @Singleton
    public IAdvertsRepository provideAdvertsRepository() {
        return new AdvertsRepository();
    }

    @Provides
    @Singleton
    public IFavoritesRepository provideFavoritesRepository() {
        return new FavoritesRepository();
    }

    @Provides
    @Singleton
    public IProfileRepository provideProfileRepository() {
        return new ProfileRepository();
    }

    @Provides
    @Singleton
    public IAuthRepository provideAuthRepository() {
        return new AuthRepository();
    }

    @Provides
    @Singleton
    public ICategoryRepository provideCategoryRepository() {
        return new CategoryRepository();
    }
}
