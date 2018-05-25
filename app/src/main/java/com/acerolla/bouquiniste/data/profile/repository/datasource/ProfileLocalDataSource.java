package com.acerolla.bouquiniste.data.profile.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.utils.BouquinisteRunnable;

import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfileLocalDataSource implements IProfileDataSource {

    private static final int ZERO = 0;

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(ProfileData.class)
                                .queryForAll();
                    }
                }, result -> {
                    if (result != null && result instanceof List && !((List) result).isEmpty()) {
                        if (listener != null) {
                            listener.onResult(((ProfileData) ((List) result).get(ZERO)));
                        }
                    } else {
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }

                });
    }

    @Override
    public void saveProfile(ProfileData profile) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(ProfileData.class)
                                .createOrUpdate(profile);
                    }
                }, null);
    }

    @Override
    public void editProfile(ResultListener<ProfileData> listener, ProfileData userData) {
        //ignore
    }
}
