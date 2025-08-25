package com.edu.ifpb.pps.loadData;

import org.apache.commons.csv.CSVRecord;

public interface CSVMappingStrategy<T> {
    
    T mapear(CSVRecord record);
}
