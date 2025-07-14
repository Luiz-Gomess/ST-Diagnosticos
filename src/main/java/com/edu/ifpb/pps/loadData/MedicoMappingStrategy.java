package com.edu.ifpb.pps.loadData;

import org.apache.commons.csv.CSVRecord;

import com.edu.ifpb.pps.models.Medico;

public class MedicoMappingStrategy implements CSVMappingStrategy<Medico>{

    @Override
    public Medico mapear(CSVRecord record) {
        Medico medico = new Medico();

        try {
            medico.setCrm(record.get("crm"));
            medico.setNome(record.get("nome"));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return medico;
    }
    
}
