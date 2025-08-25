package com.edu.ifpb.pps.validacoes.ressonancia;

import java.util.List;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.validacoes.IValidador;

public class ValidadorRessonanciaHandler implements IValidador<Ressonancia>{

    private IValidador<Ressonancia> proximo;

    @Override
    public void setNext(IValidador<Ressonancia> next) {
        this.proximo = next;
    }

    @Override
    public String validar(Ressonancia exame) {
        if (proximo != null) {
            return proximo.validar(exame);
        }
        return "Exame de Ressonância validado!";
    }


    public static void main(String[] args) {
        Ressonancia ressonancia = new Ressonancia(20.0, null, null, null, "TAMBAÚ", "UNIMED", "JOELHO", "Ressonância no joelho", 3.0, List.of(
                "/home/luiz/pps/projeto/src/main/resources/imagens/banana.jpg",
                "/home/luiz/pps/projeto/src/main/resources/imagens/maca.jpg"
        ), false, true, Prioridade.URGENTE);

        ValidadorRessonanciaHandler descricao = new DescricaoVaziaValidador();
        ValidadorRessonanciaHandler implantes = new ImplantesValidador();
        ValidadorRessonanciaHandler assinatura = new AssinaturaVaziaValidador();
        ValidadorRessonanciaHandler protocolo = new ProtocoloExameValidador();

        descricao.setNext(implantes);
        implantes.setNext(assinatura);
        assinatura.setNext(protocolo);
        System.out.println(descricao.validar(ressonancia));
    }
    
}
