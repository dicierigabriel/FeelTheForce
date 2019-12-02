package com.example.calculadora;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JediAPI {
    @GET("people/{jedi}")
    Call<Jedi> buscarJEDI(@Path("jedi") String jedi);
    @GET("people")
    Call<ResultsJEDI> allJEDI();

}


