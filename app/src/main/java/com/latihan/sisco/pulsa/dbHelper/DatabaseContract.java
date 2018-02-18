package com.latihan.sisco.pulsa.dbHelper;

import android.provider.BaseColumns;


public class DatabaseContract {

    static String TABLE_TRANSAKSI = "table_transaksi";

    static final class TransaksiColumns implements BaseColumns {

        // TransaksiModel code
        static String CODE = "code";
        // TransaksiModel nohp
        static String NOHP = "nohp";

    }
}