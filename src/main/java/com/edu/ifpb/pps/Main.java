package com.edu.ifpb.pps;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.Hemograma;
import com.edu.ifpb.pps.exames.Ultrassonografia;
import com.edu.ifpb.pps.gerador.GeradorHTML;
import com.edu.ifpb.pps.gerador.GeradorPDF;
import com.edu.ifpb.pps.gerador.GeradorTXT;

public class Main {
    public static void main(String[] args) {
        Exame ultrassonografia = new Ultrassonografia();

        ultrassonografia.medico = new Medico("Dr. Luizinho", "1234556");
        ultrassonografia.gerador = new GeradorHTML();
        
        System.out.println();
        System.out.println(ultrassonografia.gerarLaudo("Corpo da Ultrassonografia"));
        System.out.println();

        Exame hemograma = new Hemograma();

        hemograma.medico = new Medico("Dr. Luizinho", "1234556");
        hemograma.gerador = new GeradorHTML();
        
        System.out.println();
        System.out.println(hemograma.gerarLaudo("Corpo da Ultrassonografia"));
        System.out.println();

        hemograma.gerador = new GeradorPDF();
        
        System.out.println();
        System.out.println(hemograma.gerarLaudo("Corpo da Ultrassonografia"));
        System.out.println();

        hemograma.gerador = new GeradorTXT();
        
        System.out.println();
        System.out.println(hemograma.gerarLaudo("Corpo da Ultrassonografia"));
        System.out.println();
    }
}