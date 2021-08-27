package com.gswcode.dictionarywebservice.repository;

import com.gswcode.dictionarywebservice.domain.DictItem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DictItemRepository extends JpaRepository<DictItem, Long> {

    @Query("SELECT d from DictItem d where d.idDictConf.id = ?1 and d.termName = ?2")
    Optional<DictItem> findByUniqueIndex(long dictionaryId, String name);

    @Query("SELECT d FROM DictItem d where d.idDictConf.id in (?1)")
    public List<DictItem> getItemsByDictionariesId(List<Long> id);

}
