package br.com.sailboat.zerotohero.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.sailboat.canoe.base.BaseSQLite;
import br.com.sailboat.canoe.helper.CreateTablesHelper;
import br.com.sailboat.canoe.helper.LogHelper;
import br.com.sailboat.zerotohero.R;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseHistorySQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.ExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutExerciseSQLite;
import br.com.sailboat.zerotohero.persistence.sqlite.WorkoutSQLite;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    private static DatabaseOpenHelper instance;

    private Context context;

    private DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DatabaseOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            CreateTablesHelper.createTables(context, database, getZeroToHeroTables());
        } catch (Exception e) {
            LogHelper.logException(e);
            Toast.makeText(context, R.string.msg_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // TODO
    }

    private List<BaseSQLite> getZeroToHeroTables() {
        List<BaseSQLite> tables = new ArrayList<>();
        tables.add(WorkoutSQLite.newInstance(context));
        tables.add(ExerciseSQLite.newInstance(context));
        tables.add(WorkoutExerciseSQLite.newInstance(context));
        tables.add(ExerciseHistorySQLite.newInstance(context));

        return tables;
    }

}
