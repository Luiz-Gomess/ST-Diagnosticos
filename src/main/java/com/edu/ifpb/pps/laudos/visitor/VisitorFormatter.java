package com.edu.ifpb.pps.laudos.visitor;

import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.RaioX;

public interface VisitorFormatter {
    
    void gerarLaudo(Hemograma hemograma);
    void gerarLaudo(Ressonancia ressonancia);
    void gerarLaudo(RaioX ultrassonografia);
}
