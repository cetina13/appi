package com.app.appi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.app.appi.model.AdapterPokemon;
import com.app.appi.model.ModelPokemon;
import com.app.appi.myclass.Pokemon;
import com.app.appi.service.ServicioPokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private AdapterPokemon adapterPokemon;
    private Retrofit retrofit;
    public static  final String BASE_URL="https://pokeapi.co/api/v2/";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciador();
        showPokemos();
    }

    private void showPokemos() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServicioPokemon poke = retrofit.create(ServicioPokemon.class);
        Call<ModelPokemon> pokemonCall = poke.getPokemones();
        pokemonCall.enqueue(new Callback<ModelPokemon>() {
        @Override
        public void onResponse(Call<ModelPokemon> call, Response<ModelPokemon> response) {
            if (response.isSuccessful()){
                ModelPokemon modelPokemon = response.body();
                ArrayList<Pokemon> lista = modelPokemon.getResults();
                adapterPokemon = new AdapterPokemon(lista,getApplicationContext());
                listView.setAdapter(adapterPokemon);
            }else{
                Toast.makeText(getApplicationContext(),"Error. "+response.errorBody(),Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Call<ModelPokemon> call, Throwable t) {
            Log.e(null,""+t.getMessage());
        }
    });
    }

    private void iniciador(){
        listView = findViewById(R.id.listPoke);
    }
}