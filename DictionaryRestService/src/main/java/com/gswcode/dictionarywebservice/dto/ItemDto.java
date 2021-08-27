package com.gswcode.dictionarywebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private Long dictionaryId;
    private String termName;
    private String termDescription;
    private boolean isActive;
    private Long masterItemId;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final ItemDto other = (ItemDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemDto{" + "id=" + id + ", dictionaryId=" + dictionaryId + ", termName=" + termName + ", termDescription=" + termDescription + ", isActive=" + isActive + ", masterItemId=" + masterItemId + '}';
    }

}
