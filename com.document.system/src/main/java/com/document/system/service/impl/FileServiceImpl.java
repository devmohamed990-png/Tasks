package com.document.system.service.impl;

import com.document.system.exception.AccessDeniedException;
import com.document.system.exception.ResourceNotFoundException;
import com.document.system.model.File;
import com.document.system.repository.FileRepository;
import com.document.system.service.FileService;
import com.document.system.service.ItemService;
import com.document.system.service.PermissionServie;
import com.document.system.utils.Constants;
import com.document.system.utils.PDFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private PermissionServie permissionServie;
    @Autowired
    private ItemService itemService;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public File createFile(String email) {
            try {
                File file = new File();
                file.setData(Files.readAllBytes(PDFUtils.createPDFFile(storagePath + Constants.FILE_NAME)));
                file.setItem(itemService.createFileItem(email));
                return fileRepository.save(file);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("an error occurred, Please, try another file");
            }
    }

    private File getFile(long id) {
        Optional<File> optional = fileRepository.findById(id);
        if(optional.isEmpty()) {
            throw new ResourceNotFoundException("This file doesn't exist");
        }
        return optional.get();
    }

    @Override
    public Resource getFile(Long id, String email) {
        File file = getFile(id);
        if(permissionServie.hasAuthority(email, file.getItem().getId())) {
            Path path = Paths.get(storagePath + file.getItem().getName());
            try {
                Resource resource = new UrlResource(path.toUri());
                if(resource.exists()) {
                    return resource;
                } else {
                    throw new ResourceNotFoundException("This file not found");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("an error occurred, Please, try another file");
            }
        } else {
            throw new AccessDeniedException("This user doesn't have access");
        }
    }
}
