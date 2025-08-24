package com.edu.ifpb.pps.exames;

import java.util.LinkedList;
import java.util.Queue;

import com.edu.ifpb.pps.enums.Prioridade;

public class FilaExames {

    private Queue<Exame> fila;

    public FilaExames() {
        this.fila = new LinkedList<>();
    }

    // Adiciona exame respeitando prioridade
    public void adicionarExame(Exame exame) {
        if (exame.getPrioridade() == Prioridade.URGENTE) {
            // Vai direto para o início da fila
            ((LinkedList<Exame>) fila).addFirst(exame);

        } else if (exame.getPrioridade() == Prioridade.POUCO_URGENTE) {
            // Procura o último URGENTE e insere logo depois
            int index = 0;
            int ultimoUrgente = -1;
            for (Exame e : fila) {
                if (e.getPrioridade() == Prioridade.URGENTE) {
                    ultimoUrgente = index;
                }
                index++;
            }

            if (ultimoUrgente == -1) {
                ((LinkedList<Exame>) fila).addFirst(exame); // se não tem urgentes, vai pro início
            } else {
                ((LinkedList<Exame>) fila).add(ultimoUrgente + 1, exame);
            }

        } else {
            // ROTINA -> vai para o final
            fila.add(exame);
        }
    }

    public boolean estaVazia() {
        return fila.isEmpty();
    }

    // Retira o próximo exame da fila
    public Exame proximoExame() {
        return fila.poll();
    }

    public void mostrarFila() {
        fila.forEach(System.out::println);
    }
}
