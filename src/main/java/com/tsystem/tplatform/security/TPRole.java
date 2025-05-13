/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tsystem.tplatform.security;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "tprole")
public class TPRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;
    
    @Column(name = "level")
    private Integer level;
    
    @OneToMany(mappedBy = "tprole", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<TPSecurityToken> tpsecurityTokenList;

    public TPRole() {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<TPSecurityToken> getTpsecurityTokenList() {
        return tpsecurityTokenList;
    }

    public void setTpsecurityTokenList(List<TPSecurityToken> tpsecurityTokenList) {
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
        if (!(object instanceof TPRole)) {
            return false;
        }
        TPRole other = (TPRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tplatform.entityes.security.TPRole[ id=" + id + " ]";
    }
    
}
