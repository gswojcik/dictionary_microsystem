package com.gswcode.dictionarywebservice.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Service
public class RegistryService {

    private final Logger logger = LoggerFactory.getLogger(RegistryService.class);

    private final List<RegistryHeader> list = new LinkedList<>();

    public void add(HttpServletRequest request, long dictionaryId, long itemId) {
        logger.debug("Creating registry for dictionary: " + dictionaryId + ", item: " + itemId);
        logger.debug("Request object: " + request);
        list.add(0, new RegistryHeader(request, dictionaryId, itemId));
    }

    public List<RegistryHeader> getRegistry() {
        return list;
    }

}
