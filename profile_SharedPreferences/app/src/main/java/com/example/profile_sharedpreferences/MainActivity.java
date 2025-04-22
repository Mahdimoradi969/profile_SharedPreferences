package com.example.profile_sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // تعریف نام SharedPreferences و کلید نام کاربر
    private static final String PREFS_NAME = "UserProfilePrefs";
    private static final String KEY_USERNAME = "username";

    // تعریف ویجت‌ها
    private EditText editTextName;
    private Button buttonSave;
    private TextView textViewGreeting;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // مقداردهی اولیه کامپوننت‌ها
        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);
        textViewGreeting = findViewById(R.id.textViewGreeting);
        
        // دریافت SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        
        // نمایش نام ذخیره شده قبلی (اگر وجود داشته باشد)
        loadSavedUsername();
        
        // تنظیم رویداد کلیک دکمه ذخیره
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUsername();
            }
        });
    }
    
    // متد برای ذخیره نام کاربر در SharedPreferences
    private void saveUsername() {
        String username = editTextName.getText().toString().trim();
        
        if (username.isEmpty()) {
            Toast.makeText(this, "لطفا نام خود را وارد کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // ذخیره نام کاربر در SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
        
        // نمایش پیام موفقیت
        Toast.makeText(this, "نام با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show();
        
        // بروزرسانی متن سلام
        displayGreeting(username);
    }
    
    // متد برای بارگذاری نام کاربر از SharedPreferences
    private void loadSavedUsername() {
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        
        if (!savedUsername.isEmpty()) {
            // تنظیم نام در فیلد ورودی
            editTextName.setText(savedUsername);
            
            // نمایش متن سلام
            displayGreeting(savedUsername);
        }
    }
    
    // متد برای نمایش پیام خوشامدگویی
    private void displayGreeting(String username) {
        String greeting = "سلام " + username + " عزیز، خوش آمدید!";
        textViewGreeting.setText(greeting);
    }
}