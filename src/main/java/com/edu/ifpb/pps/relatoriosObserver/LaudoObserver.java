package com.edu.ifpb.pps.relatoriosObserver;

import com.edu.ifpb.pps.exames.Exame;

public interface LaudoObserver {
    void onLaudoGerado(Exame exame);
}