package com.document.system.model;

import com.document.system.enums.FileTypeEnum;
import com.document.system.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ITEM")
@Setter
@Getter
public class Item extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FileTypeEnum type;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "permission_group_id", referencedColumnName = "id")
    private PermissionGroup permissionGroup;
}
