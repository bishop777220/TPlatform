/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.tsystem.tplatform.log;

import com.tsystem.tplatform.entityes.TPItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Bishop
 */
@Entity
@Table(name = "tplogger")
public class TPLogger implements Serializable {
    
    private static final long serialVersionUID = 1L;

    // Идентефикатор
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    
    @ManyToOne    
    @JoinColumn(name = "fileTemplate", referencedColumnName = "id")
    private TPItem tpitem;

    public TPLogger(TPItem tpitem) {
        this.tpitem = tpitem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TPItem getTpitem() {
        return tpitem;
    }

    public void setTpitem(TPItem tpitem) {
        this.tpitem = tpitem;
    }
    
    
}
