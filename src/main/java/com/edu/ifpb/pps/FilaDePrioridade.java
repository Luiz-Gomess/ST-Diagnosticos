package com.edu.ifpb.pps;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.edu.ifpb.pps.exames.Exame;

public class FilaDePrioridade {
    private PriorityQueue<Exame> fila;

        public FilaDePrioridade() {
        fila = new PriorityQueue<>(Comparator
            .comparing(Exame::getPrioridade) 
            );
    }

    public void adicionar(Exame exame) {
        fila.add(exame);
    }

    public Exame proximo() {
        return fila.poll();
    }

}
