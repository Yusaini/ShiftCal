package de.nulide.shiftcal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnExportC;
    Button btnImportC;
    Button btnLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnExportC = findViewById(R.id.btnExportC);
        btnExportC.setOnClickListener(this);
        btnImportC = findViewById(R.id.btnImportC);
        btnImportC.setOnClickListener(this);
        btnLicense = findViewById(R.id.btnLicense);
        btnLicense.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnExportC){

        }else if(view == btnImportC){

        }else if(view == btnLicense){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("FloatingActionButton - https://github.com/futuresimple/android-floating-action-button"));
            startActivity(browserIntent);
        }else{

        }

    }
}
