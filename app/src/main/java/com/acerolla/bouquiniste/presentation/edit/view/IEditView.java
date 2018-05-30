package com.acerolla.bouquiniste.presentation.edit.view;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IEditView {

    int getExtraId();
    void setContentData(AdvertData data);

    void notifySuccess(String text);
    void notifyFailure(String text);

    void navigateBack(boolean isEdited);
    void navigateToCategories();

    void changeCategory(int categoryId, String categoryTitle);

    void share();
}
