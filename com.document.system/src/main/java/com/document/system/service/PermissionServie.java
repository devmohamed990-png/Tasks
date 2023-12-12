package com.document.system.service;

import com.document.system.enums.PermissionLevelEnum;
import com.document.system.model.Permission;

public interface PermissionServie {

    Permission createPermission(String email, PermissionLevelEnum permissionLevel, String group);
    boolean hasPermission(String email, String space, PermissionLevelEnum permissionLevel);

    boolean hasAuthority(String email, Long id);

    Permission getPermission(String email);
}
