package com.acerolla.bouquiniste.data.profile.entity;

import com.acerolla.bouquiniste.data.utils.db.DbHelper;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
@DatabaseTable(tableName = DbHelper.TABLE_PROFILE)
public class ProfileData {

    @DatabaseField(id = true, columnName = "id")
    private int mId;

    @DatabaseField(columnName = "user")
    private String mUser;

    @DatabaseField(columnName = "email")
    private String mEmail;

    public ProfileData() {

    }

    public ProfileData(int id, String user, String email) {
        this.mId = id;
        this.mUser = user;
        this.mEmail = email;
    }

    public int getId() {
        return mId;
    }

    public String getUser() {
        return mUser;
    }

    public String getEmail() {
        return mEmail;
    }
}
