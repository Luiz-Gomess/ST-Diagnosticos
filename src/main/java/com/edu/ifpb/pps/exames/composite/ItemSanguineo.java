package com.edu.ifpb.pps.exames.composite;


// Abstração comum ao GrupoIndicadores e Indicadores
public abstract class ItemSanguineo {
    protected String nome;

    public ItemSanguineo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Métodos para o composite
    public void adicionar(ItemSanguineo item) {
        throw new UnsupportedOperationException();
    }
}
