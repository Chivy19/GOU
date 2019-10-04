package com.example.gou;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class datosPersonales extends AppCompatActivity {
    public TextView fec_nac, fec_ven_pase;
    public EditText nombre, apellido, numDocumento, numTelefono;
    public ImageButton selector_fecha_nac,getSelector_fecha_lc;
    public Spinner spinner;
    private int dia, mes, anio;
    public String nom,apell,numDoc,tel,fechaLic,fechaNac,tipoDoc;
    public  boolean sig = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
        //obtención de datoa
        fec_nac = findViewById(R.id.Fecha_nac);
        fec_ven_pase = findViewById(R.id.FechaVencimientoLC);
        selector_fecha_nac = findViewById(R.id.btn_fecha);
        getSelector_fecha_lc = findViewById(R.id.btn_vencimientoLC);
        nombre = findViewById(R.id.Nombre);
        apellido = findViewById(R.id.Apellido);
        numDocumento = findViewById(R.id.NumDocumento);
        numTelefono = findViewById(R.id.NumTelefono);
        //asignación campos de Spinner
        spinner = findViewById(R.id.selector);
        String[]selector = {"Tipo de documento","Tarjeta de Identidad","Cédula de ciudadania"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,selector);
        spinner.setAdapter(adapter);
    }

    public void onClickNacimiento(View v){
        if(v==selector_fecha_nac){
            final Calendar c = Calendar.getInstance();
            dia =  c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int montOfYear, int dayOfMonth) {
                    fec_nac.setText(dayOfMonth+ "/"+(montOfYear+1)+"/"+year);
                }
            },anio,mes,dia);
            datePickerDialog.show();
        }
    }

    public void retroceso(View view){
        Intent infoCuenta = new Intent(this,formulario_registro.class);
        startActivity(infoCuenta);
    }

    public void onClickLC(View view){
        if(view==getSelector_fecha_lc){
            final Calendar c = Calendar.getInstance();
            dia =  c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int montOfYear, int dayOfMonth) {
                    fec_ven_pase.setText(dayOfMonth+ "/"+(montOfYear+1)+"/"+year);
                }
            },anio,mes,dia);

            datePickerDialog.show();
        }
    }

    public void fotos(View view){
        if(validar()==true){
            Intent intent = new Intent(this, fotosDatosPersonales.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Verifique todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validar(){
        nom = nombre.getText().toString().trim();
        apell = apellido.getText().toString().trim();
        numDoc = numDocumento.getText().toString().trim();
        tel = numTelefono.getText().toString().trim();
        fechaNac = (String) fec_nac .getText();
        fechaLic = (String) fec_ven_pase.getText();
        tipoDoc=spinner.getSelectedItem().toString();
        if(nom.equals("")||apell.equals("")||numDoc.equals("")||tel.equals("")||fechaNac.equals(" ")||fechaLic.equals(" ")||tipoDoc.equals("")||tipoDoc.equals("Tipo de documento")||nom.length()<3||apell.length()<3||tel.length()<7||tel.length()>10||numDoc.length()<8||numDoc.length()>=11){
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show();
            sig = false;
        }else {
            sig = true;
        }
        return sig;
    }
}
