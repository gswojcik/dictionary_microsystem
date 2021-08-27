package com.gswcode.dictionarywebservice.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "dict_conf", catalog = "dictionary", schema = "")
@NamedQueries({
        @NamedQuery(name = "DictConf.findAll", query = "SELECT d FROM DictConf d"),
        @NamedQuery(name = "DictConf.findById", query = "SELECT d FROM DictConf d WHERE d.id = :id"),
        @NamedQuery(name = "DictConf.findByDictName", query = "SELECT d FROM DictConf d WHERE d.dictName = :dictName"),
        @NamedQuery(name = "DictConf.findByDictDescription", query = "SELECT d FROM DictConf d WHERE d.dictDescription = :dictDescription"),
        @NamedQuery(name = "DictConf.findByIsActive", query = "SELECT d FROM DictConf d WHERE d.isActive = :isActive"),
        @NamedQuery(name = "DictConf.findByCreationTime", query = "SELECT d FROM DictConf d WHERE d.creationTime = :creationTime"),
        @NamedQuery(name = "DictConf.findByDeactivationTime", query = "SELECT d FROM DictConf d WHERE d.deactivationTime = :deactivationTime"),
        @NamedQuery(name = "DictConf.findByAuthor", query = "SELECT d FROM DictConf d WHERE d.author = :author")})
public class DictConf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "dict_name")
    private String dictName;
    @Column(name = "dict_description")
    private String dictDescription;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "creation_time")
    private Timestamp creationTime;
    @Column(name = "deactivation_time")
    private Timestamp deactivationTime;
    @Column(name = "author")
    private String author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDictConf", fetch = FetchType.LAZY)
    private List<DictItem> dictItemList;
    @OneToMany(mappedBy = "masterDictId", fetch = FetchType.LAZY)
    private List<DictConf> dictConfList;
    @JoinColumn(name = "master_dict_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DictConf masterDictId;

    public DictConf() {
    }

    public DictConf(Long id) {
        this.id = id;
    }

    public DictConf(Long id, String dictName) {
        this.id = id;
        this.dictName = dictName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictDescription() {
        return dictDescription;
    }

    public void setDictDescription(String dictDescription) {
        this.dictDescription = dictDescription;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getDeactivationTime() {
        return deactivationTime;
    }

    public void setDeactivationTime(Timestamp deactivationTime) {
        this.deactivationTime = deactivationTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<DictItem> getDictItemList() {
        return dictItemList;
    }

    public void setDictItemList(List<DictItem> dictItemList) {
        this.dictItemList = dictItemList;
    }

    public List<DictConf> getDictConfList() {
        return dictConfList;
    }

    public void setDictConfList(List<DictConf> dictConfList) {
        this.dictConfList = dictConfList;
    }

    public DictConf getMasterDictId() {
        return masterDictId;
    }

    public void setMasterDictId(DictConf masterDictId) {
        this.masterDictId = masterDictId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DictConf)) {
            return false;
        }
        DictConf other = (DictConf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gswcode.dictionarywebservice.domain.DictConf[ id=" + id + " ]";
    }

}
