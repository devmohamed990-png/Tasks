package com.document.system.service.impl;

import com.document.system.enums.FileTypeEnum;
import com.document.system.enums.PermissionLevelEnum;
import com.document.system.exception.AccessDeniedException;
import com.document.system.exception.ResourceExistException;
import com.document.system.exception.ResourceNotFoundException;
import com.document.system.model.Item;
import com.document.system.model.Permission;
import com.document.system.repository.ItemRepository;
import com.document.system.service.ItemService;
import com.document.system.service.PermissionServie;
import com.document.system.utils.Constants;
import com.document.system.utils.FileUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PermissionServie permissionServie;

    @Value("${storage.path}")
    private String storagePath;

    @Transactional
    @Override
    public Item createSpace() {
        Item item = itemRepository.findByName(Constants.SPACE_NAME);
        if(item != null) {
            throw new ResourceExistException("This Space already exists");
        } else {
            FileUtils.createFolder(storagePath + Constants.SPACE_NAME);
            Permission adminPermission = permissionServie.createPermission(Constants.ADMIN_USER, PermissionLevelEnum.EDIT, Constants.GROUP_NAME);
            Permission viewPermission = permissionServie.createPermission(Constants.VIEW_USER, PermissionLevelEnum.VIEW, Constants.GROUP_NAME);
            item = new Item();
            item.setType(FileTypeEnum.Space);
            item.setName(Constants.SPACE_NAME);
            item.setPermissionGroup(adminPermission.getPermissionGroup());
            return itemRepository.save(item);
        }
    }

    @Transactional
    @Override
    public Item createFolder(String email) {
        Item item = null;
        if(permissionServie.hasPermission(email, Constants.SPACE_NAME, PermissionLevelEnum.EDIT)) {
            item = itemRepository.findByName(Constants.FOLDER_NAME);
            if(item != null) {
                throw new ResourceExistException("This folder already exists");
            } else {
                FileUtils.createFolder(storagePath + Constants.FOLDER_NAME);
                item = new Item();
                item.setType(FileTypeEnum.Folder);
                item.setName(Constants.FOLDER_NAME);
                item.setPermissionGroup(permissionServie.getPermission(email).getPermissionGroup());
                return itemRepository.save(item);
            }
        } else {
            throw new AccessDeniedException("This user doesn't have access");
        }
    }

    @Transactional
    @Override
    public Item createFileItem(String email) {
        Item item = null;
        if(permissionServie.hasPermission(email, Constants.SPACE_NAME, PermissionLevelEnum.EDIT)) {
            item = itemRepository.findByName(Constants.FILE_NAME);
            if(item != null) {
                throw new ResourceExistException("This file already exists");
            } else {
                item = new Item();
                item.setType(FileTypeEnum.File);
                item.setName(Constants.FILE_NAME);
                item.setPermissionGroup(permissionServie.getPermission(email).getPermissionGroup());
                return itemRepository.save(item);
            }
        } else {
            throw new AccessDeniedException("This user doesn't have access");
        }
    }

    @Override
    public Item getItem(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public Item getItem(Long itemId) {
        Optional<Item> optional = itemRepository.findById(itemId);
        if(optional.isEmpty()) {
            throw new ResourceNotFoundException("This item doesn't exist");
        }
        return optional.get();
    }

    @Override
    public Item getFileMetadata(Long id, String email) {
        Item item = itemRepository.getItem(id, email);
        if(item != null) {
            return item;
        }
        throw new AccessDeniedException("This user doesn't have access");
    }
}
