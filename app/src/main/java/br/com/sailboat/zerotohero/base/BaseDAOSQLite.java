package br.com.sailboat.zerotohero.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import br.com.sailboat.zerotohero.persistence.DatabaseOpenHelper;

public abstract class BaseDAOSQLite {

    private Context context;
    private DatabaseOpenHelper databaseOpenHelper;

    public BaseDAOSQLite(Context context) {
        setContext(context.getApplicationContext());
        setDatabaseOpenHelper(DatabaseOpenHelper.getInstance(getContext()));
    }

    protected long executeInsert(SQLiteStatement statement) {
        try {
            getWritableDatabase().beginTransactionNonExclusive();
            long id = statement.executeInsert();
            statement.clearBindings();
            getWritableDatabase().setTransactionSuccessful();

            return id;
        } finally {
            getWritableDatabase().endTransaction();
        }
    }

    protected void executeUpdate(SQLiteStatement statement) {
        try {
            getWritableDatabase().beginTransactionNonExclusive();
            statement.executeUpdateDelete();
            statement.clearBindings();
            getWritableDatabase().setTransactionSuccessful();
        } finally {
            getWritableDatabase().endTransaction();
        }
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
