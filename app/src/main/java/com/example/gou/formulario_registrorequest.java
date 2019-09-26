package com.example.gou;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class formulario_registrorequest extends StringRequest{
    private static final String ruta = "https://enlamala.000webhostapp.com/registro1.php";
    private Map<String,String> parametros;
    public formulario_registrorequest (int Id,String nombreuser,String Contraseña,String email , Response.Listener<String>listener){
        super(Request.Method.POST,ruta,listener, null);
        parametros = new HashMap<>();
        parametros.put("id", Id+"");
        parametros.put("nombreuser", nombreuser+"");
        parametros.put("correo",email+"");

        parametros.put("contraseña",Contraseña+"");
    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
    }
