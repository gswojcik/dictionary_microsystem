package com.gswcode.dictionarywebservice.fileprocessor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TerminationStatus {
    private String terminatedFileName;
    private int totalLines;
    private int linesProcessed;
    private int linesRemaining;
    private int remainingFiles;
}
