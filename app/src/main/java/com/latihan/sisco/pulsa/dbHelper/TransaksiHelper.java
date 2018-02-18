package com.latihan.sisco.pulsa.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.latihan.sisco.pulsa.TransaksiModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.latihan.sisco.pulsa.dbHelper.DatabaseContract.TransaksiColumns.CODE;
import static com.latihan.sisco.pulsa.dbHelper.DatabaseContract.TransaksiColumns.NOHP;
import static com.latihan.sisco.pulsa.dbHelper.DatabaseContract.TABLE_TRANSAKSI;


public class TransaksiHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public TransaksiHelper(Context context) {
        this.context = context;
    }

    public TransaksiHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<TransaksiModel> getAllData() {
        Cursor cursor = database.query(TABLE_TRANSAKSI, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<TransaksiModel> arrayList = new ArrayList<>();
        TransaksiModel transaksiModel;
        if (cursor.getCount() > 0) {
            do {
                transaksiModel = new TransaksiModel();
                transaksiModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                transaksiModel.setCode(cursor.getString(cursor.getColumnIndexOrThrow(CODE)));
                transaksiModel.setNohp(cursor.getString(cursor.getColumnIndexOrThrow(NOHP)));
                arrayList.add(transaksiModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(TransaksiModel transaksiModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CODE, transaksiModel.getCode());
        initialValues.put(NOHP, transaksiModel.getNohp());
        return database.insert(TABLE_TRANSAKSI, null, initialValues);
    }


    public int update(TransaksiModel transaksiModel) {
        ContentValues args = new ContentValues();
        args.put(CODE, transaksiModel.getCode());
        args.put(NOHP, transaksiModel.getNohp());
        return database.update(TABLE_TRANSAKSI, args, _ID + "= '" + transaksiModel.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_TRANSAKSI, _ID + " = '" + id + "'", null);
    }

}
