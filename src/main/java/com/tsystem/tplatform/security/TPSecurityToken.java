/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tsystem.tplatform.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.tsystem.tplatform.entityes.TPItem;

/** mvn install:install-file -Dfile=cxf-2.7.3.jar -DgroupId=org.apache.cxf -DartifactId=cxf-bundle -Dversion=2.7.3 -Dpackaging=jar
 *
 * @author mrbis
 */
@Entity
@Table(name = "tpsecuritytoken")
public class TPSecurityToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @ManyToMany
	@JoinTable(name = "SecurityToken_DefinitionBindingCollection", 
	      joinColumns = @JoinColumn(name = "tpsecurityToken_id"), 
	      inverseJoinColumns = @JoinColumn(name = "tpitem_id"))
    private Set<TPItem> tpitemList;
    
    @ManyToOne    
    @JoinColumn(name = "tpsecurityMember", referencedColumnName = "id")
    private TPSecurityMember tpsecurityMember;
    
    @ManyToOne    
    @JoinColumn(name = "tprole", referencedColumnName = "id")
    private TPRole tprole;

    public TPSecurityToken() {
        this.tpitemList = new HashSet<TPItem>();
    }

    public TPSecurityToken(TPSecurityMember tpsecurityMember, TPRole tprole, Set<TPItem> tpitemList) {
        this.tpitemList = tpitemList;
        this.tpsecurityMember = tpsecurityMember;
        this.tprole = tprole;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<TPItem> getTpitemList() {
        return tpitemList;
    }

    public void setTpitemList(Set<TPItem> tpitemList) {
        this.tpitemList = tpitemList;
    }

    public TPSecurityMember getTpsecurityMember() {
        return tpsecurityMember;
    }

    public void setTpsecurityMember(TPSecurityMember tpsecurityMember) {
        this.tpsecurityMember = tpsecurityMember;
    }

    public TPRole getTprole() {
        return tprole;
    }

    public void setTprole(TPRole tprole) {
        this.tprole = tprole;
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
        if (!(object instanceof TPSecurityToken)) {
            return false;
        }
        TPSecurityToken other = (TPSecurityToken) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tplatform.entityes.security.TPSecurityToken[ id=" + id + " ]";
    }
    
}
