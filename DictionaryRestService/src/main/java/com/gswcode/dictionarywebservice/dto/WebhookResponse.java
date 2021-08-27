package com.gswcode.dictionarywebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebhookResponse {
    private long filesToProcess;
}
