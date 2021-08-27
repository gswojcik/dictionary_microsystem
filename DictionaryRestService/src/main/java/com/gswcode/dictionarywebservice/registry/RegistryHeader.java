package com.gswcode.dictionarywebservice.registry;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;

@Data
public final class RegistryHeader {

    private final static AtomicInteger index = new AtomicInteger(0);

    private int id;
    private String ipAddr;
    private long dictionaryId;
    private long itemId;
    private String usedEndpoint;
    private ZonedDateTime zonedDateTime;

    {
        this.zonedDateTime = ZonedDateTime.now();
        this.id = index.incrementAndGet();
    }

    public RegistryHeader(HttpServletRequest request, long dictionaryId, long itemId) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        this.ipAddr = ipAddress;
        this.dictionaryId = dictionaryId;
        this.itemId = itemId;
        this.usedEndpoint = request.getServletPath();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
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
        final RegistryHeader other = (RegistryHeader) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
