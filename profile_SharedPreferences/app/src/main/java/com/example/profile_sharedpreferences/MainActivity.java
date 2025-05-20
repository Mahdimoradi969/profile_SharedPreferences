package com.example.profile_sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonSave;
    TextView textViewGreeting;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);
        textViewGreeting = findViewById(R.id.textViewGreeting);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String name = sharedPreferences.getString("name", "");
        if (!name.equals("")) {
            textViewGreeting.setText("سلام " + name + " عزیز");
            editTextName.setText(name);
        }

        buttonSave.setOnClickListener(v -> {
            String username = editTextName.getText().toString();

            if (username.isEmpty()) {
                Toast.makeText(MainActivity.this, "لطفا نام را وارد کنید", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", username);
                editor.apply();

                Toast.makeText(MainActivity.this, "نام ذخیره شد", Toast.LENGTH_SHORT).show();
                textViewGreeting.setText("سلام " + username + " عزیز");
            }
        });
    }
}
