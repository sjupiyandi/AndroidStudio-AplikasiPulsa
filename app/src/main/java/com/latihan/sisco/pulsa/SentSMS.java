package com.latihan.sisco.pulsa;

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


public class SentSMS extends AppCompatActivity implements View.OnClickListener{

    private  Button btnKirim;
    private  EditText notelp;
    private  Spinner spinner;
    private String code;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_sms);
        btnKirim = (Button) findViewById(R.id.idbtnStart);
        notelp = (EditText) findViewById(R.id.idTxtPhoneNo);
        spinner =(Spinner) findViewById(R.id.spinner);
        btnKirim.setOnClickListener(this);
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
        String myNumber = notelp.getText().toString().trim();
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
        }
    }
}
