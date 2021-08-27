package com.gswcode.dictionary.service.service;

import com.gswcode.dictionary.service.client.RestClient;
import com.gswcode.dictionary.service.client.dto.DictionaryDto;
import com.gswcode.dictionary.service.client.mapper.DictionaryMapper;
import com.gswcode.dictionary.service.model.Dictionary;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    private final Logger logger = LoggerFactory.getLogger(DictionaryService.class);
    private final RestClient restClient;
    private final DictionaryMapper mapper;

    public DictionaryService(RestClient restClient, DictionaryMapper mapper) {
        this.restClient = restClient;
        this.mapper = mapper;
    }

    private boolean serverAvailable = true;

    public List<Dictionary> getDictionaries() {
        List<DictionaryDto> dtos;
        try {
            dtos = Arrays.asList(restClient.getDictionaries());
            serverAvailable = true;
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage(), ex);
            dtos = new ArrayList<>();
            serverAvailable = false;
        }
        return mapper.mapToModelList(dtos);
    }

    public String saveDictionary(Dictionary model) {
        if (model.getId() > 0) {
            DictionaryDto dto = restClient.getDictionaryById(model.getId());
            dto.setName(model.getName());
            dto.setDescription(model.getDescription());
            dto.setAuthor(model.getAuthor());
            dto.setMasterDictionaryId(model.getMasterDictionaryId());
            return restClient.updateDictionary(dto).getMessage();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            model.setCreatedAt(LocalDateTime.now().format(formatter));
            DictionaryDto dto = mapper.mapToDto(model);
            return restClient.addDictionary(dto).getMessage();
        }
    }

    public Dictionary getDictionaryById(long id) {
        return mapper.mapToModel(restClient.getDictionaryById(id));
    }

    public String deactivateDictionary(long id) {
        return restClient.deactivateDictionary(id).getMessage();
    }

    public String activateDictionary(long id) {
        return restClient.activateDictionary(id).getMessage();
    }

    public String deleteDictionary(long id) {
        return restClient.deleteDictionary(id).getMessage();
    }

    public boolean isServerAvailable() {
        return serverAvailable;
    }

}
