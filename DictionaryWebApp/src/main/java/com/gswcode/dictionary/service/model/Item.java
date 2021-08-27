package com.gswcode.dictionary.service.model;

public class Item {
    private long id;
    private long dictionaryId;
    private String dictionaryName; 
    private boolean isSubdictionary;
    private String term;
    private String description;
    private String status;
    private long aliasId;
    private String alias;

    public Item() {
    }

    public Item(long id, long dictionaryId, String dictionaryName, boolean isSubdictionary, String term, String description, String status, long aliasId, String alias) {
        this.id = id;
        this.dictionaryId = dictionaryId;
        this.dictionaryName = dictionaryName;
        this.isSubdictionary = isSubdictionary;
        this.term = term;
        this.description = description;
        this.status = status;
        this.aliasId = aliasId;
        this.alias = alias;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public boolean isIsSubdictionary() {
        return isSubdictionary;
    }

    public void setIsSubdictionary(boolean isSubdictionary) {
        this.isSubdictionary = isSubdictionary;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAliasId() {
        return aliasId;
    }

    public void setAliasId(long aliasId) {
        this.aliasId = aliasId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}
