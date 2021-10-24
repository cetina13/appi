package com.app.appi.model;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.appi.R;
import com.app.appi.myclass.Pokemon;

import java.util.List;

public class AdapterPokemon extends BaseAdapter {

private List<Pokemon> pokemonlist;
Context contex;

    public AdapterPokemon(List<Pokemon> pokemonlist, Context contex) {
        this.pokemonlist = pokemonlist;
        this.contex = contex;
    }

    @Override
    public int getCount() {
        return pokemonlist.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemonlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Pokemon pokemon = (Pokemon) getItem(position);
        convertView= LayoutInflater.from(contex).inflate(R.layout.element,null );
        if (convertView != null){
            TextView name = convertView.findViewById(R.id.tvName);
            TextView url = convertView.findViewById(R.id.tvUrl);
            name.setText(pokemon.getName());
            url.setText(pokemon.getUrl());
                    }
        else{
            Log.i(TAG, "Error en el adaptador");
        }


        return convertView;
    }
}
