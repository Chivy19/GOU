package com.example.gou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class iniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        final EditText UsuarioT = (EditText)findViewById(R.id.usuario);
        Button Inicio = (Button)findViewById(R.id.inicio);

        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= UsuarioT.getText().toString();
                Intent Bienvenido = new Intent(iniciarSesion.this,Bienvenido.class);
                Bienvenido.putExtra("nombreuser",user);
                startActivity(Bienvenido);
                Response.Listener<String>respuesta =new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonrespuesta = new JSONObject(response);
                            boolean ok = jsonrespuesta.getBoolean("success");
                            if(ok==true){
                                String nombreuser =jsonrespuesta.getString("nombreuser");
                                Intent Bienvenido = new Intent(iniciarSesion.this,Bienvenido.class);
                                Bienvenido.putExtra("nombreuser",nombreuser);

                                iniciarSesion.this.startActivity(Bienvenido);
                                iniciarSesion.this.finish();
                            }else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder(iniciarSesion.this );
                                alerta.setMessage("Fallo en el Login")
                                        .setNegativeButton("Reintentar",null)
                                        .create()
                                        .show();
                            }

                        }catch(JSONException e){
                            e.getMessage();
                        }
                    }
                };
                loginRequest r = new loginRequest(user,respuesta);
                RequestQueue cola = Volley.newRequestQueue(iniciarSesion.this);
                cola.add(r);
            }
        });
    }
}
