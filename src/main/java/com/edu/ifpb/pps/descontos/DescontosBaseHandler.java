package com.edu.ifpb.pps.descontos;

import java.time.LocalDate;
import java.util.List;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.enums.Prioridade;

public abstract class DescontosBaseHandler {

    private DescontosBaseHandler proximo;
    public static double descontoDinamico;
    private double descontoMaximo = 0.30;
    protected double descontoIndividual;


    public void setNext(DescontosBaseHandler next) {
        this.proximo = next;
    }

    public void setDescontoIndividual (double newDesconto){
        this.descontoIndividual = newDesconto;
    }

    public double aplicarDesconto(Exame exame) {
        if (descontoDinamico >= descontoMaximo) {
            return descontoDinamico;
        }
        else if (proximo != null) {
            return proximo.aplicarDesconto(exame);
        } 

        return descontoDinamico;
    }

    public static void main(String[] args) {
        Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851", LocalDate.of(1963, 06, 8));
        Medico solicitante = new Medico("João da Silva", "7653");
        Medico laudista = new Medico("João Laudista", "12345");

        Exame ressonancia = new Ressonancia(20.0, paciente, solicitante, laudista, "TAMBAÚ", "UNIMED", "JOELHO", "Ressonância no joelho", 3.0, List.of(
            "/home/luiz/pps/projeto/src/main/resources/imagens/banana.jpg",
            "/home/luiz/pps/projeto/src/main/resources/imagens/maca.jpg"
        ), false, true, Prioridade.URGENTE);

        DescontoIdososHandler idosos = new DescontoIdososHandler();
        idosos.setNext(new DescontoConvenioHandler());

        System.out.println();
        System.out.println(idosos.aplicarDesconto(ressonancia));
    }
}
