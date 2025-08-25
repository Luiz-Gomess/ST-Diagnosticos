package com.edu.ifpb.pps.relatoriosObserver;

import java.util.HashMap;
import java.util.Map;

import com.edu.ifpb.pps.exames.Exame;

public class EstatisticaLaudosObserver implements LaudoObserver {
    private Map<String, Integer> contadorPorTipo = new HashMap<>();

    public void onLaudoGerado(Exame exame) {
        String tipo = exame.getClass().getSimpleName();
        contadorPorTipo.put(tipo, contadorPorTipo.getOrDefault(tipo, 0) + 1);
        System.out.println("[Observer] Laudo contabilizado: " + tipo + " (" + contadorPorTipo.get(tipo) + ")");
    }

    public void gerarRelatorio() {
        System.out.println("===== RELATÓRIO DE LAUDOS =====");
        contadorPorTipo.forEach((tipo, qtd) ->
            System.out.println(tipo + ": " + qtd + " laudos gerados"));
    }
}
