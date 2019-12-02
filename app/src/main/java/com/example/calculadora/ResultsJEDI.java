package com.example.calculadora;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ResultsJEDI {
    private Jedi[] results;
    public Jedi[] getResults() {
        return results;
    }

    public void setResults(Jedi[] results) {
        this.results = results;
    }

}
