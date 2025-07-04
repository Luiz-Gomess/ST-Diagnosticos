package com.edu.ifpb.pps.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CorpoExame {
    public String titulo;
    public String descricao;
    public List<String> listaItens = new ArrayList<>();
    public List<Map<String, String>> imagens = new ArrayList<>();
}
