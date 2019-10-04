package com.example.gou;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class formulario_registro extends AppCompatActivity {
    public EditText Id;
    public EditText Username;
    public EditText Correo;
    public EditText Password;
    public EditText Pass;
    public String id, username, correo, password,pass;
    public  boolean contr=false,email1,co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro);
        Id =(EditText) findViewById(R.id.id);
        Correo =(EditText) findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        Pass = findViewById(R.id.repassword);

       /* final EditText IdT =(EditText) findViewById(R.id.id);
        final EditText UsernameT = (EditText)findViewById(R.id.username);
        final EditText CorreoT =(EditText) findViewById(R.id.email);
        final EditText PasswordT = (EditText)findViewById(R.id.password);*/

        /*Button Btn_continuar =(Button) findViewById(R.id.btncontinuar);

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
        });*/
    }

    //metodo validaciòn de datos
    void validar(){
        id = Id.getText().toString();
        username = Username.getText().toString();
        correo = Correo.getText().toString();
        password = Password.getText().toString();
        pass = Pass.getText().toString();
        if((id.isEmpty()) || (username.isEmpty()) || (correo.isEmpty()) || (password.isEmpty())||(pass.isEmpty())){
            Toast.makeText(this, "Rellene todos los campos para continuar"/*+id+" "+username+" "+correo+" "+password+" "+pass*/, Toast.LENGTH_LONG).show();
        }
        if((validarCorreo(correo)==true)&&(ValidarContraseña(password,pass)==true)){
            Intent intent = new Intent(this,datosPersonales .class);
            startActivity(intent);

        }
    }
    //metodo para obtenciòn de datos y View del botòn
    public void registro(View view){
        validar();
    }
    public boolean validarCorreo(String correo  ){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // El email a validar
        Matcher mather = pattern.matcher(correo);
        if (mather.find() == true) {
            //Toast.makeText(this, "Correo valido", Toast.LENGTH_SHORT).show();
            email1=true;
        } else {
            Toast.makeText(this, "Correo Invalido", Toast.LENGTH_SHORT).show();
            email1=false;
            Correo.setText("");
            correo="";

        }
        return email1;

    }
    public boolean ValidarContraseña(String contr,String contr2){
        boolean cla=false;
        char clave;
        byte contNumero = 0, contLetraMay = 0, contLetraMin = 0;
        int tam = 0;
        for (byte i = 0; i < password.length(); i++) {
            tam = password.length();
            clave = password.charAt(i);
            String passValue = String.valueOf(clave);
            if (passValue.matches("[A-Z]")) {
                contLetraMay++;
            } else if (passValue.matches("[a-z]")) {
                contLetraMin++;
            }
            else if (passValue.matches("[0-9]")) {
                contNumero++;
            }
            if(password.equals(pass)){
                cla = true;
            }else{
                cla = false;
            }
        }
        if ((contLetraMay >= 1) && (contLetraMin >= 1) && (contNumero >= 1) && (tam >= 8)&&(cla==true)) {
            //Toast.makeText(this, "contraseña aceptada", Toast.LENGTH_SHORT).show();
          //  Intent intent = new Intent(this,datosPersonales .class);
          //  startActivity(intent);
            co =true;
        } else if ((contLetraMay < 1) || (contLetraMin < 1) || (contNumero < 1) || (tam < 8)||(cla==false)) {
            Toast.makeText(this, "contraseña invalida", Toast.LENGTH_SHORT).show();
            co = false;
            Password.setText("");
            Pass.setText("");
        }
        return co;
    }
}

