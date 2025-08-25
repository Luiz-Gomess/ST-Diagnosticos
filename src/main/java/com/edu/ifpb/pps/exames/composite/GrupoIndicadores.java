package com.edu.ifpb.pps.exames.composite;

import java.util.ArrayList;
import java.util.List;

// Agrupa os diferentes indicadores
public class GrupoIndicadores extends ItemSanguineo {
    private List<ItemSanguineo> itens = new ArrayList<>();

    public GrupoIndicadores(String nome) {
        super(nome);
    }

    @Override
    public void adicionar(ItemSanguineo item) {
        itens.add(item);
    }
    
    public List<ItemSanguineo> getItens() {
        return itens;
    }
}