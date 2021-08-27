package com.gswcode.dictionarywebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DictionaryDto {
    private long id;
    private long masterDictionaryId;
    private String name;
    private String description;
    private String author;
    private String creationAt;
    private boolean isActive;
    private int totalItems;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DictionaryDto other = (DictionaryDto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
