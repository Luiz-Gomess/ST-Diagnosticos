package com.edu.ifpb.pps.services;

import com.edu.ifpb.pps.descontos.DescontoConvenioHandler;
import com.edu.ifpb.pps.descontos.DescontoIdososHandler;
import com.edu.ifpb.pps.descontos.DescontosBaseHandler;
import com.edu.ifpb.pps.exames.Exame;

public class ServicoFinanceiro {
    private DescontosBaseHandler descontoIdosos;
    private DescontosBaseHandler descontoConvenio;

    public ServicoFinanceiro() {
        this.descontoIdosos = new DescontoIdososHandler();
        this.descontoConvenio = new DescontoConvenioHandler();
        descontoIdosos.setNext(descontoConvenio);
    }

    public Double calcularPrecoExame(Exame exame) {
        System.out.println("Calculando preço do exame...");
        double preco = exame.getValor() * (1 - this.descontoIdosos.aplicarDesconto(exame));
        return preco;
    }
}
