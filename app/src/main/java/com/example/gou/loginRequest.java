package com.example.gou;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class loginRequest extends StringRequest {

    private static final String ruta = "https://enlamala.000webhostapp.com/login1.php";
    private Map<String,String> parametros;
    public loginRequest (String user,  Response.Listener<String>listener){
        super(Request.Method.POST,ruta,listener, null);
        parametros = new HashMap<>();
        parametros.put("nombreuser", user+"");
    }

    @Override
    protected Map<String, String> getParams()  {
        return parametros;

    }
}
