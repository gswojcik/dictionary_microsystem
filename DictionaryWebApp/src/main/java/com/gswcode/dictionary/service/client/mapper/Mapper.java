package com.gswcode.dictionary.service.client.mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @param <M> Model Object
 * @param <D> Dto Object of Model
 */
public interface Mapper<M, D> {
    
    DateTimeFormatter FORMAT_TO_MODEL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter FORMAT_TO_DTO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    
    List<M> mapToModelList(List<D> dtos);
    
    M mapToModel(D dto);
    
    D mapToDto(M dto);
    
}
