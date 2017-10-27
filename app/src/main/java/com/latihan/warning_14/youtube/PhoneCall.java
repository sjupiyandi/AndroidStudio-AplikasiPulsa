package com.latihan.warning_14.youtube;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneCall extends AppCompatActivity {

    private EditText notelp;
    private Button buttonhub;
    private String sNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        notelp=(EditText)findViewById(R.id.editTextTelp);
        buttonhub=(Button)findViewById(R.id.buttonHubungi);

        aksi();

    }

    protected void aksi(){
        buttonhub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                sNo = notelp.getText().toString();

                if(sNo.trim().isEmpty()){
                    i.setData(Uri.parse("tel:085263234433"));
                }else{
                    i.setData(Uri.parse("tel:"+sNo));
                }
                if (ActivityCompat.checkSelfPermission(PhoneCall.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PhoneCall.this, "Silahkan beri izin akses terlebih dahulu", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }else{
                    startActivity(i);
                }
            }
        });
    }

    protected void requestPermission(){
        ActivityCompat.requestPermissions(PhoneCall.this, new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
