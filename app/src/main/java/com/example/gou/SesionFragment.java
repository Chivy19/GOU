package com.example.gou;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonRequest jrq;
    EditText Usuario, Contra;
    Button btnInicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion,container,false);
        Usuario = (EditText)vista.findViewById(R.id.usuario);
        Contra = (EditText)vista.findViewById(R.id.contraseña);
        btnInicio = (Button)vista.findViewById(R.id.iniciar);
        rq = Volley.newRequestQueue(getContext());

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();
            }
        });

        return vista;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Validando"+Usuario.getText().toString(), Toast.LENGTH_SHORT).show();
        User usuario = new User();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObjectobj = null;
        try{
            jsonObjectobj = jsonArray.getJSONObject(0);
            usuario.setNombreuser(jsonObjectobj.optString("nombreuser"));
            usuario.setContra(jsonObjectobj.optString("correo"));
            usuario.setCorreo(jsonObjectobj.optString("contraseña"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getContext(),Main2Activity.class);
        intent.putExtra(Main2Activity.nombre,usuario.getNombreuser());
        intent.putExtra(Main2Activity.correo,usuario.getCorreo());
        startActivity(intent);

    }

    private void iniciar(){
        String url = "https://enlamala.000webhostapp.com/inicio.php?nombreuser="+Usuario.getText().toString()+"&correo="+Contra.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);

    }
}
