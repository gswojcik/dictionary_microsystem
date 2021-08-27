package com.gswcode.dictionarywebservice.service;

import com.gswcode.dictionarywebservice.domain.DictConf;
import com.gswcode.dictionarywebservice.dto.ServiceStatusDto;
import com.gswcode.dictionarywebservice.repository.DictConfRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public class DictionaryService {

    private final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    private DictConfRepository repo;

    public List<DictConf> getDictionaryList() {
        return repo.findAll();
    }

    public DictConf getDictionaryById(long id) {
        Optional<DictConf> opt = repo.findById(id);
        return opt.orElseGet(DictConf::new);
    }

    public ServiceStatusDto save(DictConf dictConf) {
        logger.debug("Saving object: " + dictConf);
        Optional<DictConf> optDict = repo.findByDictName(dictConf.getDictName());
        ServiceStatusDto status = new ServiceStatusDto();
        if (optDict.isPresent()) {
            String msg = "Dictionary with name " + dictConf.getDictName() + " already exists! Please set other name";
            logger.debug(msg);
            status.setMessage(msg);
            status.setSuccess(false);
        } else {
            dictConf.setIsActive(true);
            if (dictConf.getCreationTime() == null) {
                dictConf.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
            }
            repo.save(dictConf);
            boolean isObjectSaved = dictConf.getId() != null;
            logger.debug("is object saved: " + isObjectSaved);
            if (isObjectSaved) {
                status.setMessage("Object added");
                status.setSuccess(true);
            } else {
                status.setMessage("We can not add new dictionary at this moment, please try later");
                status.setSuccess(false);
            }
        }
        return status;
    }

    public ServiceStatusDto update(DictConf dictConf) {
        logger.debug("Updating object: " + dictConf);
        ServiceStatusDto status = new ServiceStatusDto();

        Optional<DictConf> checkName = repo.findByDictName(dictConf.getDictName());
        if (checkName.isPresent() && checkName.get().getId().longValue() != dictConf.getId().longValue()) {
            String msg = "Dictionary with name " + dictConf.getDictName() + " already exists! Please set other name";
            logger.debug(msg);
            status.setMessage(msg);
            status.setSuccess(false);
            return status;
        }
        Optional<DictConf> optDict = repo.findById(dictConf.getId());
        if (optDict.isPresent()) {
            DictConf domain = optDict.get();
            domain.setDictName(dictConf.getDictName());
            domain.setDictDescription(dictConf.getDictDescription());
            domain.setAuthor(dictConf.getAuthor());
            if (!dictConf.equals(dictConf.getMasterDictId()))
                domain.setMasterDictId(dictConf.getMasterDictId());
            repo.save(domain);
            status.setMessage("Dictionary updated");
            if (dictConf.equals(dictConf.getMasterDictId()))
                status.setMessage("Dictionary updated partially, it is not allowed to set master dictionary id the same as current dictionary id");
            status.setSuccess(true);
        } else {
            status.setMessage("We can not update thew dictionary at this moment, please try later");
            status.setSuccess(false);
        }
        return status;
    }

    public ServiceStatusDto deactivate(long id) {
        logger.debug("Deactivating dictionary: " + id);
        ServiceStatusDto status = new ServiceStatusDto();
        Optional<DictConf> optDict = repo.findById(id);
        if (optDict.isPresent()) {
            DictConf domain = optDict.get();
            if (domain.getIsActive()) {
                domain.setIsActive(false);
                domain.setDeactivationTime(Timestamp.valueOf(LocalDateTime.now()));
                domain.getDictItemList().forEach(t -> t.setTermActive(false));
                repo.save(domain);
                status.setMessage("Dictionary with items deactivated: " + domain.getDictName());
                status.setSuccess(true);
            } else {
                status.setMessage("Dictionary " + domain.getDictName() + " is already archived!");
                status.setSuccess(false);
            }
        } else {
            status.setMessage("We can not update thew dictionary at this moment, please try later");
            status.setSuccess(false);
        }
        return status;
    }

    public ServiceStatusDto activate(long id) {
        logger.debug("Activating dictionary: " + id);
        ServiceStatusDto status = new ServiceStatusDto();
        Optional<DictConf> optDict = repo.findById(id);
        if (optDict.isPresent()) {
            DictConf domain = optDict.get();
            if (!domain.getIsActive()) {
                domain.setIsActive(true);
                domain.getDictItemList().forEach(t -> t.setTermActive(true));
                repo.save(domain);
                status.setMessage("Dictionary with items activated: " + domain.getDictName());
                status.setSuccess(true);
            } else {
                status.setMessage("Dictionary " + domain.getDictName() + " is already active!");
                status.setSuccess(false);
            }
        } else {
            status.setMessage("We can not update thew dictionary at this moment, please try later");
            status.setSuccess(false);
        }
        return status;
    }

    public ServiceStatusDto delete(long id) {
        boolean exists = repo.findById(id).isPresent();
        ServiceStatusDto status = new ServiceStatusDto();
        if (exists) {
            repo.deleteById(id);
            status.setMessage("Dictionary has been deleted");
            status.setSuccess(true);
        } else {
            status.setMessage("That dictionary doesn't exist");
            status.setSuccess(false);
        }
        return status;
    }

}
