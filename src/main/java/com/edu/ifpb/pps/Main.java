package com.edu.ifpb.pps;

import java.time.LocalDate;
import java.util.List;

import com.edu.ifpb.pps.enums.Genero;
import com.edu.ifpb.pps.loadData.CSVDataLoader;
import com.edu.ifpb.pps.loadData.impl.MedicoMappingStrategy;
import com.edu.ifpb.pps.loadData.impl.PacienteMappingStrategy;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

public class Main {
    public static void main(String[] args) {
        
        // Paciente paciente = new Paciente();
        // paciente.setCpf("123.456.789.00");
        // paciente.setNome("João da Silva");
        // paciente.setGenero(Genero.MASCULINO);
        // paciente.setEmail("joao.silva@email.com");
        // paciente.setTelefone("4002-8922");
        // paciente.setDataNasc(LocalDate.of(2005, 6, 8));

        // Medico m1 = new Medico("Carlos ANDRÉ", "12344");
        // Medico m2 = new Medico("Marcelo Breno", "09876");

        // VisitorFormatter laudoFormatter = new HTMLFormatterVisitor();

        // Exame exame = new Hemograma(paciente, m1, m2, "Bancarios", "HapVida");
        // exame.setId(1);

        // exame.gerarLaudo(laudoFormatter);

        // System.out.println("-------------------------------------------");
        
        // exame = new Ressonancia(paciente, m1, m2, "TAMBAU", "Unimed");
        // exame.setId(231);
        
        // exame.gerarLaudo(laudoFormatter);
        
        // System.out.println("-------------------------------------------");
        
        // exame = new Ultrassonografia(paciente, m1, m2, "Cabo Branco", "UniDUB");
        // exame.setId(748437543);
        
        // exame.gerarLaudo(laudoFormatter);

        Paciente p = new Paciente(1, "12334", "Luiz", Genero.MASCULINO, "lf@email.com", "987999851", LocalDate.of(2005,6,8) );

        CSVDataLoader<Paciente> loader = new CSVDataLoader<>(new PacienteMappingStrategy());

        List<Paciente> lista = loader.carregar("src/main/resources/data/pacientes.csv");

        System.out.println(lista.get(0));

        CSVDataLoader<Medico> loader2 = new CSVDataLoader<>(new MedicoMappingStrategy());
        List<Medico> lista2 = loader2.carregar("src/main/resources/data/medicos.csv");

        System.out.println(lista2.get(0));


    }
}