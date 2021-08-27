package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.registry.RegistryHeader;
import com.gswcode.dictionarywebservice.registry.RegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("metadata/registry")
public class ControllerRegistry {

    private final static AtomicInteger REQUEST_NO = new AtomicInteger(0);
    private final Logger logger = LoggerFactory.getLogger(DictionaryController.class);
    private final RegistryService registryService;

    public ControllerRegistry(RegistryService registryService) {
        this.registryService = registryService;
    }

    @GetMapping("/show")
    @ResponseStatus(HttpStatus.OK)
    public List<RegistryHeader> show() {
        logger.debug("Preparing registry events...");
        return registryService.getRegistry();
    }

    public static long getNextRegisterId() {
        return REQUEST_NO.incrementAndGet();
    }

}
