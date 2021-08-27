package com.gswcode.dictionary.service.model;

public class Dictionary {
    private long id;
    private String name;
    private String description;
    private String author;
    private String createdAt;
    private String status;
    private long masterDictionaryId;
    private String masterDictionaryName;
    private int items;

    public Dictionary() {
    }

    public Dictionary(long id, String name, String description, String author, String createdAt, String status, long masterDictionaryId, String masterDictionaryName, int items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.createdAt = createdAt;
        this.status = status;
        this.masterDictionaryId = masterDictionaryId;
        this.masterDictionaryName = masterDictionaryName;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getMasterDictionaryId() {
        return masterDictionaryId;
    }

    public void setMasterDictionaryId(long masterDictionaryId) {
        this.masterDictionaryId = masterDictionaryId;
    }

    public String getMasterDictionaryName() {
        return masterDictionaryName;
    }

    public void setMasterDictionaryName(String masterDictionaryName) {
        this.masterDictionaryName = masterDictionaryName;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }                   
    
}
