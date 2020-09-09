package com.evoltech.register.model.base;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseJpaEntity<ID> implements Persistable<ID> {

    private UUID guid;
    private LocalDateTime created;
    private LocalDateTime modified;

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }
    private void markNotNew(){
        this.isNew = false;
    }

    @PostLoad
    @PrePersist
    void onCreate() {
        markNotNew();
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
        this.setGuid(UUID.randomUUID());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }

}
