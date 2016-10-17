package br.com.sailboat.zerotohero.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;

public abstract class BaseDAOSQLite {

    private Context context;
    private DatabaseOpenHelper databaseOpenHelper;

    public BaseDAOSQLite(Context context) {
        setContext(context.getApplicationContext());
        setDatabaseOpenHelper(DatabaseOpenHelper.getInstance(getContext()));
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

    public DatabaseOpenHelper getDatabaseOpenHelper() {
        return databaseOpenHelper;
    }

    public void setDatabaseOpenHelper(DatabaseOpenHelper databaseOpenHelper) {
        this.databaseOpenHelper = databaseOpenHelper;
    }
}
