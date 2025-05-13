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
@Table(name = "tpuser")
public class TPUser extends TPSecurityMember {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "users_in_group",
            joinColumns = @JoinColumn(name = "tpuset_id"),
            inverseJoinColumns = @JoinColumn(name = "tpgroup_id"))
    private Set<TPGroup> tpgroupList;

    public TPUser() {
        this.tpgroupList = new HashSet<TPGroup>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        super.setTitle(getFirstName());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        super.setTitle(getFullName());
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        super.setTitle(getFullName());
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        super.setTitle(getFullName());
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TPGroup> getTpgroupList() {
        return tpgroupList;
    }

    public void setTpgroupList(Set<TPGroup> tpgroupList) {
        this.tpgroupList = tpgroupList;
    }

    public String getInitials() {
        String initials = "";
        if (this.firstName != null && this.lastName != null) {
            initials += this.firstName.toUpperCase().charAt(0) + this.lastName.toUpperCase().charAt(0);
        } else {
            initials += this.login.toUpperCase().charAt(0) + this.login.toUpperCase().charAt(1);
        }
        return initials;
    }

    public String getMononym() {
        String mononym = "";
        if (this.firstName != null && this.lastName != null && this.surname != null) {
            mononym += this.lastName + " " + this.firstName.toUpperCase().charAt(0) + "." + this.surname.toUpperCase().charAt(0) + ".";
        } else if (this.firstName != null && this.lastName != null && this.surname == null) {
            mononym += this.lastName + " " + this.firstName.toUpperCase().charAt(0) + ".";
        } else {
            mononym += this.login;
        }
        return mononym;
    }

    public String getFullName() {
        String fullName = "";
        if (this.lastName != null || this.firstName !=null || this.surname != null ){
            if(this.lastName != null ){
                fullName += this.lastName;
            }
            if (this.firstName != null) {
                fullName += " " + this.firstName;
            }
            if (this.surname != null) {
                fullName += " " + this.surname;
            }
        } else {
            fullName = this.login;
        }
        return fullName;
    }
}
