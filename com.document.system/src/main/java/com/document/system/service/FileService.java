package com.document.system.service;

import com.document.system.model.File;
import org.springframework.core.io.Resource;

public interface FileService {
    File createFile(String email);

    Resource getFile(Long id, String email);
}
