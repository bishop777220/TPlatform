/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tsystem.tplatform.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "tpgroup")
public class TPGroup extends TPSecurityMember {

    @Column(name = "description")
    private String description;
    
    @ManyToMany
	@JoinTable(name = "users_in_group", 
	      joinColumns = @JoinColumn(name = "tpgroup_id"), 
	      inverseJoinColumns = @JoinColumn(name = "tpuset_id"))
    private Set<TPUser> tpuserList;
    
    public TPGroup() {
        this.tpuserList = new HashSet<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TPUser> getTpuserList() {
        return tpuserList;
    }

    public void setTpuserList(Set<TPUser> tpuserList) {
        this.tpuserList = tpuserList;
    }

}
