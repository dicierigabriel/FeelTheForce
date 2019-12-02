package com.example.calculadora;

import java.util.ArrayList;

public class Utils {
    public static String[] filterJediNames(ResultsJEDI results){
        ArrayList<String> res = new ArrayList<>();
        for(Jedi e : results.getResults()){
            res.add(e.getName());
        }
        return res.toArray(new String[res.size()]);
    }
}
