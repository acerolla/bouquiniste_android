package com.acerolla.bouquiniste.data.advert.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;
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
                        if (listener != null) {
                            listener.onResult((List<AdvertData>) result);
                        }
                    } else {
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }
                });
    }

    @Override
    public void getAdvert(ResultListener<AdvertData> listener, int advertId) {
        //ignore
    }

    @Override
    public void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(AdvertData.class)
                                .queryBuilder()
                                .where()
                                .eq("id", userId);
                    }
                }, result -> {
                    if (result != null && result instanceof List && !((List) result).isEmpty() &&
                            ((List) result).get(ZERO)!= null && ((List) result).get(ZERO) instanceof AdvertData) {
                        if (listener != null) {
                            listener.onResult((List<AdvertData>) result);
                        }
                    } else {
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }
                });
    }

    @Override
    public AdvertData getAdvertAsync() {
        //ignore
        return null;
    }

    @Override
    public void saveAdvertToCache(AdvertData advert) {
        //ignore
    }

    @Override
    public void clearAdverts() {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(AdvertData.class)
                                .deleteBuilder()
                                .delete();
                    }
                }, null);
    }

    @Override
    public void release() {

    }
}
