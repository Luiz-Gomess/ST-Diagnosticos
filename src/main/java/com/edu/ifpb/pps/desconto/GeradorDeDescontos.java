package com.edu.ifpb.pps.desconto;

import com.edu.ifpb.pps.exames.Exame;

public abstract class GeradorDeDescontos {
    protected GeradorDeDescontos next;

    public void setNext(GeradorDeDescontos next) {
        this.next = next;
    }

    public void processar(Exame exame) {
        aplicarDesconto(exame);
        if(this.next != null){
            next.processar(exame);
        }
    }

    protected abstract void aplicarDesconto(Exame exame);
}
