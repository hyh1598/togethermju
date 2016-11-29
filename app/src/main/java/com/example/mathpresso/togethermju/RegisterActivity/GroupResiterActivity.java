package com.example.mathpresso.togethermju.RegisterActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mathpresso.togethermju.R;

public class GroupResiterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_register);
        getIntent();

        Spinner personnelSpinner = (Spinner) findViewById(R.id.personnel_spinner);
        EditText title = (EditText) findViewById(R.id.input_title);
        EditText pupose = (EditText) findViewById(R.id.input_purpose);
        EditText content = (EditText) findViewById(R.id.input_content);
        Button finish = (Button) findViewById(R.id.btn_finish);
        Button cancel = (Button) findViewById(R.id.btn_cancel);
    }
}
