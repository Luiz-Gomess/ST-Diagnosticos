package com.edu.ifpb.pps.loadData;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVDataLoader<T> {

    
    private CSVMappingStrategy<T> strategy;
    
    public CSVDataLoader (CSVMappingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void setStrategy (CSVMappingStrategy<T> newStrategy) {
        this.strategy = newStrategy;
    }

    public List<T> carregar(String path) {
        
        List<T> resultado = new ArrayList<>();
        CSVFormat format = CSVFormat.DEFAULT
        .withFirstRecordAsHeader() 
        .withTrim();

            try (Reader reader = new FileReader(path);
        
                CSVParser csvParser = new CSVParser(reader, format)) {

                for (CSVRecord csvRecord : csvParser) {
                    resultado.add(strategy.mapear(csvRecord));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        return resultado;
    }
}
