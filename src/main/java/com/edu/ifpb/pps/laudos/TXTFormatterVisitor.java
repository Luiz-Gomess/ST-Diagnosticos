package com.edu.ifpb.pps.laudos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

public class TXTFormatterVisitor extends VisitorFormatter {

    private final String PASTA_DESTINO = getPathDestino("txt");


    private void createTXT(String conteudo, String tipoExame) {

        // Cria o arquivo TXT com o conteúdo gerado 
        try {
            Path caminhoSaida = Path.of(String.format(PASTA_DESTINO + "laudo_%s.txt", tipoExame));
            Files.writeString(caminhoSaida, conteudo);
            System.out.println("✅ Laudo TXT gerado com sucesso!");
            System.out.println("Arquivo salvo em: " + caminhoSaida.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gerarLaudo(Hemograma hemograma) {

    }

    @Override
    public void gerarLaudo(Ressonancia ressonancia) {

        // Gera o corpo do conteúdo para o exame Ressonância 

        StringBuilder sb = new StringBuilder();

        // Adiciona o cabeçalho 
        sb.append(gerarCabecalho(ressonancia));
        sb.append(String.format("ÓRGÃO AVALIADO: %s\n", ressonancia.getRegiaoCorpo())); 
        sb.append("----------------------------------------------------------------------\n");
        sb.append("LAUDO DESCRITIVO:\n");
        sb.append(ressonancia.getDescricao());
        sb.append("\n\n");
        sb.append("Contraste: \n");
        sb.append(ressonancia.getContrasteUsado() > 0.0 ? ressonancia.getContrasteUsado() + " ml" : "Não utilizado");
        sb.append("\n\n");

        // Como é um txt, apenas aparecem os caminhos das imagens.
        if (!ressonancia.getImagens().isEmpty()) {
            sb.append("IMAGENS DE REFERÊNCIA (caminhos):\n");
            for (String caminho : ressonancia.getImagens()) {
                sb.append(String.format("- %s\n", caminho));
            }
            sb.append("\n");
        }

        //Adiciona o Rodapé
        sb.append(gerarRodape(ressonancia));
        this.createTXT(sb.toString(), "ressonancia");
    }

    @Override
    public void gerarLaudo(RaioX raiox) {

        // Gera o corpo do conteúdo para o exame RaioX
        StringBuilder sb = new StringBuilder();

        sb.append(gerarCabecalho(raiox));

        sb.append(String.format("ÓRGÃO AVALIADO: %s\n", raiox.getOrgaoAvaliado()));
        sb.append("-".repeat(70) + "\n");
        sb.append("DESCRIÇÃO \n");
        sb.append(raiox.getLaudoDescritivo());
        sb.append("\n\n");
        sb.append("IMAGEM DE REFERÊNCIA (caminho):\n");
        sb.append("\n");
        sb.append("- " + raiox.getCaminhoImagem() + "\n\n");
        sb.append("RADIOLOGISTA \n");
        sb.append(raiox.getMedicoLaudista().getNome() + "\n");
        sb.append(gerarRodape(raiox));
        this.createTXT(sb.toString(), "raiox");

    }
    
    // Gera o cabeçalho comum a todos os tipos de exames
    private String gerarCabecalho(Exame exame) {

        StringBuilder sb = new StringBuilder();

        sb.append("=".repeat(70) + "\n");
        sb.append(String.format("%" + (35 + exame.getClass().getSimpleName().length() / 2) + "s\n", exame.getClass().getSimpleName().toUpperCase()));
        sb.append("=".repeat(70) + "\n\n");
        sb.append(String.format("%-25s %s\n", "Paciente:", exame.getPaciente().getNome()));
        sb.append(String.format("%-25s %s\n", "Dr(a):", exame.getMedicoSolicitante().getNome()));
        sb.append(String.format("%-25s %s\n", "Coleta:", exame.getLocalColeta()));
        sb.append(String.format("%-25s %s\n", "Convênio:", exame.getConvenio()));
        exame.getPaciente().getDataNasc();
        sb.append(String.format("%-25s %s\n", "D.N.:", exame.getPaciente().getDataNasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        sb.append("-".repeat(70) + "\n");
        
        return sb.toString();
    }

    // Gera o rodapé comum a todos os tipos de exames
    private String gerarRodape (Exame exame) {
        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat(70) + "\n");
        sb.append("Gerado em: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
        sb.append("Dr(a): " + exame.getMedicoLaudista().getNome() + "\n");
        sb.append("CRM: " + exame.getMedicoLaudista().getCrm() + "\n");
        sb.append("-".repeat(70) + "\n");

        return sb.toString();
    }

    // Teste
    public static void main(String[] args) {
        Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851",
            LocalDate.of(2005, 6, 8)
        );
        Medico solicitante = new Medico("João da Silva", "7653");
        Medico laudista = new Medico("João Laudista", "12345");

        TXTFormatterVisitor formatter = new TXTFormatterVisitor();
        Exame ressonancia = new Ressonancia(paciente, solicitante, laudista, "TAMBAÚ", "UNIMED", "JOELHO", "Ressonância no joelho", 3.0, List.of(
            "/home/luiz/pps/projeto/src/main/resources/imagens/banana.jpg",
            "/home/luiz/pps/projeto/src/main/resources/imagens/maca.jpg"
        ), false, true);

        ressonancia.gerarLaudo(formatter);

        Exame raiox = new RaioX(paciente, solicitante, laudista, "Bancários", "Roseane Doris", "PULMÃO", "Raio X do pulmão",
         "/home/luiz/pps/projeto/src/main/resources/imagens/banana.jpg", true
        );
        raiox.gerarLaudo(formatter);
    }
}
