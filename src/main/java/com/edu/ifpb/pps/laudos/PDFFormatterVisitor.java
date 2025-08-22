package com.edu.ifpb.pps.laudos;

import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;

public class PDFFormatterVisitor extends VisitorFormatter{

    private final String PASTA_DESTINO = getPathDestino("pdf");

    @Override
    public void gerarLaudo(Hemograma hemograma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarLaudo'");
    }

    @Override
    public void gerarLaudo(Ressonancia ressonancia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarLaudo'");
    }

    @Override
    public void gerarLaudo(RaioX ultrassonografia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarLaudo'");
    }
    
}
