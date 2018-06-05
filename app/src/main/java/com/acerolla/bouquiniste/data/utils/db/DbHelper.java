package com.acerolla.bouquiniste.data.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

    public static final String TABLE_TOKEN = "bouquiniste_token";
    public static final String TABLE_PROFILE = "bouquiniste_profile";
    public static final String TABLE_ADVERTS = "bouquiniste_adverts";

    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "bouquiniste.app";

    public DbHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ProfileData.class);
            TableUtils.createTable(connectionSource, AdvertData.class);
            TableUtils.createTable(connectionSource, TokenData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

}
