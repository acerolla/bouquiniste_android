package com.acerolla.bouquiniste.data.profile.repository;

import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.repository.datasource.ProfileDataSourceFactory;

import java.util.Random;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfileRepository implements IProfileRepository {

    private static final String DEFAULT_NAME = "Bouquiniste-";
    private static final String DEFAULT_EMAIL = "Unknown";
    private static final int UPPER_BOUND = 99999;
    private static final int ZERO = 0;

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        ProfileDataSourceFactory.getCloudDataSource().loadProfile(resultFromCloud -> {
            if (resultFromCloud == null) {
                ProfileDataSourceFactory.getLocalDataSource().loadProfile(resultFromLocal -> {
                    if (resultFromLocal == null) {
                        ProfileData userName = generateUser();
                        ProfileDataSourceFactory.getLocalDataSource().saveProfile(userName);
                        listener.onResult(userName);
                    } else {
                        listener.onResult(resultFromLocal);
                    }
                });
            } else {
                listener.onResult(resultFromCloud);
            }
        });
    }

    private ProfileData generateUser() {
        return new ProfileData(
                ZERO,
                DEFAULT_NAME + new Random().nextInt(UPPER_BOUND),
                DEFAULT_EMAIL);
    }

    @Override
    public void release() {

    }
}
