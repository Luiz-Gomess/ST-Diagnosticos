package com.edu.ifpb.pps.laudos.visitor;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.Ultrassonografia;

public interface VisitorFormatter {

    Object gerarCabecalho(Exame exame);
    Object gerarRodape(Exame exame);

    void gerarLaudo(Hemograma hemograma);
    void gerarLaudo(Ressonancia ressonancia);
    void gerarLaudo(Ultrassonografia ultrassonografia);
}
