package com.edu.ifpb.pps.validacoes;

public interface IValidador<EXAME> {
    
    void setNext(IValidador<EXAME> next);
    String validar (EXAME exame);

}
