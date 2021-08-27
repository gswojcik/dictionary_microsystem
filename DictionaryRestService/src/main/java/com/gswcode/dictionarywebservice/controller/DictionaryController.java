package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.dto.DictionaryDto;
import com.gswcode.dictionarywebservice.dto.ServiceStatusDto;
import com.gswcode.dictionarywebservice.mapper.DictionaryMapper;
import com.gswcode.dictionarywebservice.service.DictionaryService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("dictionary")
public class DictionaryController {

    private final Logger logger = LoggerFactory.getLogger(DictionaryController.class);
    private final DictionaryService service;
    private final DictionaryMapper mapper;

    public DictionaryController(DictionaryService service, DictionaryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/getList")
    @ResponseStatus(HttpStatus.OK)
    public List<DictionaryDto> getList() {
        List<DictConf> dictConfList = service.getDictionaryList();
        logger.debug("Objects from db: " + dictConfList.size());
        List<DictionaryDto> dtos = mapper.mapToListDto(dictConfList);
        logger.debug("Objects mapped: " + dictConfList.size());
        return dtos;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceStatusDto add(@RequestBody DictionaryDto dictionary) {
        try {
            logger.debug("Object to add received: " + dictionary);
            ServiceStatusDto status = service.save(mapper.mapToDomain(dictionary));
            status.setRequestId(ControllerRegistry.getNextRegisterId());
            return status;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid request", ex);
        }
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ServiceStatusDto update(@RequestBody DictionaryDto dictionary) {
        try {
            logger.debug("Object to update received: " + dictionary);
            ServiceStatusDto status = service.update(mapper.mapToDomain(dictionary));
            status.setRequestId(ControllerRegistry.getNextRegisterId());
            return status;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid request", ex);
        }
    }

    @PutMapping("/deactivate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ServiceStatusDto deactivate(@RequestParam("dictionaryId") long dictionaryId) {
        logger.debug("Moving dictionary to archive: " + dictionaryId);
        ServiceStatusDto status = service.deactivate(dictionaryId);
        status.setRequestId(ControllerRegistry.getNextRegisterId());
        return status;
    }

    @PutMapping("/activate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ServiceStatusDto activate(@RequestParam("dictionaryId") long dictionaryId) {
        logger.debug("Restoring dictionary from archive: " + dictionaryId);
        ServiceStatusDto status = service.activate(dictionaryId);
        status.setRequestId(ControllerRegistry.getNextRegisterId());
        return status;
    }

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public DictionaryDto getById(@RequestParam("dictionaryId") long dictionaryId) {
        logger.debug("Loading dictionary: " + dictionaryId);
        return mapper.mapToDto(service.getDictionaryById(dictionaryId));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public ServiceStatusDto deleteById(@RequestParam("dictionaryId") long dictionaryId) {
        logger.debug("Deleting dictionary: " + dictionaryId);
        return service.delete(dictionaryId);
    }

}
