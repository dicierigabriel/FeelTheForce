package com.example.calculadora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.*;
import android.view.*;


import android.os.Bundle;


import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.calculadora.Utils.filterJediNames;
import static com.example.calculadora.staticVariables.allJedis;
import static org.xmlpull.v1.XmlPullParser.TYPES;


public class MainActivity extends AppCompatActivity {
    ListView lista_jedi;
    TextView name, height, mass, hair_color, skin_color, eye_color, birth_year, gender;
    Button back;
    int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista_jedi = (ListView) findViewById(R.id.jedis);



        final Call<ResultsJEDI> allJEDI = new RetrofitConfig().getJediSearch().allJEDI();
        final Context ctx = this.getBaseContext();
        allJEDI.enqueue(new Callback<ResultsJEDI>() {

                            @Override
                            public void onResponse(Call<ResultsJEDI> call, Response<ResultsJEDI> response) {
                                ResultsJEDI resultados = response.body();
                                String[] nomes_JEDI = filterJediNames(resultados);
                                allJedis = nomes_JEDI;
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                                        ctx, R.layout.listview_style, nomes_JEDI);
                                lista_jedi.setAdapter(spinnerArrayAdapter);

                            }

                            @Override
                            public void onFailure(Call<ResultsJEDI> call, Throwable t) {

                            }
                        });



        // Selecionar item da lista
        lista_jedi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    setContentView(R.layout.jediscreen);
                    name = (TextView) findViewById(R.id.name);
                    height = (TextView) findViewById(R.id.height);
                    mass = (TextView) findViewById(R.id.mass);
                    hair_color = (TextView) findViewById(R.id.hair_color);
                    skin_color = (TextView) findViewById(R.id.skin_color);
                    eye_color = (TextView) findViewById(R.id.eye_color);
                    birth_year = (TextView) findViewById(R.id.birth_year);
                    gender = (TextView) findViewById(R.id.gender);
                    String nome = ((TextView) view).getText().toString();
                    int indx_jedi = Arrays.asList(allJedis).indexOf(nome) + 1;

                Call<Jedi> call = new RetrofitConfig().getJediSearch().buscarJEDI(String.valueOf(indx_jedi));
                call.enqueue(new Callback<Jedi>() {
                    @Override
                    public void onResponse(Call<Jedi> call, Response<Jedi> response) {


                        Jedi jedi = response.body();

                        String nome_jedi = jedi.getName().toString();

                        name.setText(jedi.getName().toString());
                        height.setText("Altura: " + jedi.getHeight().toString());
                        mass.setText("Peso: "+jedi.getMass().toString());
                        hair_color.setText("Cor do cabelo: "+jedi.getHair_color().toString());
                        skin_color.setText("Cor da pele: "+jedi.getSkin_color().toString());
                        eye_color.setText("Cor dos olhos: "+jedi.getEye_color().toString());
                        birth_year.setText("Ano de nascimento: "+jedi.getBirth_year().toString());
                        gender.setText("Genero: "+jedi.getGender().toString());
                        back = (Button) findViewById(R.id.back);
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent( MainActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        });
                        //AlertDialog.Builder dialogo = new AlertDialog.Builder(
                         //       MainActivity.this);
                        //dialogo.setTitle("Jedi");
                       // dialogo.setMessage("Jedi selecionado: " + nome_jedi );
                       // dialogo.show();
                    }

                    @Override
                    public void onFailure(Call<Jedi> call, Throwable t) {
                        Log.e("JEDIService   ", "Erro ao buscar o Jedi:" + t.getMessage());
                    }
                });



            }


        });



}
}
