package com.example.mathpresso.togethermju.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.mathpresso.togethermju.MainActivity;
import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.core.AppController;

import java.util.ArrayList;

import static com.example.mathpresso.togethermju.core.AppController.user;

public class UserEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Spinner majorSpinner = (Spinner) findViewById(R.id.major_spinner);
        Spinner yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        Spinner monthSpinner = (Spinner) findViewById(R.id.month_spinner);
        Spinner daySpinner = (Spinner) findViewById(R.id.day_spinner);

        ArrayList<String> majorArrayList = new ArrayList<String>();
        ArrayList<String> yearArrayList = new ArrayList<String>();
        ArrayList<String> MonthArrayList = new ArrayList<String>();
        ArrayList<String> DayArrayList = new ArrayList<String>();

        RadioButton maleButton = (RadioButton) findViewById(R.id.male_radio_button);
        RadioButton femaleButton = (RadioButton) findViewById(R.id.female_radio_button);

        EditText editText = (EditText) findViewById(R.id.user_name);
        editText.setText(user.getName());

        /*
        if (user.getGender().equals("남자")) {
            maleButton.setChecked(true);
        } else if (user.getGender().equals("여자")) {
            femaleButton.setChecked(true);
        }
        */

        ArrayAdapter majorAdapter = ArrayAdapter.createFromResource(this, R.array.major,
                android.R.layout.simple_spinner_item);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(majorAdapter);

        /*
        for (int i = 0; i < majorSpinner.getCount(); i++) {
            majorArrayList.add(String.valueOf(majorSpinner.getItemAtPosition(i)));
        }

        for (int i = 0; i < arrayList.size(); i++) {
            if (user.getMajor().equals(majorArrayList.get(i))) {
                majorSpinner.setSelection(i);
            }
        }
        */

        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this, R.array.year,
                android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        /*
        for (int i = 0; i < majorSpinner.getCount(); i++) {
            yearArrayList.add(String.valueOf(majorSpinner.getItemAtPosition(i)));
        }

        for (int i = 0; i < yearArrayList.size(); i++) {
            if (user.getMajor().equals(majorArrayList.get(i))) {
                majorSpinner.setSelection(i);
            }
        }
        */

        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.month,
                android.R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        /*
        for (int i = 0; i < majorSpinner.getCount(); i++) {
            monthArrayList.add(String.valueOf(majorSpinner.getItemAtPosition(i)));
        }

        for (int i = 0; i < monthArrayList.size(); i++) {
            if (user.getMajor().equals(majorArrayList.get(i))) {
                majorSpinner.setSelection(i);
            }
        }
        */

        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.day,
                android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        /*
        for (int i = 0; i < majorSpinner.getCount(); i++) {
            dayArrayList.add(String.valueOf(majorSpinner.getItemAtPosition(i)));
        }

        for (int i = 0; i < monthArrayList.size(); i++) {
            if (user.getMajor().equals(dayArrayList.get(i))) {
                majorSpinner.setSelection(i);
            }
        }
        */
    }

    public void clickCancelButton(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
    public void clickEditButton(View view) {

    }
}
