package com.document.system.model;

import com.document.system.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERMISSION_GROUPS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGroup extends BaseEntity {

    @Column(name = "group_name", nullable = false, unique = true)
    private String name;
}
