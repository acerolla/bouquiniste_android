package com.acerolla.bouquiniste.data.category.entity;

import java.io.Serializable;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */

public class CategoryChildrenData implements Serializable {

    private int mId;
    private String mName;

    public CategoryChildrenData() {

    }

    public CategoryChildrenData(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
