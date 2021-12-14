package com.example.apiheader;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = findViewById(R.id.txtDatos);



    }

    private void findR(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api-uat.kushkipagos.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();


        ApiBancos apiBancos = retrofit.create(ApiBancos.class);
        Call<List<Bancos>> call = apiBancos.getBancos("79af2904a8ff47f2906a03e6fc46b6c7","Public-Merchant-Id");


        call.enqueue(new Callback<List<Bancos>>() {
            @Override
            public void onResponse(Call<List<Bancos>> call, Response<List<Bancos>> response) {
                if(!response.isSuccessful())
                {

                    datos.setText("Código: " + response.code());
                    return;

                }
                List<Bancos> kushkiList = response.body();


                //Mostrar los datos en el TextView
                for (Bancos data: kushkiList.subList(0,20))
                {
                    String labelId = "Codigo: ";
                    String labelName = "Nombre: ";

                    SpannableString myTextCode = new SpannableString(labelId + data.getCode() + "\n");
                    SpannableString myTextName = new SpannableString(labelName + data.getName() + "\n\n");

                    StyleSpan bold = new StyleSpan(Typeface.BOLD);
                    StyleSpan bold2 = new StyleSpan(Typeface.BOLD);

                    myTextCode.setSpan(bold, 0 , labelId.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    myTextName.setSpan(bold2, 0 , labelName.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                    datos.append(myTextCode);
                    datos.append(myTextName);


                }



            }

            @Override
            public void onFailure(Call<List<Bancos>> call, Throwable t) {
                String msj = "Mensaje de error: " + t.getMessage();
                datos.setText(msj);

            }
        });
    }


   public  void  bntVisualizar (View view)
   {
       findR();
   }



}