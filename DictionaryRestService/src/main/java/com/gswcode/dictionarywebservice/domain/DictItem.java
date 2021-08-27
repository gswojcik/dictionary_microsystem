package com.gswcode.dictionarywebservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "dict_item", catalog = "dictionary", schema = "")
@NamedQueries({
        @NamedQuery(name = "DictItem.findAll", query = "SELECT d FROM DictItem d"),
        @NamedQuery(name = "DictItem.findById", query = "SELECT d FROM DictItem d WHERE d.id = :id"),
        @NamedQuery(name = "DictItem.findByTermName", query = "SELECT d FROM DictItem d WHERE d.termName = :termName"),
        @NamedQuery(name = "DictItem.findByTermDescription", query = "SELECT d FROM DictItem d WHERE d.termDescription = :termDescription"),
        @NamedQuery(name = "DictItem.findByTermActive", query = "SELECT d FROM DictItem d WHERE d.termActive = :termActive")})
public class DictItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "term_name")
    private String termName;
    @Column(name = "term_description")
    private String termDescription;
    @Column(name = "term_active")
    private boolean termActive;
    @JoinColumn(name = "id_dict_conf", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DictConf idDictConf;
    @OneToMany(mappedBy = "aliasId", fetch = FetchType.LAZY)
    private List<DictItem> dictItemList;
    @JoinColumn(name = "alias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DictItem aliasId;

    public DictItem() {
    }

    public DictItem(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean getTermActive() {
        return termActive;
    }

    public void setTermActive(boolean termActive) {
        this.termActive = termActive;
    }

    public DictConf getIdDictConf() {
        return idDictConf;
    }

    public void setIdDictConf(DictConf idDictConf) {
        this.idDictConf = idDictConf;
    }

    public List<DictItem> getDictItemList() {
        return dictItemList;
    }

    public void setDictItemList(List<DictItem> dictItemList) {
        this.dictItemList = dictItemList;
    }

    public DictItem getAliasId() {
        return aliasId;
    }

    public void setAliasId(DictItem aliasId) {
        this.aliasId = aliasId;
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
        if (!(object instanceof DictItem)) {
            return false;
        }
        DictItem other = (DictItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gswcode.dictionarywebservice.domain.DictItem[ id=" + id + " ]";
    }

}
