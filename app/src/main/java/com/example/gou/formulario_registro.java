package com.example.gou;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


public class formulario_registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro);
        final EditText IdT =(EditText) findViewById(R.id.id);
        final EditText UsernameT = (EditText)findViewById(R.id.username);
        final EditText CorreoT =(EditText) findViewById(R.id.email);
        final EditText PasswordT = (EditText)findViewById(R.id.password);


        Button Btn_continuar =(Button) findViewById(R.id.btncontinuar);

        Btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Id = Integer.parseInt(IdT.getText().toString());
                String nombreuser= UsernameT.getText().toString();
                String correos = CorreoT.getText().toString();
                String Contraseña = PasswordT.getText().toString();

                Response.Listener<String>respuesta = new Response.Listener<String>() {
                    @Override

                    //El programa ejecuta para ver si la app tiene internet
                    public void onResponse(String response) {
                        Toast.makeText(formulario_registro.this, "Registrado", Toast.LENGTH_SHORT).show();
                    }
                };

                Toast.makeText(formulario_registro.this, ""+Id+" "+nombreuser+" "+correos+" "+Contraseña, Toast.LENGTH_SHORT).show();
                formulario_registrorequest r = new formulario_registrorequest(Id,nombreuser,correos,Contraseña,respuesta);
                RequestQueue cola = Volley.newRequestQueue(formulario_registro.this);
                cola.add(r);
            }
        });
    }
}
