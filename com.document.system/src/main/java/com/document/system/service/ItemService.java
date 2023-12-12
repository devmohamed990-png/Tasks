package com.document.system.service;

import com.document.system.model.Item;

public interface ItemService {

    Item createSpace();

    Item createFolder(String email);

    Item createFileItem(String email);

    Item getItem(String name);

    Item getItem(Long itemId);

    Item getFileMetadata(Long id, String email);
}
