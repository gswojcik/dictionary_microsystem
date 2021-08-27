package com.gswcode.dictionarywebservice.repository;

import com.gswcode.dictionarywebservice.domain.DictConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DictConfRepository extends JpaRepository<DictConf, Long> {

    @Query("select dc from DictConf dc where dc.dictName= ?1")
    Optional<DictConf> findByDictName(String name);

    @Query("select dc from DictConf dc where dc.isActive = true")
    List<DictConf> findAllActive();

}
