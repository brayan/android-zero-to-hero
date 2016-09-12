package com.brayanbedritchuk.zerotohero.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.brayanbedritchuk.zerotohero.persistence.Database;

public abstract class BaseDAOSQLite {

    private Context context;
    private Database databaseOpenHelper;

    public BaseDAOSQLite(Context context) {
        setContext(context.getApplicationContext());
        setDatabaseOpenHelper(Database.getInstance(getContext()));
    }

    public SQLiteDatabase getReadableDatabase() {
        return getDatabaseOpenHelper().getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return getDatabaseOpenHelper().getWritableDatabase();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Database getDatabaseOpenHelper() {
        return databaseOpenHelper;
    }

    public void setDatabaseOpenHelper(Database databaseOpenHelper) {
        this.databaseOpenHelper = databaseOpenHelper;
    }
}
