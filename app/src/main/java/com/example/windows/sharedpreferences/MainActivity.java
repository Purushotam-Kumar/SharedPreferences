package com.example.windows.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private RadioGroup radioGroup;
    private CheckBox happy,sad,angry,lazy;
    private Switch switchOnOff;
    private Spinner spinner;
    private Button saveButton,moveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewIds();
        loadData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewClass.class));
            }
        });

    }


    public void getViewIds(){
        text = (EditText) findViewById(R.id.text);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        happy = (CheckBox)findViewById(R.id.happy);
        sad = (CheckBox)findViewById(R.id.sad);
        angry = (CheckBox)findViewById(R.id.angry);
        lazy = (CheckBox)findViewById(R.id.lazy);
        switchOnOff = (Switch)findViewById(R.id.switchOnOff);
        spinner = (Spinner)findViewById(R.id.spinner);
        saveButton = (Button) findViewById(R.id.saveButton);
        moveButton = (Button) findViewById(R.id.moveButton);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("text", text.getText().toString());

        editor.putInt("radio",radioGroup.getCheckedRadioButtonId());

        editor.putBoolean("happy", happy.isChecked());
        editor.putBoolean("sad", sad.isChecked());
        editor.putBoolean("angry", angry.isChecked());
        editor.putBoolean("lazy", lazy.isChecked());

        editor.putBoolean("switch", switchOnOff.isChecked());

        editor.putInt("spinner", spinner.getSelectedItemPosition());

        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        text.setText(sharedPreferences.getString("text", ""));

        radioGroup.check(sharedPreferences.getInt("radio",0));

        happy.setChecked(sharedPreferences.getBoolean("happy",false));
        sad.setChecked(sharedPreferences.getBoolean("sad",false));
        angry.setChecked(sharedPreferences.getBoolean("angry",false));
        lazy.setChecked(sharedPreferences.getBoolean("lazy",false));

        switchOnOff.setChecked(sharedPreferences.getBoolean("switch", false));

        spinner.setSelection(sharedPreferences.getInt("spinner",0));

    }
}