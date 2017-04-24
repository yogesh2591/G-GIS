package com.kadam.yogesh.g_gis.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 27957388 on 04/22/17.
 */

public class DatabaseHelper {

    private static class ComplaintsDB extends SQLiteOpenHelper {
        //region DATABASE STRING CONSTANT
        private static final String TEXT_TYPE = " TEXT";
        private static final String BLOB_TYPE = " BLOB";
        private static final String INT_TYPE = " INTEGER";
        private static final String UNIQUE = " UNIQUE";
        private static final String PRIMARY_KEY = " PRIMARY KEY";
        private static final String AUTOINCREMENT = " AUTOINCREMENT";
        private static final String COMMA_SEP = ",";
        private static final String NOT_NULL = " NOT NULL";
        private static final String DEFAULT = " DEFAULT";
        private static final String SPACE = " ";
        //endregion
        //region DATABASE DETAILS
        private static final String DATABASE_APLLICATION = "application.db";
        private static final int DATABASE_VERSION = 1;

        //endregion
        public ComplaintsDB(Context context) {
            super(context, DATABASE_APLLICATION, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
