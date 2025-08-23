package com.edu.ifpb.pps.exames.composite;

import java.util.ArrayList;
import java.util.List;

// Indicadores Sanguineos. Ex.: Glicose, Colesterol, etc
public class Indicador extends ItemSanguineo {
    private String valor;
    private String unidade;
    private List<String> valoresReferencia = new ArrayList<>();
    private List<String> valoresCriticos = new ArrayList<>();


    public Indicador(String nome, String valor, String unidade, List<String> valoresReferencia, List<String> valoresCriticos) {
        super(nome);
        this.valor = valor;
        this.unidade = unidade;
        this.valoresReferencia = valoresReferencia;
        this.valoresCriticos = valoresCriticos;
    }

    public String getValor() { 
        return valor; 
    }
    public String getUnidade() { 
        return unidade; 
    }

    public String valoresReferenciaFormatados(){
        String conteudo = "";
        for (String valor : this.valoresReferencia) {
            conteudo += valor + "<br>";
        }

        return conteudo;
    }

    public String valoresCriticosFormatados(){
        String conteudo = "";
        for (String valor : this.valoresCriticos) {
            conteudo += valor + "<br>";
        }

        return conteudo;
    }
    public List<String> getValoresReferencia() { 
        return valoresReferencia;
    }

    public void addValorReferencia(String valor) {
        this.valoresReferencia.add(valor);
    }

    public void removeValorReferencia(String valor) {
        this.valoresReferencia.remove(valor);
    }

    public void addValorCritico(String valor) {
        this.valoresCriticos.add(valor);
    }

    public void removeValorCritico(String valor) {
        this.valoresCriticos.remove(valor);
    }
}
