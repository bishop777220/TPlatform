/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tsystem.tplatform.entityes;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.tsystem.tplatform.security.TPSecurityToken;
import com.tsystem.tplatform.security.TPUser;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "tpithem")
@Inheritance(strategy = InheritanceType.JOINED)
public class TPItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;
    
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne    
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    private TPUser createdBy;
    
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @ManyToOne    
    @JoinColumn(name = "updatedBy", referencedColumnName = "id")
    private TPUser updatedBy;
    
    @Version
    @Column(name = "version")
    private int version;
    
    @ManyToMany
	@JoinTable(name = "parentTPItem", 
	      joinColumns = @JoinColumn(name = "tpitem_id"), 
	      inverseJoinColumns = @JoinColumn(name = "parentTPItem_id"))
    private Set<TPItem> paretTPItem;

    @ManyToMany
	@JoinTable(name = "SecurityToken_DefinitionBindingCollection", 
	      joinColumns = @JoinColumn(name = "tpitem_id"), 
	      inverseJoinColumns = @JoinColumn(name = "tpsecurityToken_id"))
    private Set<TPSecurityToken> tpsecurityTokenList;
    
    public TPItem() {
        this.tpsecurityTokenList = new HashSet<TPSecurityToken>();
        this.paretTPItem = new HashSet<TPItem>();
    }

    @PrePersist
    protected void onCreate() {
        this.updatedBy = this.createdBy = CurrentSession.getCurrentUser();
        this.updated = this.created = new Date();
        
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedBy = CurrentSession.getCurrentUser();
        this.updated = new Date();
    }

    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public TPUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(TPUser createdBy) {
        this.createdBy = createdBy;
    }

    public TPUser getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(TPUser updatedBy) {
        this.updatedBy = updatedBy;
    }
    

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<TPItem> getParetTPItem() {
        return paretTPItem;
    }

    public void setParetTPItem(Set<TPItem> paretTPItem) {
        this.paretTPItem = paretTPItem;
    }

    public Set<TPSecurityToken> getTpsecurityTokenList() {
        return tpsecurityTokenList;
    }

    public void setTpsecurityTokenList(Set<TPSecurityToken> tpsecurityTokenList) {
        this.tpsecurityTokenList = tpsecurityTokenList;
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
        if (!(object instanceof TPItem)) {
            return false;
        }
        TPItem other = (TPItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tplatform.entityes.TPItem[ id=" + id + " ]";
    }

}
