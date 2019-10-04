package com.example.gou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class soat_tecno_vehiculo extends AppCompatActivity {
    public ImageButton soatFech,tecnomecanicaFech;
    String seleccion;
    public ImageView  Soat, Tecno;
    public TextView fechSoat,fechMecanica;
    public Spinner Selector;
    final int cod_selecciona = 10;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int dia, mes, anio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soat_tecno_vehiculo);
        soatFech = findViewById(R.id.fech_soat);
        tecnomecanicaFech = findViewById(R.id.fech_mec);
        Soat = findViewById(R.id.soat);
        Tecno = findViewById(R.id.mecanica);
        fechSoat = findViewById(R.id.fecha_soat);
        fechMecanica = findViewById(R.id.fech_mecanica);
    }


    public void fehSoat(View v){
        if(v==soatFech){
            final Calendar c = Calendar.getInstance();
            dia =  c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int montOfYear, int dayOfMonth) {
                    fechSoat.setText(dayOfMonth+ "/"+(montOfYear+1)+"/"+year);
                }
            },dia,mes,anio);
            datePickerDialog.show();
        }
    }


    public void fehMec(View v){
        if(v==tecnomecanicaFech){
            final Calendar c = Calendar.getInstance();
            dia =  c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int montOfYear, int dayOfMonth) {
                    fechMecanica.setText(dayOfMonth+ "/"+(montOfYear+1)+"/"+year);
                }
            },dia,mes,anio);
            datePickerDialog.show();
        }
    }

    //Metodo abrir galeria
    void galeria(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"seleccione la aplicación"),cod_selecciona);
    }

    //Metodo abrir camara
    public void llamarIntentFP(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Metodo seleccion de imagen soat
    public void seleccionFotoSoat(View view){
        seleccion = "SOAT";
        AlertDialog.Builder alertaFotos = new AlertDialog.Builder(soat_tecno_vehiculo.this);
        View viewFotos = getLayoutInflater().inflate(R.layout.spinner_camera_galeria,null);
        alertaFotos.setTitle("Seleccionar imagen");
        final Spinner spinnerFotos = (Spinner) viewFotos.findViewById(R.id.spinnerFotos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( soat_tecno_vehiculo.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.galeria_camera));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFotos.setAdapter(adapter);
        alertaFotos.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!spinnerFotos.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
                    if(spinnerFotos.getSelectedItem().toString().equals("Galería")){
                        galeria();
                        dialogInterface.dismiss();
                    }else{
                        llamarIntentFP();
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertaFotos.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertaFotos.setView(viewFotos);
        AlertDialog dialogo =  alertaFotos.create();
        dialogo.show();
    }

    public void seleccionFotoTecno(View view){
        seleccion = "j";
        AlertDialog.Builder alertaFotos = new AlertDialog.Builder(soat_tecno_vehiculo.this);
        View viewFotos = getLayoutInflater().inflate(R.layout.spinner_camera_galeria,null);
        alertaFotos.setTitle("Seleccionar imagen");
        final Spinner spinnerFotos = (Spinner) viewFotos.findViewById(R.id.spinnerFotos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( soat_tecno_vehiculo.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.galeria_camera));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFotos.setAdapter(adapter);
        alertaFotos.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!spinnerFotos.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
                    if(spinnerFotos.getSelectedItem().toString().equals("Galería")){
                        galeria();
                        dialogInterface.dismiss();
                    }else{
                        llamarIntentFP();
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertaFotos.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertaFotos.setView(viewFotos);
        AlertDialog dialogo =  alertaFotos.create();
        dialogo.show();
    }

    //Metodo seleccion de Tarjeta de Propiedad

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            switch (requestCode){
                case cod_selecciona:
                    if(seleccion.equals("SOAT")){
                        Uri mpath = data.getData();
                        Soat.setImageURI(mpath);
                    }else{
                        Uri mpath = data.getData();
                        Tecno.setImageURI(mpath);

                    }
                    break;
                case REQUEST_IMAGE_CAPTURE:
                    if(seleccion.equals("SOAT")){
                        Bundle extras = data.getExtras();
                        Bitmap imagen = (Bitmap)extras.get("data");
                        Soat.setImageBitmap(imagen);
                    }else{
                        Bundle extras = data.getExtras();
                        Bitmap imagen = (Bitmap)extras.get("data");
                        Tecno.setImageBitmap(imagen);
                    }
                    break;
            }
        }
    }
}
