package com.edu.ifpb.pps.geradorDeLaudo.impl;

import com.edu.ifpb.pps.geradorDeLaudo.GeradorDeLaudo;

public class GeradorHTML implements GeradorDeLaudo{

    @Override
    public String gerar(String cabecalho, String corpo, String rodape) {
        return String.format(
        """
        <html>
            <head>
                <title> %s </title>
            </head> 
            <body> 
                <h1>  %s  </h1> 
                <p>  %s  </p> 
            <footer>  %s  </footer> 
            </body> 
        </html>""",cabecalho,  cabecalho, corpo, rodape)
        ;
    }

}
