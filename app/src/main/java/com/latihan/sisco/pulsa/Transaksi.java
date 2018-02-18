package com.latihan.sisco.pulsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.latihan.sisco.pulsa.dbHelper.TransaksiHelper;

import java.util.ArrayList;

public class Transaksi extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransaksiAdapter transaksiAdapter;
    private TransaksiHelper transaksiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        transaksiHelper = new TransaksiHelper(this);
        transaksiAdapter = new TransaksiAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getAllData() {
        transaksiHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<TransaksiModel> transaksiModels = transaksiHelper.getAllData();
        transaksiHelper.close();
        transaksiAdapter.addItem(transaksiModels);
        recyclerView.setAdapter(transaksiAdapter);
    }
}
