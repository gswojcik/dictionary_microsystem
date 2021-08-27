package com.gswcode.dictionarywebservice.dto;

import com.gswcode.dictionarywebservice.util.Common;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class ServiceStatusDto {
    private long requestId;
    private String currDateTime;
    private boolean success;
    private String message;

    public ServiceStatusDto() {
        this.currDateTime = LocalDateTime.now().format(Common.LOCAL_DT_FORMATTER);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.requestId ^ (this.requestId >>> 32));
        hash = 97 * hash + Objects.hashCode(this.currDateTime);
        hash = 97 * hash + Objects.hashCode(this.message);
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
        final ServiceStatusDto other = (ServiceStatusDto) obj;
        if (this.requestId != other.requestId) {
            return false;
        }
        if (!Objects.equals(this.currDateTime, other.currDateTime)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

}
