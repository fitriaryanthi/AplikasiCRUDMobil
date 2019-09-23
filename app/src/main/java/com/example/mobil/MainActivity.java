package com.example.mobil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class DBHelper extends SQLiteOpenHelper {
        public static final String TABLE_NAME = "data_mobil";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "nama_mobil";
        public static final String COLUMN_MERK = "merk_mobil";
        public static final String COLUMN_HARGA = "harga_mobil";
        public static final String db_name = "mobil.db";
        private static final int db_version=1;

        private static final String db_create = "create table "
                + TABLE_NAME + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " varchar (50) not null, "
                + COLUMN_MERK + " varchar (50) not null, "
                + COLUMN_HARGA + "varchar (50) not null);";

        public DBHelper(Context context) {
            super (context, db_name, null, db_version );
        }

        public void onCreate (SQLiteDatabase db) {
            db.execSQL(db_create);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }
}
