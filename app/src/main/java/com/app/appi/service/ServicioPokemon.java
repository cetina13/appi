package com.app.appi.service;

import com.app.appi.model.ModelPokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicioPokemon {

@GET("pokemon")
Call<ModelPokemon> getPokemones();



}
