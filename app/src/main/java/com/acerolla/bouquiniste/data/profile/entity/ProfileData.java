package com.acerolla.bouquiniste.data.profile.entity;

import com.acerolla.bouquiniste.data.utils.db.DbHelper;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
@DatabaseTable(tableName = DbHelper.TABLE_PROFILE)
public class ProfileData {

    @DatabaseField(id = true, columnName = "id")
    private int mId;

    @DatabaseField(columnName = "token")
    private String mToken;

    @DatabaseField(columnName = "name")
    private String mName;

    @DatabaseField(columnName = "email")
    private String mEmail;

    public ProfileData() {

    }

    public ProfileData(int id, String token, String user, String email) {
        mId = id;
        mToken = token;
        mName = user;
        mEmail = email;
    }

    public int getId() {
        return mId;
    }

    public String getUser() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getToken() {
        return mToken;
    }
}
