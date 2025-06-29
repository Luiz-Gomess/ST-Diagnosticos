package com.edu.ifpb.pps;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.modelmapper.ModelMapper;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.factory.ExameFactory;
import com.edu.ifpb.pps.geradorDeLaudo.impl.GeradorHTML;
import com.edu.ifpb.pps.geradorDeLaudo.impl.GeradorPDF;
import com.edu.ifpb.pps.modelos.Medico;
import com.edu.ifpb.pps.modelos.Paciente;



public class Main {
    public static void main(String[] args) {

        PriorityQueue<Exame> fila = new PriorityQueue<>(Comparator.comparing(Exame::getPrioridade));

        Paciente p1 = new Paciente("Luiz", "fernando.albuquerque@academico.ifpb.edu.br","83987999851");
        Paciente p2 = new Paciente("Luiz Fernando", "fernando.albuquerque@academico.ifpb.edu.br","83987999851");
        Paciente p3 = new Paciente("Joãozinho", "fernando.albuquerque@academico.ifpb.edu.br","83987999851");
        Medico m1 = new Medico("Dr. Medico", "1234");

        Exame exame;
        ModelMapper mapper = new ModelMapper();

        Map<String, Object> dadosExame= new HashMap<>();
        dadosExame.put("nomeMedicoSolicitante", "Medico Solicitante");
        dadosExame.put("medico", m1);
        dadosExame.put("paciente", p1);
        dadosExame.put("preco", 100.0);

        exame = ExameFactory.create("ultrassonografia");
        exame = mapper.map(dadosExame, exame.getClass());
        
        exame.setPrioridade(Prioridade.POUCO_URGENTE);
        exame.setGerador(new GeradorHTML());

        fila.add(exame);

        exame = ExameFactory.create("hemograma");
        exame = mapper.map(dadosExame, exame.getClass());

        exame.setGerador(new GeradorPDF());
        exame.setPrioridade(Prioridade.URGENTE);

        fila.add(exame);
        
        System.out.println(fila.poll().gerarLaudo("Laudo do exame urgente"));
        System.out.println(fila.poll().gerarLaudo("Laudo do exame pouco urgente"));

        
        
        

    }
}