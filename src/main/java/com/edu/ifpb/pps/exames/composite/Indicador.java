package com.edu.ifpb.pps.exames.composite;

import java.util.ArrayList;
import java.util.List;

// Indicadores Sanguineos. Ex.: Glicose, Colesterol, etc
public class Indicador extends ItemSanguineo {
    private String valor;
    private String unidade;
    private String valoresReferencia;
    // private List<String> valoresReferencia = new ArrayList<>();
    // private List<String> valoresCriticos = new ArrayList<>();


    public Indicador(String nome, String valor, String unidade, String valoresReferencia) {
        super(nome);
        this.valor = valor;
        this.unidade = unidade;
        this.valoresReferencia = valoresReferencia;
    }

    public String getValor() { 
        return valor; 
    }
    public String getUnidade() { 
        return unidade; 
    }
    public String getValorReferencia() { 
        return valoresReferencia;
    }
    // public List<String> getValorReferencia() { 
    //     return valoresReferencia;
    // }

    // public void addValorReferencia(String valor) {
    //     this.valoresReferencia.add(valor);
    // }

    // public void removeValorReferencia(String valor) {
    //     this.valoresReferencia.remove(valor);
    // }

    // public void addValorCritico(String valor) {
    //     this.valoresCriticos.add(valor);
    // }

    // public void removeValorCritico(String valor) {
    //     this.valoresCriticos.remove(valor);
    // }
}
