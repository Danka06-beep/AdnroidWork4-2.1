package com.example.adnroidwork4_21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CityActivity extends AppCompatActivity {

    private Spinner mCountriesSpinner;
    private Spinner mCitiesSpinner;
    private Spinner mHouseNumberSpinner;
    private Button mShowAddressBtn;

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case (android.R.id.home):
                finish();
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initViews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void initViews() {
        mCountriesSpinner = findViewById(R.id.countriesSpinner);
        mCitiesSpinner = findViewById(R.id.citiesSpinner);
        mHouseNumberSpinner = findViewById(R.id.houseNumberSpinner);
        mShowAddressBtn = findViewById(R.id.showAddress);
        initSpinnerCountries();
        initHousNumbersSpinner();
        ClicmShowAddressBtn();
    }

    private void initHousNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i = 1; i <= 50; i++) {
            houseNumbers[i - 1] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, houseNumbers);
        mHouseNumberSpinner.setAdapter(adapter);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountriesSpinner.setAdapter(adapterCountries);
        mCountriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] countries = getResources().getStringArray(R.array.countries);
                initSpinnerCities(countries[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapter = null;
        switch (country) {
            case "????????????":
                adapter = ArrayAdapter.createFromResource(this, R.array.r_cities, android.R.layout.simple_spinner_item);
                break;
            case "??????????????":
                adapter = ArrayAdapter.createFromResource(this, R.array.u_cities, android.R.layout.simple_spinner_item);
                break;
            case "????????????????????":
                adapter = ArrayAdapter.createFromResource(this, R.array.b_cities, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapter != null) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCitiesSpinner.setAdapter(adapter);
        }
    }
    private void ClicmShowAddressBtn(){
        mShowAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CityActivity.this
                        ,mCountriesSpinner.getSelectedItem().toString()
                                + " "
                                + mCitiesSpinner.getSelectedItem().toString()
                                + " "
                                + mHouseNumberSpinner.getSelectedItem().toString()
                        ,Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}