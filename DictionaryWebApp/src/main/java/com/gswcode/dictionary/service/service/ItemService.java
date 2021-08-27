package com.gswcode.dictionary.service.service;

import com.gswcode.dictionary.service.client.RestClient;
import com.gswcode.dictionary.service.client.dto.ItemDto;
import com.gswcode.dictionary.service.client.dto.ServiceStatusDto;
import com.gswcode.dictionary.service.client.mapper.ItemMapper;
import com.gswcode.dictionary.service.model.Item;
import com.gswcode.dictionary.service.model.Status;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    private RestClient restClient;

    @Autowired
    private ItemMapper mapper;
    
    public List<Item> getItems(long dictionaryId) {
        List<Item> list = mapper.mapToModelList(restClient.getItemsByDictionaryId(dictionaryId));
        list.forEach(t -> t.setIsSubdictionary(mapper.validateSubdictionary(dictionaryId, t)));
        return list;
    }
    
    public String saveItem(Item model) {
        if (model.getId() > 0) {
            ItemDto dto = restClient.getItemById(model.getId());
            dto.setTermName(model.getTerm());
            dto.setTermDescription(model.getDescription());
            dto.setMasterItemId(model.getAliasId());
            return restClient.updateItem(dto).getMessage();
        } else {
            model.setStatus(Status.ACTIVE);
            ItemDto dto = mapper.mapToDto(model);
            return restClient.addItem(dto).getMessage();
        }
    }
    
    public Item getItemById(long id) {
        return mapper.mapToModel(restClient.getItemById(id));
    }
    
    public String deactivateItem(long id) {
        return restClient.deactivateItem(id).getMessage();
    }

    public ServiceStatusDto activateItem(long id) {
        return restClient.activateItem(id);
    }

    public String deleteItem(long id) {
         return restClient.deleteItem(id).getMessage();
    }
    
}
