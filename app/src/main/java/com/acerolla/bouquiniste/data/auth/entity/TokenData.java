package com.acerolla.bouquiniste.data.auth.entity;

import com.acerolla.bouquiniste.data.utils.db.DbHelper;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
@DatabaseTable(tableName = DbHelper.TABLE_TOKEN)
public class TokenData {

    @DatabaseField(id = true, columnName = "token")
    private String mToken;

    public TokenData() {

    }

    public TokenData(String token) {
        mToken = token;
    }

    public String getToken() {
        return mToken;
    }
}
