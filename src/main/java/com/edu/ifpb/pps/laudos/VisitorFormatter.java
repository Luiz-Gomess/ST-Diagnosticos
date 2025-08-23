package com.edu.ifpb.pps.laudos;

import com.edu.ifpb.pps.exames.impl.Sanguineo;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.RaioX;

public abstract class VisitorFormatter {

    public String getPathDestino(String formato) {
        return "./src/main/resources/templates/laudos_criados/" + formato + "/";
    }
    
    public abstract void gerarLaudo(Sanguineo hemograma);
    public abstract void gerarLaudo(Ressonancia ressonancia);
    public abstract void gerarLaudo(RaioX ultrassonografia);
}
