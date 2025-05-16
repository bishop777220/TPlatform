/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tsystem.tplatform.entityes;

import com.tsystem.tplatform.security.TPSecurityToken;
import com.tsystem.tplatform.security.TPUser;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;



/**
 *
 * @author Bishop
 */
@Entity
@Table(name = "tpithem")
@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public abstract class TPItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // Идентефикатор
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // Наименование
    @Column(name = "title")
    private String title;

    // Когда создано
    @Column(name = "created")
    private LocalDateTime created;

    // Кем создано
    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    private TPUser createdBy;

    // Дата последнего изменения
    @Column(name = "updated")
    private LocalDateTime updated;

    // Кем изменено
    @ManyToOne
    @JoinColumn(name = "updatedBy", referencedColumnName = "id")
    private TPUser updatedBy;

    // Номер версии
    @Version
    @Column(name = "version")
    private int version;

    // Родительный обьекты от которых наследуюся разришения
    @ManyToMany
    @JoinTable(name = "parentTPItem",
            joinColumns = @JoinColumn(name = "tpitem_id"),
            inverseJoinColumns = @JoinColumn(name = "parentTPItem_id"))
    private Set<TPItem> paretTPItem;

    // Жетоны безопасности
    @ManyToMany
    @JoinTable(name = "SecurityToken_DefinitionBindingCollection",
            joinColumns = @JoinColumn(name = "tpitem_id"),
            inverseJoinColumns = @JoinColumn(name = "tpsecurityToken_id"))
    private Set<TPSecurityToken> tpsecurityTokenList;

    public TPItem() {
        this.tpsecurityTokenList = new HashSet<>();
        this.paretTPItem = new HashSet<>();
    }

    // Метод исполняеться при создании
    @PrePersist
    protected void onCreate() {
        //this.updatedBy = this.createdBy = CurrentSession.getCurrentUser();
        this.updated = this.created = LocalDateTime.now();

    }

    // Метод исполняеться при изменении
    @PreUpdate
    protected void onUpdate() {
        //this.updatedBy = CurrentSession.getCurrentUser();
        this.updated = LocalDateTime.now();
    }

    /**
     * Возвращает уникальный идентефикатор
     *
     * @return UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Задает уникальный идентефикатор
     *
     * @param id (UUID)
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Возвращает наименование
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Задает наименование
     *
     * @param title (String)
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает дату создания
     *
     * @return (LocalDateTime)
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * Задает дату создания
     *
     * @param created (LocalDateTime)
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * Возвращает дату изменения
     *
     * @return (LocalDateTime)
     */
    public LocalDateTime getUpdated() {
        return updated;
    }

    /**
     * Задает дату изменения
     *
     * @param updated (LocalDateTime)
     */
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    /**
     * Возвращает кем создано
     *
     * @return (TPUser)
     */
    public TPUser getCreatedBy() {
        return createdBy;
    }

    /**
     * Задает кем создано
     *
     * @param createdBy (TPUser)
     */
    public void setCreatedBy(TPUser createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Возвращает кем изменено
     *
     * @return (TPUser)
     */
    public TPUser getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Задает кем изменено
     *
     * @param updatedBy (TPUser)
     */
    public void setUpdatedBy(TPUser updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Возвращает номер версии
     * @return (Integer)
     */
    public int getVersion() {
        return version;
    }

    /**
     * Возвращает набор родительных обьектов
     * @return (Set)
     */
    public Set<TPItem> getParetTPItem() {
        return paretTPItem;
    }

    /**
     * Задает набор родительных обьектов
     * @param paretTPItem (Set)
     */
    public void setParetTPItem(Set<TPItem> paretTPItem) {
        this.paretTPItem = paretTPItem;
    }

    /**
     * Возвращает набор жетонов безопасности
     * @return (Set)
     */
    public Set<TPSecurityToken> getTpsecurityTokenList() {
        return tpsecurityTokenList;
    }

    /**
     * Задает набор жетонов безопасности
     * @param tpsecurityTokenList (Set)
     */
    public void setTpsecurityTokenList(Set<TPSecurityToken> tpsecurityTokenList) {
        this.tpsecurityTokenList = tpsecurityTokenList;
    }

    /**
     * Возвращает hash обьекта
     */
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
