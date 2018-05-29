package com.acerolla.bouquiniste.data.profile.repository;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.repository.datasource.IProfileDataSource;
import com.acerolla.bouquiniste.data.profile.repository.datasource.ProfileDataSourceFactory;

import java.util.Random;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ProfileRepository implements IProfileRepository {

    private static final int ZERO = 0;

    public static final int DEFAULT_ID = ZERO;

    private static final String DEFAULT_NAME = "Unauthorized User";
    private static final String DEFAULT_EMAIL = "Unknown";
    private static final String DEFAULT_TOKEN = "invalid_token";

    private IProfileDataSource mCacheSource;

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        ProfileDataSourceFactory.getCloudDataSource().loadProfile(resultFromCloud -> {
            if (resultFromCloud == null) {
                ProfileDataSourceFactory.getLocalDataSource().loadProfile(resultFromLocal -> {
                    if (resultFromLocal == null) {
                        if (listener != null) {
                            listener.onResult(generateProfile());
                        }
                    } else {
                        if (listener != null) {
                            getCacheSource().saveProfile(resultFromLocal);
                            listener.onResult(resultFromLocal);
                        }
                    }
                });
            } else {
                if (listener != null) {
                    ProfileDataSourceFactory.getLocalDataSource().saveProfile(resultFromCloud);
                    getCacheSource().saveProfile(resultFromCloud);
                    listener.onResult(resultFromCloud);
                }
            }
        });
    }

    private ProfileData generateProfile() {
        return new ProfileData(
                DEFAULT_ID,
                DEFAULT_TOKEN,
                DEFAULT_EMAIL,
                DEFAULT_NAME
        );
    }


    @Override
    public void editProfile(ResultListener<ProfileData> listener, String userName) {
        ProfileDataSourceFactory.getCloudDataSource().editProfile(resultFromCloud -> {
            if (resultFromCloud != null) {
                ProfileData profile = getUserProfileAsync();
                profile.setName(resultFromCloud);
                ProfileDataSourceFactory.getLocalDataSource().saveProfile(profile);
                getCacheSource().saveProfile(profile);
                if (listener != null) {
                    listener.onResult(profile);
                }
            } else {
                if (listener != null) {
                    listener.onResult(null);
                }
            }
        }, userName);
    }

    @Override
    public ProfileData getUserProfileAsync() {
        return getCacheSource().getUserProfileAsync();
    }

    private IProfileDataSource getCacheSource() {
        if (mCacheSource == null) {
            mCacheSource = ProfileDataSourceFactory.getMemoryCacheDataSource();
        }

        return mCacheSource;
    }

    @Override
    public void saveProfile(ProfileData data) {
        getCacheSource().saveProfile(data);
        ProfileDataSourceFactory.getLocalDataSource()
                .saveProfile(data);
    }

    @Override
    public void release() {
        if (mCacheSource != null) {
            mCacheSource.release();
        }
        mCacheSource = null;
    }
}
