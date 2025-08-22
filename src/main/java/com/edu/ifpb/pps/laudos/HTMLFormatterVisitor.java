package com.edu.ifpb.pps.laudos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Hemograma;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.utils.Utils;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

public class HTMLFormatterVisitor implements VisitorFormatter {

    private final TemplateEngine templateEngine;

    public HTMLFormatterVisitor() {

            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            
            resolver.setPrefix("templates/");
            resolver.setSuffix(".html");
            resolver.setCharacterEncoding("UTF-8");

            this.templateEngine = new TemplateEngine();
            this.templateEngine.setTemplateResolver(resolver);

            this.templateEngine.addDialect(new LayoutDialect());

    }

    private void createHTML(Path caminho, String conteudo) {
        try {
            // Escreve todo o conteúdo da string HTML no arquivo.
            // Se o arquivo já existir, ele será sobrescrito.
            Files.writeString(caminho, conteudo);

            System.out.println("-------------------------------------------------");
            System.out.println("✅ Laudo gerado com sucesso!");
            System.out.println("Arquivo salvo em: " + caminho.toAbsolutePath());
            System.out.println("-------------------------------------------------");

        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar o arquivo HTML.");
            e.printStackTrace();
        }
    }

    @Override
    public void gerarLaudo(Hemograma hemograma) {

    }

    @Override
    public void gerarLaudo(Ressonancia ressonancia) {

        Context context = new Context();
        List<String> imagensEmBase64 = Utils.converterImagemParaBase64(ressonancia.getImagens());

        context.setVariable("exame", ressonancia);
        context.setVariable("titulo", "Laudo de Ressonância Magnética");
        context.setVariable("dataGeracao", new java.util.Date());
        
        // Envia a LISTA DE STRINGS BASE64 para o template, não mais a lista de caminhos
        context.setVariable("imagensBase64", imagensEmBase64); 

        String conteudo = templateEngine.process("laudos/ressonancia.html", context);
        this.createHTML(Path.of("laudo_ressonancia.html"), conteudo);

    }

    @Override
    public void gerarLaudo(RaioX raiox) {

        Context context = new Context();
        List<String> imagensEmBase64 = Utils.converterImagemParaBase64(List.of(raiox.getCaminhoImagem()));

        context.setVariable("exame", raiox);
        context.setVariable("titulo", "Laudo de Raio X");
        context.setVariable("dataGeracao", new java.util.Date());
        
        // Envia a LISTA DE STRINGS BASE64 para o template, não mais a lista de caminhos
        context.setVariable("imagemBase64", imagensEmBase64.get(0)); 


        String conteudo = templateEngine.process("laudos/raiox.html", context);
        this.createHTML(Path.of("laudo_raiox.html"), conteudo);
    }
    
    public static void main(String[] args) {
        Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851", LocalDate.of(2005, 06, 8));
        Medico solicitante = new Medico("João da Silva", "7653");
        Medico laudista = new Medico("João Laudista", "12345");

        Exame ressonancia = new Ressonancia(paciente, solicitante, laudista, "TAMBAÚ", "UNIMED", "JOELHO", "Ressonância no joelho", 3.0, List.of(
            "/home/luiz/pps/projeto/src/main/resources/imagens/banana.jpg",
            "/home/luiz/pps/projeto/src/main/resources/imagens/maca.jpg"
        ), false);

        HTMLFormatterVisitor visitor = new HTMLFormatterVisitor();
        ressonancia.gerarLaudo(visitor);

        Exame raiox = new RaioX(paciente, solicitante, laudista, "Bancários", "Roseane Doris", "PULMÃO", "Raio X do pulmão", "/home/luiz/pps/projeto/src/main/resources/imagens/banana.jpg");
        raiox.gerarLaudo(visitor);
    }
}
