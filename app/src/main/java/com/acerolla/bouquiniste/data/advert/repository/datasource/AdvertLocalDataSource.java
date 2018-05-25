package com.acerolla.bouquiniste.data.advert.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.utils.BouquinisteRunnable;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class AdvertLocalDataSource implements IAdvertDataSource {

    private static final int ZERO = 0;

    @Override
    public void getAdvertList(ResultListener<List<AdvertData>> listener) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(AdvertData.class)
                                .queryForAll();
                    }
                }, result -> {
                    if (result != null && result instanceof List && !((List) result).isEmpty() &&
                            ((List) result).get(ZERO)!= null && ((List) result).get(ZERO) instanceof AdvertData) {
                        listener.onResult((List<AdvertData>) result);
                    } else {
                        listener.onResult(null);
                    }
                });
    }

    @Override
    public void getAdvert(ResultListener<AdvertData> listener, int advertId) {
        //ignore
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData advert) {
        //ignore
    }

    @Override
    public void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        //ignore
    }
}
