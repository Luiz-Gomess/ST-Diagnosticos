package com.edu.ifpb.pps;

import java.time.LocalDate;
import java.util.List;

import com.edu.ifpb.pps.enums.Genero;
import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.FilaExames;
import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.laudos.TXTFormatterVisitor;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.loadData.CSVDataLoader;
import com.edu.ifpb.pps.loadData.impl.MedicoMappingStrategy;
import com.edu.ifpb.pps.loadData.impl.PacienteMappingStrategy;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.INotificador;
import com.edu.ifpb.pps.notificacoes.NotificadorEmail;

public class Main {
    public static void main(String[] args) {
        
        // Paciente paciente = new Paciente();
        // paciente.setCpf("123.456.789.00");
        // paciente.setNome("João da Silva");
        // paciente.setGenero(Genero.MASCULINO);
        // paciente.setEmail("joao.silva@email.com");
        // paciente.setTelefone("4002-8922");
        // paciente.setDataNasc(LocalDate.of(2005, 6, 8));

        

        // VisitorFormatter laudoFormatter = new HTMLFormatterVisitor();

        // Exame exame = new Hemograma(paciente, m1, m2, "Bancarios", "HapVida");
        // exame.setId(1);

        // exame.gerarLaudo(laudoFormatter);

        // System.out.println("-------------------------------------------");
        
        // exame = new Ressonancia(paciente, m1, m2, "TAMBAU", "Unimed");
        // exame.setId(231);
        
        // exame.gerarLaudo(laudoFormatter);
        
        // System.out.println("-------------------------------------------");
        
        // 
        // exame.setId(748437543);
        
        // exame.gerarLaudo(laudoFormatter);

        Paciente p = new Paciente(1, "12334", "Luiz", Genero.MASCULINO, "lf@email.com", "987999851", LocalDate.of(2005,6,8) );

        CSVDataLoader<Paciente> loader = new CSVDataLoader<>(new PacienteMappingStrategy());

        List<Paciente> lista = loader.carregar("src/main/resources/data/pacientes.csv");

        // System.out.println(lista);

        CSVDataLoader<Medico> loader2 = new CSVDataLoader<>(new MedicoMappingStrategy());
        List<Medico> lista2 = loader2.carregar("src/main/resources/data/medicos.csv");

        // System.out.println(lista2.get(0));

        Medico m1 = new Medico();
        m1.setNome("Carlos ANDRÉ");
        m1.setCrm("12344");
        Medico m2 = new Medico();
        m2.setNome("Marcelo Breno");
        m2.setCrm("09876");

        Exame exame1 = new Hemograma(p, m1, m2, "Bancarios", "HapVida", Prioridade.ROTINA);
        // Exame exame2 = new Ressonancia(p, m1, m2, "TAMBAU", "Unimed", "Cabeça", "Ressonância Magnética da Cabeça", 5.0, List.of("imagem1.jpg", "imagem2.jpg"), false, Prioridade.URGENTE);
        Exame exame3 = new Hemograma(p, m1, m2, "Bancarios", "HapVida", Prioridade.URGENTE);
        // Exame exame4 = new Ressonancia(p, m1, m2, "TAMBAU", "Unimed", "Joelho", "Ressonância Magnética do Joelho", 10.0, List.of("imagem3.jpg"), true, Prioridade.URGENTE);

        FilaExames fila = new FilaExames();
        fila.adicionarExame(exame1);
        // fila.adicionarExame(exame2);
        fila.adicionarExame(exame3);
        // fila.adicionarExame(exame4);

        fila.mostrarFila();

        // Gerar laudos
        VisitorFormatter Laudoformatter = new TXTFormatterVisitor();
        fila.proximoExame().gerarLaudo(Laudoformatter);

        INotificador notificarEmail = new NotificadorEmail(null);
        notificarEmail.notificar(lista.get(3), "src/main/resources/data/laudos/laudo_ressonancia.txt");
        System.out.println("Laudo enviado por email com sucesso! " + lista.get(3).getEmail());
    }
}