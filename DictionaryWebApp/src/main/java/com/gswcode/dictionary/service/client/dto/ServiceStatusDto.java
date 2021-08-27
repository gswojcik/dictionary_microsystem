package com.gswcode.dictionary.service.client.dto;

import java.util.Objects;

public class ServiceStatusDto {
    private long requestId;
    private String currDateTime;
    private boolean success;
    private String message;

    public ServiceStatusDto() {
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getCurrDateTime() {
        return currDateTime;
    }

    public void setCurrDateTime(String currDateTime) {
        this.currDateTime = currDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    @Override
    public String toString() {
        return "ServiceStatusDto{" + "requestId=" + requestId + ", currDateTime=" + currDateTime + ", success=" + success + ", message=" + message + '}';
    }

}
