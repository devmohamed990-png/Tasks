package com.document.system.model;

import com.document.system.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FILES")
@Setter
@Getter
public class File extends BaseEntity {

    @Lob
    @Column(name = "binary_data")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
}
