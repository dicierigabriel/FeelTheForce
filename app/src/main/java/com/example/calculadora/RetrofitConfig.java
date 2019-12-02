package com.example.calculadora;



import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
    public JediAPI getJediSearch() {
        return this.retrofit.create(JediAPI.class);
    }
}