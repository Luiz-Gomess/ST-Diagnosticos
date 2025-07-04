package com.edu.ifpb.pps.laudos.impl;

import java.util.Map;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.GeradorDeLaudo;
import com.edu.ifpb.pps.models.CorpoExame;

public class GeradorHTML implements GeradorDeLaudo {

    @Override
    public String gerarLaudo(Exame exame) {

        CorpoExame dados = exame.getDescricaoExame();
        
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>Exame de " +dados.titulo + " </title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>" + dados.titulo + "</h1>\n");
        sb.append("<p>" + dados.descricao + "</p>\n");
        sb.append("<h2>Detalhes do Exame</h2>\n");

        for (String linha : (Iterable<String>) dados.listaItens) {
            sb.append("<p>" + linha + "</p>\n");
        }

        for (Map<String, String> imagem : dados.imagens) {
            sb.append("<img src=\"" + imagem.get("src") + "\" alt=\"" + imagem.get("alt") + "\" />\n");
        }
        
        sb.append("</body>\n");
        sb.append("</html>\n");

        return sb.toString();
    }
    
}
