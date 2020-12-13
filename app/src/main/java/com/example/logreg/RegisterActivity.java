package com.example.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText email, user, pass, name;
    Button bttnreg, bttnback;

    public static DB adatbazis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        bttnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean fill = true;
                if (email.getText().toString().trim().length() == 0) {
                    email.setError("Adja meg az e-mail címét!");
                    toast("e-mail");
                    fill = false;
                    return;
                } else if (user.getText().toString().trim().length() == 0) {
                    user.setError("Adja meg a felhasználónevét!");
                    toast("felhasználónév");
                    fill = false;
                    return;
                } else if (pass.getText().toString().trim().length() == 0) {
                    pass.setError("Adjon meg egy jelszót!");
                    toast("jelszó");
                    fill = false;
                    return;
                } else if (name.getText().toString().trim().length() == 0) {
                    name.setError("Adja meg a nevét!");
                    toast("név");
                    fill = false;
                    return;
                }

                email.getText().clear();
                user.getText().clear();
                pass.getText().clear();
                name.getText().clear();
                if (adatbazis.reg( email.getText().toString(),
                        user.getText().toString(),
                        pass.getText().toString(),
                        name.getText().toString())) {
                    toastSikertelen();
                } else {
                    toastSikeres();
                }
            }
        });
        bttnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backActivity = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(backActivity);
                finish();
            }
        });
    }

    private void init() {
        bttnback=findViewById(R.id.back);
        bttnreg=findViewById(R.id.reg);
        user = findViewById(R.id.felh);
        pass = findViewById(R.id.jelszo);
        email = findViewById(R.id.e_mail);
        name = findViewById(R.id.name);
        adatbazis = new DB(RegisterActivity.this);

    }
    public void toast(String hiba) {
        Toast.makeText(this, "Kötelező a(z) " + hiba + " megadása!", Toast.LENGTH_SHORT).show();
    }

    public void toastSikeres() {
        Toast.makeText(this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
    }

    public void toastSikertelen() {
        Toast.makeText(this, "Sikertelen regisztráció", Toast.LENGTH_SHORT).show();
    }
}
