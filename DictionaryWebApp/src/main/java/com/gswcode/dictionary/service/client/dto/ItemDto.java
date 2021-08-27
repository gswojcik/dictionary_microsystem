package com.gswcode.dictionary.service.client.dto;

public class ItemDto {
    private long id;
    private long dictionaryId;
    private String termName;
    private String termDescription;
    private boolean active;
    private long masterItemId;

    public ItemDto() {
    }

    public ItemDto(long id, long dictionaryId, String term, String description, boolean active, long masterItemId) {
        this.id = id;
        this.dictionaryId = dictionaryId;
        this.termName = term;
        this.termDescription = description;
        this.active = active;
        this.masterItemId = masterItemId;
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

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermDescription() {
        return termDescription;
    }

    public void setTermDescription(String termDescription) {
        this.termDescription = termDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getMasterItemId() {
        return masterItemId;
    }

    public void setMasterItemId(long masterItemId) {
        this.masterItemId = masterItemId;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", dictionaryId=" + dictionaryId +
                ", termName='" + termName + '\'' +
                ", termDescription='" + termDescription + '\'' +
                ", active=" + active +
                ", masterItemId=" + masterItemId +
                '}';
    }

}
