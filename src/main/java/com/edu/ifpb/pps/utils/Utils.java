package com.edu.ifpb.pps.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public abstract class Utils {

    public static List<String> converterImagemParaBase64(List<String> caminhosDasImagens) {

        List<String> imagensEmBase64 = new ArrayList<>();

        if (caminhosDasImagens != null) {
            for (String caminho : caminhosDasImagens) {
                String base64;
                try {
                    File arquivo = new File(caminho);
                    if (arquivo.exists()) {
                        byte[] bytesDoArquivo = Files.readAllBytes(arquivo.toPath());
                        base64 =  Base64.getEncoder().encodeToString(bytesDoArquivo);
                        imagensEmBase64.add(base64);
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo de imagem: " + caminho);
                    e.printStackTrace();
                }
            }
        }

        return imagensEmBase64;


    }
    
}
