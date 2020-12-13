package com.example.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button login, reg;
    EditText user, pass, email;
    DB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClickLisener();
    }
    public boolean cont()
    {
        String felh = user.getText().toString();
        String jelszo = pass.getText().toString();
        String e_mail = email.getText().toString();
        boolean existuser = true;
        if (!database.check(felh,jelszo,e_mail)) {
            existuser = false;
            Toast.makeText(this, "Nem létezik ilyen nevű felhasználó!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Már létezik ilyen nevű felhasználó!", Toast.LENGTH_SHORT).show();
        }
        return existuser;

    }

    private void onClickLisener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont())
                {
                    Intent login = new Intent(getApplicationContext(), LoggedInActivity.class);
                    startActivity(login);
                    finish();
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regist = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regist);
                finish();
            }
        });

    }

    private void init() {
        login = findViewById(R.id.login);
        reg = findViewById(R.id.regist);
        email = findViewById(R.id.e_mail);
        pass = findViewById(R.id.jelszo);
        database = new DB(MainActivity.this);
    }
}
