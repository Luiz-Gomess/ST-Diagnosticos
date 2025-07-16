package com.edu.ifpb.pps.laudos.visitor;

import java.io.FileOutputStream;
import java.io.IOException;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.Ultrassonografia;

public class HTMLFormatterVisitor implements VisitorFormatter {

    @Override
    public Object gerarCabecalho(Exame exame) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>Laudo de " + exame.getClass().getSimpleName() + "</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<header>\n");
        sb.append("<p> RA: " + exame.getId() + "</p>\n");
        sb.append("<p> Sr(a): " + exame.getNomePaciente() + "</p>\n");
        sb.append("<p> Dr(a): " + exame.getMedicoSolicitante() + "</p>\n");
        sb.append("<p> Coleta: " + exame.getLocalColeta() + "</p>\n");
        sb.append("<p> Convenio: " + exame.getConvenio() + "</p>\n");
        sb.append("<p> Data de Nascimento: " + exame.getDataNascimento() + "</p>\n");
        sb.append("<p> Idade: " + exame.getIdade() + "</p>\n");
        sb.append("<p> Data de Atendimento: " + exame.getDataExame() + "</p>\n");
        sb.append("</header>\n");
        sb.append("<hr>\n");
        sb.append("<main>\n");

        return sb.toString();
    }

    @Override
    public Object gerarRodape(Exame exame) {
        StringBuilder sb = new StringBuilder();
        sb.append("</main>\n");
        sb.append("<footer>\n");
        sb.append("<p> " + exame.getMedicoLaudista().getNome() + "</p>\n");
        sb.append("<p> " + exame.getMedicoLaudista().getCrm() + "</p>\n");
        sb.append("</footer>\n");
        sb.append("</body>\n");
        sb.append("</html>");

        return sb.toString();
    }


    @Override
    public void gerarLaudo(Hemograma hemograma) {


        StringBuilder sb = new StringBuilder();

        sb.append("<h1> Hemograma </h1>\n");
        sb.append("<p> Hemoglobina: " + hemograma.getHemoglobina() + "</p>\n");
        sb.append("<p> Hematócrito: " + hemograma.getHematocrito() + "</p>\n");
        sb.append("<p> Leucócitos: " + hemograma.getLeucocitos() + "</p>\n");
        sb.append("<p> Plaquetas: " + hemograma.getPlaquetas() + "</p>\n");
        sb.append("<p> V.M.C.: " + hemograma.getVolumeCorpuscularMedio() + "</p>\n");

        String cabecalho = (String) gerarCabecalho(hemograma);
        String corpo = sb.toString();
        String rodape = (String) gerarRodape(hemograma);

        String html = cabecalho + corpo + rodape;

        try (FileOutputStream fos = new FileOutputStream("exame.html")) {
            fos.write(html.getBytes());
            System.out.println("File created and content written.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    @Override
    public void gerarLaudo(Ressonancia ressonancia) {

        gerarCabecalho(ressonancia);

        StringBuilder sb = new StringBuilder();

        sb.append("<h1> Ressonancia </h1>\n");
        sb.append("<p> Região do Corpo: " + ressonancia.getRegiaoCorpo() + "</p>\n");
        sb.append("<p> Conclusão do Laudo: " + ressonancia.getConclusaoLaudo() + "</p>\n");

        for(String camimhoImagem : ressonancia.getImagens()) {
            sb.append("<img src=\"" + camimhoImagem + "\" alt=\"Imagem de Ressonancia\" />\n");
        }
        sb.append("<p> Contraste Usado: " + (ressonancia.isContrasteUsado() ? "Sim" : "Não") + "</p>\n");

        System.out.println(sb.toString());

        gerarRodape(ressonancia);

    }

    @Override
    public void gerarLaudo(Ultrassonografia ultrassonografia) {

        gerarCabecalho(ultrassonografia);

        StringBuilder sb = new StringBuilder();

        sb.append("<h1> Ultrassonografia </h1>\n");
        sb.append("<p> Órgão Avaliado: " + ultrassonografia.getOrgaoAvaliado() + "</p>\n");
        sb.append("<p> Laudo Descritivo: " + ultrassonografia.getLaudoDescritivo() + "</p>\n");
        sb.append("<img src=\"" + ultrassonografia.getCaminhoImagem() + "\" alt=\"Imagem de Ultrassonografia\" />\n");

        System.out.println(sb.toString());

        gerarRodape(ultrassonografia);
    }
    
}
