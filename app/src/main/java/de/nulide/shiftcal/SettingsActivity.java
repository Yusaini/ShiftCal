package de.nulide.shiftcal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView settingsList;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        settingsList = findViewById(R.id.settingslist);
        settings = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                settings);
        settingsList.setAdapter(listAdapter);
        settingsList.setOnItemClickListener(this);
        settings.add("Export Calendar");
        settings.add("Import Calendar");
        settings.add("License");
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Nulide/ShiftCal/blob/master/ThirdPartyProjects.md"));
        startActivity(browserIntent);
    }

}
