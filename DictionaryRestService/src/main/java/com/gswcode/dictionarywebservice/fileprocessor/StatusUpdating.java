package com.gswcode.dictionarywebservice.fileprocessor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class StatusUpdating {
    private final String type;
    private final long typeId;
    private final int isActive;
}
