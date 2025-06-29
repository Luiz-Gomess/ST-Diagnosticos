package com.edu.ifpb.pps.exames;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.geradorDeLaudo.GeradorDeLaudo;
import com.edu.ifpb.pps.modelos.Medico;
import com.edu.ifpb.pps.modelos.Paciente;

import lombok.Data;

@Data
public abstract class Exame {
    
    private int id;
    private String nomeMedicoSolicitante;
    private Medico medico;
    private Paciente paciente;
    private Prioridade prioridade;
    private double preco;
    private double desconto;
    
    protected GeradorDeLaudo gerador;

    public void adicionarDesconto(double desconto) {
        this.desconto += desconto;
    }

    public double getPrecoFinal(){
        return this.preco * (1 - this.desconto);
    }

    public String gerarRodape() {
        return medico.getNome() + " " +medico.getCrm();
    }

    public abstract String gerarLaudo(String corpo);

    
}