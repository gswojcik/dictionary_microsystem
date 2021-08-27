package com.gswcode.dictionary.service.client.mapper;

import com.gswcode.dictionary.service.client.dto.ItemDto;
import com.gswcode.dictionary.service.model.Item;
import com.gswcode.dictionary.service.model.ItemKey;
import com.gswcode.dictionary.service.model.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<Item, ItemDto> {

    public final static Map<Long, ItemKey> ITEM_NAMES_MAP = new HashMap<>();
    
    @Override
    public List<Item> mapToModelList(List<ItemDto> dtos) {
        ITEM_NAMES_MAP.clear();
        ITEM_NAMES_MAP.put(0l, new ItemKey(0, "", 0, ""));
        dtos.forEach(dto -> ITEM_NAMES_MAP.put(dto.getId(), new ItemKey(dto.getId(), dto.getTermName(), dto.getDictionaryId(), DictionaryMapper.NAMES_MAP.get(dto.getDictionaryId()).getName())));
        List<Item> items = new ArrayList<>();
        dtos.forEach(dto -> {
            items.add(mapToModel(dto));
        });
        return items;
    }

    @Override
    public Item mapToModel(ItemDto dto) {
        Item item = new Item();
        item.setDictionaryName(DictionaryMapper.NAMES_MAP.get(dto.getDictionaryId()).getName());
        item.setId(dto.getId());
        item.setDictionaryId(dto.getDictionaryId());
        item.setIsSubdictionary(false);
        item.setTerm(dto.getTermName());
        item.setDescription(dto.getTermDescription());
        String status = dto.isActive() ? Status.ACTIVE : Status.ARCHIVED;
        item.setStatus(status);
        item.setAliasId(dto.getMasterItemId());
        if (dto.getMasterItemId()> 0)
            item.setAlias(ITEM_NAMES_MAP.get(dto.getMasterItemId()).getItemName());
        return item;
    }

    @Override
    public ItemDto mapToDto(Item domain) {
        boolean isActive = domain.getStatus().equals(Status.ACTIVE);
        return new ItemDto(
                domain.getId(), 
                domain.getDictionaryId(), 
                domain.getTerm(), 
                domain.getDescription(), 
                isActive, 
                domain.getAliasId());        
    }
    
    public boolean validateSubdictionary(long masterDictionaryId, Item model) {
        return model.getDictionaryId() != masterDictionaryId;
    }

}
