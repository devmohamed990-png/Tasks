package com.document.system.service.impl;

import com.document.system.enums.PermissionLevelEnum;
import com.document.system.model.Permission;
import com.document.system.model.PermissionGroup;
import com.document.system.repository.PermissionRepository;
import com.document.system.service.ItemService;
import com.document.system.service.PermissionGroupService;
import com.document.system.service.PermissionServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServieImpl implements PermissionServie {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionGroupService permissionGroupService;
    @Autowired
    private ItemService itemService;

    @Override
    public Permission createPermission(String email, PermissionLevelEnum permissionLevel, String group) {
        PermissionGroup permissionGroup = permissionGroupService.createGroup(group);
        return permissionRepository.save(new Permission(email, permissionLevel, permissionGroup));
    }

    @Override
    public boolean hasPermission(String email, String itemName, PermissionLevelEnum permissionLevel) {
        Permission permission = getPermission(email);
        if(permission != null && permission.getPermissionGroup().getId().equals(itemService.getItem(itemName).getPermissionGroup().getId())
                && permission.getPermissionLevel() == permissionLevel) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasAuthority(String email, Long itemId) {
        Permission permission = getPermission(email);
        if(permission != null && permission.getPermissionGroup().getId().equals(itemService.getItem(itemId).getPermissionGroup().getId())) {
            return true;
        }
        return false;
    }

    @Override
    public Permission getPermission(String email) {
        return permissionRepository.findByEmail(email);
    }
}
