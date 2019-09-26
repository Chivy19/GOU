package com.example.gou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        final TextView mensaje = (TextView)findViewById(R.id.mensaje);
        Intent i  = this.getIntent();
        String nombreuser = i.getStringExtra("nombreuser");
        mensaje.setText(mensaje.getText()+" "+nombreuser);
    }
}
