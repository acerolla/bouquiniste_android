package com.acerolla.bouquiniste.data.category.entity;

import com.acerolla.bouquiniste.data.utils.db.DbHelper;
import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@DatabaseTable(tableName = DbHelper.TABLE_CATEGORY)
public class CategoryParentData implements Parent<CategoryChildrenData> {

    @DatabaseField(id = true, columnName = "id")
    private int mId;

    @DatabaseField(columnName = "title")
    private String mTitle;

    @DatabaseField(columnName = "children", dataType = DataType.SERIALIZABLE)
    private ArrayList<CategoryChildrenData> mChildren;

    public CategoryParentData() {

    }

    public CategoryParentData(int id, String title, ArrayList<CategoryChildrenData> children) {
        mId = id;
        mTitle = title;
        mChildren = children;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public ArrayList<CategoryChildrenData> getChildren() {
        return mChildren;
    }

    @Override
    public ArrayList<CategoryChildrenData> getChildList() {
        return getChildren();
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
