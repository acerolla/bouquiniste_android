package com.acerolla.bouquiniste.presentation.detail.view;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IDetailView {

    void setContentData(AdvertData data);
    void setContentVisibility(boolean isVisible);
    void setLoaderVisibility(boolean isVisible);
    void setErrorVisibility(boolean isVisible);
    void changeFavoriteStatus(boolean isFavorite);
    int getExtraId();
}
