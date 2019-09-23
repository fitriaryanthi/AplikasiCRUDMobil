package com.example.mobil;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDataSource {
    private SQLiteDatabase database;

    private DBHelper dBhelper;

    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_MERK, MainActivity.DBHelper.COLUMN_HARGA};

    public DBDataSource (Context context)
    {
        dBhelper = new DBHelper (context);
    }

    public void open() throws SQLException {
        database = dBhelper.getWritableDatabase ();
    }

    public void close() {
        dBhelper.close();
    }

    public Mobil createMobil (String nama, String merk, String harga) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_MERK, merk);
        values.put(DBHelper.COLUMN_HARGA, harga);

        long insertId = database.insert(DBHelper.TABLE_NAME, null,values);

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, DBHelper.COLUMN_ID +" = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Mobil newMobil = cursorToMobil(cursor);

        cursor.close();

        return newMobil;
    }
    private Mobil cursorToMobil (Cursor cursor)
    {
        Mobil mobil = new Mobil();

        Log.v("info", "The getLong "+cursor.getLong(0));
        Log.v("info", "The setLatLng "+cursor.getString(1) +","+cursor.getString(2));
        mobil.setId(cursor.getLong(0));
        mobil.setNama_mobil(cursor.getString(1));
        mobil.setMerk_mobil(cursor.getString(2));
        mobil.setHarga_mobil(cursor.getString(3));

        return mobil;

    }

    public ArrayList<Mobil> getAllMobil() {
        ArrayList<Mobil> daftarMobil = new ArrayList<Mobil>();

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Mobil mobil = cursorToMobil(cursor);
            daftarMobil.add(mobil);
            cursor.moveToNext();
        }

        cursor.close();
        return daftarMobil;
    }

    public Mobil getMobil(long id)
    {
        Mobil mobil = new Mobil();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id ="+id, null, null, null, null);
        cursor.moveToFirst();
        mobil = cursorToMobil(cursor);

        cursor.close();

        return mobil;
    }

    public void updateMobil(Mobil b)
    {

        String strFilter = "_id=" + b.getId();
        ContentValues args = new ContentValues();
        args.put(DBHelper.COLUMN_NAME, b.getNama_mobil());
        args.put(DBHelper.COLUMN_MERK, b.getMerk_mobil());
        args.put(DBHelper.COLUMN_HARGA, b.getHarga_mobil() );

        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public void deleteMobil(long id)
    {
        String strFilter = "_id=" + id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}
