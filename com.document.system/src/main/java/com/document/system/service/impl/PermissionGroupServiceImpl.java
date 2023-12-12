package com.document.system.service.impl;

import com.document.system.model.PermissionGroup;
import com.document.system.repository.PermissionGroupRepository;
import com.document.system.service.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;

    @Override
    public PermissionGroup createGroup(String name) {
        if(name == null || name.isEmpty()) {
            return null;
        }
        PermissionGroup permissionGroup = permissionGroupRepository.findByName(name);
        if(permissionGroup != null) {
            return permissionGroup;
        }
        return permissionGroupRepository.save(new PermissionGroup(name));
    }
}
