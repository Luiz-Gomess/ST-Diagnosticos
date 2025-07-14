package com.edu.ifpb.pps.loadData;

import java.time.LocalDate;

import org.apache.commons.csv.CSVRecord;

import com.edu.ifpb.pps.enums.Genero;
import com.edu.ifpb.pps.models.Paciente;

public class PacienteMappingStrategy implements CSVMappingStrategy<Paciente>{

    @Override
    public Paciente mapear(CSVRecord record) {

        Paciente paciente = new Paciente();
        try {
            paciente.setCpf(record.get("cpf"));
            paciente.setDataNasc(LocalDate.parse(record.get("dataNasc")));
            paciente.setEmail(record.get("email"));
            paciente.setGenero(Genero.valueOf(record.get("genero")));
            paciente.setId(Integer.parseInt(record.get("id")));
            paciente.setNome(record.get("nome"));
            paciente.setTelefone(record.get("telefone"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return paciente;
    }
    
}
