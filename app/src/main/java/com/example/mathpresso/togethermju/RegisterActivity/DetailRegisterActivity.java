package com.example.mathpresso.togethermju.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.mathpresso.togethermju.R;

public class DetailRegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_register);

        getIntent();

        Spinner majorSpinner = (Spinner) findViewById(R.id.major_spinner);
        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        Spinner monthSpinner = (Spinner) findViewById(R.id.month_spinner);
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);

        ArrayAdapter majorAdapter = ArrayAdapter.createFromResource(this, R.array.major,
                android.R.layout.simple_spinner_item);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(majorAdapter);

        // majorSpinner.getSelectedItem().toString() 이용
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this, R.array.year,
                android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.month,
                android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.day,
                android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);
    }

    public void clickBackButton(View view) {
        startActivity(new Intent(this, EmailRegisterActivity.class));
    }

    public void clickNextButton(View view) {
        Spinner majorSpinner = (Spinner) findViewById(R.id.major_spinner);
        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        Spinner monthSpinner = (Spinner) findViewById(R.id.month_spinner);
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);

        RadioButton maleButton = (RadioButton) findViewById(R.id.male_radio_button);
        RadioButton femaleButton = (RadioButton) findViewById(R.id.female_radio_button);

        String gender;
        String major = majorSpinner.getSelectedItem().toString();
        int year = Integer.parseInt(yearSpinner.getSelectedItem().toString());
        int month = Integer.parseInt(monthSpinner.getSelectedItem().toString());
        int day = Integer.parseInt(daySpinner.getSelectedItem().toString());

        if ((maleButton.isChecked() == true)) {
            gender = "남자";
        } else {
            gender = "여자";
        }

        startActivity(new Intent(this, FavoriteRegisterActivity.class));
    }

}
