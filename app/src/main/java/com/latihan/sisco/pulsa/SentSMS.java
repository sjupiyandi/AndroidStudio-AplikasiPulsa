package com.latihan.sisco.pulsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.latihan.sisco.pulsa.dbHelper.TransaksiHelper;


public class SentSMS extends AppCompatActivity implements View.OnClickListener{

    private  Button btnKirim,btnTransaksi;
    private  EditText notelp;
    private  Spinner spinner;
    private String code;
    private TransaksiAdapter transaksiAdapter;
    private TransaksiHelper transaksiHelper;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_sms);
        btnKirim = (Button) findViewById(R.id.idbtnStart);
        btnTransaksi = (Button) findViewById(R.id.idbtnTransaksi);
        notelp = (EditText) findViewById(R.id.idTxtPhoneNo);
        spinner =(Spinner) findViewById(R.id.spinner);
        btnKirim.setOnClickListener(this);
        btnTransaksi.setOnClickListener(this);
        transaksiHelper = new TransaksiHelper(this);
        transaksiAdapter = new TransaksiAdapter(this);
    }

    protected void kirimSMS(){
        int index= spinner.getSelectedItemPosition();
        code = "";
        switch (index){
            case 0:
                code = "S5";
                break;
            case 1:
                code = "S10";
                break;
            case 2:
                code = "S20";
                break;
            case 3:
                code = "S25";
                break;
            case 4:
                code = "S50";
                break;
            case 5:
                code = "S100";
                break;
            case 6:
                code = "T5";
                break;
            case 7:
                code = "T10";
                break;
            case 8:
                code = "T25";
                break;
            case 9:
                code = "I5";
                break;
            case 10:
                code = "I10";
                break;
            case 11:
                code = "I25";
                break;
            case 12:
                code = "X5";
                break;
            case 13:
                code = "X10";
                break;
            case 14:
                code = "X25";
                break;
            case 15:
                code = "SD5";
                break;
            case 16:
                code = "SD10";
                break;
            case 17:
                code = "TD5";
                break;
            case 18:
                code = "TD10";
                break;
            case 19:
                code = "ID5";
                break;
            case 20:
                code = "ID10";
                break;
            case 21:
                code = "XD5";
                break;
            case 22:
                code = "XD10";
                break;
            default:
                code = "";
                break;

        }
        int permissionCheck = ContextCompat.checkSelfPermission(SentSMS.this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
               IsiPesan(code);
        } else {
               ActivityCompat.requestPermissions(SentSMS.this, new String[]{Manifest.permission.SEND_SMS},
               MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    public void IsiPesan(String code){
        String myNumber = "085381054444";
        String myMsg = code +"."+ notelp.getText().toString().trim()+".1234";
        if(myNumber==null || myNumber.equals("") || myMsg==null  || myMsg.equals("") ){
            Toast.makeText(this,"Pesan Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isDigitsOnly(myNumber)){
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(myNumber, null, myMsg, null, null);
                Toast.makeText(this,"Mengirim Pesan",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Nomor Telp Hanya Angka",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    IsiPesan(code);
                }else{
                    Toast.makeText(this,"Silahkan beri izin akses terlebih dahulu",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnKirim){
            kirimSMS();
            insertData();
        } else if (v == btnTransaksi){
            Intent intent = new Intent(SentSMS.this, Transaksi.class);
            startActivity(intent);
        }
    }

    private void insertData() {
        transaksiHelper.open();
        TransaksiModel transaksi = new TransaksiModel(code.toString(), notelp.getText().toString());
        transaksiHelper.insert(transaksi);
        transaksiHelper.close();
    }
}
