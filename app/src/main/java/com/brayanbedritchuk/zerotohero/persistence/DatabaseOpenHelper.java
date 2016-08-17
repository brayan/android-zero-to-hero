package com.brayanbedritchuk.zerotohero.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.brayanbedritchuk.zerotohero.helper.LogHelper;
import com.brayanbedritchuk.zerotohero.helper.sqlite.CreateTablesHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    private static DatabaseOpenHelper instance;

    private DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            CreateTablesHelper.createTables(db);
        } catch (Exception e) {
            LogHelper.printExceptionLog(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

//        try {
//            EditTablesHelper.editTables(db, oldVersion, newVersion);
//        } catch (Exception e) {
//            LogHelper.printExceptionLog(e);
//        }
    }

}
