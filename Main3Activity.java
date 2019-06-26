package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    TextView textView;
    Databasehelper db;
    Button button;
    EditText text1, text2;
    String name, email, email1, txt4;
    int d;
    public String text4;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = findViewById(R.id.textView);
        db = new Databasehelper(this);
        final int cr[] = new int[2];
        text = getIntent().getStringExtra("listviewclick");
        text1 = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        Cursor c = db.listcontents();
        if (c.getCount() >= 0) {
            while (c.moveToNext()) {
                if (text.contentEquals(c.getString(0))) {
                    email = c.getString(1);

                    cr[0] = c.getInt(2);
                    text4 = text + "\n" + email + "\n" + cr[0];
                    textView.setText(text4);
                }
            }

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = text1.getText().toString();
                d = Integer.valueOf(text2.getText().toString());
                if (d > cr[0]) {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "no suffucient balance", Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    db.updatetable(text, email, cr[0] - d);
                    int g=cr[0]-d;
                    text4=text+"\n"+email+"\n"+g;
                    textView.setText(text4);
                    Cursor c = db.listcontents();
                    if (c.getCount() > 0) {
                        while (c.moveToNext()) {
                            if (name.contentEquals(c.getString(0))) {
                                email1 = c.getString(1);
                                int dp = c.getInt(2);
                                cr[1] = dp;
                                db.updatetable(name, email1, cr[1] + d);
                                Context context = getApplicationContext();
                                Toast toast = Toast.makeText(context, "transfered", Toast.LENGTH_SHORT);
                                toast.show();

                            }
                        }
                    }

                }

            }
        });
    }
}