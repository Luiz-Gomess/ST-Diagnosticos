package com.edu.ifpb.pps.laudos;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.edu.ifpb.pps.exames.Exame;
import com.edu.ifpb.pps.exames.impl.Sanguineo;
import com.edu.ifpb.pps.exames.impl.RaioX;
import com.edu.ifpb.pps.exames.impl.Ressonancia;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class PDFFormatterVisitor extends VisitorFormatter{

    private final String PASTA_DESTINO = getPathDestino("pdf");
    private PdfWriter writer;
    private PdfDocument pdf;
    private Document document; 

    private int tamanhoFonte = 9;
    
    private void config (String filename) {
        try {
            this.writer = new PdfWriter(PASTA_DESTINO + filename);
            this.pdf = new PdfDocument(writer);
            this.document = new Document(pdf, PageSize.A4);
            document.setMargins(36, 36, 36, 36);
            document.setFontSize(tamanhoFonte);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void gerarLaudo(Sanguineo hemograma) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarLaudo'");
    }

    @Override
    public void gerarLaudo(Ressonancia ressonancia) {
        config("laudo_ressonancia.pdf");

        gerarCabecalho(ressonancia);
        
        Paragraph tituloRessonancia = new Paragraph()
                .add(new Text("Laudo de Ressonância Magnética: ").setBold().setFontSize(tamanhoFonte))
                .add(new Text(ressonancia.getRegiaoCorpo()).setBold().setFontSize(tamanhoFonte).setFontColor(ColorConstants.BLUE))
                .setMarginTop(25);
        document.add(tituloRessonancia);

        // --- Achados do Exame ---
        document.add(new Paragraph("Achados do Exame:").setBold().setMarginTop(20));
        document.add(new Paragraph(ressonancia.getDescricao()).setMarginLeft(20));

        // --- Detalhes Técnicos ---
        document.add(new Paragraph("Detalhes Técnicos:").setBold().setMarginTop(20));

        // --- Contraste utilizado ---
        Div detalhesDiv = new Div()
                .setBackgroundColor(new DeviceGray(0.95f)) // Um cinza bem claro
                .setBorder(new SolidBorder(new DeviceGray(0.85f), 1))
                .setPadding(15)
                .setMarginTop(10)
                .setBorderRadius(new BorderRadius(5));

        Paragraph contraste = new Paragraph()
                .add(new Text("Contraste Utilizado:\n").setBold())
                .add(new Text(Double.toString(ressonancia.getContrasteUsado())));
        
        detalhesDiv.add(contraste);
        document.add(detalhesDiv);

        // --- Imagens ---
        document.add(new Paragraph("Imagens de Referência:").setBold().setMarginTop(20));

        int numeroDeColunas = 3;
        Table tabelaImagens = new Table(UnitValue.createPercentArray(numeroDeColunas)).useAllAvailableWidth();
        tabelaImagens.setBorder(Border.NO_BORDER); // Remove todas as bordas da tabela

        for (int i = 0; i < ressonancia.getImagens().size(); i++) {
            String caminho = ressonancia.getImagens().get(i);
            
            // Carrega a imagem do arquivo
            Image img;
            try {
                img = new Image(ImageDataFactory.create(caminho));
                String legenda = "Imagem " + (i + 1);
                Cell cell = createImageCell(img, legenda);
                tabelaImagens.addCell(cell);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        document.add(tabelaImagens);
        gerarRodape(ressonancia);
        document.close();
        
    }

    @Override
    public void gerarLaudo(RaioX raiox) {

        config("laudo_raioX.pdf");

        gerarCabecalho(raiox);
                // --- Título do Laudo ---
        Paragraph tituloLaudo = new Paragraph()
            .add(new Text("Laudo de Raio-X: ").setBold().setFontSize(tamanhoFonte))
            .add(new Text(raiox.getOrgaoAvaliado()).setBold().setFontSize(tamanhoFonte).setFontColor(ColorConstants.BLUE))
            .setMarginTop(20);
        document.add(tituloLaudo);
        
        // --- Laudo Descritivo ---
        document.add(new Paragraph("Laudo Descritivo:").setBold().setMarginTop(20));
        document.add(new Paragraph(raiox.getLaudoDescritivo()).setMarginLeft(20));

        // --- Imagem (exemplo, requer carregar a imagem) ---
        ImageData data;
        String pathImagem = raiox.getCaminhoImagem();
        try {
            data = ImageDataFactory.create(pathImagem);
            Image img = new Image(data).setWidth(150).setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(new Paragraph("Imagem de Referência:").setBold().setMarginTop(20));
            document.add(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        gerarRodape(raiox);
        document.close();
        
    }

    // Cabeçalho 
    private void gerarCabecalho(Exame exame) {
        document.add(new Paragraph("ST Diagnósticos")).setFontSize(tamanhoFonte).setBold();
        document.add(new Paragraph("Sistema de Controle de Exames Médicos")).setFontSize(tamanhoFonte);
        document.add(new Paragraph("").setMarginTop(10).setBorderBottom(new SolidBorder(ColorConstants.GRAY, 1)));

        Table infoTable = new Table(UnitValue.createPercentArray(new float[]{1, 4})).useAllAvailableWidth();
        infoTable.setMarginTop(20);
        
        infoTable.addCell(createCell("Id Exame:", true));
        // infoTable.addCell(createCell(Integer.toString(exame.getId()), false));
        infoTable.addCell(createCell("23", false));
        infoTable.addCell(createCell("Paciente:", true));
        infoTable.addCell(createCell(exame.getPaciente().getNome(), false));
        infoTable.addCell(createCell("Dr(a):", true));
        infoTable.addCell(createCell(exame.getMedicoSolicitante().getNome(), false));
        infoTable.addCell(createCell("Coleta:", true));
        infoTable.addCell(createCell(exame.getLocalColeta(), false));
        infoTable.addCell(createCell("Convênio:", true));
        infoTable.addCell(createCell(exame.getConvenio(), false));
        infoTable.addCell(createCell("D.N.:", true));
        infoTable.addCell(createCell(exame.getPaciente().getDataNasc().toString(), false));

        document.add(infoTable);
    }

    private void gerarRodape (Exame exame) {
        document.add(new Paragraph("Laudo emitido conforme análise da imagem adquirida.")
            .setTextAlignment(TextAlignment.CENTER)
            .setMarginTop(40)
            .setPaddingTop(10)
            .setBorderTop(new SolidBorder(ColorConstants.GRAY, 1)));

        Paragraph assinatura = new Paragraph()
            .add(String.format("Dr(a): %s\n", exame.getMedicoLaudista().getNome()))
            .add(String.format("CRM: %s", exame.getMedicoLaudista().getCrm()))
            .setTextAlignment(TextAlignment.RIGHT)
            .setMarginTop(30);
        document.add(assinatura);
    }


    // Método auxiliar para criar as células das tabelas
    private static Cell createCell(String content, boolean isHeader) {
        Cell cell = new Cell().add(new Paragraph(content));
        cell.setPadding(5);
        cell.setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f));
        if (isHeader) {
            cell.setBold();
        }
        return cell;
    }

    private static Cell createImageCell(Image img, String legenda) {

        Cell cell = new Cell();
        cell.setBorder(Border.NO_BORDER);   
        cell.setPadding(5);     

        // Redimensiona a imagem para ocupar 100% da largura da célula
        img.setWidth(UnitValue.createPercentValue(50));

        // Cria o parágrafo da legenda
        Paragraph pLegenda = new Paragraph(legenda)
                .setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER);

        // Adiciona a imagem e a legenda à célula
        cell.add(img);
        cell.add(pLegenda);
        
        return cell;
    }
    
}
