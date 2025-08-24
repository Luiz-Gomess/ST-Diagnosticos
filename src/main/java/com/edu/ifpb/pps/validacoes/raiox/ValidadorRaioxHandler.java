package com.edu.ifpb.pps.validacoes.raiox;

import java.time.LocalDate;

import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.validacoes.IValidador;

public abstract class ValidadorRaioxHandler implements IValidador<RaioX> {

    private IValidador<RaioX> proximo;

    @Override
    public void setNext(IValidador<RaioX> next) {
        this.proximo = next;
    }

    @Override
    public String validar(RaioX exame) {
        if (proximo != null) {
            return proximo.validar(exame);

        } 
            // System.out.println("Exame valdiado");
        return "Exame de Raiox validado.";
        
    }

    public static void main(String[] args) {

        // Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851", LocalDate.of(2005, 06, 8));
        // Medico solicitante = new Medico("João da Silva", "7653");
        // Medico laudista = new Medico("João Laudista", "12345");

        // RaioX raiox = new RaioX(paciente, solicitante, laudista, "Bancários", "Roseane Doris", "PULMÃO", "Raio X do pulmão",
        //  "", true
        // );

        // ValidadorRaioxHandler assinatura = new AssinaturaValidador();
        // ValidadorRaioxHandler imagem = new ContemImagemValidador();
        // ValidadorRaioxHandler laudo = new DescricaoRaioxValidador();

        // assinatura.setNext(imagem);
        // imagem.setNext(laudo);
        // System.out.println(assinatura.validar(raiox));
    }
    
}
