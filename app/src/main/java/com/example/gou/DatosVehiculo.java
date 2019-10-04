package com.example.gou;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class DatosVehiculo extends AppCompatActivity {
    final int cod_selecciona = 10;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public Spinner Selector;
    public EditText PlacaCarro, Modelo, Marca, Color, Cupos;
    ImageView TarjetaPropiedad, FotoVehiculo;
    String tVehiculo,placaV,modelo,marca,col,numCupos;
    boolean val=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_vehiculo);
        //Cargar valores
        Selector = findViewById(R.id.selector);
        PlacaCarro = findViewById(R.id.placaCarro);
        Modelo = findViewById(R.id.modelo);
        Marca = findViewById(R.id.marca);
        Color = findViewById(R.id.color);
        Cupos = findViewById(R.id.cupos);
        String tVehiculo,placaV,modelo,marca,color,numCupos;
        FotoVehiculo = findViewById(R.id.fotoVehiculo);
        TarjetaPropiedad = findViewById(R.id.tarjetaPropiedad);
        //Crear vector para combo Box
        String[]selector = {"Tipo de vehiculo","Carro","Moto"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,selector);
        Selector.setAdapter(adapter);
    }
    //Metodo abrir galeria
    void galeria(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"seleccione la aplicación"),cod_selecciona);
    }

    //Metodo cámara TP
    public void llamarIntentTP(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }
    //Metodo cámara FV
    public void llamarIntentFP(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Metodo seleccion de imagen vehiculo
    public void seleccionFotoVehiculo(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(DatosVehiculo.this);
        View view1 = getLayoutInflater().inflate(R.layout.spiner_fotos,null);
        alerta.setTitle("Seleccionar imagen");
        seleccion = Selector.getSelectedItem().toString()+"FV";
        AlertDialog.Builder alertaFotos = new AlertDialog.Builder(DatosVehiculo.this);
        View viewFotos = getLayoutInflater().inflate(R.layout.spinner_camera_galeria,null);
        alertaFotos.setTitle("Seleccionar imagen");
        final Spinner spinnerFotos = (Spinner) viewFotos.findViewById(R.id.spinnerFotos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( DatosVehiculo.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.galeria_camera));
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
    String seleccion;
    public void seleccionTP(View view){
        AlertDialog.Builder alerta = new AlertDialog.Builder(DatosVehiculo.this);
        View view1 = getLayoutInflater().inflate(R.layout.spiner_fotos,null);
        alerta.setTitle("Seleccionar imagen");
        seleccion = Selector.getSelectedItem().toString()+"TP";
        AlertDialog.Builder alertaFotos = new AlertDialog.Builder(DatosVehiculo.this);
        View viewFotos = getLayoutInflater().inflate(R.layout.spinner_camera_galeria,null);
        alertaFotos.setTitle("Seleccionar imagen");
        final Spinner spinnerFotos = (Spinner) viewFotos.findViewById(R.id.spinnerFotos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( DatosVehiculo.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.galeria_camera));
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
                        llamarIntentTP();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            switch (requestCode){
                case cod_selecciona:
                    if(seleccion.equals(Selector.getSelectedItem().toString()+"TP")){
                        Uri mpath = data.getData();
                        TarjetaPropiedad.setImageURI(mpath);
                    }else{
                        Uri mpath = data.getData();
                        FotoVehiculo.setImageURI(mpath);
                    }

                    break;
                case REQUEST_IMAGE_CAPTURE:
                    if(seleccion.equals(Selector.getSelectedItem().toString()+"TP")){
                        Bundle extras = data.getExtras();
                        Bitmap imagen = (Bitmap)extras.get("data");
                        TarjetaPropiedad.setImageBitmap(imagen);
                    }else{
                        Bundle extras = data.getExtras();
                        Bitmap imagen = (Bitmap)extras.get("data");
                        FotoVehiculo.setImageBitmap(imagen);
                    }
                    break;
            }
        }
    }
    public void validar(){
        tVehiculo = Selector.getSelectedItem().toString();
        placaV = PlacaCarro.getText().toString();
        modelo =Modelo.getText().toString();
        col = Color.getText().toString();
        numCupos = Cupos.getText().toString();
        marca =Marca.getText().toString();
        if(tVehiculo.equals("Tipo de vehiculo")||placaV.equals("")||modelo.equals("")||col.equals("")||marca.equals("")){
            Toast.makeText(this, "Completar los espacios vacíos", Toast.LENGTH_SHORT).show();
            //val=false;
        }
            //val =true;
        /*if(tVehiculo=="Moto"){
            num=1;
        }else if(tVehiculo=="Carro"){
            num=4;
        }if(num<1){
            Cupos.setText("");
            Toast.makeText(this, "Inserte nuveamente los cupos de la moto", Toast.LENGTH_SHORT).show();
        }if(num<1||num>5){
            Cupos.setText("");
            Toast.makeText(this, "Inserte nuevamente los cupos del carro", Toast.LENGTH_SHORT).show();
        }*/
        //return val;
    }
    public void continuar(View view){
        validar();
    }

}
