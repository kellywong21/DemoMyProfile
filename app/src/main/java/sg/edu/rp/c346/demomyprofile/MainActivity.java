package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextGPA;
    RadioGroup radioGroupGender;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextGPA = findViewById(R.id.editTextGPA);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Step 1a: Get the user input from the editText and store it in a variable
                String strName = editTextName.getText().toString();
                Float gpa = Float.parseFloat(editTextGPA.getText().toString());
                int selectedID = radioGroupGender.getCheckedRadioButtonId();


                //Step 1b: Obtain an instance of the SharedPreference
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                //Step 1c: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();
                //Step 1d: Add the key-value pair
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa",gpa);
                prefEdit.putInt("gender", selectedID);


                //Step 1e: Call commit() to save the changes into SharedPreference
                prefEdit.commit();


            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Get the user input from the editText and store it in a variable
        String strName = editTextName.getText().toString();
        Float gpa = Float.parseFloat(editTextGPA.getText().toString());
        int selectedID = radioGroupGender.getCheckedRadioButtonId();


        //Step 1b: Obtain an instance of the SharedPreference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1d: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa",gpa);
        prefEdit.putInt("gender", selectedID);


        //Step 1e: Call commit() to save the changes into SharedPreference
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data from the SharedPreferences object
        String name = prefs.getString("name", "");
        Float gpa = prefs.getFloat("gpa",2.0f);
        int gender = prefs.getInt("gender", 0);


        //Step 2c: Update the UI element with the value
        editTextName.setText(name);
        editTextGPA.setText(""+gpa);
        radioGroupGender.check(gender);




    }
}
