package com.edu.ifpb.pps.laudos.visitor;

import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;

public class PDFFormatterVisitor implements VisitorFormatter{

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
