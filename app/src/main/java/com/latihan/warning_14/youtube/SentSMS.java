package com.latihan.warning_14.youtube;

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
import android.widget.Toast;


public class SentSMS extends AppCompatActivity {

    private  Button btnKirim;
    private  EditText isipesan, notelp;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_sms);
        btnKirim = (Button) findViewById(R.id.idbtnStart);
        isipesan = (EditText) findViewById(R.id.idTxtMsg);
        notelp = (EditText) findViewById(R.id.idTxtPhoneNo);
        kirimSMS();
    }

    protected void kirimSMS(){
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(SentSMS.this, Manifest.permission.SEND_SMS);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {

                    IsiPesan();
                } else {
                    ActivityCompat.requestPermissions(SentSMS.this, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
                }
            }
        });
    }

    public void IsiPesan(){
        String myNumber = notelp.getText().toString().trim();
        String myMsg = isipesan.getText().toString().trim()+".1234";
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

                    IsiPesan();
                }else{
                    Toast.makeText(this,"Silahkan beri izin akses terlebih dahulu",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
