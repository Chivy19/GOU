package com.example.gou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class fotosDatosPersonales extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public ImageView fot_lic,fot_di,fot_user;
    public Button seleccionar;
    final int cod_selecciona = 10;
    String seleccion = "",path = "";
    private final String carpeta_raiz = "misImagenes/";
    private final String ruta_imagen = carpeta_raiz+"misFotos  ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_datos_personales);
        fot_lic = findViewById(R.id.lc_foto);
        fot_di = findViewById(R.id.di_foto);
        fot_user = findViewById(R.id.perfil_foto);
        seleccionar =  findViewById(R.id.seleccion);
        StrictMode.VmPolicy.Builder builderm = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builderm.build());
    }


     void galeria(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"seleccione la aplicación"),cod_selecciona);
    }


    public void seleccion(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(fotosDatosPersonales.this);
        View view1 = getLayoutInflater().inflate(R.layout.spiner_fotos,null);
        alerta.setTitle("Seleccionar imagen");
        final Spinner spinner = (Spinner) view1.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( fotosDatosPersonales.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.imagenes));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        alerta.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!spinner.getSelectedItem().toString().equalsIgnoreCase("Seleccionar")){
                    seleccion = spinner.getSelectedItem().toString();
                    Toast.makeText(fotosDatosPersonales.this, seleccion, Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertaFotos = new AlertDialog.Builder(fotosDatosPersonales.this);
                    View viewFotos = getLayoutInflater().inflate(R.layout.spinner_camera_galeria,null);
                    alertaFotos.setTitle("Seleccionar imagen");
                    final Spinner spinnerFotos = (Spinner) viewFotos.findViewById(R.id.spinnerFotos);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>( fotosDatosPersonales.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.galeria_camera));
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
                                            llamarIntent();
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
            }
        });

        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alerta.setView(view1);
        AlertDialog dialogo =  alerta.create();
        dialogo.show();
    }

    public void llamarIntent(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            switch (requestCode){
                case cod_selecciona:
                    if(seleccion.equals("Foto de perfil")){
                        Uri mpath = data.getData();
                        fot_user.setImageURI(mpath);
                    }else if(seleccion.equals("Licencia de conducción")){
                        Uri mpath = data.getData();
                        fot_lic.setImageURI(mpath);
                    }else{
                        Uri mpath = data.getData();
                        fot_di.setImageURI(mpath);
                    }
                    break;
                case REQUEST_IMAGE_CAPTURE:
                    if(seleccion.equals("Foto de perfil")){
                        Bundle extras = data.getExtras();
                        Bitmap imagen = (Bitmap)extras.get("data");
                        fot_user.setImageBitmap(imagen);
                    }else if(seleccion.equals("Licencia de conducción")){
                        Bundle extras = data.getExtras();
                        Bitmap imagen = (Bitmap)extras.get("data");
                        fot_lic.setImageBitmap(imagen);
                    }else{
                        Bundle extras = data.getExtras();
                        Bitmap imagen = (Bitmap)extras.get("data");
                        fot_di.setImageBitmap(imagen);
                    }
                    break;
            }
        }
    }


    public void siguiente(View view){
        Intent intent = new Intent(this,DatosVehiculo.class);
        startActivity(intent);
    }



}
