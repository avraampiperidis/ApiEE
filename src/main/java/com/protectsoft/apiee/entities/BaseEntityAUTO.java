
package com.protectsoft.apiee.entities;

import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntityAUTO extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    public BaseEntityAUTO() {
    }
     
    public BaseEntityAUTO(Long id) {
        this.id = id;
    } 

    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId(Long id) {
        if(!Objects.equals(this.id, id)) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }
    
    public void overrideId(Long id) {
        this.id = id;
    }
    
}
