package com.gswcode.dictionary.service.model;

public class ItemKey {
    private long id;
    private String itemName;
    private long idDictionary;
    private String dictionaryName;
    private String filter = "";

    public ItemKey() {
    }

    public ItemKey(long id, String itemName, long idDictionary, String dictionaryName) {
        this.id = id;
        this.itemName = itemName;
        this.idDictionary = idDictionary;
        this.dictionaryName = dictionaryName;
        if (!dictionaryName.isBlank())
            this.filter = dictionaryName + ": " + itemName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getIdDictionary() {
        return idDictionary;
    }

    public void setIdDictionary(long idDictionary) {
        this.idDictionary = idDictionary;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemKey other = (ItemKey) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    } 
    
}
