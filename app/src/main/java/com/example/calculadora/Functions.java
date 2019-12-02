package com.example.calculadora;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

public class Functions {
    public static double soma(EditText numero1, EditText numero2){
        double num1 = Double.parseDouble(
                numero1.getText().toString());

        double num2 = Double.parseDouble(
                numero2.getText().toString());
        return  num1 + num2;
    }
    public static void androidprintscreen(Context ctx, String texto, String printtitle){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(ctx);

        dialogo.setTitle(printtitle);

        dialogo.setMessage(texto);

        dialogo.setNeutralButton("OK", null);

        dialogo.show();
    }
    public static double multiplica(EditText numero1, EditText numero2){
        double num1 = Double.parseDouble(
                numero1.getText().toString());

        double num2 = Double.parseDouble(
                numero2.getText().toString());
        return  num1 * num2;
    }
}