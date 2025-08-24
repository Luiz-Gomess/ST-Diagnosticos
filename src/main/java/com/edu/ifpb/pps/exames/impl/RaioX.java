package com.edu.ifpb.pps.exames.impl;

import com.edu.ifpb.pps.enums.Prioridade;
import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.VisitorFormatter;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;

public class RaioX extends Exame{

    private String orgaoAvaliado;
    private String laudoDescritivo;
    private String caminhoImagem;
    private boolean assinaturaRadiologista;

    public RaioX(Double valor,Paciente paciente, Medico medicoSolicitante, Medico medicoLaudista, String localColeta,String convenio, String orgaoAvaliado, String laudoDescritivo, String caminho, boolean assinaturaRadiologista, Prioridade prioridade) {
        super(valor, paciente, medicoSolicitante, medicoLaudista, localColeta, convenio, prioridade);
        this.orgaoAvaliado = orgaoAvaliado;
        this.laudoDescritivo = laudoDescritivo;
        this.caminhoImagem = caminho;
        this.assinaturaRadiologista = assinaturaRadiologista;
    }

    @Override
    public void gerarLaudo(VisitorFormatter visitor) {
        visitor.gerarLaudo(this);  // Aqui é o dispatch para visit(Hemograma)
    }

    public String getOrgaoAvaliado() {
        return orgaoAvaliado;
    }

    public void setOrgaoAvaliado(String orgaoAvaliado) {
        this.orgaoAvaliado = orgaoAvaliado;
    }

    public String getLaudoDescritivo() {
        return laudoDescritivo;
    }

    public void setLaudoDescritivo(String laudoDescritivo) {
        this.laudoDescritivo = laudoDescritivo;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public boolean getAssinaturaRadiologista() {
        return assinaturaRadiologista;
    }

    public void setAssinaturaRadiologista(boolean assinaturaRadiologista) {
        this.assinaturaRadiologista = assinaturaRadiologista;
    }
    
}
