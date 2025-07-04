package com.edu.ifpb.pps.exames.impl;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.GeradorDeLaudo;
import com.edu.ifpb.pps.models.CorpoExame;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Hemograma extends Exame{

    private Integer hemoglobinas;
    private Integer leucocitos;
    private Integer plaquetas;

    public Hemograma(GeradorDeLaudo gerador, Integer hemoglobinas, Integer leucocitos, Integer plaquetas) {
        super(gerador);
        this.hemoglobinas = hemoglobinas;
        this.leucocitos = leucocitos;
        this.plaquetas = plaquetas;
    }

    @Override
    public CorpoExame getDescricaoExame() {
        CorpoExame corpoExame = new CorpoExame();
        corpoExame.titulo = "Exame de Hemograma";
        corpoExame.descricao = "O hemograma é um exame de sangue que faz ...";
        corpoExame.listaItens.add("Qtde de hemoglobinas: " + hemoglobinas);
        corpoExame.listaItens.add("Qtde de leucocitos: " + leucocitos);


        return corpoExame;
    }

    
    
}
