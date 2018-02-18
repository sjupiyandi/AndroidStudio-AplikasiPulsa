package com.latihan.sisco.pulsa.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.latihan.sisco.pulsa.dbHelper.DatabaseContract.TransaksiColumns.CODE;
import static com.latihan.sisco.pulsa.dbHelper.DatabaseContract.TransaksiColumns.NOHP;
import static com.latihan.sisco.pulsa.dbHelper.DatabaseContract.TABLE_TRANSAKSI;




public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_TRANSAKSI = "create table " + TABLE_TRANSAKSI +
            " (" + _ID + " integer primary key autoincrement, " +
            CODE + " text not null, " +
            NOHP + " text not null);";
    private static String DATABASE_NAME = "dbtransaksi";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRANSAKSI);

    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI);
        onCreate(db);
    }


}
