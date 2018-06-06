package com.acerolla.bouquiniste.domain.main;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.data.category.repository.ICategoryRepository;
import com.acerolla.bouquiniste.data.profile.repository.IProfileRepository;
import com.acerolla.bouquiniste.domain.profile.IProfileInteractor;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class MainInteractor implements IMainInteractor{

    private IAuthRepository mAuthRepository;
    private IProfileRepository mProfileRepository;
    private IAdvertsRepository mAdvertsRepository;
    private ICategoryRepository mCategoryRepository;

    public MainInteractor(IAuthRepository authRepository, IProfileRepository profileRepository, IAdvertsRepository advertsRepository, ICategoryRepository categoryRepository) {
        mAuthRepository = authRepository;
        mProfileRepository = profileRepository;
        mAdvertsRepository = advertsRepository;
        mCategoryRepository = categoryRepository;
    }

    @Override
    public boolean isUserLoggedIn() {
        return mAuthRepository.getTokenAsync() != null;
    }

    @Override
    public void tryLoginUser() {
        mAuthRepository.getToken(resultFromToken -> {
            if (resultFromToken != null && mProfileRepository != null) {
                mProfileRepository.loadProfile(result -> {
                    //ignore
                    //just to cache profile
                });
            }
        });
    }

    @Override
    public void logout(ResultListener<Object> listener) {
        mAdvertsRepository.clearAdverts();
        mCategoryRepository.clearCategory();
        mProfileRepository.clearProfile();
        mAuthRepository.logout(result -> {
            if (listener != null) {
                listener.onResult(result);
            }
        });
    }

    @Override
    public void release() {
        mAuthRepository = null;
        mProfileRepository = null;
        mAdvertsRepository = null;
    }
}
