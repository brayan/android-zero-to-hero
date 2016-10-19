package br.com.sailboat.zerotohero.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.sailboat.zerotohero.helper.CreateTableHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    private static DatabaseOpenHelper instance;

    private Context context;

    private DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setContext(context);
    }

    public static DatabaseOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        CreateTableHelper.createTables(getContext(), database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // TODO
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
