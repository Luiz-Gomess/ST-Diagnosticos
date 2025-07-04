package com.edu.ifpb.pps.laudos.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.laudos.GeradorDeLaudo;
import com.edu.ifpb.pps.models.CorpoExame;

public class GeradorHTML implements GeradorDeLaudo {

    @Override
    public String gerarLaudo(Exame exame) {
        
        CorpoExame dados = exame.getDescricaoExame();

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/"); 
        resolver.setSuffix(".html");       
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode("HTML"); 

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        Context context = new Context();
        context.setVariable("imagens", dados.imagens); 
        context.setVariable("titulo", dados.titulo); 
        context.setVariable("descricao", dados.descricao); 
        context.setVariable("listaItems", dados.listaItens); 

        String htmlGerado = engine.process("exame", context);

        try (PrintWriter writer = new PrintWriter(new FileWriter("exame_preenchido.html"))) {
            writer.println(htmlGerado);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }
    
}
