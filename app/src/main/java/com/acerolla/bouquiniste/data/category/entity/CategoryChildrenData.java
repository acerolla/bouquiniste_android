package com.acerolla.bouquiniste.data.category.entity;

import java.io.Serializable;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryChildrenData implements Serializable {

    private int mId;

    private String mTitle;

    public CategoryChildrenData() {

    }

    public CategoryChildrenData(int id, String title) {
        mId = id;
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
