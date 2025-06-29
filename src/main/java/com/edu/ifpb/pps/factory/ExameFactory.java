package com.edu.ifpb.pps.factory;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.Ultrassonografia;

public abstract class ExameFactory {
    
    public static Exame create (String tipo) {
        Exame exame = switch (tipo) {
            case "ultrassonografia" -> new Ultrassonografia();
            case "hemograma"-> new Hemograma();
            default -> null;
        };

        return exame;
    }
}
