package com.acerolla.bouquiniste.data.adding.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.utils.BouquinisteRunnable;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingLocalDataSource implements IAddingDataSource {

    @Override
    public void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(AdvertData.class)
                                .create(advertData);
                    }
                }, null);
    }
}
