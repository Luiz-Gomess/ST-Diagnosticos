package com.edu.ifpb.pps;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.laudos.impl.GeradorHTML;
import com.edu.ifpb.pps.validacoes.Validador;

public class Main {
    public static void main(String[] args) {

        // Exame exame = new Hemograma(new GeradorHTML(), 10.3, 12.5, 40.0);
        Exame exame = new Hemograma(new GeradorHTML(), 10, 12, 40);
        exame.gerarLaudo();
        
        exame = new Ressonancia(new GeradorHTML());
        exame.gerarLaudo();

        // Validador validadorHemograma = new ExecessoValoresHemoglobinas();

        // validadorHemograma.validar(exame);
          
    }
}