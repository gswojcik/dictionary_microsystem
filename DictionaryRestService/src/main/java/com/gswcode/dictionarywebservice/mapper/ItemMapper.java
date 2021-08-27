package com.gswcode.dictionarywebservice.mapper;

import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.domain.DictItem;
import com.gswcode.dictionarywebservice.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    private final Logger logger = LoggerFactory.getLogger(ItemMapper.class);

    public List<ItemDto> mapToListDto(List<DictItem> list) {
        List<ItemDto> dtos = new ArrayList<>();
        list.forEach(dictionary -> {
            dtos.add(mapToDto(dictionary));
        });

        logger.debug("Objects mapped successfully, quantity: " + dtos.size());
        return dtos;
    }

    public ItemDto mapToDto(DictItem domain) {
        logger.debug("Mapping item domain to dto: " + domain);
        long idDictConf = 0;
        if (domain.getIdDictConf() != null) {
            idDictConf = domain.getIdDictConf().getId();
        }
        long idMasterItem = 0;
        if (domain.getAliasId() != null) {
            idMasterItem = domain.getAliasId().getId();
        }
        return new ItemDto(
                domain.getId(),
                idDictConf,
                domain.getTermName(),
                domain.getTermDescription(),
                domain.getTermActive(),
                idMasterItem);
    }

    public DictItem mapToDomain(ItemDto dto) {
        logger.debug("Mapping item dto to domain: " + dto);
        DictItem item = new DictItem();
        if (dto.getId() != null && dto.getId() != 0)
            item.setId(dto.getId());
        item.setTermName(dto.getTermName());
        item.setTermDescription(dto.getTermDescription());
        if (dto.getMasterItemId() != null && dto.getMasterItemId() != 0) {
            item.setAliasId(new DictItem(dto.getMasterItemId()));
        }
        if (dto.getDictionaryId() != null && dto.getDictionaryId() != 0) {
            item.setIdDictConf(new DictConf(dto.getDictionaryId()));
        }
        return item;
    }

}
