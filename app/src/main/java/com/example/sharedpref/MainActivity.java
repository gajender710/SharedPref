package com.example.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mail, pass, username;
    private Button b;

    private TextInputEditText textInputEditText1, textInputEditText2, textInputEditText3;
    private Switch switch1;

    Shared_Prefss sp;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT1 = "text1";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";
    public static final String SWITCH1 = "switch1";

    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    final String MAIL_PATTERN = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new Shared_Prefss(this);
        mail = findViewById(R.id.et_Email);
        pass = findViewById((R.id.et_Pass));
        username = findViewById(R.id.et_Username);

        textInputEditText1 =  findViewById(R.id.Edittext_mail);
        textInputEditText2 =  findViewById(R.id.Edittext_pass);
        textInputEditText3 =  findViewById(R.id.Edittext_username);
        switch1 = (Switch) findViewById(R.id.switch1);

        b = findViewById(R.id.Sign_in);
        b.setOnClickListener(this::Submit_data);

        Boolean b = sp.loadBooleanData(SWITCH1);
        if (b) {
            textInputEditText1.setText(sp.loadStringData(TEXT1));
            textInputEditText2.setText(sp.loadStringData(TEXT2));
            textInputEditText3.setText(sp.loadStringData(TEXT3));
        }
    }

    boolean is_mailValid() {
        String s = mail.getEditText().getText().toString().trim();
        if (s.isEmpty()) {
            mail.setError("enter mail");
            return false;
        }
        Pattern pattern = Pattern.compile(MAIL_PATTERN);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            mail.setError("Invalid mail format");
            return false;
        }
        mail.setErrorEnabled(false);
        return true;
    }

    boolean is_passlValid() {
        String s = pass.getEditText().getText().toString().trim();
        if (s.isEmpty()) {
            pass.setError("Enter password");
            return false;
        }
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            pass.setError("Accurate Password format");
            return false;
        }
        pass.setErrorEnabled(false);
        return true;
    }

    boolean is_userValid() {
        String s = username.getEditText().getText().toString();
        if (s.isEmpty()) {
            username.setError("Enter Username");
            return false;
        }
        username.setErrorEnabled(false);
        return true;

    }

    private void Submit_data(View view) {
        if (!is_mailValid() | !is_passlValid() | !is_userValid()) {
            return;
        }

        sp.saveData(TEXT1, mail.getEditText().getText().toString());
        sp.saveData(TEXT2, pass.getEditText().getText().toString());
        sp.saveData(TEXT3, username.getEditText().getText().toString());
        sp.saveBoolean(SWITCH1, switch1.isChecked());

        String str = mail.getEditText().getText().toString().trim() + "/n" + pass.getEditText().getText().toString().trim();
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();



    }
}