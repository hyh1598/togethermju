package com.example.mathpresso.togethermju.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mathpresso.togethermju.R;
import com.example.mathpresso.togethermju.model.Group;

import static com.example.mathpresso.togethermju.R.id.back_button;
import static com.example.mathpresso.togethermju.R.id.btn_cancel;

public class GroupResiterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_register);
        getIntent();

        Spinner personnelSpinner = (Spinner) findViewById(R.id.personnel_spinner);
        EditText title = (EditText) findViewById(R.id.input_title);
        EditText purpose = (EditText) findViewById(R.id.input_purpose);
        EditText content = (EditText) findViewById(R.id.input_content);
        Button finish = (Button) findViewById(R.id.btn_finish);
        Button cancel = (Button) findViewById(btn_cancel);
        ArrayAdapter personnelAdapter = ArrayAdapter.createFromResource(this, R.array.personnel,
                android.R.layout.simple_spinner_item);
        personnelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personnelSpinner.setAdapter(personnelAdapter);
        int personnel = Integer.parseInt(personnelSpinner.getSelectedItem().toString());
        String gt = title.getText().toString();
        String gp = purpose.getText().toString();
        String gc = content.getText().toString();

        Group group = new Group(null,null,null,gt,gp,gc,personnel);

        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });
        finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

            }
        });
    }
}
