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
import com.edu.ifpb.pps.exames.composite.GrupoIndicadores;
import com.edu.ifpb.pps.exames.composite.Indicador;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.edu.ifpb.pps.exames.impl.Sanguineo;
import com.edu.ifpb.pps.models.Medico;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.utils.Utils;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

public class HTMLFormatterVisitor extends VisitorFormatter {

    private final TemplateEngine templateEngine;
    private final String PASTA_DESTINO = getPathDestino("html");

    public HTMLFormatterVisitor() {
        // Configuração do Template Engine do Thymeleaf
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
            System.out.println("Arquivo salvo em: " + caminho);
            System.out.println("-------------------------------------------------");

        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar o arquivo HTML.");
            e.printStackTrace();
        }
    }

    
    @Override
    public void gerarLaudo(Sanguineo sanguineo) {
        Context context = new Context();

        // Passa o objeto Exame completo para o cabeçalho do laudo
        context.setVariable("exame", sanguineo);
        context.setVariable("titulo", "Laudo Sanguineo Completo");
        context.setVariable("dataGeracao", new java.util.Date());

        // Passa a lista de itens (a estrutura Composite) para o template
        context.setVariable("itensSanguineos", sanguineo.getItensSanguineos());

        // Informa ao template os nomes das classes para fazer a verificação de tipo
        context.setVariable("GrupoResultados", GrupoIndicadores.class);
        context.setVariable("ItemResultado", Indicador.class);

        String conteudo = templateEngine.process("laudos/sanguineo.html", context);
        System.out.println("aui 3");
        this.createHTML(Path.of(PASTA_DESTINO + "laudo_sanguineo.html"), conteudo);
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
        this.createHTML(Path.of(PASTA_DESTINO + "laudo_ressonancia.html"), conteudo);

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
        this.createHTML(Path.of(PASTA_DESTINO + "laudo_raiox.html"), conteudo);
    }
    
    // Teste
    public static void main(String[] args) {
        // Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851", LocalDate.of(2005, 06, 8));
        // Medico solicitante = new Medico("João da Silva", "7653");
        // Medico laudista = new Medico("João Laudista", "12345");

        // Exame ressonancia = new Ressonancia(paciente, solicitante, laudista, "TAMBAÚ", "UNIMED", "JOELHO", "Ressonância no joelho", 3.0, List.of(
        //     "./src/main/resources/imagens/maca.jpg",
        //     "./src/main/resources/imagens/banana.jpg",
        //     "./src/main/resources/imagens/maca.jpg"
        // ), false, true);

        HTMLFormatterVisitor visitor = new HTMLFormatterVisitor();
        // ressonancia.gerarLaudo(visitor);
        // ressonancia.gerarLaudo(new TXTFormatterVisitor());
        // ressonancia.gerarLaudo(new PDFFormatterVisitor());

        // Exame raiox = new RaioX(paciente, solicitante, laudista, "Bancários", "Roseane Doris", "PULMÃO", "Raio X do pulmão", "/home/luiz/pps/projeto/src/main/resources/imagens/banana.jpg", true);
        // Exame raiox = new RaioX(paciente, solicitante, laudista, "Bancários", "Roseane Doris", "PULMÃO", "Raio X do pulmão", "./src/main/resources/imagens/maca.jpg", true);
        // raiox.gerarLaudo(visitor);
        // raiox.gerarLaudo(new TXTFormatterVisitor());

        // raiox.gerarLaudo(new PDFFormatterVisitor());

        // Sanguineo sanguineo = new Sanguineo(paciente, solicitante, laudista, "TAMBAÚ", "UNIMED");

        Indicador glicose = new Indicador("glicose", "83", "mg/DL",List.of(
            "Normal: 60 a 99 ",
            "Hipoglicemia: < 60",
            "Intolerante: 100 a 125",
            "Diabetes: Acima de 125"
        ), List.of(
            "-"
        )
        
        );

        // Indicador creatinina = new Indicador("creatinina", "1,02", "mg/DL", List.of(
        //     """
        // Adultos (Homens): 0,50 a 1,30
        // Adultos (Mulheres): 0,40 a 1,10
        // Idosos (>60 anos): 0,30 a 1,20
        // Crianças: 0,20 a 0,50"""
        // ));
        Indicador creatinina = new Indicador("creatinina", "1,02", "mg/DL", List.of(
            "Adultos (Homens): 0,50 a 1,30",
            "Adultos (Mulheres): 0,40 a 1,10",
            "Idosos (>60 anos): 0,30 a 1,20",
            "Crianças: 0,20 a 0,50"
        ), 
            List.of(
                "vH  > 1.5 (alerta)",
                "vM - > 1,2 (alerta)",
                "vIdoso > 1.5 (risco elevado)" ,
                "vCrianças > 0,8  (investigar)"                              
            )
        );

        GrupoIndicadores hemografia = new GrupoIndicadores("hemografia");
        hemografia.adicionar(creatinina);
        hemografia.adicionar(glicose);

        
        // sanguineo.adicionarItem(hemografia);
        // // sanguineo.gerarLaudo(visitor);
        // // sanguineo.gerarLaudo(new TXTFormatterVisitor());
        // sanguineo.gerarLaudo(new PDFFormatterVisitor());

        // 2. Criar os grupos e itens (a estrutura composite)
        // GrupoIndicadores eritrograma = new GrupoIndicadores("ERITROGRAMA");
        // eritrograma.adicionar(new Indicador("Hemácias", "4.400", "milhões/mm³", "4.1 - 5.1"));
        // eritrograma.adicionar(new Indicador("Hemoglobina", "12.0", "g/dL", "11.5 - 14.5"));
        // eritrograma.adicionar(new Indicador("Hematócrito", "35.8", "%", "33 - 41"));

        // GrupoIndicadores leucograma = new GrupoIndicadores("LEUCOGRAMA");
        // leucograma.adicionar(new Indicador("Leucócitos", "6.500", "/mm³", "4.000 - 11.000"));

        // // 3. Adicionar os grupos ao sanguineo
        // sanguineo.adicionarItem(eritrograma);
        // sanguineo.adicionarItem(leucograma);

        // sanguineo.gerarLaudo(visitor);


    }
}
