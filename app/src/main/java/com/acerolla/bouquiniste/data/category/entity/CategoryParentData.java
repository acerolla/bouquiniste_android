package com.acerolla.bouquiniste.data.category.entity;

import com.acerolla.bouquiniste.data.utils.db.DbHelper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
@DatabaseTable(tableName = DbHelper.TABLE_CATEGORY)
public class CategoryParentData {

    @DatabaseField(id = true, columnName = "id")
    private int mId;

    @DatabaseField(columnName = "name")
    private String mName;

    @DatabaseField(columnName = "hotspot", dataType = DataType.SERIALIZABLE)
    private ArrayList<CategoryChildrenData> mChildren;

    public CategoryParentData() {

    }

    public CategoryParentData(String name, ArrayList<CategoryChildrenData> children) {
        mName = name;
        mChildren = children;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<CategoryChildrenData> getChildren() {
        return mChildren;
    }
}
